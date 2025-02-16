package com.example.digitallogic;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DigitalLogicToolkit {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a decimal number: ");
        int decimal = scanner.nextInt();

        NumberConverter numberConverter = new NumberConverter();
        String binary = numberConverter.decimalToBinary(decimal);
        System.out.println("Binary: " + binary);

        TruthTable truthTable = new TruthTable(binary);
        System.out.println("Truth Table: " + truthTable.getTable());

        // KMAP Example - using truth table constructor
        System.out.print("Enter the number of variables for the K-Map (2-4): ");
        int numVars = scanner.nextInt();
        TruthTable kmapTruthTable = new TruthTable(numVars);

        // Prompt for output of each row in truth table
        for (int i = 0; i < Math.pow(2, numVars); i++) {
            System.out.printf("Enter output for %s: ", kmapTruthTable.getVariables(i));
            kmapTruthTable.setOutput(i, scanner.next());
        }

        KMap kmap = new KMap(numVars);
        kmap.populateFromTruthTable(kmapTruthTable);
        System.out.println("K-Map:");
        System.out.println(kmap);
        System.out.println("Simplified Boolean Expression: " + kmap.simplify());

        GrayCode grayCode = new GrayCode();
        List<Map<String, String>> grayCodedTable = grayCode.applyGrayCode(truthTable.getTable());
        System.out.println("Gray Coded Truth Table: " + grayCodedTable);

        scanner.close();
    }
}