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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.example.Item;
import com.example.ItemType;
import com.example.Library;
import com.example.User;

public class SubToNews {
    List<Item> currentnews = new ArrayList<>();
    List<Item> newsubs = new ArrayList<>();
    User user;

    public SubToNews(User user) {
        this.currentnews = user.rentedItems;
        this.user = user;
    }

    public JPanel show(JPanel j) {
        DefaultListModel<String> ownedlist1 = new DefaultListModel<>();
        DefaultListModel<String> booklist = new DefaultListModel<>();
        HashMap<String, Item> grabList = new HashMap<>();
        HashMap<String, Item> grabListowned = new HashMap<>();
        loadData(booklist, grabList, ownedlist1, grabListowned);

        JTextField searchField = new JTextField(50);
        JButton searchButton = new JButton("Search ->");
        searchButton.setPreferredSize(new Dimension(200, 20));
        JButton resetButton = new JButton("Reset Results");
        resetButton.setPreferredSize(new Dimension(150, 20));
        j.add(searchField, BorderLayout.NORTH);
        j.add(resetButton);
        j.add(searchButton, BorderLayout.NORTH);

        resetButton.addActionListener(e -> {
            ownedlist1.clear();
            booklist.clear();
            loadData(booklist, grabList, ownedlist1, grabListowned);
        });
        JList unowned = new JList<>(booklist);
        JList owned = new JList<>(ownedlist1);
        unowned.setPreferredSize(new Dimension(900, 400));
        JScrollPane textbooklist = new JScrollPane((unowned));
        textbooklist.setPreferredSize(new Dimension(900, 300));
        j.add(textbooklist, BorderLayout.CENTER);
        owned.setPreferredSize(new Dimension(900, 400));
        JScrollPane ownedlist = new JScrollPane((owned));
        ownedlist.setPreferredSize(new Dimension(900, 300));
        j.add(ownedlist, BorderLayout.CENTER);

        searchButton.setPreferredSize(new Dimension(150, 20));
        searchButton.addActionListener(e -> {
            String searchbarstring = searchField.getText();
            List<Item> searched = Library.searchItems(searchbarstring, Library.items.size());
            booklist.clear();
            ownedlist1.clear();
            for (Item item : searched) {
                if (item.getItemType() == ItemType.SUBSCRIPTION && !currentnews.contains(item)) {
                    booklist.addElement(item.name + " " + "(" + item.itemType + ") " + "("
                            + String.format("%.2f", item.cost) + ")");

                } else if (item.getItemType() == ItemType.SUBSCRIPTION && currentnews.contains(item)) {
                    ownedlist1.addElement(item.name + " " + "(" + item.itemType + ") ");
                }
            }
        });
        JButton Subscribe = new JButton("Subscribe");
        Subscribe.addActionListener(e -> {
            String selecteditem = (String) unowned.getSelectedValue();
            additemtocart(j, grabList, selecteditem);
        });
        j.add(Subscribe, BorderLayout.SOUTH);

        JButton unsubscribe = new JButton("UnSubscribe");
        unsubscribe.addActionListener(e -> {
            String selectedbook = (String) owned.getSelectedValue();
            Item selecteditem = grabListowned.get(selectedbook);
            user.rentedItems.remove(selecteditem);
            currentnews.remove(selecteditem);
            ownedlist1.removeElement(selectedbook);
            booklist.addElement(selectedbook);

        });
        j.add(unsubscribe, BorderLayout.SOUTH);
        JButton opennewsletterbutton = new JButton("Open book");
        opennewsletterbutton.addActionListener(e -> {
            String selectedbook = (String) owned.getSelectedValue();
            if (selectedbook != null) {
                if (ownedlist1.contains(selectedbook)) {
                    opennewsletters(grabListowned, selectedbook);
                }

            }
        });
        j.add(opennewsletterbutton, BorderLayout.CENTER);

        return j;
    }

    public List<Item> getnewsletter() {
        return newsubs;
    }

    private void opennewsletters(HashMap<String, Item> items, String key) {
        JFrame newsletter = new JFrame(key);
        JPanel contentPanel = new JPanel(new BorderLayout());
        JLabel booklabel = new JLabel("Details for " + key, SwingConstants.CENTER);
        contentPanel.add(booklabel, BorderLayout.CENTER);
        newsletter.setLocationRelativeTo(null);
        newsletter.setResizable(false);
        newsletter.add(contentPanel);
        newsletter.setSize(800, 800);
        newsletter.setVisible(true);

    }

    private void loadData(DefaultListModel<String> items123, HashMap<String, Item> list,
            DefaultListModel<String> itemsowned, HashMap<String, Item> ownedlist) {
        for (Item item : Library.items) {
            if (!currentnews.contains(item) && (item.getItemType() == ItemType.SUBSCRIPTION)) {
                String formatted = item.name + " " + "(" + item.itemType + ") " + "(" + String.format("%.2f", item.cost)
                        + ")";
                items123.addElement(formatted);
                list.put(formatted, item);
            }
            if (currentnews.contains(item) && (item.getItemType() == (ItemType.SUBSCRIPTION))) {
                String formatted = item.name + " " + "(" + item.itemType + ") " + "(" + String.format("%.2f", item.cost)
                        + ")";
                itemsowned.addElement(formatted);
                ownedlist.put(formatted, item);

            }

        }
    }

    private void additemtocart(JPanel j, HashMap<String, Item> item, String key) {
        boolean canrent = this.user.rentItem(item.get(key));
        if (canrent == true) {
            this.user.addToCart(item.get(key));
            JOptionPane.showMessageDialog(j, "Item added to cart.", "Item Added", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(j, "Reason: " + grabDenyReason(this.user.rentalDenied),
                    "Item Cannot Be Added", JOptionPane.OK_OPTION);
        }
        System.out.println(this.user.userCart + " " + this.user.cartTotal);
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
