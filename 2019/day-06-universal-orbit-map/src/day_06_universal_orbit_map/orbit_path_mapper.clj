(ns day-06-universal-orbit-map.orbit-path-mapper)

(declare find-path-to-planet)

(defn check-next-orbits [orbit-pairs updated-path destination planets-orbiting-this]
  (let [all-paths (map #(find-path-to-planet orbit-pairs % updated-path destination) planets-orbiting-this)]
    (first (filter #(seq %) all-paths))))

(defn get-planets-orbiting-this-one [current-planet orbit-pairs]
  (let [current-planet-pairs (filter #(= (first %) current-planet) orbit-pairs)]
    (map #(nth % 1) current-planet-pairs)))

(defn find-path-to-planet [orbit-pairs current-planet current-path destination]
  (let [updated-path (conj current-path current-planet)
        planets-orbiting-this (get-planets-orbiting-this-one current-planet orbit-pairs)]
    (if (= current-planet destination)
      updated-path
      (if (empty? planets-orbiting-this)
        []
        (check-next-orbits orbit-pairs updated-path destination planets-orbiting-this)))))