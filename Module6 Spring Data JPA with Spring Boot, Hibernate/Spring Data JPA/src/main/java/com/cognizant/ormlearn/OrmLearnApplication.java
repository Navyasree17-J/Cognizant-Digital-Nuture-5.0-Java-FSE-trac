package com.cognizant.ormlearn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
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
        ConfigurableApplicationContext context = new SpringApplicationBuilder(OrmLearnApplication.class)
                .headless(false)
                .run(args);

        SwingUtilities.invokeLater(() -> createAndShowGUI(context));
    }

    private static void createAndShowGUI(ConfigurableApplicationContext context) {
        CountryService countryService = context.getBean(CountryService.class);

        // --- STYLING THEMES ---
        Color primaryColor = new Color(41, 128, 185);   // Modern Blue Header
        Color successColor = new Color(39, 174, 96);    // Emerald Green (Add)
        Color warningColor = new Color(230, 126, 34);   // Hibernate Orange (Update)
        Color dangerColor = new Color(192, 41, 43);     // Crimson Red (Delete)
        Color darkBg = new Color(52, 73, 94);           // Slate Gray (Find)
        Color bgColor = new Color(245, 247, 250);        // Background Gray
        
        Font titleFont = new Font("Segoe UI", Font.BOLD, 18);
        Font labelFont = new Font("Segoe UI", Font.BOLD, 12);
        Font contentFont = new Font("Segoe UI", Font.PLAIN, 14);

        // --- WINDOW SETUP ---
        JFrame frame = new JFrame("JPA / Hibernate Complete CRUD Console");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(780, 520);
        frame.getContentPane().setBackground(bgColor);
        frame.setLayout(new BorderLayout(15, 15));

        // --- HEADER PANEL ---
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(primaryColor);
        headerPanel.setBorder(new EmptyBorder(12, 10, 12, 10));
        JLabel titleLabel = new JLabel("MySQL Country Database CRUD Management Panel");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(titleFont);
        headerPanel.add(titleLabel);
        frame.add(headerPanel, BorderLayout.NORTH);

        // --- LEFT PANEL: CONTROL ENTRY FORM ---
        JPanel leftPanel = new JPanel(new GridLayout(6, 1, 5, 12));
        leftPanel.setBackground(bgColor);
        leftPanel.setBorder(new EmptyBorder(10, 20, 10, 10));
        leftPanel.setPreferredSize(new Dimension(320, 400));

        JLabel codeLabel = new JLabel("Country Code (2 Letters max):");
        codeLabel.setFont(labelFont);
        JTextField codeField = new JTextField();
        codeField.setFont(contentFont);

        JLabel nameLabel = new JLabel("Country Name:");
        nameLabel.setFont(labelFont);
        JTextField nameField = new JTextField();
        nameField.setFont(contentFont);

        leftPanel.add(codeLabel);
        leftPanel.add(codeField);
        leftPanel.add(nameLabel);
        leftPanel.add(nameField);

        // Grid Wrapper for Action Buttons
        JPanel buttonGrid = new JPanel(new GridLayout(2, 2, 8, 8));
        buttonGrid.setBackground(bgColor);

        JButton btnFind = new JButton("🔍 Find Code");
        JButton btnAdd = new JButton("➕ Add New");
        JButton btnUpdate = new JButton("✏️ Update Name");
        JButton btnDelete = new JButton("❌ Delete Code");

        JButton[] buttons = {btnFind, btnAdd, btnUpdate, btnDelete};
        for (JButton btn : buttons) {
            btn.setFont(labelFont);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
        }
        btnFind.setBackground(darkBg);
        btnAdd.setBackground(successColor);
        btnUpdate.setBackground(warningColor);
        btnDelete.setBackground(dangerColor);

        buttonGrid.add(btnFind);
        buttonGrid.add(btnAdd);
        buttonGrid.add(btnUpdate);
        buttonGrid.add(btnDelete);
        leftPanel.add(buttonGrid);

        frame.add(leftPanel, BorderLayout.WEST);

        // --- RIGHT PANEL: OUTPUT JLIST LOGGER VIEW ---
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> countryList = new JList<>(listModel);
        countryList.setFont(contentFont);
        countryList.setSelectionBackground(primaryColor);
        countryList.setSelectionForeground(Color.WHITE);
        countryList.setFixedCellHeight(30);

        JScrollPane scrollPane = new JScrollPane(countryList);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(210, 215, 225), 1), 
                "Live MySQL Record Status View"
        ));
        
        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setBackground(bgColor);
        centerWrapper.setBorder(new EmptyBorder(10, 10, 20, 20));
        centerWrapper.add(scrollPane, BorderLayout.CENTER);
        frame.add(centerWrapper, BorderLayout.CENTER);

        // --- CORE HELPER REFRESH FUNCTION ---
        Runnable refreshDatabaseGrid = () -> {
            listModel.clear();
            try {
                List<Country> countries = countryService.getAllCountries();
                for (Country country : countries) {
                    listModel.addElement("  🏳️   [" + country.getCode() + "]    -    " + country.getName());
                }
                if (countries.isEmpty()) {
                    listModel.addElement("Database Table is completely empty.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error communicating with MySQL: " + ex.getMessage(), "DB Connection Error", JOptionPane.ERROR_MESSAGE);
            }
        };

        // --- BUTTON ACTION RECEPTORS ---

        // 1. FIND BY CODE (Throws Custom Exception)
        btnFind.addActionListener(e -> {
            String targetCode = codeField.getText().trim().toUpperCase();
            if(targetCode.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please type a country code to query.", "Input Missing", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                Country found = countryService.findCountryByCode(targetCode);
                nameField.setText(found.getName());
            } catch (CountryNotFoundException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "CountryNotFoundException Caught", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 2. ADD NEW COUNTRY (Mandatory Requirement)
        btnAdd.addActionListener(e -> {
            String code = codeField.getText().trim().toUpperCase();
            String name = nameField.getText().trim();
            if (code.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Both code and name must be filled out.", "Input Missing", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                Country c = new Country();
                c.setCode(code);
                c.setName(name);
                countryService.addCountry(c);
                
                refreshDatabaseGrid.run();
                codeField.setText("");
                nameField.setText("");
                JOptionPane.showMessageDialog(frame, "Success: Country record inserted safely into MySQL database.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Database Constraint Collision: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 3. UPDATE EXISTING COUNTRY NAME
        btnUpdate.addActionListener(e -> {
            String code = codeField.getText().trim().toUpperCase();
            String newName = nameField.getText().trim();
            if (code.isEmpty() || newName.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Provide both code and target new name.", "Input Missing", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                countryService.updateCountry(code, newName);
                refreshDatabaseGrid.run();
                codeField.setText("");
                nameField.setText("");
                JOptionPane.showMessageDialog(frame, "Success: Record updated successfully.");
            } catch (CountryNotFoundException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Not Found", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 4. DELETE COUNTRY RECORD
        btnDelete.addActionListener(e -> {
            String targetCode = codeField.getText().trim().toUpperCase();
            if (targetCode.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Type an existing code to delete.", "Input Missing", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                countryService.deleteCountry(targetCode);
                refreshDatabaseGrid.run();
                codeField.setText("");
                nameField.setText("");
                JOptionPane.showMessageDialog(frame, "Success: Country removed from active database registry.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Failed to drop entity: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Open window completely centered
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        refreshDatabaseGrid.run();
    }
}