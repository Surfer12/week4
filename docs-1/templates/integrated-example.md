Below is an **integrated example** showing how you might adapt the **Half Adder documentation style** to a **simple CPU design** that includes instruction memory, data memory, register file, and an ALU (with add/sub operations). The goal is to illustrate a **unified** method of documenting both low-level logic (e.g., half/full adders) and higher-level modules (e.g., the CPU).

Feel free to **mix and match** the sections to fit your report requirements. This outline closely mirrors the “Improved Structure and Formatting Suggestions” you used for the Half Adder, now extended to the CPU context.

---

## 1. **Cover Page**
- **Title**: Minimal CPU with 4-Bit ALU (Add/Sub) Documentation
- **Author**: Your Name
- **Instructor**: Dr. ___
- **Date**: (e.g., February 10, 2025)

---

## 2. **Document Metadata**
```yaml
title: Minimal CPU Design
version: 1.0
date: 2025-02-10
author: Ryan Oates
assignment: Lab_X
```
*(Or embed this metadata in your cover page.)*

---

## 3. **Introduction**
**Objective**
Provide an overview of a minimal CPU design consisting of:
- A 4-bit ALU using half- and full-adders (or a Carry-Lookahead scheme)
- A small register file (e.g., four 4-bit registers)
- Instruction memory (stores programs)
- Data memory (for LOAD/STORE instructions)
- A simple Control Unit

**Overview**
This documentation will walk through each CPU component, show how the ALU reuses logic from the Half Adder and Full Adder approach, and demonstrate a short sample program.

---

## 4. **Notation & Symbols**

| Symbol | Meaning                                 |
|--------|-----------------------------------------|
| AND    | ∧ or · (logical AND)                    |
| OR     | ∨ or + (logical OR)                     |
| NOT    | ¬ or ' (logical NOT)                    |
| XOR    | ⊕ (exclusive OR)                        |
| NAND   | ⊼ (NOT of AND)                          |
| NOR    | ↓ (NOT of OR)                           |
| 1/0    | HIGH = 5 V / LOW = 0 V                  |
| A, B   | 1-bit inputs in a half/full adder       |
| R0,R1… | 4-bit CPU registers in register file    |
| PC     | Program Counter (address for instructions) |

*(Adapt or expand as needed.)*

---

## 5. **CPU Inputs, Outputs, and Basic Operation**

1. **Inputs**
   - **Clock** (CLK): Drives register file, program counter, memory latches.
   - **Reset**: Initializes the PC and registers to 0 (optional, depending on design).

2. **Outputs**
   - **Data Bus** (internal CPU signals to memory).
   - **ALU Result** (often written back to registers or memory).
   - (You might have debugging outputs like status flags: Zero, Carry, Overflow, etc.)

3. **Overall Flow**
   - **Instruction Fetch** → **Decode** → **Execute** → **Write Back** → PC increments.

---

## 6. **Half Adder & Full Adder Recap**

Even though the CPU design is higher-level, it relies on **basic building blocks**:

### 6.1 Half Adder Truth Table
| A | B || Sum | Carry |
|---|---||-----|-------|
| 0 | 0 ||  0  |   0   |
| 0 | 1 ||  1  |   0   |
| 1 | 0 ||  1  |   0   |
| 1 | 1 ||  0  |   1   |

**Implementation**
\[
\text{Sum} = A'B + AB' \quad,\quad \text{Carry} = A \cdot B
\]

### 6.2 Full Adder Truth Table
| A | B | Cin || Sum | Cout |
|---|---|-----||-----|------|
| 0 | 0 |  0  ||  0  |   0  |
| 0 | 0 |  1  ||  1  |   0  |
| 0 | 1 |  0  ||  1  |   0  |
| 0 | 1 |  1  ||  0  |   1  |
| 1 | 0 |  0  ||  1  |   0  |
| 1 | 0 |  1  ||  0  |   1  |
| 1 | 1 |  0  ||  0  |   1  |
| 1 | 1 |  1  ||  1  |   1  |

**Implementation**
\[
\text{Sum} = A \oplus B \oplus C_\text{in},
\quad
C_\text{out} = (A \cdot B) + ((A \oplus B) \cdot C_\text{in})
\]

