// EXERCISE 31: Basic JDBC Connection
// Objective: Connect to a database and retrieve relational data using SQL statements.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcSelectExamples31 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Relational Database Connector (JDBC) ===");
        
        String url = "jdbc:sqlite::memory:"; 

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            
            System.out.println("🔌 Database pipeline connected successfully.");

            stmt.execute("CREATE TABLE students (id INTEGER PRIMARY KEY, name TEXT, gpa REAL);");
            stmt.execute("INSERT INTO students VALUES (1, 'Alice', 3.9);");
            stmt.execute("INSERT INTO students VALUES (2, 'Bob', 3.4);");
            stmt.execute("INSERT INTO students VALUES (3, 'Charlie', 3.7);");

            System.out.print("\nEnter the minimum GPA score to filter students (e.g., 3.5): ");
            double minGpa = scanner.nextDouble();

            String sqlQuery = "SELECT id, name, gpa FROM students WHERE gpa >= " + minGpa;
            System.out.println("Executing Query: " + sqlQuery);

            try (ResultSet rs = stmt.executeQuery(sqlQuery)) {
                
                System.out.println("\n=== Query Results From 'students' Table ===");
                System.out.printf("%-5s | %-10s | %-5s%n", "ID", "NAME", "GPA");
                System.out.println("-----------------------------------");

                boolean hasRecords = false;

                while (rs.next()) {
                    hasRecords = true;
                    // Pull specific columns out of the current row by column name
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double gpa = rs.getDouble("gpa");

                    System.out.printf("%-5d | %-10s | %-5.1f%n", id, name, gpa);
                }

                if (!hasRecords) {
                    System.out.println("No students found matching that criteria.");
                }
            }

        } 
        catch (SQLException e) {
            System.out.println("⚠️ A Database error occurred during processing.");
            System.out.println("SQL State Error Details: " + e.getMessage());
        }
    }
}