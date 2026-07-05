package com.spring.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Exercise 7: Custom Query Repository Execution Validation with Dashboard View.
 */
public class UserRepositoryQueryTest implements ActionListener {

    private static JTextField nameSearchField;
    private static JTextArea databaseOutputArea;
    private static JButton runQueryButton;

    /**
     * 🤖 AUTOMATED JUNIT TEST
     */
    @Test
    public void testCustomRepositoryFindByNameQuery() {
        System.out.println("🧪 [JUnit 5 Automated] Executing custom findByName query validation stream...");
        
        // Define repository mock with custom matching query layer logic
        UserRepository customRepo = new UserRepository() {
            @Override
            public Optional<User> findById(Long id) { return Optional.empty(); }

            @Override
            public List<User> findByName(String name) {
                List<User> users = new ArrayList<>();
                if ("Clark Kent".equals(name)) {
                    users.add(new User(77L, "Clark Kent"));
                }
                return users;
            }
        };

        List<User> queryResult = customRepo.findByName("Clark Kent");
        
        assertFalse(queryResult.isEmpty(), "❌ Failure: Query collection shouldn't be empty!");
        assertEquals(1, queryResult.size());
        assertEquals("Clark Kent", queryResult.get(0).getName());
    }

    /**
     * 🎮 VISUAL SWING INTERFACE
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("🔍 Exercise 7: Repository Query Inspector");
            frame.setSize(540, 360);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(6, 6, 6, 6);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel queryPromptLabel = new JLabel("Enter Name Query (Try 'Clark Kent' or 'Bruce Wayne'):");
            nameSearchField = new JTextField("Clark Kent", 15);

            runQueryButton = new JButton("Execute findByName(String name) Query");
            
            databaseOutputArea = new JTextArea(8, 42);
            databaseOutputArea.setEditable(false);
            databaseOutputArea.setText("Repository Result Table Stream: Idle.\nInput query string value above to search records.");
            databaseOutputArea.setBorder(BorderFactory.createEtchedBorder());
            databaseOutputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

            runQueryButton.addActionListener(new UserRepositoryQueryTest());

            gbc.gridx = 0; gbc.gridy = 0; panel.add(queryPromptLabel, gbc);
            gbc.gridy = 1; panel.add(nameSearchField, gbc);
            gbc.gridy = 2; panel.add(runQueryButton, gbc);
            gbc.gridy = 3; panel.add(databaseOutputArea, gbc);

            frame.add(panel);
            frame.setVisible(true);
        });
    }

    /**
     * ⚡ CUSTOM JPA DERIVED METHOD SIMULATOR
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        databaseOutputArea.setText("");
        databaseOutputArea.setForeground(Color.BLACK);
        
        String searchTargetName = nameSearchField.getText().trim();

        if (searchTargetName.isEmpty()) {
            databaseOutputArea.setText("❌ Repository Error: Target search parameter text is blank.");
            databaseOutputArea.setForeground(Color.RED);
            return;
        }

        // Simulating the backend custom dataset rows
        UserRepository runtimeRepoSimulator = new UserRepository() {
            @Override
            public Optional<User> findById(Long id) { return Optional.empty(); }

            @Override
            public List<User> findByName(String name) {
                List<User> rows = new ArrayList<>();
                // Populate fake table rows for selection match matches
                if (name.equalsIgnoreCase("Clark Kent")) {
                    rows.add(new User(77L, "Clark Kent"));
                } else if (name.equalsIgnoreCase("Bruce Wayne")) {
                    rows.add(new User(100L, "Bruce Wayne"));
                    rows.add(new User(101L, "Bruce Wayne (Alt Timeline)"));
                }
                return rows;
            }
        };

        databaseOutputArea.append("⚡ Dispatching Query: SELECT * FROM users WHERE name = '" + searchTargetName + "'\n");
        List<User> matchingRows = runtimeRepoSimulator.findByName(searchTargetName); //

        if (!matchingRows.isEmpty()) {
            databaseOutputArea.append("✔️ Query processing complete. Rows returned: " + matchingRows.size() + "\n\n");
            databaseOutputArea.append("=================== DATASET RESULTS ===================\n");
            for (User u : matchingRows) {
                databaseOutputArea.append("  [ID]: " + u.getId() + "  |  [NAME]: " + u.getName() + "\n");
            }
            databaseOutputArea.append("========================================================\n");
            databaseOutputArea.setForeground(new Color(0, 100, 0)); // Success Dark Green
        } else {
            databaseOutputArea.append("\n⚠️ HTTP Status: 200 OK passed.\nResult State: Empty Set (0 rows matching criteria found).");
            databaseOutputArea.setForeground(Color.BLUE);
        }
    }
}