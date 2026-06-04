// EXERCISE 21: Custom Exception
// Objective: Create, throw, and handle custom user-defined exceptions.

import java.util.Scanner;

class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message); 
    }
}

public class CustomExceptionExample21 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Voting Eligibility Checker ===");

        try {
            System.out.print("Enter your age: ");
            int age = scanner.nextInt();
            if (age < 18) {
                throw new InvalidAgeException("Access Denied: You must be 18 or older to vote.");
            }
            System.out.println("\n🎉 Access Granted! You are eligible to vote.");

        } 
        catch (InvalidAgeException e) {
            System.out.println("\n⚠️ Custom Exception Caught!");
            System.out.println("Error Message: " + e.getMessage());
        } 
        catch (Exception e) {
            System.out.println("\nAn unexpected error occurred: " + e.getMessage());
        }

        System.out.println("\nProgram continues running smoothly past the try-catch block...");
    }
}