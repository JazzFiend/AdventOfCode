(ns day-04-secure-container.password-checker-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            [day-04-secure-container.password-checker :refer :all]))

(fact "A repeated nunber will pass"
      (check-password-v1 "111111") => true)

(fact "A number with a decreasing pair of digits will fail"
      (check-password-v1 "223450") => false)

(fact "A number with no double will fail"
      (check-password-v1 "123789") => false)

(fact "A number with a double and only increases will pass"
      (check-password-v1 "122789") => true)

(fact "A number with multiple doubles and only increases will pass"
      (check-password-v2 "112233") => true)

(fact "A number with four in a row and only increases will fail"
      (check-password-v2 "123444") => false)

(fact "A number with a double and only increases will pass"
      (check-password-v2 "111122") => true)
