(ns day-04-secure-container.password-rule-checker
  (:require [clojure.spec.alpha :as s]))

(declare contains-repeated-digits)
(declare are-adjacent-chars-equal)
(declare are-all-digits-increasing)
(declare adjacent-digits-increasing)
(declare first-character-to-compare)
(declare second-character-to-compare)

(defn contains-repeated-digits [password]
  (loop [i 0]
    (if (are-adjacent-chars-equal password i)
      true
      (if (= i (- (count password) 2))
        false
        (recur (inc i))))))

(defn are-adjacent-chars-equal [password index]
  (= (first-character-to-compare password index) (second-character-to-compare password index)))

(defn are-all-digits-increasing [password]
  (loop [i 0]
    (if (adjacent-digits-increasing password i)
      (if (= i (- (count password) 2))
        true
        (recur (inc i)))
      false)))

(defn adjacent-digits-increasing [password index]
  (<= (Integer/parseInt (first-character-to-compare password index)) (Integer/parseInt (second-character-to-compare password index))))

(defn first-character-to-compare [string index]
  (subs string index (+ index 1)))

(defn second-character-to-compare [string index]
  (subs string (+ index 1) (+ index 2)))