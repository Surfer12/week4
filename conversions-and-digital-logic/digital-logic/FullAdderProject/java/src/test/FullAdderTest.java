package test;

import core.FullAdderCircuit;

/**
 * Exhaustive test for 1-bit full adder truth table.
 */
public class FullAdderTest {

    public static void main(String[] args) {
        FullAdderCircuit fa = new FullAdderCircuit();

        // 8 combos for (A,B,Cin)
        boolean[][] truthTable = {
            // A, B, Cin, Expected_Sum, Expected_Cout
            {false, false, false, false, false},
            {false, false, true,  true,  false},
            {false, true,  false, true,  false},
            {false, true,  true,  false, true},
            {true,  false, false, true,  false},
            {true,  false, true,  false, true},
            {true,  true,  false, false, true},
            {true,  true,  true,  true,  true}
        };

        boolean passed = true;

        for (boolean[] row : truthTable) {
            boolean A = row[0];
            boolean B = row[1];
            boolean Cin = row[2];
            boolean expSum = row[3];
            boolean expCout = row[4];

            fa.setInputs(A, B, Cin);
            fa.evaluate(); // run event-driven simulation

            boolean gotSum = fa.getSum();
            boolean gotCout = fa.getCout();

            boolean sumMatch = (gotSum == expSum);
            boolean coutMatch = (gotCout == expCout);

            System.out.printf("Inputs: A=%b B=%b Cin=%b  =>  Sum=%b Cout=%b  [Expected: %b %b]\n",
                A, B, Cin, gotSum, gotCout, expSum, expCout);

            if (!sumMatch || !coutMatch) {
                passed = false;
                System.err.println("Mismatch detected!");
            }
        }

        if (passed) {
            System.out.println("All Full Adder tests passed!");
        } else {
            System.err.println("Some tests failed. Check logs for details.");
        }
    }
}