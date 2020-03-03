(ns day-04-secure-container.password-rule-checker-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            [day-04-secure-container.password-rule-checker :refer :all]))

(fact "A number with six repeated digits will pass the Repeated Digits check"
      (contains-repeated-digits "111111") => true)

(fact "A number with no repeated digits will fail the Repeated Digits check"
      (contains-repeated-digits "987654") => false)

(fact "A number with two repeated digits at the end will pass the Repeated Digits check"
      (contains-repeated-digits "987655") => true)

(fact "A number with all increasing digits will pass the Increasing Digits check"
      (are-all-digits-increasing "123456") => true)

(fact "A number with one pair of decreasing digits will fail the Increasing Digits check"
      (are-all-digits-increasing "135686") => false)

(fact "A number with with repeated digits and increasing digits will pass the Increasing Digits check"
      (are-all-digits-increasing "124457") => true)