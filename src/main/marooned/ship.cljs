(ns marooned.ship
  (:require [marooned.util :as u :refer [PI PIp2 sin cos]]
            [marooned.svg :as svg]
            [marooned.audio :as audio]))


(def ^:const max-dh (/ PI 1000.0))
(def ^:const min-dh (- max-dh))
(def ^:const side-thruster-delta (/ max-dh 80.0))
(def ^:const forward-thruster-delta 0.01)


(def hull-points [[0 -20]
                  [18 12]
                  [12 20]
                  [-12 20]
                  [-18 12]])


(defn- make-thruster-cone [x y r]
  (svg/g {:translate [x y]
          :rotate    r
          :display   "none"}
         (svg/ellipse {:fill "yellow"
                       :cx   0
                       :cy   0
                       :rx   3
                       :ry   3}
                      (svg/animate {:attributeName "ry"
                                    :values        "3;6;3"
                                    :dur           "100ms"
                                    :repeatCount   "indefinite"})
                      (svg/animate {:attributeName "fill-opacity"
                                    :values        "0.5;1.0;0.5"
                                    :dur           "100ms"
                                    :repeatCount   "indefinite"}))))


(defn create-ship [state]
  (let [left-thruster-cone    (make-thruster-cone 8 -8 90)
        right-thruster-cone   (make-thruster-cone -8 -8 -90)
        forward-thruster-cone (make-thruster-cone 0 20 0)
        hull                  (svg/g left-thruster-cone
                                     right-thruster-cone
                                     forward-thruster-cone
                                     (svg/polygon {:stroke       "red"
                                                   :stroke-width 3
                                                   :fill         "var(--text-color)"
                                                   :points       hull-points}))
        speed                 (svg/line {:stroke       "green"
                                         :stroke-width 2
                                         :x1           0
                                         :y1           0
                                         :x2           0
                                         :y2           0})
        g                     (svg/g hull
                                     speed)]
    (assoc state :ship {:x        0
                        :y        500
                        :h        PIp2
                        :vh       0
                        :vs       0
                        :dh       0
                        :thruster {:left    left-thruster-cone
                                   :right   right-thruster-cone
                                   :forward forward-thruster-cone}
                        :hull     hull
                        :speed    speed
                        :g        g})))


(defn handle-reset [state]
  (if (and (-> state :control :reset :on)
           (= (-> state :control :reset :ts)
              (-> state :ts)))
    (update state :ship merge {:x  0
                               :y  500
                               :h  PIp2
                               :vh 0
                               :vs 0
                               :dh 0})
    state))


(defn- hull-point-inside [shape ship-x ship-y h]
  (let [sin-h (sin h)
        cos-h (cos h)
        pt    (svg/point)]
    (fn [[x y]]
      ; x' = 𝑥 cos 𝜃 + 𝑦 sin 𝜃
      ; y' = 𝑦 cos 𝜃 − 𝑥 sin 𝜃
      (let [x' (+ (* x cos-h) (* y sin-h))
            y' (- (* y cos-h) (* x sin-h))]
        (svg/is-xy-in? shape (svg/set-point pt (+ ship-x x') (+ ship-y y')))))))

#_(defn- ship-inside? [shape ship-x ship-y ship-h]
    (every? (hull-point-inside shape ship-x ship-y ship-h) hull-points))

(defn- ship-touches? [shape ship-x ship-y ship-h]
  (some (hull-point-inside shape ship-x ship-y ship-h) hull-points))


(defn handle-cave-collision [state]
  state)


(defn set-thruster [state thruster on?]
  (svg/set-attr (-> state :ship :thruster thruster) "display" (if on? "on" "none"))
  (if on?
    (audio/play-on state thruster)
    (audio/play-off state thruster 100)))


(defn handle-thrusters [state]
  (let [dt            (:dt state)
        ts            (:ts state)
        left          (-> state :control :left)
        right         (-> state :control :right)
        forward       (-> state :control :forward)
        side-delta    (+ (if (:on left) (- side-thruster-delta) 0.0)
                         (if (:on right) side-thruster-delta 0.0))
        forward-delta (if (:on forward) forward-thruster-delta 0.0)
        ship          (:ship state)
        dh            (u/clamp min-dh (+ (:dh ship) side-delta) max-dh)
        h             (+ (:h ship) (* dt dh))
        new-telemetry (u/vec+ (:vh ship) (:vs ship) h forward-delta)
        vh            (:h new-telemetry)
        vs            (:v new-telemetry)
        x             (+ (:x ship) (:dx new-telemetry))
        y             (+ (:y ship) (:dy new-telemetry))]
    (svg/set-attr (:g ship) :translate [x y])
    (svg/set-attr (:speed ship) :rotate (u/rad->deg vh) :y2 (* -100 vs))
    (svg/set-attr (:hull ship) :rotate (u/rad->deg h))
    (cond-> (update state :ship assoc :x x :y y :h h :vh vh :vs vs :dh dh)
      (= (:ts left) ts) (set-thruster :left (:on left))
      (= (:ts right) ts) (set-thruster :right (:on right))
      (= (:ts forward) ts) (set-thruster :forward (:on forward)))))


(defn tick-ship [state]
  (-> state
      (handle-reset)
      (handle-thrusters)
      (handle-cave-collision)))