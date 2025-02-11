# C Full Adder

## Overview
This implementation simulates a 1-bit full adder using C. It includes reusable components for gates, wires, and circuits.

## Directory Structure
```
c/
├── include/
│   ├── gate.h       # Header for gate structure and API.
│   ├── wire.h       # Header for wire structure and API.
│   ├── circuit.h    # Header for the full adder circuit structure and API.
├── src/
│   ├── gate.c       # Implements gate logic (AND, XOR, OR).
│   ├── wire.c       # Implements wire behavior.
│   ├── circuit.c    # Implements the full adder circuit internals.
│   ├── main.c       # Demo entry point.
└── Makefile          # Build script.
```

## How to Build and Run

### Build
1. Navigate to the `c/` directory:
   ```bash
   cd c
   ```
2. Build the project:
   ```bash
   make
   ```

### Run
Execute the binary:
```bash
./fulladder
```

## Cleanup
To remove generated files:
```bash
make clean
```

## Notes
- Extendable to multi-bit adders by chaining single-bit adders.
- Can be incorporated into larger event-driven simulations.