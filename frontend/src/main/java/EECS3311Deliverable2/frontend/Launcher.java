package EECS3311Deliverable2.frontend;

import javax.swing.SwingUtilities;

public class Launcher {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			// This code launches the program.
			// if u wanna test the changes go to user.csv and log in with an appropriate user. 
			public void run() {
				MainWindow main = new MainWindow();
			}
		});
	}
}
