(ns day-06-universal-orbit-map.test-orbit-counter
  (:require [midje.sweet :refer [fact facts =>]]
            [day-06-universal-orbit-map.orbit-counter :as orbit-counter]))

(defn get-orbits-from-text-file [file-name]
  (slurp file-name))

(facts "Orbit Count Tests"
       (fact "Empty list of orbits"
             (orbit-counter/calculate-oribit-count "") => 0)
       (fact "One orbit"
             (orbit-counter/calculate-oribit-count "COM)A") => 1)
       (fact "Two planets orbiting COM has a total of 2"
             (orbit-counter/calculate-oribit-count "COM)A\nCOM)B") => 2)
       (fact "One indirect orbit should have a total of 3"
             (orbit-counter/calculate-oribit-count "COM)A\nA)B") => 3)
       (fact "A chain of three orbits should have a checksum of 3"
             (orbit-counter/calculate-oribit-count "COM)A\nA)B\nB)C") => 6)
       (fact "Acceptance Test"
             (let [orbits (get-orbits-from-text-file "./test/day_06_universal_orbit_map/acceptance_test.txt")]
               (orbit-counter/calculate-oribit-count orbits) => 42))
       (fact "Puzzle Part 1"
             (let [orbits (get-orbits-from-text-file "./test/day_06_universal_orbit_map/input.txt")]
               (orbit-counter/calculate-oribit-count orbits) => 151345)))
