(ns day-05-sunny-with-chance-asteroids.noun-verb-calculator-test
  (:require [clojure.test :refer :all]
            [midje.sweet :refer :all]
            [clojure.string :as str :refer [split]]
            [day-05-sunny-with-chance-asteroids.noun-verb-calculator :refer :all]))

(defn get-gravity-assist-program
  [file-name]
  (let [text-from-file (slurp file-name)
        instructions-as-strings (str/split text-from-file #",")]
    (vec (map read-string instructions-as-strings))))

(fact "A bad output value should result in a thrown exception"
      (let [gravity-assist-program (get-gravity-assist-program "./test/day_05_sunny_with_chance_asteroids/Day02_Input.txt")
            desired-zero-memory-value 3]
        (find-noun-verb-for-output gravity-assist-program desired-zero-memory-value) => (throws Exception)))

(facts "Puzzles"
       (fact "Part 2"
             (let [gravity-assist-program (get-gravity-assist-program "./test/day_05_sunny_with_chance_asteroids/Day02_Input.txt")
                   desired-zero-memory-value 19690720
                   noun-verb-combination (find-noun-verb-for-output gravity-assist-program desired-zero-memory-value)]
               noun-verb-combination => 9820)))