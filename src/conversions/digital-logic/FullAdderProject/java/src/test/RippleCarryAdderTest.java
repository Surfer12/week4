package test;

import core.RippleCarryAdder;
import core.Signal;

import java.util.List;

public class RippleCarryAdderTest {
    public static void main(String[] args) {
        // Test 4-bit Ripple Carry Adder
        test4BitAdder();
    }

    private static void test4BitAdder() {
        System.out.println("Testing 4-bit Ripple Carry Adder!");

        // Create a 4-bit Ripple Carry Adder
        RippleCarryAdder adder = new RippleCarryAdder(4);

        // Define test cases: {A, B, Cin, Expected_Sums (LSB -> MSB), Expected_Cout}
        int[][] testCases = {
            {0b0000, 0b0000, 0, 0b0000, 0},
            {0b0001, 0b0001, 0, 0b0010, 0},
            {0b0011, 0b0101, 0, 0b1000, 0},
            {0b1111, 0b0001, 0, 0b0000, 1},
            {0b1111, 0b1111, 0, 0b1110, 1},
            {0b1111, 0b1111, 1, 0b1111, 1},
        };

        for (int[] testCase : testCases) {
            int a = testCase[0];
            int b = testCase[1];
            int cin = testCase[2];
            int expectedSum = testCase[3];
            int expectedCout = testCase[4];

            // Set up inputs
            for (int i = 0; i < 4; i++) {
                adder.getSums().get(i).setValue((a >> i) & 1);
                adder.getSums().get(i).setValue((b >> i) & 1);
            }
            adder.getGlobalCout().setValue(cin);

            // Simulate the adder
            adder.evaluate();

            // Check results
            int actualSum = 0;
            for (int i = 0; i < 4; i++) {
                if (adder.getSums().get(i).getValue()) {
                    actualSum |= (1 << i);
                }
            }
            int actualCout = adder.getGlobalCout().getValue() ? 1 : 0;

            System.out.printf("A: %04d B: %04d Cin: %d -> Sum: %04d (exp=%04d), Cout: %d (exp=%d)\n",
                a, b, cin, actualSum, expectedSum, actualCout, expectedCout);

            assert actualSum == expectedSum : "Sum mismatch!";
            assert actualCout == expectedCout : "Cout mismatch!";
        }

        System.out.println("All tests passed!");
    }
}