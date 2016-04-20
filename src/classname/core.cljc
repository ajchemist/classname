(ns classname.core
  (:require clojure.string))

(defn- parse-args [xs]
  (loop [res [], xs xs]
    (let [x (first xs), rest (rest xs)]
      (cond
        (string?     x) (recur (conj res {x true}) rest)
        (keyword?    x) (recur (conj res {x true}) rest)
        (symbol?     x) (recur (conj res {x true}) rest)
        (number?     x) (recur (conj res {(str x) true}) rest)
        (map?        x) (recur (conj res x) rest)
        (sequential? x) (recur (into res (parse-args x)) rest)
        (set?        x) (recur (into res (parse-args (seq x))) rest)
        :else (if (empty? rest) res (recur res rest))))))

(defn classname [& xs]
  (->> xs
    parse-args
    (apply merge)
    (map #(when (second %) (name (first %))))
    (remove nil?)
    (clojure.string/join " ")))
