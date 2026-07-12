package com.cognizant.banking;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.cognizant.banking")
public class SpringBankingApplication {

    public static void main(String[] args) {
        // Run application default framework profiles
        @SuppressWarnings("unused")
		ConfigurableApplicationContext context = new SpringApplicationBuilder(SpringBankingApplication.class)
                .headless(false)
                .properties("server.port=8081") // Default bootstrap port context
                .run(args);

        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Hands-on 1: Banking Domain Microservices Console");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 480);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel banner = new JPanel();
        banner.setBackground(new Color(44, 62, 80));
        banner.setBorder(new EmptyBorder(12, 10, 12, 10));
        JLabel title = new JLabel("Distributed Account & Loan Domain Context Broker Console");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 15));
        banner.add(title);
        frame.add(banner, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel(new GridLayout(2, 1, 10, 25));
        leftPanel.setBorder(new EmptyBorder(30, 20, 30, 10));
        leftPanel.setPreferredSize(new Dimension(340, 400));

        JButton btnAccount = new JButton("💳 Query Account Microservice");
        btnAccount.setBackground(new Color(41, 128, 185));
        btnAccount.setForeground(Color.WHITE);
        btnAccount.setFont(new Font("Segoe UI", Font.BOLD, 12));

        JButton btnLoan = new JButton("💰 Query Loan Microservice");
        btnLoan.setBackground(new Color(39, 174, 96));
        btnLoan.setForeground(Color.WHITE);
        btnLoan.setFont(new Font("Segoe UI", Font.BOLD, 12));

        leftPanel.add(btnAccount);
        leftPanel.add(btnLoan);
        frame.add(leftPanel, BorderLayout.WEST);

        JTextArea consoleOutput = new JTextArea("System Operational. Select a domain endpoint to pull plain text table matrices...");
        consoleOutput.setFont(new Font("Consolas", Font.PLAIN, 13));
        consoleOutput.setEditable(false);
        frame.add(new JScrollPane(consoleOutput), BorderLayout.CENTER);

        // Routing Wire Hook Actions
        btnAccount.addActionListener(e -> queryService("http://localhost:8081/accounts/details", consoleOutput));
        btnLoan.addActionListener(e -> queryService("http://localhost:8081/loans/details", consoleOutput));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void queryService(String urlStr, JTextArea output) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = in.readLine()) != null) {
                sb.append(line).append("\n");
            }
            in.close();
            output.setText(sb.toString());
        } catch (Exception ex) {
            output.setText("Error dispatching request context stream: " + ex.getMessage());
        }
    }
}