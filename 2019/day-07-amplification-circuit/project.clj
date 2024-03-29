(defproject day-07-amplification-circuit "0.1.0-SNAPSHOT"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.clojure/math.numeric-tower "0.0.5"]]
  :target-path "target/%s"
  :profiles {:dev {:dependencies [[midje "1.10.5"]
                                  [org.clojure/math.combinatorics "0.1.6"]]}}
  :repl-options {:init-ns day-07-amplification-circuit.core}
  :plugins [[lein-cloverage "1.2.4"]])