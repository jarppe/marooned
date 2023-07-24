(ns marooned.ufo
  (:require [marooned.svg :as svg]
            [marooned.audio :as audio]
            [marooned.util :as u :refer [atan]]
            [marooned.bullets :as bullets]))


(def hull-fill  "rgb(17, 158, 49)")
(def cockpit-fill "rgb(58, 193, 84)")
(def hull-fill-collosion "rgb(184, 48, 48)")
(def cockpit-fill-collision "rgb(223, 34, 33)")
(def ufo-x 5700)
(def ufo-y 200)
(def ufo-shoot-rate 5000)


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
  (svg/set-attr (-> state :ufo :g) :display "on")
  (update state :ufo assoc
          :active? true
          :shoot-ts (:ts state)
          :killed false))


(defn handle-ufo-collision [state]
  (if (some (partial svg/is-xy-in? (-> state :ufo :hull)) (-> state :ship :hull-points))
    (do   (svg/set-attr (-> state :ufo :hull) :fill hull-fill-collosion)
          (svg/set-attr (-> state :ufo :cockpit) :fill cockpit-fill-collision)
          (-> state
              (audio/play :ufo-explosion)
              (assoc :status {:status :game-over
                              :reason :ufo-collision
                              :ts     (:ts state)})))
    state))


(def ufo-balance (comp (u/bound -1.0 +1.0)
                       (u/scaler [-1000 +1000] [-1.0 +1.0])))
(def ufo-volume (comp (u/bound 0.0 0.5)
                      (u/scaler [1500 0] [0.0 0.5])
                      #(js/Math.abs %)))


(defn ufo-shoot [state]
  (let [ship      (-> state :ship)
        ufo       (-> state :ufo)
        x         (:x ufo)
        y         (:y ufo)
        h         (atan (- (:x ship) x)
                        (- y (:y ship)))
        ship-hull (-> ship :hull)]
    (bullets/add-bullet state :ufo
                        x y h
                        (fn [^js pt]
                          (svg/is-xy-in? ship-hull pt))
                        (fn [state]
                          (-> state
                              (audio/play :ufo-explosion)
                              (assoc :status {:status :game-over
                                              :reason :ufo-bullet
                                              :ts     (:ts state)}))))))


(defn handle-ufo-shoot [state]
  (if (and (-> state :ship :x (> 4900))
           (> (- (-> state :ts)
                 (-> state :ufo :shoot-ts))
              ufo-shoot-rate))
    (-> state
        (ufo-shoot)
        (update :ufo assoc :shoot-ts (:ts state)))
    state))


(defn handle-ufo-killed [state]
  (if (-> state :ufo :killed)
    (do (svg/set-attr (-> state :ufo :g) :display "none")
        (-> state
            (audio/play-off :ufo)
            (audio/play :ufo-explosion)
            (update :ufo assoc :active? false)))
    state))


(defn tick [state]
  (if (and (-> state :ufo :active?)
           (-> state :status :status (= :run)))
    (let [{:keys [path path-len hull cockpit]} (:ufo state)
          n                                    (mod (/ (:ts state) 15) path-len)
          p                                    (.getPointAtLength path n)
          x                                    (.-x ^js p)
          y                                    (.-y ^js p)
          ufo-dist                             (->> state :ship :x (- ufo-x))]
      (svg/set-attr hull :cx x :cy y)
      (svg/set-attr cockpit :cx x :cy y)
      (-> state
          (update :ufo assoc :x x :y y)
          (audio/play-on :ufo {:balance (ufo-balance ufo-dist)
                               :volume  (ufo-volume ufo-dist)})
          (handle-ufo-collision)
          (handle-ufo-shoot)
          (handle-ufo-killed)))
    state))


(defn game-over [state]
  (audio/play-off state :ufo))
