// EXERCISE 32: Database Insertion (CRUD)
// Objective: Safeguard database operations against SQL Injection using PreparedStatement.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcInsertExamples32 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Database Record Insertion (CRUD - Create) ===");

        String url = "jdbc:sqlite::memory:";

        try (Connection conn = DriverManager.getConnection(url);
             Statement setupStmt = conn.createStatement()) {

            setupStmt.execute("CREATE TABLE products (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, price REAL);");
            System.out.println("🔌 Connected to database. 'products' table initialized.");

            System.out.print("\nEnter product name: ");
            String productName = scanner.nextLine();

            System.out.print("Enter product price: ");
            double productPrice = scanner.nextDouble();

            String sqlInsert = "INSERT INTO products (name, price) VALUES (?, ?);";

            try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
                
                pstmt.setString(1, productName);
                pstmt.setDouble(2, productPrice);

                int rowsAffected = pstmt.executeUpdate();
                System.out.println("\n🎉 Success! " + rowsAffected + " row(s) inserted smoothly.");
            }

            System.out.println("\n--- Current Inventory Status ---");
            try (ResultSet rs = setupStmt.executeQuery("SELECT * FROM products;")) {
                System.out.printf("%-5s | %-15s | %-5s%n", "ID", "PRODUCT", "PRICE");
                System.out.println("----------------------------------------");
                while (rs.next()) {
                    System.out.printf("%-5d | %-15s | $%-5.2f%n", 
                        rs.getInt("id"), 
                        rs.getString("name"), 
                        rs.getDouble("price"));
                }
            }

        } catch (SQLException e) {
            System.out.println("⚠️ A database transmission exception occurred.");
            System.out.println("Details: " + e.getMessage());
        }
    }
}