// EXERCISE 20: Bubble Sort
// Objective: Implement an in-place sorting algorithm using nested loops and adjacent cell swapping.

import java.util.Arrays;
import java.util.Scanner;

public class BubbleSortExample20 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Bubble Sort Demonstration ===");

        System.out.print("Enter the number of elements to sort: ");
        int size = scanner.nextInt();

        if (size <= 0) {
            System.out.println("Error: Array size must be greater than 0.");
            return;
        }

        int[] numbers = new int[size];
        System.out.println("Enter " + size + " integers in any scrambled order:");
        for (int i = 0; i < size; i++) {
            System.out.print("Element " + i + ": ");
            numbers[i] = scanner.nextInt();
        }

        System.out.println("\nOriginal Array: " + Arrays.toString(numbers));

        int n = numbers.length;
        
        for (int i = 0; i < n - 1; i++) {            
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {                
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                    
                    swapped = true; 
                }
            }

            if (!swapped) {
                break;
            }
        }
        System.out.println("Sorted Array:   " + Arrays.toString(numbers));
    }
}