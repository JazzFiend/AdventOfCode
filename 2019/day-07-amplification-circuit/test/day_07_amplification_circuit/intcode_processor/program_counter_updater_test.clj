(ns day-07-amplification-circuit.intcode-processor.program-counter-updater-test
  (:require [day-07-amplification-circuit.intcode-processor.instruction-decoder :refer [INPUT LESS-THAN OUTPUT]]
            [day-07-amplification-circuit.intcode-processor.program-counter-updater :as program-counter-updater]
            [midje.sweet :refer [fact facts throws]]))

(facts "Update PC via Branch"
       (fact "Branch True"
             (program-counter-updater/update-program-counter-by-branch-condition 20 7) => 20)
       (fact "Branch False"
             (program-counter-updater/update-program-counter-by-branch-condition "BRANCH_FALSE" 7) => 10))

(facts "Update PC via Argument Number"
       (fact "Two Parameter Opcode"
             (program-counter-updater/update-program-counter-by-argument-number LESS-THAN 10) => 14)
       (fact "One Parameter Opcode"
             (program-counter-updater/update-program-counter-by-argument-number OUTPUT 10) => 12)
       (fact "Input Opcode"
             (program-counter-updater/update-program-counter-by-argument-number INPUT 10) => 12)
       (fact "Invalid Opcode"
             (program-counter-updater/update-program-counter-by-argument-number 76 10) => (throws Exception)))
