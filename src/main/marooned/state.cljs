(ns marooned.state)


(defonce app-state (atom nil))


(defn on-change [path listener & args]
  (let [path (if (vector? path) path [path])]
    (add-watch app-state (gensym) (fn [_ _ old-state new-state]
                                    (let [old-value (get-in old-state path)
                                          new-value (get-in new-state path)]
                                      (when (not= old-value new-value)
                                        (js/setTimeout (fn [_] (apply listener new-value args)) 0)))))))
