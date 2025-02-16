# =========================================
# File: circuit_visualization.py
# Description:
#   Circuit-level visualization of binary operations
#   showing gates and digital logic implementation.
# =========================================

class Colors:
    """ANSI color codes for terminal output."""
    BLUE = '\033[94m'
    GREEN = '\033[92m'
    YELLOW = '\033[93m'
    RED = '\033[91m'
    CYAN = '\033[96m'
    MAGENTA = '\033[95m'
    ENDC = '\033[0m'
    BOLD = '\033[1m'

def show_half_adder(a: int, b: int) -> None:
    """Visualize a half adder circuit with inputs and outputs."""
    sum_bit = a ^ b  # XOR
    carry = a & b    # AND

    print("\n=== Half Adder Circuit ===")
    print(f"Inputs:  A = {a}, B = {b}")
    print("\nCircuit Diagram:")
    print("     A─────┬─────►XOR──────►Sum")
    print("           │      ▲")
    print("           │      │")
    print("     B─────┼──────┘")
    print("           │")
    print("           └─────►AND──────►Carry")
    print("\nGate Operations:")
    print(f"XOR Gate: {a} ⊕ {b} = {sum_bit}")
    print(f"AND Gate: {a} & {b} = {carry}")
    print("\nOutputs:")
    print(f"Sum:   {sum_bit}")
    print(f"Carry: {carry}")

    print("\nK-map for XOR (Sum):")
    print("      B=0   B=1")
    print("A=0:   0     1")
    print("A=1:   1     0")

    print("\nK-map for AND (Carry):")
    print("      B=0   B=1")
    print("A=0:   0     0")
    print("A=1:   0     1")

def show_full_adder(a: int, b: int, c_in: int) -> None:
    """Visualize a full adder circuit with inputs and outputs."""
    # First half adder
    sum1 = a ^ b
    carry1 = a & b

    # Second half adder
    sum_out = sum1 ^ c_in
    carry2 = sum1 & c_in

    # OR gate for final carry
    carry_out = carry1 | carry2

    print("\n=== Full Adder Circuit ===")
    print(f"Inputs: A = {a}, B = {b}, Carry_in = {c_in}")
    print("\nCircuit Diagram:")
    print("     A─────┬─────►XOR─────┬─────►XOR──────►Sum")
    print("           │      ▲       │      ▲")
    print("           │      │       │      │")
    print("     B─────┼──────┘       │      │")
    print("           │              │      │")
    print("           └─────►AND─────┼──────┘")
    print("                  │       │")
    print("                  │       │")
    print("   Cin───────────┼───────┘")
    print("                  │")
    print("                  └─────►OR───────►Carry")
    print("\nGate Operations:")
    print("First Half Adder:")
    print(f"XOR1: {a} ⊕ {b} = {sum1}")
    print(f"AND1: {a} & {b} = {carry1}")
    print("\nSecond Half Adder:")
    print(f"XOR2: {sum1} ⊕ {c_in} = {sum_out}")
    print(f"AND2: {sum1} & {c_in} = {carry2}")
    print("\nFinal Carry:")
    print(f"OR: {carry1} | {carry2} = {carry_out}")
    print("\nOutputs:")
    print(f"Sum:        {sum_out}")
    print(f"Carry_out:  {carry_out}")

    print("\nK-map for Sum (A ⊕ B ⊕ Cin):")
    print("      BC")
    print("     00  01  11  10")
    print("A=0:  0   1   0   1")
    print("A=1:  1   0   1   0")

    print("\nK-map for Carry:")
    print("      BC")
    print("     00  01  11  10")
    print("A=0:  0   0   1   1")
    print("A=1:  0   1   1   1")

