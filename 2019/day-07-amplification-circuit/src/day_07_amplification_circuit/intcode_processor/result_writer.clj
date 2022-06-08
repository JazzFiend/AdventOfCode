(ns day-07-amplification-circuit.intcode-processor.result-writer 
  (:require [day-07-amplification-circuit.intcode-processor.instruction-decoder :as instruction-decoder]))

(declare write-to-memory-location)
(declare append-to-output-buffer)

(defn write-to-program [opcode program program-counter value]
  (if (instruction-decoder/perform-memory-write? opcode)
    (if (instruction-decoder/two-parameter-opcode? opcode)
      (let [write-location (nth program (+ program-counter 3))]
        (write-to-memory-location program value write-location))

      ;; If the opcode does not have two parameters, the only other option is an input opcode
      (let [write-location (nth program (+ program-counter 1))]
        (write-to-memory-location program value write-location)))

    program))

(defn write-to-output-buffer [opcode output-buffer value]
  (if (instruction-decoder/perform-output-write? opcode)
    (append-to-output-buffer output-buffer value)
    output-buffer))

(defn write-to-memory-location [program value location]
  (assoc program location value))

(defn append-to-output-buffer [output value]
  (conj output value))