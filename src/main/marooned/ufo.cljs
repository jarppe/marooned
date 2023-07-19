(ns marooned.ufo
  (:require [marooned.svg :as svg]))


(defn make-ufo [x y w h lap-duration-sec]
  (let [path    (svg/path {:id           "ufo-path"
                           :stroke-width 2
                           :stroke       "gray"
                           :fill         "none"
                           :d            (str "M " x " " y " "
                                              "c " w " 0 " (- w) " " h " 0 " h " "
                                              "c " w " 0 " (- w) " " (- h) " 0 " (- h))})
        hull    (svg/ellipse {:fill "rgb(17, 158, 49)"
                              :cx   0
                              :cy   0
                              :rx   80
                              :ry   15})
        cockpit (svg/ellipse {:translate [0 -15]
                              :fill      "rgb(39, 182, 71)"
                              :cx        0
                              :cy        0
                              :rx        30
                              :ry        10})
        shape   (svg/g hull cockpit)]
    {:shape      shape
     :hull       hull
     :update-pos (let [path-length (.getTotalLength path)]
                   (fn [ts]
                     (let [n (mod (/ ts lap-duration-sec) path-length)
                           p (.getPointAtLength path n)
                           x (.-x ^js p)
                           y (.-y ^js p)]
                       (svg/set-attr hull :cx x :cy y)
                       (svg/set-attr cockpit :cx x :cy y))))}))

