package com.example.digitallogic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Map;

public class TruthTableTest {
    @Test
    public void testTruthTableFromBinary() {
        TruthTable tt = new TruthTable("1011");
        List<Map<String, String>> table = tt.getTable();
        assertEquals(4, table.size());
        assertEquals("0", table.get(0).get("position"));
        assertEquals("1", table.get(0).get("bit"));
        assertEquals("1", table.get(1).get("position"));
        assertEquals("0", table.get(1).get("bit"));
    }

    @Test
    public void testTruthTableConstructorKMap() {
        TruthTable tt = new TruthTable(3); // 3 variable truth table
        assertEquals(8, tt.getTable().size());
        assertEquals("000", tt.getVariables(0));
        assertEquals("0", tt.getOutput(0));
    }
}