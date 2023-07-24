(ns marooned.diamond
  (:require [marooned.svg :as svg]))


(defn create [state]
  (let [diamond-x     2350
        diamond-y     470
        translate     (fn [[x y]] [(+ diamond-x x) (+ diamond-y y)])
        diamond       (svg/polygon {:stroke-width 2
                                    :fill         "rgb(209, 209, 89)"
                                    :points       (map translate
                                                       [[-30 0]
                                                        [0 -40]
                                                        [30 0]
                                                        [0 40]])})
        diamond-shape (svg/g {:stroke-width 2
                              :stroke       "yellow"
                              :fill         "none"}
                             diamond
                             (svg/polyline {:translate [diamond-x diamond-y]
                                            :points    [[-30 0]
                                                        [0 10]
                                                        [30 0]]})
                             (for [y1 [-40 40]]
                               (svg/line {:translate [diamond-x diamond-y]
                                          :x1        0
                                          :y1        y1
                                          :x2        0
                                          :y2        0}
                                         (svg/animate {:attributeName "x2"
                                                       :values        "-30;30"
                                                       :dur           "2s"
                                                       :repeatCount   "indefinite"})
                                         (svg/animate {:attributeName "y2"
                                                       :values        "0;5;0"
                                                       :dur           "2s"
                                                       :repeatCount   "indefinite"}))))]
    (assoc state :diamond {:g         diamond-shape
                           :body      diamond
                           :captured? false})))


(defn reset [state]
  (svg/set-attr (-> state :diamond :body) :fill "rgb(209, 209, 89)")
  (svg/set-attr (-> state :diamond :g) :stroke "yellow")
  (update state :diamond assoc :captured? false))


(defn capture? [state]
  (and (-> state :diamond :captured? not)
       (some (partial svg/is-xy-in? (-> state :diamond :body)) (-> state :ship :hull-points))))


(defn tick [state]
  (if (capture? state)
    (do (svg/set-attr (-> state :diamond :body) :fill "rgb(90, 90, 38)")
        (svg/set-attr (-> state :diamond :g) :stroke "rgb(169, 169, 97)")
        (-> state
            (update :diamond assoc :captured? true)
            (update :status update :lives + 3)))
    state))

