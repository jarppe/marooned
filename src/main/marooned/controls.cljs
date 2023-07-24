(ns marooned.controls
  (:require [clojure.string :as str]
            [marooned.state :refer [app-state]]
            [marooned.util :as u]))


(def key-code->control (cond-> {"ArrowUp"    :forward
                                "ArrowLeft"  :left
                                "ArrowRight" :right
                                "Space"      :cannon
                                "Enter"      :continue
                                "KeyS"       :toggle-sound}
                         (str/starts-with? js/window.location.host "localhost")
                         (assoc "KeyD" :debug
                                "KeyR" :reset)))


(defn on-key [down?]
  (fn [^js e]
    (when-not (or (.-repeat e)
                  (.-ctrlKey e)
                  (.-metaKey e))
      (when-let [control (key-code->control (.-code e))]
        (.preventDefault e)
        (swap! app-state (fn [state] (update state :control update control assoc :on down? :ts (:ts state))))))
    nil))


(defn on-control [control on?]
  (fn [^js e]
    (.preventDefault e)
    (swap! app-state (fn [state] (update state :control update control assoc :on on? :ts (:ts state))))
    nil))


(defn add-listeners-for [control]
  (-> (u/get-elem (name control))
      (u/add-event-listener :mousedown (on-control control true))
      (u/add-event-listener :mouseup (on-control control false))
      (u/add-event-listener :touchstart (on-control control true))
      (u/add-event-listener :touchend (on-control control false))
      (u/add-event-listener :touchcancel (on-control control false))))


(defn init [state]
  (-> js/window
      (u/add-event-listener :keydown (on-key true))
      (u/add-event-listener :keyup (on-key false)))
  (add-listeners-for :left)
  (add-listeners-for :right)
  (add-listeners-for :forward)
  (add-listeners-for :cannon)
  state)