def show_ripple_carry_adder(a: str, b: str) -> None:
    """Visualize a ripple carry adder for multi-bit addition."""
    # Ensure equal length
    max_len = max(len(a), len(b))
    a = a.zfill(max_len)
    b = b.zfill(max_len)

    print(f"\n=== {max_len}-bit Ripple Carry Adder ===")
    print("Input binary numbers:")
    print(f"A: {a}")
    print(f"B: {b}")

    # Process each bit position
    carry = 0
    result = ""
    carries = []

    print("\nBit-by-bit addition with full adders:")
    for i in range(max_len-1, -1, -1):
        bit_a = int(a[i])
        bit_b = int(b[i])

        # Calculate full adder outputs
        sum1 = bit_a ^ bit_b
        carry1 = bit_a & bit_b
        sum_out = sum1 ^ carry
        carry2 = sum1 & carry
        carry_next = carry1 | carry2

        print(f"\nBit position {max_len-1-i}:")
        print(f"Full Adder {i}:")
        print(f"  Inputs:  A={bit_a}, B={bit_b}, Cin={carry}")
        print(f"  Outputs: Sum={sum_out}, Cout={carry_next}")

        result = str(sum_out) + result
        carries.append(carry)
        carry = carry_next

    if carry:
        result = '1' + result
        carries.append(carry)

    print("\nFinal Result:")
    print(f"  {''.join(str(c) for c in carries[:-1])}  (Carries)")
    print(f"  {a}")
    print(f"+ {b}")
    print(f"  {'-' * max_len}")
    print(f"  {result}")

def print_static_circuit_diagram() -> None:
    """Print a static circuit diagram example."""
    print("--- Full Circuit Diagram ---")
    print("Input --[NAND (1-bit)]--> --[NAND (1-bit)]--> --[XNOR (1-bit)]--> Output")
    print("")
    print("--- Advanced Details for Component 1 (NAND) ---")
    print("Truth Table:")
    print("A B | NAND")
    print("0 0 | 1")
    print("0 1 | 1")
    print("1 0 | 1")
    print("1 1 | 0")
    print("")
    print("K-map:")
    print("      B=0   B=1")
    print("A=0:   1     1")
    print("A=1:   1     0")
    print("")
    print("SOP Expression:")
    print("Output = A'B' + A'B + AB'")

