# Mary Had a Little Lamb FSM Implementation

## Design Overview
This Finite State Machine (FSM) plays "Mary Had a Little Lamb" using:
- 5 states (requiring 3 flip-flops for state storage)
- 8 tasks (controlled by state transitions)
- 2-bit note encoding (0-3)

## Hardware Requirements

### Understanding the 3-Bit Requirement
1. **State Space Analysis**:
   - We need to represent 5 distinct states
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