(ns day-04-secure-container.password-checker
  (:require [clojure.spec.alpha :as s]
            [day-04-secure-container.password-rule-checker :as rule-checker]))

(declare check-password-v1)
(declare check-password-v2)

(defn check-password-v1 [password]
  (and (rule-checker/contains-repeated-digits password) (rule-checker/are-all-digits-increasing password)))

(defn check-password-v2 [password]
  (and (rule-checker/contains-two-repeated password) (rule-checker/are-all-digits-increasing password)))