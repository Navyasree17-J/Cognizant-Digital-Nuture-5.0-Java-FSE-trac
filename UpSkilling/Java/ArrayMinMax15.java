// EXERCISE 15: Find the Largest and Smallest in an Array
// Objective: Master array initialization, element traversal, and linear search patterns.

import java.util.Scanner;

public class ArrayMinMax15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Find Largest & Smallest in Array ===");
        System.out.print("Enter the number of elements you want in the array: ");
        int size = scanner.nextInt();
        if (size <= 0) {
            System.out.println("Error: Array size must be greater than 0.");
            return;
        }
        int[] numbers = new int[size];
        System.out.println("Enter " + size + " integers:");
        for (int i = 0; i < size; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            numbers[i] = scanner.nextInt();
        }

        // 4. Initialize tracker variables with the very first element of the array
        int smallest = numbers[0];
        int largest = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > largest) {
                largest = numbers[i]; 
            }
            if (numbers[i] < smallest) {
                smallest = numbers[i]; 
            }
        }
        System.out.println("\n--- Array Scan Summary ---");
        System.out.print("Your Array: [ ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println("]");

        System.out.println("Smallest Number: " + smallest);
        System.out.println("Largest Number:  " + largest);
    }
}