package test;

import core.FullAdderCircuit;
import java.util.Random;

/**
 * Simple performance test measuring how quickly we can run many random input cycles.
 */
public class BenchmarkTest {

    public static void main(String[] args) {
        FullAdderCircuit fa = new FullAdderCircuit();
        Random rand = new Random();

        long startTime = System.nanoTime();

        final int ITERATIONS = 1_000_000;
        for (int i = 0; i < ITERATIONS; i++) {
            boolean A = rand.nextBoolean();
            boolean B = rand.nextBoolean();
            boolean C = rand.nextBoolean();
            fa.setInputs(A, B, C);
            fa.evaluate();
            // read outputs to ensure it's not optimized away
            boolean sum = fa.getSum();
            boolean cout = fa.getCout();
        }

        long endTime = System.nanoTime();
        double ms = (endTime - startTime) / 1_000_000.0;

        System.out.printf("Completed %,d iterations in %.2f ms\n", ITERATIONS, ms);
        System.out.printf("Approx. speed: %.2f thousand ops/ms\n", (ITERATIONS / ms));
    }
}