def show_circuit_menu() -> None:
    """Show menu for circuit-level visualizations."""
    while True:
        print("\n=== Circuit Visualization Menu ===")
        print("0. Show Static Diagram")
        print("1. Half Adder")
        print("2. Full Adder")
        print("3. Ripple Carry Adder")
        print("4. NOT Gate")
        print("5. OR Gate")
        print("6. NOR Gate")
        print("7. NAND Gate")
        print("8. XNOR Gate")
        print("9. Gate Chain Builder")
        print("10. Return to Main Menu")

        choice = input("Enter choice (0-10): ")

        if choice == "0":
            print_static_circuit_diagram()
            input("\nPress Enter to continue...")
            continue
        elif choice == "1":
            print("\nEnter two 1-bit numbers (0 or 1):")
            try:
                a = int(input("A: "))
                b = int(input("B: "))
                if a not in (0, 1) or b not in (0, 1):
                    print("Inputs must be 0 or 1")
                    continue
                show_half_adder(a, b)
            except ValueError:
                print("Invalid input")

        elif choice == "2":
            print("\nEnter three 1-bit numbers (0 or 1):")
            try:
                a = int(input("A: "))
                b = int(input("B: "))
                c = int(input("Carry in: "))
                if a not in (0, 1) or b not in (0, 1) or c not in (0, 1):
                    print("Inputs must be 0 or 1")
                    continue
                show_full_adder(a, b, c)
            except ValueError:
                print("Invalid input")

        elif choice == "3":
            print("\nEnter two binary numbers:")
            a = input("Enter first binary number: ")
            b = input("Enter second binary number: ")
            if not all(bit in '01' for bit in a + b):
                print("Invalid binary numbers")
                continue
            show_ripple_carry_adder(a, b)

        elif choice == "4":
            # NOT Gate
            print("\nEnter 1-bit input for NOT Gate (0 or 1):")
            try:
                a = int(input("A: "))
                if a not in (0, 1):
                    print("Input must be 0 or 1")
                    continue
                show_not_gate(a)
                adv = input("Show advanced details (truth table, K-map, SOP)? (y/n): ").strip().lower()
                if adv == "y":
                    print("\n--- Advanced Details for NOT Gate ---")
                    print("Truth Table:")
                    print("A | NOT A")
                    print("0 | 1")
                    print("1 | 0")
                    print("\nK-map for NOT Gate:")
                    print("A | NOT A")
                    print("0 | 1")
                    print("1 | 0")
                    print("\nSum of Products (SOP) Expression:")
                    print("Output = A' (True when A=0)")
            except ValueError:
                print("Invalid input")

        elif choice == "5":
            # OR Gate
            print("\nEnter two 1-bit numbers for OR Gate (0 or 1):")
            try:
                a = int(input("A: "))
                b = int(input("B: "))
                if a not in (0, 1) or b not in (0, 1):
                    print("Inputs must be 0 or 1")
                    continue
                show_or_gate(a, b)
                adv = input("Show advanced details (truth table, K-map, SOP)? (y/n): ").strip().lower()
                if adv == "y":
                    print("\n--- Advanced Details for OR Gate ---")
                    print("Truth Table:")
                    print("A B | A OR B")
                    print("0 0 |   0")
                    print("0 1 |   1")
                    print("1 0 |   1")
                    print("1 1 |   1")
                    print("\nK-map for OR Gate:")
                    print("      B=0   B=1")
                    print("A=0:   0     1")
                    print("A=1:   1     1")
                    print("\nSum of Products (SOP) Expression:")
                    print("Output = A + B")
            except ValueError:
                print("Invalid input")

        elif choice == "6":
            # NOR Gate
            print("\nEnter two 1-bit numbers for NOR Gate (0 or 1):")
            try:
                a = int(input("A: "))
                b = int(input("B: "))
                if a not in (0, 1) or b not in (0, 1):
                    print("Inputs must be 0 or 1")
                    continue
                show_nor_gate(a, b)
                adv = input("Show advanced details (truth table, K-map, SOP)? (y/n): ").strip().lower()
                if adv == "y":
                    print("\n--- Advanced Details for NOR Gate ---")
                    print("Truth Table:")
                    print("A B | NOR")
                    print("0 0 |  1")
                    print("0 1 |  0")
                    print("1 0 |  0")
                    print("1 1 |  0")
                    print("\nK-map for NOR Gate:")
                    print("      B=0   B=1")
                    print("A=0:   1     0")
                    print("A=1:   0     0")
                    print("\nSum of Products (SOP) Expression:")
                    print("Output = A' B'")
            except ValueError:
                print("Invalid input")

        elif choice == "7":
            # NAND Gate
            print("\nEnter two 1-bit numbers for NAND Gate (0 or 1):")
            try:
                a = int(input("A: "))
                b = int(input("B: "))
                if a not in (0, 1) or b not in (0, 1):
                    print("Inputs must be 0 or 1")
                    continue
                show_nand_gate(a, b)
                adv = input("Show advanced details (truth table, K-map, SOP)? (y/n): ").strip().lower()
                if adv == "y":
                    print("\n--- Advanced Details for NAND Gate ---")
                    print("Truth Table:")
                    print("A B | NAND")
                    print("0 0 |  1")
                    print("0 1 |  1")
                    print("1 0 |  1")
                    print("1 1 |  0")
                    print("\nK-map for NAND Gate:")
                    print("      B=0   B=1")
                    print("A=0:   1     1")
                    print("A=1:   1     0")
                    print("\nSum of Products (SOP) Expression:")
                    print("Output = A'B' + A'B + AB'")
            except ValueError:
                print("Invalid input")

        elif choice == "8":
            # XNOR Gate
            print("\nEnter two 1-bit numbers for XNOR Gate (0 or 1):")
            try:
                a = int(input("A: "))
                b = int(input("B: "))
                if a not in (0, 1) or b not in (0, 1):
                    print("Inputs must be 0 or 1")
                    continue
                show_xnor_gate(a, b)
                adv = input("Show advanced details (truth table, K-map, SOP)? (y/n): ").strip().lower()
                if adv == "y":
                    print("\n--- Advanced Details for XNOR Gate ---")
                    print("Truth Table:")
                    print("A B | XNOR")
                    print("0 0 |  1")
                    print("0 1 |  0")
                    print("1 0 |  0")
                    print("1 1 |  1")
                    print("\nK-map for XNOR Gate:")
                    print("      B=0   B=1")
                    print("A=0:   1     0")
                    print("A=1:   0     1")
                    print("\nSum of Products (SOP) Expression:")
                    print("Output = A'B' + AB")
            except ValueError:
                print("Invalid input")

        elif choice == "9":
            # Dynamic Circuit Builder
            dynamic_circuit_builder()

        elif choice == "10":
            break

        else:
            print("Invalid choice")

        input("\nPress Enter to continue...")

