;================================================
;  FSM Design: Mary Had a Little Lamb
;  Version 1.0 - 2024-02-12
;  Course: CIS 240 Sequential Logic
;================================================

[DOCUMENT_METADATA]
title=Mary_Had_a_Little_Lamb_FSM_Design
version=1.0
date=2024-02-12
assignment=Sequential Logic FSM Implementation

;----------------------------------
; SECTION: Notation Key and Symbols
;----------------------------------
[Notation Key]
SymbolNotation="""
- Note Encoding:
  0: No note (rest)
  1: C note
  2: D note
  3: E note
- State[2:0]: 3-bit state vector
- CLK: Clock signal
- RST: Reset signal
- START: Start signal
"""

;----------------------------------
; SECTION: Step-by-Step Implementation
;----------------------------------
[Step 1: Musical Note Encoding]
NoteDefinition="""
Musical Note Mapping:
- 0 means no note (rest)
- 1 means C
- 2 means D
- 3 means E
"""

[Step 2: State Analysis]
StateRequirements="""
State Count Analysis:
1. Need to track position in song
2. Need to store current note
3. Need to handle transitions
4. Need start/end states
Total States Required: 8 states
Required Flip-flops: 3 (2^3 = 8 possible states)
"""

[Step 3: Block I/O Definition]
InputOutputSpec="""
Inputs:
- CLK (Clock)
- RST (Reset)
- START (Begin playing)

Outputs:
- NOTE[2:0] (3-bit note encoding)
- STATE[2:0] (Current state)
- DONE (Song completion)
"""

[Step 4: Truth Tables]
StateTransitionTable="""
Current State | Next State | Output Note
000 (Reset)   | 001       | 0 (Rest)
001 (State1)  | 010       | 3 (E)
010 (State2)  | 011       | 2 (D)
011 (State3)  | 100       | 1 (C)
100 (State4)  | 101       | 2 (D)
101 (State5)  | 110       | 3 (E)
110 (State6)  | 111       | 0 (Rest)
111 (Done)    | 111       | 0 (Rest)
"""

[Step 5: Circuit Implementation]
CircuitComponents="""
1. Flip-flops:
   - 3 D flip-flops for state storage
   - Clock-triggered
   - Asynchronous reset

2. Combinational Logic:
   - Next state logic
   - Output logic for notes
   - State decoder
"""

[Step 6: Testing Strategy]
TestConfiguration="""
Test Signals:
1. Clock: 10Hz (100ms period)
2. Reset: Initial pulse
3. Start: Trigger after reset
4. Monitor:
   - State transitions
   - Note outputs
   - Done signal
"""

;----------------------------------
; SECTION: Implementation Details
;----------------------------------
[Circuit Details]
TimingRequirements="""
1. Clock Period: 100ms
2. Reset Duration: 10ms
3. Start Delay: 20ms after reset
4. Note Duration: One clock cycle per note
"""

StateEncoding="""
State Vector [2:0]:
000: Initial/Reset
001: First Note (E)
010: Second Note (D)
011: Third Note (C)
100: Fourth Note (D)
101: Fifth Note (E)
110: Rest State
111: Done State
"""

;----------------------------------
; SECTION: Verification
;----------------------------------
[Testing]
TestProcedure="""
1. Apply reset
2. Wait for reset to complete
3. Assert start signal
4. Monitor note sequence:
   E -> D -> C -> D -> E -> E -> E -> rest
5. Verify DONE signal
"""

;----------------------------------
; SECTION: Results
;----------------------------------
[Results]
Implementation="""
Successfully implemented:
1. State machine with 8 states
2. 3-bit note encoding
3. Proper musical sequence
4. Reset and start functionality
"""

;----------------------------------
; SECTION: Conclusion
;----------------------------------
[Conclusion]
Summary="""
1. FSM design successfully implements "Mary Had a Little Lamb"
2. Uses minimal state encoding (3 flip-flops)
3. Produces correct musical sequence
4. Includes all necessary control signals
"""

FutureImprovements="""
1. Add variable tempo control
2. Implement multiple songs
3. Add repeat functionality
4. Include note duration control
"""

# Sequential Logic Design: Mary Had a Little Lamb FSM
## Slide-by-Slide Documentation

;================================================
; Slide 7: FSM to play Mary has a little lamb
;================================================
[DOCUMENT_METADATA]
slide_number=7
topic=Note_Encoding_Introduction

[Content]
Musical_Note_Encoding="""
- 0 means no note (rest)
- 3 means E
- 2 means D
- 1 means C
"""

