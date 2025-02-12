# Mary Had a Little Lamb FSM Implementation

## Overview
This is a Finite State Machine (FSM) implementation that plays the melody of "Mary Had a Little Lamb" using digital logic. The circuit uses 5 states to control 8 distinct musical tasks, outputting notes encoded in 3 bits.

## Circuit Components

### Main FSM (MaryLambFSM.asc)
- **Input Signals:**
  - CLK: Clock input for timing
  - RST: Reset signal (active high)
  - START: Signal to begin playing the song

- **Output Signals:**
  - NOTE[1:0]: 2-bit output encoding the current note
  - STATE[2:0]: Current state of the FSM
  - DONE: Indicates when the song is complete

### Note Encoding
```
0: Rest (no note)
3: E note
2: D note
1: C note
```

### State Organization (5 States)
```
000: Initial/Reset
001: Playing "Ma-"
010: Playing "ry"
011: Playing "had"
100: Playing "a"
```

### Task Sequence (8 Tasks)
```
1. Play E (Mary)
2. Play D (had)
3. Play C (a)
4. Play D (lit-)
5. Play E (tle)
6. Play Rest
7. Repeat sequence
8. End song
```

## Musical Implementation

### Note Sequence
The FSM implements the melody "Mary Had a Little Lamb":
```
E(3) D(2) C(1) D(2) | E(3) E(3) E(3) rest(0) | D(2) D(2) D(2) rest(0) | E(3) E(3) E(3) rest(0)
```

### State-Task Mapping
Each state controls multiple tasks:
- State 001 (Mary): Tasks 1, 2 (E, D notes)
- State 010 (Had): Tasks 3, 4 (C, D notes)
- State 011 (A): Tasks 5, 6 (E, Rest)
- State 100 (Little): Tasks 7, 8 (Repeat/End)

## Circuit Architecture

### State Storage
- Uses 3-bit state encoding (for 5 states)
- Implements task control logic for 8 tasks
- State transitions trigger appropriate task sequences

### Task Control
- Each state can trigger multiple tasks
- Tasks are sequenced using combinational logic
- Note outputs (0-3) are generated based on current task

## Test Circuit (MaryLambFSMTest.asc)

### Test Configuration
- Clock frequency: 10Hz (100ms period)
- Reset: Initial 10ms high pulse
- Start trigger: 20ms after reset
- Simulation time: 2 seconds

### Test Signals
```
CLK: PULSE(0 5 0 1n 1n 50m 100m)    // 10Hz clock
RST: PULSE(5 0 0 1n 1n 10m 1)       // Initial reset pulse
START: PULSE(0 5 20m 1n 1n 1 1)     // Delayed start signal
```

## Musical Sequence
The FSM plays the first phrase of "Mary Had a Little Lamb":
```
E(3) D(2) C(1) D(2) | E(3) E(3) E(3) rest(0) | D(2) D(2) D(2) rest(0) | E(3) E(3) E(3) rest(0)
```

## Implementation Details

### State Machine Design
- Uses 3-bit state encoding for states
- Uses 2-bit encoding for notes (0-3)
- Implements sequential logic using Register components
- Includes combinational logic for state transitions using MUX4 components
- Automatically cycles through notes at clock rate
- Returns to initial state upon reset

### Circuit Architecture
1. State Storage:
   - Four Register components (X1-X4) for state maintenance
   - Synchronous operation with clock input

2. State Transitions:
   - MUX4 components (X5-X8) for next state logic
   - Conditional branching based on current state and inputs

## Usage Instructions

1. Reset the circuit by asserting RST high
2. Wait for RST to go low
3. Assert START high to begin the melody
4. Monitor NOTE[1:0] for current note being played (0-3)
5. DONE signal indicates completion of melody

## Testing and Verification
The test circuit provides a complete environment for verifying FSM operation:
- Automated reset sequence
- Delayed start trigger
- Full melody cycle verification
- State transition monitoring

## Timing Diagram
```
CLK   _|‾|_|‾|_|‾|_|‾|_|‾|_|‾|_|‾|_|‾|_
RST   ‾‾|_________________________
START ______|‾|________________________
NOTE  XXXX|3|2|1|2|3|3|3|0|2|2|2|0|...
```

## Notes
- Each state transition occurs on rising clock edge
- Reset returns FSM to initial state regardless of current state
- Rest states (0) are inserted between musical phrases
- DONE signal remains high until reset
- Note values are now 0-3 instead of binary patterns

## Future Enhancements
Possible improvements to consider:
1. Variable tempo control
2. Multiple melody support
3. Repeat functionality
4. Dynamic note duration