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

(fact "A number with with only two repeated digits will pass the Only Two Repeats check"
      (contains-two-repeated "124457") => true)

(fact "A number with with three repeated digits will fail the Only Two Repeats check"
      (contains-two-repeated "144457") => false)

(fact "A number with with only two repeated digits at the start will pass the Only Two Repeats check"
      (contains-two-repeated "449257") => true)

(fact "A number with with only two repeated digits at the end will pass the Only Two Repeats check"
      (contains-two-repeated "429277") => true)

(fact "A number with with three repeated digits at the start will fail the Only Two Repeats check"
      (contains-two-repeated "444257") => false)

(fact "A number with with three repeated digits at the end will fail the Only Two Repeats check"
      (contains-two-repeated "429777") => false)

(fact "A number with with four repeated digits and two repeated digits will pass the Only Two Repeats check"
      (contains-two-repeated "111122") => true)