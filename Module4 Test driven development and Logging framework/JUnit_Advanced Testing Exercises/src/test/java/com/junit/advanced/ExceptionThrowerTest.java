package com.junit.advanced;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

/**
 * Exercise 4: Exception Testing with an Embedded Java Swing Interactive Window.
 */
public class ExceptionThrowerTest {

    private final ExceptionThrower thrower = new ExceptionThrower();

    /**
     * 🤖 AUTOMATED JUNIT TEST
     * JUnit 5 handles this automatically during full test suite runs.
     */
    @Test
    public void testExpectedException() {
        System.out.println("🧪 [JUnit 5 Automated] Verifying Exception throwing mechanics...");
        assertThrows(IllegalArgumentException.class, () -> {
            thrower.throwException(10, 0);
        });
    }

    /**
     * 🎮 VISUAL SWING INTERFACE
     * Launches an interactive desktop control panel when run as a Java Application!
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("💥 Exercise 4: Visual Exception Tester");
            frame.setSize(480, 320);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null); // Center window

            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 8, 8, 8);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Interactive Input fields
            JLabel divLabel = new JLabel("Enter Dividend:");
            JTextField divField = new JTextField("12", 10);

            JLabel divisorLabel = new JLabel("Enter Divisor:");
            JTextField divisorField = new JTextField("0", 10); // Defaults to 0 to easily showcase the error!

            JButton runButton = new JButton("Execute Division Runtime");

            // Output message monitor panel
            JTextArea displayArea = new JTextArea(4, 30);
            displayArea.setEditable(false);
            displayArea.setLineWrap(true);
            displayArea.setWrapStyleWord(true);
            displayArea.setText("Tip: Keep the divisor as '0' and click the button to see the exception trigger!");
            displayArea.setBorder(BorderFactory.createEtchedBorder());

            // Add action processing logic on button click
            runButton.addActionListener(e -> {
                try {
                    int num1 = Integer.parseInt(divField.getText().trim());
                    int num2 = Integer.parseInt(divisorField.getText().trim());

                    ExceptionThrower logicEngine = new ExceptionThrower();
                    int calculation = logicEngine.throwException(num1, num2);

                    // If it doesn't break, show success outcome
                    displayArea.setText("➡️ Calculation Result: " + num1 + " / " + num2 + " = " + calculation);
                    displayArea.setForeground(new Color(0, 100, 0)); // Green text

                } catch (NumberFormatException ex) {
                    displayArea.setText("❌ Input Error: Both fields must contain valid integers.");
                    displayArea.setForeground(Color.BLUE);
                } catch (IllegalArgumentException ex) {
                    // CATCH THE CUSTOM RULE EXCEPTION AND PRINT VISUALLY
                    displayArea.setText("🔥 [EXPECTED EXCEPTION CAUGHT SUCCESSFULLY!]\n" + ex.getMessage());
                    displayArea.setForeground(Color.RED); // Alert Red text
                }
            });

            // Position visual components inside layout grid
            gbc.gridx = 0; gbc.gridy = 0; panel.add(divLabel, gbc);
            gbc.gridx = 1; panel.add(divField, gbc);

            gbc.gridx = 0; gbc.gridy = 1; panel.add(divisorLabel, gbc);
            gbc.gridx = 1; panel.add(divisorField, gbc);

            gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; panel.add(runButton, gbc);
            gbc.gridy = 3; panel.add(displayArea, gbc);

            frame.add(panel);
            frame.setVisible(true);
        });
    }
}