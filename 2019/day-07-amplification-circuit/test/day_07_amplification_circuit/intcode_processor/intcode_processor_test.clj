(ns day-07-amplification-circuit.intcode-processor.intcode-processor-test
  (:require [midje.sweet :refer [facts fact]]
            [clojure.string :as str]
            [day-07-amplification-circuit.intcode-processor.intcode-processor :as intcode-processor]))

(defn get-program-from-text-file [file-name]
  (let [text-from-file (slurp file-name)
        instructions-as-strings (str/split text-from-file #",")]
    (vec (map read-string instructions-as-strings))))

(facts "Functional Tests"
       (fact "Each input value should be taken off the queue as they are used"
             (let [program [3 5 3 6 99 0 0]
                   input [25 52]]
               (intcode-processor/run-program-return-program program input) => [3 5 3 6 99 25 52]))

       (fact "Attempting to pull an input off the queue when it is empty should act as a HALT"
             (let [program [3 0 4 0 3 0 4 0 99]
                   input [8]]
               (intcode-processor/run-program-return-output program input) => [8])))

(facts "Acceptance Tests"
       (facts "Puzzles"
              (fact "Day 02 - Part 1"
                    (let [program (get-program-from-text-file "./test/day_07_amplification_circuit/intcode_processor/input_files/Day02_Input_Modified.txt")
                          output-program (intcode-processor/run-program-return-program program [])]
                      (nth output-program 0) => 2782414))
              (fact "Day 05 - Part 1"
                    (let [program (get-program-from-text-file "./test/day_07_amplification_circuit/intcode_processor/input_files/Day05_Input.txt")]
                      (intcode-processor/run-program-return-output program [1]) => [0 0 0 0 0 0 0 0 0 13787043]))
              (fact "Day 05 - Part 2"
                    (let [program (get-program-from-text-file "./test/day_07_amplification_circuit/intcode_processor/input_files/Day05_Input.txt")]
                      (intcode-processor/run-program-return-output program [5]) => [3892695]))))