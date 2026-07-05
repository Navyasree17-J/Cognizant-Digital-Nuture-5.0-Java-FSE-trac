package com.spring.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;

/**
 * Exercise 8: Exception Interception Pipeline Verification Engine.
 */
public class GlobalExceptionTest implements ActionListener {

    private static JTextArea wiretapConsole;
    private static JButton simulateCrashButton;

    /**
     * 🤖 AUTOMATED JUNIT TEST
     */
    @Test
    public void testControllerAdviceInterception() {
        System.out.println("🧪 [JUnit 5 Automated] Verifying @ControllerAdvice interception rules...");
        
        GlobalExceptionHandler adviceInstance = new GlobalExceptionHandler();
        NoSuchElementException simulationToken = new NoSuchElementException("Missing item trace");

        // Execute the handler match manually to confirm context behavior
        ResponseEntity<String> errorResponse = adviceInstance.handleNotFound(simulationToken);

        assertNotNull(errorResponse);
        assertEquals(HttpStatus.NOT_FOUND, errorResponse.getStatusCode());
        assertEquals("User not found", errorResponse.getBody());
    }

    /**
     * 🎮 VISUAL SWING INTERFACE
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("🚨 Exercise 8: Global Error Interceptor");
            frame.setSize(540, 360);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(6, 6, 6, 6);
            gbc.fill = GridBagConstraints.BOTH;

            JLabel headerLabel = new JLabel("Spring Boot @ControllerAdvice Exception Monitor Sandbox");
            headerLabel.setFont(new Font("Arial", Font.BOLD, 12));
            headerLabel.setHorizontalAlignment(JLabel.CENTER);

            simulateCrashButton = new JButton("Simulate Controller Throwing NoSuchElementException");
            
            wiretapConsole = new JTextArea(8, 42);
            wiretapConsole.setEditable(false);
            wiretapConsole.setText("Network Stream Status: Waiting for target events...");
            wiretapConsole.setBorder(BorderFactory.createEtchedBorder());
            wiretapConsole.setFont(new Font("Monospaced", Font.PLAIN, 12));

            simulateCrashButton.addActionListener(new GlobalExceptionTest());

            gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0; gbc.weighty = 0.0; panel.add(headerLabel, gbc);
            gbc.gridy = 1; panel.add(simulateCrashButton, gbc);
            gbc.gridy = 2; gbc.weighty = 1.0; panel.add(wiretapConsole, gbc);

            frame.add(panel);
            frame.setVisible(true);
        });
    }

    /**
     * ⚡ INTERCEPTOR SIMULATOR DISPATCHER
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        wiretapConsole.setText("");
        wiretapConsole.setForeground(Color.BLACK);

        wiretapConsole.append("1. [Client Request] Dispatching target endpoint lookup payload...\n");
        wiretapConsole.append("2. [Controller Execution] Resource lookup missed database index map.\n");
        wiretapConsole.append("3. [Exception Dropped] Core threw a java.util.NoSuchElementException!\n");
        wiretapConsole.append("4. [Spring Engine] Intercepting error via @ControllerAdvice context map...\n\n");

        try {
            // Instantiate advice controller stack elements manually
            GlobalExceptionHandler interceptor = new GlobalExceptionHandler();
            
            // Generate target exception trigger
            NoSuchElementException thrownError = new NoSuchElementException("Requested user entity record does not exist.");
            
            // Interceptor processing pipeline
            ResponseEntity<String> dynamicInterceptResponse = interceptor.handleNotFound(thrownError);

            // Display standard output logs
            wiretapConsole.append("====== INTERCEPTED HTTP ERROR RESPONSE ======\n");
            wiretapConsole.append("HTTP Response Status : " + dynamicInterceptResponse.getStatusCode() + "\n");
            wiretapConsole.append("HTTP Raw Status Code : " + dynamicInterceptResponse.getStatusCode().value() + "\n");
            wiretapConsole.append("Response Payload Body: \"" + dynamicInterceptResponse.getBody() + "\"\n");
            wiretapConsole.append("=============================================\n\n");
            wiretapConsole.append("✔️ STATUS SUCCESS: Exception handled, preventing server crash.");
            wiretapConsole.setForeground(new Color(139, 0, 0)); // Highlight success catch state

        } catch (Exception ex) {
            wiretapConsole.append("❌ Pipeline Failure: System crashed unhandled: " + ex.getMessage());
            wiretapConsole.setForeground(Color.RED);
        }
    }
}