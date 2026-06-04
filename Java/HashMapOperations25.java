// EXERCISE 25: HashMap Operations
// Objective: Master key-value paired data collections using Java's HashMap class.

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HashMapOperations25 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Double> studentGrades = new HashMap<>();

        System.out.println("=== Dynamic HashMap Operations ===");

        System.out.println("\n--- Step 1: Adding Student Records ---");
        System.out.print("How many student records do you want to enter? ");
        int count = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 0; i < count; i++) {
            System.out.println("\nRecording Student #" + (i + 1) + ":");
            System.out.print("Enter unique Student ID (e.g., S101): ");
            String id = scanner.nextLine().trim().toUpperCase();

            System.out.print("Enter Student GPA (0.0 - 4.0): ");
            double gpa = scanner.nextDouble();
            scanner.nextLine(); 
            studentGrades.put(id, gpa);
        }

        System.out.println("\nCurrent Database: " + studentGrades);

        System.out.println("\n--- Step 2: Looking Up a Record ---");
        System.out.print("Enter a Student ID to search for: ");
        String searchId = scanner.nextLine().trim().toUpperCase();

        if (studentGrades.containsKey(searchId)) {
            double retrievedGpa = studentGrades.get(searchId);
            System.out.println("Match Found! Student " + searchId + " has a GPA of: " + retrievedGpa);
        } else {
            System.out.println("Record Not Found: ID '" + searchId + "' does not exist.");
        }


        // --- PART 3: Removing an Entry ---
        System.out.println("\n--- Step 3: Removing a Record ---");
        System.out.print("Enter a Student ID to remove from the system: ");
        String removeId = scanner.nextLine().trim().toUpperCase();

        if (studentGrades.containsKey(removeId)) {
            // The .remove() method deletes the entire key-value pair using the key
            double removedGpa = studentGrades.remove(removeId);
            System.out.println("Successfully deleted record " + removeId + " (Saved GPA was " + removedGpa + ")");
        } else {
            System.out.println("Deletion failed: ID '" + removeId + "' does not exist.");
        }


        System.out.println("\n=== Final Finalized Grade Directory ===");
        for (Map.Entry<String, Double> entry : studentGrades.entrySet()) {
            System.out.println("• Student ID: " + entry.getKey() + " ➔ GPA: " + entry.getValue());
        }
        System.out.println("Total Active Records: " + studentGrades.size());
    }
}