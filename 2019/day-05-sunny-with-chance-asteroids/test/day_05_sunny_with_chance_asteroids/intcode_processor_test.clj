(ns day-05-sunny-with-chance-asteroids.intcode-processor-test
  (:require [midje.sweet :refer :all]
            [clojure.test :refer :all]
            [clojure.string :as str :refer [split]]
            [day-05-sunny-with-chance-asteroids.intcode-processor :refer :all]
            [day-05-sunny-with-chance-asteroids.noun-verb-calculator :refer :all]))

(defn get-program-from-text-file
  [file-name]
  (let [text-from-file (slurp file-name)
        instructions-as-strings (str/split text-from-file #",")]
    (vec (map read-string instructions-as-strings))))

(fact "Tests to remove"
      (let [program [1005 2 4 55555 1105 0 4 99]]
        (run-program-return-program program []) => [1005 2 4 55555 1105 0 4 99]))

(fact "Program Examples - Day 2"
      (let [program [1 0 0 0 99]]
        (run-program-return-program program []) => [2 0 0 0 99])
      (let [program [2 3 0 3 99]]
        (run-program-return-program program []) => [2 3 0 6 99])
      (let [program [2 4 4 5 99 0]]
        (run-program-return-program program []) => [2 4 4 5 99 9801])
      (let [program [1 1 1 4 99 5 6 0 99]]
        (run-program-return-program program []) => [30 1 1 4 2 5 6 0 99])
      (let [program [1 9 10 3 2 3 11 0 99 30 40 50]]
        (run-program-return-program program []) => [3500 9 10 70 2 3 11 0 99 30 40 50]))

(fact "Program Examples - Day 5"
      (let [program [3 0 4 0 99]]
        (run-program-return-output program [100]) => [100])

      (let [program [1002 4 3 4 33]]
        (run-program-return-program program []) => [1002 4 3 4 99])

      (let [program [3 9 8 9 10 9 4 9 99 -1 8]]
        (run-program-return-output program [8]) => [1])

      (let [program [3 3 1107 -1 8 3 4 3 99]]
        (run-program-return-output program [8]) => [0])

      (let [program [3 12 6 12 15 1 13 14 13 4 13 99 -1 0 1 9]]
        (run-program-return-output program [22]) => [1]))

(facts "Puzzles"
       (fact "Day 02 - Part 1"
             (let [program (get-program-from-text-file "./test/day_05_sunny_with_chance_asteroids/Day02_Input_Modified.txt")
                   output-program (run-program-return-program program [])]
               (nth output-program 0) => 2782414))
       (fact "Day 05 - Part 1"
             (let [program (get-program-from-text-file "./test/day_05_sunny_with_chance_asteroids/Day05_Input.txt")]
               (run-program-return-output program [1]) => [0 0 0 0 0 0 0 0 0 13787043]))

       (fact "Day 05 - Part 2"
             (let [program (get-program-from-text-file "./test/day_05_sunny_with_chance_asteroids/Day05_Input.txt")]
               (run-program-return-output program [5]) => [3892695])))