// EXERCISE 14: Palindrome Checker
// Objective: Master string manipulation techniques and loop-based reversals.

import java.util.Scanner;

public class PalindromeCheck14 {
    public static void main(String[] args) {
        // Declaring the Scanner the traditional way for user input
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Palindrome Checker ===");

        System.out.print("Enter text to check: ");
        String originalText = scanner.nextLine(); 
        String cleanText = originalText.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversedText = "";
        for (int i = cleanText.length() - 1; i >= 0; i--) {
            reversedText = reversedText + cleanText.charAt(i);
        }
        System.out.println("\n--- Evaluation Result ---");
        System.out.println("Processed Text: \"" + cleanText + "\"");
        System.out.println("Reversed Text:  \"" + reversedText + "\"");

        if (cleanText.equals(reversedText) && !cleanText.isEmpty()) {
            System.out.println("\nResult: \"" + originalText + "\" is a PALINDROME! 🎉");
        } else {
            System.out.println("\nResult: \"" + originalText + "\" is NOT a palindrome.");
        }
    }
}