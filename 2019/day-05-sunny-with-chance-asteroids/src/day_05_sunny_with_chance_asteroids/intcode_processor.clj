(ns day-05-sunny-with-chance-asteroids.intcode-processor
  (:require [clojure.spec.alpha :as s]
            [day-05-sunny-with-chance-asteroids.instruction-decoder :as instruction-decoder]
            [day-05-sunny-with-chance-asteroids.intcode-computer :as computer]))

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
         output-buffer []]
    (let
     [opcode (instruction-decoder/decode-opcode program program-counter)
      instruction-result (computer/run-next-instruction program program-counter input-data)
      updated-program (computer/write-to-program opcode program program-counter instruction-result)
      updated-output-buffer (computer/write-to-output-buffer opcode output-buffer instruction-result)
      updated-program-counter (update-program-counter opcode program-counter instruction-result)]
      (if (halt-program? updated-program-counter)
        (if (= output-type "output")
          updated-output-buffer
          (if (= output-type "program")
            updated-program
            (throw (Exception. (str "Invalid ouput-type encountered: " output-type)))))
        (recur updated-program updated-program-counter updated-output-buffer)))))

(defn update-program-counter [opcode program-counter value]
  (cond
    (instruction-decoder/is-halt opcode)
    "HALT"

    (instruction-decoder/is-branch opcode)
    (instruction-decoder/update-program-counter-by-branch-condition value program-counter)

    :else (instruction-decoder/update-program-counter-by-argument-number opcode program-counter)))

(defn halt-program? [program-counter]
  (= program-counter "HALT"))
