# Mojo Full Adder

## Overview
This implementation leverages Mojo's high-performance, Python-like syntax to create a 1-bit full adder circuit with XOR, AND, and OR gates.

## Directory Structure
```
mojo/
├── gates.mojo      # Definitions for Wire, XORGate, ANDGate, ORGate.
├── circuit.mojo    # FullAdder structure, chaining logic gates.
```

## How to Run

1. Ensure you have Mojo installed and accessible.
2. Navigate to the `mojo/` directory:
   ```bash
   cd mojo
   ```
3. Execute the simulation (adjust syntax as per toolchain version):
   ```bash
   mojo run circuit.mojo
   ```

## Example Output
The simulation performs an exhaustive test of all possible input combinations for a single-bit full adder, verifying correctness.

---
## Extensions
- Add multi-bit adders using arrays of the `FullAdder` structure.
- Facilitate batch processing of inputs with Mojo's SIMD capabilities.