package com.example.singleton;

public class SingletonTest {
    public static void main(String[] args) {
        System.out.println("--- Testing Singleton Pattern ---");

        // Fetching the instance twice
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("First activity recorded.");
        logger2.log("Second activity recorded.");

        System.out.println("\n--- Verification ---");
        
        boolean isSameInstance = (logger1 == logger2);
        System.out.println("Are logger1 and logger2 the exact same instance? -> " + isSameInstance);
        System.out.println("Logger1 Memory Hashcode: " + logger1.hashCode());
        System.out.println("Logger2 Memory Hashcode: " + logger2.hashCode());
        
        if (isSameInstance) {
            System.out.println("\nSUCCESS: Both references share the exact same instance!");
        } else {
            System.out.println("\nFAILURE: Multiple instances were created.");
        }
    }
}