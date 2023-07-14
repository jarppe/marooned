(ns marooned.fullscreen
  (:require [marooned.state :as state]
            [marooned.util :as u :refer [js-get]]))


(defn- set-fullscreen [on?]
  (if on?
    (do (u/add-class "fullscreen" "active")
        (-> (.requestFullscreen (u/get-elem "wrapper"))
            (.then (fn [_] (println "fullscreen: success"))
                   (fn [_] (println "fullscreen: rejected")))))
    (do (u/remove-class "fullscreen" "active")
        (-> (js/document.exitFullscreen)
            (.then (fn [_] (println "fullscreen: success"))
                   (fn [_] (println "fullscreen: rejected")))))))


(defn init! []
  (let [can-fullscreen? (some? (js-get js/document "exitFullscreen"))
        is-fullscreen?  (some? (js-get js/document "fullscreenElement"))]
    (when-not can-fullscreen?
      (u/set-attr "fullscreen" :display "none"))
    (when can-fullscreen?
      (u/add-event-listener "fullscreen" :click (fn [_] (swap! state/app-state update :is-fullscreen? not)))
      (state/on-change :is-fullscreen? set-fullscreen))
    (swap! state/app-state merge {:can-fullscreen? can-fullscreen?
                                  :is-fullscreen?  is-fullscreen?})))