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
             [:ufo "audio/ufo.mp3" {:loop true}]])


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
  (or (get sounds sound-name)
      (throw (ex-info "unknown sound" {:sound-name sound-name}))))


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


(defn- set-sound-on! [on?]
  (js/console.log "set-sound-on" on?)
  (if on?
    (u/add-class "sound" "active")
    (u/remove-class "sound" "active"))
  (.mute Howler (not on?)))


(defn init [state]
  (u/add-event-listener "sound" :click (fn [_] (swap! state/app-state update :sound-on? (fn [sound-was-on?]
                                                                                          (let [sound-on? (not sound-was-on?)]
                                                                                            (set-sound-on! sound-on?)
                                                                                            sound-on?)))))
  (assoc state :sound-on? true))
