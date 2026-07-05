package com.junit.advanced;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.swing.*;
import java.awt.*;

/**
 * Exercise 3: Test Execution Order with a Live Java Swing Visual Interface.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Instructs JUnit 5 to read @Order annotations
public class OrderedTests {

    // Shared tracking variables to show your data inside the sequential steps
    private static String databaseName = "Default_DB";
    private static int dataAmount = 100;

    @Test
    @Order(1) // Runs 1st in the automated sequence
    public void testStep1_Initialize() {
        System.out.println("🔄 [Order 1] Connecting to Target Database: " + databaseName + "...");
    }

    @Test
    @Order(2) // Runs 2nd in the automated sequence
    public void testStep2_ProcessData() {
        System.out.println("⚙️ [Order 2] Processing records... Rows calculated: " + dataAmount);
    }

    @Test
    @Order(3) // Runs 3rd in the automated sequence
    public void testStep3_CleanUp() {
        System.out.println("🧹 [Order 3] Closing active links to: " + databaseName + "\n");
    }

    /**
     * 🎮 VISUAL SWING LAUNCHER
     * This turns this test file into an interactive graphical page when run as a Java Application!
     */
    public static void main(String[] args) {
        // Run the Swing GUI cleanly on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("🎯 Exercise 3: Visual Ordered Steps");
            frame.setSize(450, 320);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null); // Center window on screen

            // Create a main container layout with clean padding
            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 8, 8, 8);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Form inputs
            JLabel dbLabel = new JLabel("Enter Database Name:");
            JTextField dbField = new JTextField("MyCustomDatabase", 12);

            JLabel rowsLabel = new JLabel("Rows to Process:");
            JTextField rowsField = new JTextField("500", 12);

            // Action trigger button
            JButton runButton = new JButton("Execute Ordered Lifecycle");
            
            // Visual text area to show the outputs right on the page
            JTextArea outputArea = new JTextArea(5, 30);
            outputArea.setEditable(false);
            outputArea.setText("Click the button above to watch Step 1, 2, and 3 run!");
            outputArea.setBorder(BorderFactory.createEtchedBorder());

            // Add actions to button click
            runButton.addActionListener(e -> {
                try {
                    // 1. Capture your custom GUI inputs dynamically
                    databaseName = dbField.getText().trim();
                    dataAmount = Integer.parseInt(rowsField.getText().trim());

                    // 2. Execute the steps sequentially using your values
                    outputArea.setText(""); // Clear previous text
                    outputArea.append("🚀 [Running Steps Chronologically]\n");
                    outputArea.append("🔄 Step 1: Initializing connection to -> " + databaseName + "\n");
                    outputArea.append("⚙️ Step 2: Extracting data records for -> " + dataAmount + " items\n");
                    outputArea.append("🧹 Step 3: Connection closed. Lifecycle sequence complete!");
                    
                    // Simultaneously mirror it down to your system console logs!
                    OrderedTests testRunner = new OrderedTests();
                    testRunner.testStep1_Initialize();
                    testRunner.testStep2_ProcessData();
                    testRunner.testStep3_CleanUp();

                } catch (NumberFormatException ex) {
                    outputArea.setText("❌ Input Error: Please enter a valid whole number for data rows.");
                }
            });

            // Layout arrangement placement rules
            gbc.gridx = 0; gbc.gridy = 0; panel.add(dbLabel, gbc);
            gbc.gridx = 1; panel.add(dbField, gbc);
            
            gbc.gridx = 0; gbc.gridy = 1; panel.add(rowsLabel, gbc);
            gbc.gridx = 1; panel.add(rowsField, gbc);
            
            gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; panel.add(runButton, gbc);
            gbc.gridy = 3; panel.add(outputArea, gbc);

            frame.add(panel);
            frame.setVisible(true);
        });
    }
}