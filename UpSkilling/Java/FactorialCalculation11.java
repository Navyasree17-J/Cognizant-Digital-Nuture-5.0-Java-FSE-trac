// EXERCISE 11: Factorial Calculator
// Objective: Use loop patterns for mathematical calculation and accumulation.

import java.util.Scanner;

public class FactorialCalculation11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Factorial Calculator ===");

        System.out.print("Enter a positive whole number: ");
        int number = scanner.nextInt();

        if (number < 0) {
            System.out.println("Error: Factorial is not defined for negative numbers.");
        } else {
            long factorial = 1;

            for (int i = 1; i <= number; i++) {
                factorial = factorial * i; 
            }

            System.out.println("\nResult: " + number + "! = " + factorial);
        }
    }
}