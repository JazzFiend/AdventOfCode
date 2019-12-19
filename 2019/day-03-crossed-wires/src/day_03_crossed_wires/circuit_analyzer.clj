(ns day-03-crossed-wires.circuit-analyzer
  (:require [clojure.spec.alpha :as s]
            [day-03-crossed-wires.wire-analyzer :as wire-analyzer]))

(declare find-manhattan-distance-to-closest-intersection)
(declare calculate-manhattan-distance)
(declare calculate-minimum-signal-delay)
(declare get-intersections)
(declare calculate-signal-delay)
(declare find-first-index-in-vector)

(defn find-manhattan-distance-to-closest-intersection [wire-script-1 wire-script-2]
  (let [wire-1 (wire-analyzer/compute-wire-points wire-script-1)
        wire-2 (wire-analyzer/compute-wire-points wire-script-2)
        common-points (get-intersections wire-1 wire-2)]
    (apply min (map calculate-manhattan-distance common-points))))

(defn calculate-manhattan-distance [point]
  (+ (Math/abs (nth point 0)) (Math/abs (nth point 1))))

(defn calculate-minimum-signal-delay [wire-script-1 wire-script-2]
  (let [wire-1 (wire-analyzer/compute-wire-points wire-script-1)
        wire-2 (wire-analyzer/compute-wire-points wire-script-2)
        common-points (vec (get-intersections wire-1 wire-2))]
    (loop [i 0
           singal-delays []]
      (if (>= i (count common-points))
        (apply min singal-delays)
        (recur (inc i)
               (conj singal-delays (calculate-signal-delay wire-1 wire-2 (nth common-points i))))))))

(defn get-intersections [wire-1 wire-2]
  (clojure.set/intersection (disj (set wire-1) [0 0]) (disj (set wire-2) [0 0])))

(defn calculate-signal-delay [wire-1 wire-2 intersection-point]
  (+ (find-first-index-in-vector wire-1 intersection-point) (find-first-index-in-vector wire-2 intersection-point)))

(defn find-first-index-in-vector [vector element]
  (loop [i 0]
    (if (>= i (count vector))
      -1 ;Should throw instead
      (if (= (nth vector i) element)
        i
        (recur (inc i))))))