// EXERCISE 4: Leap Year Checker
// Objective: Apply nested conditional logic.

import java.util.Scanner;

public class LeapYearChecker4 {
    public static void main(String[] args) {
        System.out.println("=== Leap Year Checker ===");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a year: ");
        int year = scanner.nextInt();

            boolean isLeapYear = false;
            if (year % 4 == 0) {
                if (year % 100 == 0) {
                    if (year % 400 == 0) {
                        isLeapYear = true;
                    } else {
                        isLeapYear = false;
                    }
                } else {
                    isLeapYear = true;
                }
            } else {
                isLeapYear = false;
            }

            if (isLeapYear) {
                System.out.println("\nResult: " + year + " is a LEAP YEAR.");
            } else {
                System.out.println("\nResult: " + year + " is NOT a leap year.");
            }
        scanner.close();
    }
}