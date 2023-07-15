(ns marooned.game
  (:require [marooned.state :as state]
            [marooned.audio :as audio]
            [marooned.util :as u]
            [marooned.svg :as svg]
            [marooned.debug :as debug]))


(def ^:const PI js/Math.PI)
(def ^:const PIp2 (/ js/Math.PI 2.0))
(def ^:const PIx2 (* js/Math.PI 2.0))

(def ^:const sin js/Math.sin)
(def ^:const cos js/Math.cos)
(def ^:const atan js/Math.atan2)
(def ^:const sqrt js/Math.sqrt)


(defn deg->rad [v] (-> v (/ 180.0) (* PI)))
(defn rad->deg [v] (-> v (/ PI) (* 180.0)))


(def ^:const max-dh (/ PI 1000.0))
(def ^:const min-dh (- max-dh))
(def ^:const side-thruster-delta (/ max-dh 80.0))
(def ^:const forward-thruster-delta 0.01)


(def init-game {:ts 0
                :x  0.0
                :y  0.0
                :vh 0.0
                :vs 0.0
                :h  0.0
                :dh 0.0})


(defn vec+ [h1 v1 h2 v2]
  (let [x (+ (* v1 (sin h1))
             (* v2 (sin h2)))
        y (+ (* v1 (cos h1))
             (* v2 (cos h2)))]
    [(atan x y)
     (sqrt (+ (* x x) (* y y)))
     x (- y)]))


(defn handle-thrusters [state]
  (let [dt                   (:dt state)
        left-thruster-delta  (if (:left-thruster state)
                               (- side-thruster-delta)
                               0.0)
        right-thruster-delta (if (:right-thruster state)
                               side-thruster-delta
                               0.0)
        dh                   (u/clamp min-dh
                                      (+ (:dh state)
                                         (+ left-thruster-delta right-thruster-delta))
                                      max-dh)
        h                    (+ (:h state) (* dt dh))
        forward-thruster     (if (:forward-thruster state)
                               forward-thruster-delta
                               0.0)
        [nh nv nx ny]        (vec+ (:vh state) (:vs state) h forward-thruster)
        x                    (+ (:x state) nx)
        y                    (+ (:y state) ny)]
    (svg/set-attr (:speed state) :rotate (rad->deg nh) :y2 (* -100 nv))
    (svg/set-attr (:hull state) :rotate (rad->deg h))
    (svg/set-attr (:ship state) :translate [x y])
    (assoc state
           :x x
           :y y
           :vh nh
           :vs nv
           :h h
           :dh dh)))


(defn tick [state ts]
  (let [prev-ts (:ts state)
        dt      (- ts prev-ts)]
    (-> state
        (assoc :dt dt)
        (handle-thrusters)
        (debug/update-debug)
        (assoc :ts ts))))


(defn on-tick [ts]
  (swap! state/app-state tick ts))


(defn make-thruster-ctrl [thruster]
  (let [cone (keyword (str (name thruster) "-cone"))]
    (fn [down?]
      (swap! state/app-state (fn [state]
                               (update state :sound update thruster (fn [sound-id]
                                                                      (if down?
                                                                        (do
                                                                          (svg/set-attr (state cone) :display "")
                                                                          (audio/play-on thruster))
                                                                        (do
                                                                          (svg/set-attr (state cone) :display "none")
                                                                          (audio/play-off thruster sound-id 200))))))))))


(defn init! []
  (state/on-change :left-thruster (make-thruster-ctrl :left-thruster))
  (state/on-change :right-thruster (make-thruster-ctrl :right-thruster))
  (state/on-change :forward-thruster (make-thruster-ctrl :forward-thruster))
  (state/on-change :cannon (fn [down?]
                             (when down?
                               (audio/play-on :cannon))))
  (state/on-change :reset! (fn [down?]
                             (when down?
                               (swap! state/app-state merge init-game))))
  (swap! state/app-state merge init-game))