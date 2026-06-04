// EXERCISE 38: Custom Annotations
// Objective: Define and read custom metadata configurations using Java's Annotation frameworks.

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Scanner;

// 1. Define the Custom Annotation
// @Target specifies WHERE this annotation can be placed (FIELD means on class variables)
@Target(ElementType.FIELD)
// @Retention specifies WHEN this metadata is accessible (RUNTIME means it can be read via Reflection while running)
@Retention(RetentionPolicy.RUNTIME)
@interface ValidateRange {
    double min(); // Attribute specifying the minimum valid value
    double max(); // Attribute specifying the maximum valid value
}

// 2. The Target Data Model: Using our custom annotation to enforce business rules
class Smartphone {
    String modelName;

    // A phone screen size must reasonably be between 4.0 and 7.5 inches
    @ValidateRange(min = 4.0, max = 7.5)
    double screenSize;

    // A phone battery capacity must reasonably be between 1000 and 7000 mAh
    @ValidateRange(min = 1000.0, max = 7000.0)
    double batteryCapacity;

    public Smartphone(String name, double size, double battery) {
        this.modelName = name;
        this.screenSize = size;
        this.batteryCapacity = battery;
    }
}

// 3. The Processing Engine: Uses reflection to inspect the metadata rules
class AnnotationValidator {
    public static boolean validate(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        
        // Loop through all fields inside the object's class blueprint
        for (Field field : clazz.getDeclaredFields()) {
            
            // Check if the current field is tagged with our custom @ValidateRange annotation
            if (field.isAnnotationPresent(ValidateRange.class)) {
                // Fetch the specific annotation instance to read its configured attributes
                ValidateRange range = field.getAnnotation(ValidateRange.class);
                
                // Bypass standard private/visibility blocks to read the value safely
                field.setAccessible(true);
                double value = field.getDouble(obj);

                // Perform the rule validation check
                if (value < range.min() || value > range.max()) {
                    System.out.println("❌ Validation Error on field '" + field.getName() + "':");
                    System.out.println("   -> Input Value: " + value);
                    System.out.println("   -> Allowed Strict Range: [" + range.min() + " to " + range.max() + "]");
                    return false; // Found a rule violation!
                }
            }
        }
        return true; // Everything passed safely
    }
}

public class AnnotationExample38 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Metadata Custom Annotation Validator ===");

        // 4. Capture dynamic specifications from the user
        System.out.print("\nEnter Smartphone Model Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Screen Size in inches (Allowed: 4.0 - 7.5): ");
        double size = scanner.nextDouble();

        System.out.print("Enter Battery Capacity in mAh (Allowed: 1000 - 7000): ");
        double battery = scanner.nextDouble();

        // Instantiate our data object using the dynamic inputs
        Smartphone userPhone = new Smartphone(name, size, battery);

        System.out.println("\n⏳ Initializing Annotation Framework Validation Pipeline...");
        
        try {
            // Trigger the reflection-backed metadata engine
            boolean isValid = AnnotationValidator.validate(userPhone);

            if (isValid) {
                System.out.println("✅ Success! The smartphone object passed all metadata rules.");
                System.out.println("📱 Device '" + userPhone.modelName + "' saved cleanly to the database framework.");
            } else {
                System.out.println("⚠️ Object rejected! The data violated the defined annotation constraints.");
            }
            
        } catch (IllegalAccessException e) {
            System.out.println("Error processing validations: " + e.getMessage());
        }
    }
}