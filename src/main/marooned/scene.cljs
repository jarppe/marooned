(ns marooned.scene
  (:require [marooned.state :as state]
            [marooned.util :as u]
            [marooned.svg :as svg]
            [marooned.shapes :as shapes]
            [marooned.ufo :as ufo]))


(defn on-resize [_]
  (let [state   @state/app-state
        scene   (:scene state)
        wrapper (u/get-elem "wrapper")
        width   (u/js-get wrapper "clientWidth")
        height  (u/js-get wrapper "clientHeight")
        scale   (min (/ width 2000.0)
                     (/ height 1000.0))]
    (svg/set-attr (u/get-elem "game") :viewBox (str "0 0 " width " " height))
    (svg/set-attr scene :scale scale :translate [0 (/ (- height (* scale 1000)) 2.0)])))


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


(def hull-points [[0 -20]
                  [18 12]
                  [12 20]
                  [-12 20]
                  [-18 12]])


(defn make-diamond []
  (let [diamond-x     2350
        diamond-y     500
        diamond       (svg/polygon {:stroke-width 2
                                    :stroke       "yellow"
                                    :fill         "rgb(209, 209, 89)"
                                    :points       (mapv (fn [[x y]] [(+ diamond-x x) (+ diamond-y y)])
                                                        [[-30 0]
                                                         [0 -40]
                                                         [30 0]
                                                         [0 40]])})
        diamond-shape (svg/g {:stroke-width 2
                              :stroke       "yellow"
                              :fill         "none"
                              :translate    [diamond-x diamond-y]}
                             (svg/polyline {:points [[-30 0]
                                                     [0 10]
                                                     [30 0]]})
                             (for [y1 [-40 40]]
                               (svg/line {:x1 0
                                          :y1 y1
                                          :x2 0
                                          :y2 0}
                                         (svg/animate {:attributeName "x2"
                                                       :values        "-30;30"
                                                       :dur           "2s"
                                                       :repeatCount   "indefinite"})
                                         (svg/animate {:attributeName "y2"
                                                       :values        "0;5;0"
                                                       :dur           "2s"
                                                       :repeatCount   "indefinite"}))))]
    [diamond diamond-shape]))


(defn make-defs []
  (svg/defs
    (svg/pattern {:id           "soil-pattern"
                  :x            0
                  :y            0
                  :width        100
                  :height       100
                  :patternUnits "userSpaceOnUse"}
                 (svg/path {:fill "dark-gray"
                            :d    shapes/soil-pattern}))))



(defn make-game-scene []
  (let [cave                    (svg/path {:stroke       "gray"
                                           :stroke-width 2
                                           :fill         "dark-gray"
                                           :d            shapes/cave-path})
        background              (svg/rect {:x      -1000
                                           :y      -1000
                                           :width  10000
                                           :height 3000
                                           :fill   "url(#soil-pattern)"})
        left-thruster-cone      (make-thruster-cone 8 -8 90)
        right-thruster-cone     (make-thruster-cone -8 -8 -90)
        forward-thruster-cone   (make-thruster-cone 0 20 0)
        hull                    (svg/g
                                 left-thruster-cone
                                 right-thruster-cone
                                 forward-thruster-cone
                                 (svg/polygon {:stroke       "red"
                                               :stroke-width 3
                                               :fill         "var(--text-color)"
                                               :points       hull-points}))
        speed                   (svg/line {:stroke       "green"
                                           :stroke-width 2
                                           :x1           0
                                           :y1           0
                                           :x2           0
                                           :y2           0})
        ship                    (svg/g hull speed)
        [diamond diamond-shape] (make-diamond)
        ufo                     (ufo/make-ufo 200 200 600 600 20)
        board                   (svg/g
                                 background
                                 cave
                                 diamond
                                 diamond-shape
                                 ship
                                 (:shape ufo))
        scene                   (svg/g board)
        root                    [(make-defs)
                                 scene]]
    {:root                  root
     :scene                 scene
     :board                 board
     :cave                  cave
     :ship                  ship
     :hull                  hull
     :left-thruster-cone    left-thruster-cone
     :right-thruster-cone   right-thruster-cone
     :forward-thruster-cone forward-thruster-cone
     :speed                 speed
     :diamond               diamond
     :ufo                   ufo}))


(defn ^:dev/after-load reset-scene []
  (let [scene (make-game-scene)]
    (swap! state/app-state merge scene)
    (-> "game"
        (u/clear-elem)
        (u/append* (:root scene)))
    (js/setTimeout on-resize 0)))


(defn init! []
  (u/add-event-listener js/window :resize on-resize)
  (reset-scene))