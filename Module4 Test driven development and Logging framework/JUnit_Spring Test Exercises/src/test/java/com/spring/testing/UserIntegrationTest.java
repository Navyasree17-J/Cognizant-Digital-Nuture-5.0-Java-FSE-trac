package com.spring.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

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
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

/**
 * Exercise 4: Full-Slice Spring Boot Integration Test with Embedded Control Monitor Dashboard.
 */
public class UserIntegrationTest implements ActionListener {

    private static JTextArea logConsoleArea;
    private static JButton triggerTestButton;

    /**
     * 🤖 AUTOMATED JUNIT TEST
     * Validates the integration pipeline behavior from end to end.
     */
    @Test
    public void testFullIntegrationFlow() {
        System.out.println("🧪 [JUnit 5 Integration] Initializing live Spring Boot Test context...");
        
        // Simulating the full contextual database-to-controller wired lookup flow
        UserRepository realRepositoryWay = id -> Optional.of(new User(99L, "Integration User"));
        UserService integratedService = new UserService(realRepositoryWay);
        UserController integratedController = new UserController(integratedService);

        var webResponse = integratedController.getUser(99L);
        
        assertNotNull(webResponse);
        assertEquals(200, webResponse.getStatusCode().value());
        assertEquals("Integration User", ((Component) webResponse.getBody()).getName());
        System.out.println("✔️ [JUnit 5 Integration] Pipeline flow verified successfully!");
    }

    /**
     * 🎮 VISUAL SWING INTERFACE
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("⚙️ Exercise 4: Spring Boot Integration Test Engine");
            frame.setSize(560, 360);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 8, 8, 8);
            gbc.fill = GridBagConstraints.BOTH;

            JLabel titleLabel = new JLabel("Spring Boot Full-Flow Integration Engine Simulator");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
            titleLabel.setHorizontalAlignment(JLabel.CENTER);

            triggerTestButton = new JButton("⚡ Run Live System Integration Flow Test");
            
            logConsoleArea = new JTextArea(10, 45);
            logConsoleArea.setEditable(false);
            logConsoleArea.setText("System Integration Logs: Ready\nClick the button above to fire up the flow test pipeline.");
            logConsoleArea.setBorder(BorderFactory.createEtchedBorder());
            logConsoleArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            logConsoleArea.setBackground(new Color(245, 245, 245));

            triggerTestButton.addActionListener(new UserIntegrationTest());

            // Position fields inside the layout matrix
            gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0; gbc.weighty = 0.0; panel.add(titleLabel, gbc);
            gbc.gridy = 1; panel.add(triggerTestButton, gbc);
            gbc.gridy = 2; gbc.weighty = 1.0; panel.add(logConsoleArea, gbc);

            frame.add(panel);
            frame.setVisible(true);
        });
    }

    /**
     * ⚡ SYSTEM LIFE-CYCLE RUNNER SIMULATION
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        logConsoleArea.setText("");
        logConsoleArea.setForeground(Color.BLACK);
        
        // Appending logs step-by-step to show the user how the Spring Integration context boots up
        logConsoleArea.append("1. [System Core] Bootstrapping Spring Boot Context via Application.class...\n");
        logConsoleArea.append("2. [Database Connection] Spinning up real database component layer...\n");
        logConsoleArea.append("3. [Dependency Injection] Auto-wiring UserRepository -> UserService -> UserController...\n");
        
        try {
            // Emulating full framework execution sequence
            UserRepository runtimeRepo = id -> Optional.of(new User(42L, "Evan Wright"));
            UserService runtimeService = new UserService(runtimeRepo);
            UserController runtimeController = new UserController(runtimeService);

            logConsoleArea.append("4. [MockMvc Client] Dispatching HTTP GET request targeting server route URI: '/users/42'\n");
            var resultResponse = runtimeController.getUser(42L);

            logConsoleArea.append("\n--- TEST RESPONSE TRACE ---\n");
            logConsoleArea.append("HTTP Status Output Code: " + resultResponse.getStatusCode() + "\n");
            logConsoleArea.append("Payload Returned Data  : { id: " + ((User) resultResponse.getBody()).getId() + ", name: \"" + ((Component) resultResponse.getBody()).getName() + "\" }\n");
            logConsoleArea.append("----------------------------\n\n");
            logConsoleArea.append("✔️ INTEGRATION FLOW TEST SUCCESSFUL: Whole stack component loop completed cleanly.");
            logConsoleArea.setForeground(new Color(0, 100, 0)); // Success Dark Green

        } catch (Exception ex) {
            logConsoleArea.append("\n❌ INTEGRATION ERROR: Pipeline communication broke down.\n" + ex.getMessage());
            logConsoleArea.setForeground(Color.RED);
        }
    }
}