package com.junit.advanced;

/**
 * Exercise 4: Production class designed to throw exceptions on invalid input.
 */
public class ExceptionThrower {

    public int throwException(int dividend, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("❌ Illegal Action: Cannot divide by zero!");
        }
        return dividend / divisor;
    }
}