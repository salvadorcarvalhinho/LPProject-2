Programing Languages 2024/2025
# Phase 1 - L1++ Code Implementation

This repository contains an interpreter for L1++, a small functional programming language with mutable state and lazy evaluation capabilities. L1++ features include variables, functions with closures, control structures, mutable boxes, and both strict and lazy lists.

## Features

- Variables and let bindings
- Arithmetic and boolean expressions
- First-class functions and closures
- Control structures (if-else, while)
- Mutable state with boxes (box, deref, assign)
- Lists (both strict and lazy lists with `::` and `:?`)
- Pattern matching with match expressions

## Building the Project

To build the interpreter, use the provided build script:

```bash
./makeit.sh
```

This script:
1. Removes any previous compiled Java classes
2. Generates the parser using JavaCC from ParserL0.jj
3. Compiles all Java files

If you need to clean up compiled files without rebuilding, run:

```bash
./clean.sh
```

## Running the Interpreter

After building the project, run the L0 interpreter:

```bash
java L0int
```

This will start an interactive REPL (Read-Eval-Print Loop) where you can enter expressions, terminated with `;;`. The result of evaluating the expressions will be printed after each entry.

## License

This project is part of Programming Languages course (2024/2025).
