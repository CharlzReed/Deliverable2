package EECS3311Deliverable2.frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
public class OpenOnBook {
	public JPanel show(JPanel j) {
		String[] a= {"a","b","c","c","c","c","c","c","c","c"};
		DefaultListModel<String> booklist=new DefaultListModel<>();
		for (String e:a) {
			booklist.addElement(e);
		}
		JList textblist=new JList<>(booklist);
		textblist.setPreferredSize(new Dimension(900, 800));
		JScrollPane textbooklist =new JScrollPane((textblist));
		
		textbooklist.setPreferredSize(new Dimension(900,600));
		j.add(textbooklist,BorderLayout.CENTER);
		JFrame book=new JFrame("book");
		JButton openbook= new JButton("Open book");
		openbook.addActionListener(e ->{
			String selectedbook=(String) textblist.getSelectedValue();
			if (selectedbook != null){
				openbookdetails(selectedbook,a);
			}
		});
		j.add(openbook,BorderLayout.CENTER);
				
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
}
