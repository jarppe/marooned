(ns marooned.diamons
  (:require [marooned.svg :as svg]))


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