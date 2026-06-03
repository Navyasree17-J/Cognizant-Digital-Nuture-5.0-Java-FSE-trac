// EXERCISE 10: Number Guessing Game
// Objective: Implement indefinite loop patterns and comparative game logic.

import java.util.Scanner;

public class NumberGuessingGame10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Welcome to the Number Guessing Game! ===");
        System.out.println("I have selected a secret number between 1 and 100.");
        System.out.println("Can you guess what it is?");
        System.out.println("--------------------------------------------");

        int secretNumber = (int) (Math.random() * 100) + 1;
        
        int userGuess = 0;
        int attemptCount = 0;

        while (userGuess != secretNumber) {
            
            System.out.print("Enter your guess: ");
            userGuess = scanner.nextInt();
            attemptCount++; 

            if (userGuess > secretNumber) {
                System.out.println("Too High! 📈 Try a lower number.\n");
            } else if (userGuess < secretNumber) {
                System.out.println("Too Low! 📉 Try a higher number.\n");
            } else {
                System.out.println("\n🎉 CONGRATULATIONS! You found it!");
                System.out.println("The secret number was indeed: " + secretNumber);
                System.out.println("It took you exactly " + attemptCount + " attempts to win.");
            }
        }
    }
}