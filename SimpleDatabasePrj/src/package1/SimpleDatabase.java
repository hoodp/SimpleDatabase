package package1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import javax.swing.JOptionPane;

/**********************************************************************
 * The following class implements the iSimpleDatabase class and stores
 * information in a doubly linked list.
 * @author Paul Hood
 * @version 12/04/2013
 *********************************************************************/
public class SimpleDatabase implements iSimpleDatabase {

	/** Integer that keeps track of the size of the database **/
	private int count;

	/** Top node in the linked list */
	private Node top;

	/** Last node in the linked list */
	private Node tail;

	/** Stack that stores operations for undo **/
	private Stack<Node[]> stack;

	/******************************************************************
	 * Constructor that sets the top and the tail node to null.
	 *****************************************************************/
	public SimpleDatabase() {
		top = tail = null;
		count = 0;
		stack = new Stack<Node[]>();
	}

	/******************************************************************
	 * Method that returns the total number of items in the list.
	 * @return int of total items in the list.
	 *****************************************************************/
	public int getCount() {
		return count;
	}

	/******************************************************************
	 * Method returns the current stack.
	 * @return Stack of saved Node[]
	 *****************************************************************/
	public Stack<Node[]> getStack() {
		return stack;
	}

	/******************************************************************
	 * Method inserts a student in the bottom of the list.
	 * @param student Student to be added.
	 *****************************************************************/
	public void insert(Student student) {
		Node newNode = new Node(student);

		// list is empty
		if (top == null) {
			top = newNode;
		}

		// insert at bottom
		else {
			tail.setNext(newNode);
			newNode.setPrevious(tail);
		}

		// set tail to the new node
		tail = newNode;
		count++;
	}

	/******************************************************************
	 * Method deletes a student from the list.
	 * @param gNumber String of the students g Number.
	 * @return True if the student was deleted.
	 *****************************************************************/
	public boolean delete(String gNumber) {

		// no nodes
		if (top == null) {
			return false;
		}

		// 1 node and gNumbers match
		else if (top == tail && (top.getData().gNumber.equals(
				gNumber))) {
			top = tail = null;
			count = 0;
			return true;
		}

		// 1 node and no match
		else if (top == tail && !top.getData().gNumber.equals(
				gNumber)) {
			return false;
		}

		// checks match for top node
		else if (top.getData().gNumber.equals(gNumber)) {
			top = top.getNext();
			top.setPrevious(null);
			count--;
			return true;
		}

		// checks match for bottom node
		else if (tail.getData().gNumber.equals(gNumber)) {
			tail = tail.getPrevious();
			tail.setNext(null);
			count--;
			return true;
		}

		// searches for match in the middle
		else {
			Node current = top;

			// stops at the tail because the tail is already checked
			while (current.getNext() != tail) {
				String findGNum = current.getNext().getData().gNumber;

				// match found
				if (findGNum.equals(gNumber)) {
					current.setNext(current.getNext().getNext());
					current.getNext().setPrevious(current);
					count--;
					return true;
				}
				current = current.getNext();
			}
		}

		// no match found
		return false;
	}

	/******************************************************************
	 * Responsible for searching for a student in the linked list.
	 * @param gNumber Student that is being searched for.
	 * @return The student if present in the database, otherwise null.
	 *****************************************************************/
	public Student find(String gNumber) {

		// no nodes to search for
		if (top == null) {
			return null;
		}

		// 1 node
		else if (top == tail) {

			// match
			if (top.getData().gNumber.equals(gNumber)) {
				return top.getData();
			}

			// no match
			else {
				return null;
			}
		}

		// match on top of linked list
		else if (top.getData().gNumber.equals(gNumber)) {
			return top.getData();
		}

		// match on bottom of linked list
		else if (tail.getData().gNumber.equals(gNumber)) {
			return tail.getData();
		}

		// search for a match in the middle
		else {
			Node current = top;

			// stops at tail because tail is already checked
			while (current.getNext() != tail) {
				if (current.getNext().getData().gNumber.equals(
						gNumber)) {
					return current.getNext().getData();
				}
				current = current.getNext();
			}
		}

		// no match
		return null;
	}