# -----------------------------------------
# Additional Gate Visualizations
# -----------------------------------------

def show_not_gate(a: int) -> None:
    """Visualize a NOT gate circuit with input and output."""
    result = 1 if a == 0 else 0
    print("\n=== NOT Gate Circuit ===")
    print(f"Input:  A = {a}")
    print("\nCircuit Diagram:")
    print("     A─────►NOT─────►Output")
    print("\nGate Operation:")
    print(f"NOT Gate: ¬{a} = {result}")
    print("\nOutput:")
    print(f"Result: {result}")

    print("\nK-map for NOT Gate:")
    print("A | NOT A")
    print("0 | 1")
    print("1 | 0")

def show_or_gate(a: int, b: int) -> None:
    """Visualize an OR gate circuit with inputs and output."""
    result = a | b
    print("\n=== OR Gate Circuit ===")
    print(f"Inputs:  A = {a}, B = {b}")
    print("\nCircuit Diagram:")
    print("     A─────┐")
    print("           ├────►OR─────►Output")
    print("     B─────┘")
    print("\nGate Operation:")
    print(f"OR Gate: {a} ∨ {b} = {result}")
    print("\nOutput:")
    print(f"Result: {result}")

    print("\nK-map for OR Gate:")
    print("      B=0   B=1")
    print("A=0:   0     1")
    print("A=1:   1     1")

def show_nor_gate(a: int, b: int) -> None:
    """Visualize a NOR gate circuit with inputs and output."""
    result = 1 if (a | b) == 0 else 0
    print("\n=== NOR Gate Circuit ===")
    print(f"Inputs:  A = {a}, B = {b}")
    print("\nCircuit Diagram:")
    print("     A─────┐")
    print("           ├────►NOR─────►Output")
    print("     B─────┘")
    print("\nGate Operation:")
    print(f"NOR Gate: ¬({a} ∨ {b}) = {result}")
    print("\nOutput:")
    print(f"Result: {result}")

    print("\nK-map for NOR Gate:")
    print("      B=0   B=1")
    print("A=0:   1     0")
    print("A=1:   0     0")

def show_nand_gate(a: int, b: int) -> None:
    """Visualize a NAND gate circuit with inputs and output."""
    result = 0 if (a & b) == 1 else 1
    print("\n=== NAND Gate Circuit ===")
    print(f"Inputs:  A = {a}, B = {b}")
    print("\nCircuit Diagram:")
    print("     A─────┐")
    print("           ├────►NAND────►Output")
    print("     B─────┘")
    print("\nGate Operation:")
    print(f"NAND Gate: ¬({a} ∧ {b}) = {result}")
    print("\nOutput:")
    print(f"Result: {result}")

    print("\nK-map for NAND Gate:")
    print("      B=0   B=1")
    print("A=0:   1     1")
    print("A=1:   1     0")

def show_xnor_gate(a: int, b: int) -> None:
    """Visualize an XNOR gate circuit with inputs and output."""
    result = 1 if a == b else 0
    print("\n=== XNOR Gate Circuit ===")
    print(f"Inputs:  A = {a}, B = {b}")
    print("\nCircuit Diagram:")
    print("     A─────┐")
    print("           ├────►XNOR────►Output")
    print("     B─────┘")
    print("\nGate Operation:")
    print(f"XNOR Gate: {a} XNOR {b} = {result}")
    print("\nOutput:")
    print(f"Result: {result}")

    print("\nK-map for XNOR Gate:")
    print("      B=0   B=1")
    print("A=0:   1     0")
    print("A=1:   0     1")

