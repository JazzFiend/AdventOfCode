(ns day-03-crossed-wires.wire-analyzer-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            [day-03-crossed-wires.wire-analyzer :refer :all]))

(fact "A single wire with one path entry should be represented correctly."
      (let [wire ["U1"]]
        (compute-wire-points wire) => [[0 0] [0 1]]))

(fact "A simple single wire should be represented correctly."
      (let [wire ["U1" "R2" "D3" "L4"]]
        (compute-wire-points wire) => [[0 0] [0 1] [1 1] [2 1] [2 0] [2 -1] [2 -2] [1 -2] [0 -2] [-1 -2] [-2 -2]])
      (let [wire ["R8" "U5" "L5" "D3"]]
        (compute-wire-points wire) => [[0 0] [1 0] [2 0] [3 0] [4 0] [5 0] [6 0] [7 0] [8 0] [8 1] [8 2] [8 3]
                                       [8 4] [8 5] [7 5] [6 5] [5 5] [4 5] [3 5] [3 4] [3 3] [3 2]])
      (let [wire ["U7" "R6" "D4" "L4"]]
        (compute-wire-points wire) => [[0 0] [0 1] [0 2] [0 3] [0 4] [0 5] [0 6] [0 7] [1 7] [2 7] [3 7] [4 7]
                                       [5 7] [6 7] [6 6] [6 5] [6 4] [6 3] [5 3] [4 3] [3 3] [2 3]]))
