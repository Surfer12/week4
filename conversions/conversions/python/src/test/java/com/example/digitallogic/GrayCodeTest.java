package com.example.digitallogic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Map;

public class GrayCodeTest {

    @Test
    public void testApplyGrayCode() {
        GrayCode grayCode = new GrayCode();
        TruthTable tt = new TruthTable("1011");
        List<Map<String, String>> grayCodedTable = grayCode.applyGrayCode(tt.getTable());
        assertEquals("1", grayCodedTable.get(0).get("gray"));
        assertEquals("0", grayCodedTable.get(1).get("gray"));
    }

    @Test
    public void testBinaryToGrayCode() {
        GrayCode grayCode = new GrayCode();
        assertEquals("1100", grayCode.binaryToGrayCode("1010"));
    }
}