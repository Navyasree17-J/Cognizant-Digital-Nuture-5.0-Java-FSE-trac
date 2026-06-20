// EXERCISE 19: Binary Search
// Objective: Implement the divide-and-conquer binary search algorithm on a sorted array.

import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch19 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Binary Search Demonstration ===");
        System.out.println("Note: The array will be automatically sorted for this search.");

        System.out.print("Enter the number of elements: ");
        int size = scanner.nextInt();

        if (size <= 0) {
            System.out.println("Error: Array size must be greater than 0.");
            return;
        }

        int[] numbers = new int[size];
        System.out.println("Enter " + size + " integers (in any order):");
        for (int i = 0; i < size; i++) {
            System.out.print("Element " + i + ": ");
            numbers[i] = scanner.nextInt();
        }

        Arrays.sort(numbers);
        System.out.print("\nSorted Array used for search: ");
        System.out.println(Arrays.toString(numbers));

        System.out.print("Enter the target number to search for: ");
        int target = scanner.nextInt();

        int low = 0;
        int high = numbers.length - 1;
        int foundIndex = -1; 

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (numbers[mid] == target) {
                foundIndex = mid; 
                break;            
            } 
            else if (target < numbers[mid]) {
                high = mid - 1;
            } 
            else {
                low = mid + 1;
            }
        }

        System.out.println("\n--- Search Results ---");
        if (foundIndex != -1) {
            System.out.println("Success! Target " + target + " was found at index position: " + foundIndex);
        } else {
            System.out.println("Target " + target + " was NOT found in the sorted array.");
        }
    }
}