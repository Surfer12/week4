# Half Adder Design Documentation

**Version:** 11.0
**Date:** 2024-02-XX
**Author:** Ryan Oates
**Assignment:** Lab_Two

## 1. Introduction

This document details the design, implementation, and verification of a Half Adder (HA) digital circuit. A half adder takes two 1-bit inputs (A and B) and produces a 1-bit sum (Sum) and a 1-bit carry (Carry).

## 2. Notation and Symbols

| Symbol        | Meaning                     |
|---------------|-----------------------------|
| `∧` or `·`    | AND                         |
| `∨` or `+`    | OR                          |
| `¬` or `!`   | NOT                         |
| `⊕`          | XOR                         |
| `⊼`         | NAND                        |
| `↓`         | NOR                         |
| `1` (HIGH)   | 5V (Logic High)          |
| `0` (LOW)    | 0V (Logic Low)           |
| `─`         | Wire                         |
| `├`         | Junction                    |
| `└`, `┌`       | Turn                        |
| `►`         | Signal Flow                  |

## 3. Inputs and Truth Table

**Inputs:**
* `A`: 1-bit input
* `B`: 1-bit input

**Truth Table:**

| A | B | Sum | Carry |
|---|---|-----|-------|
| 0 | 0 |  0  |   0   |
| 0 | 1 |  1  |   0   |
| 1 | 0 |  1  |   0   |
| 1 | 1 |  0  |   1   |

## 4. Boolean Expressions

* **Sum:**  S = A ⊕ B  (which is equivalent to  S = A'B + AB')
* **Carry:** C = A · B

## 5. K-Map and SOP Reduction

**K-Map for Sum:**
```
       B
    0   1
A  -------
0 |  0   1
1 |  1   0
```

Simplified Expression: Sum = A'B + AB'

**K-Map for Carry:**
```
     B
    0   1
A -------
0 |  0   0
1 |  0   1
```
Simplified Expression: Carry = AB

## 6. Gate-Level Implementation

### 6.1 Basic Implementation (AND, OR, NOT)
```
 A ─┬─► [NOT] ─► A' ─► [AND] ─┐
   │                          │
   ├──────────► [AND] ────┐   ├─► [OR] ─► SUM
   │                      │   │
 B ─┼─► [NOT] ─► B' ─► [AND] ─┘
   │
   └────────────► [AND] ─► CARRY
```

### Logic Operations:
Sum = OR( AND(A', B), AND(A, B'))
Carry= AND(A, B)

### 6.2. NAND Gate Implementation
```
         ┌─► [NAND1] ─┐
 A ─┬───►┤            ├─► [NAND3] ─► [OR] ─► SUM
    │    └─► [NAND2] ─┘
    │
    └────────► [AND] ───────────────► CARRY
       (A·B)
```
Logic Operations:
    NOT(A) = A NAND A
    AND(A,B) = (A NAND B) NAND (A NAND B)
    OR(A,B) = (A NAND A) NAND (B NAND B)
    XOR(A,B) = ((A NAND B) NAND A) NAND ((A NAND B) NAND B)

## 7. LTSpice Simulation
* Netlist example can be found in `sim/ltspice`.

## 8. Verification
Refer to `half_adder_verification.md`.
