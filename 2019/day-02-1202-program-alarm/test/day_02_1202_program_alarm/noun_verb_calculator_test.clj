(ns day-02-1202-program-alarm.noun-verb-calculator-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            [day-02-1202-program-alarm.noun-verb-calculator :refer :all]))

(defn get-gravity-assist-program
  [file-name]
  (let [text-from-file (slurp file-name)
        instructions-as-strings (clojure.string/split text-from-file #",")]
    (vec (map read-string instructions-as-strings))))

(fact "A bad output value should result in a thrown exception"
      (let [gravity-assist-program (get-gravity-assist-program "./test/day_02_1202_program_alarm/input.txt")
            desired-zero-memory-value 3]
        (find-noun-verb-for-output gravity-assist-program desired-zero-memory-value) => (throws Exception)))

(facts "Puzzles"
       (fact "Part 2"
             (let [gravity-assist-program (get-gravity-assist-program "./test/day_02_1202_program_alarm/input.txt")
                   desired-zero-memory-value 19690720
                   noun-verb-combination (find-noun-verb-for-output gravity-assist-program desired-zero-memory-value)]
               noun-verb-combination => 9820)))