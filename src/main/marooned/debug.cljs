(ns marooned.debug
  (:require [marooned.state :as state]
            [marooned.util :as util]))


(defn num-fmt [^js v]
  (if v
    (.toFixed v 2)
    "-"))


(defn thruster [v]
  (if v "ON" "OFF"))


(def debug-items
  [[:dt num-fmt]
   [:x num-fmt]
   [:y num-fmt]
   [:vh num-fmt]
   [:vs num-fmt]
   [:h num-fmt]
   [:dh num-fmt]
   [:left-thruster thruster]
   [:right-thruster thruster]
   [:forward-thruster thruster]
   [:got-diamond? (fn [v] (if v "true" "false"))]])


(defn insert-debug-panel []
  (js/console.log `insert-debug-panel)
  (util/append
   "wrapper"
   (util/create-element
    "div" {:id "debug"}
    (util/create-element
     "table"
     (util/create-element
      "tbody"
      (for [[k] debug-items]
        (util/create-element
         "tr"
         (util/create-element
          "td" {:key k} (pr-str k))
         (util/create-element
          "td" {:id (str "debug-data-" k)} "-"))))))))


(defn remove-debug-panel []
  (js/console.log `remove-debug-panel)
  (util/remove-child "wrapper" "debug"))


(defn update-debug [state]
  (when (:debug? state)
    (doseq [[k fmt] debug-items]
      (util/set-text (str "debug-data-" k) (fmt (k state)))))
  state)


(defn init! []
  (state/on-change :debug-toggle (fn [debug-key-down?]
                                   (js/console.log ":debug-toggle" debug-key-down?)
                                   (when debug-key-down?
                                     (swap! state/app-state update :debug? (comp (fn [debug?]
                                                                                   (if debug?
                                                                                     (insert-debug-panel)
                                                                                     (remove-debug-panel))
                                                                                   debug?)
                                                                                 not))))))