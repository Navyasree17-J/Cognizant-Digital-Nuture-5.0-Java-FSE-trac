// EXERCISE 8: Operator Precedence
// Objective: Explore how Java evaluates expressions.

public class OperatorPrecedence8 {
    public static void main(String[] args) {
        System.out.println("=== Operator Precedence Demonstration ===");

        int resultA = 10 + 5 * 2; 
        int resultB = (10 + 5) * 2;
        int resultC = 20 - 4 / 2 + 3 % 2;

        System.out.println("Expression A: 10 + 5 * 2   = " + resultA);
        System.out.println("Expression B: (10 + 5) * 2 = " + resultB);
        System.out.println("Expression C: 20 - 4 / 2 + 3 % 2 = " + resultC);

        System.out.println();
        System.out.println("=== Explanation of Operations ==="); //
        
        System.out.println("• In Expression A, Multiplication (*) has higher precedence than Addition (+).");
        System.out.println("  Hence, 5 * 2 is evaluated first (10), then 10 + 10 = 20.");
        
        System.out.println("• In Expression B, Parentheses () override standard precedence rules.");
        System.out.println("  Hence, 10 + 5 is evaluated first (15), then 15 * 2 = 30.");
        
        System.out.println("• In Expression C, Multiplicative operators (/, %) are evaluated first from left to right.");
        System.out.println("  4 / 2 becomes 2, and 3 % 2 leaves a remainder of 1. Then addition and subtraction occur.");
        System.out.println("  Calculation: 20 - 2 + 1 = 19.");
    }
}