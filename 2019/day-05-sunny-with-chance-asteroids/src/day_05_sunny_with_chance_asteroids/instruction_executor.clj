(ns day-05-sunny-with-chance-asteroids.instruction-executor)

(defn addition [augend addend]
  (+ augend addend))

(defn multiplication [multiplicand multiplier]
  (* multiplicand multiplier))

(defn input-command [input]
  (nth input 0))

; Writes are done elsewhere, so this is just a passthrough
(defn output-command [value]
  value)

(defn jump-if-true [value new-instruction-pointer]
  (if (= value 0)
    "BRANCH_FALSE"
    new-instruction-pointer))

(defn jump-if-false [value new-instruction-pointer]
  (if (= value 0)
    new-instruction-pointer
    "BRANCH_FALSE"))

(defn less-than [a b]
  (if (< a b)
    1
    0))

(defn equals [a b]
  (if (= a b)
    1
    0))

(defn halt []
  ; Halt makes no changes. Just return.
  )
