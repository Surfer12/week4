## 7. Classroom CPU Integration

### 7.1 Major Components

1. **Instruction Memory**
   - Holds program instructions.
   - Program Counter (PC) addresses each instruction sequentially.

2. **Data Memory**
   - Stores data that can be loaded into or stored from CPU registers.

3. **Register File**
   - Small set of registers (e.g., R0, R1, R2, R3).
   - Two read ports, one write port per cycle.

4. **ALU**
   - Contains or uses the **Full Adder** for add/sub.
   - Control signal (e.g., `Add0sub1`) selects addition vs. subtraction.

5. **Control Unit**
   - Decodes opcode bits to generate signals for ALU, memory, register writes, etc.

### 7.2 Instruction Set Overview

- **ADD** (`00` in 2-bit opcode):
  - \(\text{Rdest} = \text{Rdest} + \text{Rsrc}\); sets ALU to add mode.
- **SUB** (`01`):
  - \(\text{Rdest} = \text{Rdest} - \text{Rsrc}\); sets ALU to subtract mode.
- **LOAD** (`10`):
  - \(\text{Rdest} = \text{Memory}[\text{addr}]\).
- **STORE** (`11`):
  - \(\text{Memory}[\text{addr}] = \text{Rsrc}\).

### 7.3 Program Flow Example

1. **LOAD R0, 0**
   - R0 ← DataMem[0]

2. **LOAD R1, 1**
   - R1 ← DataMem[1]

3. **ADD R3, R1**
   - R3 ← R3 + R1

4. **STORE R3, 3**
   - DataMem[3] ← R3

The PC increments each clock cycle to fetch these instructions in sequence.

### 7.4 High-Level Block Diagram

```
┌─────────────────────┐        ┌───────────────────┐
│   Instruction Mem    │        │    Data Memory    │
│(Stores opcodes/data) │        │ (4 or 8 locations)│
└────────┬─────────────┘        └─────────┬─────────┘
         │                                │
         ▼                                ▼
    [ Control Unit ] ───→ (WrMem, AddSub, WrReg, etc.)
         │                        │
         ▼                        │
    ┌───────────┐                │
    │RegisterFile│<───────────────┘
    └───────────┘
         │
         ▼
        [ALU]
     (Full Adder core)
         │
         └── (Result) → back to RegisterFile or DataMem (STORE)
```

### 7.5 Verification Checklist (Minimal CPU)
- **[ ]** *Instruction Fetch/Decode:* Ensure correct mapping of PC to instruction memory.
- **[ ]** *Register File Operation:* Confirm read/write signals and register addressing.
- **[ ]** *ALU (Add/Sub):* Validate with Full Adder logic.
- **[ ]** *Memory Access:* LOAD reads from correct address, STORE writes to correct location.
- **[ ]** *Program Execution:* Step through instructions, check final register/memory states.

---

## 8. Simulation & Results

### 8.1 Simulation Setup

- **Tool**: LTSpice (or Logisim, etc.)
- **Transient Analysis**: Example 0–100 ns.
- **Input Stimulus** (Half Adder or Full Adder):
  - A toggles at 20 ns, B toggles at 40 ns, etc.
- **Digital Plot**: Set threshold \(\approx 2.5\text{V}\) to view waveforms as 1/0.

### 8.2 Example Netlists

**Half Adder (Basic Gates)**
```
* Half Adder Netlist
XU1 A vdd 0 A_INV INVERT
XU2 B vdd 0 B_INV INVERT
XU3 A_INV B vdd 0 N1 ANDx      ; node = A'·B
XU4 A B_INV vdd 0 N2 ANDx      ; node = A·B'
XU5 N1 N2 vdd 0 SUM ORx        ; Sum = (A'B + AB')
XU6 A B vdd 0 CARRY ANDx       ; Carry = A·B

Vdd vdd 0 5V
.tran 0 80n
...
```

### 8.3 Waveform Analysis

**Timing Table Example** (Half Adder)

| Time   | A | B | Sum | Carry | Notes                     |
|--------|---|---|-----|-------|---------------------------|
| 0–20ns | 0 | 0 |  0  |   0   | Initial state             |
| 20–40ns| 1 | 0 |  1  |   0   | A → 1                     |
| 40–60ns| 1 | 1 |  0  |   1   | B → 1, carry generated    |
| 60–80ns| 0 | 1 |  1  |   0   | A → 0                     |
| 80–100ns|0|0 |  0  |   0   | Return to initial state    |

### 8.4 Troubleshooting Notes

- **Outputs Not Reaching 5V**: Check gate fan-out or loads.
- **Glitches**: Possibly uninitialized inputs or overlapping changes.
- **Mismatch**: Re-check netlist wiring vs. circuit diagram.
- **Timing**: Confirm gate delay is sufficient for stable waveforms before next change.

---

## 9. Overall Verification Checklists

This section summarizes critical checks across all stages:

1. **Notation & Symbols**
   - [ ] Confirm consistent use of `+` for OR, `·` for AND, `'` for NOT, etc.

2. **Half Adder**
   - [ ] Truth table matches \((A \oplus B)\) and \((A \cdot B)\).
   - [ ] Gate-level diagram tested in simulation.

3. **Full Adder**
   - [ ] Extended truth table with Cin, verified K-Map.
   - [ ] Final Sum & Cout signals correct in simulation.

4. **CPU Integration**
   - [ ] Instruction memory addresses stable each clock cycle.
   - [ ] Control lines: Add/Sub, WrMem, WrReg are correct per opcode.
   - [ ] Register file read/write tested, no conflicts.
   - [ ] LOAD/STORE verified with sample addresses.

5. **Simulation**
   - [ ] Timing diagrams show correct transitions, no spurious pulses.
   - [ ] Final waveforms match expected outputs from logic.

---


## 10. Conclusion

By merging the Half Adder and Full Adder designs with a simple CPU architecture, we demonstrate:

- **Logical Foundations**: The half adder and full adder circuits (with truth tables and K-Maps) serve as the core of any binary arithmetic design.
- **Implementation Versatility**: Basic gates or NAND-only approaches can yield the same results; trade-offs revolve around gate count, speed, and uniformity.
- **CPU Integration**: Combining an ALU, register file, instruction memory, and control logic creates a minimal yet functional system capable of executing simple instructions (ADD, SUB, LOAD, STORE).
- **Verification**: Thorough checklists, simulation waveforms, and K-Map validations ensure correctness at each stage.

This unified documentation—with removed duplications and expanded sections—should guide future labs or projects requiring robust digital logic designs and CPU concepts.