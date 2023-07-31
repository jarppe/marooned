(ns marooned.controls
  (:require [clojure.string :as str]
            [marooned.state :refer [app-state]]
            [marooned.audio :as audio]
            [marooned.debug :as debug]
            [marooned.util :as u]))


(defn control-handler [control]
  (fn [down?]
    (swap! app-state (fn [state] (update state :control update control assoc :on down? :ts (:ts state))))))


(def key-code->control-handler (cond-> {"ArrowUp"    (control-handler :forward)
                                        "ArrowLeft"  (control-handler :left)
                                        "ArrowRight" (control-handler :right)
                                        "Space"      (control-handler :cannon)
                                        "Enter"      (control-handler :continue)
                                        "KeyS"       (fn [down?] (when down? (audio/toggle-sound-on!)))}
                                 (str/starts-with? js/window.location.host "localhost")
                                 (assoc "KeyD" (fn [down?] (when down? (debug/toggle-debug!)))
                                        "KeyR" (control-handler :reset))))


(defn on-key [down?]
  (fn [^js e]
    (when-not (or (.-repeat e)
                  (.-ctrlKey e)
                  (.-metaKey e))
      (when-let [handler (key-code->control-handler (.-code e))]
        (.preventDefault e)
        (handler down?)))
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
  (u/add-event-listener "sound" :click (fn [_] (audio/toggle-sound-on!)))
  state)
