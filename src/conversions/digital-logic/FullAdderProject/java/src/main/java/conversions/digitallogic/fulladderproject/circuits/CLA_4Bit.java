package conversions.digitallogic.fulladderproject.circuits;

import conversions.digitallogic.fulladderproject.logic.CLA_CarryLogic;
import conversions.digitallogic.fulladderproject.logic.CLA_GeneratePropagate;
import conversions.digitallogic.fulladderproject.gates.XORGate;

public class CLA_4Bit {

    private CLA_GeneratePropagate gpLogic;
    private CLA_CarryLogic carryLogic;
    private XORGate[] sumXORs;

    public CLA_4Bit() {
        gpLogic = new CLA_GeneratePropagate();
        carryLogic = new CLA_CarryLogic();
        sumXORs = new XORGate[4];
        for (int i = 0; i < 4; i++) {
            sumXORs[i] = new XORGate();
        }
    }

    public boolean[] add(boolean[] a, boolean[] b, boolean cIn) {
        if (a.length != 4 || b.length != 4) {
            throw new IllegalArgumentException("Inputs must be 4-bit arrays.");
        }

        boolean[] g = new boolean[4];
        boolean[] p = new boolean[4];
        for (int i = 0; i < 4; i++) {
            g[i] = gpLogic.calculateGenerate(a[i], b[i]);
            p[i] = gpLogic.calculatePropagate(a[i], b[i]);
        }

        boolean[] carries = carryLogic.calculateCarries(cIn, g, p);
        boolean[] sum = new boolean[4];
        sum[0] = sumXORs[0].evaluate(p[0], cIn);
        sum[1] = sumXORs[1].evaluate(p[1], carries[0]);
        sum[2] = sumXORs[2].evaluate(p[2], carries[1]);
        sum[3] = sumXORs[3].evaluate(p[3], carries[2]);

        return sum;
    }

    // ... (You would also add methods to get the carry-out, etc.) ...
}