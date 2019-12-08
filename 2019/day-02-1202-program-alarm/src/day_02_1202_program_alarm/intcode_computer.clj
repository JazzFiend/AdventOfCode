(ns day-02-1202-program-alarm.intcode-computer)

(declare run-next-instruction)
(declare addition)
(declare multiplication)
(declare halt)

(defn run-next-instruction [program program-counter]
  (let [opcode (nth program program-counter)]
    (cond
      (= opcode 1) (addition program program-counter)
      (= opcode 2) (multiplication program program-counter)
      (= opcode 99) (halt program program-counter)
      :else (throw (Exception. "Invalid opcode encountered")))))

(defn addition [program program-counter]
  (let [augend (nth program (nth program (+ program-counter 1)))
        addend (nth program (nth program (+ program-counter 2)))
        destination (nth program (+ program-counter 3))
        sum (+ augend addend)]
    (assoc program destination sum)))

(defn multiplication [program program-counter]
  (let [multiplicand (nth program (nth program (+ program-counter 1)))
        multiplier (nth program (nth program (+ program-counter 2)))
        destination (nth program (+ program-counter 3))
        product (* multiplicand multiplier)]
    (assoc program destination product)))

(defn halt [program program-counter]
    ; Halt makes no changes to the program. Just return.
  program)