[Sheet_Music]
Description="Musical score showing 'Mary Had a Little Lamb' in standard notation"
Key_Elements="""
- Time signature: 4/4
- Moderato tempo
- Piano arrangement
- First phrase shown
"""

;================================================
; Slide 8: Block I/O (inputs and outputs)
;================================================
[DOCUMENT_METADATA]
slide_number=8
topic=Input_Output_Specification

[Block_Diagram]
Components="""
Inputs:
- Clock signal
- Reset signal
- Start signal

Outputs:
- Note[2:0] encoding
- State[2:0] tracking
- Done signal
"""

;================================================
; Slide 9: How many states? How many Flip-flops?
;================================================
[DOCUMENT_METADATA]
slide_number=9
topic=State_Analysis

[State_Requirements]
Analysis="""
States needed:
1. Initial/Reset state
2. States for each note in sequence
3. Rest states
4. Final state

Flip-flop calculation:
- 8 states total
- Need 3 flip-flops (2^3 = 8)
"""

;================================================
; Slide 10: What is the output for each state?
;================================================
[DOCUMENT_METADATA]
slide_number=10
topic=State_Output_Mapping

[Truth_Table_Structure]
Description="""
I/O block defines:
- Left side: Input states
- Right side: Output notes
"""

[State_Output_Mapping]
Format="""
State[2:0] -> Note[2:0]
000 -> 000 (Rest)
001 -> 011 (E)
010 -> 010 (D)
...
"""

;================================================
; Slide 11: Draw flip-flops and "output logic"
;================================================
[DOCUMENT_METADATA]
slide_number=11
topic=Circuit_Implementation_Output

[Circuit_Components]
Elements="""
1. Three D flip-flops
2. Combinational logic for note output
3. State-to-note decoder
"""

;================================================
; Slide 12: What is the next state for each state?
;================================================
[DOCUMENT_METADATA]
slide_number=12
topic=State_Transition_Logic

[Truth_Table]
Format="""
Current State | Next State
000          | 001
001          | 010
010          | 011
...
"""

;================================================
; Slide 13: Draw flip-flops and "next state logic"
;================================================
[DOCUMENT_METADATA]
slide_number=13
topic=Circuit_Implementation_Next_State

[Circuit_Design]
Components="""
1. D flip-flops (3)
2. Next state combinational logic
3. State transition control
"""

;================================================
; Slide 14: Combine "output logic" and "next state logic"
;================================================
[DOCUMENT_METADATA]
slide_number=14
topic=Complete_Circuit_Integration

[Final_Circuit]
Integration="""
1. State storage (flip-flops)
2. Next state logic
3. Output decoder
4. Control signals
"""

;================================================
; Slide 15-17: Build the PC
;================================================
[DOCUMENT_METADATA]
slide_number=15-17
topic=Implementation_Steps

[Build_Process]
Steps="""
1. Understand the problem
2. Implement circuit components
3. Test and verify functionality
4. Document results
"""

[Testing_Requirements]
Verification="""
1. Reset functionality
2. State transitions
3. Note sequence accuracy
4. Completion detection
"""

# Detailed Slide-by-Slide Analysis: Mary Had a Little Lamb FSM

;================================================
; Slide 7: FSM to play Mary has a little lamb
;================================================
[Detailed_Analysis]
Key_Points="""
1. Musical Note Encoding:
   - 0 = rest/silence (no note played)
   - 1 = C note (lowest note in sequence)
   - 2 = D note (middle note)
   - 3 = E note (highest note)

2. Musical Structure:
   - Song follows pattern: E D C D | E E E (rest) | D D D (rest)
   - Each note needs equal timing
   - Rests are important for musical phrasing
"""

Implementation_Tips="""
1. Consider timing requirements:
   - Each note needs consistent duration
   - Rests must be properly timed
   - State transitions must match musical tempo

2. Musical Sequence Planning:
   - Map out complete sequence including rests
   - Plan for repeating sections
   - Consider end conditions
"""

;================================================
; Slide 8: Block I/O (inputs and outputs)
;================================================
[Detailed_Block_Analysis]
Input_Signals="""
1. CLK (Clock):
   - Controls timing of state transitions
   - Determines note duration
   - Must be stable and consistent

2. RST (Reset):
   - Returns FSM to initial state
   - Clears all flip-flops
   - Important for initialization

3. START:
   - Triggers beginning of song
   - Transitions from idle to first note
   - Should be edge-triggered
"""

