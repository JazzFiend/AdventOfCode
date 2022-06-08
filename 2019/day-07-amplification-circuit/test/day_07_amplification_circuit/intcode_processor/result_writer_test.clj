(ns day-07-amplification-circuit.intcode-processor.result-writer-test
  (:require [midje.sweet :refer [facts fact]]
            [day-07-amplification-circuit.intcode-processor.result-writer :as result-writer]))

(facts "write-to-program"
       (fact "An opcode that doesn't write to memory should not change the program when running write-to-program"
             (let [opcode 4
                   program [0 1 2 3]
                   program-counter 0
                   value 99]
               (result-writer/write-to-program opcode program program-counter value) => program))
       (fact "Memory should be overwritten using an opcode with two parameters"
             (let [opcode 1
                   program [1101 6 3 0]
                   program-counter 0
                   value 5]
               (result-writer/write-to-program opcode program program-counter value) => [5 6 3 0]))
       (fact "Memory should be overwritten when using the input opcode"
             (let [program [3 0 1 11]]
               (result-writer/write-to-program 3 program 0 45) => [45 0 1 11])))

(facts "write-to-output-buffer"
       (fact "Output buffer should be unchanged when an output opcode is not seen"
             (let [opcode 1
                   output-buffer [12 99 100]
                   value 88]
               (result-writer/write-to-output-buffer opcode output-buffer value) => output-buffer))

       (fact "Output buffer should add a new value when an output opcode is seen"
             (let [opcode 4
                   output-buffer [10 100]
                   value 1000]
               (result-writer/write-to-output-buffer opcode output-buffer value) => [10 100 1000])))