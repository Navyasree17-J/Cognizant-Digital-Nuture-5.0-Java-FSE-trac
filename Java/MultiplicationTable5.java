// EXERCISE 5: Multiplication Table
// Objective: Implement loops.

import java.util.Scanner;

public class MultiplicationTable5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Multiplication Table ===");

        System.out.print("Enter a number to print its table: ");
        int number = scanner.nextInt();

        System.out.println("\nMultiplication Table for " + number + ":");
        System.out.println("---------------------------------");

        for (int i = 1; i <= 10; i++) {
            
            int result = number * i;
            
            System.out.println(number + " x " + i + " = " + result);
        }
        
        System.out.println("---------------------------------");
        
    }
}