# -----------------------------------------------------------------------------
# Dynamic Circuit Builder Implementation
# -----------------------------------------------------------------------------

def compute_gate(gate: str, inputs: list) -> int:
    """Compute the output for a given gate and its inputs."""
    if gate.upper() == "NOT":
        return 1 if inputs[0] == 0 else 0
    elif gate.upper() == "OR":
        return inputs[0] | inputs[1]
    elif gate.upper() == "NOR":
        return 1 if (inputs[0] | inputs[1]) == 0 else 0
    elif gate.upper() == "NAND":
        return 0 if (inputs[0] & inputs[1]) == 1 else 1
    elif gate.upper() == "XNOR":
        return 1 if inputs[0] == inputs[1] else 0
    else:
        return None


def generate_advanced_details(gate: str) -> str:
    """Generate advanced details for a given gate type."""
    gate = gate.upper()
    if gate == "NOT":
        return ("Truth Table:\nA | NOT A\n0 | 1\n1 | 0\n\n"
                "K-map (1 variable):\n[A=0]: 1\n[A=1]: 0\n\n"
                "SOP Expression:\nOutput = A' (True when A=0)")
    elif gate == "OR":
        return ("Truth Table:\nA B | A OR B\n0 0 | 0\n0 1 | 1\n1 0 | 1\n1 1 | 1\n\n"
                "K-map:\n      B=0   B=1\nA=0:   0     1\nA=1:   1     1\n\n"
                "SOP Expression:\nOutput = A + B")
    elif gate == "NOR":
        return ("Truth Table:\nA B | NOR\n0 0 | 1\n0 1 | 0\n1 0 | 0\n1 1 | 0\n\n"
                "K-map:\n      B=0   B=1\nA=0:   1     0\nA=1:   0     0\n\n"
                "SOP Expression:\nOutput = A' B'")
    elif gate == "NAND":
        return ("Truth Table:\nA B | NAND\n0 0 | 1\n0 1 | 1\n1 0 | 1\n1 1 | 0\n\n"
                "K-map:\n      B=0   B=1\nA=0:   1     1\nA=1:   1     0\n\n"
                "SOP Expression:\nOutput = A'B' + A'B + AB'")
    elif gate == "XNOR":
        return ("Truth Table:\nA B | XNOR\n0 0 | 1\n0 1 | 0\n1 0 | 0\n1 1 | 1\n\n"
                "K-map:\n      B=0   B=1\nA=0:   1     0\nA=1:   0     1\n\n"
                "SOP Expression:\nOutput = A'B' + AB")
    else:
        return "No advanced details available."


