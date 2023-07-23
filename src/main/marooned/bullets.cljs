(ns marooned.bullets
  (:require [marooned.svg :as svg]
            [marooned.util :as u :refer [sin cos]]))


(def ^:const bullet-speed 10)


(defn create [state]
  (let [ship-bullets-g (svg/g {:fill "white"})
        ufo-bullets-g  (svg/g {:fill "rgb(0, 255, 49)"})]
    (assoc state :bullets {:g    (svg/g ship-bullets-g
                                        ufo-bullets-g)
                           :ship ship-bullets-g
                           :ufo  ufo-bullets-g})))


(defn reset [state]
  (update state :bullets (fn [bullets]
                           (u/clear-elem (:ship bullets))
                           (u/clear-elem (:ufo bullets))
                           (assoc bullets :bullets ()))))


(defn add-bullet [state shooter x y h target action]
  (let [bullet-g (svg/circle {:cx x
                              :cy y
                              :r  4})
        bullet   {:g      bullet-g
                  :pt     (svg/point x y)
                  :h      h
                  :target target
                  :action action}]
    (svg/append (-> state :bullets shooter) bullet-g)
    (update-in state [:bullets :bullets] conj bullet)))



(defn check-hit-ufo [state]
  (let [ufo-hull (-> state :ufo :hull)]
    (if (some (fn [{:keys [pt]}]
                (svg/is-xy-in? ufo-hull pt))
              (-> state :bullets :ship-bullets :b))
      (update state :ufo assoc :killed-at (:ts state))
      state)))


#_(defn check-hit-ship [state]
    (let [ship-hull (-> state :ufo :hull)]
      (if (some (fn [{:keys [pt]}]
                  (svg/is-xy-in? ship-hull pt))
                (-> state :bullets :ship-bullets :b))
        (update state :ufo assoc :killed-at (:ts state))
        state)))

(defn remove-bullet [state bullet]
  (u/remove (:g bullet))
  (update-in state [:bullets :bullets] (fn [bullets] (remove #(identical? % bullet) bullets))))

(defn tick [state]
  (let [cave (-> state :scene :cave)]
    (reduce (fn [state bullet]
              (let [h      (:h bullet)
                    pt     (:pt bullet)
                    x      (+ (.-x pt) (* bullet-speed (sin h)))
                    y      (+ (.-y pt) (* (- bullet-speed) (cos h)))
                    target (:target bullet)
                    action (:action bullet)]
                (set! (.-x ^js pt) x)
                (set! (.-y ^js pt) y)
                (cond
                  (not (svg/is-xy-in? cave pt))
                  (remove-bullet state bullet)

                  (target pt)
                  (-> state
                      (remove-bullet bullet)
                      (action))

                  :else
                  (do (svg/set-attr (:g bullet) :cx x :cy y)
                      state))))
            state
            (-> state :bullets :bullets))))



