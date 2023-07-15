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
    (js/console.log `on-resize width "x" height "=>" scale)
    (svg/set-attr (u/get-elem "game") :viewBox (str "0 0 " width " " height))
    (svg/set-attr scene
                  :translate [(* width 0.5) (* height 0.5)]
                  :scale scale)))


(defn make-thruster-cone [x y r]
  (svg/g {:translate [x y]
          :rotate    r
          :display   "none"}
         (svg/ellipse {:fill "yellow"}
                      0 0 3 3
                      (svg/animate {:attributeName "ry"
                                    :values        "3;6;3"
                                    :dur           "100ms"
                                    :repeatCount   "indefinite"})
                      (svg/animate {:attributeName "fill-opacity"
                                    :values        "0.5;1.0;0.5"
                                    :dur           "100ms"
                                    :repeatCount   "indefinite"}))))


(defn make-game-scene []
  (let [speed                 (svg/line {:stroke       "green"
                                         :stroke-width 2}
                                        0 0 0 0)
        left-thruster-cone    (make-thruster-cone 8 -8 90)
        right-thruster-cone   (make-thruster-cone -8 -8 -90)
        forward-thruster-cone (make-thruster-cone 0 20 0)
        hull                  (svg/g
                               left-thruster-cone
                               right-thruster-cone
                               forward-thruster-cone
                               (svg/polygon {:stroke       "red"
                                             :stroke-width 3
                                             :fill         "var(--text-color)"}
                                            [[0 -20]
                                             [18 12]
                                             [12 20]
                                             [-12 20]
                                             [-18 12]]))
        ship                  (svg/g hull speed)
        scene                 (svg/g
                               ship
                               (svg/circle {:stroke       "green"
                                            :stroke-width 3
                                            :fill         "none"}
                                           -1000 -500 30)
                               (svg/circle {:stroke       "green"
                                            :stroke-width 3
                                            :fill         "none"}
                                           1000 -500 30)
                               (svg/circle {:stroke       "green"
                                            :stroke-width 3
                                            :fill         "none"}
                                           1000 500 30)
                               (svg/circle {:stroke       "green"
                                            :stroke-width 3
                                            :fill         "none"}
                                           -1000 500 30))]
    {:scene                 scene
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