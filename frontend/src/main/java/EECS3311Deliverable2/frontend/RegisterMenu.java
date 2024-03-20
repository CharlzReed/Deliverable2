package EECS3311Deliverable2.frontend;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.example.DataManager;
import com.example.Library;
import com.example.User;
import com.example.UserType;

public class RegisterMenu {

	private JFrame window;

	public RegisterMenu() {
		show();
	}

	private void show() {
		window = new JFrame();
		this.window.setTitle("YorkU Library Application");
		this.window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.window.setSize(1200, 800);
		this.window.setLocationRelativeTo(null);
		this.window.setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

		// Adding email and passwords for registration
		JButton backButton = createButton("<- Back");
		JTextField nameField = createTextField();
		JTextField emailField = createTextField();
		JPasswordField passwordField = new JPasswordField(20);
		JButton registerButton = createButton("Register");

		JLabel emailLabel = createLabel("Email: ");
		JLabel passwordLabel = createLabel("Password: ");
		registerButton.setPreferredSize(new Dimension(100, 50));
		backButton.setPreferredSize(new Dimension(100, 50));
		JLabel nameLabel = createLabel("Name: ");
		// Adding radio button and group for the user type
		JLabel userLabel = createLabel("User Type: ");
		JRadioButton studentBox = createRadioButton("Student");
		JRadioButton facultyBox = createRadioButton("Faculty");
		JRadioButton nonFacultyBox = createRadioButton("Non_Faculty");
		JRadioButton visitorBox = createRadioButton("Visitor");

		// Adding radio buttons to the user group
		ButtonGroup userType = new ButtonGroup();
		userType.add(studentBox);
		userType.add(facultyBox);
		userType.add(nonFacultyBox);
		userType.add(visitorBox);

		// Adding components to main panel, so it is shown to user
		mainPanel.add(backButton);
		mainPanel.add(nameLabel);
		mainPanel.add(nameField);
		mainPanel.add(emailLabel);
		mainPanel.add(emailField);
		mainPanel.add(passwordLabel);
		mainPanel.add(passwordField);
		mainPanel.add(userLabel);
		mainPanel.add(studentBox);
		mainPanel.add(facultyBox);
		mainPanel.add(nonFacultyBox);
		mainPanel.add(visitorBox);
		mainPanel.add(registerButton);

		// back button takes the user back to MainWindow.java
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.dispose();
				MainWindow open = new MainWindow();
			}
		});

		/*
		 * Note
		 * This should make a new copy of a user into the csv
		 * file provided, but it will be unverified until
		 * verified by a member of faculty.
		 */

		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String email = emailField.getText().toLowerCase();
				String password = new String(passwordField.getPassword());
				String selectedUserType = getUserType(userType);

				// used to validate email
				if (!isEmailValid(email)) {
					JOptionPane.showMessageDialog(window, "Please enter a valid email address.", "Validation Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// to validate name
				if (name.trim().isEmpty()) {
					JOptionPane.showMessageDialog(window, "Please enter your name.", "Validation Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// used to validate user selection
				if (selectedUserType.isEmpty()) {
					JOptionPane.showMessageDialog(window, "Please select a user type.", "Validation Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// uused to validate password
				if (!isPasswordValid(password)) {
					JOptionPane.showMessageDialog(window,
							"Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character, and be at least 8 characters long.",
							"Validation Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				UserType type = UserType.valueOf(selectedUserType.toUpperCase());
				int userId = Library.generateNextUserId(); // to generate a unique id for sure
				double accountBalance = 20.0; // default balance for new users

				User newUser = new User(userId, name, email, password, type, accountBalance);

				try {
					DataManager.getInstance().registerNewUser(newUser);
					String successMessage = selectedUserType.equals("Visitor") ? "Registration successful!"
							: "Registration successful! Your account type requires further validation and will be reviewed by the management team.";
					JOptionPane.showMessageDialog(window, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);
					window.dispose();
					new MainWindow().setVisible(true);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(window, "Error registering user.", "Error",
							JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});

		// Adds the main panel so it is visible to user
		this.window.add(mainPanel);

		// Making the window visible
		this.window.setVisible(true);
	}

	private boolean isEmailValid(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
				"(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		return email.matches(emailRegex);
	}

	private boolean isPasswordValid(String password) {
		boolean hasUppercase = !password.equals(password.toLowerCase());
		boolean hasLowercase = !password.equals(password.toUpperCase());
		boolean hasDigit = password.matches(".*\\d.*");
		boolean hasSpecial = password.matches(".*[!@#&()–[{}]:;',?/*~$^+=<>].*");
		return password.length() >= 8 && hasUppercase && hasLowercase && hasDigit && hasSpecial;
	}

	private String getUserType(ButtonGroup userTypeGroup) {
		for (Enumeration<AbstractButton> buttons = userTypeGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}
		return "";
	}

	private JButton createButton(String name) {
		JButton button = new JButton(name);
		button.setFocusable(false);
		return button;
	}

	private JTextField createTextField() {
		JTextField textfield = new JTextField(20);
		return textfield;
	}

	private JRadioButton createRadioButton(String name) {
		JRadioButton checkButton = new JRadioButton(name);
		return checkButton;
	}

	private JLabel createLabel(String name) {
		JLabel label = new JLabel(name);
		return label;
	}
}
