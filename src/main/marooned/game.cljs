(ns marooned.game
  (:require [marooned.state :as state]
            [marooned.debug :as debug]
            [marooned.ship :as ship]
            [marooned.scene :as scene]
            [marooned.ufo :as ufo]
            [marooned.diamond :as diamond]
            [marooned.bullets :as bullets]))


(defn tick-timer [state t]
  (let [[max-t avg-t c] (:timing state)]
    (assoc state :timing [(max max-t t)
                          (/ (+ (* avg-t c) t) (inc c))
                          (inc c)])))


(defn now []
  (.now (.-performance js/window)))


(defn reset [state]
  (-> state
      (assoc :status {:status :run
                      :ts     (:ts state)
                      :timing [0 0 0]})
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

(defn handle-game-over [state]
  (if (and (-> state :status :status (= :game-over))
           (-> state :status :ts (= (:ts state))))
    (-> state
        (ship/game-over)
        (ufo/game-over))
    state))


(defn game-loop [state tick]
  ; Update `:ts` after updating all components, this way components
  ; can detect if control was changed in this run by comparing control
  ; change `:ts` to state `:ts`:
  (let [loop-start (now)
        start-time (:start-time state)
        prev-ts    (:ts state)
        new-ts     (- tick start-time)
        dt         (- new-ts prev-ts)]
    (-> state
        (assoc :dt dt)
        (handle-reset)
        (bullets/tick)
        (ship/tick)
        (ufo/tick)
        (diamond/tick)
        (scene/tick)
        (handle-game-over)
        (tick-timer (- (now) loop-start))
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
      (assoc :ts (now))
      (ship/create)
      (ufo/create)
      (diamond/create)
      (bullets/create)
      (reset)))
