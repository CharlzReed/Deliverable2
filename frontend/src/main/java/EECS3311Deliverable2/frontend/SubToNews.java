package EECS3311Deliverable2.frontend;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class SubToNews {
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
		JButton openbook= new JButton("Subscribe");
		openbook.addActionListener(e ->{
			String selectedbook=(String) textblist.getSelectedValue();
			if (selectedbook != null){
				
			}
		});
		j.add(openbook,BorderLayout.CENTER);
				
		return j;
		
	}
	public List getnewsletter() {
		return null;
		
	}
}
