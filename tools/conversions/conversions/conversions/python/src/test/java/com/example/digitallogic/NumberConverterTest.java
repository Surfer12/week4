package com.example.digitallogic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NumberConverterTest {

    @Test
    public void testDecimalToBinaryPositive() {
        NumberConverter converter = new NumberConverter();
        assertEquals("1010", converter.decimalToBinary(10));
        assertEquals("1111", converter.decimalToBinary(15));
    }

    @Test
    public void testDecimalToBinaryNegative() {
        NumberConverter converter = new NumberConverter();
        assertEquals("11111111111111111111111111110110", converter.decimalToBinary(-10));
    }

    @Test
    public void testBinaryToDecimalPositive() {
        NumberConverter converter = new NumberConverter();
        assertEquals(10, converter.binaryToDecimal("1010"));
        assertEquals(15, converter.binaryToDecimal("1111"));
    }

    @Test
    public void testBinaryToDecimalNegative() {
        NumberConverter converter = new NumberConverter();
        assertEquals(-10, converter.binaryToDecimal("11111111111111111111111111110110"));
    }

    @Test
    public void testDecimalToHexPositive() {
        NumberConverter converter = new NumberConverter();
        assertEquals("a", converter.decimalToHex(10));
        assertEquals("ff", converter.decimalToHex(255));
    }

    @Test
    public void testDecimalToHexNegative() {
        NumberConverter converter = new NumberConverter();
        assertEquals("-a", converter.decimalToHex(-10));
    }

    @Test
    public void testHexToDecimalPositive() {
        NumberConverter converter = new NumberConverter();
        assertEquals(10, converter.hexToDecimal("a"));
        assertEquals(255, converter.hexToDecimal("ff"));
    }

    @Test
    public void testHexToDecimalNegative() {
        NumberConverter converter = new NumberConverter();
        assertEquals(-10, converter.hexToDecimal("-a"));
    }
}