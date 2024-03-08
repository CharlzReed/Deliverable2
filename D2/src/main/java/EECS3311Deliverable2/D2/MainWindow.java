package EECS3311Deliverable2.D2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainWindow {

	private JFrame window;
	
	public MainWindow() {
		show();
	}
	
	private void show() {
		//Initializing and setting up the window the application will be in
		window = new JFrame();
		this.window.setTitle("YorkU Library Application");
		this.window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.window.setSize(1200, 800);
		this.window.setLocationRelativeTo(null);
		this.window.setResizable(false);
		
		/* !VERY IMPORTANT NOTE!
		 * This class is for opening the window only,
		 * so all other processes will be in their own
		 * class file. 
		 * 
		 * What we are doing right now is inefficient,
		 * so try to make it so that processes are called
		 * from another class into this class*/
		
		//Making a panel to hold the login and register buttons
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		
		//Initializing login and register buttons
		JButton loginButton = createButton("Login");
		loginButton.setPreferredSize(new Dimension(100,50));
		JButton registerButton = createButton("Register");
		registerButton.setPreferredSize(new Dimension(100,50));
		
		//Setting button placement for login and register buttons
		loginButton.setBounds(400, 200, 400, 100);
		registerButton.setBounds(400, 400, 400, 100);
		
		//Action listener for login button press
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				window.dispose();
				LoginMenu open = new LoginMenu();
			}
		});
		
		//Action listener for register button press
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				window.dispose();
				RegisterMenu open = new RegisterMenu();
			}
		});
		
		//Adding buttons to panel so it is visible to user
		mainPanel.add(loginButton);
		mainPanel.add(registerButton);
		
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
	
	private void clearPanel(JPanel panel) {
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
	}
	
}