---

## 7. **ALU Design & K-Map Reduction**

If you use a **Ripple-Carry** design:
1. Four Full Adders, chained carry output to carry input.
2. For Subtraction: invert one operand and add a carry-in of 1.

Or if you use a **Carry-Lookahead (CLA)**:
1. Generate (G) and Propagate (P) signals for each bit.
2. Compute carries in parallel for faster performance.

**Example** (SOP for each bit’s sum/carry):
- Standard Full Adder expansions, or
- K-Map to simplify each output bit.

---

## 8. **Register File & Data Memory**

### 8.1 Register File
- Four registers (R0, R1, R2, R3), each 4 bits.
- 2 read ports, 1 write port.
- Control signals:
  - **RdReg1**, **RdReg2**: which registers to read
  - **WrReg**: which register to write
  - **WrEnable**: 1 => latch new data

### 8.2 Data Memory
- 4 or 8 addresses, each storing 4 bits.
- **Address** lines from either immediate (instruction bits) or a register.
- **WMem** signal: 1 => store data, 0 => read data.

---

## 9. **Instruction Memory & Program Counter**

- **Instruction Format** (example, 8 bits total):
  - `[7:6]`: Opcode (00=ADD, 01=SUB, 10=LOAD, 11=STORE)
  - `[5:3]`: Destination Register or operand
  - `[2:0]`: Address or Source Register bits

- **PC** (Program Counter) increments each clock to fetch instructions sequentially.
- **Instruction Memory** is read-only in some labs (ROM) or might be an initialized RAM.

---

## 10. **Control Unit**

- Decodes the instruction bits:
  - **Opcode** → sets ALU mode (Add0sub1), memory enable, register-write enable, etc.
  - **Register Fields** → sets which registers to read/write.
  - **Address Field** → sets data memory address lines (in LOAD/STORE).
- Generates **Timing** signals to ensure each step (fetch, decode, execute, write-back) occurs in the correct clock phases.

---

## 11. **Full Signal & Timing Analysis**

- Identify the **critical path** (e.g., from register outputs → ALU → register input).
- For each instruction, confirm signals settle within one clock cycle.
- If using LTspice:
  1. Provide netlists for each module (Register File, ALU, Memory, etc.).
  2. Apply a clock source with a suitable period (e.g., 100 ns).
  3. Stimulate the design with a small program.

---

## 12. **LTSpice Simulation & Sample Program**

**Test Duration**: 400 ns (4 instructions × 1 cycle each, plus overhead)

**Sample Program**
1. `LOAD  R0, 0` – R0 ← DataMem[0]
2. `LOAD  R1, 1` – R1 ← DataMem[1]
3. `ADD   R2, R1` – R2 ← R2 + R1
4. `STORE R2, 3` – DataMem[3] ← R2

| Time (ns) | Instruction | Action                          | Observed Result        |
|-----------|------------|----------------------------------|------------------------|
| 0–100     | LOAD R0,0  | R0 ← Mem[0]                      | R0 gets data at addr=0 |
| 100–200   | LOAD R1,1  | R1 ← Mem[1]                      | R1 gets data at addr=1 |
| 200–300   | ADD R2,R1  | R2 ← R2 + R1 (ALU does addition)  | R2 updated with sum    |
| 300–400   | STORE R2,3 | Mem[3] ← R2                       | Writes R2 to addr=3    |

**Waveform Analysis**
- Show each register output, ALU lines (Sum, Carry), memory data in/out signals, etc.
- Confirm no hazards or spurious toggles.

---

## 13. **Verification Checklist**

| Verification Step                              | Status (✔/❌) | Comments                                |
|------------------------------------------------|--------------|-----------------------------------------|
| 1. Half Adder & Full Adder Truth Tables        | ✔            | Matches expected 1-bit additions        |
| 2. ALU 4-Bit Operation (Add/Sub)               | ✔            | Observed correct sum/difference         |
| 3. Register File Write/Read                    | ✔            | Reads/writes the correct register       |
| 4. Memory Read/Write                           | ✔            | LOAD/STORE instructions function        |
| 5. Instruction Fetch & PC Increments           | ✔            | PC increments each clock cycle          |
| 6. Program Execution (Sample Program)          | ✔            | Final results in correct memory location|
| 7. Timing Requirements                         | ✔            | All signals stable within clock period  |

