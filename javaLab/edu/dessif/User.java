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
public abstract class User {
	// Attributes
	private String sFirstName;
	private String sLastName;
	private int iAge;
	private String sEmail;
	
	// Abstract methods
	abstract boolean isAdmin();
	abstract boolean isStudent();
		
	//Constructors
	public User(){}
	public User(String fName, String lName, int age, String mail)
	{
		sFirstName = fName;
		sLastName = lName;
		iAge = age;
		sEmail = mail;
	}
	
	// Setters
	public void setFirstName(String name) { sFirstName = name; }
	public void setLastName(String name) { sLastName = name; }
	public void setAge(int age) { iAge = age; }
	public void setEmail(String mail) { sEmail = mail; }
	
	// Getters
	public String getFirstName() { return sFirstName;}
	public String getLastName() { return sLastName; }
	public int getAge() { return iAge; }
	public String getEmail() { return sEmail; }
}
