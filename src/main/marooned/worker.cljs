(ns marooned.worker
  (:require [marooned.worker.cache :as cache]
            [marooned.audio :as audio]))


(js/console.info "worker: initializing...")


(.addEventListener js/self "activate"
                   (fn [^js event]
                     (js/console.info "worker: activate")
                     (.waitUntil event (cache/enable-navigation-preload))))


(.addEventListener js/self "install"
                   (fn [^js event]
                     (js/console.info "worker: install")
                     (.waitUntil event (cache/add-resources-to-cache (concat ["./index.html"
                                                                              "./styles.css"
                                                                              "./shared.js"
                                                                              "./app.js"
                                                                              "./favicon.ico"]
                                                                             (->> audio/audios
                                                                                  (map (fn [[_ f]] (str "./" f)))
                                                                                  (set)))))))


(.addEventListener js/self "fetch"
                   (fn [^js event]
                     (.respondWith event (cache/cache-fetch event))))


(.addEventListener js/self "message"
                   (fn [event]
                     (js/console.info "worker: message" event)))


#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn init []
  (js/console.info "worker: init"))
