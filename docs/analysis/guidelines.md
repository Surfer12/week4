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

## References to Analysis Documents

For further clarification on the design rationale, performance benefits, and detailed breakdown of the syntax, refer to the following documents:

- [Analysis Document](/Users/ryanoates/week4/docs/analysis/analysis.md)
- [Breakdown Explanation](/Users/ryanoates/week4/docs/analysis/breakdown.md)

These documents provide additional context and detailed descriptions of the file format decisions, and they serve as the technical basis for this standard.

## Implementation Requirements

- **Consistency**: All new files must implement this syntax consistently. Any deviation must be approved through proper revision protocols.
- **Automation**: Tools and scripts developed for processing these files should be updated to handle the Version 4.1 syntax.
- **Documentation**: Any changes to the syntax or deviations must be documented and communicated to the team, along with an update to this guidelines document if needed.

## Revision History

- **Initial Release**: This document and the Version 4.1 syntax requirements are effective as of the current date.

By following these guidelines, we ensure standardization across our project and maintain compatibility with tools and automation scripts that depend on the Version 4.1 file format.
