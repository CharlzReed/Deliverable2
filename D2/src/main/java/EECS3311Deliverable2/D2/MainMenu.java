package EECS3311Deliverable2.D2;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class MainMenu {
	private List shoppingcart=new ArrayList();
	private JFrame window;
	private CardLayout cardLayout = new CardLayout();
	private JPanel cardPanel = new JPanel(cardLayout);
	private String userName = "John Doe"; // we need to retrive from backend this and below
	private String userType = "Faculty";
	private static final Color backgroundColor = new Color(245, 245, 245);
	private static final Color buttonColor = new Color(220, 220, 220);
	private static final Color textColor = new Color(50, 50, 50);

	public MainMenu() {
		show();
	}

	private void show() {
		window = new JFrame("YorkU Library Application");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1200, 800);
		window.setLocationRelativeTo(null);
		window.setResizable(true);

		JPanel navigationPanel = createNavigationPanel();
		JPanel userInfoPanel = createUserInfoPanel();

		setupCardPanel();

		window.setLayout(new BorderLayout());
		window.add(userInfoPanel, BorderLayout.NORTH);
		window.add(navigationPanel, BorderLayout.WEST);
		window.add(cardPanel, BorderLayout.CENTER);

		window.setVisible(true);
	}

	private JPanel createNavigationPanel() {
		String[] features = {
			"Home", "Rent Physical Item", "Virtual Textbook", 
			"Track Course Textbook", "Open Book Online", 
			"Subscribe to Newsletter", "Checkout Items", 
			"Request New Textbook"
		}; // we can add more features here as required 
	
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
	
		for (String feature : features) {
			JButton button = createNavButton(feature);
			button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
			panel.add(button);
			panel.add(Box.createRigidArea(new Dimension(0, 10)));
		}
	
		return panel;
	}
	

	private JPanel createUserInfoPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
	
		panel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.DARK_GRAY));
	
		JLabel helloUser = new JLabel("Hello, " + userName + " - " + userType, JLabel.LEFT);
		helloUser.setBorder(new EmptyBorder(0, 10, 0, 0));
	
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.setOpaque(false); 
		JButton accountDetailsButton = createButton("Account Details");
		JButton logoutButton = createButton("Logout");
	
		buttonsPanel.add(accountDetailsButton);
		buttonsPanel.add(logoutButton);
	
		panel.add(helloUser, BorderLayout.WEST);
		panel.add(buttonsPanel, BorderLayout.EAST);
	
		return panel;
	}
	

	private void setupCardPanel() {
		JPanel homeCard = createHomeCard();
		cardPanel.add(homeCard, "Home");

		// otherra feature cards will be added here following the same pattern

		// i am adding dummy values here to test
		for (String cardName : new String[] { "Rent Physical Item", "Virtual Textbook",
				"Track Course Textbook", "Open Book Online",
				"Subscribe to Newsletter", "Checkout Items",
				"Request New Textbook" }) {
			cardPanel.add(createFeatureCard(cardName), cardName);
		}
	}

	private JPanel createHomeCard() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel welcomeLabel = new JLabel(
				"<html><h1>Welcome to YorkU Library Application</h1><p>Select an option from the left to begin.</p></html>",
				JLabel.CENTER);
		panel.add(welcomeLabel);
		return panel;
	}

	private JButton createNavButton(String cardName) {
		JButton button = new JButton(cardName);
		button.setAlignmentX(Component.LEFT_ALIGNMENT);
		button.setFocusable(false);
		button.addActionListener((ActionEvent e) -> cardLayout.show(cardPanel, cardName));
		return button;
	}

	private JButton createButton(String name) {
		JButton button = new JButton(name);
		button.setFocusable(false);
		button.setBackground(buttonColor); 
		button.setForeground(textColor); 
		button.setOpaque(true);
		button.setBorderPainted(true); 
		return button;
	}

	private JPanel createFeatureCard(String featureName) {
		JPanel panel = new JPanel();
		if (featureName== ("Open Book Online")){
		
			OpenOnBook onlinebook=new OpenOnBook();
			return onlinebook.show(panel);
		}else if(featureName==("Request New Textbook")) {
			
		}else if (featureName==("Virtual Textbook")) {
			VirtualTextbook virtualtextbook=new VirtualTextbook();
			return virtualtextbook.show(panel);
		}
		
		
		return panel;
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(MainMenu::new);
	}
}
