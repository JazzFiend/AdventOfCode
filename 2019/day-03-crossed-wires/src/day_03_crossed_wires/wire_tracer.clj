(ns day-03-crossed-wires.wire-tracer
  (:require [clojure.spec.alpha :as s]))

(declare trace-next-wire-length)
(declare add-points-up)
(declare add-points-down)
(declare add-points-left)
(declare add-points-right)
(declare get-next-point-left)
(declare get-next-point-right)
(declare get-next-point-up)
(declare get-next-point-down)

(defn trace-next-wire-length [current-trace next-length]
  (let [current-location (last current-trace)]
    (cond
      (= (first next-length) \U) (add-points-up current-trace (read-string (subs next-length 1)))
      (= (first next-length) \R) (add-points-right current-trace (read-string (subs next-length 1)))
      (= (first next-length) \D) (add-points-down current-trace (read-string (subs next-length 1)))
      (= (first next-length) \L) (add-points-left current-trace (read-string (subs next-length 1))))))

(defn add-points-up [original-trace distance]
  (let [loop-start (get-next-point-up original-trace)
        loop-end (+ loop-start distance)
        x-distance (nth (last original-trace) 0)]
    (loop [trace original-trace
           i loop-start]
      (if (= loop-end i)
        trace
        (let [new-trace (conj trace [x-distance i])]
          (recur new-trace (inc i)))))))

(defn add-points-down [original-trace distance]
  (let [loop-start (get-next-point-down original-trace)
        loop-end (- loop-start distance)
        x-distance (nth (last original-trace) 0)]
    (loop [trace original-trace
           i loop-start]
      (if (= loop-end i)
        trace
        (let [new-trace (conj trace [x-distance i])]
          (recur new-trace (dec i)))))))

(defn add-points-left [original-trace distance]
  (let [loop-start (get-next-point-left original-trace)
        loop-end (- loop-start distance)
        y-distance (nth (last original-trace) 1)]
    (loop [trace original-trace
           i loop-start]
      (if (= loop-end i)
        trace
        (let [new-trace (conj trace [i y-distance])]
          (recur new-trace (dec i)))))))

(defn add-points-right [original-trace distance]
  (let [loop-start (get-next-point-right original-trace)
        loop-end (+ loop-start distance)
        y-distance (nth (last original-trace) 1)]
    (loop [trace original-trace
           i loop-start]
      (if (= loop-end i)
        trace
        (let [new-trace (conj trace [i y-distance])]
          (recur new-trace (inc i)))))))

(defn get-next-point-left [trace]
  (- (nth (last trace) 0) 1))

(defn get-next-point-right [trace]
  (+ (nth (last trace) 0) 1))

(defn get-next-point-up [trace]
  (+ (nth (last trace) 1) 1))

(defn get-next-point-down [trace]
  (- (nth (last trace) 1) 1))