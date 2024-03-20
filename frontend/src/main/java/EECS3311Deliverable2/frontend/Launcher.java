package EECS3311Deliverable2.frontend;

import javax.swing.SwingUtilities;

import com.example.DataManager;

public class Launcher {

	public static void main(String[] args) {
		DataManager dataManager = DataManager.getInstance();
		dataManager.loadData();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			// This code launches the program.
			// if u wanna test the changes go to user.csv and log in with an appropriate
			// user.
			public void run() {
				MainWindow main = new MainWindow();
			}
		});
	}
}
