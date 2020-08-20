(ns day-05-sunny-with-chance-asteroids.output-writer)

(defn write-to-memory [program value location]
  (assoc program location value))

(defn write-to-output-buffer [output value]
  (conj output value))