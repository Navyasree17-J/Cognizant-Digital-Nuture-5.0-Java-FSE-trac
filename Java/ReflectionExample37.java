// EXERCISE 37: Reflection API
// Objective: Dynamically inspect structures and bypass visibility modifiers at runtime.

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

// 1. The Target Class: Contains completely private data hiding inside it
class SecretAgent {
    private String agentCodeName = "007";

    private void deployMission(String location) {
        System.out.println("🕵️ Secret Agent " + agentCodeName + " has been successfully deployed to: " + location);
    }
}

public class ReflectionExample37 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Java Reflection Inspector & Modifier ===");


        try {
            Class<?> agentClass = SecretAgent.class;
            System.out.println("\n🔎 Inspecting Class Name: " + agentClass.getName());

            Constructor<?> constructor = agentClass.getDeclaredConstructor();
            Object agentInstance = constructor.newInstance();
            System.out.println("📦 Dynamic instance of SecretAgent created in memory.");

            System.out.println("\n--- Phase 1: Overriding Private State ---");
            Field codeNameField = agentClass.getDeclaredField("agentCodeName");
            
            codeNameField.setAccessible(true); 
            
            System.out.println("Current Value fetched via reflection: " + codeNameField.get(agentInstance));
            
            System.out.print("Enter a new dynamic code name to inject into the private variable: ");
            String newCodeName = scanner.nextLine();
            
            codeNameField.set(agentInstance, newCodeName);
            System.out.println("💉 Variable state modified successfully!");


            System.out.println("\n--- Phase 2: Invoking Private Methods ---");
            System.out.print("Enter a target location to pass to the private method: ");
            String targetLocation = scanner.nextLine();

            Method privateMethod = agentClass.getDeclaredMethod("deployMission", String.class);
            
            privateMethod.setAccessible(true);

            System.out.println("🚀 Executing private method dynamically via reflection invoke()...\n");
            privateMethod.invoke(agentInstance, targetLocation);

        } catch (Exception e) {
            System.out.println("⚠️ Reflection processing failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}