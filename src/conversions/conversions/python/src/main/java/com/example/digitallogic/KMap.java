package com.example.digitallogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KMap {

    private int numVariables;
    private String[][] kmap;
    private List<String> variableNames;

    public KMap(int numVariables) {
        if (numVariables < 2 || numVariables > 4) { // Limit 2-4 for now, can expand.
            throw new IllegalArgumentException("Number of variables must be between 2 and 4.");
        }
        this.numVariables = numVariables;
        int rows = (numVariables == 2) ? 2 : (numVariables == 3 ? 2 : 4);
        int cols = (numVariables == 2) ? 2 : 4;

        this.kmap = new String[rows][cols];
        this.variableNames = generateVariableNames(numVariables);
        initializeKMap();
    }

    private List<String> generateVariableNames(int numVariables) {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < numVariables; i++) {
            names.add(String.valueOf((char)('A' + i)));
        }
        return names;
    }

    private void initializeKMap() {
        // Initialize all cells to "0"
        for (int i = 0; i < kmap.length; i++) {
            Arrays.fill(kmap[i], "0");
        }
    }

    public void populateFromTruthTable(TruthTable truthTable) {
        if (truthTable.getTable().size() != Math.pow(2, numVariables))
            throw new IllegalArgumentException("Truth Table size mismatch with K-Map");

        for (int i = 0; i < truthTable.getTable().size(); i++) {
            String variables = truthTable.getVariables(i);
            String output = truthTable.getOutput(i);

            int row = getRowIndex(variables);
            int col = getColIndex(variables);
            kmap[row][col] = output;
        }
    }

    private int getRowIndex(String variables) {
        if (numVariables == 2)
            return Integer.parseInt(variables.substring(0, 1), 2); // A
        else if (numVariables == 3)
            return Integer.parseInt(variables.substring(0, 1), 2); // A
        else { // numVariables == 4
            String grayCode = variables.substring(0, 2);
            return Integer.parseInt(grayCodeToBinary(grayCode), 2); // AB -> row
        }
    }

    private int getColIndex(String variables) {
        if (numVariables == 2) {
            return Integer.parseInt(grayCodeToBinary(variables.substring(1, 2)), 2); // B
        } else if (numVariables == 3) {
            String grayCode = variables.substring(1, 3);
            return Integer.parseInt(grayCodeToBinary(grayCode), 2);
        } else {
            String grayCode = variables.substring(2, 4);
            return Integer.parseInt(grayCodeToBinary(grayCode), 2); // CD -> col
        }
    }

    private String grayCodeToBinary(String grayCode) {
        String binary = "";
        binary += grayCode.charAt(0); // MSB same

        for (int i = 1; i < grayCode.length(); i++) {
            if (grayCode.charAt(i) == '0')
                binary += binary.charAt(i - 1);
            else
                binary += (binary.charAt(i - 1) == '0' ? '1' : '0'); // Invert
        }
        return binary;
    }

    public String getCellValue(int row, int col) {
        return kmap[row][col];
    }

    public void setCellValue(int row, int col, String value) {
        kmap[row][col] = value;
    }

    public int getNumVariables() {
        return numVariables;
    }

    public String[][] getKMap() {
        return kmap;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Top header
        sb.append(" ".repeat(variableNames.get(0).length() + 3)); // Initial padding
        if (numVariables == 2) {
            sb.append(variableNames.get(1));
        } else if (numVariables == 3) {
            sb.append(variableNames.get(1)).append(variableNames.get(2));
        } else { // numVariables == 4
            sb.append(variableNames.get(2)).append(variableNames.get(3));
        }

        List<String> colHeaders = generateColumnHeaders();
        for (String header : colHeaders)
            sb.append(String.format("%-" + (header.length() + 2) + "s", header)); // Spacing
        sb.append("\n");

        // Kmap Body
        List<String> rowHeaders = generateRowHeaders();
        for (int i = 0; i < kmap.length; i++) {
            sb.append(String.format("%-" + (variableNames.get(0).length() + 3) + "s", rowHeaders.get(i)));
            for (int j = 0; j < kmap[0].length; j++) {
                sb.append(String.format("%-" + (colHeaders.get(j).length() + 2) + "s", kmap[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private List<String> generateColumnHeaders() {
        List<String> colHeaders = new ArrayList<>();
        if (numVariables == 2) {
            colHeaders.add("0");
            colHeaders.add("1");
        } else if (numVariables == 3) {
            colHeaders.add("00");
            colHeaders.add("01");
            colHeaders.add("11");
            colHeaders.add("10");
        } else {
            colHeaders.add("00");
            colHeaders.add("01");
            colHeaders.add("11");
            colHeaders.add("10");
        }
        return colHeaders;
    }

    private List<String> generateRowHeaders() {
        List<String> rowHeaders = new ArrayList<>();
        if (numVariables == 2 || numVariables == 3) {
            rowHeaders.add("0");
            rowHeaders.add("1");
        } else {
            rowHeaders.add("00");
            rowHeaders.add("01");
            rowHeaders.add("11");
            rowHeaders.add("10");
        }
        return rowHeaders;
    }
}