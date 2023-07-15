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


(defn on-mouse-down [control-code]
  (fn [^js e]
    (.preventDefault e)
    (swap! app-state assoc control-code true)))


(defn on-mouse-up [control-code]
  (fn [^js e]
    (.preventDefault e)
    (swap! app-state assoc control-code false)))


(defn on-touch-start [control-code]
  (fn [^js e]
    (.preventDefault e)
    (js/console.log "on-touch-start" (name control-code))
    (swap! app-state assoc control-code true)))


(defn on-touch-end [control-code]
  (fn [^js e]
    (.preventDefault e)
    (js/console.log "on-touch-end" (name control-code))
    (swap! app-state assoc control-code false)))


(defn on-touch-cancel [control-code]
  (fn [^js e]
    (.preventDefault e)
    (js/console.log "on-touch-cancel" (name control-code))
    (swap! app-state assoc control-code false)))


(defn init! []
  (u/add-event-listener js/window :keydown (on-key true))
  (u/add-event-listener js/window :keyup (on-key false))

  (-> "left-thruster"
      (u/add-event-listener :mousedown (on-mouse-down :left-thruster))
      (u/add-event-listener :mouseup (on-mouse-up :left-thruster))
      (u/add-event-listener :touchstart (on-touch-start :left-thruster))
      (u/add-event-listener :touchend (on-touch-end :left-thruster))
      (u/add-event-listener :touchcancel (on-touch-cancel :left-thruster)))


  (-> "right-thruster"
      (u/add-event-listener :mousedown (on-mouse-down :right-thruster))
      (u/add-event-listener :mouseup (on-mouse-up :right-thruster))
      (u/add-event-listener :touchstart (on-touch-start :right-thruster))
      (u/add-event-listener :touchend (on-touch-end :right-thruster))
      (u/add-event-listener :touchcancel (on-touch-cancel :right-thruster)))

  (-> "forward-thruster"
      (u/add-event-listener :mousedown (on-mouse-down :forward-thruster))
      (u/add-event-listener :mouseup (on-mouse-up :forward-thruster))
      (u/add-event-listener :touchstart (on-touch-start :forward-thruster))
      (u/add-event-listener :touchend (on-touch-end :forward-thruster))
      (u/add-event-listener :touchcancel (on-touch-cancel :forward-thruster)))

  (-> "cannon"
      (u/add-event-listener :mousedown (on-mouse-down :cannon))
      (u/add-event-listener :mouseup (on-mouse-up :cannon))
      (u/add-event-listener :touchstart (on-touch-start :cannon))
      (u/add-event-listener :touchend (on-touch-end :cannon))
      (u/add-event-listener :touchcancel (on-touch-cancel :cannon))))
