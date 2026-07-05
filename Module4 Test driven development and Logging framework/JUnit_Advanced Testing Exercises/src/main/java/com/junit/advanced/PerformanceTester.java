package com.junit.advanced;

/**
 * Exercise 5: Production class simulating a performance-heavy task.
 */
public class PerformanceTester {

    /**
     * Simulates a process by pausing thread execution for a specific duration in milliseconds.
     */
    public void performTask(int durationMillis) {
        try {
            Thread.sleep(durationMillis); 
        } catch (InterruptedException e) {
            System.out.println("❌ Process was interrupted unexpectedly.");
        }
    }
}