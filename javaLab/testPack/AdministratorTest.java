/*
 * Created on 10 mars 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package testPack;

import junit.framework.TestCase;
import edu.dessif.Administrator;

/**
 * @author 209
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class AdministratorTest extends TestCase {

	public void testIsAdmin() {
		Administrator admin = new Administrator();
		assertTrue(admin.isAdmin());
	}

	public void testIsStudent() {
		Administrator admin = new Administrator();
		assertTrue(!admin.isStudent());
	}

	public void testGetFirstName() {
		Administrator admin = new Administrator();
		admin.setFirstName("J");
		assertEquals("J",admin.getFirstName());
	}

	public void testGetLastName() {
		Administrator admin = new Administrator();
		admin.setLastName("C");
		assertEquals("C",admin.getLastName());
	}

}
