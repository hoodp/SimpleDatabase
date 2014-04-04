package package1;

import java.util.EmptyStackException;

import org.junit.Assert;
import org.junit.Test;

/**********************************************************************
 * The following class is a JUnit test for the SimpleDatabase and 
 * student class.
 * @author Paul Hood
 * @version 12/04/2013
 *********************************************************************/
public class TestDatabase {

	// Creates a new database class and 10 students
	SimpleDatabase db = new SimpleDatabase();
	Student s1 = new Student("Paul Hood", "1", 1.0);
	Student s2 = new Student("Dianne Taylor", "2", 2.0);
	Student s3 = new Student("James Franco", "3", 3.0);
	Student s4 = new Student("Steve Wood", "4", 4.0);
	Student s5 = new Student("Kelly James", "5", 4.0);
	Student s6 = new Student("Jimmy Brown", "6", 3.5);
	Student s7 = new Student("Sean Taylor", "7", 3.23);
	Student s8 = new Student("Paul Wood", "8", 2.22);
	Student s9 = new Student("First Last", "9", 1.78);
	Student s10 = new Student("Test Name", "10", 3.9);

	/******************************************************************
	 * Method tests the count variable in the SimpleDatabase class.
	 *****************************************************************/
	@Test
	public void countTest() {
		db.insert(s1);
		db.insert(s2);
		db.insert(s3);
		Assert.assertEquals(3, db.getCount());
	}

	/******************************************************************
	 * Method tests the count variable in the SimpleDatabase class 
	 * after a delete.
	 *****************************************************************/
	@Test
	public void countAfterDelete() {
		db.insert(s1);
		db.insert(s2);
		db.delete(s1.gNumber);
		Assert.assertEquals(1, db.getCount());
	}

	/******************************************************************
	 * Method tests the count variable in the SimpleDatabase class 
	 * after removing duplicates.
	 *****************************************************************/
	@Test
	public void countAfterDuplicate() {
		s6 = s7 = s1;
		db.insert(s1);
		db.insert(s6);
		db.insert(s7);
		db.removeDuplicates();
		Assert.assertEquals(1, db.getCount());
	}

	/******************************************************************
	 * Method tests SimpleDatabase's insert method by testing the 
	 * gNumbers of the added nodes.
	 *****************************************************************/
	@Test
	public void insertTest() {
		db.insert(s1);
		db.insert(s2);
		db.insert(s3);
		Assert.assertEquals("123", db.gNumberString());
	}

	/******************************************************************
	 * Tests the insert method in the SimpleDatabase class by checking
	 * the top node.
	 *****************************************************************/
	@Test
	public void insertTest2() {
		db.insert(s5);
		db.insert(s4);
		db.insert(s1);
		String top = s5.gNumber;
		Assert.assertEquals("5", top);
	}

	/******************************************************************
	 * Tests the insert method in the SimpleDatabase class by checking
	 * the bottom node.
	 *****************************************************************/
	@Test
	public void insertTest3() {
		db.insert(s2);
		db.insert(s4);
		String bottom = s4.gNumber;
		Assert.assertEquals("4", bottom);
	}

	/******************************************************************
	 * Tests the delete method from the SimpleDatabase class when it 
	 * empty.
	 *****************************************************************/
	@Test
	public void testDelete1() {
		Assert.assertFalse(db.delete("G01202019"));
	}

	/******************************************************************
	 * Tests the delete method from the SimpleDatabase class when it 
	 * it has one item that matches.
	 *****************************************************************/
	@Test
	public void testDelete2() {
		db.insert(s1);
		Assert.assertTrue(db.delete("1"));
	}

	/******************************************************************
	 * Tests the delete method from the SimpleDatabase class when it 
	 * it has one item that does not match.
	 *****************************************************************/
	@Test
	public void testDelete3() {
		db.insert(s1);
		db.insert(s2);
		db.insert(s3);
		db.insert(s4);
		db.insert(s5);
		Assert.assertFalse(db.delete("6"));
	}

