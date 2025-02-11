# Simple Classroom Computer: Design Documentation

## Document Metadata
**Title:** Simple Classroom Computer Design Documentation
**Version:** 1.0
**Date:** 2024-XX-XX
**Author:** [Your Name]

---

## 1. Overall Block Diagram
1. **Instruction Memory (X1)**
   - Holds the machine instructions in a small SRAM-like block.
   - The Program Counter (PC) feeds the address signals (`Addr[2:0]` in the schematic), incremented each clock cycle to fetch successive instructions.
   - The output (`INSTR[7:0]`) is latched or read directly by control circuitry and the register file.

2. **Data Memory (X6)**
   - Stores data that can be read/written by LOAD and STORE instructions.
   - Address lines come from either the register file output or instruction bits, depending on how the control is set up.
   - A `WMem` (write enable) signal allows data to be stored when the CPU executes a STORE instruction.

3. **Register File (X3)**
   - A small set of internal registers (e.g., R0, R1, R2, R3).
   - The CPU can read from two source registers and write to one destination register each cycle.
   - Each register is typically 8 bits wide.

4. **ALU / Arithmetic-Logic Stage**
   - Performs ADD or SUB operations based on an ALU control bit (`Add0sub1`).
   - For ADD, it sums two register values; for SUB, it subtracts.
   - The result is fed back either to the register file or to data memory (STORE), depending on the instruction.

5. **Control Signals**
   - Decoded from the instruction bits. A typical instruction format might be:
     ```
     [7:5] = Op-code
     [4:3] = Destination Register
     [2:1] = Source Register
     [0]   = Possibly an immediate flag or an ALU control bit
     ```
   - The control logic sets:
     - **ALU operation** (Add vs. Sub)
     - **Write Enable** for the register file
     - **Memory Write Enable (WMem)**
     - **Source/destination register selects**
     - **Increment PC** or jump (if a jump instruction exists)

---

## 2. Instruction Set Overview
The schematic supports four primary op-codes:

1. **ADD** (`00` in binary, op-code = 0)
   - Performs `Rdest = Rdest + Rsource`.
   - `Add0sub1 = 0` for the ALU to perform addition.

2. **SUB** (`01` in binary, op-code = 1)
   - Performs `Rdest = Rdest - Rsource`.
   - `Add0sub1 = 1` to direct the ALU for subtraction.

3. **LOAD** (`10` in binary, op-code = 2)
   - Loads data from Data Memory into the destination register.
   - The memory address for LOAD may be given by immediate instruction bits or via indirect addressing from registers.

4. **STORE** (`11` in binary, op-code = 3)
   - Stores the content of the source register into Data Memory.
   - Memory address selection can also depend on instruction bits or register outputs.

> **Note:** The specific bit-field allocation (i.e., which bits refer to the source vs. destination) is design-dependent and must be clearly documented.

---

## 3. Program Flow Example
A brief sample program in assembly-like form might be:

1. **LOAD R0, 0x00**
   - Op-code = `10` (LOAD)
   - Destination: R0 gets the value from Data Memory at address `0x00`.
   - Effect: R0 ← DataMem[0x00]

2. **LOAD R1, 0x0C**
   - Loads data from Data Memory address `0x0C` into R1.

3. **ADD R3, R2**
   - Op-code = `00` (ADD)
   - Adds the content of R2 to R3, storing the result in R3.
   - Effect: R3 ← R3 + R2

4. **STORE R3, 0x0F**
   - Op-code = `11` (STORE)
   - Stores the value from R3 into Data Memory at address `0x0F`.

Each instruction is fetched sequentially as the Program Counter (PC) increments each clock cycle.

---

## 4. Putting It All Together
- **Fetch:** The CPU reads the instruction from Instruction Memory using the PC.
- **Decode:** The instruction is parsed into opcode and register selectors to generate the necessary control signals.
- **Execute:**
  - For ADD/SUB operations, the ALU processes the two register operands.
  - For LOAD, data is read from Data Memory into a register.
  - For STORE, the content of a register is written back to Data Memory.
- **Write-Back:** The result is stored in the designated register or memory location.
- **PC Increment:** The Program Counter updates to point to the next instruction.

This flow mirrors the structured approach used in the Half Adder design documentation, ensuring that each part of the CPU is verified and documented.

---

## 5. Key Takeaways and Tips
1. **Modular Design:**
   - Isolate each module (Instruction Memory, Data Memory, Register File, ALU, Control) for independent testing.
2. **Clear Instruction Format:**
   - Specify which bits perform what function to avoid ambiguity in decoding.
3. **Memory Access:**
   - Ensure that the address selection for LOAD/STORE operations is correct and that the write enable (`WMem`) activates only for STORE.
4. **Simulation:**
   - Run transient analyses (e.g., in LTspice) with defined clock signals to verify register and memory state transitions.
5. **Incremental Testing:**
   - Start with a simple program (e.g., LOAD, then ADD, then STORE) and verify each step.
6. **Extend and Optimize:**
   - After verification, consider adding more operations or expanding memory/register sizes.

---

## 6. Conclusion
The simple classroom computer demonstrates how digital logic blocks—such as instruction and data memories, a register file, and an ALU—work together. Through systematic fetch, decode, execute, and write-back cycles, the CPU performs basic operations (ADD, SUB, LOAD, STORE). This documentation, tailored for the schematic image (@l3), integrates a detailed explanation similar in style to the 'Half Adder Design Documentation,' ensuring thorough verification and clarity throughout the CPU's design.

---

*End of Document*