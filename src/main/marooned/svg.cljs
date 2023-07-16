(ns marooned.svg
  (:refer-clojure :exclude (filter))
  (:require [clojure.string :as str]
            [goog.object :as g]))


(def ^:const xmlns "http://www.w3.org/2000/svg")


(def transform-attr [:translate
                     :rotate
                     :scale
                     :skew])


(def transform-attr? (set transform-attr))
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
    (.setAttributeNS elem nil "transform" (format-transform attrs)))
  elem)


(defn set-attr [elem & attrs]
  (when (seq attrs)
    (let [specials (loop [specials                      []
                          [attr-name attr-value & more] attrs]
                     (if-not attr-name
                       specials
                       (let [special? (special-attr? attr-name)]
                         (when-not special?
                           (.setAttributeNS elem nil (name attr-name) (str attr-value)))
                         (recur (if special?
                                  (conj specials [attr-name attr-value])
                                  specials)
                                more))))]
      (when (seq specials)
        (set-special-attrs elem specials))))
  elem)


(defn get-attr [elem attr-name]
  (.getAttributeNS elem  nil (name attr-name)))


(defn append* [elem children]
  (doseq [c children]
    (.appendChild elem c))
  elem)


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
    (when (#{"polygon" "path"} tag)
      (js/console.log "create-element:" tag (pr-str children)))
    (doseq [c children]
      (if (sequential? c)
        (append* elem c)
        (append elem c)))
    elem))


(defn svg [& args]
  (let [[attrs children] (if (map? (first args))
                           [(first args) (rest args)]
                           [nil args])]
    (create-element "svg" (merge {:version "1.1"
                                  :xmlns   xmlns}
                                 attrs)
                    children)))



(def g (partial create-element "g"))


(def circle (partial create-element "circle"))
(def ellipse (partial create-element "ellipse"))
(def rect (partial create-element "rect"))
(def path (partial create-element "path"))
(def line (partial create-element "line"))


(defn- convert-points [args]
  (update args :points (fn [points]
                         (if (vector? points)
                           (reduce (fn [s [x y]]
                                     (str s x "," y " "))
                                   ""
                                   points)
                           points))))


(defn polyline [args & children]
  (apply create-element "polyline" (convert-points args) children))


(defn polygon [args & children]
  (apply create-element "polygon" (convert-points args) children))


(def defs (partial create-element "defs"))

(def clip-path (partial create-element "clipPath"))

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

(def animate (partial create-element "animate"))
