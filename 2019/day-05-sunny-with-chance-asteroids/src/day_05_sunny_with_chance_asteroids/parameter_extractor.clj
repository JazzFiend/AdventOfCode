(ns day-05-sunny-with-chance-asteroids.parameter-extractor
  (:require [clojure.math.numeric-tower :as math]))

(declare extract-mode)
(declare is-positional-mode)

(defn extract-parameter [program program-counter parameter-number]
  (let [instruction  (nth program program-counter)
        parameter-mode (extract-mode instruction parameter-number)]
    (if (is-positional-mode parameter-mode)
      (nth program (nth program (+ program-counter parameter-number)))
      (nth program (+ program-counter parameter-number)))))

(defn extract-mode [instruction parameter-number]
  (mod (quot instruction (math/expt 10 (+ parameter-number 1))) 2))

(defn is-positional-mode [mode]
  (if (= mode 0)
    true
    false))
