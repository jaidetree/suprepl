(ns suprepl.core
  (:require
   [suprepl.nrepl :refer [start-repl-server]]
   [rebel-readline.main :as rebel]
   [cognitect.rebl]
   [clojure.pprint :refer [pprint]]))

(defn -main
  [& _]
  (cognitect.rebl/ui)
  (start-repl-server {})
  (rebel/-main)
  (System/exit 0))
