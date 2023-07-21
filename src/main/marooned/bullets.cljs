(ns marooned.bullets
  (:require [marooned.svg :as svg]
            [marooned.audio :as audio]))


(defn create-state []
  {:g (svg/g)
   :a ()})


(defn shoot [state]
  (let [{:keys [x y h]} state
        bullets         (-> state :bullets :a)]
    #_(audio/play-on :cannon)
    (js/console.log "shoot:" x y h)
    state))


(defn tick [state ts]
  state)