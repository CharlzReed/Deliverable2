package EECS3311Deliverable2.frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CheckoutPayment {
	private JFrame window;
	
	public CheckoutPayment() {
		show();
	}
	
	private void show() {
		window = new JFrame("YorkU Library Application");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setSize(600, 400);
		window.setLocationRelativeTo(null);
		window.setResizable(true);
		
		JPanel buttonContainer = new JPanel();
		buttonContainer.setSize(new Dimension(600, 50));
		JButton debitCreditButton = createButton("Debit/Credit", 200, 50);
		JButton mobileWalletButton = createButton("Mobile Wallet", 200, 50);
		
		buttonContainer.add(debitCreditButton);
		buttonContainer.add(mobileWalletButton);
		window.add(buttonContainer, BorderLayout.NORTH);
		
		//DEBIT/CREDIT PAYMENT
		JPanel cardPayment = new JPanel();
		cardPayment.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel firstNameLabel = createLabel("First Name:");
		JTextField firstNameInput = createTextField(50);
		JLabel lastNameLabel = createLabel("Last Name:");
		JTextField lastNameInput = createTextField(50);
		JLabel cardLabel = createLabel("Card Number (16 Digits):");
		JTextField cardInput = createTextField(50);
		JLabel expLabel = createLabel("Exp Date (MM/YY):");
		JTextField expInput = createTextField(50);
		JLabel CVVLabel = createLabel("CVV (3 Digits):");
		JTextField CVVInput = createTextField(50);
		
		cardPayment.add(firstNameLabel);
		cardPayment.add(firstNameInput);
		cardPayment.add(lastNameLabel);
		cardPayment.add(lastNameInput);
		cardPayment.add(cardLabel);
		cardPayment.add(cardInput);
		cardPayment.add(expLabel);
		cardPayment.add(expInput);
		cardPayment.add(CVVLabel);
		cardPayment.add(CVVInput);
		
		JButton pay = createButton("Purchase", 200, 50);
		cardPayment.add(pay, BorderLayout.SOUTH);
		
		//Mobile Wallet Payment
		JPanel mobilePayment = new JPanel();
		double balance = 0.0;
		
		JLabel mobileBalance = createLabel("Balance: " + balance + "\n");
		JLabel newBalance = createLabel("Balance After Purchase: " + balance);
		JButton pay2 = createButton("Purchase", 200, 50);
		
		mobilePayment.add(mobileBalance);
		mobilePayment.add(newBalance);
		mobilePayment.add(pay2);
		
		debitCreditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent click) {
				window.remove(mobilePayment);
				window.add(cardPayment);
				window.repaint();
				window.revalidate();
			}
		});
		
		mobileWalletButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent click) {
				window.remove(cardPayment);
				window.add(mobilePayment);
				window.repaint();
				window.revalidate();
			}
		});
		
		window.setVisible(true);
	}
	
	private JButton createButton(String name, int width, int height) {
        JButton button = new JButton(name);
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(width, height));
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        return button;
    }
	
	private JTextField createTextField(int width) {
		JTextField textfield = new JTextField(width);
		return textfield;
	}
	
	private JLabel createLabel(String label) {
		JLabel retLabel = new JLabel(label);
		return retLabel;
	}
	
//	private JPanel createDebitPayment() {
//		JPanel debitPayment = new JPanel();
//		
//		JLabel cardLabel = createLabel("Card Number (16 Digits):");
//		JTextField cardInput = createTextField(30);
//		JLabel expLabel = createLabel("Exp Date (M/Y):");
//		JTextField expInput = createTextField(30);
//		JLabel CVVLabel = createLabel("CVV (3 Digits):");
//		JTextField CVVInput = createTextField(30);
//		
//		debitPayment.add(cardLabel);
//		debitPayment.add(cardInput);
//		debitPayment.add(expLabel);
//		debitPayment.add(expInput);
//		debitPayment.add(CVVLabel);
//		debitPayment.add(CVVInput);
//		
//		return debitPayment;
//	}
//	
//	private JPanel createCreditPayment() {
//		return null;
//		
//	}
//	
//	private JPanel createMobileWalletPayment() {
//		return null;
//	}
}
