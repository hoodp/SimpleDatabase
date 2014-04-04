package package1;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**********************************************************************
 * The following class is responsible for initializing the GUI and 
 * setting the JMenuBar.
 * @author Paul Hood
 * @version 12/04/2013
 *********************************************************************/
public class GUISimpleDatabase {

	/******************************************************************
	 * This method starts the program and sets the JMenuBar.
	 *****************************************************************/
	public static void main(String[] args) {
		JMenuBar menuBar;
		JMenu fileMenu;
		JMenuItem menuExit;
		fileMenu = new JMenu("File");
		menuExit = new JMenuItem("Exit");
		menuBar = new JMenuBar();
		fileMenu.add(menuExit);
		menuBar.add(fileMenu);
		SimpleDatabasePanel panel = new SimpleDatabasePanel(menuExit);
		JFrame frame = new JFrame("Simple Database");
		frame.setJMenuBar(menuBar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		frame.setSize(514, 552);
		frame.setVisible(true);
	}
}
