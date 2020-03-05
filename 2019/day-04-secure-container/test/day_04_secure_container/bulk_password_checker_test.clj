(ns day-04-secure-container.bulk-password-checker-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            [day-04-secure-container.bulk-password-checker :refer :all]))

(facts "Puzzles"
       (fact "Part 1"
             (count-valid-passwords-v1 171309 643603) => 1625)
       (fact "Part 2"
             (count-valid-passwords-v2 171309 643603) => 1111))

