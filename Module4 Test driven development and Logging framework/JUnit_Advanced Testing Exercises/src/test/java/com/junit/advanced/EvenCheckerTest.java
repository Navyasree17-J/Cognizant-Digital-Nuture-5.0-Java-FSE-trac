package com.junit.advanced;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Exercise 1: Parameterized Testing using Pure JUnit 5 Framework.
 */
public class EvenCheckerTest {

    private final EvenChecker checker = new EvenChecker();

    /**
     * Test Case A: Validating dynamic stream of EVEN integers
     */
    @ParameterizedTest
    @ValueSource(ints = { 2, 4, 10, 42, -8, 0 }) // Modern JUnit 5 source injector
    public void verifyEvenNumbers(int number) {
        System.out.println("🧪 [JUnit 5 Parameterized] Verifying that " + number + " is EVEN.");
        assertTrue(checker.isEven(number), "❌ Failure: " + number + " should be evaluated as even!");
    }

    /**
     * Test Case B: Validating dynamic stream of ODD integers
     */
    @ParameterizedTest
    @ValueSource(ints = { 1, 3, 7, 15, -11 }) // Another set of dynamic inputs
    public void verifyOddNumbers(int number) {
        System.out.println("🧪 [JUnit 5 Parameterized] Verifying that " + number + " is ODD.");
        assertFalse(checker.isEven(number), "❌ Failure: " + number + " should be evaluated as odd!");
    }
}