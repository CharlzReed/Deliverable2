package EECS3311Deliverable2.D2;

import javax.swing.SwingUtilities;

public class Launcher {
	
	public static void main(String [] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			//This code launches the program.
			//if u wanna test these changes click login and then type the following:
			// email: temp@yorku.ca
			// password: 123
			public void run() {
				MainWindow main = new MainWindow();
			}
		});
	}
}
