/*
 * Created on 10 mars 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package testPack;

import junit.framework.TestCase;
import edu.dessif.Student;

/**
 * @author 209
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class StudentTest extends TestCase {

	public void testIsAdmin() {
		Student stu = new Student();
		assertTrue(!stu.isAdmin());
	}

	public void testIsStudent() {
		Student stu = new Student();
		assertTrue(stu.isStudent());
	}

	public void testGetFirstName() {
		Student stu = new Student();
		stu.setFirstName("g");
		assertEquals("g",stu.getFirstName());		
	}

	public void testGetLastName() {
		Student stu = new Student();
		stu.setLastName("gp");
		assertEquals("gp",stu.getLastName());
	}

}
