(ns day-06-universal-orbit-map.orbit-counter
  (:require [clojure.string :as str]
            [day-06-universal-orbit-map.orbit-pair-extracter :as orbit-pair-extractor]))

(declare calculate-oribit-count)
(declare find-path-between-planets)
(declare count-orbits-recursive) ; private

(defn calculate-orbits-for-planet [orbit-pairs planet distance-from-center]
  (let [all-orbiting-planets (map #(first %) orbit-pairs)
        planets-orbiting-this (filter #(= % planet) all-orbiting-planets)]
    (* (count planets-orbiting-this) distance-from-center)))

(defn calculate-subsequent-orbits [orbit-pairs planet distance-from-center]
  (let [each-orbiter (map #(nth % 1) (filter #(= (first %) planet) orbit-pairs))
        subsequent-orbit-list (map #(count-orbits-recursive orbit-pairs % (+ distance-from-center 1)) each-orbiter)]
    (reduce + subsequent-orbit-list)))

(defn count-orbits-recursive [orbit-pairs current-planet distance-from-center]
  (let [total-orbits (calculate-orbits-for-planet orbit-pairs current-planet distance-from-center)
        total-subsequent-orbits (calculate-subsequent-orbits orbit-pairs current-planet distance-from-center)]
    (+ total-orbits total-subsequent-orbits)))

(defn count-orbits [orbit-list]
  (let [orbit-pairs (orbit-pair-extractor/extract-orbit-pairs orbit-list)]
    (count-orbits-recursive orbit-pairs "COM" 1)))

(defn calculate-oribit-count [orbit-list]
  (if (str/blank? orbit-list)
    0
    (count-orbits orbit-list)))
