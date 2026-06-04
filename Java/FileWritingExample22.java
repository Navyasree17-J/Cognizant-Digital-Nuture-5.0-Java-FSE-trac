// EXERCISE 22: File Writing
// Objective: Accept user input and save it persistently to a local text file.

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWritingExample22 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Persistent File Writer ===");
        System.out.print("Enter a string or sentence to save to the file: ");
        String userInput = scanner.nextLine(); // nextLine() ensures we capture spaces
        String fileName = "output.txt";

        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            
            bufferedWriter.write(userInput);
            
            bufferedWriter.newLine();

            System.out.println("\n🎉 Success! Data has been successfully written.");
            System.out.println("File location: Check your current project folder for '" + fileName + "'");

        } 
        catch (IOException e) {
            System.out.println("\n⚠️ An error occurred while writing to the file.");
            System.out.println("Error Details: " + e.getMessage());
        }
    }
}