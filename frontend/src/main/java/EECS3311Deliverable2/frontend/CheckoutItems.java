package EECS3311Deliverable2.frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class CheckoutItems {
	
	private List<String> cart;
	
	public JPanel show(JPanel j) {
		String[] a = { "a", "b", "c", "c", "c", "c", "c", "c", "c", "c" };
		DefaultListModel<String> cartList = new DefaultListModel<>();
		for (String e : a) {
			cartList.addElement(e);
		}
		
		JLabel checkoutLabel = createLabel("Welcome to Checkout!");
		checkoutLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		j.add(checkoutLabel, BorderLayout.NORTH);
		
		JList cart = new JList<>(cartList);
		cart.setPreferredSize(new Dimension(900, 800));
		JScrollPane listOfItems = new JScrollPane(cart);
		listOfItems.setPreferredSize(new Dimension(900, 400));
		
		j.add(listOfItems, BorderLayout.CENTER);
		
		//method to get a total here added into the JLabel
		Double subtotalAmount = 0.0;
		Double taxes = 0.0;
		Double total = 0.0;
		
		JPanel checkoutInfo = createCheckoutInfo(subtotalAmount, taxes, total);
		
		j.add(checkoutInfo, BorderLayout.CENTER);
		
		//BUTTONS
		JButton removeCart = createButton("Remove from Cart", 200, 50);
		removeCart.addActionListener(e -> {
			String selectedItem = (String) cart.getSelectedValue();
			if (selectedItem != null) {
//				backend to remove from cart
			}
		});
		
		JButton checkoutItems = createButton("Checkout", 200, 50);
		checkoutItems.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent click) {
				CheckoutPayment checkout = new CheckoutPayment();
			}
		});
		
		j.add(checkoutItems, BorderLayout.SOUTH);
		j.add(removeCart, BorderLayout.SOUTH);

		return j;
	}

//	private void opentexttextbookdetails(String selectedbook, String a[]) {
//		JFrame textbookdetails = new JFrame(selectedbook);
//		JPanel contentPanel = new JPanel(new BorderLayout());
//		JLabel booklabel = new JLabel("Details for " + selectedbook, SwingConstants.CENTER);
//		contentPanel.add(booklabel, BorderLayout.CENTER);
//		textbookdetails.setLocationRelativeTo(null);
//		textbookdetails.setResizable(false);
//		textbookdetails.add(contentPanel);
//		textbookdetails.setSize(800, 800);
//		textbookdetails.setVisible(true);
//	}
	
	protected void initializeCart(List<String> cart) {
		this.cart = new ArrayList<String>(cart);
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
	
private JPanel createCheckoutInfo(double subtotalAmount, double taxes, double total) {
		JPanel checkoutInfo = new JPanel();
		
		JLabel subtotal = createLabel("Subtotal: " + subtotalAmount);
		JLabel taxTotal = createLabel("Taxes: " + taxes);
		JLabel finalTotal = createLabel("Total: " + total);
		
		subtotal.setFont(new Font("Arial", Font.PLAIN, 15));
		taxTotal.setFont(new Font("Arial", Font.PLAIN, 15));
		finalTotal.setFont(new Font("Arial", Font.PLAIN, 15));
		
		JList totals = new JList<>();
		totals.add(subtotal);
		totals.add(taxTotal);
		totals.add(finalTotal);
		
		checkoutInfo.add(subtotal);
		checkoutInfo.add(taxTotal);
		checkoutInfo.add(finalTotal);
		
		return checkoutInfo;
	}
}
