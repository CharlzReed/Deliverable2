package EECS3311Deliverable2.frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.example.User;
import com.example.Item;
import com.example.Library;

public class RentPhysItem {
	private User currentUser;

	public RentPhysItem(JPanel j, User currentUser) {
		this.currentUser = currentUser;
		show(j);
	}
	
	private JPanel show(JPanel j) {
		DefaultListModel<String> itemList=new DefaultListModel<>();
		HashMap<String, Item> grabList = new HashMap<>();
		
		//Storing description of items to show to the user, along with the list of items
		loadData(itemList, grabList);
		
		JTextField searchBar = createTextField(50);
		JButton resetButton = createButton("Reset Results", 150, 20);
		JButton searchButton = createButton("Search -->", 150, 20);
		
		j.add(searchBar);
		j.add(resetButton);
		j.add(searchButton);

		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemList.clear();
				loadData(itemList, grabList);
			}
		});
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchText = searchBar.getText();
				List<Item> results = Library.searchItems(searchText, Library.items.size());

				itemList.clear();

				for (Item item : results) {
					if(item.isSubscription() == false) {
						itemList.addElement(item.name + " " + "(" + item.itemType + ") " + "(" + String.format("%.2f", item.cost) + ")");
					}
				}
			}
		});
		
		JList itemSelect = new JList<>(itemList);
		itemSelect.setPreferredSize(new Dimension(900, 800));
		JScrollPane itemsScroll =new JScrollPane(itemSelect);
		
		itemsScroll.setPreferredSize(new Dimension(900,600));
		j.add(itemsScroll, BorderLayout.CENTER);

		JButton addCart = createButton("Add To Cart", 200, 50);
		
		addCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedItem = ((String) itemSelect.getSelectedValue());
				addItemToCart(j, grabList, selectedItem);
			}
		});
		
		j.add(addCart,BorderLayout.CENTER);

		return j;
	}

	private void loadData(DefaultListModel<String> itemList, HashMap<String, Item> storedList) {
		for (Item item : Library.items) {
			if(item.isSubscription() == false) {
				String formatted = item.name + " " + "(" + item.itemType + ") " + "(" + String.format("%.2f", item.cost) + ")";
				itemList.addElement(formatted);
				storedList.put(formatted, item);
			}
		}
	}
	
	private JTextField createTextField(int width) {
		JTextField textfield = new JTextField(width);
		return textfield;
	}
	
	private JButton createButton(String name, int width, int height) {
        JButton button = new JButton(name);
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(width, height));
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        return button;
    }

	private void addItemToCart(JPanel j, HashMap<String, Item> item, String key) {
		boolean canRent = this.currentUser.rentItem(item.get(key));
		if (canRent == true) {
			this.currentUser.addToCart(item.get(key));
			JOptionPane.showMessageDialog(j, "Item added to cart.", "Item Added", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(j, "Reason: " + grabDenyReason(this.currentUser.rentalDenied), "Item Cannot Be Added", JOptionPane.OK_OPTION);
		}

		System.out.println(this.currentUser.userCart + " " + this.currentUser.cartTotal);
	}

	private String grabDenyReason(int rentalDenied) {
		switch (rentalDenied) {
			case 1: {
				return "Too many items overdue!";
			}
			case 2: {
				return "Item does not exist!";
			}
			case 3: {
				return "Item has no available copies!";
			}
			case 4: {
				return "You are over the rental item limit!";
			}

		}
		return "Error";
	}
	
}
