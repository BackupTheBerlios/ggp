/*
 * Created on 17 mars 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package edu.dessif;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * @author gge
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class CreateStudentTable {

	public static void main(String[] args)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/m2glr",
						"root",
						"");
			

			Statement stmt = con.createStatement();

			stmt.executeUpdate("INSERT INTO student VALUES ('toto','tutu')");

			ResultSet rs = stmt.executeQuery("SELECT * FROM student");

			while(rs.next())

			{

				int x = rs.getInt("id");

				String name = rs.getString("nom");

				String fname = rs.getString("prenom");

				System.out.println(x+" "+name+" "+fname); 

			}

			
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
	}
}
