package EECS3311Deliverable2.frontend;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.example.Library;
import com.example.User;

public class LoginMenu {

    private JFrame window;

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

        JLabel emailLabel = new JLabel("Email: ", SwingConstants.CENTER);

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
                String email = emailField.getText().toLowerCase();
                String password = new String(passwordField.getPassword());

                // Read all users from the backend. this is how we link backend to frontend.
                // also look at the imports at the top.
                // along with this i implemented a maven file. all you need to do is import the
                // packages from backend that u need and use those in the frontend

                try {
                    User loggedIn = null;
                    for (User user : Library.users) {
                        if (user.email.toLowerCase().equals(email) && user.password.equals(password)) {
                            JOptionPane.showMessageDialog(window, "Login Successful!");
                            loggedIn = user;
                            break;
                        }
                    }
                    if (loggedIn != null) {
                        window.dispose();
                        new MainMenu(loggedIn).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(window, "Invalid email or password.", "Login Failed",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(window, "Error reading user data.", "Login Error",
                            JOptionPane.ERROR_MESSAGE);
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
