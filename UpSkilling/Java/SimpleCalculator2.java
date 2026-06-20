// EXERCISE 2: Simple Calculator
// Objective: Practice arithmetic operations and user input.

import java.util.Scanner;

public class SimpleCalculator2 {
    public static void main(String[] args) {
        // Create a standard Scanner object to read terminal input
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Simple Calculator ===");

        // 1. Prompt the user to enter two numbers
        System.out.print("Enter the first number: ");
        double num1 = scanner.nextDouble();

        System.out.print("Enter the second number: ");
        double num2 = scanner.nextDouble();

        // 2. Ask the user to choose an operation
        System.out.println("\nChoose an operation:");
        System.out.println("+ : Addition");
        System.out.println("- : Subtraction");
        System.out.println("* : Multiplication");
        System.out.println("/ : Division");
        System.out.print("Enter your choice (+, -, *, /): ");
        char operation = scanner.next().charAt(0);

        double result = 0;
        boolean validOperation = true;

        // Using modern Java Switch Expressions to eliminate warnings and "break" keywords
        switch (operation) {
            case '+' -> result = num1 + num2;
            case '-' -> result = num1 - num2;
            case '*' -> result = num1 * num2;
            case '/' -> {
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("\nError: Division by zero is not allowed.");
                    validOperation = false;
                }
            }
            default -> {
                System.out.println("\nError: Invalid operation choice.");
                validOperation = false;
            }
        }
        if (validOperation) {
            System.out.println("\nResult: " + num1 + " " + operation + " " + num2 + " = " + result);
        }
        scanner.close();
    }
}