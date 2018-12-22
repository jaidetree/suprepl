(ns suprepl.rebel
  (:require
    [cognitect.rebl]
    [rebel-readline.clojure.main :as rebel]))

(defn rebl-eval
  [input]
  (let [output (eval input)]
    (cognitect.rebl/submit input output)
    output))

(defn repl
  []
  (rebel/repl* {:eval rebl-eval}))
