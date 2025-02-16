package gates;

import core.Gate;
import core.Wire;

import java.util.List;

public class AndGate extends Gate {

    public AndGate(List<Wire> inputs, Wire output, String name) {
        super(inputs, output, name);
    }

    @Override
    public void evaluate() {
        boolean result = true;
        for (Wire in : inputs) {
            result &= in.getValue();
        }
        output.setValue(result);
    }
}