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

public class MainMenu {
	
	private JFrame window;
	
	public MainMenu() {
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
		
		//depends on user type
		JButton virtualtextbookbutton =createButton("Virtual textbook");
		JButton trackcoursetextbookbutton =createButton("Track Course Textbook");
		JLabel hellouser = createLabel("Hello ");
		
		JButton accountdetailsbutton = createButton("Account details");
		accountdetailsbutton.setPreferredSize(new Dimension(100,50));
		JTextField bookSearchField = createTextField();

		JButton openbookonlineButton = createButton("Open Book Online");
		JLabel bookSearchLabel = createLabel("Book Search: ");
		JLabel currentlyrentedLabel = createLabel("Currently Rented Books: ");
		JLabel currentlyrentedbooks = createLabel("");
		
		JButton contactsupportbutton = createButton("Contact Support");
		JButton checkoutitems= createButton("Checkout Items");
		JButton rentphysicalitem = createButton("Rent Physical Item");
		JButton newsletterSubscription = createButton("Subscribe to Newsletter");
		JButton requestnewtextbook=createButton("Request new textbook");
		
		accountdetailsbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				window.dispose();
				
			}
		});
		
		openbookonlineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				window.dispose();
				
			}
		});
		contactsupportbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				window.dispose();
				
			}
		});
		checkoutitems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				window.dispose();
				
			}
		});
		rentphysicalitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				window.dispose();
				
			}
		});
		newsletterSubscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				window.dispose();
				
			}
		});
		
		requestnewtextbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				window.dispose();
				
			}
		});
		//Adding components to main panel, so it is shown to user
		mainPanel.add(hellouser);
		mainPanel.add(openbookonlineButton);
		mainPanel.add(bookSearchLabel);
		mainPanel.add(bookSearchField);
		mainPanel.add(rentphysicalitem);
		mainPanel.add(newsletterSubscription);
		mainPanel.add(currentlyrentedLabel);
		mainPanel.add(virtualtextbookbutton);
		mainPanel.add(virtualtextbookbutton);
		mainPanel.add(trackcoursetextbookbutton);
		mainPanel.add(accountdetailsbutton);
		mainPanel.add(checkoutitems);
		mainPanel.add(contactsupportbutton);
		mainPanel.add(requestnewtextbook);
		//Buttons
		
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