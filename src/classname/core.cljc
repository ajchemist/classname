(ns classname.core
  (:require
   [clojure.string :as string]))

(defn- parse-args [xs]
  (loop [res {}, xs xs]
    (let [x (first xs), rest (rest xs)]
      (cond
        (string?     x) (recur (assoc res x        true) rest)
        (keyword?    x) (recur (assoc res (name x) true) rest)
        (symbol?     x) (recur (assoc res (name x) true) rest)
        (number?     x) (recur (assoc res (str x)  true) rest)
        (map?        x) (recur (merge res x) rest)
        (sequential? x) (recur (merge res (parse-args x)) rest)
        (set?        x) (recur (merge res (parse-args (seq x))) rest)
        :else (if (empty? rest) res (recur res rest))))))

(defn classname
  "Merge-like classname utility"
  [& xs]
  (->> xs
    (parse-args)
    (filter #(val %))
    (keys)
    (string/join " ")))
