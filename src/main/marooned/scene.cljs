(ns marooned.scene
  (:require [marooned.util :as u]
            [marooned.svg :as svg]
            [marooned.shapes :as shapes]))


(def ship-offset-x 500)


(defn on-resize [_]
  (let [^js wrapper (u/get-elem "wrapper")
        width   (.-clientWidth wrapper)
        height  (.-clientHeight wrapper)
        scale   (min (/ width 2000.0)
                     (/ height 1000.0))]
    (js/console.log "on-resize:" width "x" height)
    (svg/set-attr "game" :viewBox (str "0 0 " width " " height))
    (svg/set-attr "scene" :scale scale :translate [0 (/ (- height (* scale 1000.0)) 2.0)])))


(defn create [state]
  (let [cave       (svg/path {:stroke       "gray"
                              :stroke-width 2
                              :fill         "rgb(24, 24, 24)"
                              :d            shapes/cave-path})
        soil       (svg/pattern {:id           "soil-pattern"
                                 :x            0
                                 :y            0
                                 :width        100
                                 :height       100
                                 :patternUnits "userSpaceOnUse"}
                                (svg/path {:fill "rgb(73, 73, 73)"
                                           :d    shapes/soil-pattern}))
        background (svg/g (svg/rect {:x      -1000
                                     :y      -1000
                                     :width  10000
                                     :height 3000
                                     :fill   "url(#soil-pattern)"}))
        dialogs    (svg/g {:stroke       "red"
                           :stroke-width 4
                           :fill         "rgb(24, 24, 24)"})
        board      (svg/g background
                          cave
                          (-> state :door :g)
                          (-> state :diamond :g)
                          (-> state :blackhole :g)
                          (-> state :bullets :g)
                          (-> state :ship :hull)
                          (-> state :ship :g)
                          (-> state :ship :speed)
                          (-> state :ufo :g))
        scene      (svg/g {:id "scene"} board dialogs)]
    (-> (u/clear-elem "game")
        (u/append* [(svg/defs soil (-> state :door :defs) (-> state :blackhole :defs))
                    scene]))
    (assoc state :scene {:scene   scene
                         :board   board
                         :cave    cave
                         :dialogs dialogs})))


(defn tick [state]
  (svg/set-attr (-> state :scene :board)
                :translate
                [(->> state :ship :x (min 5800) (- ship-offset-x)) 0])
  state)


(defn on-screen-orientation-change [^js e]
  (let [orientation-type  (-> e .-target .-type)
        orientation-angle (-> e .-target .-angle)]
    (js/console.log "on-screen-orientation-change: type =" orientation-type ", angle =" orientation-angle)))


#_(defn on-device-orientation-change [^js e]
    (let [alpha (.-alpha e)
          beta  (.-beta e)
          gamma (.-gamma e)]
      (js/console.log "on-device-orientation-change:" "alpha" alpha "beta" beta "gamma" gamma)))


(defn init [state]
  (u/add-event-listener js/window :resize on-resize)
  ;(u/add-event-listener js/window :deviceorientation on-device-orientation-change)
  (u/add-event-listener js/screen.orientation :change on-screen-orientation-change)
  (js/setTimeout on-resize 0)
  state)
