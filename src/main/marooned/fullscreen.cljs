(ns marooned.fullscreen
  (:require [marooned.state :as state]
            [marooned.util :as u]))


(defn- toggle-fullscreen [on?]
  (if on?
    (do (u/remove-class "fullscreen" "active")
        (-> (js/document.exitFullscreen)
            (.then (fn [_] (println "fullscreen: success"))
                   (fn [_] (println "fullscreen: rejected"))))
        true)
    (do (u/add-class "fullscreen" "active")
        (-> (.requestFullscreen (u/get-elem "wrapper"))
            (.then (fn [_] (println "fullscreen: success"))
                   (fn [_] (println "fullscreen: rejected"))))
        false)))


(defn init [state]
  (let [can-fullscreen? js/document.fullscreenEnabled
        is-fullscreen?  (some? (.-fullscreenElement js/document))]
    (when-not can-fullscreen?
      (u/set-attr "fullscreen" :style "display: none;"))
    (when can-fullscreen?
      (u/add-event-listener "fullscreen" :click (fn [_] (swap! state/app-state update :is-fullscreen? toggle-fullscreen))))
    (assoc state
           :can-fullscreen? can-fullscreen?
           :is-fullscreen?  is-fullscreen?)))