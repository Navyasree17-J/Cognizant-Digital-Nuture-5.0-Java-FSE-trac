// EXERCISE 19: Binary Search
// Objective: Implement the divide-and-conquer binary search algorithm on a sorted array.

import java.util.Scanner;
import java.util.Arrays;

public class BinarySearch19 {
    public static void main(String[] args) {
        // Declaring the Scanner the traditional way for dynamic user input
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Binary Search Demonstration ===");
        System.out.println("Note: The array will be automatically sorted for this search.");

        // 1. Prompt the user to specify the size of the array
        System.out.print("Enter the number of elements: ");
        int size = scanner.nextInt();

        if (size <= 0) {
            System.out.println("Error: Array size must be greater than 0.");
            return;
        }

        // 2. Instantiate and populate the array
        int[] numbers = new int[size];
        System.out.println("Enter " + size + " integers (in any order):");
        for (int i = 0; i < size; i++) {
            System.out.print("Element " + i + ": ");
            numbers[i] = scanner.nextInt();
        }

        // 3. CRITICAL STEP: Sort the array so Binary Search works correctly
        Arrays.sort(numbers);
        System.out.print("\nSorted Array used for search: ");
        System.out.println(Arrays.toString(numbers));

        // 4. Prompt the user for the Target Value to search for
        System.out.print("Enter the target number to search for: ");
        int target = scanner.nextInt();

        // 5. Initialize search boundary pointers
        int low = 0;
        int high = numbers.length - 1;
        int foundIndex = -1; // Default to -1 (Not Found)

        // 6. Execute the Binary Search Loop
        while (low <= high) {
            // Find the midpoint between low and high boundaries
            int mid = low + (high - low) / 2;

            if (numbers[mid] == target) {
                foundIndex = mid; // Match found!
                break;            // Exit the loop immediately
            } 
            else if (target < numbers[mid]) {
                // Target is smaller, so it must be in the left half.
                // Shift the 'high' boundary down past the midpoint.
                high = mid - 1;
            } 
            else {
                // Target is larger, so it must be in the right half.
                // Shift the 'low' boundary up past the midpoint.
                low = mid + 1;
            }
        }

        // 7. Display the results
        System.out.println("\n--- Search Results ---");
        if (foundIndex != -1) {
            System.out.println("Success! Target " + target + " was found at index position: " + foundIndex);
        } else {
            System.out.println("Target " + target + " was NOT found in the sorted array.");
        }
    }
}