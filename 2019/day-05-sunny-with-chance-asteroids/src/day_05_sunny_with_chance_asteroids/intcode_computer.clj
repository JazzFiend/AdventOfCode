(ns day-05-sunny-with-chance-asteroids.intcode-computer
  (:require [clojure.spec.alpha :as s]
            [day-05-sunny-with-chance-asteroids.parameter-extractor :as parameter-extractor]
            [day-05-sunny-with-chance-asteroids.instruction-decoder :as instruction-decoder]
            [day-05-sunny-with-chance-asteroids.output-writer :as output-writer]))

(declare execute-opcode)

(defn run-next-instruction [program program-counter input]
  (let [opcode (instruction-decoder/decode-opcode program program-counter)]
    (execute-opcode opcode program program-counter input)))

(defn execute-opcode [opcode program program-counter input]
  (cond
    (instruction-decoder/is-two-parameter-opcode opcode)
    (let [parameter-1 (parameter-extractor/extract-parameter program program-counter 1)
          parameter-2 (parameter-extractor/extract-parameter program program-counter 2)]
      (instruction-decoder/run-two-parameter-opcode opcode parameter-1 parameter-2))

    (instruction-decoder/is-one-parameter-opcode opcode)
    (let [parameter (parameter-extractor/extract-parameter program program-counter 1)]
      (instruction-decoder/run-one-parameter-opcode opcode parameter))

    (instruction-decoder/is-input-parameter-opcode opcode)
    (instruction-decoder/run-input-parameter-opcode opcode input)

    (instruction-decoder/is-zero-parameter-opcode opcode)
    (instruction-decoder/run-zero-parameter-opcode opcode)

    :else (throw (Exception. (str "Invalid opcode encountered: " opcode)))))

(defn write-to-program [opcode program program-counter value]
  (if (instruction-decoder/perform-memory-write opcode)
    (cond
      (instruction-decoder/is-two-parameter-opcode opcode)
      (let [write-location (nth program (+ program-counter 3))]
        (output-writer/write-to-memory program value write-location))

      (instruction-decoder/is-input-parameter-opcode opcode)
      (let [write-location (nth program (+ program-counter 1))]
        (output-writer/write-to-memory program value write-location))

      :else (throw (Exception. (str "Invalid opcode encountered: " opcode))))

    program))

(defn write-to-output-buffer [opcode output-buffer value]
  (if (instruction-decoder/perform-output-write opcode)
    (output-writer/write-to-output-buffer output-buffer value)
    output-buffer))
