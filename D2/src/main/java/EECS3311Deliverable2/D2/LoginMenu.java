package EECS3311Deliverable2.D2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginMenu {
	
	private JFrame window;
	
	public LoginMenu() {
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
		
		//Adding email and password fields for registration
		JButton backButton = createButton("<- Back");
		JTextField emailField = createTextField();
		JPasswordField passwordField = new JPasswordField(20);
		JLabel emailLabel = createLabel("Email: ");
		JLabel passwordLabel = createLabel("Password: ");
		JButton loginButton = createButton("Login");
		
		//Changing button sizes
		backButton.setPreferredSize(new Dimension(100,50));
		loginButton.setPreferredSize(new Dimension(100,50));
		
		//Back button returns to MainWindow.java
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				window.dispose();
				MainWindow open = new MainWindow();
			}
		});
		
		//Action listener for login button press
		/*Note
		 * This should validate user email and password
		 * using the provided csv of the user info, which will
		 * either reject or accept the info given by the user*/
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				window.dispose();
				
			}
		});
		//Adding components to main panel, so it is shown to user
		mainPanel.add(backButton);
		mainPanel.add(emailLabel);
		mainPanel.add(emailField);
		mainPanel.add(passwordLabel);
		mainPanel.add(passwordField);
		mainPanel.add(loginButton);
		
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
	
	private JLabel createLabel(String name) {
		JLabel label = new JLabel(name);
		return label;
	}
}