Programing Languages 2024/2025
# Phase 2 - X++ Code Implementation

This repository contains an interpreter for X++, a small functional programming language with mutable state and lazy evaluation capabilities. X++ features include variables, functions with closures, control structures, mutable boxes, and both strict and lazy lists.

## Features

- Variables and let bindings
- Arithmetic and boolean expressions
- First-class functions and closures
- Control structures (if-else, while)
- Mutable state with boxes (box, deref, assign)
- Lists (both strict and lazy lists with `::` and `:?`)
- Pattern matching with match expressions
- Type checking support
- Recursive types support

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

After building the project, run the X++ interpreter:

```bash
./x++.sh
```

This will start an interactive REPL (Read-Eval-Print Loop) where you can enter expressions, terminated with `;;`. The result of evaluating the expressions will be printed after each entry.

If you prefer, you van also run a program using:
```bash
./x++.sh test_name.txt
```

## License

This project is part of Programming Languages course (2024/2025).
