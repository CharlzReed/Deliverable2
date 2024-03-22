package EECS3311Deliverable2.frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.example.User;
import com.example.DataManager;
import com.example.CSVReader;

public class CheckoutPayment {
	private JFrame window;
	private CheckoutItems checkout;
	private User currentUser;
	
	public CheckoutPayment(CheckoutItems checkout, User currentUser) {
		this.currentUser = currentUser;
		this.checkout = checkout;
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
		
		JLabel nameLabel = createLabel("Name:");
		JTextField nameInput = createTextField(50);
		JLabel cardLabel = createLabel("Card Number (16 Digits):");
		JTextField cardInput = createTextField(50);
		JLabel expLabel = createLabel("Exp Date (MM/YY):");
		JTextField expInput = createTextField(50);
		JLabel CVVLabel = createLabel("CVV (3 Digits):");
		JTextField CVVInput = createTextField(50);
		
		cardPayment.add(nameLabel);
		cardPayment.add(nameInput);
		cardPayment.add(cardLabel);
		cardPayment.add(cardInput);
		cardPayment.add(expLabel);
		cardPayment.add(expInput);
		cardPayment.add(CVVLabel);
		cardPayment.add(CVVInput);
		
		JButton debitCreditPay = createButton("Purchase", 200, 50);
		cardPayment.add(debitCreditPay, BorderLayout.SOUTH);
		
		//Mobile Wallet Payment
		JPanel mobilePayment = new JPanel();
		
		JLabel mobileBalance = createLabel("Balance: " + String.format("%.2f", this.currentUser.accountBalance) + " |");
		JLabel newBalance = createLabel("Balance After Purchase: " + String.format("%.2f", this.currentUser.accountBalance 
		- (this.currentUser.cartTotal * 1.13)));
		JButton mobileWalletPay = createButton("Purchase", 200, 50);
		
		mobilePayment.add(mobileBalance);
		mobilePayment.add(newBalance);
		mobilePayment.add(mobileWalletPay);
		
		debitCreditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent click) {
				window.remove(mobilePayment);
				window.add(cardPayment);
				window.revalidate();
				window.repaint();
			}
		});
		
		mobileWalletButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent click) {
				window.remove(cardPayment);
				window.add(mobilePayment);
				window.revalidate();
				window.repaint();
			}
		});

		debitCreditPay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent click) {
				boolean isValid = processDebitCredit(nameInput.getText(), cardInput.getText(), expInput.getText(), CVVInput.getText());
				if(isValid) {
					JOptionPane.showMessageDialog(debitCreditPay, "Thank you for your purchase!", "Transaction Success", JOptionPane.INFORMATION_MESSAGE);
					checkout.emptyCart();
					DataManager.getInstance().saveData();
					window.dispose();
				}
				else {
					JOptionPane.showMessageDialog(debitCreditPay, "Reason: This card is invalid, please try again.", "Transaction Failure", JOptionPane.OK_OPTION);
				}
			}
		});

		mobileWalletPay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent click) {
				boolean isValid = processMobileWallet(currentUser.accountBalance, currentUser.cartTotal);
				if(isValid) {
					JOptionPane.showMessageDialog(debitCreditPay, "Thank you for your purchase!", "Transaction Success", JOptionPane.INFORMATION_MESSAGE);
					currentUser.accountBalance -= (currentUser.cartTotal * 1.13);
					checkout.newBalance(currentUser.accountBalance);
					checkout.emptyCart();
					DataManager.getInstance().saveData();
					window.dispose();
				}
				else {
					JOptionPane.showMessageDialog(debitCreditPay, "Your account does not have enough money.", "Transaction Failure", JOptionPane.OK_OPTION);
				}
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

	private boolean processDebitCredit(String name, String cardNumber, String expNum, String cvv) {
		if(name.matches("^[A-Za-z]+(?: [A-Za-z]+)?$") && cardNumber.matches("\\d{16}") && expNum.matches("\\d{2}/\\d{2}") && cvv.matches("\\d{3}")) {
			return true;
		}

		return false;
	}

	private boolean processMobileWallet(double accountBalance, double cartTotal) {
		if(accountBalance - cartTotal >= 0.00) {
			return true;
		}
		
		return false;
	}
}
