// EXERCISE 36: HTTP Client API (Java 11+)
// Objective: Dynamic API communication using Java 11's modern HttpClient framework.

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GitHubApiClient36 {
    private static String parseField(String json, String key) {
        Pattern pattern = Pattern.compile("\"" + key + "\":\\s*\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return matcher.group(1);
        }
        
        Pattern numPattern = Pattern.compile("\"" + key + "\":\\s*([^,\\}]+)");
        Matcher numMatcher = numPattern.matcher(json);
        if (numMatcher.find()) {
            return numMatcher.group(1).trim();
        }
        return "N/A";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Modern Java 11+ GitHub API Client ===");
        
        System.out.print("Enter a GitHub username to lookup (e.g., torvalds, defunkt): ");
        String username = scanner.nextLine().trim();

        if (username.isEmpty()) {
            System.out.println("Error: Username cannot be empty.");
            return;
        }

        String targetUrl = "https://api.github.com/users/" + username;
        System.out.println("📡 Connecting to public server: " + targetUrl);

        try {
            HttpClient client = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_2) // Request modern HTTP/2 connections
                    .connectTimeout(Duration.ofSeconds(10)) // Max wait time of 10 seconds to connect
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(targetUrl))
                    .timeout(Duration.ofSeconds(5)) // Max wait time for data transmission
                    .header("User-Agent", "Java11-HttpClient-App") // Required by GitHub API safety filters
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            System.out.println("🚀 Sending request over internet sockets...");

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("\n--- HTTP Response Envelope Metadata ---");
            System.out.println("🟢 Response Status Code: " + response.statusCode());
            System.out.println("🌐 Protocol Version:      " + response.version());
            System.out.println("📄 Content-Type Flag:     " + response.headers().firstValue("content-type").orElse("Unknown"));

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                
                System.out.println("\n🎉 Profile Found! Raw JSON Stream snippet captured:");
                System.out.println("----------------------------------------------------------------------");
                // Print a clean preview snippet of the full body payload
                System.out.println(responseBody.substring(0, Math.min(responseBody.length(), 400)) + "...\n[Truncated]");
                System.out.println("----------------------------------------------------------------------");

                System.out.println("\n=== Extracted Profile Dashboard ===");
                System.out.println("👤 Display Name:    " + parseField(responseBody, "name"));
                System.out.println("🏢 Company/Affil:   " + parseField(responseBody, "company"));
                System.out.println("📍 User Location:   " + parseField(responseBody, "location"));
                System.out.println("📂 Public Repos:    " + parseField(responseBody, "public_repos"));
                System.out.println("👥 Followers Count: " + parseField(responseBody, "followers"));
                
            } else if (response.statusCode() == 404) {
                System.out.println("\n❌ Lookup Failed: The GitHub username '" + username + "' does not exist.");
            } else {
                System.out.println("\n⚠️ Remote API server responded with error status code: " + response.statusCode());
            }

        } catch (java.io.IOException e) {
            System.out.println("\n⚠️ Connection Error: Unable to communicate with the server. Check your network adapter.");
            System.out.println("Details: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("\n⚠️ Connection Interrupted: Process thread was forcibly killed.");
        }
    }
}