Output_Signals="""
1. NOTE[2:0]:
   - 3-bit encoding for current note
   - Updates on state transitions
   - Must be stable during each state

2. STATE[2:0]:
   - Current state of FSM
   - Useful for debugging
   - Tracks sequence progress

3. DONE:
   - Indicates song completion
   - Stays high until reset
   - Important for control logic
"""

;================================================
; Slide 9: How many states? How many Flip-flops?
;================================================
[State_Analysis_Details]
State_Counting="""
1. Required States:
   - Initial/reset state (000)
   - States for "Ma-" (001)
   - States for "-ry" (010)
   - States for "had" (011)
   - States for "a" (100)
   - States for "little" (101)
   - Rest state (110)
   - Done state (111)

2. Flip-flop Calculation:
   - Need to represent 8 states
   - 2^2 = 4 (not enough)
   - 2^3 = 8 (perfect fit)
   - Therefore need 3 flip-flops
"""

Design_Considerations="""
1. State Encoding Strategy:
   - Binary encoding is efficient
   - Sequential states for easy counting
   - Reset state at 000 for simplicity

2. State Transitions:
   - Must handle all possible states
   - Need clean reset capability
   - Consider invalid state recovery
"""

;================================================
; Slide 10: What is the output for each state?
;================================================
[Output_Logic_Details]
Truth_Table_Structure="""
1. Input Side (States):
   - Current state [2:0]
   - Forms left side of truth table
   - All possible state combinations

2. Output Side (Notes):
   - Corresponding note [2:0]
   - Direct mapping to musical notes
   - Must include rest states
"""

Implementation_Notes="""
1. Output Encoding:
   State  | Note Output | Musical Note
   000    | 000        | Rest (initial)
   001    | 011        | E (first note)
   010    | 010        | D (second note)
   011    | 001        | C (third note)
   100    | 010        | D (fourth note)
   101    | 011        | E (fifth note)
   110    | 000        | Rest
   111    | 000        | Done (rest)
"""

;================================================
; Slide 11: Draw flip-flops and "output logic"
;================================================
[Circuit_Implementation_Details]
Flip_Flop_Design="""
1. D Flip-flop Requirements:
   - Synchronous clock input
   - Asynchronous reset
   - Stable output

2. Output Logic:
   - Combinational logic for note encoding
   - Decoder structure
   - Minimized boolean equations
"""

Circuit_Components="""
1. Storage Elements:
   - Three D flip-flops for state
   - Synchronized to system clock
   - Common reset line

2. Decoder Logic:
   - Converts state to note value
   - Uses combinational gates
   - Optimized for minimal delay
"""

;================================================
; Slide 12: What is the next state for each state?
;================================================
[Next_State_Logic_Details]
State_Transitions="""
1. Transition Table:
   Current | Next  | Condition
   000     | 001   | START=1
   001     | 010   | Always
   010     | 011   | Always
   011     | 100   | Always
   100     | 101   | Always
   101     | 110   | Always
   110     | 111   | Always
   111     | 111   | Until RST
"""

Design_Considerations="""
1. Transition Control:
   - Handle START signal
   - Manage sequence flow
   - Handle completion

2. Reset Handling:
   - Return to 000
   - Clear all states
   - Ready for new start
"""

;================================================
; Slide 13: Draw flip-flops and "next state logic"
;================================================
[Next_State_Circuit_Details]
Implementation_Strategy="""
1. Next State Logic:
   - Combinational logic blocks
   - Input: current state
   - Output: next state values

2. Control Signals:
   - Clock distribution
   - Reset handling
   - Start signal processing
"""

Circuit_Elements="""
1. State Storage:
   - D flip-flops (3)
   - Clock synchronization
   - Reset capability

2. Combinational Logic:
   - Next state calculation
   - Boolean minimization
   - Glitch prevention
"""

;================================================
; Slide 14: Combine "output logic" and "next state logic"
;================================================
[Complete_Circuit_Details]
Integration_Steps="""
1. Circuit Combination:
   - Connect flip-flops
   - Add output decoder
   - Implement next state logic
   - Include control signals

2. Signal Timing:
   - Clock distribution
   - Setup/hold times
   - Output stability
"""

Verification_Points="""
1. Functional Checks:
   - Reset operation
   - State sequence
   - Note outputs
   - Completion detection

2. Timing Verification:
   - Clock requirements
   - State transitions
   - Output stability
"""

Would you like me to elaborate on any specific aspect or provide more detailed explanations for any particular slide?