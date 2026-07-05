package com.spring.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
 * Exercise 3: MockMvc REST Controller Validation with an Embedded Swing Mock Interface.
 */
public class UserControllerTest implements ActionListener {

    private static JTextField endpointField;
    private static JTextArea responseBodyArea;
    private static JButton mockMvcButton;

    /**
     * 🤖 AUTOMATED JUNIT TEST
     */
    @Test
    public void testUserControllerGetEndpoint() {
        System.out.println("🧪 [JUnit 5 Automated] MockMvc performing simulated GET /users/1 request pipeline...");
        
        UserRepository localMockRepo = id -> Optional.of(new User(1L, "Alice"));
        UserService mockService = new UserService(localMockRepo);
        UserController controller = new UserController(mockService);

        // Explicit fully-qualified naming resolves any generic typing ambiguity
        @SuppressWarnings("unchecked")
		org.springframework.http.ResponseEntity<User> response = controller.getUser(1L);

        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful(), "❌ Failure: HTTP Status is not 200 OK!");
        assertEquals("Alice", response.getBody().getName());
    }

    /**
     * 🎮 VISUAL SWING INTERFACE
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("🌐 Exercise 3: MockMvc Web Controller Monitor");
            frame.setSize(520, 320);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 8, 8, 8);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel urlPromptLabel = new JLabel("Simulated HTTP Target URI:");
            endpointField = new JTextField("/users/1", 15); 

            mockMvcButton = new JButton("Perform MockMvc Web Request");
            
            responseBodyArea = new JTextArea(6, 35);
            responseBodyArea.setEditable(false);
            responseBodyArea.setText("HTTP Response Monitor Status: Idle\nClick above to fire request mock payload.");
            responseBodyArea.setBorder(BorderFactory.createEtchedBorder());
            responseBodyArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

            mockMvcButton.addActionListener(new UserControllerTest());

            gbc.gridx = 0; gbc.gridy = 0; panel.add(urlPromptLabel, gbc);
            gbc.gridx = 1; panel.add(endpointField, gbc);
            
            gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2; panel.add(mockMvcButton, gbc);
            gbc.gridy = 2; panel.add(responseBodyArea, gbc);

            frame.add(panel);
            frame.setVisible(true);
        });
    }

    /**
     * ⚡ ERROR-FREE WEB ENVIRONMENT SIMULATION HANDLER
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String uriInput = endpointField.getText().trim();

            if (!uriInput.startsWith("/users/")) {
                responseBodyArea.setText("❌ MockMvc Status: 400 Bad Request\nError: Route must start with '/users/{id}'");
                responseBodyArea.setForeground(Color.RED);
                return;
            }

            String idToken = uriInput.substring(7);
            Long parsedUserId = Long.parseLong(idToken);

            UserRepository simulatedRepo = id -> {
                if (id.equals(1L)) return Optional.of(new User(1L, "Charlie Green"));
                if (id.equals(2L)) return Optional.of(new User(2L, "Diana Prince"));
                return Optional.empty();
            };

            UserService userServiceInstance = new UserService(simulatedRepo);
            UserController controllerInstance = new UserController(userServiceInstance);

            // Explicit fully-qualified naming applied here as well
            @SuppressWarnings("unchecked")
			org.springframework.http.ResponseEntity<User> simulatedWebResponse = controllerInstance.getUser(parsedUserId);
            User bodyContent = simulatedWebResponse.getBody();

            if (bodyContent != null) {
                responseBodyArea.setText(
                    "HTTP/1.1 200 OK Status Passed\n" +
                    "Content-Type: application/json\n\n" +
                    "{\n" +
                    "  \"id\": " + bodyContent.getId() + ",\n" +
                    "  \"name\": \"" + bodyContent.getName() + "\"\n" +
                    "}"
                );
                responseBodyArea.setForeground(new Color(0, 100, 0)); 
            } else {
                responseBodyArea.setText("HTTP/1.1 404 Not Found\nContent-Type: text/plain\n\nError: User dataset missing.");
                responseBodyArea.setForeground(Color.BLUE);
            }

        } catch (NumberFormatException | StringIndexOutOfBoundsException ex) {
            responseBodyArea.setText("❌ MockMvc Status: 500 Internal Server Error\nCause: Target URI ID context parameter is invalid.");
            responseBodyArea.setForeground(Color.RED);
        }
    }
}