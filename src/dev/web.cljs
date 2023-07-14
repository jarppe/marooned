(ns web
  (:require [clojure.string :as str]
            [goog.object :as g]))


#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn init []
  (println "user.cljs: init"))


(comment

  (def ^:const sin js/Math.sin)
  (def ^:const cos js/Math.cos)
  (def ^:const atan js/Math.atan2)
  (def ^:const sqrt js/Math.sqrt)


  (sin 0)
  (atan 1 0)

  (defn vec+ [h1 v1 h2 v2]
    (let [x (+ (* v1 (sin h1))
               (* v2 (sin h2)))
          y (+ (* v1 (cos h1))
               (* v2 (cos h2)))]
      [(atan x y)
       (sqrt (+ (* x x) (* y y)))
       x y]))

  (let [[h v] (vec+ 0 10 (* 0.5 js/Math.PI) 10)]
    [(.toFixed h 2) (.toFixed v 2)]))
