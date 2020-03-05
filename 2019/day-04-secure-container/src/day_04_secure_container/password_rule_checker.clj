(ns day-04-secure-container.password-rule-checker
  (:require [clojure.spec.alpha :as s]))

(declare contains-repeated-digits)
(declare contains-two-repeated)
(declare are-all-digits-increasing)
(declare are-adjacent-chars-equal)
(declare more-than-two-repeats)
(declare is-second-to-last-character)
(declare adjacent-digits-increasing)
(declare first-character-to-compare)
(declare second-character-to-compare)

(defn contains-repeated-digits [password]
  (loop [i 0]
    (if (are-adjacent-chars-equal password i)
      true
      (if (is-second-to-last-character password i)
        false
        (recur (inc i))))))

(defn contains-two-repeated [password]
  (loop [i 0]
    (if (are-adjacent-chars-equal password i)
      (if (more-than-two-repeats password i)
        (if (is-second-to-last-character password i)
          false
          (recur (inc i)))
        true)
      (if (is-second-to-last-character password i)
        false
        (recur (inc i))))))

(defn are-all-digits-increasing [password]
  (loop [i 0]
    (if (adjacent-digits-increasing password i)
      (if (is-second-to-last-character password i)
        true
        (recur (inc i)))
      false)))

(defn are-adjacent-chars-equal [password index]
  (= (first-character-to-compare password index) (second-character-to-compare password index)))

(defn more-than-two-repeats [password index]
  (if (= index 0)
    (are-adjacent-chars-equal password (+ index 1))
    (if (= index (- (count password) 2))
      (are-adjacent-chars-equal password (- index 1))
      (or (are-adjacent-chars-equal password (- index 1)) (are-adjacent-chars-equal password (+ index 1))))))

(defn is-second-to-last-character [password index]
  (= index (- (count password) 2)))

(defn adjacent-digits-increasing [password index]
  (<= (Integer/parseInt (first-character-to-compare password index)) (Integer/parseInt (second-character-to-compare password index))))

(defn first-character-to-compare [string index]
  (subs string index (+ index 1)))

(defn second-character-to-compare [string index]
  (subs string (+ index 1) (+ index 2)))