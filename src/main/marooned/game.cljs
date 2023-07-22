(ns marooned.game
  (:require [marooned.state :as state]
            [marooned.debug :as debug]
            [marooned.ship :as ship]
            [marooned.scene :as scene]
            [marooned.ufo :as ufo]
            [marooned.diamond :as diamond]
            [marooned.bullets :as bullets]))


(defn reset [state]
  (-> state
      (assoc :start-time (.now (.-performance js/window))
             :status {:status :run
                      :ts     (:ts state)})
      (ship/reset)
      (ufo/reset)
      (bullets/reset)
      (diamond/reset)))


(defn handle-reset [state]
  (if (and (-> state :control :reset :on)
           (= (-> state :control :reset :ts)
              (-> state :ts)))
    (reset state)
    state))


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
        (handle-reset)
        (ship/tick)
        (ufo/tick)
        (diamond/tick)
        (bullets/tick)
        (scene/tick)
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
      (ship/create)
      (ufo/create)
      (diamond/create)
      (bullets/create)
      (reset)))
