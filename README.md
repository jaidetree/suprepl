# Super REPL

An opinionated Clojure library to run a nREPL CLI REPL with Rebel-Readline, common cider middleware, proto-repl support, and Cognitect's REBL ui along with nREPL middleware to automatically send forms to REBL.

## Why

It took me months of work to learn how to setup a rich REPL experience from both the command line and editors such as cider and proto-repl. It made sense in the event of onboarding new collaborators to have a preset-package one can include and use to get started with a rich REPL experience fast.

This project is also an example how to customize your own rich REPL experience.

## Features
- Starts an nREPL server
- Creates .nrepl-port file for compatibility with existing dev tools
- Displays parsable nREPL connection header
- Launches interactive Rebel-Readline powered REPL
- Runs Cognitect's REBL UI automatically
- Forms sent from both the Rebel-Readline CLI REPL and from connected nREPL clients are evaluated in the REPL and sent to REBL.

## Installation

### For Clojure CLI\tools.deps users

### For Leiningen users

## Usage

### For Clojure CLI\tools.deps users

Run in your terminal:
```shell
clj -m suprepl.core
```

### For Leiningen users
Run in your terminal:

```shell
lein suprepl
```

## Included support

| Name                   | Description                                            |
| ---------------------- | ------------------------------------------------------ |
| [cider-nrepl](https://github.com/clojure-emacs/cider-nrepl) | Middleware to support emacs + cider
| [nREPL](https://github.com/nrepl/nrepl)                  | Allow editors and other clients to connect to the REPL to evaluate forms and look up docs. |
| [proto-repl](https://github.com/jasongilman/proto-repl)         | Supports working with a REPL using Atom's proto-repl package.
| [REBL](https://github.com/cognitect-labs/REBL-distro) | UI to inspect and navigate Clojure data structures.
| [Rebel-Readline](https://github.com/bhauman/rebel-readline)         | Provides a very rich CLI REPL user experience               |

## Known Issues

 - Running REBL with nREPL means some data will not be able to be inspected due to how data is sent over-the-wire. An issue has been made to nREPL to provide better support for Clojure's 1.10 datafy and nav.
 - Running REBL with nREPL requires middleware to receive submitted forms automatically. Because of this the follow REPL checkbox in REBL is ignored.

## Todo
- [X] File issue with nREPL to have their CLI functions exposed and split up for reuse.
- [ ] Support nREPLs configuration files
- [ ] Replace nREPL<->REBL middleware with https://github.com/RickMoynihan/nrebl.middleware.
- [ ] Add more common middleware
- [ ] Prepare a project.clj so it can be used as a lein plugin

## Contributions

Contributions are welcome. Please fork this repo and submit a pull request. If you're unsure if a pull request will be accepted file an issue or contact me if you want to discuss it first.

## License

Most source comes from the original libraries. Be sure to check out each dependency's license if you have concerns.
