(ns suprepl.nrepl
  (:require
   [cider.nrepl]
   [clojure.java.io :as io]
   [clojure.pprint :refer [pprint]]
   [clojure.string :as string]
   [nrebl.middleware]
   [nrepl.cmdline :as nrepl]
   [nrepl.server :refer [default-handler start-server]]
   [nrepl.transport :as transport]
   [nrepl.version :as version]))


(def defaults
  {:middleware (concat [#'nrebl.middleware/wrap-nrebl]
                       (map resolve cider.nrepl/cider-middleware))})

(defn build-handler
  "Build an nREPL handler from `middleware`.
  `middleware` is a sequence of vars or string which can be resolved
  to vars, representing middleware you wish to mix in to the nREPL
  handler. Vars can resolve to a sequence of vars, in which case
  they'll be flattened into the list of middleware."
  [middleware]
  (apply default-handler middleware))

(defn get-host
  [server]
  (let [^java.net.ServerSocket socket (:server-socket server)]
    (-> socket
        (.getInetAddress)
        (.getHostName))))

(defn print-server-details
  [{:keys [port transport] :as server}]
  (let [host (get-host server)]
    (cognitect.rebl/inspect server)
    (println (format "nREPL server started on port %d on host %s - %s://%s:%d"
                     port
                     host
                     (transport/uri-scheme transport)
                     host
                     port))
    (println (format "nREPL %s" (:version-string version/version)))
    (println (str "Clojure " (clojure-version)))
    (println (System/getProperty "java.vm.name") (System/getProperty "java.runtime.version"))
    (println (str "Interrupt: Control+C"))
    (println (str "Exit:      Control+D or (exit) or (quit)"))))

(defn save-port-file!
  [{:keys [port]}]
  (let [port-file (io/file ".nrepl-port")]
    (.deleteOnExit port-file)
    (spit port-file port)))

(defn start-repl-server
  [opts]
  (let [{:keys [bind middleware port]} (merge defaults opts)]
    (doto (start-server
           :bind bind
           :handler (build-handler middleware)
           :port port
           :transport-fn #'transport/bencode)
          (print-server-details)
          (save-port-file!))))
