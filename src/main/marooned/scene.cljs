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


(defn make-game-scene []
  (let [hull  (svg/polygon {:stroke       "red"
                            :stroke-width 3
                            :fill         "none"}
                           [[-20 20]
                            [0 -20]
                            [20 20]])
        speed (svg/line {:stroke       "green"
                         :stroke-width 2}
                        0 0 0 0)
        ship  (svg/g speed hull)
        scene (svg/g
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
    {:scene scene
     :ship  ship
     :hull  hull
     :speed speed}))



(defn init! []
  (u/add-event-listener js/window :resize on-resize)
  (let [scene (make-game-scene)]
    (swap! state/app-state merge scene)
    (u/append "game" (:scene scene))
    (js/setTimeout on-resize 0)))