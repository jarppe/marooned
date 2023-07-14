(ns marooned.worker.cache
  (:require [marooned.util :refer [js-get js-get-in]]))


(defn cache []
  (js/caches.open "v2"))


(defn add-resources-to-cache [resources]
  (-> (cache)
      (.then (fn [^js cache] (.addAll cache (clj->js resources))))))


(defn put-in-cache [req resp]
  (-> (cache)
      (.then (fn [^js cache] (.put cache req resp)))))


(defn cache-fetch [event]
  (let [request (js-get event "request")]
    (-> (js/Promise.resolve request)
        (.then js/fetch)
        (.then (fn [^js response]
                 (-> (put-in-cache request (.clone response))
                     (.then (fn [_] response)))))
        (.catch (fn [_error]
                  (js/caches.match request)))
        (.catch (fn [error]
                  (js/console.log "cache: cache-fetch: cache failed" request error)
                  (throw error))))))


(defn enable-navigation-preload []
  (when-let [preload (js-get-in js/self ["registration" "navigationPreload"])]
    (.enable preload)))
