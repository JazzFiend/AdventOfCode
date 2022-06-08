(ns day-07-amplification-circuit.intcode-processor.parameter-extractor-test
  (:require [midje.sweet :refer [facts fact]]
            [day-07-amplification-circuit.intcode-processor.parameter-extractor :as parameter-extractor]))

(facts "Parameter Extractor Tests"
 (fact "First parameter can be extracted in positional mode"
       (let [program [1005 4 3 4 200]
             program-counter 0
             parameter-number 1]
         (parameter-extractor/extract-parameter program program-counter parameter-number) => 200))

 (fact "Second parameter can be extracted in positional mode"
       (let [program [1008 4 22 4 33]
             program-counter 0
             parameter-number 2]
         (parameter-extractor/extract-parameter program program-counter parameter-number) => 22)))

