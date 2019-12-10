(ns day-02-1202-program-alarm.intcode-processor-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            [day-02-1202-program-alarm.intcode-processor :refer :all]
            [day-02-1202-program-alarm.noun-verb-calculator :refer :all]))

(defn get-gravity-assist-program
  [file-name]
  (let [text-from-file (slurp file-name)
        instructions-as-strings (clojure.string/split text-from-file #",")]
    (vec (map read-string instructions-as-strings))))

(fact "Simple programs should output the correct memory vector."
      (let [program [1 0 0 0 99]]
        (run-program program) => [2 0 0 0 99])
      (let [program [2 3 0 3 99]]
        (run-program program) => [2 3 0 6 99])
      (let [program [2 4 4 5 99 0]]
        (run-program program) => [2 4 4 5 99 9801])
      (let [program [1 1 1 4 99 5 6 0 99]]
        (run-program program) => [30 1 1 4 2 5 6 0 99])
      (let [program [1 9 10 3 2 3 11 0 99 30 40 50]]
        (run-program program) => [3500 9 10 70 2 3 11 0 99 30 40 50]))

(facts "Puzzles"
       (fact "Part 1"
             (let [gravity-assist-program (get-gravity-assist-program "./test/day_02_1202_program_alarm/modified_input.txt")
                   output-program (run-program gravity-assist-program)]
               (nth output-program 0) => 2782414)
))