	/******************************************************************
	 * This method reverses the linked list.
	 *****************************************************************/
	public void reverseList() {
		if (count > 1) {
			Node first = top;

			// set the top equal to the tail
			top = tail;

			// set the tail equal to the top
			tail = first;
			Node second = top;
			while (second != null) {
				first = second.getNext();
				second.setNext(second.getPrevious());
				second.setPrevious(first);
				second = second.getNext();
			}
		}
	}

	/******************************************************************
	 * Searches for duplicates in the list. If duplicates are present
	 * it removes all but one.
	 *****************************************************************/
	public void removeDuplicates() {
		Node current = top;

		// ArrayList stores Students that are not a duplicate
		ArrayList<Student> students = new ArrayList<Student>();
		while (current != null) {
			Student currStudent = current.getData();

			// boolean if match is found
			boolean match = false;
			if (students.size() > 0) {

				// goes through the list looking for match
				for (int i = 0; i < students.size(); i++) {
					if (currStudent.compareTo(students.get(i)) == 1) {
						delete(currStudent.gNumber);
						match = true;

						// stops searching through arraylist
						break;
					}
				}	
			}

			// if no match found add to array list
			if (!match) {
				students.add(currStudent);
			}
			current = current.getNext();
		}
	}

	/******************************************************************
	 * Saves the current linked list to a database.
	 * @param fileName String of the file name.
	 *****************************************************************/
	public void saveDB(String fileName) {
		try {
			PrintWriter write = new PrintWriter(new BufferedWriter(
					new FileWriter(new File(fileName))));
			Node current = top;
			while (current != null) {
				Student info = current.getData();
				write.println(info.name);
				write.println(info.gNumber);
				write.println(info.gpa);
				current = current.getNext();
			}
			write.close();
			stack.clear();
		}

		// no data to save
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "No data available.");
		}

		// user closes the windows or error occurs
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No File Saved.");
		}
	}

	/******************************************************************
	 * Loads the desired linked list from the file chooser.
	 * @param fileName String of the file name.
	 *****************************************************************/
	public void loadDB(String fileName) {

		// reset count
		count = 0;
		top = tail = null;
		try {
			Scanner in = new Scanner(new File(fileName));
			while (in.hasNext()) {
				String name = in.nextLine();
				String gNumber = in.nextLine().trim();
				double gpa = Double.parseDouble(in.nextLine());
				insert(new Student(name, gNumber, gpa));
			}
			in.close();
			stack.clear();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: Database not"
					+ " loaded");
		}
	}

	/******************************************************************
	 * Method creates a string of all the students and their 
	 * information.
	 * @return String to be displayed on the GUI.
	 *****************************************************************/
	public String toString() {
		String display = "";
		Node current = top;
		while (current != null) {
			display += current.getData() + "\n";
			current = current.getNext();
		}
		return display;
	}

	/******************************************************************
	 * Method is responsible for undoing any action that takes place
	 * on the gui.
	 *****************************************************************/
	public void undo() {
		if (stack.size() > 0
				) {
			count = 0;
			top = tail = null;

			// get nodes from the top of the stack
			Node[] nodeArray = stack.pop();

			// insert each node into the position they were previously
			for (int i = 0; i < nodeArray.length; i++) {
				insert(nodeArray[i].getData());
			}
		}
	}

	/******************************************************************
	 * Method saves each node to a stack after an operation so in the
	 * undo operation can perform.
	 *****************************************************************/
	public void saveToStack() {

		// set array length to count
		Node[] nodeArray = new Node[count];
		Node current = top;

		// insert each node into array
		for (int i = 0; i < count; i++) {
			nodeArray[i] = current;
			current = current.getNext();
		}

		// push onto top of stack
		stack.push(nodeArray);
	}

	/******************************************************************
	 * Method returns a string of G-Numbers. Mainly used for testing.
	 *****************************************************************/
	public String gNumberString() {
		String line = "";
		Node current = top;
		while (current != null) {
			line += current.getData().gNumber;
			current = current.getNext();
		}
		return line;
	}
}
