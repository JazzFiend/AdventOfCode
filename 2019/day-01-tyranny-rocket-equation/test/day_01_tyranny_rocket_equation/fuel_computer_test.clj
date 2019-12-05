(ns day-01-tyranny-rocket-equation.fuel-computer-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            [day-01-tyranny-rocket-equation.fuel-computer :refer :all]))

(defn get-mass-from-file
  [file-name]
  (let [text-from-file (slurp file-name)
        mass-values-as-strings (clojure.string/split-lines text-from-file)]
    (map read-string mass-values-as-strings)))

(fact "The simple calculation method determines the correct amount of fuel for a given set of masses"
      (let [test-mass-values [12 14 1969 100756]
            test-total-fuel  34241]
        (compute-total-fuel-simple test-mass-values) => test-total-fuel))

(fact "The complex calculation method determines the correct amount of fuel for a given set of masses"
      (let [test-mass-values [14 1969 100756]
            test-total-fuel  51314]
        (compute-total-fuel-complex test-mass-values) => test-total-fuel))

(facts "Puzzles"
       (fact "Part 1"
             (let [mass-values (get-mass-from-file "./test/day_01_tyranny_rocket_equation/input.txt")]
               (compute-total-fuel-simple mass-values) => 3515171))
       (fact "Part 2"
             (let [mass-values (get-mass-from-file "./test/day_01_tyranny_rocket_equation/input.txt")]
               (compute-total-fuel-complex mass-values) => 5269882)))
