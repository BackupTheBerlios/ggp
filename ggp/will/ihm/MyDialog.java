/*
 * Created on 3 mars 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package ihm;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controleur.MyTableModel;


/**
 * @author 210
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MyDialog extends JDialog{
	
		public MyDialog(){
			creerContenu();
			pack();
			setVisible(true);
		}
		
		public MyDialog(JFrame parentFrame, boolean isModal){
			creerContenu();
			pack();
			setVisible(true);
		}
		
		public void creerContenu(){
			getContentPane().setLayout(new BorderLayout());
//			
//			ListModel myModel1 = new MyModel();
//			JList list1 = new JList(myModel1);
//			list1.setPrototypeCellValue("000000000");
//			JScrollPane scrollPane1 = new JScrollPane(list1);
//			
//			ListModel myModel2 = new MyModel();
//			JList list2 = new JList(myModel2);
//			list2.setPrototypeCellValue("000000000");
//			JScrollPane scrollPane2 = new JScrollPane(list2);
//			
//			getContentPane().add(scrollPane1,"West");
//			getContentPane().add(scrollPane2,"East");
			
			MyTableModel myTableModel1 = new MyTableModel();
			JTable table1 = new JTable(myTableModel1);
			JScrollPane scrollPane1 = new JScrollPane(table1);
			
			JTable table2 = new JTable(myTableModel1);
			JScrollPane scrollPane2 = new JScrollPane(table2);
			
			getContentPane().add(scrollPane1,"West");
			getContentPane().add(scrollPane2,"East");
		}
}
