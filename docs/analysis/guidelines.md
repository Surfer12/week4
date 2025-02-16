# Schematic File Format Guidelines

This document outlines the guidelines and requirements for all future netlist and schematic files in our project. All files must adhere to the new Version 4.1 syntax as described below.

## Version 4.1 Syntax Overview

1. **Header**: Every file must begin with the line:
   
       Version 4.1
   
2. **Sheet Declaration**: The file must include a SHEET declaration that specifies the sheet number and dimensions. Example:
   
       SHEET 1 880 680
   
3. **Wire Declarations**: WIRE statements must follow the header and sheet declarations. Each WIRE statement specifies connections between coordinates. Example:
   
       WIRE 48 -64 -16 -64
   
4. **Flags and IOPINs**: Input/Output labels and flags must be declared using FLAG and IOPIN statements. These specify signal names and their mapping onto physical pins. Example:
   
       FLAG -16 -32 B[3:0]
       IOPIN -16 -32 In
   
5. **Symbol Blocks**: Use SYMBOL blocks to declare sub-modules or components (such as HalfAdder or FullAdder). Each block should include the SYMBOL keyword, its coordinates, orientation, and any symbolic attributes (e.g., Instance Names). Example:
   
       SYMBOL HalfAdder 416 192 M90
       SYMATTR InstName X1

6. **Subcircuit Definitions**: All subcircuit definitions must be placed in TEXT blocks with proper indentation using !+ for continuation lines:

       TEXT -24 424 Left 2 !.subckt SUBCIRCUIT_NAME in1 in2 vdd vss out
       TEXT -24 456 Left 2 !+ component_definition_line1
       TEXT -24 488 Left 2 !+ component_definition_line2
       TEXT -24 520 Left 2 !.ends

7. **Simulation Directives**: All simulation commands must be placed in TEXT blocks:

       TEXT -24 296 Left 2 !.tran 800n
       TEXT -24 328 Left 2 !.model NMOS NMOS
       TEXT -24 360 Left 2 !.model PMOS PMOS

8. **Include Statements**: Use TEXT blocks for including external files:

       TEXT -24 392 Left 2 !.include "external_file.asc"

## References to Analysis Documents

For further clarification on the design rationale, performance benefits, and detailed breakdown of the syntax, refer to the following documents:

- [Analysis Document](/Users/ryanoates/week4/docs/analysis/analysis.md)
- [Breakdown Explanation](/Users/ryanoates/week4/docs/analysis/breakdown.md)

These documents provide additional context and detailed descriptions of the file format decisions, and they serve as the technical basis for this standard.

## Implementation Requirements

- **Consistency**: All new files must implement this syntax consistently. Any deviation must be approved through proper revision protocols.
- **Automation**: Tools and scripts developed for processing these files should be updated to handle the Version 4.1 syntax.
- **Documentation**: Any changes to the syntax or deviations must be documented and communicated to the team, along with an update to this guidelines document if needed.
- **Hierarchical Design**: When using hierarchical designs:
  - Each subcircuit must be defined in its own .asc file
  - Symbol files (.asy) must be created for each subcircuit
  - Include statements must use relative paths
  - All dependencies must be properly documented

## File Organization

1. **Circuit Files (.asc)**:
   - Main circuit file with Version 4.1 header
   - SHEET declaration
   - WIRE and FLAG statements
   - SYMBOL blocks for components
   - Simulation directives in TEXT blocks
   - Include statements in TEXT blocks

2. **Symbol Files (.asy)**:
   - Generated automatically by LTspice
   - One symbol file per subcircuit
   - Must match pin names with subcircuit definition

3. **Test Files**:
   - Must follow same Version 4.1 syntax
   - Include voltage/current sources for inputs
   - Include proper simulation directives
   - Document test vectors and expected results

## Examples

1. **Half Adder Implementation**:
```
Version 4.1
SHEET 1 880 680

* Circuit implementation
WIRE 48 -64 32 -64
...

FLAG 32 -64 a
IOPIN 32 -64 In
...

SYMBOL NAND 48 -64 R0
SYMATTR InstName X1
...

TEXT -24 296 Left 2 !.tran 400n
...
```

2. **Full Adder Implementation**:
```
Version 4.1
SHEET 1 880 680

* Circuit using Half Adders
WIRE 176 128 144 128
...

FLAG 144 128 a
IOPIN 144 128 In
...

SYMBOL half_adder 176 144 R0
SYMATTR InstName X1
...

TEXT -24 296 Left 2 !.tran 800n
...
```

## Revision History

- **Initial Release**: Version 4.1 syntax requirements effective as of current date
- **Update 1**: Added subcircuit definition formatting in TEXT blocks
- **Update 2**: Added hierarchical design requirements
- **Update 3**: Added detailed examples from half adder and full adder implementations

By following these guidelines, we ensure standardization across our project and maintain compatibility with tools and automation scripts that depend on the Version 4.1 file format.
