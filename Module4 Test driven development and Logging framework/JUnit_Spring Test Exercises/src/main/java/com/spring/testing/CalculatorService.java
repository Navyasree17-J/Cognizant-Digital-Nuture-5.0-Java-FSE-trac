package com.spring.testing;

import org.springframework.stereotype.Service;

/**
 * Exercise 1: Basic Service Method logic managed inside Spring Context Container.
 */
@Service // Registers this class as a managed bean in the Spring Container [cite: 38]
public class CalculatorService {

    /**
     * Service method performing basic summation arithmetic logic[cite: 36].
     */
    public int add(int a, int b) {
        return a + b; // [cite: 42]
    }
}