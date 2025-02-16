# LTspice Circuit Project

This repository contains LTspice circuit designs and simulations following Version 4.1 syntax guidelines.

## Directory Structure

```
.
├── src/
│   └── circuits/
│       ├── basic/          # Basic logic gates and fundamental components
│       ├── arithmetic/     # Arithmetic components (half/full adders)
│       ├── alu/           # ALU and related complex components
│       ├── test-benches/  # Test circuit files
│       └── symbols/       # Symbol files (.asy)
│
└── docs/
    ├── circuits/         # Circuit-specific documentation
    ├── analysis/         # Analysis and performance reports
    └── guidelines/       # Project guidelines and standards
```

## Circuit Files

All circuit files (.asc) follow Version 4.1 syntax requirements:

1. Header with Version 4.1 declaration
2. SHEET declaration
3. Wire declarations
4. Flags and IOPINs
5. Symbol blocks
6. Subcircuit definitions in TEXT blocks

## Components

- **Half Adder**: Basic arithmetic component (`src/circuits/arithmetic/half_adder.asc`)
- **Full Adder**: Uses two half adders (`src/circuits/arithmetic/full_adder.asc`)
- **ALU**: 4-bit Arithmetic Logic Unit (`src/circuits/alu/alu.asc`)

## Testing

Test benches are located in `src/circuits/test-benches/` and follow the same Version 4.1 syntax guidelines.

## Documentation

- Circuit documentation in `docs/circuits/`
- Analysis reports in `docs/analysis/`
- Project guidelines in `docs/guidelines/`

## Version Control

- All circuit files must be committed in ASCII format
- Symbol files (.asy) must be generated and committed
- Test results and simulation data should be documented

## Contributing

1. Follow Version 4.1 syntax guidelines
2. Place files in appropriate directories
3. Include proper documentation
4. Add test benches for new components
5. Update symbol files when needed
