// EXERCISE 40: Design Patterns (Factory)
// Objective: Decouple object creation from business logic using a centralized Factory object.

import java.util.Scanner;

// 1. Define a common Product Interface that all factory items must implement
interface Notification {
    void send(String message);
}

// 2. Concrete Product A: SMS Channel Implementation
class SMSNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("📱 [SMS GATEWAY] Sending text alert: \"" + message + "\"");
    }
}

// 3. Concrete Product B: Email Channel Implementation
class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("📧 [SMTP SERVER] Sending electronic mail: \"" + message + "\"");
    }
}

// 4. Concrete Product C: Push Notification Implementation
class PushNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("🔔 [APP PUSH] Dispatching device home screen notification: \"" + message + "\"");
    }
}


// 5. The Factory Class: The centralized engine room for object creation
class NotificationFactory {
    // This method takes a dynamic criteria string and returns the abstract interface type
    public static Notification createNotification(String channelType) {
        if (channelType == null || channelType.isEmpty()) {
            return null;
        }
        
        // Dynamically choose and construct the appropriate concrete object
        switch (channelType.toUpperCase()) {
            case "SMS":
                return new SMSNotification();
            case "EMAIL":
                return new EmailNotification();
            case "PUSH":
                return new PushNotification();
            default:
                // Handle invalid channel type rules gracefully
                throw new IllegalArgumentException("Unknown channel type: " + channelType);
        }
    }
}


public class FactoryPatternExample40 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Architectural Factory Design Pattern ===");

        // 6. Request dynamic parameters from the user terminal
        System.out.print("\nEnter transmission channel (SMS, EMAIL, or PUSH): ");
        String chosenChannel = scanner.nextLine().trim();

        System.out.print("Enter the alert message payload text: ");
        String userMessage = scanner.nextLine();

        System.out.println("\n⏳ Handing creation duties off to the Factory Engine...");
        
        try {
            // 7. Request the object from the Factory. 
            // Notice the variable type is the generic interface 'Notification', NOT a specific class!
            Notification dynamicAlert = NotificationFactory.createNotification(chosenChannel);

            System.out.println("📦 Factory returned a target instance of: " + dynamicAlert.getClass().getSimpleName());
            
            // 8. Execute the polymorphic method call
            System.out.println("🚀 Executing transmission...\n");
            dynamicAlert.send(userMessage);

        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ Transmission Halted: " + e.getMessage());
            System.out.println("Please run the program again and select an approved factory channel identifier.");
        }
    }
}