// EXERCISE 3: Even or Odd Checker
// Objective: Utilize conditional statements.

import java.util.Scanner;

public class EvenOrOddChecker3 {
    public static void main(String[] args) {
        // Create a Scanner object to read user input from the terminal
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Even or Odd Checker ===");

        System.out.print("Enter any whole number: ");
        int number = scanner.nextInt();

        if (number % 2 == 0) {
            System.out.println("\nResult: " + number + " is an EVEN number.");
        } else {
            System.out.println("\nResult: " + number + " is an ODD number.");
        }
        scanner.close();
    }
}