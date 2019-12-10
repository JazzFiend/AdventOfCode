(ns day-02-1202-program-alarm.intcode-computer-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            [day-02-1202-program-alarm.intcode-computer :refer :all]))

(facts "Each opcode should work correctly"
       (fact "Addition should work correctly and return the modified program"
             (let [simple-addition-program [1 1 2 0]
                   program-counter 0]
               (run-next-instruction simple-addition-program program-counter) => [3 1 2 0])
             (let [simple-addition-program [1 0 0 0 99]
                   program-counter 0]
               (run-next-instruction simple-addition-program program-counter) => [2 0 0 0 99]))

       (fact "Multiplication should work correctly and return the modified program"
             (let [simple-multiplication-program [2 0 2 3]
                   program-counter 0]
               (run-next-instruction simple-multiplication-program program-counter) => [2 0 2 4])
             (let [simple-multiplication-program [2 3 0 3 99]
                   program-counter 0]
               (run-next-instruction simple-multiplication-program program-counter) => [2 3 0 6 99])
             (let [simple-multiplication-program [2 4 4 5 99 0]
                   program-counter 0]
               (run-next-instruction simple-multiplication-program program-counter) => [2 4 4 5 99 9801]))

       (fact "Halt should have no effect on memory"
             (let [simple-halt-program [99]
                   program-counter 0]
               (run-next-instruction simple-halt-program program-counter) => [99])))

(fact "An invalid opcode should throw an exception"
      (let [bad-program [19 0 2 3]
            program-counter 0]
        (run-next-instruction bad-program program-counter) => (throws Exception)))