	/******************************************************************
	 * Tests the deletion of a node at the top.
	 *****************************************************************/
	@Test
	public void testDelete4() {
		db.insert(s4);
		db.insert(s1);
		db.insert(s3);
		Assert.assertTrue(db.delete("1"));
	}

	/******************************************************************
	 * Tests deletion of node at the bottom of the list.
	 *****************************************************************/
	@Test
	public void testDelete5() {
		db.insert(s3);
		db.insert(s4);
		db.insert(s5);
		Assert.assertTrue(db.delete("5"));
	}

	/******************************************************************
	 * Tests deletion of node in the middle of the list.
	 *****************************************************************/
	@Test
	public void testDelete6() {
		db.insert(s1);
		db.insert(s2);
		db.insert(s3);
		db.insert(s4);
		db.insert(s5);
		Assert.assertTrue(db.delete(s3.gNumber));
	}

	/******************************************************************
	 * Asserts false since the item is not in the list.
	 *****************************************************************/
	@Test
	public void testDelete7() {
		db.insert(s1);
		db.insert(s2);
		db.insert(s3);
		db.insert(s4);
		db.insert(s5);
		Assert.assertFalse(db.delete("12"));
	}

	/******************************************************************
	 * Looks for an item with an empty list.
	 *****************************************************************/
	@Test
	public void testFind1() {
		Assert.assertNull(db.find("3"));
	}

	/******************************************************************
	 * Looks for student with one item in list with a match
	 *****************************************************************/
	@Test
	public void testFind2() {
		Student student = new Student("Paul Hood", "G01202019", 4.0);
		db.insert(student);
		Assert.assertSame(student, db.find("G01202019"));
	}

	/******************************************************************
	 * Looks for student with one item in list and no match.
	 *****************************************************************/
	@Test
	public void testFind3() {
		Student student = new Student("First Last", "G66875246", 3.0);
		db.insert(s1);
		Assert.assertNull(db.find(student.gNumber));
	}

	/******************************************************************
	 * Returns the Student that is on top of the list.
	 *****************************************************************/
	@Test
	public void testFind4() {
		db.insert(s5);
		db.insert(s4);
		db.insert(s3);
		Assert.assertSame(s5, db.find("5"));
	}

	/******************************************************************
	 * Returns the Student that is on the bottom of the list.
	 *****************************************************************/
	@Test
	public void testFind5() {
		db.insert(s5);
		db.insert(s4);
		db.insert(s3);
		Student bottom = new Student("Jimmy", "G120", 2.0);
		db.insert(bottom);
		Assert.assertSame(bottom, db.find("G120"));
	}

	/******************************************************************
	 * Looks for student in the middle of the list.
	 *****************************************************************/
	@Test
	public void testFind6() {
		db.insert(s1);
		db.insert(s2);
		db.insert(s3);
		db.insert(s4);
		db.insert(s5);
		Assert.assertSame(s3, db.find("3"));
	}

	/******************************************************************
	 * Looks for student that is not in the list.
	 *****************************************************************/
	@Test
	public void testFind7() {
		db.insert(s5);
		db.insert(s4);
		db.insert(s3);
		db.insert(s2);
		db.insert(s1);
		Assert.assertNull(db.find("0"));
	}

	/******************************************************************
	 * Adds 5 nodes and reverses the list.
	 *****************************************************************/
	@Test
	public void testReverse1() {
		db.insert(s1);
		db.insert(s3);
		db.insert(s5);
		db.insert(s7);
		db.insert(s9);
		db.reverseList();
		Assert.assertEquals("97531", db.gNumberString());
	}

	/******************************************************************
	 * Adds 5 nodes and reverses the list the list twice.
	 *****************************************************************/
	@Test
	public void testReverse2() {
		db.insert(s1);
		db.insert(s3);
		db.insert(s5);
		db.insert(s7);
		db.insert(s9);
		db.reverseList();
		db.reverseList();
		Assert.assertEquals("13579", db.gNumberString());
	}

