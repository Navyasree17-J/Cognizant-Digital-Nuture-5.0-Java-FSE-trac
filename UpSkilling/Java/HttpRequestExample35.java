// EXERCISE 35: Network HTTP Request
// Objective: Construct and execute an HTTP network request using Java's HttpClient framework.

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class HttpRequestExample35 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Network HTTP Request Agent ===");
        
        System.out.print("Enter a search topic to request from the API (e.g., java, technology): ");
        String searchTerm = scanner.nextLine().trim().toLowerCase().replace(" ", "+");

        String apiEndpoint = "https://httpbin.org/get?query=" + searchTerm;
        System.out.println("\n📡 Constructing transmission pipeline to: " + apiEndpoint);

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiEndpoint))
                    .GET() // We use GET to request/retrieve data from a resource
                    .header("Accept", "application/json") // Ask the server for JSON back
                    .build();

            System.out.println("🚀 Sending HTTP GET request packet...");

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("\n--- Server Network Response ---");
            System.out.println("HTTP Status Code: " + response.statusCode());

            if (response.statusCode() == 200) {
                System.out.println("🎉 Connection Successful! Raw payload body received:");
                System.out.println("--------------------------------------------------");
                
                System.out.println(response.body());
                
                System.out.println("--------------------------------------------------");
            } else {
                System.out.println("⚠️ Server returned an unexpected error code: " + response.statusCode());
            }

        } catch (java.io.IOException | InterruptedException e) {
            System.out.println("\n⚠️ Network Error: Unable to establish an internet connection.");
            System.out.println("Error Details: " + e.getMessage());
        }
    }
}