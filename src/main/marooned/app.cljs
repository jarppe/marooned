(ns marooned.app
  (:require [marooned.state :as state]
            [marooned.fullscreen :as fullscreen]
            [marooned.audio :as sound]
            [marooned.controls :as controls]
            [marooned.game :as game]
            [marooned.scene :as scene]
            [marooned.debug :as debug]
            [clojure.string :as str]))


(defn ^:export run []
  (println "init: init game")
  (when-not (str/starts-with? js/window.location.host "localhost")
    (when-let [^js service-worker (.-serviceWorker js/navigator)]
      (println "init: registering worker...")
      (-> service-worker
          (.register "worker.js" #js {:scope "./"})
          (.then (fn [_] (println "init: worker registered")))
          (.catch (fn [error] (println "init: worker failed" error))))))
  (swap! state/app-state (fn [state]
                           (-> state
                               (sound/init)
                               (fullscreen/init)
                               (controls/init)
                               (game/init)
                               (scene/init)
                               (debug/init)
                               (game/run))))
  (println "init: done"))