	/******************************************************************
	 * Adds three nodes, reverses list then adds another node.
	 *****************************************************************/
	@Test
	public void testReverse3() {
		db.insert(s8);
		db.insert(s6);
		db.insert(s4);
		db.reverseList();
		db.insert(s10);
		Assert.assertEquals("46810", db.gNumberString());
	}

	/******************************************************************
	 * Tests reverse after deleting nodes.
	 *****************************************************************/
	@Test
	public void testReverse4() {
		db.insert(s4);
		db.insert(s8);
		db.delete("4");
		db.delete("8");
		db.reverseList();
		Assert.assertEquals("", db.gNumberString());
	}

	/******************************************************************
	 * Tests the remove duplicates method with no duplicates in the 
	 * list.
	 *****************************************************************/
	@Test
	public void testRemoveDuplicates1() {
		db.insert(s10);
		db.insert(s5);
		db.insert(s3);
		db.removeDuplicates();
		Assert.assertEquals(3, db.getCount());
	}

	/******************************************************************
	 * Tests the remove duplicates method with 1 duplicate in the 
	 * list.
	 *****************************************************************/
	@Test
	public void testRemoveDuplicates2() {
		Student copy = s10;
		db.insert(copy);
		db.insert(s10);
		db.insert(s5);
		db.insert(s3);
		db.insert(s1);
		db.removeDuplicates();
		Assert.assertEquals(4, db.getCount());
	}

	/******************************************************************
	 * Tests the remove duplicates method with multiple duplicates.
	 *****************************************************************/
	@Test
	public void testRemoveDuplicates3() {
		s2 = s1;
		s4 = s3;
		s6 = s5;
		db.insert(s1);
		db.insert(s2);
		db.insert(s3);
		db.insert(s4);
		db.insert(s5);
		db.insert(s6);
		db.removeDuplicates();
		Assert.assertEquals(3, db.getCount());
	}

	/******************************************************************
	 * Tests the remove duplicates with multiple of same item.
	 *****************************************************************/
	@Test
	public void testRemoveDuplicates4() {
		Student copy = new Student("Paul Hood", "G01202019", 3.9);
		s1 = s2 = s3 = s4 = s5 = copy;
		db.insert(copy);
		db.insert(s1);
		db.insert(s2);
		db.insert(s3);
		db.insert(s4);
		db.insert(s5);
		db.removeDuplicates();
		Assert.assertEquals(1, db.getCount());
	}

	/******************************************************************
	 * Tests removeDuplicates with random number of matches.
	 *****************************************************************/
	@Test
	public void testRemoveDuplicates5() {
		s5 = s6 = s7 = s1;
		s8 = s9 = s2;
		s3 = s4;
		db.insert(s1);
		db.insert(s2);
		db.insert(s3);
		db.insert(s4);
		db.insert(s5);
		db.insert(s6);
		db.insert(s7);
		db.insert(s8);
		db.insert(s9);
		db.insert(s10);
		db.removeDuplicates();
		Assert.assertEquals(4, db.getCount());
	}

	/******************************************************************
	 * Tests remove duplicates after deleting an item.
	 *****************************************************************/
	@Test
	public void testRemoveDuplicates6() {
		db.insert(s1);
		db.insert(s1);
		db.insert(s1);
		db.insert(s2);
		db.insert(s2);
		db.insert(s3);
		db.delete(s1.gNumber);
		db.removeDuplicates();
		Assert.assertEquals("123", db.gNumberString());
	}

	/******************************************************************
	 * Tests remove duplicates after deleting and reversing the list.
	 *****************************************************************/
	@Test
	public void testRemoveDuplicates7() {
		db.insert(s10);
		db.insert(s10);
		db.insert(s3);
		db.insert(s3);
		db.insert(s4);
		db.delete(s4.gNumber);
		db.reverseList();
		db.removeDuplicates();
		db.removeDuplicates();
		Assert.assertEquals("310", db.gNumberString());
	}

