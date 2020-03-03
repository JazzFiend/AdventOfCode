(ns day-04-secure-container.password-checker-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            [day-04-secure-container.password-checker :refer :all]))

(fact "A repeated nunber will pass"
      (check-password "111111") => true)

(fact "A number with a decreasing pair of digits will fail"
      (check-password "223450") => false)

(fact "A number with no double will fail"
      (check-password "123789") => false)

(fact "A number with a double and only increases will pass"
      (check-password "122789") => true)
