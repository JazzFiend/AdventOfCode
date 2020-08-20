(ns day-05-sunny-with-chance-asteroids.instruction-executor-test
  (:require [clojure.test :refer :all]
            [midje.sweet :refer :all]
            [day-05-sunny-with-chance-asteroids.instruction-executor :as instruction-executor]))

(facts "Each opcode should work correctly"
       (fact "Addition"
             (instruction-executor/addition 15 22) => 37)

       (fact "Multiplication"
             (instruction-executor/multiplication 9 11) => 99)

       (fact "Input"
             (instruction-executor/input-command [3 56 2]) => 3)

       (fact "Output"
             (instruction-executor/output-command 85) => 85)

       (fact "Jump If True - true case"
             (instruction-executor/jump-if-true 45 465) => 465)

       (fact "Jump If True - false case"
             (instruction-executor/jump-if-true 0 465) => "BRANCH_FALSE")

       (fact "Jump If False - true case"
             (instruction-executor/jump-if-false 45 8255) => "BRANCH_FALSE")

       (fact "Jump If False - false case"
             (instruction-executor/jump-if-false 0 27) => 27)

       (fact "Less Than - true case"
             (instruction-executor/less-than 10 45) => 1)

       (fact "Less Than - false case"
             (instruction-executor/less-than 45 45) => 0)

       (fact "Equals - true case"
             (instruction-executor/equals 34 34) => 1)

       (fact "Equals - false case"
             (instruction-executor/equals 34 35) => 0)

       (fact "Halt"
             (instruction-executor/halt) => nil))