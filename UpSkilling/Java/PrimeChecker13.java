// EXERCISE 13: Prime Number Checker
// Objective: Apply loop optimization and flag-based conditional logic.

import java.util.Scanner;

public class PrimeChecker13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Prime Number Checker ===");

        System.out.print("Enter a positive whole number: ");
        int number = scanner.nextInt();

        boolean isPrime = true;

        if (number <= 1) {
            isPrime = false;
        } else {
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    isPrime = false; 
                    break; 
                }
            }
        }

        // 3. Display the result
        if (isPrime) {
            System.out.println("\nResult: " + number + " is a PRIME number. ⭐");
        } else {
            System.out.println("\nResult: " + number + " is NOT a prime number.");
        }
    }
}