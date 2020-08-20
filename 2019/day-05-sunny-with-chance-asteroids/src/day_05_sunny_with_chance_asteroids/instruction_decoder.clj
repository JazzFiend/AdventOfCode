(ns day-05-sunny-with-chance-asteroids.instruction-decoder
  (:require [clojure.spec.alpha :as s]
            [day-05-sunny-with-chance-asteroids.instruction-executor :as instruction-executor]))

(defn decode-opcode [program program-counter]
  (mod (nth program program-counter) 100))

(defn is-two-parameter-opcode [opcode]
  (or
   (= opcode 1)
   (= opcode 2)
   (= opcode 5)
   (= opcode 6)
   (= opcode 7)
   (= opcode 8)))

(defn is-one-parameter-opcode [opcode]
  (= opcode 4))

(defn is-input-parameter-opcode [opcode]
  (= opcode 3))

(defn is-zero-parameter-opcode [opcode]
  (= opcode 99))

(defn run-two-parameter-opcode [opcode parameter-1 parameter-2]
  (cond
    (= opcode 1)
    (instruction-executor/addition parameter-1 parameter-2)

    (= opcode 2)
    (instruction-executor/multiplication parameter-1 parameter-2)

    (= opcode 5)
    (instruction-executor/jump-if-true parameter-1 parameter-2)

    (= opcode 6)
    (instruction-executor/jump-if-false parameter-1 parameter-2)

    (= opcode 7)
    (instruction-executor/less-than parameter-1 parameter-2)

    (= opcode 8)
    (instruction-executor/equals parameter-1 parameter-2)

    :else (throw (Exception. (str "Invalid opcode encountered: " opcode)))))

(defn run-one-parameter-opcode [opcode parameter]
  (if (= opcode 4)
    (instruction-executor/output-command parameter)
    (throw (Exception. (str "Invalid opcode encountered: " opcode)))))

(defn run-input-parameter-opcode [opcode input]
  (if (= opcode 3)
    (instruction-executor/input-command input)
    (throw (Exception. (str "Invalid opcode encountered: " opcode)))))

(defn run-zero-parameter-opcode [opcode]
  (if (= opcode 99)
    (instruction-executor/halt)
    (throw (Exception. (str "Invalid opcode encountered: " opcode)))))

(defn perform-memory-write [opcode]
  (or
   (= opcode 1)
   (= opcode 2)
   (= opcode 3)
   (= opcode 7)
   (= opcode 8)))

(defn perform-output-write [opcode]
  (= opcode 4))

(defn is-halt [opcode]
  (= opcode 99))

(defn is-branch [opcode]
  (or
   (= opcode 5)
   (= opcode 6)))

(defn update-program-counter-by-branch-condition [value program-counter]
  (if (= value "BRANCH_FALSE")
    (+ program-counter 3)
    value))

(defn update-program-counter-by-argument-number [opcode program-counter]
  (if (or (= opcode 1) (= opcode 2) (= opcode 5) (= opcode 6) (= opcode 7) (= opcode 8))
    (+ program-counter 4)
    (if (or (= opcode 3) (= opcode 4))
      (+ program-counter 2)
      (throw (Exception. (str "Invalid opcode encountered: " opcode))))))