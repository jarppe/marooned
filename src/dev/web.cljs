(ns web
  (:require [clojure.string :as str]
            [goog.object :as g]
            [marooned.state :as state]))


#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn init []
  (println "user.cljs: init"))


(comment

  (def ^:const sin js/Math.sin)
  (def ^:const cos js/Math.cos)
  (def ^:const atan js/Math.atan2)
  (def ^:const sqrt js/Math.sqrt)


  (require '[marooned.state :as state])
  (js/console.log "state:" (pr-str @state/app-state))
  (+ 1 2)
  (.-performance js/window)
  .performance.now

  (sin 0)
  (atan 1 0)



  (let [x             600
        ufo-x         800
        ship-offset-x 200]
    (- ufo-x ship-offset-x x))

  (defn vec+ [h1 v1 h2 v2]
    (let [x (+ (* v1 (sin h1))
               (* v2 (sin h2)))
          y (+ (* v1 (cos h1))
               (* v2 (cos h2)))]
      [(atan x y)
       (sqrt (+ (* x x) (* y y)))
       x y]))

  (let [[h v] (vec+ 0 10 (* 0.5 js/Math.PI) 10)]
    [(.toFixed h 2) (.toFixed v 2)])



  (defn create-element [tag & children]
    (let [[attrs children] (if (map? (first children))
                             [(first children) (rest children)]
                             [nil children])]
      [tag attrs children]))


  (defn polygon [args & children]
    (apply create-element "polygon"
           (if (vector? (:points args))
             (update args :points (fn [points]
                                    (reduce (fn [s [x y]]
                                              (str s x "," y " "))
                                            ""
                                            points)))
             args)
           children))

  (polygon {:points [[1 2] [3 4]]})

  ;
  )
