(ns day-07-amplification-circuit.intcode-processor.intcode-computer
  (:require [day-07-amplification-circuit.intcode-processor.instruction-decoder :as instruction-decoder]
            [day-07-amplification-circuit.intcode-processor.instruction-executor :as instruction-executor]
            [day-07-amplification-circuit.intcode-processor.parameter-extractor :as parameter-extractor]))

(declare execute-opcode)

(defn run-next-instruction [program program-counter input]
  (let [opcode (instruction-decoder/decode-opcode program program-counter)]
    (execute-opcode opcode program program-counter input)))

(defn execute-opcode [opcode program program-counter input]
  (cond
    (instruction-decoder/two-parameter-opcode? opcode)
    (let [parameter-1 (parameter-extractor/extract-parameter program program-counter 1)
          parameter-2 (parameter-extractor/extract-parameter program program-counter 2)]
      (instruction-executor/run-two-parameter-opcode opcode parameter-1 parameter-2))

    (instruction-decoder/one-parameter-opcode? opcode)
    (let [parameter (parameter-extractor/extract-parameter program program-counter 1)]
      (instruction-executor/run-one-parameter-opcode opcode parameter))

    (instruction-decoder/input-parameter-opcode? opcode)
    (instruction-executor/run-input-parameter-opcode opcode input)

    (instruction-decoder/zero-parameter-opcode? opcode)
    (instruction-executor/run-zero-parameter-opcode opcode)

    :else (throw (Exception. (str "Invalid opcode encountered: " opcode)))))

(defn update-input [opcode input]
  (if (and (instruction-decoder/input-parameter-opcode? opcode) (> (count input) 0))
    (drop 1 input)
    input))
