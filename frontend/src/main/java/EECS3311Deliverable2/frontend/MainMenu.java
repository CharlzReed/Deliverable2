package EECS3311Deliverable2.frontend;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.example.Item;
import com.example.Library;
import com.example.User;

public class MainMenu {
	private JFrame window;
	private CardLayout cardLayout = new CardLayout();
	private JPanel cardPanel = new JPanel(cardLayout);
	private User currentUser;
	private CheckoutItems checkoutUpdate;
	private static final Color backgroundColor = new Color(245, 245, 245);
	private static final Color buttonColor = new Color(220, 220, 220);
	private static final Color textColor = new Color(50, 50, 50);

	public MainMenu(User currentUser) {
		this.currentUser = currentUser;
		setupUIManager();
		show();
	}

	public MainMenu() {
		show();
	}

	private void setupUIManager() {
		UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 14));
		UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 14));
	}

	private JScrollPane createRentedItemsPanel() {
		JPanel rentedItemsPanel = new JPanel();
		rentedItemsPanel.setLayout(new BoxLayout(rentedItemsPanel, BoxLayout.Y_AXIS));
		rentedItemsPanel.setBorder(new TitledBorder(new EtchedBorder(), "Currently Rented Hardcover Books:"));

		// Scroll pane for overflow of items
		JScrollPane scrollPane = new JScrollPane(rentedItemsPanel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		List<String> rentedItems = Library.getUserRentedItemsWithDueDates(currentUser.userID);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate today = LocalDate.now();

		if (rentedItems.isEmpty()) {
			rentedItemsPanel.add(new JLabel("No items currently rented."));
		} else {
			for (String item : rentedItems) {
				JLabel itemLabel = new JLabel(item);
				itemLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

				LocalDate dueDate = LocalDate.parse(item.split(", Due Date: ")[1], formatter);
				if (dueDate.isBefore(today)) {
					itemLabel.setForeground(Color.RED); // Overdue
					itemLabel.setText(itemLabel.getText() + " - Overdue");
				} else if (dueDate.equals(today) || dueDate.isEqual(today.plusDays(1))) {
					itemLabel.setForeground(Color.ORANGE); // Due soon
					itemLabel.setText(itemLabel.getText() + " - Due Soon");
				}
				rentedItemsPanel.add(itemLabel);
			}
		}

		return scrollPane; // Return the scroll pane instead of the panel
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

			//When checkout items is clicked on, everything gets refreshed so cart shows
			if(feature.equals("Checkout Items")) {
				button.addActionListener((ActionEvent e) -> this.checkoutUpdate.refresh());
			}

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

		JLabel helloUser = new JLabel("Hello, " + currentUser.getName() + " - " + currentUser.getUserType().toString(),
				JLabel.LEFT);
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
				"<html><h1>Welcome to YorkU Library Application</h1></html>",
				JLabel.CENTER);

		// Add the rented items panel
		JScrollPane rentedItemsPanel = createRentedItemsPanel();

		panel.add(welcomeLabel, BorderLayout.NORTH);
		panel.add(rentedItemsPanel, BorderLayout.CENTER);

		return panel;
	}

	private JButton createNavButton(String cardName) {
		JButton button = new JButton(cardName);
		button.setAlignmentX(Component.LEFT_ALIGNMENT);
		button.setFocusable(false);
		//added so it can open new window on button -addison
		if (cardName=="Request New Textbook"){
			button.addActionListener((ActionEvent e) -> {
				new ReqNewTextbook(currentUser).show();
			});
		}else{

		
		button.addActionListener((ActionEvent e) -> cardLayout.show(cardPanel, cardName));
		}
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
		if (featureName == ("Open Book Online")) {

			OpenOnBook onlinebook = new OpenOnBook(currentUser.rentedItems);
			return onlinebook.show(panel);
		} else if (featureName == ("Virtual Textbook")) {
			VirtualTextbook virtualtextbook = new VirtualTextbook(currentUser.rentedItems);
			return virtualtextbook.show(panel);
		}else if (featureName==("Subscribe to Newsletter")){
			SubToNews news =new SubToNews(currentUser);
			for(Item newsletter: news.getnewsletter()){
				currentUser.addItem(newsletter);
			}
			return news.show(panel);
		} else if (featureName == ("Request New Textbook")) {

		}
		else if (featureName == ("Rent Physical Item")) {
			new RentPhysItem(panel, this.currentUser);
		}
		else if (featureName == ("Checkout Items")) {
			this.checkoutUpdate = new CheckoutItems(panel, this.currentUser);
			this.checkoutUpdate.show(panel);
		}
		
		return panel;
	}

	public void setVisible(boolean visible) {
		window.setVisible(visible);
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
