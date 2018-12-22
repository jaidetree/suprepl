(ns suprepl.core
  (:require
   [clojure.pprint :refer [pprint]]
   [cognitect.rebl]
   [suprepl.rebel :refer [repl]]
   [suprepl.nrepl :refer [start-repl-server]]))

(defn -main
  [& _]
  (cognitect.rebl/ui)
  (start-repl-server {})
  (repl)
  (System/exit 0))

(comment
 (println msg))
