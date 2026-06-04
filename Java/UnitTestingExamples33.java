// EXERCISE 33: Unit Testing (JUnit Concept)
// Objective: Understand the mechanics of automated unit testing and data assertions.

import java.util.Scanner;

// 1. The Unit Under Test: A clean class containing business logic methods
class BasicCalculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }
}

// 2. The Test Class: Contains dedicated test methods to validate the logic
public class UnitTestingExamples33 {
        public static void assertEquals(int expected, int actual, String testName) {
        if (expected == actual) {
            System.out.println("✅ PASS: " + testName);
        } else {
            System.out.println("❌ FAIL: " + testName);
            System.out.println("   -> Expected value: " + expected);
            System.out.println("   -> Actual returned value: " + actual);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BasicCalculator calculator = new BasicCalculator();

        System.out.println("=== Automated Unit Testing Suite ===");
        
        System.out.println("\nRunning automated structural tests...");
        
        assertEquals(10, calculator.add(7, 3), "testAdditionWithPositiveNumbers()");
        
        assertEquals(-2, calculator.add(-5, 3), "testAdditionWithNegativeNumbers()");
        
        assertEquals(20, calculator.multiply(4, 5), "testMultiplication()");


        System.out.println("\n--- Step 2: Dynamic Live Assertion ---");
        System.out.println("Let's simulate a live test assertion. Enter two numbers to test the add() method:");
        
        System.out.print("Enter integer A: ");
        int inputA = scanner.nextInt();
        
        System.out.print("Enter integer B: ");
        int inputB = scanner.nextInt();
        
        int expectedSum = inputA + inputB;

        System.out.print("\nNow enter your HYPOTHETICAL expected answer to test validation (Type " + expectedSum + " to PASS, or type anything else to deliberately FAIL): ");
        int userHypothesis = scanner.nextInt();

        System.out.println("\nExecuting Dynamic Test Assetion...");
        int actualResult = calculator.add(inputA, inputB);        
        assertEquals(userHypothesis, actualResult, "dynamicUserAdditionTest()");
    }
}