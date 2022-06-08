(ns day-07-amplification-circuit.intcode-processor.instruction-executor 
  (:require [day-07-amplification-circuit.intcode-processor.instruction-decoder :refer [ADD EQUAL HALT INPUT JUMP-IF-FALSE JUMP-IF-TRUE LESS-THAN
                                                                                        MULT OUTPUT]]
            [day-07-amplification-circuit.intcode-processor.opcode-executor :as opcode-executor]))

(defn run-two-parameter-opcode [opcode parameter-1 parameter-2]
  (cond
    (= opcode ADD)
    (opcode-executor/addition parameter-1 parameter-2)

    (= opcode MULT)
    (opcode-executor/multiplication parameter-1 parameter-2)

    (= opcode JUMP-IF-TRUE)
    (opcode-executor/jump-if-true parameter-1 parameter-2)

    (= opcode JUMP-IF-FALSE)
    (opcode-executor/jump-if-false parameter-1 parameter-2)

    (= opcode LESS-THAN)
    (opcode-executor/less-than parameter-1 parameter-2)

    (= opcode EQUAL)
    (opcode-executor/equals parameter-1 parameter-2)

    :else (throw (Exception. (str "Invalid opcode encountered: " opcode)))))

(defn run-one-parameter-opcode [opcode parameter]
  (if (= opcode OUTPUT)
    (opcode-executor/output-command parameter)
    (throw (Exception. (str "Invalid opcode encountered: " opcode)))))

(defn run-input-parameter-opcode [opcode input]
  (if (= opcode INPUT)
    (opcode-executor/input-command input)
    (throw (Exception. (str "Invalid opcode encountered: " opcode)))))

(defn run-zero-parameter-opcode [opcode]
  (if (= opcode HALT)
    (opcode-executor/halt)
    (throw (Exception. (str "Invalid opcode encountered: " opcode)))))
