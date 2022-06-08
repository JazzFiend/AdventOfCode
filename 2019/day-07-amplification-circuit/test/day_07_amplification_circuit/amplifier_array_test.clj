(ns day-07-amplification-circuit.amplifier-array-test
  (:require [clojure.string :as str]
            [day-07-amplification-circuit.amplifier-array :as amplifier-array]
            [midje.sweet :refer [fact facts]]))

(defn get-program-from-text-file
  [file-name]
  (let [text-from-file (slurp file-name)
        instructions-as-strings (str/split text-from-file #",")]
    (vec (map read-string instructions-as-strings))))

(facts "Acceptance Tests"
       (fact "Acceptance Test 1"
             (let [program [3 15 3 16 1002 16 10 16 1 16 15 15 4 15 99 0 0]]
               (amplifier-array/calculate-largest-output program) => 43210))

       (fact "Acceptance Test 2"
             (let [program [3 23 3 24 1002 24 10 24 1002 23 -1 23 101 5 23
                            23 1 24 23 23 4 23 99 0 0]]
               (amplifier-array/calculate-largest-output program) => 54321))

       (fact "Acceptance Test 3"
             (let [program [3 31 3 32 1002 32 10 32 1001 31 -2 31 1007 31 0
                            33 1002 33 7 33 1 33 31 31 1 32 31 31 4 31 99 0
                            0 0]]
               (amplifier-array/calculate-largest-output program) => 65210))

       (fact "Puzzle 1"
             (let [program (get-program-from-text-file "./test/day_07_amplification_circuit/intcode_processor/input_files/Day07_Input.txt")]
               (amplifier-array/calculate-largest-output program) => 99376)))

(facts "Feedback Loop"
       (fact "Acceptance Test 1"
             (let [program [3 26 1001 26 -4 26 3 27 1002 27 2 27 1 27 26 27 4 27
                            1001 28 -1 28 1005 28 6 99 0 0 5]]
               (amplifier-array/calculate-largest-output-with-feedback program) => 139629729))

       (fact "Acceptance Test 2"
             (let [program [3 52 1001 52 -5 52 3 53 1 52 56 54 1007 54 5 55 1005 55 26 1001 54
                            -5 54 1105 1 12 1 53 54 53 1008 54 0 55 1001 55 1 55 2 53 55 53 4
                            53 1001 56 -1 56 1005 56 6 99 0 0 0 0 10]]
               (amplifier-array/calculate-largest-output-with-feedback program) => 18216))

       (fact "Puzzle 2"
             (let [program (get-program-from-text-file "./test/day_07_amplification_circuit/intcode_processor/input_files/Day07_Input.txt")]
               (amplifier-array/calculate-largest-output-with-feedback program) => 99376)))
