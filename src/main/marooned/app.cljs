(ns marooned.app
  (:require [marooned.state :as state]
            [marooned.fullscreen :as fullscreen]
            [marooned.audio :as sound]
            [marooned.controls :as controls]
            [marooned.game :as game]
            [marooned.scene :as scene]
            [marooned.debug :as debug]))


(defonce init-app
  (delay (when-let [^js service-worker (.-serviceWorker js/navigator)]
           (println "init: registering worker...")
           (-> service-worker
               (.register "worker.js" #js {:scope "./"})
               (.then (fn [_] (println "init: worker registered")))
               (.catch (fn [error] (println "init: worker failed" error)))))
         (println "init: init game")
         (swap! state/app-state (fn [state]
                                  (-> state
                                      (sound/init)
                                      (fullscreen/init)
                                      (controls/init)
                                      (game/init)
                                      (scene/init)
                                      (debug/init)
                                      (game/run))))
         (println "init: done")))

; Start point of the app. In prod called just once from index.html, when developing
; called also when changed:

(defn ^:export run []
  @init-app)
