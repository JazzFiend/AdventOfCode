(ns day-03-crossed-wires.circuit-analyzer-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            [day-03-crossed-wires.circuit-analyzer :refer :all]))
(defn get-wires-from-file
  [file-name]
  (let [text-from-file (slurp file-name)
        wires-as-strings (clojure.string/split text-from-file #"\n")]
    (vec (map clojure.string/split wires-as-strings [#"," #","]))))

(fact "The circuit analyzer will find the smallest intersection point between two wires."
      (let [wire-1 ["R8" "U5" "L5" "D3"]
            wire-2 ["U7" "R6" "D4" "L4"]]
        (find-manhattan-distance-to-closest-intersection wire-1 wire-2) => 6)
      (let [wire-1 ["R75" "D30" "R83" "U83" "L12" "D49" "R71" "U7" "L72"]
            wire-2 ["U62" "R66" "U55" "R34" "D71" "R55" "D58" "R83"]]
        (find-manhattan-distance-to-closest-intersection wire-1 wire-2) => 159)
      (let [wire-1 ["R98" "U47" "R26" "D63" "R33" "U87" "L62" "D20" "R33" "U53" "R51"]
            wire-2 ["U98" "R91" "D20" "R16" "D67" "R40" "U7" "R15" "U6" "R7"]]
        (find-manhattan-distance-to-closest-intersection wire-1 wire-2) => 135))

(fact "The circuit analyzer will return the signal delay given two intersecting wires."
      (let [wire-1 ["R8" "U5" "L5" "D3"]
            wire-2 ["U7" "R6" "D4" "L4"]]
        (calculate-minimum-signal-delay wire-1 wire-2) => 30)
      (let [wire-1 ["R75" "D30" "R83" "U83" "L12" "D49" "R71" "U7" "L72"]
            wire-2 ["U62" "R66" "U55" "R34" "D71" "R55" "D58" "R83"]]
        (calculate-minimum-signal-delay wire-1 wire-2) => 610)
      (let [wire-1 ["R98" "U47" "R26" "D63" "R33" "U87" "L62" "D20" "R33" "U53" "R51"]
            wire-2 ["U98" "R91" "D20" "R16" "D67" "R40" "U7" "R15" "U6" "R7"]]
        (calculate-minimum-signal-delay wire-1 wire-2) => 410))

(facts "Puzzles"
       (fact "Puzzle 1"
             (let [wires (get-wires-from-file "./test/day_03_crossed_wires/input.txt")
                   wire-1 (nth wires 0)
                   wire-2 (nth wires 1)]
               (find-manhattan-distance-to-closest-intersection wire-1 wire-2) => 245
               (calculate-minimum-signal-delay wire-1 wire-2) => 48262)))
