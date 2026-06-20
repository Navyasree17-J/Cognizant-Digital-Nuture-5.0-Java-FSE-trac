// EXERCISE 23: File Reading
// Objective: Open an external text file and read its contents line-by-line into the console.

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReadingExample23 {
    public static void main(String[] args) {
        String fileName = "output.txt";

        System.out.println("=== Persistent File Reader ===");
        System.out.println("Attempting to read content from: " + fileName + "\n");

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            
            String line;
            int lineCount = 1;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("Line " + lineCount + ": " + line);
                lineCount++;
            }

            System.out.println("\n🎉 Success! Reached the end of the file.");

        } 
        catch (FileNotFoundException e) {
            System.out.println("⚠️ Error: The file '" + fileName + "' could not be found.");
            System.out.println("Please make sure you ran Exercise 22 first to generate this file!");
        } 
        catch (IOException e) {
            System.out.println("⚠️ An error occurred while trying to read the file.");
            System.out.println("Error Details: " + e.getMessage());
        }
    }
}