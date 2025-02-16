# Circuit Design Standards
*Based on Professor's Reference Library (/Users/ryanoates/week4/professor-reference-library/)*

## File Version and Basic Structure
- All files must use **Version 4** header
- Required components:
  - SHEET definitions
  - WIRE declarations
  - FLAGs
  - IOPIN declarations
- Each circuit component requires two files:
  - `.asc` - Circuit schematic file
  - `.asy` - Symbol file

## Project Organization
### Directory Structure
```
project_root/
├── LogicLibrary/       # Basic components (AND, NAND, etc.)
│   ├── ANDx.asc
│   ├── ANDx.asy
│   ├── NAND.asc
│   └── NAND.asy
├── implementation/     # Complex circuits and specific implementations
│   ├── ALU.asc
│   └── ALU.asy
└── tests/             # Test files at root level
    └── InputTest.txt
```

### File Pairs
Every component must have both files:
1. Schematic (`.asc`):
   - Contains the circuit implementation
   - Includes all wire connections and component instances
2. Symbol (`.asy`):
   - Defines the block representation
   - Specifies pin locations and attributes

## Naming Conventions
### Component Names
- Use UPPERCASE for all component names
  - Correct: `ANDx`, `NAND`, `INVERT`
  - Incorrect: `and`, `Nand`

### Signal Names
- Internal signals use 'mid' prefix
  - Example: `midb[3:0]`, `mida[3:0]`
- Clock signals use 'Clk' naming
  - Example: `Clk`, `SysClk`

### Instance Names
- Use X1, X2 format for component instances
  ```
  SYMBOL NAND 128 128 R0
  SYMATTR InstName X1
  ```

### Test Voltage Sources
- Use V prefix followed by signal name
  - Example: `VA1`, `VB1`, `VCLK`

## Circuit Standards
### Bus Notation
- Use [3:0] format for 4-bit buses
  - Example: `A[3:0]`, `B[3:0]`, `Sum[3:0]`

### Comments
- Use semicolon format
  ```
  TEXT -384 288 Left 2 ;Add
  ```

### Voltage and Timing
- Standard voltage levels:
  - Logic Low: 0V
  - Logic High: 5V
- Timing units:
  - Nanoseconds: `1n`
  - Milliseconds: `1m`
  Example:
  ```
  PULSE(0 5 0 1n 1n 0.5m 1m)
  ```

## Symbol Standards
### Pin Attributes
- All pins must include SpiceOrder attribute
  ```
  PINATTR SpiceOrder 1
  ```
- Use PINATTR format for all pin attributes
  ```
  PINATTR PinName A
  PINATTR SpiceOrder 1
  ```

### Symbol Type
- Use SymbolType BLOCK for new components
  ```
  Version 4
  SymbolType BLOCK
  ```

### Pin Orientation
- Inputs: LEFT side
- Outputs: RIGHT side
- Standard pin spacing: 8 units
  ```
  PIN -48 -16 LEFT 8
  PIN 64 0 RIGHT 8
  ```

## SPICE Models
### Required Models
- NMOS: Sp2025N
- PMOS: Sp2025P

### Model Parameters
```
.MODEL Sp2025N NMOS (
    LEVEL=7
    CGSO=3.4E-10
    CGDO=3.4E-10
    CJ=6.8e-4
    CJSW=1.26e-10
    MJ=0.5
    MJSW=0.5
    CGBO=5.75E-10
    RSH=33.8
)

.MODEL Sp2025P PMOS (
    LEVEL=7
    CGDO=5.3752E-11
    CGSO=5.3752E-11
    CGBO=3.3650E-10
    CJ=4.8447E-04
    MJ=0.5027
    CJSW=1.6457E-10
    MJSW=0.217168
)
```

## Testing Standards
### Voltage Source Definition
```
VA1 A1 0 PULSE(0 5 0 1n 1n 0.5m 1m)
VB1 B1 0 PULSE(0 5 0 1n 1n 8m 16m)
VCLK Clk 0 PULSE(0 5 0 1n 1n 0.5m 1m)
```

### Test File Organization
- Test vectors should be in separate .txt files
- Use descriptive names (e.g., `EightInputsForAdderTest.txt`)
- Include comments describing test scenarios

## Compliance
To ensure compliance with these standards:
1. Use the provided templates from LogicLibrary
2. Follow the naming conventions strictly
3. Include all required attributes and parameters
4. Maintain proper file organization
5. Use the standard voltage and timing values

For questions or clarification, refer to the professor's reference library at:
`/Users/ryanoates/week4/professor-reference-library/`