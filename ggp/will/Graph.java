import ihm.VectorFrame;
import configuration.Config;

/*
 * Created on 24 févr. 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

/**
 * @author ferreira
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class Graph {
	
	
	public static void main(String[] args) {
		new Config();
		final VectorFrame vectorFrame = new VectorFrame("Graph");
		vectorFrame.pack();
		vectorFrame.setVisible(true);
		
		
	}
}
