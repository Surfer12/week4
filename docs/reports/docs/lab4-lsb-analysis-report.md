# LSB Block Design and Implementation
CIS 240 - Lab 2
Author: Ryan Oates
Date: February 10, 2025

## 1. Design Overview

### 1.1 Objective
Design and implement a simplified LSB (Least Significant Bit) block for a ripple-carry adder, where carry-in is fixed at zero. The design emphasizes gate-level optimization and verifiable functionality through LTSpice simulation.

### 1.2 Design Requirements
- Inputs: A[0], B[0]
- Outputs: Sum[0], Carry_out
- Fixed constraint: Cin = 0
- Implementation: Gate-level using available components
- Verification: Complete simulation of all input combinations

## 2. Theoretical Analysis

### 2.1 Truth Table Analysis
```
Input Combinations  | Outputs
A[0] B[0] | Sum[0] Carry_out
-------------------------
  0   0   |   0      0
  0   1   |   1      0
  1   0   |   1      0
  1   1   |   0      1
```

### 2.2 Boolean Expression Derivation
From truth table analysis:
- Sum[0] = A'B + AB' (XOR function)
- Carry_out = AB (AND function)

### 2.3 Gate-Level Reduction
Sum Implementation Options:
1. Direct XOR approach (if XOR gate available)
2. NAND-based XOR implementation:
   - NAND(A,B) → N1
   - NAND(A,B) → N2
   - NAND(N1,N2) → Sum[0]

Carry Implementation:
- Direct AND gate for Carry_out

## 3. LTSpice Implementation

### 3.1 Component Selection
Required components:
- 2x NAND gates (for Sum generation)
- 1x OR gate (or NAND with inversion)
- 1x AND gate (for Carry generation)

### 3.2 Schematic Creation Steps
1. Create new schematic (File → New Schematic)
2. Place input voltage sources:
   - V1 for A[0]
   - V2 for B[0]
   - Vdd supply
3. Place and configure gates:
   ```
   [NAND1] - inputs: A[0], B[0]
   [NAND2] - inputs: A[0], B[0]
   [OR1]   - inputs: NAND1_out, NAND2_out
   [AND1]  - inputs: A[0], B[0]
   ```
4. Add node labels:
   - Input nodes: A[0], B[0]
   - Output nodes: Sum[0], Carry_out
   - Power: vdd
   - Ground: 0

### 3.3 Symbol Creation
1. Create symbol (Hierarchy → Create Symbol)
2. Define pins:
   ```
   Left side:
   - A[0]: Input, position (0,10)
   - B[0]: Input, position (0,30)
   
   Right side:
   - Sum[0]: Output, position (80,10)
   - Carry_out: Output, position (80,30)
   ```
3. Save symbol as "LSB_ADD.asy"

## 4. Simulation Setup

### 4.1 Test Circuit Configuration
```spice
* Test circuit netlist
V1 A[0] 0 PULSE(0 5 0 1n 1n 20n 40n)
V2 B[0] 0 PULSE(0 5 0 1n 1n 40n 80n)
Vdd vdd 0 5

.tran 0 100n 0 0.1n
```

### 4.2 Analysis Configuration
- Transient analysis: 100ns duration
- Step size: 0.1ns
- Measurement points:
  1. Rise/fall times
  2. Propagation delays
  3. Output stability

## 5. Verification Process

### 5.1 Test Vector Coverage
Truth table verification matrix:
```
Time Window | A[0] B[0] | Expected Sum[0] Carry_out
0-20ns     |  0   0    |    0          0
20-40ns    |  1   0    |    1          0
40-60ns    |  1   1    |    0          1
60-80ns    |  0   1    |    1          0
80-100ns   |  0   0    |    0          0
```

### 5.2 Timing Analysis
Critical path measurements:
- Sum path delay (through NAND-OR chain)
- Carry path delay (through AND gate)
- Setup/hold time verification

## 6. Results and Analysis

### 6.1 Functional Verification
- Truth table conformance
- Output stability analysis
- Glitch detection
- Timing margin verification

### 6.2 Performance Metrics
- Gate count: 4 total gates
- Maximum propagation delay
- Power consumption estimation
- Area efficiency analysis

## 7. Integration Notes

### 7.1 Block Interface
- Standard voltage levels (0V/5V)
- Load characteristics
- Fan-out limitations

### 7.2 Usage Guidelines
1. Power supply requirements
2. Input signal specifications
3. Output loading constraints
4. Timing considerations

## 8. Appendices

### A. Complete Schematic
[Insert final schematic screenshot]

### B. Simulation Waveforms
[Insert key waveform captures]

### C. LTSpice Files
- LSB_ADD.asc (schematic)
- LSB_ADD.asy (symbol)
- TEST_LSB.asc (test bench)