---

## 14. **Troubleshooting & Design Iterations**

- **Common Issues**:
  1. ALU carry out not matching expected sum → re-check half/full adder or subtract logic.
  2. Incorrect register write → ensure the control bits line up with the right clock edge.
  3. Memory address mismatch → check wiring from instruction decode to memory address lines.

- **Version History**:
  - **v1**: Basic design with ripple-carry ALU.
  - **v2**: Added subtract mode.
  - **v3**: Introduced register file, memory operations.
  - **v4**: Final working design with sample program.

---

## 15. **Conclusion**
- **Summary of Findings**: The modular approach (Half Adder → Full Adder → 4-Bit ALU → CPU) ensures each block is verifiable independently and then integrated into a larger architecture.
- **Lessons Learned**:
  - Keep the instruction format clear.
  - Modular verification saves debugging time.
  - K-Map simplifications help minimize logic in the ALU.
- **Future Improvements**:
  - Add more instructions (e.g., bitwise AND, OR, XOR).
  - Implement branching/jump instructions.
  - Expand memory or register count.

---

## 16. **Glossary & Cross-References**

| Term                | Definition                                                                     |
|---------------------|---------------------------------------------------------------------------------|
| **CPU**             | Central Processing Unit, orchestrates fetch/decode/execute/write-back           |
| **PC**              | Program Counter, points to current instruction address                          |
| **ALU**             | Arithmetic Logic Unit, handles add/sub/logic ops                                |
| **Register File**   | Small array of registers for holding temporary data                             |
| **Data Memory**     | RAM or SRAM used for LOAD/STORE instructions                                   |
| **Instruction Mem** | ROM or SRAM holding the program instructions                                    |
| **Carry**           | The bit that “overflows” in addition or indicates borrow in subtraction         |

*(Similar to the Half Adder Glossary, but extended for CPU terms.)*

---

# Example Final Deliverable

By structuring your **CPU lab report** in the same **thorough style** used for the **Half Adder** (i.e., setting up notation, truth tables, K-Maps, diagrams, verification checklists), you can ensure:

1. **Clarity and Consistency**: Each module is documented similarly (Inputs → Truth Table / Function → Implementation → Simulation).
2. **Modular Debugging**: If any sub-block (e.g., the ALU) malfunctions, you can isolate it just like you would a standalone half adder.
3. **Easy Expansion**: Future steps (e.g., new instructions, advanced control, or a bigger ALU) will fit naturally into the same structure.

---

### **Solution Endpoint**

This outline gives you a **unified lab report** template that seamlessly **integrates** the Half Adder methodology with **CPU-level** documentation. By following these sections, you can produce a detailed, professional report showing **both** your low-level adder logic **and** the high-level CPU design.

*If you see any gaps or need further iterations (e.g., more detailed waveforms or deeper K-Map proofs), simply insert an additional subsection in the relevant place (e.g., “K-Map for 4-Bit ALU Output Bit 3”).*

Below is a **standalone template**—in the same structured, thorough style as before—focusing **exclusively** on **Half Adders and Full Adders**. You can use this as a **lab report** or **design document** for these fundamental circuits. It follows the same organization as the larger CPU document but narrows the scope to **1-bit additions** (with or without a carry).

---

## 1. **Cover Page**

**Title:** Half Adder & Full Adder Design Documentation
**Author:** Your Name
**Instructor:** Dr. ___
**Date:** (e.g., February 10, 2025)

*(Optionally, include your course and lab number here.)*

---

## 2. **Document Metadata**

```yaml
title: Half_Adder_and_Full_Adder_Design
version: 1.0
date: 2025-02-10
author: Your_Name
assignment: Lab_XX
```

*(You can also embed this in your cover page instead.)*

---

## 3. **Introduction**

