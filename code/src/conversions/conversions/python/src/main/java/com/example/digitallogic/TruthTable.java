package com.example.digitallogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TruthTable {
    private List<Map<String, String>> table;

    public TruthTable(String binaryString) {
        table = new ArrayList<>();
        for (int i = 0; i < binaryString.length(); i++) {
            Map<String, String> row = new HashMap<>();
            row.put("position", String.valueOf(i));
            row.put("bit", String.valueOf(binaryString.charAt(i)));
            table.add(row);
        }
    }

    public TruthTable(int numVariables) { // Overloaded constructor for K-Map
        table = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, numVariables); i++) {
            Map<String, String> row = new HashMap<>();
            row.put("variables", decimalToBinary(i, numVariables));
            row.put("output", "0"); // Initialize
            table.add(row);
        }
    }

    public List<Map<String, String>> getTable() {
        return table;
    }

    public void setOutput(int row, String output) {
        table.get(row).put("output", output);
    }

    public String getOutput(int row) {
        return table.get(row).get("output");
    }

    public String getVariables(int row) {
        return table.get(row).get("variables");
    }

    public String decimalToBinary(int decimal, int numVariables) {
        if (decimal >= 0) {
            String bin = Integer.toBinaryString(decimal);
            // Pad with leading zeros
            while (bin.length() < numVariables)
                bin = "0" + bin;

            return bin;
        } else {
            // Two's complement for negative numbers
            return Integer.toBinaryString(decimal); // Java's toBinaryString handles two's complement
        }
    }
}