	/******************************************************************
	 * Method tests the saveDB method in the SimpleDatabase method.
	 *****************************************************************/
	@Test
	public void testSave1() {
		String path = "testSave/save1.txt";
		db.insert(s1);
		db.insert(s5);
		db.saveDB(path);
		SimpleDatabase db1 = new SimpleDatabase();
		db1.loadDB(path);
		Assert.assertEquals(2, db1.getCount());
	}

	/******************************************************************
	 * Method tests the saveDB method in the SimpleDatabase method by 
	 * loading a student in and using the Student classes compareTo
	 * method.
	 *****************************************************************/
	@Test
	public void testSave2() {
		String path = "testSave/save2.txt";
		Student saveStu = new Student("Paul Hood", "G01202019", 3.99);
		db.insert(s1);
		db.insert(s2);
		db.insert(saveStu);
		db.insert(s3);
		db.insert(s4);
		db.saveDB(path);
		SimpleDatabase db1 = new SimpleDatabase();
		db1.loadDB(path);
		Student loadStu = db1.find("G01202019");
		Assert.assertEquals(1, saveStu.compareTo(loadStu));
	}

	/******************************************************************
	 * Method tests the load feature by inserting 3 nodes and saving
	 * them. It then loads the database and checks each student.
	 *****************************************************************/
	@Test
	public void testLoad1() {
		String path = "testLoad/load1.txt";
		db.insert(s1);
		db.insert(s2);
		db.insert(s3);
		db.saveDB(path);
		SimpleDatabase db1 = new SimpleDatabase();
		db1.loadDB(path);

		// loop goes through each student, since their gNumbers are in
		// order, they should match i.
		for (int i = 1; i <= db1.getCount(); i++) {
			Assert.assertEquals("" + i, db.find("" + i).gNumber);
		}
	}

	/******************************************************************
	 * Method tests the loadDB method by creating three new students,
	 * adding them to a list then loading them again. It compares the
	 * string to make sure they are the same.
	 *****************************************************************/
	@Test
	public void testLoad2() {
		String path = "testLoad/load2.txt";
		Student new1 = new Student("Name1", "G01202019", 3.55);
		Student new2 = new Student("Name2", "G54892665", 2.05);
		Student new3 = new Student("Name3", "G54601027", 1.79);
		db.insert(new1);
		db.insert(new2);
		db.insert(new3);
		db.saveDB(path);
		SimpleDatabase db1 = new SimpleDatabase();
		db1.loadDB(path);
		String original = db.gNumberString();
		String load = db1.gNumberString();
		Assert.assertTrue(original.equals(load));
	}

	/******************************************************************
	 * Method tests the SimpleDatabases toString method before anything
	 * is added to the list.
	 *****************************************************************/
	@Test
	public void testToString1() {
		Assert.assertEquals("", db.toString());
	}

	/******************************************************************
	 * Method tests the SimpleDatabases toString method after 1 
	 * addition.
	 *****************************************************************/
	@Test
	public void testToString2() {
		db.insert(new Student("1", "2", 3.0));
		String testString = "Name: 1\nG-Number: 2\nGPA: 3.0\n\n";
		Assert.assertTrue(testString.equals(db.toString()));
	}

	/******************************************************************
	 * Method attempts to create a new Node[] from a empty stack.
	 *****************************************************************/
	@Test (expected = EmptyStackException.class)
	public void testStack1() {
		Node[] testArray = db.getStack().pop();
	}

	/******************************************************************
	 * Method tests the Stack in the SimpleDatabase class after a node
	 * is inserted.
	 *****************************************************************/
	@Test
	public void testStack2() {
		db.insert(new Student("Student","G01222222", 3.0));
		db.saveToStack();
		Assert.assertTrue(db.getStack().size() == 1);
	}

	/******************************************************************
	 * Method tests the Stack in the SimpleDatabase class after a node
	 * is inserted and removed.
	 *****************************************************************/
	@Test
	public void testStack3() {
		db.insert(new Student("Student","G01222222", 3.0));
		db.saveToStack();
		db.delete("G01222222");
		db.saveToStack();
		Assert.assertTrue(db.getStack().size() == 2);
	}

