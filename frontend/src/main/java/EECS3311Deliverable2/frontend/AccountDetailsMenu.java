package EECS3311Deliverable2.frontend;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.example.User;

public class AccountDetailsMenu extends JFrame {
    private User currentUser;
    private JLabel nameLabel;
    private JLabel accountType;
    private JLabel emailLabel;
    private JLabel balanceLabel;

    public AccountDetailsMenu(User currentUser) {
        this.currentUser = currentUser;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Account Details");
        setSize(600, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel userInfoPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        userInfoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Display user details
        nameLabel = new JLabel("Name: " + currentUser.getName());
        emailLabel = new JLabel("Email: " + currentUser.getEmail());
        accountType = new JLabel("Account Type: " + currentUser.getAccountType());
        balanceLabel = new JLabel("Balance: $" + currentUser.getAccountBalance());

        userInfoPanel.add(nameLabel);
        userInfoPanel.add(emailLabel);
        userInfoPanel.add(balanceLabel);
        userInfoPanel.add(accountType);

        add(userInfoPanel, BorderLayout.CENTER);

        setVisible(true);
    }

}