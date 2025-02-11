package core;

import java.util.List;
import gates.*;

/**
 * Abstract Gate representation. Each Gate has inputs (Wires) and one output (Wire).
 * The evaluate() method updates the output based on the gate's logic.
 */
public abstract class Gate {
    protected List<Wire> inputs;
    protected Wire output;
    protected String name;

    public Gate(List<Wire> inputs, Wire output, String name) {
        this.inputs = inputs;
        this.output = output;
        this.name = name;
    }

    public Wire getOutput() {
        return output;
    }

    public String getName() {
        return name;
    }

    /**
     * Evaluate the logic and update output Wire's value if changed.
     */
    public abstract void evaluate();
}