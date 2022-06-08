(ns day-07-amplification-circuit.intcode-processor.opcode-executor-test
  (:require [midje.sweet :refer [facts fact]]
            [day-07-amplification-circuit.intcode-processor.opcode-executor :as opcode-executor]))

(facts "Each opcode should work correctly"
       (fact "Addition"
             (opcode-executor/addition 15 22) => 37)

       (fact "Multiplication"
             (opcode-executor/multiplication 9 11) => 99)

       (facts "Input"
              (fact "Normal Input"
                    (opcode-executor/input-command [3 56 2]) => 3)
              (fact "Ran out of inputs"
                    (opcode-executor/input-command []) => "INPUT_ARRAY_EMPTY"))

       (fact "Output"
             (opcode-executor/output-command 85) => 85)

       (facts "Jump If True"
              (fact "True case"
                    (opcode-executor/jump-if-true 45 465) => 465)
              (fact "False case"
                    (opcode-executor/jump-if-true 0 465) => "BRANCH_FALSE"))

       (facts "Jump If False"
              (fact "True case"
                    (opcode-executor/jump-if-false 45 8255) => "BRANCH_FALSE")
              (fact "False case"
                    (opcode-executor/jump-if-false 0 27) => 27))

       (facts "Less Than"
              (fact "True case"
                    (opcode-executor/less-than 10 45) => 1)
              (fact "False case"
                    (opcode-executor/less-than 45 45) => 0))

       (facts "Equals"
              (fact "True case"
                    (opcode-executor/equals 34 34) => 1)
              (fact "False case"
                    (opcode-executor/equals 34 35) => 0))

       (fact "Halt"
             (opcode-executor/halt) => nil))