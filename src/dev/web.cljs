(ns web
  (:require [clojure.string :as str]
            [marooned.state :as state]))



(comment

  js/window
  js/screen.orientation.type
  ;; => "landscape-secondary"
  js/screen.orientation.angle
  ;; => 270

  (def r (atom nil))

  (-> (js/screen.orientation.lock "landscape")
      (.then (fn [_] (reset! r ["screen orientation: locked"])))
      (.catch (fn [error] (reset! r ["screen orientation: error" error]))))
  @r
  (js/console.log "Hello")

  (:debug-on? @state/app-state)

  ;
  )
