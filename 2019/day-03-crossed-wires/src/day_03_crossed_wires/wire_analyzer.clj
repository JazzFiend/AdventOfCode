(ns day-03-crossed-wires.wire-analyzer
  (:require [clojure.spec.alpha :as s]
            [day-03-crossed-wires.wire-tracer :as wire-tracer]))

(declare compute-wire-points)

(defn compute-wire-points [direction-list]
  (loop [wire-points [[0 0]]
         i 0]
    (if (>= i (count direction-list))
      wire-points
      (let [updated-wire-points (wire-tracer/trace-next-wire-length wire-points (nth direction-list i))]
        (recur updated-wire-points (inc i))))))

