package com.junit.advanced;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import java.time.Duration;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

/**
 * Exercise 5: Timeout/Performance Testing with an Embedded Java Swing Interactive Window.
 */
public class PerformanceTesterTest {

    private final PerformanceTester tester = new PerformanceTester();

    /**
     * 🤖 AUTOMATED JUNIT TEST
     * Verifies that the baseline operation finishes within 1000 milliseconds.
     */
    @Test
    public void testTaskTimeout() {
        System.out.println("🧪 [JUnit 5 Automated] Testing that performTask completes within 1000ms boundary limit...");
        assertTimeout(Duration.ofMillis(1000), () -> {
            tester.performTask(400); // 400ms is safely under 1000ms limit!
        });
    }

    /**
     * 🎮 VISUAL SWING INTERFACE
     * Launches an interactive desktop dashboard frame when run as a Java Application!
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("⏱️ Exercise 5: Visual Performance Tester");
            frame.setSize(480, 280);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null); // Center window

            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Interactive input form fields
            JLabel promptLabel = new JLabel("Set task simulation duration (ms):");
            JTextField durationField = new JTextField("400", 12); // Defaults to a passing speed

            JButton runButton = new JButton("Launch Performance Simulation");

            // Output feedback message status display label
            JLabel statusLabel = new JLabel("Status: Idle (Ready to simulate task)");
            statusLabel.setFont(new Font("Arial", Font.BOLD, 12));

            // Setup tracking action on button tap
            runButton.addActionListener(e -> {
                try {
                    int durationInput = Integer.parseInt(durationField.getText().trim());
                    statusLabel.setText("⏳ Task executing... please wait...");
                    statusLabel.setForeground(Color.BLACK);
                    statusLabel.paintImmediately(statusLabel.getVisibleRect()); // Refresh component frames instantly

                    // Track runtime metrics manually inside the visual application window
                    long startTime = System.currentTimeMillis();
                    
                    PerformanceTester logicEngine = new PerformanceTester();
                    logicEngine.performTask(durationInput);
                    
                    long endTime = System.currentTimeMillis();
                    long calculatedDuration = endTime - startTime;

                    // Evaluate your custom input variables against the 1000ms performance criteria threshold
                    if (calculatedDuration > 1000) {
                        statusLabel.setText("⚠️ SLA Breach! Task took " + calculatedDuration + " ms (Max: 1000ms limit!)");
                        statusLabel.setForeground(Color.RED); // Alert Red text
                    } else {
                        statusLabel.setText("✔️ Pass! Completed successfully in " + calculatedDuration + " ms.");
                        statusLabel.setForeground(new Color(0, 128, 0)); // Success Green text
                    }

                } catch (NumberFormatException ex) {
                    statusLabel.setText("❌ Input Error: Please enter a valid whole number string.");
                    statusLabel.setForeground(Color.BLUE);
                }
            });

            // Grid positioning layout configurations
            gbc.gridx = 0; gbc.gridy = 0; panel.add(promptLabel, gbc);
            gbc.gridx = 1; panel.add(durationField, gbc);
            
            gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2; panel.add(runButton, gbc);
            gbc.gridy = 2; panel.add(statusLabel, gbc);

            frame.add(panel);
            frame.setVisible(true);
        });
    }
}