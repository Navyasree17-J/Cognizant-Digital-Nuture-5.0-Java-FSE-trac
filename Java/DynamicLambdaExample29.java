// EXERCISE 29: Lambda Expressions
// Objective: Implement a Functional Interface using dynamic inline Lambda Expressions.

import java.util.Scanner;

@FunctionalInterface
interface MathOperation {
    double calculate(double a, double b);
}

public class DynamicLambdaExample29 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Dynamic Lambda Expression Calculator ===");

        System.out.print("Enter the first number (X): ");
        double num1 = scanner.nextDouble();

        System.out.print("Enter the second number (Y): ");
        double num2 = scanner.nextDouble();

        System.out.println("\n--- Processing via Inline Lambdas ---");

        MathOperation addition = (a, b) -> a + b;
        MathOperation subtraction = (a, b) -> a - b;
        MathOperation multiplication = (a, b) -> a * b;
        MathOperation division = (a, b) -> {
            if (b == 0) {
                System.out.println("⚠️ Math Error: Cannot divide by zero!");
                return 0;
            }
            return a / b;
        };

        System.out.println("Addition Result (X + Y):       " + addition.calculate(num1, num2));
        System.out.println("Subtraction Result (X - Y):    " + subtraction.calculate(num1, num2));
        System.out.println("Multiplication Result (X * Y): " + multiplication.calculate(num1, num2));
        System.out.println("Division Result (X / Y):       " + division.calculate(num1, num2));
    }
}