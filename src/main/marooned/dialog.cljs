(ns marooned.dialog
  (:require [marooned.svg :as svg]
            [marooned.util :as u]
            [marooned.state :as state]))


(defn- crashed-dialog [opts]
  (svg/g {:translate [1000 300]}
         (svg/rect {:x      -300
                    :y      0
                    :width  600
                    :height 400})
         (svg/text {:x           0
                    :y           50
                    :text-anchor "middle"}
                   "CRASH")
         (svg/text {:x           0
                    :y           150
                    :text-anchor "middle"}
                   "Click this or press <RETURN>")
         (svg/text {:x           0
                    :y           200
                    :text-anchor "middle"}
                   "key to continue")))


(defn continue [_]
  (swap! state/app-state (fn [state]
                           (update state :control update :continue assoc :on true :ts (:ts state)))))


(defn show-dialog [g dialog-id opts]
  (let [dialog (case dialog-id
                 :crash (crashed-dialog opts))]
    (u/add-event-listener dialog :click continue {:once true})
    (svg/append g (svg/g {:class "dialog"}
                         (svg/rect {:x      -1000
                                    :y      0
                                    :width  4000
                                    :height 1000
                                    :stroke "none"
                                    :fill   "rgba(0, 0, 0, 0.5)"})
                         dialog))))


(defn close-dialog [g]
  (u/clear-elem g))
