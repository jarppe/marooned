(ns marooned.util
  (:require [goog.object :as g]))


(def ^:const PI js/Math.PI)
(def ^:const PIp2 (/ js/Math.PI 2.0))
(def ^:const PIx2 (* js/Math.PI 2.0))

(def ^:const sin js/Math.sin)
(def ^:const cos js/Math.cos)
(def ^:const atan js/Math.atan2)
(def ^:const sqrt js/Math.sqrt)


(defn deg->rad [v] (-> v (/ 180.0) (* PI)))
(defn rad->deg [v] (-> v (/ PI) (* 180.0)))


(defn js-get [elem key]
  (when elem
    (g/get elem key)))


(defn js-get-in [elem keys]
  (reduce js-get
          elem
          keys))


(defn js-set [elem k v & key-vals]
  (g/set elem k v)
  (if (seq key-vals)
    (recur elem (first key-vals) (second key-vals) (nnext key-vals))
    elem))


(defn scaler [[src-min src-max] [target-min target-max]]
  (let [src-diff    (double (- src-max src-min))
        target-diff (double (- target-max target-min))]
    (fn [v]
      (+ target-min (* target-diff (/ (- v src-min) src-diff))))))


(defn bound [min-value max-value]
  (fn [v]
    (cond
      (< v min-value) min-value
      (> v max-value) max-value
      :else v)))


(defn clamp [min-value value max-value]
  (if (< value min-value)
    min-value
    (if (> value max-value)
      max-value
      value)))


(defn vec+ [h1 v1 h2 v2]
  (let [x (+ (* v1 (sin h1))
             (* v2 (sin h2)))
        y (+ (* v1 (cos h1))
             (* v2 (cos h2)))]
    {:h  (atan x y)
     :v  (sqrt (+ (* x x) (* y y)))
     :dx x
     :dy (- y)}))


(defn get-elem ^js [id]
  (if (string? id)
    (js/document.getElementById  id)
    id))


(defn get-class-list ^js [elem]
  (js-get (get-elem elem) "classList"))


(defn add-class [elem class]
  (let [class-list (get-class-list elem)]
    (.add class-list class))
  elem)


(defn add-classes [elem classes]
  (let [class-list (get-class-list elem)]
    (doseq [class classes]
      (.add class-list class)))
  elem)


(defn remove-class [elem class]
  (let [class-list (get-class-list elem)]
    (.remove class-list class))
  elem)


(defn remove-classes [elem classes]
  (let [class-list (get-class-list elem)]
    (doseq [class classes]
      (.remove class-list class)))
  elem)


(defn set-attr ^js [elem & attrs]
  (let [elem (get-elem elem)]
    (loop [[attr-name attr-value & more] attrs]
      (.setAttribute elem (name attr-name) (str attr-value))
      (if (seq more)
        (recur more)
        elem))))


(defn set-text ^js [elem text-content]
  (let [elem (get-elem elem)]
    (js-set elem "textContent" text-content)
    elem))


(defn append* [elem children]
  (let [elem (get-elem elem)]
    (doseq [c children]
      (.append elem c))
    elem))


(defn append [elem & children]
  (append* elem children))


(defn create-element [tag & children]
  (let [[attrs children] (if (map? (first children))
                           [(first children) (rest children)]
                           [nil children])
        elem             (js/document.createElement tag)]
    (doseq [[attr-name attr-value] attrs]
      (if (= attr-name :class)
        (add-classes elem (map str (if (sequential? attr-value)
                                     (remove nil? attr-value)
                                     (cons attr-value nil))))
        (.setAttribute elem (name attr-name) (str attr-value))))
    (doseq [c children]
      (if (sequential? c)
        (append* elem c)
        (append elem c)))
    elem))


(defn remove-child [parent child]
  (let [pe (get-elem parent)
        ce (get-elem child)]
    (when (and pe ce)
      (.removeChild pe ce)))
  parent)


(defn clear-elem ^js [elem]
  (set-text elem ""))


(defn add-event-listener
  ([elem event handler] (add-event-listener elem event handler nil))
  ([elem event handler opts]
   (let [elem (get-elem elem)]
     (.addEventListener elem (name event) handler opts)
     elem)))
