package EECS3311Deliverable2.frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.example.Item;
import com.example.User;

public class CheckoutItems {
	private User currentUser;
	private DefaultListModel<String> cartList = new DefaultListModel<>();
	private HashMap<String, Item> grabList = new HashMap<String, Item>();
	private JPanel j;
	private JPanel checkoutInfo;
	private JLabel balanceLabel;

	public CheckoutItems(JPanel j, User currentUser) {
		this.currentUser = currentUser;
		this.j = j;
	}
	
	public JPanel show(JPanel j) {
		System.out.println("is cart empty?: " + cartList.isEmpty() + " account Balance?: " + this.currentUser.getAccountBalance());
		System.out.println(this.currentUser.userCart + " " + "HI");
		// String[] a = { "a", "b", "c", "c", "c", "c", "c", "c", "c", "c" };
		loadCart(this.cartList, grabList);

		System.out.println(this.currentUser.userCart + " " + "BYE");
		
		JLabel checkoutLabel = createLabel("Welcome to Checkout!");
		checkoutLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		j.add(checkoutLabel, BorderLayout.NORTH);
		
		JList cart = new JList<>(cartList);
		cart.setPreferredSize(new Dimension(900, 800));
		JScrollPane listOfItems = new JScrollPane(cart);
		listOfItems.setPreferredSize(new Dimension(900, 400));
		j.add(listOfItems, BorderLayout.CENTER);
		
		//BUTTONS
		JButton removeCart = createButton("Remove from Cart", 200, 50);
		removeCart.addActionListener(e -> {
			String selectedItem = (String) cart.getSelectedValue();
			if (selectedItem != null) {
				cartList.removeElement(selectedItem);
				this.currentUser.cartTotal -= grabList.get(selectedItem).cost;
				this.currentUser.userCart.remove(grabList.get(selectedItem));
				grabList.remove(selectedItem);
				refresh();
			}
		});
		
		JButton checkoutItems = createButton("Checkout", 200, 50);
		checkoutItems.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent click) {
				if(cartList.isEmpty() == true) {
					JOptionPane.showMessageDialog(j, "Reason: Cart is empty!", "Checkout Unavailable", JOptionPane.OK_OPTION);
				}
				else {
					CheckoutPayment checkout = new CheckoutPayment(CheckoutItems.this, currentUser);
				}
			}
		});

		//method to get a total here added into the JLabel
		this.checkoutInfo = createCheckoutInfo(this.currentUser.cartTotal);
		
		j.add(this.checkoutInfo, BorderLayout.CENTER);
	
		j.add(checkoutItems, BorderLayout.SOUTH);
		j.add(removeCart, BorderLayout.SOUTH);
		// j.add(refreshCart, BorderLayout.SOUTH);

		return this.j;
	}

	protected void newBalance(double balance) {
		this.currentUser.setAccountBalance(balance);
		refresh();
	}

	private void loadCart(DefaultListModel<String> cartList, HashMap<String, Item> grabList) {
		for (Item item : this.currentUser.userCart) {
			System.out.println("Init on user cart " + this.currentUser.cartTotal);
			String formatting = item.name + " " + "(" + item.itemType + ") " + "(" + String.format("%.2f", item.cost) + ")";
			cartList.addElement(item.name + " " + "(" + item.itemType + ") " + "(" + String.format("%.2f", item.cost) + ")");
			grabList.put(formatting, item);
		}
	}

	protected void emptyCart() {
		cartList.clear();
		grabList.clear();
		this.currentUser.cartTotal = 0.00;
		this.currentUser.userCart.clear();
		System.out.println(this.currentUser.getAccountBalance());
		refresh();
	}
	
	private JLabel createLabel(String label) {
		JLabel newLabel = new JLabel(label);
		return newLabel;
	}
	
	private JButton createButton(String name, int width, int height) {
        JButton button = new JButton(name);
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(width, height));
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        return button;
    }
	
	private JPanel createCheckoutInfo(double subtotalAmount) {
		JPanel checkoutInfo = new JPanel();

		double sub = subtotalAmount;
		double taxes = sub * 0.13;
		double total = sub + taxes;
		
		this.balanceLabel = createLabel("Account Balance: " + String.format("%.2f", this.currentUser.getAccountBalance()) + " ");
		JLabel subtotal = createLabel("Subtotal: " + String.format("%.2f", subtotalAmount) + " |");
		JLabel taxTotal = createLabel("Taxes: " + String.format("%.2f", taxes) + " |");
		JLabel finalTotal = createLabel("Total: " + String.format("%.2f", total));
		
		this.balanceLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		subtotal.setFont(new Font("Arial", Font.PLAIN, 15));
		taxTotal.setFont(new Font("Arial", Font.PLAIN, 15));
		finalTotal.setFont(new Font("Arial", Font.PLAIN, 15));
		
		JList totals = new JList<>();
		totals.add(this.balanceLabel);
		totals.add(subtotal);
		totals.add(taxTotal);
		totals.add(finalTotal);
		
		checkoutInfo.add(this.balanceLabel);
		checkoutInfo.add(subtotal);
		checkoutInfo.add(taxTotal);
		checkoutInfo.add(finalTotal);
		
		return checkoutInfo;
	}

	//cart gets refreshed when this is called
	public void refresh() {
		cartList.clear();
		this.balanceLabel.setText("Account Balance: " + String.format("%.2f", this.currentUser.getAccountBalance()) + " |");
		loadCart(cartList, grabList);
		j.remove(checkoutInfo);
		this.checkoutInfo = createCheckoutInfo(this.currentUser.cartTotal);
		j.add(this.checkoutInfo);
		j.repaint();
		j.revalidate();
	}
}
