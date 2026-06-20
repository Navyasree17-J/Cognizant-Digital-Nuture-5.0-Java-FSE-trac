// EXERCISE 34: JSON Parsing
// Objective: Parse and extract attributes from a raw structured JSON string.

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParsingExample34 {

    public static String extractJsonString(String json, String key) {
        Pattern pattern = Pattern.compile("\"" + key + "\":\\s*\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return matcher.group(1); // Return the text inside the quotes
        }
        return "Not Found";
    }
    public static double extractJsonDouble(String json, String key) {
        // Regex pattern to look for "key" : number
        Pattern pattern = Pattern.compile("\"" + key + "\":\\s*([0-9.]+)");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1)); // Convert string digits to a real double
        }
        return 0.0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Dynamic JSON String Parser ===");
        System.out.print("Enter product name (e.g., Wireless Mouse): ");
        String nameInput = scanner.nextLine();

        System.out.print("Enter product category (e.g., Electronics): ");
        String categoryInput = scanner.nextLine();

        System.out.print("Enter product price (e.g., 29.99): ");
        double priceInput = scanner.nextDouble();

        String rawJsonData = "{\n" +
                "  \"product_name\": \"" + nameInput + "\",\n" +
                "  \"category\": \"" + categoryInput + "\",\n" +
                "  \"price\": " + priceInput + "\n" +
                "}";

        System.out.println("\n--- Raw JSON String Generated ---");
        System.out.println(rawJsonData);
        System.out.println("---------------------------------");


        System.out.println("\nExecuting Parser Engine...");
        
        String parsedName = extractJsonString(rawJsonData, "product_name");
        String parsedCategory = extractJsonString(rawJsonData, "category");
        double parsedPrice = extractJsonDouble(rawJsonData, "price");

        System.out.println("\n=== Successfully Parsed Java Objects ===");
        System.out.println("🛒 item Name:     " + parsedName);
        System.out.println("🏷️ Item Category: " + parsedCategory);
        System.out.println("💰 Item Cost:     $" + parsedPrice);

        double tax = parsedPrice * 0.10;
        System.out.printf("💸 Calculated Tax: $%.2f%n", tax);
    }
}