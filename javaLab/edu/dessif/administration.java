/*
 * Created on 3 mars 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package edu.dessif;

/**
 * @author 209
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class administration {

	public static void main(String[] args) {
		Administrator admin = new Administrator();
		Student gge = new Student();
		Student bmz = new Student();
		
		gge.setFirstName("Guilhem");
		gge.setLastName("GP");
		bmz.setFirstName("Benoit");
		bmz.setLastName("M");
		admin.setFirstName("aucun");
		admin.setLastName("Dieu");
		
		info(admin);
		info(gge);
		info(bmz);
		
		
	}
	
	static public void info(User u)
	{
		if(u.isAdmin())
		{
			System.out.println(u.getFirstName()+" "+u.getLastName()+" est un admin.");
		}
		else
		{
			System.out.println( u.getFirstName()+" "+u.getLastName()+" est un étudiant.");
		}
	}
}
