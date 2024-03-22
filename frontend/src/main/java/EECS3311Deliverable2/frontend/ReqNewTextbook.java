package EECS3311Deliverable2.frontend;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import com.example.User;
import com.example.Item;
import com.example.ItemType;
import com.example.Library;
public class ReqNewTextbook {
    private JFrame frame;
    User currentUser;
    public ReqNewTextbook(User currentUser) {
        this.currentUser=currentUser;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Request New Textbook");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setLocationRelativeTo(null);
        HashMap<String,Item> grablist=new HashMap<>();
        DefaultListModel<String>items=new DefaultListModel<>();
        loadData(items, grablist);
        JRadioButton teachingRadioButton = new JRadioButton("Teaching");
        JRadioButton selfImprovingRadioButton = new JRadioButton("Self-improving");
        ButtonGroup group = new ButtonGroup();
        group.add(teachingRadioButton);
        group.add(selfImprovingRadioButton);
        JPanel optionsPanel = new JPanel();
        optionsPanel.add(teachingRadioButton);
        optionsPanel.add(selfImprovingRadioButton);
        JPanel panel = new JPanel();
        JTextField searchBar = createTextField(50);
        JButton resetButton = createButton("Reset Results", 150, 20);
		JButton searchButton = createButton("Search -->", 150, 20);
        resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				items.clear();
				loadData(items, grablist);
			}
		});
        searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchText = searchBar.getText();
				List<Item> results = Library.searchItems(searchText, Library.items.size());

				items.clear();

				for (Item item : results) {
					if(!currentUser.rentedItems.contains(item) &&item.getItemType() == ItemType.TEXTBOOK) {
						items.addElement(item.name + " " + "(" + item.itemType + ") " + "(" + String.format("%.2f", item.cost) + ")");
					}
				}
			}
		});
        JList itemselection=new JList<>(items);
        itemselection.setPreferredSize(new Dimension(900, 800));
        JScrollPane itemsScroll =new JScrollPane(itemselection);
        itemsScroll.setPreferredSize(new Dimension(900, 600));
        JLabel label = new JLabel("Request New Textbook Feature", SwingConstants.CENTER);
        
        JButton requestButton = new JButton("Request");
        requestButton.setEnabled(false);
        ActionListener radioListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (teachingRadioButton.isSelected() || selfImprovingRadioButton.isSelected()) {
                    requestButton.setEnabled(true);
                } else {
                    requestButton.setEnabled(false);
                }
            }
        };
        optionsPanel.add(requestButton);
        teachingRadioButton.addActionListener(radioListener);
        selfImprovingRadioButton.addActionListener(radioListener);
        requestButton.addActionListener(e -> {
            String selectedbook= (String) itemselection.getSelectedValue();
            Item selecteditem = grablist.get(selectedbook);
            Library.requestItem(selecteditem.getname(), selecteditem.getItemType(), selecteditem.getlocationInLibrary(), selecteditem.getcost());
            items.removeElement(selectedbook);
            grablist.remove(selectedbook);
            JOptionPane.showMessageDialog(panel, "Book has been requested", "Book Requested", JOptionPane.INFORMATION_MESSAGE);
			frame.dispose();
			
        });
        panel.add(label);
        panel.add(searchBar,BorderLayout.NORTH);
        panel.add(resetButton,BorderLayout.NORTH);
        panel.add(searchButton,BorderLayout.NORTH);
        panel.add(itemsScroll,BorderLayout.CENTER);
        panel.add(optionsPanel, BorderLayout.SOUTH);
        frame.add(panel);
        frame.setVisible(true);
    }
    private void loadData(DefaultListModel<String>items123,HashMap<String,Item>list){
        for (Item item : Library.items) {
            if (!currentUser.rentedItems.contains(item) && (item.getItemType() == ItemType.TEXTBOOK)) {
                String formatted = item.name + " " + "(" + item.itemType + ") " + "(" + String.format("%.2f", item.cost) + ")";
                items123.addElement(formatted);
                list.put(formatted, item);
            }

        }
    }
    public void show() {
        frame.setVisible(true);
    }
    private JTextField createTextField(int width) {
		JTextField textfield = new JTextField(width);
		return textfield;
	}
    private JButton createButton(String name, int width, int height) {
        JButton button = new JButton(name);
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(width, height));
        return button;
    }
}