	/******************************************************************
	 * Method inserts two of the same nodes, deletes the duplicates 
	 * then undos the stack.
	 *****************************************************************/
	@Test
	public void testStack4() {
		db.insert(s1);
		db.saveToStack();
		db.insert(s1);
		db.saveToStack();
		db.removeDuplicates();
		db.saveToStack();
		db.undo();
		Assert.assertEquals(1, db.getCount());
	}

	/******************************************************************
	 * Tests the undo operation in the Simple Database class.
	 *****************************************************************/
	@Test
	public void testUndo1() {
		db.insert(s1);
		db.saveToStack();
		db.undo();
		Assert.assertTrue(db.getCount() == 1);
	}

	/******************************************************************
	 * Tests the undo operation after reverseList is called.
	 *****************************************************************/
	@Test
	public void testUndo2() {
		db.insert(s1);
		db.saveToStack();
		db.insert(s2);
		db.saveToStack();
		db.insert(s3);
		db.saveToStack();
		db.reverseList();
		db.undo();
		Assert.assertEquals("123", db.gNumberString());
	}

	/******************************************************************
	 * Tests the undo operation after delete is called multiple times.
	 *****************************************************************/
	@Test
	public void testUndo3() {
		db.insert(s8);
		db.saveToStack();
		db.insert(s6);
		db.saveToStack();
		db.insert(s2);
		db.saveToStack();
		db.delete("8");
		db.saveToStack();
		db.delete("6");
		db.saveToStack();
		db.delete("2");
		db.saveToStack();
		db.undo();
		db.undo();
		db.undo();
		db.undo();
		Assert.assertEquals("862", db.gNumberString());
	}

	/******************************************************************
	 * Tests creating new Students.
	 *****************************************************************/
	@Test
	public void testStudent1() {
		Student student = new Student("Paul Hood", "G01202019", 4.0);
		Assert.assertTrue(student.name.equals("Paul Hood"));
		Assert.assertTrue(student.gNumber.equals("G01202019"));
		Assert.assertTrue(student.gpa == 4.0);
	}

	/******************************************************************
	 * Tests the students class compareTo method.
	 *****************************************************************/
	@Test
	public void testStudent2() {
		s2 = s1;
		Assert.assertTrue(s1.compareTo(s2) == 1);
	}

	/******************************************************************
	 * Tests the students class compareTo method.
	 *****************************************************************/
	@Test
	public void testStudent3() {
		Assert.assertTrue(s1.compareTo(s2) == -1);
	}

	/******************************************************************
	 * Tests the students class compareTo method.
	 *****************************************************************/
	@Test
	public void testStudent4() {
		Assert.assertFalse(s1.compareTo(s2) == 1);
	}

	/******************************************************************
	 * Tests the students class toString method, expecting them nto to 
	 * match.
	 *****************************************************************/
	@Test
	public void testStudent5() {
		Assert.assertFalse(s1.equals(s10));
	}

	/******************************************************************
	 * Tests the students class toString method, expecting them to 
	 * match.
	 *****************************************************************/
	@Test
	public void testStudent6() {
		s2 = s8;
		Assert.assertTrue(s2.equals(s8));
	}

	/******************************************************************
	 * Tests the creation of a new node.
	 *****************************************************************/
	@Test
	public void newNodeTest1() {
		Node newNode = new Node(s1);
		Assert.assertNull(newNode.getNext());
	}

	/******************************************************************
	 * Tests the creation of a new node.
	 *****************************************************************/
	@Test
	public void newNodeTest2() {
		Node newNode = new Node(s6);
		Assert.assertNull(newNode.getPrevious());
	}

	/******************************************************************
	 * Tests setting the data of a new Node.
	 *****************************************************************/
	@Test
	public void newNodeTest3() {
		Student student = new Student("Paul", "G01202019", 3.9);
		Node node = new Node(student);
		node.setData(s4);
		Assert.assertFalse(s4.compareTo(student) == 1);
	}
}
