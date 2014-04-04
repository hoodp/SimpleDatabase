package package1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**********************************************************************
 * This class creates a JPanel and is displayed on the JFrame from the
 * GUISimpleDatabase class.
 * @author Paul Hood
 * @version 12/04/2013
 *********************************************************************/
public class SimpleDatabasePanel extends JPanel
implements ActionListener {

	/** JPanel that holds the two panels on the top */
	private JPanel pnlTop;

	/** JPanel that holds the bottom two buttons */
	private JPanel pnlBottom;

	/** Menu item that allows the user to exit */
	private JMenuItem menuExit;

	/** JLabel for the name */
	private JLabel lblName;

	/** JLabel for the gNumber */
	private JLabel lblGNumber;

	/** JLabel for the gpa */
	private JLabel lblGpa;

	/** JLabel displays the total number of students in current db **/
	private JLabel lblTotal;

	/** JTextField for students name */
	private JTextField txtName;

	/** JTextField for students gNumber */
	private JTextField txtGNumber;

	/** JTextField for students gpa */
	private JTextField txtGpa;

	/** JTextArea for displaying the students info */
	private JTextArea txtDisplay;

	/** Scroll pane for the text area */
	private JScrollPane scroll;

	/** JButton for finding a student **/
	private JButton btnFind;

	/** JButton for deleting a student **/
	private JButton btnDelete;

	/** JButton for reversing the linkedList **/
	private JButton btnReverse;

	/** JButton for displaying the linkedlist **/
	private JButton btnDisplay;

	/** JButton for saving the linked list **/
	private JButton btnSave;

	/** JButton for finding duplicates **/
	private JButton btnDuplicate;

	/** JButton for loading a linked list **/
	private JButton btnLoad;

	/** JButton for inserting student into form */
	private JButton btnInsert;

	/** JButton for exiting the form **/
	private JButton btnExit;

	/** JButton for undoing the last operation **/
	private JButton btnUndo;

	/** Used to store the students in the DB **/
	private SimpleDatabase db;

	/******************************************************************
	 * Construtor creates a JPanel and updates the display.
	 * @param menuExit JMenuItem From the main GUI.
	 *****************************************************************/
	public SimpleDatabasePanel(JMenuItem menuExit) {
		db = new SimpleDatabase();
		this.menuExit = menuExit;
		setLayout(new BorderLayout(0, 0));
		setSize(500, 500);

		// setup JPanels
		pnlBottom = new JPanel();
		FlowLayout flBottom = (FlowLayout) pnlBottom.getLayout();
		flBottom.setAlignment(FlowLayout.RIGHT);
		pnlTop = new JPanel();
		pnlTop.setLayout(null);

		// buttons
		btnFind = new JButton("Find");
		btnDelete = new JButton("Delete");
		btnReverse = new JButton("Reverse");
		btnDisplay = new JButton("Display");
		btnSave = new JButton("Save");
		btnDuplicate = new JButton("Duplicate");
		btnLoad = new JButton("Load");
		btnInsert = new JButton("Insert");
		btnExit = new JButton("Exit");
		btnUndo = new JButton("Undo");

		// textfields and labels
		lblName = new JLabel("Name:");
		lblGNumber = new JLabel("G-Number:");
		lblGpa = new JLabel("GPA:");
		lblTotal = new JLabel(displayTotal());
		txtName = new JTextField();
		txtGNumber = new JTextField();
		txtGpa = new JTextField();
		txtDisplay = new JTextArea();
		txtDisplay.setEditable(false);
		scroll = new JScrollPane();
		scroll.setViewportView(txtDisplay);
		scroll.setColumnHeaderView(lblTotal);

		// label and textfield locations
		lblName.setBounds(10, 15, 46, 14);
		lblGNumber.setBounds(10, 49, 52, 14);
		lblGpa.setBounds(10, 83, 46, 14);
		txtName.setBounds(72, 12, 231, 20);
		txtGNumber.setBounds(72, 46, 231, 20);
		txtGpa.setBounds(72, 80, 231, 20);
		scroll.setBounds(10, 147, 485, 309);

		// button locations
		btnInsert.setBounds(313, 11, 89, 23);
		btnDelete.setBounds(405, 11, 89, 23);
		btnDisplay.setBounds(313, 45, 89, 23);
		btnFind.setBounds(405, 45, 89, 23);
		btnDuplicate.setBounds(313, 79, 89, 23);
		btnReverse.setBounds(405, 79, 89, 23);
		btnLoad.setBounds(313, 113, 89, 23);
		btnSave.setBounds(405, 113, 89, 23);
		btnUndo.setLocation(252, 5);

		// action listeners
		btnFind.addActionListener(this);
		btnDelete.addActionListener(this);
		btnReverse.addActionListener(this);
		btnDisplay.addActionListener(this);
		btnSave.addActionListener(this);
		btnDuplicate.addActionListener(this);
		btnLoad.addActionListener(this);
		btnInsert.addActionListener(this);
		btnExit.addActionListener(this);
		btnUndo.addActionListener(this);
		menuExit.addActionListener(this);

		// top panel
		pnlTop.add(lblName);
		pnlTop.add(lblGNumber);
		pnlTop.add(lblGpa);
		pnlTop.add(txtName);
		pnlTop.add(txtGNumber);
		pnlTop.add(txtGpa);
		pnlTop.add(btnInsert);
		pnlTop.add(btnDelete);
		pnlTop.add(btnFind);
		pnlTop.add(btnReverse);
		pnlTop.add(btnDuplicate);
		pnlTop.add(btnDisplay);
		pnlTop.add(btnLoad);
		pnlTop.add(btnSave);
		pnlTop.add(scroll);

		//bottom panel
		pnlBottom.add(btnUndo);
		pnlBottom.add(btnExit);

		// add panels
		add(pnlTop, BorderLayout.CENTER);
		add(pnlBottom, BorderLayout.SOUTH);

		// update the display
		updateDisplay();
	}

	/******************************************************************
	 * Method displays the total number of students in the current db
	 * and updates the buttons.
	 * @return String of the total number of students
	 *****************************************************************/
	public String displayTotal() {
		updateDisplay();
		return "Total Students: " + db.getCount();
	}

	/******************************************************************
	 * Method sets the buttons on or off.
	 *****************************************************************/
	public void updateDisplay() {
		int total = db.getCount();
		if (total == 0) {
			setButtons(false);
		}
		else {
			setButtons(true);
		}

		// able to check for duplicates && reverse
		if (total > 1) {
			btnDuplicate.setEnabled(true);
			btnReverse.setEnabled(true);
		}

		// no duplicates
		else {
			btnDuplicate.setEnabled(false);
			btnReverse.setEnabled(false);
		}
	}

	/******************************************************************
	 * Method controls each of the buttons that can be enabled.
	 * @param enable Sets the buttons
	 *****************************************************************/
	private void setButtons(boolean enable) {
		btnDelete.setEnabled(enable);
		btnFind.setEnabled(enable);
		btnSave.setEnabled(enable);
		btnDisplay.setEnabled(enable);
	}

	/******************************************************************
	 * This method ensures that the textbox's length is greater than 0.
	 * @param field JTextfield to be checked
	 * @return True is length is greater than 0
	 *****************************************************************/
	private boolean checkText(JTextField field) {
		int length = field.getText().trim().length();

		// length of string is greater than 0
		if (length > 0) {
			return true;
		}

		// reset the JTextField
		else {
			setTextField(field);
			return false;
		}
	}

	/******************************************************************
	 * Method checks if the GPA is a double
	 * @param field JTextField data to be checked
	 * @return True if the number is a double
	 *****************************************************************/
	private boolean checkDouble(JTextField field) {
		try {
			double num = Double.parseDouble(field.getText().trim());
			if (num > 4.0) {
				throw new NumberFormatException();
			}
		}

		// GPA not a double
		catch (Exception e) {

			// reset TextField
			setTextField(field);
			return false;
		}
		return true;
	}

	/******************************************************************
	 * Method sets the cursor location to the textfield with the error.
	 * @param field JTextField with an error
	 *****************************************************************/
	public void setTextField(JTextField field) {
		field.setText("");
		field.requestFocus();
	}

	/******************************************************************
	 * Method clears all the textfields to create a new student
	 *****************************************************************/
	private void clear() {
		txtGpa.setText("");
		txtGNumber.setText("");
		txtName.setText("");
		txtName.requestFocus();
	}

	/******************************************************************
	 * Method validates all the textfields and checks the double
	 *****************************************************************/
	public boolean valid() {

		// check for info in all boxes
		if (checkText(txtName) && checkText(txtGNumber) && 
				checkText(txtGpa)) {

			// check for valid double
			if (checkDouble(txtGpa)) {
				return true;
			}

			// not valid double
			else {
				txtDisplay.setText("Error: Enter a valid GPA.");
				return false;
			}
		}

		// error in a JTextField
		else {
			txtDisplay.setText("Error: All fields must be entered.");
			return false;
		}
	}

	/******************************************************************
	 * Method creates a student from the textfields
	 * @return Student to add to database
	 *****************************************************************/
	public String results(Boolean found, String gNumber) {
		String result;
		if (found) {
			result = " was removed from"; 
		}
		else {
			result = " is not in";
		}
		return gNumber + result + " the database.";
	}

	/******************************************************************
	 * Method creates a student from the textfields
	 * @return Student to add to database
	 *****************************************************************/
	private Student createStudent() {
		String name = txtName.getText().trim();
		String gNumber = txtGNumber.getText().trim();
		double gpa = Double.parseDouble(txtGpa.getText().trim());

		// name length is greater than 30 characters
		if (name.length() > 30) {
			name = name.substring(0, 30);
		}

		// g number is greater than 30 characters
		if (gNumber.length() > 30) {
			gNumber = gNumber.substring(0, 30);
		}

		// clears the text fields.
		clear();
		return new Student(name, gNumber, gpa);
	}

	/******************************************************************
	 * Method asks if the user wants to exit
	 * @return True if user wants to close form.
	 *****************************************************************/
	private boolean exit() {
		if (JOptionPane.showConfirmDialog(null,"Are you sure you wish "
				+ "to exit?", "Exit", JOptionPane.YES_NO_OPTION)== 0) {

			// user wishes to exit
			return true;
		}

		// user cancels
		return false;
	}

	/******************************************************************
	 * Method returns a string of the desired text file name.
	 * @param type If "save" save dialog will open, otherwise load.
	 *****************************************************************/
	public String fileName(String type) {

		JFileChooser choose = new JFileChooser();
		int result;
		if (type.equals("save")) {

			// open save dialog
			result = choose.showSaveDialog(null);
		}
		else {

			// open load dialog
			result = choose.showOpenDialog(null);
		}
		if (result == JFileChooser.APPROVE_OPTION) {
			return choose.getSelectedFile().getPath();
		}
		else {
			return null;
		}
	}

	/******************************************************************
	 * This method checks for events and performs certain actions.
	 * @param e Action event that took place
	 *****************************************************************/
	public void actionPerformed(ActionEvent e) {
		JComponent event = (JComponent) e.getSource();
		if ((event == menuExit || event == btnExit) && exit()) {
			System.exit(1);
		}
		else if (event == btnInsert && valid()) {
			db.saveToStack();
			db.insert(createStudent());	
			txtDisplay.setText("Student inserted into the list!");
		}
		else if (event == btnDelete && checkText(txtGNumber)) {
			String number = txtGNumber.getText().trim();
			db.saveToStack();
			if (db.delete(number)) {
				txtDisplay.setText(results(true, number));
				clear();
			}
			else {
				txtDisplay.setText(results(false, number));
			}
		}
		else if (event == btnFind && checkText(txtGNumber)) {
			Student find = db.find(txtGNumber.getText().trim());
			if (find != null) {
				txtDisplay.setText("Student found!\n" + find);
			}
			else {
				txtDisplay.setText("Match not found!");
			}
		}
		else if (event == btnReverse) {
			db.saveToStack();
			db.reverseList();
			txtDisplay.setText("Database reversed!");
		}
		else if (event == btnDisplay) {
			txtDisplay.setText(db.toString());
		}
		else if (event == btnDuplicate) {
			int total = db.getCount();
			db.saveToStack();
			db.removeDuplicates();
			if (total != db.getCount()) {
				txtDisplay.setText("Duplicates removed.");
			}
			else {
				txtDisplay.setText("No duplicates found.");
			}

		}
		else if (event == btnLoad) {
			String fileName = fileName("load");
			if (fileName != null) {
				db.loadDB(fileName);
				txtDisplay.setText("Load successful!");
			}
			else {
				txtDisplay.setText("Load not successful!");
			}
		}
		else if (event == btnSave) {
			String fileName = fileName("save");

			// user did not cancel
			if (fileName != null) {
				db.saveDB(fileName);
				txtDisplay.setText("List saved.");
			}
			else {
				txtDisplay.setText("List was not saved.");
			}
		}
		else if (event == btnUndo) {
			try {
				int stackLength = db.getStack().peek().length;
				if (stackLength == 0) {
					txtDisplay.setText("Cannot undo any further.");
				}
				else {
					txtDisplay.setText("Undo successful!");
				}
				db.undo();
			}	
			catch (Exception error) {
				txtDisplay.setText("Cannot undo!");
			}
		}

		// update information
		lblTotal.setText(displayTotal());
	}
}
