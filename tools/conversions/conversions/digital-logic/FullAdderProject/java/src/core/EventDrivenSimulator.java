package core;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implements a simple event-driven evaluation. Whenever a wire changes,
 * we queue re-evaluation of gates connected to that wire.
 */
public class EventDrivenSimulator {

    private static Queue<Gate> evalQueue = new LinkedList<>();

    public static void queueGateEvaluation(Gate gate) {
        if (!evalQueue.contains(gate)) {
            evalQueue.offer(gate);
        }
    }

    /**
     * Processes the queue until stable: no more gate updates.
     */
    public static void runSimulation() {
        while (!evalQueue.isEmpty()) {
            Gate gate = evalQueue.poll();
            gate.evaluate();  // This may cause wires to update, triggering more gates
        }
    }
}