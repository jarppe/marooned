(ns marooned.bullets
  (:require [marooned.svg :as svg]
            [marooned.audio :as audio]
            [marooned.ufo :as ufo]
            [marooned.util :as u :refer [sin cos]]))


(def ^:const bullet-speed 10)
(def ^:const cannon-cooling-ms 2000)


(defn create [state]
  (let [ship-bullets-g (svg/g {:fill "white"})
        ufo-bullets-g  (svg/g {:fill "green"})]
    (assoc state :bullets {:g            (svg/g ship-bullets-g
                                                ufo-bullets-g)
                           :ship-bullets {:g ship-bullets-g
                                          :b ()}
                           :ufo-bullets  {:g ufo-bullets-g
                                          :b ()}})))


(defn reset [state]
  (update state :bullets (fn [s]
                           (-> s
                               (assoc :ship-fire-ts (:ts state))
                               (update :ship-bullets (fn [b]
                                                       (u/clear-elem (:g b))
                                                       (assoc b :b ())))
                               (update :ufo-bullets (fn [b]
                                                      (u/clear-elem (:g b))
                                                      (assoc b :b ())))))))



(defn update-bullet-position [cave bullet]
  (let [h  (:h bullet)
        pt (:pt bullet)
        x  (+ (.-x pt) (* bullet-speed (sin h)))
        y  (+ (.-y pt) (* (- bullet-speed) (cos h)))]
    (set! (.-x ^js pt) x)
    (set! (.-y ^js pt) y)
    (if (svg/is-xy-in? cave pt)
      (do (svg/set-attr (:g bullet) :cx x :cy y)
          bullet)
      (do (u/remove (:g bullet))
          nil))))


(defn update-bullets-positions [bullets cave]
  (doall (keep (partial update-bullet-position cave) bullets)))


(defn check-hit-ufo [state]
  (let [ufo-hull (-> state :ufo :hull)]
    (if (some (fn [{:keys [pt]}]
                (svg/is-xy-in? ufo-hull pt))
              (-> state :bullets :ship-bullets :b))
      (ufo/kill state)
      state)))


(defn tick-ship-bullets [state]
  (let [cave (-> state :scene :cave)]
    (-> state
        (update-in [:bullets :ship-bullets :b] update-bullets-positions cave)
        (update-in [:bullets :ufo-bullets :b] update-bullets-positions cave)
        (check-hit-ufo))))


(defn tick [state]
  (-> state
      (tick-ship-bullets)))


(defn add-bullet [state]
  (let [ship     (-> state :ship)
        x        (-> ship :x)
        y        (-> ship :y)
        h        (-> ship :h)
        bullet-g (svg/circle {:cx x
                              :cy y
                              :r  3})
        bullet   {:g  bullet-g
                  :pt (svg/point x y)
                  :h  h}]
    (svg/append (-> state :bullets :ship-bullets :g) bullet-g)
    (update-in state [:bullets :ship-bullets :b] conj bullet)))


(defn shoot [state]
  (if (< (- (:ts state) (-> state :bullets :ship-fire-ts)) cannon-cooling-ms)
    (do (js/console.log "too soon!" (:ts state) (-> state :bullets :ship-fire-ts) "=>" (- (:ts state) (-> state :bullets :ship-fire-ts)))
        (audio/play state :click))
    (-> state
        (audio/play :cannon)
        (assoc-in [:bullets :ship-fire-ts] (:ts state))
        (add-bullet))))


