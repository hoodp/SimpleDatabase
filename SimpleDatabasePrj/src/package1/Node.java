package package1;

/**********************************************************************
 * Node class is responsible for setting each node that is going to be
 * stored in the double linked list.
 * @author Paul Hood
 * @version 12/04/2013
 *********************************************************************/
public class Node {

	/** Holds the students information */
	private Student data;

	/** Node for the next student */
	private Node next;

	/** Node for the previous student */
	private Node previous;

	/******************************************************************
	 * Constructor creates a New Node and sets its previous and next 
	 * values.
	 * @param data Student that is stored in the node.
	 * @param next Current nodes next node in the list.
	 * @param previous Current nodes previous node in the list.
	 *****************************************************************/
	public Node(Student data, Node next, Node previous) {
		this.data = data;
		this.next = next;
		this.previous = previous;
	}

	/******************************************************************
	 * Constructor creates a new node and sets its student property.
	 * @param data Student that is stored in the node.
	 *****************************************************************/
	public Node(Student data) {
		this.data = data;
	}

	/******************************************************************
	 * Method returns the current nodes student toString method.
	 * @return String of the nodes students values.
	 *****************************************************************/
	public String toString() {
		return data.toString();
	}

	/******************************************************************
	 * Method sets the data for the new student
	 * @param data Student data information
	 *****************************************************************/
	public void setData(Student data) {
		this.data = data;
	}

	/******************************************************************
	 * Method returns the Students Information
	 * @return data Student's information
	 *****************************************************************/
	public Student getData() {
		return data;
	}

	/******************************************************************
	 * This method returns the next node in the linked list.
	 * @return Next node in linked list
	 *****************************************************************/
	public Node getNext() {
		return next;
	}

	/******************************************************************
	 * This method sets the next Node.
	 * @param next Next node 
	 *****************************************************************/
	public void setNext(Node next) {
		this.next = next;
	}

	/******************************************************************
	 * This method returns the previous node in the linked list.
	 * @return previous node in linked list
	 *****************************************************************/
	public Node getPrevious() {
		return previous;
	}

	/******************************************************************
	 * This method sets the previous Node.
	 * @param previous Previous node 
	 *****************************************************************/
	public void setPrevious(Node previous) {
		this.previous = previous;
	}
}
