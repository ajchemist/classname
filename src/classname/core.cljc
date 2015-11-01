(ns classname.core
  (:require clojure.string))

(defn- parse-args [xs]
  (loop [res [], xs xs]
    (let [x (first xs), next (next xs)]
      (cond
        (string?  x) (recur (conj res {x true}) next)
        (keyword? x) (recur (conj res {x true}) next)
        (symbol?  x) (recur (conj res {x true}) next)
        (number?  x) (recur (conj res {(str x) true}) next)
        (map?     x) (recur (conj res x) next)
        (vector?  x) (recur (into res (parse-args x)) next)
        :else (if (empty? next) res (recur res next))))))

(defn classname [& xs]
  (->> xs
    parse-args
    (apply merge)
    (map #(when (second %) (name (first %))))
    (remove nil?)
    (clojure.string/join " ")))
