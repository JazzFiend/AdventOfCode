(ns day-07-amplification-circuit.intcode-processor.intcode-processor
  (:require [day-07-amplification-circuit.intcode-processor.instruction-decoder :as instruction-decoder]
            [day-07-amplification-circuit.intcode-processor.intcode-computer :as computer]
            [day-07-amplification-circuit.intcode-processor.program-counter-updater :as program-counter-updater]
            [day-07-amplification-circuit.intcode-processor.result-writer :as result-writer]))

(declare run-program)
(declare update-program-counter)
(declare halt-program?)

(defn run-program-return-output [input-program input-data]
  (run-program input-program input-data "output"))

(defn run-program-return-program [input-program input-data]
  (run-program input-program input-data "program"))

(defn run-program [input-program input-data output-type]
  (loop [program input-program
         program-counter 0
         input input-data
         output-buffer []]
    (let
     [opcode (instruction-decoder/decode-opcode program program-counter)
      instruction-result (computer/run-next-instruction program program-counter input)
      updated-input (computer/update-input opcode input)
      updated-program (result-writer/write-to-program opcode program program-counter instruction-result)
      updated-output-buffer (result-writer/write-to-output-buffer opcode output-buffer instruction-result)
      updated-program-counter (update-program-counter opcode program-counter instruction-result)]
      (if (halt-program? updated-program-counter)
        (if (= output-type "output")
          updated-output-buffer
          (if (= output-type "program")
            updated-program
            (throw (Exception. (str "Invalid ouput-type encountered: " output-type)))))
        (recur updated-program updated-program-counter updated-input updated-output-buffer)))))

(defn update-program-counter [opcode program-counter value]
  (cond
    (or (instruction-decoder/halt? opcode) (= value "INPUT_ARRAY_EMPTY"))
    "HALT"

    (instruction-decoder/branch? opcode)
    (program-counter-updater/update-program-counter-by-branch-condition value program-counter)

    :else (program-counter-updater/update-program-counter-by-argument-number opcode program-counter)))

(defn halt-program? [program-counter]
  (= program-counter "HALT"))
