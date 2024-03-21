package EECS3311Deliverable2.frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class RentPhysItem {
	public JPanel show(JPanel j) {
		String[] a= {"a","b","c","c","c","c","c","c","c","c"};
		DefaultListModel<String> itemList=new DefaultListModel<>();
		
		for (String e:a) {
			itemList.addElement(e);
		}
		
		JTextField searchBar = createTextField(50);
		JButton searchButton = createButton("Search -->", 200, 20);
		
		j.add(searchBar);
		j.add(searchButton);
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchText = searchBar.getText();
				ArrayList<String> matchedItems = new ArrayList<>();
				for(String item : a) {
					if(item.contains(searchText)) {
						matchedItems.add(item);
					}
				}
				
				itemList.clear();
				
				for(String item : matchedItems) {
					itemList.addElement(item);
				}
			}
		});
		
		JList textblist=new JList<>(itemList);
		textblist.setPreferredSize(new Dimension(900, 800));
		JScrollPane textbooklist =new JScrollPane(textblist);
		
		textbooklist.setPreferredSize(new Dimension(900,600));
		j.add(textbooklist,BorderLayout.CENTER);
		JFrame item =new JFrame("item");
		JButton itemDetails = createButton("Get Item Details", 200, 50);
		JButton addCart = createButton("Add To Cart", 200, 50);
		
		itemDetails.addActionListener(e ->{
			String selectedbook=(String) textblist.getSelectedValue();
			if (selectedbook != null){
				openbookdetails(selectedbook,a);
			}
		});
		
		addCart.addActionListener(e ->{
		});
		
		j.add(itemDetails,BorderLayout.CENTER);
		j.add(addCart,BorderLayout.CENTER);
				
		return j;
		
	}
	private void openbookdetails(String selectedbook,String a[]) {
		JFrame bookdetails=new JFrame(selectedbook);
		JPanel contentPanel= new JPanel(new BorderLayout());
		JLabel booklabel = new JLabel("Details for " + selectedbook,SwingConstants.CENTER);
		contentPanel.add(booklabel,BorderLayout.CENTER);
		bookdetails.setLocationRelativeTo(null);
        bookdetails.setResizable(false);
		bookdetails.add(contentPanel);
		bookdetails.setSize(800,800);
		bookdetails.setVisible(true);
		
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
	
}
