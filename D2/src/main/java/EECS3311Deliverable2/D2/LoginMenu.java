package EECS3311Deliverable2.D2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginMenu {

    private JFrame window;
    // using temp credentials
    private final String tempEmail = "temp@yorku.ca";
    private final String tempPassword = "123";

    public LoginMenu() {
        show();
    }

    private void show() {
        window = new JFrame("YorkU Library Application Login");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(1200, 800);
        window.setLocationRelativeTo(null);
        window.setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JLabel emailLabel = new JLabel("Email: ",SwingConstants.CENTER);
        
        JTextField emailField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = createButton("Login");
        JButton backButton = createButton("<- Back");

        loginButton.setPreferredSize(new Dimension(100, 50));
        backButton.setPreferredSize(new Dimension(100, 50));

        mainPanel.add(emailLabel);
        mainPanel.add(emailField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(backButton);
        mainPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // we need to make this better later
                if (email.equals(tempEmail) && password.equals(tempPassword)) {
                    JOptionPane.showMessageDialog(window, "Login Successful!");
                    window.dispose();
                    new MainMenu(); 
                } else {
                    JOptionPane.showMessageDialog(window, "Invalid email or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                new MainWindow();
            }
        });

        window.add(mainPanel);
        window.setVisible(true);
    }

    private JButton createButton(String name) {
        JButton button = new JButton(name);
        button.setFocusable(false);
        return button;
    }
}