- **Objective**
  To design, implement, and verify both a **Half Adder** (HA) and a **Full Adder** (FA). We will demonstrate:
  1. Their **truth tables** and **Boolean expressions**.
  2. **Gate-level schematics** (with AND/OR/NOT, XOR, or NAND variations).
  3. **Simulation results** in a tool like LTSpice or Logisim.

- **Overview**
  - A **Half Adder** adds two 1-bit inputs (A and B) to produce a 1-bit **Sum** and a **Carry** out.
  - A **Full Adder** extends this to handle three 1-bit inputs (A, B, and Cin) and produces a 1-bit **Sum** plus a **Carry** out.
  - We will compare logic minimization methods (K-Map, SOP) and show complete verification through simulation.

---

## 4. **Notation & Symbols**

| Symbol  | Meaning                                                                |
|---------|-----------------------------------------------------------------------|
| **A, B** | Primary 1-bit inputs to the adder                                    |
| **Cin** | Carry-in bit (used in Full Adder only)                                |
| **Sum** | 1-bit sum output                                                     |
| **Carry**, **Cout** | 1-bit carry output                                        |
| **1/0** | Logic High = 1 (5 V), Logic Low = 0 (0 V)                              |
| **AND** | Logical AND (denoted by • or ∧)                                       |
| **OR**  | Logical OR (denoted by + or ∨)                                        |
| **NOT** | Logical Inversion (denoted by ' or ¬)                                 |
| **XOR** | Exclusive OR (⊕)                                                      |
| **K-Map** | Karnaugh Map for Boolean simplification                             |
| **SOP**  | Sum of Products (OR of AND terms)                                    |

*(Feel free to rename or remove symbols not relevant to your design.)*

---

## 5. **Inputs & Truth Tables**

### 5.1 Half Adder

**Input Variables**
- A, B ∈ {0,1}

**Truth Table**

| A | B || Sum | Carry |
|---|---||-----|-------|
| 0 | 0 ||  0  |   0   |
| 0 | 1 ||  1  |   0   |
| 1 | 0 ||  1  |   0   |
| 1 | 1 ||  0  |   1   |

- The **Sum** bit is equivalent to A ⊕ B.
- The **Carry** bit is 1 only if both A and B are 1.

### 5.2 Full Adder

**Input Variables**
- A, B, Cin ∈ {0,1}

**Truth Table**

| A | B | Cin || Sum | Cout |
|---|---|-----||-----|------|
| 0 | 0 |  0  ||  0  |   0  |
| 0 | 0 |  1  ||  1  |   0  |
| 0 | 1 |  0  ||  1  |   0  |
| 0 | 1 |  1  ||  0  |   1  |
| 1 | 0 |  0  ||  1  |   0  |
| 1 | 0 |  1  ||  0  |   1  |
| 1 | 1 |  0  ||  0  |   1  |
| 1 | 1 |  1  ||  1  |   1  |

- The **Sum** is A ⊕ B ⊕ Cin.
- The **Cout** is (A·B) + ((A ⊕ B)·Cin).

---

## 6. **K-Map & SOP Reduction**

### 6.1 Half Adder K-Map

**Sum =**
\[
\begin{array}{c|c|c}
   & B=0 & B=1 \\ \hline
 A=0 & 0 & 1 \\
 A=1 & 1 & 0 \\
\end{array}
\]
- Grouping the 1’s → **Sum** = A'B + AB'  (i.e., A ⊕ B)

**Carry =**
\[
\begin{array}{c|c|c}
   & B=0 & B=1 \\ \hline
 A=0 & 0 & 0 \\
 A=1 & 0 & 1 \\
\end{array}
\]
- Single 1 in cell (A=1,B=1) → **Carry** = A·B

### 6.2 Full Adder K-Map (For Sum or Cout)

You can create 2D or 3D K-Maps for **Sum** and **Cout**. Because Full Adder has 3 inputs, you typically do a 2D K-Map with 4 rows × 2 columns (or 2 rows × 4 columns), or handle it in two smaller 2-bit K-Maps if you prefer.

**Final Results**
\[
\text{Sum} = A \oplus B \oplus C_\text{in}, \quad
C_\text{out} = (A \cdot B) + \bigl((A \oplus B) \cdot C_\text{in}\bigr)
\]
(Confirmed by K-Map or direct boolean expansion.)

---

## 7. **Gate-Level Implementation & Circuit Diagrams**

### 7.1 Half Adder (Basic Gates)

- **Sum** = A'B + AB'
- **Carry** = A·B

**Schematic Example** (using NOT, AND, OR):

```
         ┌─►[NOT]─► A'
A -------┤
         │        ┌─►[AND]─┐
         └────────┘         \
B ---------------┐           [OR] ---> SUM
                 └─►[NOT]─► B'      /
                          ┌─►[AND]─┘
                          └─►(A'B, AB')

Carry = A·B (direct AND gate)
```

*(Alternatively, you can show a single XOR gate for Sum if available.)*

### 7.2 Full Adder (Ripple version)

Basic approach:
1. **Half Adder 1**: S1 = A ⊕ B, C1 = A·B
2. **Half Adder 2**: Final Sum = S1 ⊕ Cin, C2 = S1·Cin
3. **Cout** = C1 + C2

**Schematic Example** (using two half adders + OR gate for carry):

```
      ┌───[HA1]─────► S1
A,B --│             \
      └───> C1       \
Cin ------[HA2]------(XOR)----> FINAL SUM
           └──> C2

Cout = C1 + C2  (OR)
```

*(Or use a single full-adder logic block that merges these internally.)*

---

## 8. **Full Signal & Timing Analysis**

1. **Node Equations**
   - For the Half Adder: node1 = A'·B, node2 = A·B', Sum = node1 + node2, Carry = A·B.
   - For the Full Adder: combine the partial sums/carries as shown above.

2. **Propagation Delays**
   - Each NOT or AND/OR gate has some nominal delay (e.g., 5–10 ns).
   - The **critical path** for the Half Adder’s Sum might be (NOT → AND → OR) ~ 25 ns total.
   - For the Full Adder, you may have two half-adder delays in series plus an OR gate for the final carry.

3. **Timing Diagram**
   - If A and B change simultaneously, observe Sum and Carry after the gate delays.
   - No “memory” behavior here, so after inputs settle, outputs quickly reflect the new sum/carry.

---

## 9. **LTSpice Simulation & Netlists**

### 9.1 Simulation Setup

- **Tool**: LTSpice (or Logisim, Multisim, etc.)
- **Transient Analysis**: 0 to 80 ns, step 1 ns (for example).
- **Input Stimulus**: Switch A and B at 20 ns intervals to cycle through all 4 input combos (0,0 → 0,1 → 1,0 → 1,1).

**Example Input Stimulus**
- `A`: 0 → 1 at 20 ns, 1 → 0 at 60 ns
- `B`: 0 → 1 at 40 ns, 1 → 0 at 80 ns

### 9.2 Example Netlist (Half Adder with basic gates)
```
* HALF ADDER Netlist
* (Uses NOT, AND, OR from your library)
.include LogicLibSymbols.inc

VIN_A A 0 PWL(0ns 0V 20ns 0V 20.1ns 5V 60ns 5V 60.1ns 0V 80ns 0V)
VIN_B B 0 PWL(0ns 0V 40ns 0V 40.1ns 5V 80ns 5V)

XU1 A vdd 0 A_INV INVERT
XU2 B vdd 0 B_INV INVERT
XU3 A_INV B vdd 0 N1 ANDx      ; node for A'·B
XU4 A B_INV vdd 0 N2 ANDx      ; node for A·B'
XU5 N1 N2 vdd 0 SUM ORx        ; Sum = (A'·B) + (A·B')
XU6 A B vdd 0 CARRY ANDx       ; Carry = A·B

Vdd vdd 0 5V
.tran 0 80n
.end
```

*(Adapt gate names, netlist lines, or library includes for your environment.)*

### 9.3 Waveform Analysis

| Time (ns) | A | B | Sum (HA) | Carry (HA) | Comments                                    |
|-----------|---|---|----------|------------|---------------------------------------------|
| 0–20      | 0 | 0 |    0     |     0      | No input transitions yet                   |
| 20–40     | 1 | 0 |    1     |     0      | A high, B low → sum=1, carry=0            |
| 40–60     | 1 | 1 |    0     |     1      | Both high → sum=0, carry=1                |
| 60–80     | 0 | 1 |    1     |     0      | A low, B high → sum=1, carry=0            |

Check that your measured waveforms match the truth table.

*(For Full Adder, add Cin transitions or do a 3-input pattern.)*

---

## 10. **Verification Checklist**

| Verification Step                      | Status (✔/❌) | Comments                                     |
|----------------------------------------|--------------|----------------------------------------------|
| **Truth Table (HA, FA)**              | ✔            | Matches derived outputs (Sum, Carry)         |
| **K-Map Simplifications**             | ✔            | Final expressions are minimal (A'B + AB', etc.) |
| **Gate-Level Implementation**          | ✔            | Schematic matches final Boolean expressions  |
| **Timing Consistency**                | ✔            | Output stable after ~25 ns, no hazards       |
| **LTSpice Simulation**                | ✔            | Waveforms validate all input combos          |
| **Propagation Delay within Specs**     | ✔            | Sum path < 30 ns, Carry path ~10 ns          |
| **No Missing or Redundant Gates**      | ✔            | Implementation is minimal and correct        |

---

## 11. **Troubleshooting & Design Iterations**

- **Common Issues**
  1. **Signal Inversion**: If Sum is inverted, check NOT gates or XOR usage.
  2. **Carry Stuck High/Low**: Typically from a miswired AND gate.
  3. **Redundant Gates**: Early designs may use extra gates; simplifying can reduce complexity.

- **Version History**
  - **v1**: Basic approach with 2 AND + OR + 2 NOT for Half Adder.
  - **v2**: Confirmed simulation, removed any redundant logic.
  - **v3**: Extended design to Full Adder, verified with 3-input waveforms.

---

## 12. **Conclusion**

- **Summary of Findings**:
  - The Half Adder and Full Adder circuits produce correct Sum and Carry bits for all input combinations.
  - Simple K-Map or direct Boolean expansions yield the minimal SOP forms.
  - Simulation waveforms confirm the truth tables and correct timing behavior.

- **Lessons Learned**:
  1. Boolean algebra (or K-Map) is straightforward for 1-bit adders.
  2. Thorough simulation ensures no wiring errors.
  3. XOR is a concise way to represent the Sum in both HA and FA.

- **Future Improvements**:
  - **NAND-only** implementation to reduce parts count (universal gate).
  - **Carry-Lookahead** approach for multi-bit additions to minimize delay.
  - Incorporate the Full Adder into a ripple-carry or 4-bit ALU module.

---

## 13. **Glossary & Cross-References**

| Term         | Definition                                                           |
|--------------|-----------------------------------------------------------------------|
| **Half Adder**  | 1-bit adder with 2 inputs (A,B) and 2 outputs (Sum, Carry).       |
| **Full Adder**  | 1-bit adder with 3 inputs (A,B,Cin) and 2 outputs (Sum, Cout).    |
| **Boolean Expression** | Algebraic form using AND, OR, NOT, XOR.                    |
| **Karnaugh Map**       | A grid-based simplification method for Boolean expressions. |
| **Sum of Products (SOP)** | OR of multiple AND terms.                                |
| **XOR**         | Exclusive OR, 1 if inputs differ, 0 if same.                      |
| **Propagation Delay**  | Time from an input change to the stable output.            |
| **Verification Checklist** | A structured set of pass/fail checks for correctness.   |

*(If your report references sections among each other, add “See Section 6.2” or “See Gate-Level Implementation.”)*

---

# **Sample Final Deliverable**

Using the above **Half Adder & Full Adder** documentation template:

1. You define your **objectives** and show the **truth tables**.
2. Apply **K-Map** or direct Boolean expansions for **Sum** and **Carry**.
3. Provide **gate-level schematics** (or netlists for simulation).
4. Run **LTSpice** or **Logisim** to confirm correct waveforms for all input combinations.
5. Fill in your **verification checklist** (✔ or ❌) to highlight success/failure.
6. Summarize your findings and mention any **future expansions** (e.g., multi-bit adders, CPU ALU).

This ensures a **clear, consistent** report that follows the same style you used for other digital modules—just **focused specifically on half and full adders**.