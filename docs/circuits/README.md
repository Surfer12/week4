# Circuit Organization

This document describes the organization of circuit files in the project, following Version 4.1 syntax guidelines.

## Directory Structure

```
src/circuits/
├── basic-gates/       # Fundamental logic gates
│   ├── NAND.asc      # NAND gate implementation
│   ├── NOR.asc       # NOR gate implementation
│   ├── INVERT.asc    # Inverter implementation
│   ├── ANDx.asc      # Configurable AND gate
│   ├── ORx.asc       # Configurable OR gate
│   └── MUX.asc       # Multiplexer implementation
│
├── memory/           # Memory and storage components
│   ├── dff.asc       # D Flip-Flop
│   ├── RegisterFile.asc # Register file implementation
│   ├── DataMem.asc   # Data memory
│   └── InstructionMemory.asc # Instruction memory
│
├── processor/        # Processor components
│   ├── Computer.asc  # Top-level computer implementation
│   ├── ALU.asc      # Arithmetic Logic Unit
│   └── Master.asc   # Master control unit
│
├── arithmetic/       # Arithmetic circuits
│   ├── half_adder.asc    # Half adder
│   ├── full_adder.asc    # Full adder
│   ├── cla_4bit.asc      # 4-bit carry-lookahead adder
│   ├── carry_save_adder.asc # Carry-save adder
│   ├── cla_gp_unit.asc   # Generate-propagate unit
│   └── cla_logic_unit.asc # CLA logic unit
│
├── test-benches/    # Test circuits
│   ├── *_test.asc   # Component test benches
│   └── GateTest.asc # Gate testing suite
│
├── models/          # SPICE models and parameters
│   └── ModelSp2025.txt # Model parameters
│
├── symbols/         # Symbol files
│   ├── *.asy       # Symbol definitions
│   └── README.md   # Symbol usage guidelines
│
└── labs/           # Laboratory exercises
    └── lab2-*.asc  # Lab 2 circuit files
```

## File Naming Conventions

1. **Basic Gates**: Use PascalCase for gate names (e.g., `NAND.asc`, `NOR.asc`)
2. **Test Benches**: Append `_test` to the component name (e.g., `half_adder_test.asc`)
3. **Symbol Files**: Match the circuit file name with `.asy` extension
4. **Lab Files**: Include lab number and date (e.g., `lab2-update-feb8.asc`)

## Component Categories

### Basic Gates
- Fundamental logic gates (NAND, NOR, NOT)
- Configurable gates (ANDx, ORx)
- Basic combinational circuits (MUX)

### Memory Components
- Sequential elements (D Flip-Flops)
- Storage components (Register File, Memory)
- Memory interfaces

### Processor Components
- Top-level integration
- Core processing units
- Control logic

### Arithmetic Circuits
- Basic arithmetic (Half/Full Adders)
- Advanced arithmetic (CLA, CSA)
- Specialized units (Generate-Propagate)

## Testing Strategy

1. Each component has a corresponding test bench in `test-benches/`
2. Test files follow the same Version 4.1 syntax
3. Standard test vectors are provided
4. Results are documented in the test files

## Model Management

- All SPICE models are centralized in `models/`
- Model parameters are version controlled
- Each model file includes documentation

## Symbol Library

- Symbols follow LTspice standard format
- Each symbol includes required attributes
- Version information is maintained
- Documentation provided in symbols/README.md

## Lab Exercises

- Organized by lab number
- Include date in filename for version tracking
- Maintain separate directory to avoid cluttering main components

## Usage Guidelines

1. Place new components in appropriate directories
2. Create corresponding test benches
3. Generate and update symbol files
4. Document any special requirements
5. Follow Version 4.1 syntax guidelines 