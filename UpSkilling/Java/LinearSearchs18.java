// EXERCISE 18: Linear Search
// Objective: Implement sequential array traversal and targeted value lookup.

import java.util.Scanner;

public class LinearSearchs18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Linear Search Demonstration ===");
        System.out.print("Enter the number of elements in the array: ");
        int size = scanner.nextInt();

        if (size <= 0) {
            System.out.println("Error: Array size must be greater than 0.");
            return;
        }
        int[] numbers = new int[size];
        System.out.println("Enter " + size + " integers:");
        for (int i = 0; i < size; i++) {
            System.out.print("Element " + i + ": ");
            numbers[i] = scanner.nextInt();
        }

        System.out.print("\nEnter the target number to search for: ");
        int target = scanner.nextInt();

        int foundIndex = -1; 
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == target) {
                foundIndex = i; 
                break;          
            }
        }

        System.out.println("\n--- Search Results ---");
        if (foundIndex != -1) {
            System.out.println("Success! Target " + target + " was found at index position: " + foundIndex);
        } else {
            System.out.println("Target " + target + " was NOT found anywhere in the array.");
        }
    }
}