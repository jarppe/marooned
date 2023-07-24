(ns marooned.ship
  (:require [marooned.util :as u :refer [PI PIp2 sin cos]]
            [marooned.svg :as svg]
            [marooned.audio :as audio]
            [marooned.bullets :as bullets]
            [marooned.blackhole :as blackhole]))


; game:        200  500
; diamond:    2250  500
; doors:      2950  500
; blackholes: 3350  500
; ufo:        4800  500


(def start-x 200)
(def start-y 500)
(def start-h PIp2)


(def continue-points [[6900  [6800 875]]
                      [5100  [4800 525]]
                      [3600  [3400 475]]
                      [2000  [1700 775]]
                      [-1000 [200  500]]])


(def ^:const max-dh (/ PI 1000.0))
(def ^:const min-dh (- max-dh))
(def ^:const side-thruster-delta (/ max-dh 80.0))
(def ^:const forward-thruster-delta 0.01)
(def ^:const cannon-cooling-ms 2000)


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


(defn create [state]
  (let [left-thruster-cone    (make-thruster-cone 8 -8 90)
        right-thruster-cone   (make-thruster-cone -8 -8 -90)
        forward-thruster-cone (make-thruster-cone 0 20 0)
        hull                  (svg/polygon {:stroke       "red"
                                            :stroke-width 3
                                            :fill         "rgb(24, 24, 24)"
                                            :points       hull-points})
        speed                 (svg/line {:stroke       "green"
                                         :stroke-width 2
                                         :x1           0
                                         :y1           0
                                         :x2           0
                                         :y2           0})
        g                     (svg/g left-thruster-cone
                                     right-thruster-cone
                                     forward-thruster-cone)]
    (assoc state :ship {:thruster {:left    left-thruster-cone
                                   :right   right-thruster-cone
                                   :forward forward-thruster-cone}
                        :hull     hull
                        :speed    speed
                        :g        g
                        :x        start-x
                        :y        start-y
                        :h        start-h})))


(defn set-thruster [state thruster on?]
  (svg/set-attr (-> state :ship :thruster thruster) "display" (if on? "on" "none"))
  (if on?
    (audio/play-on state thruster)
    (audio/play-off state thruster 100)))


(defn all-thruster-off [state]
  (reduce (fn [state thruster] (set-thruster state thruster false))
          state
          [:left :right :forward]))


(defn game-over [state]
  (all-thruster-off state))


(defn reset [state]
  (let [x     (-> state :ship :x)
        [x y] (some (fn [[limit pos]]
                      (when (> x limit)
                        pos))
                    continue-points)]
    (-> state
        (all-thruster-off)
        (update :ship merge {:x           x
                             :y           y
                             :vh          0
                             :vs          0
                             :dh          0
                             :fire-ts     0
                             :hull-points []}))))


(defn get-hull-svg-points [x y h]
  (let [sin-h (sin h)
        cos-h (cos h)]
    (map (fn [[hull-x hull-y]]
           (let [x' (+ (* hull-x cos-h)
                       (* hull-y sin-h))
                 y' (- (* hull-y cos-h)
                       (* hull-x sin-h))]
             (svg/point (- x x')
                        (+ y y'))))
         hull-points)))


(def max-blackhole-force (* forward-thruster-delta 1.5))
(def blackhole-force (comp (u/bound 0.0 max-blackhole-force)
                           (u/scaler [30 400] [max-blackhole-force 0])))


(defn handle-thrusters [state]
  (let [dt            (:dt state)
        ts            (:ts state)
        ship          (:ship state)
        left          (-> state :control :left)
        right         (-> state :control :right)
        forward       (-> state :control :forward)
        side-delta    (+ (if (:on left) (- side-thruster-delta) 0.0)
                         (if (:on right) side-thruster-delta 0.0))
        forward-delta (if (:on forward) forward-thruster-delta 0.0)
        dh            (u/clamp min-dh (+ (:dh ship) side-delta) max-dh)
        h             (+ (:h ship) (* dt dh))
        x             (:x ship)
        y             (:y ship)
        new-telemetry (-> {:h (:vh ship)
                           :s (:vs ship)}
                          (u/vec+ h forward-delta)
                          (u/vec+ (u/pt-h x y blackhole/blackhole1) (blackhole-force (u/pt-dist x y blackhole/blackhole1)))
                          (u/vec+ (u/pt-h x y blackhole/blackhole2) (blackhole-force (u/pt-dist x y blackhole/blackhole2))))
        vh            (:h new-telemetry)
        vs            (:s new-telemetry)
        x             (+ (:x ship) (:dx new-telemetry))
        y             (+ (:y ship) (:dy new-telemetry))
        hull-points   (get-hull-svg-points x y h)]
    (svg/set-attr (:g ship) :translate [x y] :rotate (u/rad->deg h))
    (svg/set-attr (:speed ship) :translate [x y] :rotate (u/rad->deg vh) :y2 (* -100 vs))
    (svg/set-attr (:hull ship) :points hull-points)
    (if-not (every? (partial svg/is-xy-in? (-> state :scene :cave)) hull-points)
      (-> state
          (u/game-over :cave-collision)
          (set-thruster :left false)
          (set-thruster :right false)
          (set-thruster :forward false))
      (cond-> (update state :ship assoc
                      :x x :y y
                      :h h :vh vh
                      :vs vs :dh dh
                      :hull-points hull-points)
        (= (:ts left) ts) (set-thruster :left (:on left))
        (= (:ts right) ts) (set-thruster :right (:on right))
        (= (:ts forward) ts) (set-thruster :forward (:on forward))))))


(defn handle-cannon [state]
  (let [ts       (:ts state)
        cannon   (-> state :control :cannon)
        fire?    (and (:on cannon)
                      (= (:ts cannon) ts))
        ufo-hull (-> state :ufo :hull)]
    (if fire?
      (let [ship            (:ship state)
            since-last-fire (->> ship :fire-ts (- ts))]
        (if (< since-last-fire cannon-cooling-ms)
          (audio/play state :click)
          (-> state
              (audio/play :cannon)
              (bullets/add-bullet :ship
                                  (:x ship) (:y ship) (:h ship)
                                  (fn [^js pt] (svg/is-xy-in? ufo-hull pt))
                                  (fn [state] (update state :ufo assoc :killed true)))
              (assoc-in [:ship :fire-ts] ts))))
      state)))


(defn tick [state]
  (if (-> state :status :status (= :run))
    (-> state
        (handle-thrusters)
        (handle-cannon))
    state))
