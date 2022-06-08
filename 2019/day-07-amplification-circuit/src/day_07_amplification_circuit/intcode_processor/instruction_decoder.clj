(ns day-07-amplification-circuit.intcode-processor.instruction-decoder)

; Adds arg1 to arg2 and stores the result in arg3
(def ADD 1)
; Multiplies arg1 and arg2 and stores the result in arg3
(def MULT 2)
; Take the next input value and store it in arg1
(def INPUT 3)
; Take arg1 and add it to the output buffer
(def OUTPUT 4)
; If arg1 is not zero, set the instruction pointer to arg2
; Do nothing otherwise
(def JUMP-IF-TRUE 5)
; If arg1 is zero, set the instruction pointer to arg2
; Do nothing otherwise
(def JUMP-IF-FALSE 6)
; If arg1 is less than arg2, store 1 in arg3
; Otherwise, store 0 in arg3
(def LESS-THAN 7)
; If arg1 is equal to arg2, store 1 in arg3
; Otherwise, store 0 in arg3
(def EQUAL 8)
; The program has completed and execution should halt
(def HALT 99)

(defn decode-opcode [program program-counter]
  (mod (nth program program-counter) 100))

(defn two-parameter-opcode? [opcode]
  (or
   (= opcode ADD)
   (= opcode MULT)
   (= opcode JUMP-IF-TRUE)
   (= opcode JUMP-IF-FALSE)
   (= opcode LESS-THAN)
   (= opcode EQUAL)))

(defn one-parameter-opcode? [opcode]
  (= opcode OUTPUT))

(defn input-parameter-opcode? [opcode]
  (= opcode INPUT))

(defn zero-parameter-opcode? [opcode]
  (= opcode HALT))

(defn perform-memory-write? [opcode]
  (or
   (= opcode ADD)
   (= opcode MULT)
   (= opcode INPUT)
   (= opcode LESS-THAN)
   (= opcode EQUAL)))

(defn perform-output-write? [opcode]
  (= opcode OUTPUT))

(defn halt? [opcode]
  (= opcode HALT))

(defn branch? [opcode]
  (or
   (= opcode JUMP-IF-TRUE)
   (= opcode JUMP-IF-FALSE)))
