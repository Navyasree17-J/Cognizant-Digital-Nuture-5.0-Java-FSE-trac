package com.spring.testing;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
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

/**
 * Exercise 9: Data-Driven Parameterized Framework Validation Engine.
 */
public class UserParameterizedTest implements ActionListener {

    private static JTextField arraysInputField;
    private static JTextArea executionMatrixConsole;
    private static JButton launchMatrixButton;

    /**
     * 🤖 AUTOMATED JUNIT TEST
     * Executes 3 distinct validation runs using different string inputs automatically.
     */
    @ParameterizedTest
    @ValueSource(strings = {"Alice", "Bob", "Charlie"})
    public void testMultipleUserNames(String nameInput) {
        System.out.println("🧪 [JUnit 5 Parameterized] Validating name argument: " + nameInput);
        assertNotNull(nameInput);
        assertTrue(nameInput.length() >= 3, "❌ Name length check failed!");
    }

    /**
     * 🎮 VISUAL SWING INTERFACE
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("📋 Exercise 9: Parameterized Data Matrix");
            frame.setSize(540, 360);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(6, 6, 6, 6);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel inputLabel = new JLabel("Comma-Separated Input Run Dataset:");
            arraysInputField = new JTextField("Alice, Bob, Charlie, Max, Diana", 25);

            launchMatrixButton = new JButton("Run Parametric Test Vector Matrix");
            
            executionMatrixConsole = new JTextArea(8, 42);
            executionMatrixConsole.setEditable(false);
            executionMatrixConsole.setText("Matrix Console Status: Standby.\nClick button to fire parallel dataset validation streams.");
            executionMatrixConsole.setBorder(BorderFactory.createEtchedBorder());
            executionMatrixConsole.setFont(new Font("Monospaced", Font.PLAIN, 12));

            launchMatrixButton.addActionListener(new UserParameterizedTest());

            gbc.gridx = 0; gbc.gridy = 0; panel.add(inputLabel, gbc);
            gbc.gridy = 1; panel.add(arraysInputField, gbc);
            gbc.gridy = 2; panel.add(launchMatrixButton, gbc);
            gbc.gridy = 3; panel.add(executionMatrixConsole, gbc);

            frame.add(panel);
            frame.setVisible(true);
        });
    }

    /**
     * ⚡ DYNAMIC RUNTIME VECTOR MATRIX LOOP INTERCEPTOR
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        executionMatrixConsole.setText("");
        executionMatrixConsole.setForeground(Color.BLACK);

        String rawCSVData = arraysInputField.getText().trim();
        String[] dataItems = rawCSVData.split(",");

        executionMatrixConsole.append("🏃‍♂️ Spawning Parameterized Test Matrix Execution Loop...\n");
        executionMatrixConsole.append("--------------------------------------------------\n");
        
        int successfulRuns = 0;

        for (int i = 0; i < dataItems.length; i++) {
            String element = dataItems[i].trim();
            
            executionMatrixConsole.append(" [Iteration #" + (i + 1) + "] Testing Parameter: \"" + element + "\"\n");
            
            // Simulating basic validation constraints (e.g., name cannot be shorter than 3 chars)
            if (!element.isEmpty() && element.length() >= 3) {
                executionMatrixConsole.append("   ↳ Status: PASSED (Length: " + element.length() + ")\n");
                successfulRuns++;
            } else {
                executionMatrixConsole.append("   ↳ Status: FAILED (Value constraint violation - too short!)\n");
            }
        }

        executionMatrixConsole.append("--------------------------------------------------\n");
        executionMatrixConsole.append("📈 Final Analysis: " + successfulRuns + " / " + dataItems.length + " Input Tests Passed.");
        
        if (successfulRuns == dataItems.length) {
            executionMatrixConsole.setForeground(new Color(0, 100, 0)); // Green for success
        } else {
            executionMatrixConsole.setForeground(Color.RED); // Red if any parameter fails boundary rules
        }
    }
}