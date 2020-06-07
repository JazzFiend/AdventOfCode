(ns day-04-secure-container.bulk-password-checker
  (:require [clojure.spec.alpha :as s]
            [day-04-secure-container.password-checker :as password-checker]))

(declare count-valid-passwords)
(declare construct-password-list)

(defn count-valid-passwords-v1 [start end]
  (count (filter identity (map password-checker/check-password-v1 (map str (construct-password-list start end))))))

(defn count-valid-passwords-v2 [start end]
  (count (filter identity (map password-checker/check-password-v2 (map str (construct-password-list start end))))))

(defn construct-password-list [start end]
  (loop [accum []
         i     start]
    (if (> i end)
      accum
      (recur (conj accum i)
             (inc i)))))