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
public class Student extends User {
	
	// Constructors
	public Student(){ super(); }
	public Student(String fname, String lname, int age, String mail)
	{ super(fname,lname,age,mail); }
	
	// Methods	
	public boolean isAdmin(){ return false; }
	public boolean isStudent(){ return true; }
	

}
