package com.spring.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
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
 * Exercise 1: Basic Unit Test for a Service Method with an Interactive Java Swing View.
 */
public class CalculatorServiceTest implements ActionListener {

    private final CalculatorService calculatorService = new CalculatorService();

    // Define GUI components clearly at the class level
    private static JTextField fieldA;
    private static JTextField fieldB;
    private static JLabel visualResult;
    private static JButton runButton;

    /**
     * 🤖 AUTOMATED JUNIT TEST
     */
    @Test
    public void testBasicAdditionService() {
        System.out.println("🧪 [JUnit 5 Automated] Verifying CalculatorService.add() logic pipeline...");
        int executionResult = calculatorService.add(15, 25);
        assertEquals(40, executionResult, "❌ Failure: Calculation output logic math error!");
    }

    /**
     * 🎮 VISUAL SWING INTERFACE
     */
    public static void main(String[] args) {
        // Explicitly capitalized 'S' to avoid wingUtilities error
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("🧮 Exercise 1: Spring Calculator Interface");
            frame.setSize(460, 280);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null); 

            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 8, 8, 8);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel labelA = new JLabel("Enter Number A:");
            fieldA = new JTextField("10", 8); 

            JLabel labelB = new JLabel("Enter Number B:");
            fieldB = new JTextField("20", 8); 

            runButton = new JButton("Calculate via Spring Service");
            visualResult = new JLabel("Result: Ready"); 
            visualResult.setFont(new Font("Arial", Font.BOLD, 13));

            // Connect button to the action processing method below
            runButton.addActionListener(new CalculatorServiceTest());

            // Grid layout positioning
            gbc.gridx = 0; gbc.gridy = 0; panel.add(labelA, gbc);
            gbc.gridx = 1; panel.add(fieldA, gbc);
            
            gbc.gridx = 0; gbc.gridy = 1; panel.add(labelB, gbc);
            gbc.gridx = 1; panel.add(fieldB, gbc);
            
            gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; panel.add(runButton, gbc);
            gbc.gridy = 3; panel.add(visualResult, gbc);

            frame.add(panel);
            frame.setVisible(true);
        });
    }

    /**
     * ⚡ SEPARATED ACTION PERFORMANCE METHOD
     * This moves calculations outside of the lambda scope to resolve compilation errors.
     */
    public void actionPerformed(ActionEvent e) {
        try {
            int valA = Integer.parseInt(fieldA.getText().trim());
            int valB = Integer.parseInt(fieldB.getText().trim());

            int sumResult = calculatorService.add(valA, valB);

            visualResult.setText("➡️ Spring Service Output: " + valA + " + " + valB + " = " + sumResult);
            visualResult.setForeground(new Color(0, 128, 0)); 

        } catch (NumberFormatException ex) {
            visualResult.setText("❌ Input Error: Both placeholders must be valid integers.");
            visualResult.setForeground(Color.RED);
        }
    }
}