// EXERCISE 24: ArrayList Operations
// Objective: Master dynamic collection manipulation using Java's ArrayList class.

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListOperations24 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> studentList = new ArrayList<>();

        System.out.println("=== Dynamic ArrayList Operations ===");
        System.out.println("\n--- Step 1: Adding Students ---");
        System.out.print("Enter how many students you want to add initially: ");
        int initialCount = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 0; i < initialCount; i++) {
            System.out.print("Enter name for Student #" + (i + 1) + ": ");
            String name = scanner.nextLine();
            studentList.add(name);
        }

        System.out.println("\nCurrent List: " + studentList);
        System.out.println("Total Students (Size): " + studentList.size());


        System.out.println("\n--- Step 2: Modifying an Element ---");
        if (!studentList.isEmpty()) {
            System.out.print("Enter the index number (0 to " + (studentList.size() - 1) + ") you want to update: ");
            int updateIndex = scanner.nextInt();
            scanner.nextLine(); 

            if (updateIndex >= 0 && updateIndex < studentList.size()) {
                String oldName = studentList.get(updateIndex);
                
                System.out.print("Enter the new name to replace '" + oldName + "': ");
                String newName = scanner.nextLine();
                
                studentList.set(updateIndex, newName);
                System.out.println("Updated successfully!");
            } else {
                System.out.println("Invalid index selection.");
            }
        }


        // --- PART 3: Removing Elements ---
        System.out.println("\n--- Step 3: Removing an Element ---");
        if (!studentList.isEmpty()) {
            System.out.print("Enter the index number of the student you want to remove: ");
            int removeIndex = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            if (removeIndex >= 0 && removeIndex < studentList.size()) {
                // The .remove() method physically deletes the item and shifts items over
                String removedName = studentList.remove(removeIndex);
                System.out.println("Successfully removed: " + removedName);
            } else {
                System.out.println("Invalid index selection.");
            }
        }


        // --- PART 4: Final Traversal ---
        System.out.println("\n=== Final Finalized Student List ===");
        // Iterating through an ArrayList cleanly using an enhanced for-loop
        for (String student : studentList) {
            System.out.println("• " + student);
        }
        System.out.println("Final Total (Size): " + studentList.size());
    }
}