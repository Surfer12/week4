;================================================
;  Mary Had a Little Lamb FSM Implementation Report
;  Version 1.0 - 2024-02-13
;  Author: [Student Name]
;================================================

[DOCUMENT_METADATA]
title=Mary_Had_a_Little_Lamb_FSM_Report
version=1.0
date=2024-02-13
assignment=Digital Logic FSM Implementation

;----------------------------------
; SECTION: Notation Key and Symbols
;----------------------------------
[Notation Key]
SymbolNotation="""
- State[2:0]: 3-bit state representation
- NOTE[2:0]: 3-bit musical note encoding
- CLK: Clock signal
- RST: Active-high reset
- START: Active-high start signal
- DONE: Active-high completion signal
"""

UnitsNotation="""
- Voltage: 5V logic high, 0V logic low
- Time: Clock period = 100ms (10Hz)
- Delays: Rise/Fall times = 1ns
"""

NamingConventions="""
- Q[2:0]: Current state bits
- D[2:0]: Next state bits
- NOTE encoding: 3=E, 2=D, 1=C, 0=Rest
"""

; Verification
verify_symbols_defined    = ✔  ; All signals clearly defined
verify_units_consistent   = ✔  ; Consistent 5V logic, timing in ns/ms
verify_naming_consistency = ✔  ; Consistent bus notation and signal names

;----------------------------------
; SECTION: Objectives / Problem Statement
;----------------------------------
[Objectives]
LabGoal="""
Implement a Finite State Machine (FSM) that sequences through the notes of
"Mary Had a Little Lamb" using digital logic in LTspice, demonstrating
state transitions, musical note encoding, and proper reset/start behavior.
"""

KeyQuestions="""
1. How to represent musical notes in digital logic?
2. How to sequence through states correctly?
3. How to handle reset and start conditions?
4. How to indicate completion of the sequence?
"""

;----------------------------------
; SECTION: Theoretical Background
;----------------------------------
[Theoretical Background]
TheoryConcepts="""
1. FSM State Encoding:
   - 8 states (3 bits) for complete sequence
   - Binary state progression: 000->001->010->011->100->101->110->111

2. Musical Note Encoding:
   - E = 3 (binary 011)
   - D = 2 (binary 010)
   - C = 1 (binary 001)
   - Rest = 0 (binary 000)

3. State Transition Logic:
   - Next state determined by current state
   - Reset forces state to 000
   - Start initiates sequence from 000->001
   - Final state (111) indicates completion
"""

;----------------------------------
; SECTION: Materials and Inputs
;----------------------------------
[Materials_Inputs]
EquipmentList="""
- LTspice XVII
- Digital components:
  * D flip-flops (dff)
  * Behavioral voltage sources
  * Pull-down resistors
"""

InputVariables="""
- CLK: 10Hz clock (100ms period)
- RST: Active high pulse at start
- START: Triggered after reset
"""

OperatingConditions="""
- VDD = 5V
- Ground = 0V
- Rise/Fall times = 1ns
- Clock period = 100ms
"""

;----------------------------------
; SECTION: Methodology / Procedure
;----------------------------------
[Methodology]
Implementation="""
1. State Storage:
   - Three D flip-flops for 3-bit state
   - Reset input for initialization
   - Clock-synchronized state transitions

2. Next State Logic:
   - Behavioral sources (B elements) for D[2:0]
   - State sequence implementation
   - Reset and start condition handling

3. Output Generation:
   - NOTE[2:0] encoding from current state
   - STATE[2:0] direct from flip-flops
   - DONE signal when reaching final state

4. Test Circuit:
   - Clock, reset, and start signal generation
   - Monitoring of outputs
   - 2-second simulation time
"""

;----------------------------------
; SECTION: Files and Structure
;----------------------------------
[Implementation_Files]
Files="""
1. MaryLambFSM_new.asy
   - Symbol file defining interface
   - 6 pins: CLK, RST, START, NOTE[2:0], STATE[2:0], DONE

2. MaryLambFSM_new.cir
   - Subcircuit definition
   - State storage using dff components
   - Next state logic using behavioral sources
   - Output generation logic

3. MaryLambFSMTest_new.asc
   - Test bench schematic
   - Input signal generation
   - Simulation commands
"""

;----------------------------------
; SECTION: Results and Discussion
;----------------------------------
[Results_Discussion]
KeyFindings="""
1. State Transitions:
   - Proper progression through note sequence
   - Correct musical note encoding
   - Reliable reset and start behavior

2. Timing:
   - 100ms per note (10Hz clock)
   - Clean transitions between states
   - Proper completion detection

3. Implementation Benefits:
   - Use of existing dff components
   - Clear behavioral descriptions
   - Stable operation with pull-down resistors
"""

;----------------------------------
; SECTION: Conclusion
;----------------------------------
[Conclusion]
SummaryOfLab="""
Successfully implemented a digital FSM for the "Mary Had a Little Lamb"
sequence using LTspice. The design demonstrates proper state transitions,
musical note encoding, and control signal handling. The implementation
leverages existing digital components and behavioral sources for clarity
and reliability.
"""

FutureWork="""
1. Add tempo control
2. Implement multiple song sequences
3. Add output buffering for driving external components
4. Consider adding error detection
"""

;----------------------------------
; SECTION: Appendix
;----------------------------------
[Appendix]
CircuitFiles="""
All source files located in src/logic-library/new-circuits/:
- MaryLambFSM_new.asy
- MaryLambFSM_new.cir
- MaryLambFSMTest_new.asc
"""

SimulationNotes="""
To simulate:
1. Open MaryLambFSMTest_new.asc in LTspice
2. Run transient analysis (.tran 2)
3. Monitor NOTE[2:0], STATE[2:0], and DONE signals
4. Verify state progression matches expected sequence
"""

