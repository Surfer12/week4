package conversions.digitallogic.fulladderproject.logic;

public class CLA_GeneratePropagate {

    /**
     * Calculates the Generate signal (Gi = Ai AND Bi).
     * @param a Input bit A
     * @param b Input bit B
     * @return Generate signal
     */
    public boolean calculateGenerate(boolean a, boolean b) {
        return a && b;
    }

    /**
     * Calculates the Propagate signal (Pi = Ai XOR Bi).
     * @param a Input bit A
     * @param b Input bit B
     * @return Propagate signal
     */
    public boolean calculatePropagate(boolean a, boolean b) {
        return a ^ b;
    }
}