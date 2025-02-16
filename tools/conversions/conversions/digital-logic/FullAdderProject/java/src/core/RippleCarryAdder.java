package core;

import gates.XorGate;
import gates.AndGate;
import gates.OrGate;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a hierarchical full adder circuit and can be assembled into a
 * multi-bit Ripple Carry Adder by chaining carry bits.
 */
public class FullAdderCircuit {
    private Signal a;
    private Signal b;
    private Signal cin;
    private Signal sum;
    private Signal cout;

    public FullAdderCircuit(Signal a, Signal b, Signal cin, Signal sum, Signal cout) {
        this.a = a;
        this.b = b;
        this.cin = cin;
        this.sum = sum;
        this.cout = cout;

        // Create intermediate wires
        Signal xor1Out = new Signal("xor1Out");
        Signal and1Out = new Signal("and1Out");
        Signal and2Out = new Signal("and2Out");

        // Xor Gates
        XorGate xor1 = new XorGate(List.of(a, b), xor1Out);
        XorGate xor2 = new XorGate(List.of(xor1Out, cin), sum);

        // And Gates
        AndGate and1 = new AndGate(List.of(a, b), and1Out);
        AndGate and2 = new AndGate(List.of(xor1Out, cin), and2Out);

        // Or Gate for final carry-out
        OrGate orGate = new OrGate(List.of(and1Out, and2Out), cout);
    }
}

/**
 * Multi-bit Ripple Carry Adder Simulation
 */
class RippleCarryAdder {
    private List<FullAdderCircuit> fullAdders;
    private List<Signal> sums;
    private Signal globalCout;

    public RippleCarryAdder(int bitWidth) {
        fullAdders = new ArrayList<>();
        sums = new ArrayList<>();
        // Initialize N-bit signals
        List<Signal> aSignals = new ArrayList<>();
        List<Signal> bSignals = new ArrayList<>();
        Signal carryIn = new Signal("Cin_0"); // Lowest bit carry-in starts

        for (int i = 0; i < bitWidth; i++) {
            // Define wire signals for cascaded bits
            Signal a = new Signal("A_" + i);
            Signal b = new Signal("B_" + i);
            Signal sum = new Signal("Sum_" + i);
            Signal carryOut = new Signal("Cout_" + i);

            // Create a full adder for this bit
            FullAdderCircuit fullAdder = new FullAdderCircuit(a, b, carryIn, sum, carryOut);
            fullAdders.add(fullAdder);
            sums.add(sum);

            // Propagate carry
            carryIn = carryOut;
        }

        // Capture the final carry-out
        globalCout = carryIn;
    }

    public List<Signal> getSums() {
        return sums;
    }

    public Signal getGlobalCout() {
        return globalCout;
    }
}