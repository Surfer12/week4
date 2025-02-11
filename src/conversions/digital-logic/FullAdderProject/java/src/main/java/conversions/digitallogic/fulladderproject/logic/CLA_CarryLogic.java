package conversions.digitallogic.fulladderproject.logic;

public class CLA_CarryLogic {

    /**
     * Calculates the carry signals for a 4-bit CLA.
     * @param c0 Carry-in (C0)
     * @param g Generate signals (G0, G1, G2, G3)
     * @param p Propagate signals (P0, P1, P2, P3)
     * @return Array of carry signals (C1, C2, C3, C4)
     */
    public boolean[] calculateCarries(boolean c0, boolean[] g, boolean[] p) {
        boolean[] carries = new boolean[4];
        carries[0] = g[0] || (p[0] && c0); // C1
        carries[1] = g[1] || (p[1] && g[0]) || (p[1] && p[0] && c0); // C2
        carries[2] = g[2] || (p[2] && g[1]) || (p[2] && p[1] && g[0]) || (p[2] && p[1] && p[0] && c0); // C3
        carries[3] = g[3] || (p[3] && g[2]) || (p[3] && p[2] && g[1]) || (p[3] && p[2] && p[1] && g[0]) || (p[3] && p[2] && p[1] && p[0] && c0); // C4
        return carries;
    }
}