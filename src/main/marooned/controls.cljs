(ns marooned.controls
  (:require [marooned.state :refer [app-state]]
            [marooned.util :as u :refer [js-get]]))


(defn on-key [down?]
  (fn [^js e]
    (when-not (js-get e "repeat")
      ;(js/console.debug "key:" (js-get e "code") (if down? "down" "up") e)
      (let [shift? (js-get e "shiftKey")
            ctrl?  (js-get e "ctrlKey")
            meta?  (js-get e "metaKey")]
        (when (and (not shift?)
                   (not ctrl?)
                   (not meta?))
          (when-let [key-code (case (js-get e "code")
                                "ArrowUp" :forward-thruster
                                "Space" :cannon
                                "ArrowLeft" :left-thruster
                                "ArrowRight" :right-thruster
                                "KeyD" :debug-toggle
                                "KeyR" :reset!
                                nil)]
            (.preventDefault e)
            (swap! app-state assoc key-code down?)))))
    nil))


(defn on-mouse [control-code down?]
  (fn [^js e]
    (.preventDefault e)
    (swap! app-state assoc control-code down?)))


(defn init! []
  (u/add-event-listener js/window :keydown (on-key true))
  (u/add-event-listener js/window :keyup (on-key false))
  (u/add-event-listener "left-thruster" :mousedown (on-mouse :left-thruster true))
  (u/add-event-listener "left-thruster" :mouseup (on-mouse :left-thruster false))
  (u/add-event-listener "right-thruster" :mousedown (on-mouse :right-thruster true))
  (u/add-event-listener "right-thruster" :mouseup (on-mouse :right-thruster false))
  (u/add-event-listener "forward-thruster" :mousedown (on-mouse :forward-thruster true))
  (u/add-event-listener "forward-thruster" :mouseup (on-mouse :forward-thruster false))
  (u/add-event-listener "cannon" :mousedown (on-mouse :cannon true))
  (u/add-event-listener "cannon" :mouseup (on-mouse :cannon false)))
