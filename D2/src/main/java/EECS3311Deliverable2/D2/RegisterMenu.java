package EECS3311Deliverable2.D2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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
		
		//Adding email and passwords for registration
		JButton backButton = createButton("<- Back");
		JTextField emailField = createTextField();
		JPasswordField passwordField = new JPasswordField(20);
		JButton registerButton = createButton("Register");
		JLabel emailLabel = createLabel("Email: ");
		JLabel passwordLabel = createLabel("Password: ");
		registerButton.setPreferredSize(new Dimension(100,50));
		backButton.setPreferredSize(new Dimension(100,50));
		
		//Adding radio button and group for the user type
		JLabel userLabel = createLabel("User Type: ");
		JRadioButton studentBox = createRadioButton("Student");
		JRadioButton facultyBox = createRadioButton("Faculty Staff");
		JRadioButton nonFacultyBox = createRadioButton("Non-Faculty Staff");
		JRadioButton visitorBox = createRadioButton("Visitor");
		
		//Adding radio buttons to the user group
		ButtonGroup userType = new ButtonGroup();
		userType.add(studentBox);
		userType.add(facultyBox);
		userType.add(nonFacultyBox);
		userType.add(visitorBox);
		
		//Adding components to main panel, so it is shown to user
		mainPanel.add(backButton);
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
		
		//back button takes the user back to MainWindow.java
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				window.dispose();
				MainWindow open = new MainWindow();
			}
		});
		
		/* Note
		 * This should make a new copy of a user into the csv
		 * file provided, but it will be unverified until
		 * verified by a member of faculty. */
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				window.dispose();
			}
		});
		
		//Adds the main panel so it is visible to user
		this.window.add(mainPanel);
				
		//Making the window visible
		this.window.setVisible(true);
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
