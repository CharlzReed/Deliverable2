package EECS3311Deliverable2.frontend;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.example.Item;

public class ReqNewTextbook {
   
    
	public void show(){
        JFrame newFrame = new JFrame("Request New Textbook");
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame.setSize(400, 300);
        newFrame.setLocationRelativeTo(null);
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Request New Textbook Feature", SwingConstants.CENTER);
        panel.add(label,BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {
            newFrame.setVisible(true); // Make the MainMenu frame visible again
            SwingUtilities.getWindowAncestor(panel).dispose(); // Close the parent window
            
            
        });
        
        panel.add(closeButton, BorderLayout.SOUTH);

        newFrame.add(panel);
        newFrame.setVisible(true);
        
        
    }
}
