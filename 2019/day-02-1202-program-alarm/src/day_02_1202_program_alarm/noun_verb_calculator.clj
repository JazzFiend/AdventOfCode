(ns day-02-1202-program-alarm.noun-verb-calculator
  (:require [clojure.spec.alpha :as s]
            [day-02-1202-program-alarm.intcode-processor :as processor]))

          (declare find-noun-verb-for-output)
(declare calculate-noun-verb-combo)

(defn find-noun-verb-for-output [input-program desired-value-at-zero]
  (loop [i 0
         j 0]
    (let [modified-program (assoc (assoc input-program 1 i) 2 j)
          output (processor/run-program modified-program)]
      (if (= (nth output 0) desired-value-at-zero)
        (calculate-noun-verb-combo output)
        (if (< j 99)
          (recur i (inc j))
          (if (< i 99)
            (recur (inc i) 0)
            (throw (Exception. "Desired value not found"))))))))

(defn calculate-noun-verb-combo [program-output]
  (+ (* (nth program-output 1) 100) (nth program-output 2)))
