(ns marooned.scene
  (:require [marooned.state :as state]
            [marooned.util :as u]
            [marooned.svg :as svg]))



(defn on-resize [_]
  (let [state   @state/app-state
        scene   (:scene state)
        wrapper (u/get-elem "wrapper")
        width   (u/js-get wrapper "clientWidth")
        height  (u/js-get wrapper "clientHeight")
        scale   (min (/ width 2000.0)
                     (/ height 1000.0))]
    (svg/set-attr (u/get-elem "game") :viewBox (str "0 0 " width " " height))
    (svg/set-attr scene :scale scale)))


(defn make-thruster-cone [x y r]
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


(def wall-path
  "m 2025,850 100,100 h 450 l 300,-300 h 500 l 150,250 h 800 l 150,-250 h 450 l 150,300 H 7125 V 152 l -650,-2 v 550 l 50,50 h 350 L 6975,650 V 400 l -50,-50 h -200 l -50,50 v 200 h -50 V 350 l 50,-50 h 300 l 50,50 V 700 L 6925,800 H 6225 L 6075,650 V 100 H 5075 L 4925,400 H 4475 L 4335,150 H 3525 L 3375,300 H 2625 V 650 L 2475,800 H 2175 V 600 h 300 V 350 H 2175 L 2025,450 V 700 H 1325 L 775,500 1325,300 h 700 V 150 H 775 L 275,350 h -500 v 300 h 500 l 500,200 Z")


(defn make-game-scene []
  (let [wall                  (svg/path {:stroke "none"
                                         :fill   "green"
                                         :d      wall-path})
        speed                 (svg/line {:stroke       "green"
                                         :stroke-width 2
                                         :x1           0
                                         :y1           0
                                         :x2           0
                                         :y2           0})
        left-thruster-cone    (make-thruster-cone 8 -8 90)
        right-thruster-cone   (make-thruster-cone -8 -8 -90)
        forward-thruster-cone (make-thruster-cone 0 20 0)
        hull                  (svg/g
                               left-thruster-cone
                               right-thruster-cone
                               forward-thruster-cone
                               (svg/polygon {:stroke       "red"
                                             :stroke-width 3
                                             :fill         "var(--text-color)"
                                             :points       [[0 -20]
                                                            [18 12]
                                                            [12 20]
                                                            [-12 20]
                                                            [-18 12]]}))
        ship                  (svg/g hull speed)
        scene                 (svg/g
                               (svg/g wall)
                               ship
                               (svg/circle {:stroke       "green"
                                            :stroke-width 3
                                            :fill         "none"
                                            :cx           0
                                            :cy           0
                                            :r            30})
                               (svg/circle {:stroke       "green"
                                            :stroke-width 3
                                            :fill         "none"
                                            :cx           1000
                                            :cy           0
                                            :r            30})
                               (svg/circle {:stroke       "green"
                                            :stroke-width 3
                                            :fill         "none"
                                            :cx           1000
                                            :cy           1000
                                            :r            30})
                               (svg/circle {:stroke       "green"
                                            :stroke-width 3
                                            :fill         "none"
                                            :cx           0
                                            :cy           1000
                                            :r            30}))]
    {:scene                 scene
     :wall                  wall
     :ship                  ship
     :hull                  hull
     :left-thruster-cone    left-thruster-cone
     :right-thruster-cone   right-thruster-cone
     :forward-thruster-cone forward-thruster-cone
     :speed                 speed}))


(defn ^:dev/after-load reset-scene []
  (let [scene (make-game-scene)]
    (swap! state/app-state merge scene)
    (-> "game"
        (u/clear-elem)
        (u/append (:scene scene)))
    (js/setTimeout on-resize 0)))


(defn init! []
  (u/add-event-listener js/window :resize on-resize)
  (reset-scene))