(ns marooned.audio
  (:require ["howler" :refer [Howl Howler]]
            [marooned.state :as state]
            [marooned.util :as u]))


(def ^:const default-volume 0.5)


(def audios [[:left "audio/thruster.mp3" {:loop    true
                                          :balance 1.0}]
             [:right "audio/thruster.mp3" {:loop    true
                                           :balance 0.0}]
             [:forward "audio/rocket.mp3" {:loop   true
                                           :volume 0.2}]
             [:cannon "audio/cannon.mp3" {:volume 0.1}]
             [:click "audio/click.mp3" {:volume 0.2}]
             [:ufo "audio/ufo.mp3" {:loop true}]
             [:ufo-explosion "audio/ufo-explosion.mp3"]])


(def sounds (reduce (fn [acc [k url opts]]
                      (assoc acc k (let [sound (Howl. (clj->js (merge {:src    url
                                                                       :volume default-volume}
                                                                      opts)))]
                                     (when-let [balance (:balance opts)]
                                       (.stereo ^js sound balance))
                                     sound)))
                    {}
                    audios))


(defn get-sound ^js [sound-name]
  (get sounds sound-name))


(defn- -play [^js sound {:keys [balance volume]}]
  (when balance
    (.stereo sound balance))
  (when volume
    (.volume sound volume))
  (.play sound))


(defn play
  ([state sound-name] (play state sound-name nil))
  ([state sound-name opts]
   (let [sound (get-sound sound-name)]
     (-play sound opts)
     state)))


(defn play-on
  ([state sound-name] (play-on state sound-name nil))
  ([state sound-name {:keys [balance volume]}]
   (let [sound    (get-sound sound-name)
         sound-id (or (-> state :sound sound-name)
                      (-play sound nil))]
     (when balance
       (.stereo sound balance))
     (when volume
       (.volume sound volume))
     (update state :sound assoc sound-name sound-id))))


(defn play-off
  ([state sound-name] (play-off state sound-name nil))
  ([state sound-name delay]
   (let [sound    (get-sound sound-name)
         sound-id (-> state :sound sound-name)]
     (if sound-id
       (do (if delay
             (.fade sound (.volume sound) 0 delay sound-id)
             (.stop sound sound-id))
           (update state :sound dissoc sound-name))
       state))))


(defn toggle-sound-on! []
  (swap! state/app-state update :sound-on? (fn [was-on?]
                                             (let [on? (not was-on?)]
                                               (if on?
                                                 (u/add-class "sound" "active")
                                                 (u/remove-class "sound" "active"))
                                               (.mute Howler (not on?))
                                               on?))))


(defn init [state]
  (assoc state :sound-on? true))

