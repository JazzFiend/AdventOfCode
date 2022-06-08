(ns day-07-amplification-circuit.intcode-processor.intcode-computer-test
  (:require [midje.sweet :refer [facts fact throws]]
            [day-07-amplification-circuit.intcode-processor.intcode-computer :as intcode-computer]))

(facts "Simple instructions should run"
       (fact "Addition instruction should add numbers using immediate parameters"
             (let [simple-addition-program [1101 5 10 0]
                   program-counter 0]
               (intcode-computer/run-next-instruction simple-addition-program program-counter []) => 15))

       (fact "Multiplication should multiply numbers using positional parameters"
             (let [simple-multiplication-program [2 4 4 5 99 0]
                   program-counter 0]
               (intcode-computer/run-next-instruction simple-multiplication-program program-counter []) => 9801))

       (fact "Input command should pull off the input queue"
             (let [simple-input-program [3 0]
                   program-counter 0
                   input [25]]
               (intcode-computer/run-next-instruction simple-input-program program-counter input) => 25))

       (fact "Output command should act as a passthrough"
             (let [simple-output-program [4 2 658]
                   program-counter 0]
               (intcode-computer/run-next-instruction simple-output-program program-counter []) => 658))

       (fact "JIT should return the new value of the Program Counter"
             (let [simple-jit-program [1005 2 98]
                   program-counter 0]
               (intcode-computer/run-next-instruction simple-jit-program program-counter []) => 98))

       (fact "JIF should return the new value of the Program Counter"
             (let [simple-jif-program [1106 0 98]
                   program-counter 0]
               (intcode-computer/run-next-instruction simple-jif-program program-counter []) => 98))

       (fact "Less Than should return 1 when first parameter is smaller"
             (let [simple-lt-program [1107 3 4 3]
                   program-counter 0]
               (intcode-computer/run-next-instruction simple-lt-program program-counter []) => 1))

       (fact "Equals should return 0 when parameters are not equal"
             (let [simple-eq-program [1108 11 111 3]
                   program-counter 0]
               (intcode-computer/run-next-instruction simple-eq-program program-counter []) => 0))

       (fact "Halt should return nothing"
             (let [simple-halt-program [99]
                   program-counter 0]
               (intcode-computer/run-next-instruction simple-halt-program program-counter []) => nil)))

(fact "An invalid opcode should throw an exception"
      (let [bad-program [19 0 2 3]
            program-counter 0]
        (intcode-computer/run-next-instruction bad-program program-counter []) => (throws Exception)))

(facts "update-input"
       (fact "Input opcode should reduce the inputs by one"
             (let [input-opcode 3
                   input [0 1 2 3]]
               (intcode-computer/update-input input-opcode input) => [1 2 3]))

       (fact "Non-input opcode should leave the inputs alone"
             (let [non-input-opcode 1
                   input [0 1 2 3]]
               (intcode-computer/update-input non-input-opcode input) => input))
       
       (fact "Should not try to remove an input if there are no inputs to remove"
             (let [input-opcode 3
                   input []]
               (intcode-computer/update-input input-opcode input) => input)))