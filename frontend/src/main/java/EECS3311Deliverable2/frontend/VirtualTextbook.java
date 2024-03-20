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
public class VirtualTextbook {
	
		public JPanel show(JPanel j) {
			String[] a= {"a","b","c","c","c","c","c","c","c","c"};
			DefaultListModel<String> booklist=new DefaultListModel<>();
			for (String e:a) {
				booklist.addElement(e);
			}
			JList books=new JList<>(booklist);
			books.setPreferredSize(new Dimension(900, 800));
			JScrollPane listofbooks =new JScrollPane((books));
			listofbooks.setPreferredSize(new Dimension(900,600));
			j.add(listofbooks,BorderLayout.CENTER);
		
			JButton openbook= new JButton("Open book");
			openbook.addActionListener(e ->{
				String selectedbook=(String) books.getSelectedValue();
				if (selectedbook != null){
					opentexttextbookdetails(selectedbook,a);
				}
			});
			j.add(openbook,BorderLayout.CENTER);
					
			return j;
			
		}
		private void opentexttextbookdetails(String selectedbook,String a[]) {
			JFrame textbookdetails=new JFrame(selectedbook);
			JPanel contentPanel= new JPanel(new BorderLayout());
			JLabel booklabel = new JLabel("Details for " + selectedbook,SwingConstants.CENTER);
			contentPanel.add(booklabel,BorderLayout.CENTER);
			textbookdetails.setLocationRelativeTo(null);
	        textbookdetails.setResizable(false);
			textbookdetails.add(contentPanel);
			textbookdetails.setSize(800,800);
			textbookdetails.setVisible(true);
			
		
}}
