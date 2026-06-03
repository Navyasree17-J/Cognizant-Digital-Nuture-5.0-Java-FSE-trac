// EXERCISE 12: Fibonacci Series
// Objective: Master loop variable updates and sequence generation.

import java.util.Scanner;

public class FibonacciSeries12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Fibonacci Series Generator ===");
        System.out.print("Enter the number of terms you want to print: ");
        int terms = scanner.nextInt();
        if (terms <= 0) {
            System.out.println("Please enter a number greater than 0.");
        } else {
            System.out.println("\nFibonacci Series up to " + terms + " terms:");
            
            int firstTerm = 0;
            int secondTerm = 1;
            for (int i = 1; i <= terms; i++) {
                System.out.print(firstTerm + " ");
                int nextTerm = firstTerm + secondTerm;
                                firstTerm = secondTerm;
                secondTerm = nextTerm;
            }
            System.out.println(); 
        }
    }
}