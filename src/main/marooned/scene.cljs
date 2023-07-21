(ns marooned.scene
  (:require [marooned.state :as state]
            [marooned.util :as u]
            [marooned.svg :as svg]
            [marooned.shapes :as shapes]
            [marooned.ufo :as ufo]
            [marooned.bullets :as bullets]))


(defn on-resize [_]
  (let [^js wrapper (u/get-elem "wrapper")
        width   (.-clientWidth wrapper)
        height  (.-clientHeight wrapper)
        scale   (min (/ width 2000.0)
                     (/ height 1000.0))]
    (svg/set-attr "game" :viewBox (str "0 0 " width " " height))
    (svg/set-attr "scene" :scale scale :translate [0 (/ (- height (* scale 1000.0)) 2.0)])))


(defn create-scene [state]
  (let [cave       (svg/path {:stroke       "gray"
                              :stroke-width 2
                              :fill         "dark-gray"
                              :d            shapes/cave-path})
        soil       (svg/pattern {:id           "soil-pattern"
                                 :x            0
                                 :y            0
                                 :width        100
                                 :height       100
                                 :patternUnits "userSpaceOnUse"}
                                (svg/path {:fill "dark-gray"
                                           :d    shapes/soil-pattern}))
        background (svg/rect {:x      -1000
                              :y      -1000
                              :width  10000
                              :height 3000
                              :fill   "url(#soil-pattern)"})
        board      (svg/g background
                          cave
                          (-> state :ship :g))
        scene      (svg/g {:id "scene"} board)]
    (-> (u/clear-elem "game")
        (u/append* [(svg/defs soil)
                    scene]))
    (assoc state :scene {:scene scene
                         :board board
                         :cave  cave})))


(defn init [state]
  (u/add-event-listener js/window :resize on-resize)
  (js/setTimeout on-resize 0)
  (create-scene state))
