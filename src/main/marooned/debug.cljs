(ns marooned.debug
  (:require [marooned.util :as util]
            [clojure.string :as str]))


(defn- num-fmt [^js v]
  (if v
    (.toFixed v 2)
    "-"))


(defn- on-off [v]
  (if v "ON" "OFF"))


(def debug-items
  [["status" [:status (fn [{:keys [status reason]}]
                        (str (name status)
                             (when reason
                               (str " (" (name reason) ")"))))]]
   ["timing" [:timing (fn [[max-t avg-t]] (str "max: " (.toFixed max-t 1) " "
                                               "avg: " (.toFixed avg-t 1)))]]
   ["dt" [:dt num-fmt]]
   ["x" [:ship :x num-fmt]]
   ["y" [:ship :y num-fmt]]
   ["vh" [:ship :vh num-fmt]]
   ["vs" [:ship :vs num-fmt]]
   ["h" [:ship :h num-fmt]]
   ["dh" [:ship :dh num-fmt]]
   ["left" [:control :left :on on-off]]
   ["right" [:control :right :on on-off]]
   ["forward" [:control :forward :on on-off]]
   ["cannon" [:control :cannon :on on-off]]
   #_["bullets" [:bullets :ship-bullets :b (fn [bullets]
                                             (str/join " " (map (fn [{:keys [^js pt h]}]
                                                                  (str "["
                                                                       (.toFixed (.-x pt) 1)
                                                                       ", "
                                                                       (.toFixed (.-y pt) 1)
                                                                       "] - "
                                                                       (.toFixed h 1)))
                                                                bullets)))]]])


(defn- insert-debug-panel []
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
         (util/create-element "td" k)
         (util/create-element "td" {:id (str "debug-data-" k)} "-"))))))))


(defn- remove-debug-panel []
  (util/remove-child "wrapper" "debug"))


(defn tick-debug [state]
  (when (:show-debug? state)
    (doseq [[k path] debug-items]
      (when-let [elem (util/get-elem (str "debug-data-" k))]
        (util/set-text elem (reduce (fn [v f] (f v)) state path)))))
  (let [debug (-> state :control :debug)]
    (if (and (= (:ts debug) (:ts state))
             (:on debug))
      (if (:show-debug? state)
        (do (remove-debug-panel)
            (assoc state :show-debug? false))
        (do (insert-debug-panel)
            (assoc state :show-debug? true)))
      state)))


(defn init [state]
  state)