# Mary Had a Little Lamb FSM Implementation

## Design Overview
This Finite State Machine (FSM) plays "Mary Had a Little Lamb" using:
- 5 states (requiring 3 flip-flops for state storage)
- 8 tasks (controlled by state transitions)
- 2-bit note encoding (0-3)

## Hardware Requirements

### Understanding the 3-Bit Requirement
1. **State Space Analysis**:
   - For the 'Mary Had a Little Lamb' melody, we've determined that the FSM needs to differentiate between at least 5 key stages or phrases in the musical sequence.
   - Binary counting requires: 2² = 4 states (not enough)
   - Therefore, we need 2³ = 8 states (sufficient)
   - Using 3 bits gives us states 000 through 111

2. **Flip-Flop to Bit Mapping**:
   - Each bit requires one D flip-flop
   - Bit 2 (MSB) → FF2
   - Bit 1      → FF1
   - Bit 0 (LSB) → FF0

3. **State Encoding Efficiency**:
   ```
   3 bits can represent 8 states (000-111)
   We use 5 states:
   000 - Initial/Reset
   001 - State 1
   010 - State 2
   011 - State 3
   100 - State 4
   (101-111 unused)
   ```

### Flip-Flops
- **State Storage**: 3 D flip-flops (for 5 states)
- **Total**: 3 bits of state storage
- **Clock Requirements**: All flip-flops share same clock
- **Reset Behavior**: Synchronous reset to 000

### Bit Transitions
```
FF2 FF1 FF0  State Name
0   0   0    Initial/Reset
0   0   1    Playing "Ma-"
0   1   0    Playing "ry"
0   1   1    Playing "had"
1   0   0    Playing "a"
```

### Note Encoding (2 bits)
```
0 (00): Rest (no note)
1 (01): C note
2 (10): D note
3 (11): E note
```

## State and Task Organization

### States (5 total)
```
000: Initial/Reset
001: Playing "Ma-"
010: Playing "ry"
011: Playing "had"
100: Playing "a"
```

### Tasks (8 total)
1. Play E (3) - First note of "Mary"
2. Play D (2) - Second note
3. Play C (1) - Third note
4. Play D (2) - Fourth note
5. Play E (3) - Fifth note
6. Play Rest (0) - Pause between phrases
7. Repeat sequence
8. End song

### State-Task Mapping
Each state controls specific tasks:
```
State 001 → Tasks 1-2: "Ma-" (E, D notes)
State 010 → Tasks 3-4: "ry" (C, D notes)
State 011 → Tasks 5-6: "had" (E, Rest)
State 100 → Tasks 7-8: "a" (Repeat/End)
```

## Circuit Implementation

### Input Signals
- CLK: Clock input for synchronous operation
- RST: Active-high reset
- START: Begin song sequence

### Output Signals
- NOTE[1:0]: Current note value (0-3)
- STATE[2:0]: Current state
- DONE: Song completion indicator

### Components Used
1. Storage Elements:
   - 3 D flip-flops for state storage
   - Register components for synchronous operation

2. Combinational Logic:
   - MUX4 components for state transitions
   - Decoders for note output generation

## Musical Sequence
The complete sequence plays:
```
E(3) D(2) C(1) D(2) | E(3) E(3) E(3) rest(0) | D(2) D(2) D(2) rest(0) | E(3) E(3) E(3) rest(0)
```

## Operation

### Reset Behavior
1. RST high: Sets state to 000
2. All outputs cleared
3. Ready for START signal

### Start Sequence
1. START high: Begins at state 001
2. Progresses through states/tasks
3. Cycles through notes as defined

### State Transitions
- Occur on rising clock edge
- Follow task completion order
- Include rest states between phrases

## Testing

### Test Configuration
```
Clock: 10Hz (100ms period)
Reset: Initial 10ms pulse
Start: 20ms after reset
```

### Verification Points
1. State transitions
2. Note sequence accuracy
3. Rest insertion
4. Repeat/End behavior

## Design Decisions

### Why 5 States?
- Matches musical phrases
- Minimizes state storage (3 bits)
- Allows task grouping

### Why 8 Tasks?
- Covers all note transitions
- Includes necessary rests
- Handles sequence control

### Flip-Flop Usage
- 3 bits optimal for 5 states
- Synchronous updates
- Glitch-free operation

## Future Improvements
1. Variable tempo control
2. Multiple song support
3. Adjustable note duration
4. State compression optimization

## Flip-Flop Implementation Details

### Transition Logic
1. **Clock Edge Behavior**:
   - All state changes occur on rising clock edge
   - Ensures synchronous operation
   - Prevents race conditions

2. **Next State Logic**:
   ```
   Current State → Next State (Binary)
   000 → 001 (when START is high)
   001 → 010 (after task completion)
   010 → 011 (after task completion)
   011 → 100 (after task completion)
   100 → 001 (for repeat) or 000 (for end)
   ```

3. **Flip-Flop Input Equations**:
   ```
   FF2_next = (current_state == 011) & !end_sequence
   FF1_next = (current_state == 000 & START) |
              (current_state == 001) |
              (current_state == 010)
   FF0_next = (current_state == 000 & START) |
              (current_state == 010) |
              (current_state == 011)
   ```

### Timing Considerations
1. **Setup and Hold Times**:
   - Data must be stable before clock edge
   - State changes must complete within clock period
   - Minimum clock period determined by longest path

2. **Critical Paths**:
   - State decoder → Next state logic → FF inputs
   - Note output logic → External circuitry
   - Reset logic → All FFs

3. **Clock Requirements**:
   - Period ≥ (longest path delay + setup time)
   - Must meet minimum pulse width
   - Equal distribution to all FFs