(ns marooned.app
  (:require [marooned.fullscreen :as fullscreen]
            [marooned.audio :as sound]
            [marooned.controls :as controls]
            [marooned.game :as game]
            [marooned.scene :as scene]
            [marooned.debug :as debug]
            [marooned.util :refer [js-get]]))


(defonce init-app
  (delay (println "init: registering worker...")
         (-> (js-get js/navigator "serviceWorker")
             (.register "worker.js" #js {:scope "./"})
             (.then (fn [_] (println "init: worker registered")))
             (.catch (fn [error] (println "init: worker failed" error))))
         (println "init: init modules")
         (sound/init!)
         (fullscreen/init!)
         (controls/init!)
         (println "init: game init")
         (game/init!)
         (println "init: scene")
         (scene/init!)
         (println "init: debug")
         (debug/init!)
         (println "init: animation")
         (let [start-time (-> (js-get js/window "performance")
                              (.now))]
           (js/window.requestAnimationFrame
            (fn animation [ts]
              (let [now (- ts start-time)]
                (game/on-tick now))
              (js/window.requestAnimationFrame animation))))
         (println "init: done")))


; Start point of the app. In prod called just once from index.html, when developing
; called also when changed:

(defn ^:export run []
  @init-app)
