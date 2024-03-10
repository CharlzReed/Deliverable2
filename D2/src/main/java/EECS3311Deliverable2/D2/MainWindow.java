package EECS3311Deliverable2.D2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {

    private JFrame window;

    public MainWindow() {
        show();
    }

    private void show() {
        window = new JFrame("YorkU Library Application");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1200, 800);
        window.setLocationRelativeTo(null);
        window.setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Welcome message panel
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new FlowLayout());
        welcomePanel.setBackground(Color.WHITE);
        JLabel welcomeLabel = new JLabel("<html><body><center>Welcome to the YorkU Library Management System!<br>Please log in or register to continue.</center></body></html>");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        buttonPanel.setBackground(Color.WHITE);

        JButton loginButton = createButton("Login");
        JButton registerButton = createButton("Register");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                LoginMenu login = new LoginMenu();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                RegisterMenu register = new RegisterMenu();
            }
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        mainPanel.add(welcomePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        window.add(mainPanel);
        window.setVisible(true);
    }

    private JButton createButton(String name) {
        JButton button = new JButton(name);
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(200, 50));
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        return button;
    }
}
