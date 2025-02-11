package conversions.digitallogic.fulladderproject.logic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CLA_GeneratePropagateTest {

    CLA_GeneratePropagate gp = new CLA_GeneratePropagate();

    @Test
    void calculateGenerate() {
        assertTrue(gp.calculateGenerate(true, true));
        assertFalse(gp.calculateGenerate(true, false));
        assertFalse(gp.calculateGenerate(false, true));
        assertFalse(gp.calculateGenerate(false, false));
    }

    @Test
    void calculatePropagate() {
        assertFalse(gp.calculatePropagate(true, true));
        assertTrue(gp.calculatePropagate(true, false));
        assertTrue(gp.calculatePropagate(false, true));
        assertFalse(gp.calculatePropagate(false, false));
    }
}