# Half Adder Module

This directory contains the design, implementation, and simulation files for a Half Adder circuit.

## Files

* **`doc/`**: Design documentation and verification checklists.
    * `half_adder_design.md`: Markdown file containing the design documentation.
    * `half_adder_verification.md`: Markdown file with a verification checklist.
* **`src/`**: Verilog source code for the Half Adder.
    * `half_adder_basic.v`: Verilog implementation using basic AND, OR, and NOT gates.
    * `half_adder_nand.v`: Verilog implementation using only NAND gates (and a final AND for carry).
    * `half_adder_pkg.v`: Verilog package with constants/parameters.
* **`sim/`**: Simulation and testbench files.
    * `half_adder_tb.v`: Verilog testbench for verifying the Half Adder.
    * `ltspice/`: LTSpice schematics and netlists.
        * `half_adder_basic.asc`: LTSpice schematic (basic implementation).
        * `half_adder_nand.asc`: LTSpice schematic (NAND implementation).
        * `half_adder_basic.net`: LTSpice generated netlist (basic).
        * `half_adder_nand.net`: LTSpice generated netlist (NAND).

## Usage

1. **Verilog Simulation:** Use a Verilog simulator (e.g., ModelSim, Icarus Verilog) to compile and run `half_adder_tb.v`. This will instantiate and test both `half_adder_basic.v` and `half_adder_nand.v`.

2. **LTSpice Simulation:** Open the `.asc` files in LTSpice and run the simulation. You'll need the `LogicLibrary.zip` components available in your LTSpice library path.

## Verification

The `half_adder_verification.md` file provides a checklist to ensure the design meets all requirements.
