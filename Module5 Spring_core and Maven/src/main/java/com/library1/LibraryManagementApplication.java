package com.library1;

import com.library.service.BookService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        // Load the Spring Application Context
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = context.getBean("bookService", BookService.class);

        // Schedule Swing window creation on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> createAndShowGUI(bookService, context));
    }

    private static void createAndShowGUI(BookService bookService, ClassPathXmlApplicationContext context) {
        JFrame frame = new JFrame("Library Management System (Spring + Swing)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 350);
        frame.setLayout(new BorderLayout(10, 10));

        // Book List Display
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String book : bookService.getAvailableBooks()) {
            listModel.addElement(book);
        }
        JList<String> bookList = new JList<>(listModel);
        frame.add(new JScrollPane(bookList), BorderLayout.CENTER);

        // Input Panel
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        JTextField bookInputField = new JTextField();
        JButton addButton = new JButton("Add Book");

        inputPanel.add(new JLabel(" Enter Book Title: "), BorderLayout.WEST);
        inputPanel.add(bookInputField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);
        frame.add(inputPanel, BorderLayout.SOUTH);

        // Action Listener to handle dynamic user input
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = bookInputField.getText().trim();
                if (!title.isEmpty()) {
                    bookService.registerNewBook(title); // Dynamic backend interaction
                    listModel.addElement(title);        // Refresh front-end
                    bookInputField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid book title.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Add shutdown hook to close Spring Context neatly when closing the window
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                context.close();
            }
        });

        frame.setLocationRelativeTo(null); // Center window
        frame.setVisible(true);
    }
}