package com.junit.testing;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Exercise 4: Demonstrating the AAA Pattern, Setup (@Before), and Teardown (@After).
 */
public class ExecutionLifecycleTest {

    // A shared resource object acting as our test fixture
    private Calculator calc;

    /**
     * SETUP METHOD
     * Runs automatically BEFORE each test method execution.
     */
    @Before
    public void setUp() {
        System.out.println("⚙️ [SETUP] @Before: Initializing a clean Calculator instance.");
        calc = new Calculator(); 
    }

    /**
     * TEARDOWN METHOD
     * Runs automatically AFTER each test method execution completes.
     */
    @After
    public void tearDown() {
        System.out.println("🧹 [TEARDOWN] @After: Cleaning up resources.\n");
        calc = null;
    }

    @Test
    public void testAdditionWithAAA() {
        System.out.println("🧪 [TEST RUN] Executing testAdditionWithAAA...");

        // 1. ARRANGE
        int input1 = 30;
        int input2 = 70;
        int expectedResult = 100;

        // 2. ACT
        int actualResult = calc.add(input1, input2);

        // 3. ASSERT
        System.out.println("   🔍 Verifying AAA Addition: " + input1 + " + " + input2 + " = " + actualResult);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testMultiplicationWithAAA() {
        System.out.println("🧪 [TEST RUN] Executing testMultiplicationWithAAA...");

        // 1. ARRANGE
        int input1 = 5;
        int input2 = 6;
        int expectedResult = 30;

        // 2. ACT
        int actualResult = calc.multiply(input1, input2);

        // 3. ASSERT
        System.out.println("   🔍 Verifying AAA Multiplication: " + input1 + " * " + input2 + " = " + actualResult);
        assertEquals(expectedResult, actualResult);
    }
}