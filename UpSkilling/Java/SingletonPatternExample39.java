// EXERCISE 39: Design Patterns (Singleton)
// Objective: Implement a thread-safe Singleton pattern with unified state management.

import java.util.Scanner;

// 1. Define the Singleton Class
class ApplicationSettings {
    // Rule A: Create a private static reference variable to hold the single instance
    private static ApplicationSettings instance;

    // A sample configuration property managed by our Singleton
    private String systemTheme = "Light Mode";

    // Rule B: Make the constructor strictly PRIVATE!
    // This makes it impossible to type 'new ApplicationSettings()' from outside this class.
    private ApplicationSettings() {
        System.out.println("🔧 [SYSTEM CONFIG]: Singleton instance initialized for the very first time.");
    }

    // Rule C: Provide a public static method to grant global access to the instance
    // We add 'synchronized' to make it Thread-Safe, ensuring two threads don't build twins!
    public static synchronized ApplicationSettings getInstance() {
        if (instance == null) {
            // Lazy Initialization: Only build it if someone actually asks for it
            instance = new ApplicationSettings();
        }
        return instance;
    }

    // Getter for our setting
    public String getSystemTheme() {
        return this.systemTheme;
    }

    // Setter for our setting
    public void setSystemTheme(String theme) {
        this.systemTheme = theme;
    }
}

public class SingletonPatternExample39 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Architectural Singleton Design Pattern ===");

        // 2. Access the Singleton from "Module Alpha"
        System.out.println("\n--- Module Alpha Requesting Configuration ---");
        ApplicationSettings alphaConfig = ApplicationSettings.getInstance();
        System.out.println("Alpha Module reads current theme: " + alphaConfig.getSystemTheme());

        // 3. Capture dynamic input to update the centralized setting
        System.out.print("\nEnter a new system theme to apply globally (e.g., Dark Mode): ");
        String chosenTheme = scanner.nextLine();
        
        System.out.println("\nApplying setting via Module Alpha...");
        alphaConfig.setSystemTheme(chosenTheme);


        // 4. Request the instance again from a completely different "Module Beta"
        System.out.println("\n--- Module Beta Requesting Configuration ---");
        ApplicationSettings betaConfig = ApplicationSettings.getInstance();
        System.out.println("Beta Module reads current theme: " + betaConfig.getSystemTheme());


        // 5. CRITICAL VALIDATION CHECK: Prove both references point to the exact same object in memory
        System.out.println("\n--- Memory Reference Verification Check ---");
        System.out.println("Memory address identity of Alpha Reference: " + System.identityHashCode(alphaConfig));
        System.out.println("Memory address identity of Beta Reference:  " + System.identityHashCode(betaConfig));

        if (alphaConfig == betaConfig) {
            System.out.println("\n🎉 Verification Success! Both variables point to the exact same physical memory address.");
            System.out.println("The Singleton pattern effectively restricted object creation to 1 instance.");
        } else {
            System.out.println("\n❌ Verification Failed! Duplicate instances found in memory.");
        }
    }
}