package com.cognizant.springlearn;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringLearnApplication {

    public static void main(String[] args) {
        // Bootstrap Spring Boot 3 with headless mode explicitly turned off for UI rendering
        @SuppressWarnings("unused")
		ConfigurableApplicationContext context = new SpringApplicationBuilder(SpringLearnApplication.class)
                .headless(false)
                .run(args);

        // Mount the Java Swing View
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        Color darkBlue = new Color(44, 62, 80);
        Color lightGray = new Color(240, 244, 248);

        JFrame frame = new JFrame("Hands-on 2: Spring Boot 3 REST & Core XML Studio");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 420);
        frame.setLayout(new BorderLayout(10, 10));
        frame.getContentPane().setBackground(lightGray);

        // Top Banner Title Panel
        JPanel bannerPanel = new JPanel();
        bannerPanel.setBackground(darkBlue);
        bannerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel titleLabel = new JLabel("Spring Configuration Bean & REST API Core Tester");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        bannerPanel.add(titleLabel);
        frame.add(bannerPanel, BorderLayout.NORTH);

        // Input Control Panel (Left Side)
        JPanel inputPanel = new JPanel(new GridLayout(4, 1, 5, 10));
        inputPanel.setBackground(lightGray);
        inputPanel.setBorder(new EmptyBorder(25, 25, 25, 15));
        inputPanel.setPreferredSize(new Dimension(320, 300));

        JLabel lblInput = new JLabel("Enter Date String (dd/MM/yyyy):");
        lblInput.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        // Seed default assignment with the test baseline string from Hands-on 2 instructions
        JTextField txtDateInput = new JTextField("31/12/2018"); 
        txtDateInput.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton btnSubmitRest = new JButton("📡 Dispatch REST Request");
        btnSubmitRest.setBackground(new Color(41, 128, 185));
        btnSubmitRest.setForeground(Color.WHITE);
        btnSubmitRest.setFont(new Font("Segoe UI", Font.BOLD, 12));

        inputPanel.add(lblInput);
        inputPanel.add(txtDateInput);
        inputPanel.add(new JLabel("")); // Spacer row
        inputPanel.add(btnSubmitRest);
        frame.add(inputPanel, BorderLayout.WEST);

        // Console Response Logger Area (Right Side)
        JTextArea txtOutputConsole = new JTextArea();
        txtOutputConsole.setEditable(false);
        txtOutputConsole.setFont(new Font("Consolas", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(txtOutputConsole);
        scrollPane.setBorder(BorderFactory.createTitledBorder("REST Endpoint Output Stream JSON Response"));

        JPanel centerContainer = new JPanel(new BorderLayout());
        centerContainer.setBackground(lightGray);
        centerContainer.setBorder(new EmptyBorder(15, 10, 20, 20));
        centerContainer.add(scrollPane, BorderLayout.CENTER);
        frame.add(centerContainer, BorderLayout.CENTER);

        // Action Trigger Event Binding
        btnSubmitRest.addActionListener(e -> {
            txtOutputConsole.setText("Connecting to local API stream server...");
            try {
                String inputDate = txtDateInput.getText().trim();
                String encodedDate = URLEncoder.encode(inputDate, StandardCharsets.UTF_8.toString());
                
                // Construct connection URL directing request to our active REST endpoint
                URL url = new URL("http://localhost:8085/api/date/parse?dateStr=" + encodedDate);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(
                        conn.getResponseCode() == 200 ? conn.getInputStream() : conn.getErrorStream()));
                
                String inputLine;
                StringBuilder responseJson = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    responseJson.append(inputLine).append("\n");
                }
                in.close();

                // Beautify text layout response to terminal dashboard view
                txtOutputConsole.setText("HTTP Status Code: " + conn.getResponseCode() + "\n\n" + responseJson.toString());

            } catch (Exception ex) {
                txtOutputConsole.setText("Runtime API failure block:\n" + ex.getMessage());
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}