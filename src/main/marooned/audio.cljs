(ns marooned.audio
  (:require ["howler" :refer [Howl Howler]]
            [marooned.state :as state]
            [marooned.util :as u]))


(def ^:const default-volume 0.5)


(def audios [[:left-thruster "audio/thruster.mp3" {:loop    true
                                                   :balance 0.0}]
             [:right-thruster "audio/thruster.mp3" {:loop    true
                                                    :balance 1.0}]
             [:forward-thruster "audio/rocket.mp3" {:loop   true
                                                    :volume 0.2}]
             [:cannon "audio/cannon.mp3" {:volume 0.1}]])


(def sounds (reduce (fn [acc [k url opts]]
                      (assoc acc k (let [sound (Howl. (clj->js (merge {:src    url
                                                                       :volume default-volume}
                                                                      opts)))]
                                     (when-let [balance (:balance opts)]
                                       (.stereo ^js sound balance))
                                     sound)))
                    {}
                    audios))


(defn play-on
  ([sound-name] (play-on sound-name nil))
  ([sound-name balance]
   (let [^js sound (sounds sound-name)]
     (when balance
       (.stereo sound balance))
     (.play sound))))


(defn play-off
  ([sound-name sound-id] (play-off sound-name sound-id 100))
  ([sound-name sound-id delay]
   (let [sound (sounds sound-name)]
     (.fade ^js sound (.volume sound) 0 delay sound-id)
     nil)))


(defn set-sound-on [on?]
  (js/console.log "set-sound-on" on?)
  (if on?
    (u/add-class "sound" "active")
    (u/remove-class "sound" "active"))
  (.mute Howler (not on?)))


(defn init! []
  (u/add-event-listener "sound" :click (fn [_] (swap! state/app-state update :sound-on? not)))
  (state/on-change :sound-on? set-sound-on)
  (swap! state/app-state assoc :sound-on? true))
