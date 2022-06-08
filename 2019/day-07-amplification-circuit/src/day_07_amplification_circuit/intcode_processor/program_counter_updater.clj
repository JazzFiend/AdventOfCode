(ns day-07-amplification-circuit.intcode-processor.program-counter-updater 
  (:require [day-07-amplification-circuit.intcode-processor.instruction-decoder :as instruction-decoder]))

(defn update-program-counter-by-branch-condition [value program-counter]
  (if (= value "BRANCH_FALSE")
    (+ program-counter 3)
    value))

(defn update-program-counter-by-argument-number [opcode program-counter]
  (if (instruction-decoder/two-parameter-opcode? opcode)
    (+ program-counter 4)
    (if (or (instruction-decoder/one-parameter-opcode? opcode) (instruction-decoder/input-parameter-opcode? opcode))
      (+ program-counter 2)
      (throw (Exception. (str "Invalid opcode encountered: " opcode))))))