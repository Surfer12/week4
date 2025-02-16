package com.example.digitallogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrayCode {

    public List<Map<String, String>> applyGrayCode(List<Map<String, String>> truthTable) {
        List<Map<String, String>> grayCodedTable = new ArrayList<>();
        for (Map<String, String> row : truthTable) {
            Map<String, String> newRow = new HashMap<>(row);
            try {
                int bitVal = Integer.parseInt(row.get("bit"));
                int grayVal = bitVal ^ (bitVal >> 1);  // Core Gray code conversion
                newRow.put("gray", String.valueOf(grayVal));
            } catch (NumberFormatException e) {
                newRow.put("gray", row.get("bit")); // Keep original if not an integer
            }
            grayCodedTable.add(newRow);
        }
        return grayCodedTable;
    }

     public String binaryToGrayCode(String binary) {
        String gray = "";
        gray += binary.charAt(0); // MSB remains the same

        for (int i = 1; i < binary.length(); i++) {
            // XOR current bit with previous bit
            gray += (binary.charAt(i - 1) == binary.charAt(i)) ? '0' : '1';
        }
        return gray;
    }
}