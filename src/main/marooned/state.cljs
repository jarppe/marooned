(ns marooned.state)


(defonce app-state (atom nil))


(defn on-change [key listener & args]
  (add-watch app-state (gensym) (fn [_ _ old-state new-state]
                                  (let [old-value (get old-state key)
                                        new-value (get new-state key)]
                                    (when (not= old-value new-value)
                                      (js/setTimeout (fn [_] (apply listener new-value args)) 0))))))
