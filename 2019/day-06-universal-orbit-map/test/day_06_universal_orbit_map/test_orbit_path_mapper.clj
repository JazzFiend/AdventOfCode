(ns day-06-universal-orbit-map.test-orbit-path-mapper
  (:require [midje.sweet :refer [fact facts =>]]
            [day-06-universal-orbit-map.orbit-transfer-counter :as orbit-transfer-counter]))

(defn get-orbits-from-text-file [file-name]
  (slurp file-name))

(facts "Map path between two orbits"
       (fact "Empty list of orbits"
             (orbit-transfer-counter/orbit-count-between-planets "" "S" "F") => 0)
       (fact "COM links to start and finish"
             (orbit-transfer-counter/orbit-count-between-planets "COM)S\nCOM)F" "S" "F") => 0)
       (fact "One transfer between start and finish"
             (orbit-transfer-counter/orbit-count-between-planets "COM)S\nS)A\nA)B\nB)F" "S" "F") => 1)
       (fact "Start and finish are on different branches"
             (orbit-transfer-counter/orbit-count-between-planets "COM)Z\nZ)S\nCOM)A\nA)B\nB)F" "S" "F") => 3)
       (fact "Acceptance Test"
             (let [orbits (get-orbits-from-text-file "./test/day_06_universal_orbit_map/acceptance_test_part_2.txt")]
               (orbit-transfer-counter/orbit-count-between-planets orbits "YOU" "SAN") => 4))
       (fact "Puzzle Part 2"
             (let [orbits (get-orbits-from-text-file "./test/day_06_universal_orbit_map/input.txt")]
               (orbit-transfer-counter/orbit-count-between-planets orbits "YOU" "SAN") => 4)))