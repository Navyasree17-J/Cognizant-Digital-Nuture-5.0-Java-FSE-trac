// EXERCISE 30: Stream API
// Objective: Process collections efficiently using modern functional Stream pipelines.

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DynamicStreamExamples30 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numericalList = new ArrayList<>();

        System.out.println("=== Dynamic Stream API Pipeline ===");
        System.out.print("How many numbers do you want to add to your list? ");
        int totalElements = scanner.nextInt();

        System.out.println("Enter " + totalElements + " integers (mix even and odd numbers):");
        for (int i = 0; i < totalElements; i++) {
            System.out.print("Value #" + (i + 1) + ": ");
            numericalList.add(scanner.nextInt());
        }

        System.out.println("\nOriginal Input List: " + numericalList);

        List<Integer> processedList = numericalList.stream()
                .filter(num -> num % 2 == 0)         
                .map(evenNum -> evenNum * evenNum)   
                .collect(Collectors.toList());       

        System.out.println("\n--- Stream Processing Results ---");
        System.out.println("Processed List (Even Numbers Squared): " + processedList);
    }
}