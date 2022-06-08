(ns day-07-amplification-circuit.amplifier-array
  (:require [day-07-amplification-circuit.intcode-processor.intcode-processor :as intcode-processor]
            [clojure.math.combinatorics :refer [permutations]]))

(declare run-five-amplifier-series)
(declare start-feedback-loop)
(declare run-feedback-loop)
(declare setup-inputs)

(defn calculate-largest-output [program]
  (let [phase-setting-permutations (permutations [0 1 2 3 4])]
    (apply max (mapv run-five-amplifier-series phase-setting-permutations (repeat program)))))

(defn run-five-amplifier-series [phase-setting program]
  (let [amplifier-1-output (intcode-processor/run-program-return-output
                            program [(nth phase-setting 0) 0])
        amplifier-2-output (intcode-processor/run-program-return-output
                            program [(nth phase-setting 1) (nth amplifier-1-output 0)])
        amplifier-3-output (intcode-processor/run-program-return-output
                            program [(nth phase-setting 2) (nth amplifier-2-output 0)])
        amplifier-4-output (intcode-processor/run-program-return-output
                            program [(nth phase-setting 3) (nth amplifier-3-output 0)])
        amplifier-5-output (intcode-processor/run-program-return-output
                            program [(nth phase-setting 4) (nth amplifier-4-output 0)])]
    (nth amplifier-5-output 0)))

(defn calculate-largest-output-with-feedback [program]
  (let [phase-setting-permutations (permutations [5 6 7 8 9])]
(apply max (mapv start-feedback-loop phase-setting-permutations (repeat program)))))


(defn start-feedback-loop [phase-setting program]
  (let [input-list [[(nth phase-setting 0) 0] [(nth phase-setting 1)] [(nth phase-setting 2)] [(nth phase-setting 3)] [(nth phase-setting 4)]]]
    (run-feedback-loop program input-list input-list [])))

(defn run-feedback-loop [program base-inputs usable-inputs previous-outputs]
  (let [amplifier-1-output (intcode-processor/run-program-return-output program (nth usable-inputs 0))
        amplifier-2-output (intcode-processor/run-program-return-output program (nth usable-inputs 1))
        amplifier-3-output (intcode-processor/run-program-return-output program (nth usable-inputs 2))
        amplifier-4-output (intcode-processor/run-program-return-output program (nth usable-inputs 3))
        amplifier-5-output (intcode-processor/run-program-return-output program (nth usable-inputs 4))
        new-outputs [amplifier-1-output amplifier-2-output amplifier-3-output amplifier-4-output amplifier-5-output]
        new-usable-inputs (setup-inputs base-inputs new-outputs)]
    (if (= previous-outputs new-outputs)
      (nth amplifier-5-output (- (count amplifier-5-output) 1))
      (run-feedback-loop program base-inputs new-usable-inputs new-outputs))))

(defn setup-inputs [base-inputs output]
  [(flatten (conj (nth base-inputs 0) (nth output 4)))
   (flatten (conj (nth base-inputs 1) (nth output 0)))
   (flatten (conj (nth base-inputs 2) (nth output 1)))
   (flatten (conj (nth base-inputs 3) (nth output 2)))
   (flatten (conj (nth base-inputs 4) (nth output 3)))])


