(ns day-02-1202-program-alarm.intcode-processor
  (:require [clojure.spec.alpha :as s]
            [day-02-1202-program-alarm.intcode-computer :as computer]))

(declare run-program)
(declare update-program-counter)
(declare halt-program?)

(defn run-program [input-program]
  (loop [program input-program
         program-counter 0]
    (let
     [updated-program (computer/run-next-instruction program program-counter)
      updated-program-counter (update-program-counter program-counter program)]
      (if (halt-program? updated-program-counter)
        updated-program
        (recur updated-program updated-program-counter)))))

(defn update-program-counter [program-counter program]
  (if (= (nth program program-counter) 99)
    -1
    (+ program-counter 4)))

(defn halt-program? [program-counter]
  (= program-counter -1))
