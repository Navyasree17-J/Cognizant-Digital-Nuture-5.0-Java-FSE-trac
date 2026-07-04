package com.junit.testing;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Exercise 2: JUnit test verification class.
 */
public class CalculatorTest {

    @Test
    public void testAdd() {
        Calculator calc = new Calculator();
        // Verifies that 2 + 3 equals 5
        assertEquals(5, calc.add(2, 3));
    }

    @Test
    public void testMultiply() {
        Calculator calc = new Calculator();
        // Verifies that 4 * 3 equals 12
        assertEquals(12, calc.multiply(4, 3));
    }
}