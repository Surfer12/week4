package core;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a wire carrying a boolean signal. Each Wire notifies
 * connected gates when its value changes.
 */
public class Wire {
    private boolean value;
    private String identifier;
    private List<Gate> consumers;

    public Wire(String identifier) {
        this.identifier = identifier;
        this.value = false;
        this.consumers = new ArrayList<>();
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean newValue) {
        if (this.value != newValue) {
            this.value = newValue;
            // Notify consumer gates that input changed
            for (Gate g : consumers) {
                EventDrivenSimulator.queueGateEvaluation(g);
            }
        }
    }

    public String getIdentifier() {
        return identifier;
    }

    public void addConsumer(Gate gate) {
        consumers.add(gate);
    }

    @Override
    public String toString() {
        return identifier + "=" + (value ? "1" : "0");
    }
}