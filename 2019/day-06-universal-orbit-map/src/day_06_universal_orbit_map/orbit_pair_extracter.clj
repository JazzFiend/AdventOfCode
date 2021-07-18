(ns day-06-universal-orbit-map.orbit-pair-extracter
  (:require [clojure.string :as str]))

(declare extract-orbit-pairs)

(defn extract-orbit-pairs [orbit-list]
  (let [lines (str/split-lines orbit-list)]
    (mapv #(str/split % #"\)") lines)))
