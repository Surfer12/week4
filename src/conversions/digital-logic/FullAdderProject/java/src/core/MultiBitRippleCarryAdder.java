package core;

import java.util.ArrayList;
import java.util.List;

/**
 * Extend RippleCarryAdder to handle multi-bit input as integers
 */
public class MultiBitRippleCarryAdder extends RippleCarryAdder {

    public MultiBitRippleCarryAdder(int bitWidth) {
        super(bitWidth);
    }

    public int[] add(int a, int b, int cin) {
        int bitWidth = this.getSums().size();

        // Set inputs bit by bit
        for (int i = 0; i < bitWidth; i++) {
            int aBit = (a >> i) & 1;
            int bBit = (b >> i) & 1;

            this.getFullAdders().get(i).getInputA().setValue(aBit == 1);
            this.getFullAdders().get(i).getInputB().setValue(bBit == 1);
        }

        // Set initial carry
        this.getFullAdders().get(0).getCarryIn().setValue(cin == 1);

        // Evaluate the circuit
        this.evaluate();

        // Collect results
        int sum = 0;
        for (int i = 0; i < bitWidth; i++) {
            if (this.getSums().get(i).getValue()) {
                sum |= (1 << i);
            }
        }

        int cout = this.getGlobalCout().getValue() ? 1 : 0;
        return new int[]{sum, cout};
    }
}