(ns marooned.svg
  (:refer-clojure :exclude (filter set))
  (:require [clojure.string :as str]
            [goog.object :as g]
            [marooned.util :as u]))


(def ^:const xmlns "http://www.w3.org/2000/svg")


(def transform-attr [:translate
                     :rotate
                     :scale
                     :skew])


(def transform-attr? (clojure.core/set transform-attr))
(def special-attr? (conj transform-attr? :transform))


(defn parse-transform [transform]
  (when transform
    (reduce (fn [acc [_ attr args]]
              (assoc acc (keyword attr) (map parse-double (str/split args #"[\s,]"))))
            {}
            (re-seq #"([a-z]+)\(([^)]+)\)\s*" transform))))


(defn format-transform [specials]
  (reduce (fn [acc attr-name]
            (if-let [attr-value (attr-name specials)]
              (str acc
                   (name attr-name)
                   "("
                   (if (sequential? attr-value)
                     (str/join " " attr-value)
                     attr-value)
                   ") ")
              acc))
          ""
          transform-attr))


(defn get-special-attrs [elem]
  (reduce (fn [acc attr-key]
            (if-let [value (g/get elem attr-key)]
              (assoc acc attr-key value)
              acc))
          {}
          transform-attr))


(defn merge-special-attrs [elem specials]
  (reduce (fn [acc [attr-name attr-value]]
            (if (= attr-name :transform)
              (parse-transform attr-value)
              (assoc acc attr-name attr-value)))
          (get-special-attrs elem)
          specials))


(defn set-special-attrs [elem specials]
  (let [attrs (merge-special-attrs elem specials)]
    (doseq [transform-attr-name transform-attr?]
      (g/set elem transform-attr-name (transform-attr-name attrs)))
    (.setAttribute elem "transform" (format-transform attrs)))
  elem)


(defn- convert-points [args]
  (update args :points (fn [points]
                         (if (sequential? points)

                           points))))




(defn set-attr [elem & attrs]
  (let [elem (u/get-elem elem)]
    (when (seq attrs)
      (let [specials (loop [specials                      []
                            [attr-name attr-value & more] attrs]
                       (if-not attr-name
                         specials
                         (let [special? (special-attr? attr-name)]
                           (when-not special?
                             (if (and (= attr-name :points)
                                      (sequential? attr-value))
                               (.setAttribute elem "points" (reduce (if (sequential? (first attr-value))
                                                                      (fn [s [x y]] (str s x "," y " "))
                                                                      (fn [s ^js pt] (str s (.-x pt) "," (.-y pt) " ")))
                                                                    ""
                                                                    attr-value))
                               (.setAttribute elem (name attr-name) (str attr-value))))
                           (recur (if special?
                                    (conj specials [attr-name attr-value])
                                    specials)
                                  more))))]
        (when (seq specials)
          (set-special-attrs elem specials))))
    elem))


(defn get-attr [elem attr-name]
  (let [elem (u/get-elem elem)]
    (.getAttribute elem (name attr-name))))


(defn append* [elem children]
  (let [elem (u/get-elem elem)]
    (doseq [c children]
      (.append elem c))
    elem))


(defn append [elem & children]
  (append* elem children))


(defn create-element [tag & children]
  (let [elem             (js/document.createElementNS xmlns tag)
        [attrs children] (if (map? (first children))
                           [(first children) (rest children)]
                           [nil children])]
    (g/set elem "translate" [0.0 0.0])
    (g/set elem "rotate" 0.0)
    (g/set elem "scale" 1.0)
    (apply set-attr elem (mapcat identity attrs))
    (doseq [c children]
      (if (sequential? c)
        (append* elem c)
        (append elem c)))
    elem))


(def svg (partial create-element "svg"))
(def g (partial create-element "g"))
(def circle (partial create-element "circle"))
(def ellipse (partial create-element "ellipse"))
(def rect (partial create-element "rect"))
(def path (partial create-element "path"))
(def line (partial create-element "line"))


(defn text [attrs & texts]
  (let [elem (js/document.createElementNS xmlns "text")]
    (doseq [[k v] attrs]
      (.setAttribute elem (name k) (str v)))
    (append* elem texts)))


(def polyline (partial create-element "polyline"))
(def polygon (partial create-element "polygon"))


(def defs (partial create-element "defs"))

(def clip-path (partial create-element "clipPath"))
(def pattern (partial create-element "pattern"))
(def filter (partial create-element "filter"))
(def fe-gaussian-blur (partial create-element "feGaussianBlur"))
(def fe-drop-shadow (partial create-element "feGaussianBlur"))
(def fe-offset (partial create-element "feoffset"))
(def fe-merge (partial create-element "feMerge"))
(def fe-merge-node (partial create-element "feMergeNode"))
(def fe-color-matrix (partial create-element "feColorMatrix"))

(def linear-gradient (partial create-element "linearGradient"))
(def radial-gradient (partial create-element "radialGradient"))
(def stop (partial create-element "stop"))
(def set (partial create-element "set"))


(defn mpath [id]
  (let [elem (create-element "mpath")]
    (.setAttribute elem "href" (str "#" id))
    elem))


(def animate (partial create-element "animate"))
(def animate-motion (partial create-element "animateMotion"))


(def point (try
             ; Try standard way:
             (let [^js elem (circle {})]
               (.isPointInFill elem (js/DOMPoint 0 0)))
             (fn
               ([]
                (js/DOMPoint.))
               ([x y]
                (js/DOMPoint. x y)))
             (catch :default _e
               ; Chrome still uses non-standrd way:
               (let [svg (delay (create-element "svg"))]
                 (fn
                   ([]
                    (.createSVGPoint @svg))
                   ([x y]
                    (let [point (.createSVGPoint @svg)]
                      (set! (.-x point) x)
                      (set! (.-y point) y)
                      point)))))))


(defn set-point [^js pt x y]
  (set! (.-x pt) x)
  (set! (.-y pt) y)
  pt)


(defn is-xy-in?
  ([^js elem pt]
   (.isPointInFill elem pt))
  ([^js elem x y]
   (.isPointInFill elem (point x y))))