def dynamic_circuit_builder() -> None:
    """Interactive dynamic circuit builder for gate operations."""
    circuit_components = []  # List to hold components; each component is a dict
    component_counter = 1

    advanced_mode = False
    enable_adv = input("Enable Advanced Dynamic Builder Mode? (y/n): ").strip().lower()
    if enable_adv == "y":
        advanced_mode = True
        print("Advanced Mode enabled.")
    else:
        print("Advanced Mode disabled.")

    def show_full_circuit():
        print("\n--- Full Circuit Diagram ---")
        if not circuit_components:
            print("[Empty Circuit]")
        else:
            if advanced_mode:
                diagram = "Input "
                for comp in circuit_components:
                    bw = comp.get('bit_width', 1)
                    diagram += f"--[{comp['gate']} ({bw}-bit)]--> "
                diagram += "Output"
                print(diagram)
            else:
                for comp in circuit_components:
                    print(f"[{comp['id']}] {comp['gate']}(inputs={comp['inputs']}) => {comp['output']}")

    # New nested function to display a simple K-map for the current gate chain
    def print_kmap_for_chain(components: list) -> None:
        print("\n--- K-map for Current Gate Chain ---")
        print("| ID | Gate   | Inputs        | Output |")
        print("|----|--------|---------------|--------|")
        for comp in components:
            print(f"| {comp['id']}  | {comp['gate']} | {comp['inputs']} | {comp['output']} |")
        print("-----------------------------------------")

    # Entering dynamic builder mode
    print("\nEntering Dynamic Circuit Builder Mode.")
    print("Type 'help' for a list of commands.")

    while True:
        cmd = input("Dynamic Builder > ").strip()
        if not cmd:
            continue
        parts = cmd.split()
        command = parts[0].lower()

        if command == "help":
            print("Available commands:")
            print("  add [GATE]       -> Add a new gate (NOT, OR, NOR, NAND, XNOR)")
            print("  custom [NAME]    -> Create a custom gate with a truth table")
            print("  list             -> List all circuit components with details")
            print("  delete [ID]      -> Delete a component by ID")
            print("  edit [ID]        -> Edit a component's inputs and bit width (if advanced)")
            print("  move [ID] [POS]  -> Move component to new position (starting at 1)")
            print("  move_rc [ID] [row] [column]  -> Move component using row & column (fixed 3 columns per row)")
            print("  show             -> Display full circuit diagram")
            print("  kmap             -> Display K-map for current gate chain")
            print("  advanced [ID]    -> Show advanced details for a component")
            print("  save [filename]  -> Save current circuit to file")
            print("  load [filename]  -> Load circuit from file")
            print("  exit             -> Exit dynamic builder mode")
            continue

        elif command == "add":
            if len(parts) < 2:
                print("Usage: add [GATE]")
                continue
            gate_type = parts[1].upper()
            if gate_type not in ["NOT", "OR", "NOR", "NAND", "XNOR"]:
                print("Invalid gate type. Valid types: NOT, OR, NOR, NAND, XNOR")
                continue
            try:
                inputs = []
                if gate_type == "NOT":
                    val = int(input("Enter 1-bit input for NOT Gate (0 or 1): "))
                    if val not in (0, 1):
                        print("Invalid input. Must be 0 or 1.")
                        continue
                    inputs.append(val)
                else:
                    val1 = int(input(f"Enter first 1-bit input for {gate_type} Gate (0 or 1): "))
                    val2 = int(input(f"Enter second 1-bit input for {gate_type} Gate (0 or 1): "))
                    if val1 not in (0, 1) or val2 not in (0, 1):
                        print("Invalid inputs. Must be 0 or 1.")
                        continue
                    inputs.extend([val1, val2])

                output = compute_gate(gate_type, inputs)
                adv_details = generate_advanced_details(gate_type)

                if advanced_mode:
                    bit_input = input(f"Recommended bit width for {gate_type} gate is 1-bit. Press Enter to use default or specify bit width: ").strip()
                    if bit_input == "":
                        bit_width = 1
                    else:
                        try:
                            bit_width = int(bit_input)
                        except ValueError:
                            print("Invalid bit width input. Defaulting to 1-bit.")
                            bit_width = 1
                    component = {
                        'id': component_counter,
                        'gate': gate_type,
                        'inputs': inputs,
                        'output': output,
                        'bit_width': bit_width,
                        'advanced': adv_details
                    }
                else:
                    component = {
                        'id': component_counter,
                        'gate': gate_type,
                        'inputs': inputs,
                        'output': output,
                        'advanced': adv_details
                    }
                circuit_components.append(component)
                print(f"Component added with ID {component_counter}.")
                component_counter += 1
                show_full_circuit()
            except ValueError:
                print("Invalid input. Please enter integer values (0 or 1).")

        elif command == "delete":
            if len(parts) < 2:
                print("Usage: delete [ID]")
                continue
            try:
                comp_id = int(parts[1])
                new_components = [c for c in circuit_components if c['id'] != comp_id]
                if len(new_components) == len(circuit_components):
                    print(f"No component with ID {comp_id} found.")
                else:
                    circuit_components[:] = new_components
                    print(f"Component {comp_id} deleted.")
                    show_full_circuit()
            except ValueError:
                print("Invalid ID.")

        elif command == "edit":
            if len(parts) < 2:
                print("Usage: edit [ID]")
                continue
            try:
                comp_id = int(parts[1])
                comp = next((c for c in circuit_components if c['id'] == comp_id), None)
                if not comp:
                    print(f"No component with ID {comp_id} found.")
                    continue
                gate_type = comp.get('gate')
                inputs = []
                if gate_type == "NOT":
                    val = int(input("Enter new 1-bit input for NOT Gate (0 or 1): "))
                    if val not in (0, 1):
                        print("Invalid input.")
                        continue
                    inputs.append(val)
                else:
                    val1 = int(input(f"Enter new first 1-bit input for {gate_type} Gate (0 or 1): "))
                    val2 = int(input(f"Enter new second 1-bit input for {gate_type} Gate (0 or 1): "))
                    if val1 not in (0, 1) or val2 not in (0, 1):
                        print("Invalid inputs.")
                        continue
                    inputs.extend([val1, val2])
                comp['inputs'] = inputs
                if comp.get('type') == 'CUSTOM':
                    idx = int(''.join(str(bit) for bit in inputs), 2)
                    comp['output'] = comp['truth_table'][idx]
                else:
                    comp['output'] = compute_gate(gate_type, inputs)
                if advanced_mode and comp.get('bit_width') is not None:
                    update_bw = input("Do you want to update the bit width? (y/n): ").strip().lower()
                    if update_bw == "y":
                        bit_input = input("Enter new bit width: ").strip()
                        try:
                            comp['bit_width'] = int(bit_input)
                        except ValueError:
                            print("Invalid input. Bit width unchanged.")
                print(f"Component {comp_id} updated.")
                show_full_circuit()
            except ValueError:
                print("Invalid input.")

        elif command == "move":
            if len(parts) < 3:
                print("Usage: move [ID] [new_position]")
                continue
            try:
                comp_id = int(parts[1])
                new_pos = int(parts[2]) - 1
                idx = next((i for i, c in enumerate(circuit_components) if c['id'] == comp_id), None)
                if idx is None:
                    print(f"No component with ID {comp_id} found.")
                    continue
                comp = circuit_components.pop(idx)
                if new_pos < 0 or new_pos > len(circuit_components):
                    print("Invalid new position.")
                    circuit_components.insert(idx, comp)  # revert
                    continue
                circuit_components.insert(new_pos, comp)
                print(f"Component {comp_id} moved to position {new_pos + 1}.")
                show_full_circuit()
            except ValueError:
                print("Invalid input.")

        elif command == "move_rc":
            if len(parts) < 4:
                print("Usage: move_rc [ID] [row] [column]")
                continue
            try:
                comp_id = int(parts[1])
                row = int(parts[2])
                col = int(parts[3])
                fixed_cols = 3  # each row has 3 columns
                new_pos = (row - 1) * fixed_cols + (col - 1)
                idx = next((i for i, c in enumerate(circuit_components) if c['id'] == comp_id), None)
                if idx is None:
                    print(f"No component with ID {comp_id} found.")
                    continue
                comp = circuit_components.pop(idx)
                if new_pos < 0:
                    print("Invalid position calculated.")
                    circuit_components.insert(idx, comp)  # revert
                    continue
                if new_pos > len(circuit_components):
                    new_pos = len(circuit_components)
                circuit_components.insert(new_pos, comp)
                print(f"Component {comp_id} moved to row {row}, column {col} (position {new_pos + 1}).")
                show_full_circuit()
            except ValueError:
                print("Invalid input.")

        elif command == "kmap":
            print_kmap_for_chain(circuit_components)

        elif command == "list":
            print("\nCurrent Circuit Components:")
            for comp in circuit_components:
                extra = f", Bit Width: {comp.get('bit_width', 'N/A')}" if 'bit_width' in comp else ""
                custom_info = " [Custom]" if comp.get('type') == 'CUSTOM' else ""
                print(f"ID: {comp['id']} | Gate: {comp['gate']}{custom_info} | Inputs: {comp['inputs']} | Output: {comp['output']}{extra}")

        elif command == "show":
            show_full_circuit()

        elif command == "advanced":
            if len(parts) < 2:
                print("Usage: advanced [ID]")
                continue
            try:
                comp_id = int(parts[1])
                comp = next((c for c in circuit_components if c['id'] == comp_id), None)
                if not comp:
                    print(f"No component with ID {comp_id} found.")
                    continue
                print(f"\n--- Advanced Details for Component {comp_id} ({comp['gate']}) ---")
                print(comp['advanced'])
            except ValueError:
                print("Invalid ID.")

        elif command == "save":
            if len(parts) < 2:
                print("Usage: save [filename]")
                continue
            filename = parts[1]
            try:
                import json
                with open(filename, 'w') as f:
                    json.dump(circuit_components, f)
                print(f"Circuit components saved to {filename}.")
                show_full_circuit()
            except Exception as e:
                print(f"Error saving file: {e}")

        elif command == "load":
            if len(parts) < 2:
                print("Usage: load [filename]")
                continue
            filename = parts[1]
            try:
                import json
                with open(filename, 'r') as f:
                    loaded = json.load(f)
                circuit_components[:] = loaded
                if circuit_components:
                    component_counter = max(comp['id'] for comp in circuit_components) + 1
                else:
                    component_counter = 1
                print(f"Circuit components loaded from {filename}.")
                show_full_circuit()
            except Exception as e:
                print(f"Error loading file: {e}")

        elif command == "custom":
            try:
                if len(parts) < 2:
                    custom_name = input("Enter a name for your custom gate: ").strip()
                else:
                    custom_name = parts[1]
                n_inputs = int(input("Enter number of inputs for the custom gate: "))
                expected_entries = 2 ** n_inputs
                print(f"Enter truth table outputs for each combination in order (total {expected_entries} entries).")
                method_choice = input("Enter '1' for single-line input (space-separated) or '2' for multi-line input: ").strip()
                if method_choice == '1':
                    truth_entries_raw = input("Enter the truth table outputs separated by spaces: ").split()
                elif method_choice == '2':
                    truth_entries_raw = []
                    print(f"Enter {expected_entries} truth table outputs (one per line):")
                    for i in range(expected_entries):
                        entry = input(f"Entry {i+1}: ").strip()
                        truth_entries_raw.append(entry)
                else:
                    print("Invalid input method choice. Aborting custom gate creation.")
                    continue
                if len(truth_entries_raw) != expected_entries:
                    print("Incorrect number of truth table entries.")
                    continue
                truth_entries = [int(x) for x in truth_entries_raw]
                inputs = []
                for i in range(n_inputs):
                    val = int(input(f"Enter 1-bit input {i+1}: "))
                    if val not in (0,1):
                        print("Invalid input.")
                        break
                    inputs.append(val)
                else:
                    idx = int(''.join(str(bit) for bit in inputs), 2)
                    output = truth_entries[idx]
                    adv_details = f"Custom gate truth table: {truth_entries}"
                    if advanced_mode:
                        bit_input = input(f"Recommended bit width for custom gate {custom_name} is 1-bit. Press Enter to use default or specify bit width: ").strip()
                        if bit_input == "":
                            bit_width = 1
                        else:
                            try:
                                bit_width = int(bit_input)
                            except ValueError:
                                print("Invalid bit width input. Defaulting to 1-bit.")
                                bit_width = 1
                        component = {
                            'id': component_counter,
                            'gate': custom_name,
                            'type': 'CUSTOM',
                            'inputs': inputs,
                            'output': output,
                            'bit_width': bit_width,
                            'truth_table': truth_entries,
                            'n_inputs': n_inputs,
                            'advanced': adv_details
                        }
                    else:
                        component = {
                            'id': component_counter,
                            'gate': custom_name,
                            'type': 'CUSTOM',
                            'inputs': inputs,
                            'output': output,
                            'truth_table': truth_entries,
                            'n_inputs': n_inputs,
                            'advanced': adv_details
                        }
                    circuit_components.append(component)
                    print(f"Custom component added with ID {component_counter}.")
                    component_counter += 1
                    show_full_circuit()
            except ValueError:
                print("Invalid input in custom gate creation.")

        elif command == "exit":
            print("Exiting Dynamic Circuit Builder Mode.")
            break
        else:
            print("Unknown command. Type 'help' for a list of commands.")

    # End of dynamic circuit builder loop

# -----------------------------------------