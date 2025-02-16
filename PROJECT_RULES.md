# Project Rule: Circuit Design and File Organization

This project rule encapsulates the circuit design and file organization guidelines derived from the Professor's Reference Library located at `/Users/ryanoates/week4/professor-reference-library/`. It is mandatory to follow these guidelines for all circuit design files in this project.

## File Version and Basic Structure
- Schematic files (`.asc`) must start with the header: `Version 4.1`
- Symbol files (`.asy`) must start with the header: `Version 4`
- Each circuit component requires both an `.asc` schematic file and an `.asy` symbol file.
- Essential elements include SHEET definitions, WIRE declarations, FLAGs, and IOPIN declarations (in schematic files).

## Project Organization
- **LogicLibrary/**: Contains basic components (e.g., AND, NAND) with paired `.asc` and `.asy` files.
- **implementation/**: Contains complex circuits and specific implementations with corresponding schematic and symbol files.
- **tests/**: Contains test files (e.g., `InputTest.txt`) and separate test vector files, each with descriptive names and scenario comments.

## Naming Conventions
- Component names must be in **UPPERCASE** (e.g., `ANDx`, `NAND`).
- Internal signal names should use the `mid` prefix (e.g., `midb[3:0]`, `mida[3:0]`).
- Clock signals should use the `Clk` naming format (e.g., `Clk`, `SysClk`).
- Instance names should follow the format X1, X2, etc., as in:
  ```
  SYMBOL NAND 128 128 R0
  SYMATTR InstName X1
  ```

## Circuit Standards
- Bus notations should use formats like `[3:0]` for 4-bit buses (e.g., `A[3:0]`).
- Comments must use semicolon format, for example:
  ```
  TEXT -384 288 Left 2 ;Add
  ```
- Standard voltage levels and timing units must be adhered to (Logic Low: 0V, Logic High: 5V; Timing in nanoseconds and milliseconds as specified).
- Schematic (`.asc`) files are designed for LTSpice simulation, while symbol (`.asy`) files provide block representations.

## SPICE Models and Testing Standards
- Use the provided SPICE models:
  - NMOS: Sp2025N
  - PMOS: Sp2025P
- Test files should include clearly defined voltage source definitions and be organized in separate `.txt` files with descriptive names (e.g., `EightInputsForAdderTest.txt`).

## Additional Context
- These rules are based on the Professor's Reference Library. All guidelines must be cross-referenced with the library at `/Users/ryanoates/week4/professor-reference-library/`.
- Any revisions from in-class feedback or professor's updates should be incorporated promptly.
- Maintaining proper file organization and naming conventions is essential for ensuring design consistency and reliable LTSpice simulations. 