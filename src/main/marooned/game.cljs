(ns marooned.game
  (:require [marooned.state :as state]
            [marooned.debug :as debug]
            [marooned.ship :as ship]))


(defn game-loop [state tick]
  (let [start-time (:start-time state)
        prev-ts    (:ts state)
        new-ts     (- tick start-time)
        dt         (- new-ts prev-ts)]
    ; Update `:ts` after updating all components, this way components
    ; can detect if control was changed in this run by comparing control
    ; change `:ts` to state `:ts`:
    (-> state
        (assoc :dt dt)
        (ship/tick-ship)
        (debug/tick-debug)
        (assoc :ts new-ts))))


(defn animation-frame [tick]
  (swap! state/app-state game-loop tick)
  (js/window.requestAnimationFrame animation-frame))


(defn run [state]
  (js/window.requestAnimationFrame animation-frame)
  state)


(defn init [state]
  (-> state
      (assoc :start-time (.now (.-performance js/window)))
      (ship/create-ship)))
