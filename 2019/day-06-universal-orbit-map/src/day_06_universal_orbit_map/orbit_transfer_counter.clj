(ns day-06-universal-orbit-map.orbit-transfer-counter
  (:require [clojure.string :as str]
            [clojure.set :as set]
            [day-06-universal-orbit-map.orbit-pair-extracter :as orbit-pair-extractor]
            [day-06-universal-orbit-map.orbit-path-mapper :as orbit-path-mapper]))

(declare orbit-count-between-planets)
(declare find-path-to-planet); Need this?

(defn larger [x y]
  (if (> y x)
    y
    x))

(defn calculate-orbits-between-planets [root-to-start-count root-to-end-count common-root-location]
  (+ (- (- root-to-start-count common-root-location) 1) (- (- root-to-end-count common-root-location) 3)))

(defn condense-path [root-to-start root-to-end]
  (let [common-planets (vec (set/intersection (set root-to-start) (set root-to-end)))
        common-planets-indexes (map #(.indexOf root-to-start %) common-planets)
        common-root-location (reduce larger 0 common-planets-indexes)]
    (calculate-orbits-between-planets (count root-to-start) (count root-to-end) common-root-location)))

(defn count-orbits-valid-input [orbit-list start end]
  (let [orbit-pairs (orbit-pair-extractor/extract-orbit-pairs orbit-list)
        path-to-start (orbit-path-mapper/find-path-to-planet orbit-pairs "COM" [] start)
        path-to-end (orbit-path-mapper/find-path-to-planet orbit-pairs "COM" [] end)
        orbit-count (condense-path path-to-start path-to-end)]
    orbit-count))

(defn orbit-count-between-planets [orbit-list start end]
  (if (str/blank? orbit-list)
    0
    (count-orbits-valid-input orbit-list start end)))
