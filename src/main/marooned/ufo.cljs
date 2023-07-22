(ns marooned.ufo
  (:require [marooned.svg :as svg]
            [marooned.audio :as audio]
            [marooned.scene :refer [ship-offset-x]]
            [marooned.util :as u]))


(def hull-fill  "rgb(17, 158, 49)")
(def cockpit-fill "rgb(58, 193, 84)")
(def hull-fill-collosion "rgb(184, 48, 48)")
(def cockpit-fill-collision "rgb(223, 34, 33)")
(def ufo-x 5700)
(def ufo-y 200)


(defn create [state]
  (let [w       300
        h       600
        path    (svg/path {:id           "ufo-path"
                           :stroke-width 2
                           :stroke       "gray"
                           :fill         "none"
                           :d            (str "M " ufo-x " " ufo-y " "
                                              "c " w " 0 " (- w) " " h " 0 " h " "
                                              "c " w " 0 " (- w) " " (- h) " 0 " (- h))})
        hull    (svg/ellipse {:fill hull-fill
                              :cx   0
                              :cy   0
                              :rx   80
                              :ry   15})
        cockpit (svg/ellipse {:translate [0 -15]
                              :fill      cockpit-fill
                              :cx        0
                              :cy        0
                              :rx        30
                              :ry        10})
        shape   (svg/g hull cockpit)]
    (assoc state :ufo {:g        shape
                       :hull     hull
                       :cockpit  cockpit
                       :path     path
                       :path-len (.getTotalLength path)})))


(defn reset [state]
  (svg/set-attr (-> state :ufo :hull) :fill hull-fill)
  (svg/set-attr (-> state :ufo :cockpit) :fill cockpit-fill)
  (update state :ufo assoc :active? false))


(defn handle-ufo-collision [state]
  (if (some (partial svg/is-xy-in? (-> state :ufo :hull))
            (-> state :ship :hull-xys))
    (do   (svg/set-attr (-> state :ufo :hull) :fill hull-fill-collosion)
          (svg/set-attr (-> state :ufo :cockpit) :fill cockpit-fill-collision)
          (-> state
              (assoc :status {:status :game-over
                              :reason :ufo-collision
                              :ts     (:ts state)})))
    state))


(def ufo-balance (comp (u/bound -1.0 +1.0)
                       (u/scaler [-1000 +1000] [-1.0 +1.0])))
(def ufo-volume (comp (u/bound 0.0 0.8)
                      (u/scaler [1500 0] [0.0 0.8])
                      #(js/Math.abs %)))


(defn tick [state]
  (if (and (-> state :status :status (= :run))
           (-> state :ufo :killed? (not)))
    (let [{:keys [path path-len hull cockpit]} (:ufo state)
          n                                    (mod (/ (:ts state) 15) path-len)
          p                                    (.getPointAtLength path n)
          x                                    (.-x ^js p)
          y                                    (.-y ^js p)
          ufo-dist                             (->> state :ship :x (- ufo-x))]
      (svg/set-attr hull :cx x :cy y)
      (svg/set-attr cockpit :cx x :cy y)
      (-> state
          (audio/play-on :ufo {:balance (ufo-balance ufo-dist)
                               :volume  (ufo-volume ufo-dist)})
          (handle-ufo-collision)))
    state))


(defn kill [state]
  (-> state
      (audio/play-off :ufo 1000)
      (update :ufo assoc :killed? true)))