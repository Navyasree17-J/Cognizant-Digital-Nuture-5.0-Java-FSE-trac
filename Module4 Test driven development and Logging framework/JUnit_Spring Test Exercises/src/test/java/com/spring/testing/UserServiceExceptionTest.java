package com.spring.testing;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

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
import java.util.Optional;

/**
 * Exercise 6: Verifying Business Assertions and Exception Handling Pipelines.
 */
public class UserServiceExceptionTest implements ActionListener {

    private static JTextField searchIdField;
    private static JTextArea exceptionConsoleOutput;
    private static JButton searchButton;

    /**
     * 🤖 AUTOMATED JUNIT TEST
     */
    @Test
    public void testUserServiceThrowsUserNotFoundException() {
        System.out.println("🧪 [JUnit 5 Automated] Verifying UserService throwing UserNotFoundException rule...");
        
        // Setup empty repository context mock
        UserRepository emptyMockRepo = id -> Optional.empty();
        UserService service = new UserService(emptyMockRepo);

        // Verify that lookup throws UserNotFoundException
        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            service.getUserById(999L);
        });

        assertEquals("User with ID 999 not found in the repository system.", exception.getMessage());
    }

    /**
     * 🎮 VISUAL SWING INTERFACE
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("🚨 Exercise 6: Exception Interceptor Terminal");
            frame.setSize(550, 360);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(6, 6, 6, 6);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel promptLabel = new JLabel("Search User Profile by ID (Try '404'):");
            searchIdField = new JTextField("404", 10);

            searchButton = new JButton("Execute Profile Search Query");
            
            exceptionConsoleOutput = new JTextArea(8, 42);
            exceptionConsoleOutput.setEditable(false);
            exceptionConsoleOutput.setText("Console Diagnostics: System Idle.\nEnter ID and click search to test exceptions.");
            exceptionConsoleOutput.setBorder(BorderFactory.createEtchedBorder());
            exceptionConsoleOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));

            searchButton.addActionListener(new UserServiceExceptionTest());

            gbc.gridx = 0; gbc.gridy = 0; panel.add(promptLabel, gbc);
            gbc.gridx = 1; panel.add(searchIdField, gbc);
            
            gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2; panel.add(searchButton, gbc);
            gbc.gridy = 2; panel.add(exceptionConsoleOutput, gbc);

            frame.add(panel);
            frame.setVisible(true);
        });
    }

    /**
     * ⚡ EXCEPTION HANDLING SIMULATOR
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Long searchId = Long.parseLong(searchIdField.getText().trim());

            // Simulated repository that only has profiles for ID 1 and 2
            UserRepository runtimeRepo = id -> {
                if (id.equals(1L)) return Optional.of(new User(1L, "Peter Parker"));
                if (id.equals(2L)) return Optional.of(new User(2L, "Bruce Banner"));
                return Optional.empty();
            };

            UserService userService = new UserService(runtimeRepo);

            // This line may throw a UserNotFoundException
            User foundUser = userService.getUserById(searchId);

            exceptionConsoleOutput.setText(
                "✔️ PROFILE RETRIEVED SUCCESSFULLY\n" +
                "---------------------------------\n" +
                "User ID   : " + foundUser.getId() + "\n" +
                "User Name : " + foundUser.getName() + "\n" +
                "Status    : 200 OK"
            );
            exceptionConsoleOutput.setForeground(new Color(0, 100, 0));

        } catch (UserNotFoundException ex) {
            // Intercepting and displaying our custom business validation exception
            exceptionConsoleOutput.setText(
                "🚨 EXCEPTION INTERCEPTED SUCCESSFULLY\n" +
                "---------------------------------\n" +
                "Exception Type : " + ex.getClass().getName() + "\n" +
                "Error Message  : " + ex.getMessage() + "\n\n" +
                "Stack Trace Summary:\n" +
                "  at com.spring.testing.UserService.getUserById(UserService.java:21)\n" +
                "  at com.spring.testing.UserServiceExceptionTest.actionPerformed(...)"
            );
            exceptionConsoleOutput.setForeground(Color.RED);

        } catch (NumberFormatException ex) {
            exceptionConsoleOutput.setText("❌ Input Error: ID parameters must follow structural integer formatting rules.");
            exceptionConsoleOutput.setForeground(Color.BLUE);
        }
    }
}