package core;

import gates.AndGate;
import gates.XorGate;
import gates.OrGate;

import java.util.Arrays;

/**
 * Builds a 1-bit Full Adder using:
 * 2 XOR gates, 2 AND gates, 1 OR gate.
 * Wires for intermediate signals.
 */
public class FullAdderCircuit {

    private Wire A, B, Cin;
    private Wire Sum, Cout;

    public FullAdderCircuit() {
        // Create wires
        A   = new Wire("A");
        B   = new Wire("B");
        Cin = new Wire("Cin");

        Sum  = new Wire("Sum");
        Cout = new Wire("Cout");

        // Intermediate wires
        Wire xor1Out = new Wire("xor1Out");
        Wire and1Out = new Wire("and1Out");
        Wire and2Out = new Wire("and2Out");

        // Gate 1: XOR for partial sum of A and B
        XorGate xor1 = new XorGate(Arrays.asList(A, B), xor1Out, "XOR1");
        // Gate 2: XOR for final Sum
        XorGate xor2 = new XorGate(Arrays.asList(xor1Out, Cin), Sum, "XOR2");

        // Gate 3: AND for A,B
        AndGate and1 = new AndGate(Arrays.asList(A, B), and1Out, "AND1");
        // Gate 4: AND for Cin,xor1Out
        AndGate and2 = new AndGate(Arrays.asList(Cin, xor1Out), and2Out, "AND2");

        // Gate 5: OR for final Carry-out
        OrGate orGate = new OrGate(Arrays.asList(and1Out, and2Out), Cout, "OR1");

        // Connect Wires -> Gates
        xor1Out.addConsumer(xor2);   // xor1's output feeds XOR2 & AND2
        and1Out.addConsumer(orGate);
        and2Out.addConsumer(orGate);
        Sum.addConsumer(null);       // Summation doesn't feed another gate here
        Cout.addConsumer(null);
    }

    public void setInputs(boolean aVal, boolean bVal, boolean cinVal) {
        A.setValue(aVal);
        B.setValue(bVal);
        Cin.setValue(cinVal);
    }

    public void evaluate() {
        EventDrivenSimulator.runSimulation();
    }

    public boolean getSum() {
        return Sum.getValue();
    }

    public boolean getCout() {
        return Cout.getValue();
    }
}