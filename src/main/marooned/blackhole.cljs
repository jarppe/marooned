(ns marooned.blackhole
  (:require [marooned.svg :as svg]
            [marooned.audio :as audio]
            [marooned.util :as u]))


(def blackhole1 {:x 3700
                 :y 600})

(def blackhole2 {:x 4150
                 :y 450})


(defn make-blackhole [{:keys [x y]}]
  (svg/g {:translate [x y]}
         (svg/circle {:r    350
                      :fill "url(#blackhole-glow)"})
         (svg/circle {:r    30
                      :fill "black"})))


(defn create [state]
  (assoc state :blackhole {:defs (svg/radial-gradient {:id "blackhole-glow"}
                                                      (svg/stop {:offset       "0%"
                                                                 :stop-color   "rgb(14, 0, 236)"
                                                                 :stop-opacity 0.4})
                                                      (svg/stop {:offset       "100%"
                                                                 :stop-color   "rgb(14, 0, 236)"
                                                                 :stop-opacity 0}))
                           :g    (svg/g (make-blackhole blackhole1)
                                        (make-blackhole blackhole2))}))


(defn hit? [state]
  (let [ship (-> state :ship)
        x    (:x ship)
        y    (:y ship)]
    (or (< (u/pt-dist x y blackhole1) 40)
        (< (u/pt-dist x y blackhole2) 40))))



(defn tick [state]
  (if (and (-> state :status :status (= :run))
           (hit? state))
    (-> state
        (audio/play :ufo-explosion)
        (assoc :status {:status :game-over
                        :reason :blackhole-collision
                        :ts     (:ts state)}))
    state))
