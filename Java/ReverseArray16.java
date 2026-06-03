 // EXERCISE 16: Reverse an Array
// Objective: Master in-place array manipulation using the two-pointer technique.

import java.util.Scanner;

public class ReverseArray16 {
    public static void main(String[] args) {
        // Declaring the Scanner the traditional way for dynamic user input
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Reverse an Array In-Place ===");

        // 1. Prompt the user to specify the size of the array
        System.out.print("Enter the number of elements: ");
        int size = scanner.nextInt();

        if (size <= 0) {
            System.out.println("Error: Array size must be greater than 0.");
            return;
        }

        // 2. Instantiate and populate the array
        int[] numbers = new int[size];
        System.out.println("Enter " + size + " integers:");
        for (int i = 0; i < size; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            numbers[i] = scanner.nextInt();
        }

        // Display the array before reversing
        System.out.print("\nOriginal Array: ");
        printArray(numbers);

        // 3. Reverse the array in-place using two pointers
        int start = 0;               // Pointer at the beginning
        int end = numbers.length - 1; // Pointer at the end

        while (start < end) {
            // Swap the elements at 'start' and 'end' positions
            // We need a temporary variable so we don't overwrite data during the swap
            int temp = numbers[start];
            numbers[start] = numbers[end];
            numbers[end] = temp;

            // Move the pointers closer to the center
            start++;
            end--;
        }

        // 4. Display the array after reversing
        System.out.print("Reversed Array: ");
        printArray(numbers);
    }

    // Helper method to cleanly print the array elements
    public static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }
}
