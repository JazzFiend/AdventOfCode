(ns day-04-secure-container.password-checker
  (:require [clojure.spec.alpha :as s]
            [day-04-secure-container.password-rule-checker :as rule-checker]))

(declare check-password)

(defn check-password [password]
  (and (rule-checker/contains-repeated-digits password) (rule-checker/are-all-digits-increasing password)))