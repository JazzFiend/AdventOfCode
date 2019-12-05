(ns day-01-tyranny-rocket-equation.fuel-computer)

(declare compute-fuel)
(declare compute-fuel-recursive)

(defn compute-total-fuel-simple
  [mass-vector]
  (reduce + (map compute-fuel mass-vector)))

(defn compute-total-fuel-complex
  [mass-vector]
  (reduce + (map compute-fuel-recursive mass-vector)))

(defn compute-fuel
  [mass]
  (- (int (Math/floor (/ mass 3))) 2))

(defn compute-fuel-recursive
  [mass]
  (let [fuel-result (- (int (Math/floor (/ mass 3))) 2)]
    (if (> fuel-result 0)
      (+ fuel-result (compute-fuel-recursive fuel-result))
      0)))