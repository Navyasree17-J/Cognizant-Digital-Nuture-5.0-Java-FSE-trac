// EXERCISE 9: Grade Calculator
// Objective: Use conditional statements to determine grades.

import java.util.Scanner;

public class GradeCalculator9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Dynamic Grade Calculator ===");

        System.out.print("Enter the student's marks (0 - 100): ");
        double marks = scanner.nextDouble();

        char grade;

        if (marks < 0 || marks > 100) {
            System.out.println("\nError: Invalid score entered. Marks must be between 0 and 100.");
        } else {
            if (marks >= 90) {
                grade = 'A'; // 90-100: A
            } else if (marks >= 80) {
                grade = 'B'; // 80-89: B
            } else if (marks >= 70) {
                grade = 'C'; // 70-79: C
            } else if (marks >= 60) {
                grade = 'D'; // 60-69: D
            } else {
                grade = 'F'; // Below 60: F
            }

            System.out.println("\n--- Evaluation Result ---");
            System.out.println("Score: " + marks + "%");
            System.out.println("Assigned Grade: " + grade);
        }
    }
}