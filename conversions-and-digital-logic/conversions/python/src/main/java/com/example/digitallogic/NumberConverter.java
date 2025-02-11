package com.example.digitallogic;

public class NumberConverter {

    public String decimalToBinary(int decimal) {
        if (decimal >= 0) {
            return Integer.toBinaryString(decimal);
        } else {
            // Two's complement for negative numbers
            return Integer.toBinaryString(decimal); // Java's toBinaryString handles two's complement
        }
    }

    public int binaryToDecimal(String binary) {
        return Integer.parseInt(binary, 2);  // Handles Two's complement
    }

    public String decimalToHex(int decimal) {
        if (decimal >= 0) {
            return Integer.toHexString(decimal);
        } else {
            return "-" + Integer.toHexString(Math.abs(decimal));
        }
    }

    public int hexToDecimal(String hex) {
        return Integer.parseInt(hex, 16);
    }
}