(ns marooned.door
  (:require [marooned.svg :as svg]
            [marooned.util :as u]))


(def ^:const door-height 150)
(def ^:const hallway-height 350.0)
(def ^:const door-x [3000 3100 3200])
(def ^:const dy (map (fn [v] (/ hallway-height v)) [5000.0 9000.0 7000.0]))
(def ^:const y1 (- 300 door-height))
(def ^:const y2 (+ y1 hallway-height door-height))
(def ^:const door-fill "rgb(77, 66, 190)")
(def ^:const door-fill-collision "rgb(190, 66, 79)")


(defn make-door-clip [x]
  (svg/clip-path {:id            (str "door-clip-" x)
                  :clipPathUnits "userSpaceOnUse"}
                 (svg/rect {:x      x
                            :y      300
                            :width  30
                            :height 350})))


(defn make-door [x]
  {:y y1
   :g (svg/rect {:x         x
                 :y         y1
                 :width     30
                 :height    door-height
                 :clip-path (str "url(#door-clip-" x ")")
                 :fill      door-fill})})


(defn create [state]
  (let [door-clips (mapv make-door-clip door-x)
        doors      (mapv make-door door-x)]
    (assoc state :door {:defs  door-clips
                        :doors doors
                        :g     (svg/g {:stroke "none"} (map :g doors))})))



(defn- set-y [state n y]
  (svg/set-attr (-> state :door :doors (nth n) :g) :y y)
  (assoc-in state [:door :doors n :y] y))


(defn reset [state]
  (reduce (fn [state n]
            (svg/set-attr (-> state :door :doors (nth n) :g) :fill door-fill)
            (set-y state n y1))
          state
          (range (count door-x))))


(defn handle-door-collision [state hull-points door]
  (if (some (partial svg/is-xy-in? (:g door)) hull-points)
    (do (svg/set-attr (:g door) :fill door-fill-collision)
        (u/game-over state :door-collision))
    state))


(defn tick [state]
  (if (-> state :status :status (= :run))
    (let [hull-points (-> state :ship :hull-points)]
      (reduce (fn [state n]
                (let [door (-> state :door :doors (nth n))
                      y    (+ (:y door) (* (nth dy n) (:dt state)))
                      y    (if (> y y2) y1 y)]
                  (-> state
                      (set-y n y)
                      (handle-door-collision hull-points door))))
              state
              (range (count door-x))))
    state))
