package com.cognizant.ormlearn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.exception.CountryNotFoundException;

@SpringBootApplication
public class OrmLearnApplication {

    public static void main(String[] args) {
        // Start Spring Boot with Headless mode disabled to allow Swing GUI windows
        ConfigurableApplicationContext context = new SpringApplicationBuilder(OrmLearnApplication.class)
                .headless(false)
                .run(args);

        // Open the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> createAndShowGUI(context));
    }

    private static void createAndShowGUI(ConfigurableApplicationContext context) {
        CountryService countryService = context.getBean(CountryService.class);

        // --- STYLING THEMES ---
        Color primaryColor = new Color(41, 128, 185);   // Modern Blue
        Color searchBtnColor = new Color(52, 73, 94);   // Dark Slate Blue
        Color bgColor = new Color(245, 247, 250);        // Soft Light Gray
        Color panelBgColor = Color.WHITE;
        
        Font titleFont = new Font("Segoe UI", Font.BOLD, 16);
        Font labelFont = new Font("Segoe UI", Font.BOLD, 13);
        Font contentFont = new Font("Segoe UI", Font.PLAIN, 14);

        // --- WINDOW SETUP ---
        JFrame frame = new JFrame("Hands-on 6: Exception Handling Tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 320);
        frame.getContentPane().setBackground(bgColor);
        frame.setLayout(new BorderLayout(15, 15));

        // --- HEADER PANEL ---
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(primaryColor);
        headerPanel.setBorder(new EmptyBorder(12, 10, 12, 10));
        JLabel titleLabel = new JLabel("Country Search & Exception Verification Engine");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(titleFont);
        headerPanel.add(titleLabel);
        frame.add(headerPanel, BorderLayout.NORTH);

        // --- CENTER FORM PANEL ---
        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 10, 15));
        mainPanel.setBackground(panelBgColor);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 30, 20, 30),
                BorderFactory.createLineBorder(new Color(230, 235, 240), 1)
        ));

        JLabel codeLabel = new JLabel("Enter Country Code to Find:");
        codeLabel.setFont(labelFont);
        JTextField codeField = new JTextField();
        codeField.setFont(contentFont);
        codeField.setToolTipText("Try 'IN' or a random missing code like 'ZZ'");

        JLabel nameLabel = new JLabel("Returned Country Name:");
        nameLabel.setFont(labelFont);
        JTextField nameField = new JTextField();
        nameField.setFont(contentFont);
        nameField.setEditable(false); // Read-only view
        nameField.setBackground(new Color(238, 242, 245));

        mainPanel.add(codeLabel);
        mainPanel.add(codeField);
        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        frame.add(mainPanel, BorderLayout.CENTER);

        // --- BOTTOM ACTION PANEL ---
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(bgColor);
        bottomPanel.setBorder(new EmptyBorder(0, 10, 20, 10));

        JButton searchButton = new JButton("Search Country");
        searchButton.setFont(labelFont);
        searchButton.setBackground(searchBtnColor);
        searchButton.setForeground(Color.WHITE);
        searchButton.setPreferredSize(new Dimension(180, 40));
        searchButton.setFocusPainted(false);
        bottomPanel.add(searchButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // --- ACTION CONTROLLER LOGIC (THE CORE ASSIGNMENT REQUIREMENT) ---
        searchButton.addActionListener(e -> {
            String searchCode = codeField.getText().trim().toUpperCase();
            nameField.setText(""); // Reset previous result display
            
            if (searchCode.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a 2-letter country code.", "Input Empty", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                // Call the service method that throws the custom exception
                Country country = countryService.findCountryByCode(searchCode);
                
                // If found, display the results elegantly
                nameField.setText(country.getName());
                JOptionPane.showMessageDialog(frame, "✅ Country Found!\nCode: " + country.getCode() + "\nName: " + country.getName(), "Success", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (CountryNotFoundException ex) {
                // CATCH block handling your custom hands-on exception explicitly
                JOptionPane.showMessageDialog(frame, "❌ Custom Exception Triggered!\nMessage: " + ex.getMessage(), "CountryNotFoundException Caught", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                // Catch-all block for unhandled system/database issues
                JOptionPane.showMessageDialog(frame, "Database System Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Open layout completely centered on your display monitor screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}