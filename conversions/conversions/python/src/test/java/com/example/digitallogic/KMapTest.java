package com.example.digitallogic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KMapTest {

    @Test
    public void testKMapCreation2Var() {
        KMap kmap = new KMap(2);
        assertEquals(2, kmap.getNumVariables());
        assertEquals("0", kmap.getCellValue(0, 0));
        kmap.setCellValue(0, 1, "1");
        assertEquals("1", kmap.getCellValue(0, 1));
    }

    @Test
    public void testSimplify2Var() {
        KMap kmap = new KMap(2);
        TruthTable tt = new TruthTable(2);
        tt.setOutput(0, "1"); // 00
        tt.setOutput(1, "1"); // 01
        tt.setOutput(3, "1"); // 11
        kmap.populateFromTruthTable(tt);
        String simplified = kmap.simplify();
        assertTrue(simplified.equals("B + A'") || simplified.equals("A' + B"));
    }
}