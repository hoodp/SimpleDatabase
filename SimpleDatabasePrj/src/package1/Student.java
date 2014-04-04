package package1;

/**********************************************************************
 * The following class creates a new Student and sets the student's 
 * information.
 * @author Paul Hood
 * @version 12/04/2013
 *********************************************************************/
public class Student implements Comparable<Student> {

	/** String that holds students name */
	protected String name;

	/** String that holds students G number */
	protected String gNumber;

	/** Double that stores students GPA */
	protected double gpa;

	/******************************************************************
	 * Constructor creates a new Student.
	 * @param name String of the students name.
	 * @param gNumber String of the students gNumber.
	 * @param gpa Double of the students gpa.
	 *****************************************************************/
	public Student(String name, String gNumber, double gpa) {
		this.name = name;
		this.gNumber = gNumber;
		this.gpa = gpa;
	}

	/******************************************************************
	 * Method compares two students g numbers. The students other 
	 * information is irrelevant.
	 * @param s Student to be compared
	 * @return 1 if students G-Numbers match, otherwise -1
	 *****************************************************************/
	public int compareTo(Student s) {
		if (gNumber.equals(s.gNumber)) {

			// g numbers match
			return 1;
		}
		else {

			// no match
			return -1;
		}
	}

	/******************************************************************
	 * The following method returns a string of the students 
	 * information that is stored in the list.
	 * @return String of the students information
	 *****************************************************************/
	public String toString() {
		return "Name: " + name + "\nG-Number: " + gNumber + "\nGPA: "
				+ gpa + "\n";
	}
}
