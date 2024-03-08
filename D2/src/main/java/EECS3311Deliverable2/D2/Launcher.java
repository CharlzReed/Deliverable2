package EECS3311Deliverable2.D2;

import javax.swing.SwingUtilities;

public class Launcher {
	
	public static void main(String [] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			//This code launches the program.
			public void run() {
				MainWindow main = new MainWindow();
				MainMenu open = new MainMenu();
			}
		});
	}
}
