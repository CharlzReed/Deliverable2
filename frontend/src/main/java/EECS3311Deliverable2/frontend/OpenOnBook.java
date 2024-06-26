package EECS3311Deliverable2.frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.example.Item;
import com.example.ItemType;
import com.example.Library;

public class OpenOnBook {
	List<Item> items;

	public OpenOnBook(ArrayList<Item> items) {
		this.items = items;
	}

	public JPanel show(JPanel j) {

		DefaultListModel<String> ownedlist1 = new DefaultListModel<>();
		HashMap<String, Item> grabList = new HashMap<>();
		loadData(ownedlist1, grabList);

		JTextField searchField = new JTextField(50);
		JButton searchButton = new JButton("Search ->");
		JButton resetbutton = new JButton("Reset");
		resetbutton.setPreferredSize(new Dimension(150, 20));
		j.add(searchField);
		j.add(resetbutton);
		j.add(searchButton);
		resetbutton.addActionListener(e -> {
			ownedlist1.clear();
			loadData(ownedlist1, grabList);
		});
		searchButton.setPreferredSize(new Dimension(200, 20));

		searchButton.addActionListener(e -> {
			String searchbarstring = searchField.getText();
			List<Item> searched = Library.getInstance().searchItems(searchbarstring,
					Library.getInstance().getItems().size());
			ownedlist1.clear();
			for (Item item : searched) {
				if (item.getItemType() == ItemType.BOOK && items.contains(item)) {
					ownedlist1.addElement(item.name + " " + "(" + item.itemType + ") ");
				}
			}
		});

		JList ownedlist = new JList<>(ownedlist1);
		ownedlist.setPreferredSize(new Dimension(900, 800));
		JScrollPane booklist = new JScrollPane((ownedlist));
		booklist.setPreferredSize(new Dimension(900, 600));
		j.add(booklist, BorderLayout.CENTER);
		JButton openButton = new JButton("Open Book");
		openButton.addActionListener(e -> {
			String selectedbook = (String) ownedlist.getSelectedValue();
			if (selectedbook != null) {
				openownedbook(grabList, selectedbook);
			}
		});
		j.add(openButton, BorderLayout.CENTER);

		return j;

	}

	private void openownedbook(HashMap<String, Item> items, String key) {
		JFrame openbook = new JFrame(key);
		JPanel contentPanel = new JPanel(new BorderLayout());
		JLabel booklabel = new JLabel("Details for " + key, SwingConstants.CENTER);
		contentPanel.add(booklabel, BorderLayout.CENTER);
		openbook.setLocationRelativeTo(null);
		openbook.setResizable(false);
		openbook.add(contentPanel);
		openbook.setSize(800, 800);
		openbook.setVisible(true);

	}

	private void loadData(DefaultListModel<String> items123, HashMap<String, Item> list) {
		for (Item item : Library.getInstance().getItems()) {
			if (items.contains(item) && (item.getItemType() == (ItemType.BOOK))) {
				String formatted = item.name + " " + "(" + item.itemType + ") ";
				items123.addElement(formatted);
				list.put(formatted, item);

			}

		}
	}
}
