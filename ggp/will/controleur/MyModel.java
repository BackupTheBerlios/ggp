/*
 * Created on 10 mars 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package controleur;

import javax.swing.DefaultListModel;

/**
 * @author 210
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MyModel extends DefaultListModel{
		
	private String[] vals = new String[1000];
	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	public Object getElementAt(int i) {
		System.out.println(i);
		if (vals[i]==null)
			return "vide";
		return (vals[i]);
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getSize()
	 */
	public int getSize() {
		return 1000;
	}
	/* (non-Javadoc)
	 * @see javax.swing.DefaultListModel#setElementAt(java.lang.Object, int)
	 */
	public void setElementAt(Object val, int pos) {
		vals[pos]=(String)val;
	}
	
}
