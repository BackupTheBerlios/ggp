/*
 * Created on 31 mars 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package ihm;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 * @author ggp
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class GraphicsPainter extends JFrame
{
	private JSplitPane _principal = new JSplitPane(JSplitPane.VERTICAL_SPLIT );
	private JPanel _top = new JPanel(); 
	private JPanel _bottom = new JPanel();
	
	public GraphicsPainter()
	{
		JButton test1 = new JButton("Hello_world");
		JButton test2 = new JButton("GoodBye_world");
		_top.add(test1);
		_bottom.add(test2);
		_top.add(new JLabel("adresse :"));
		_principal.add(_top,JSplitPane.TOP);
		_principal.add(_bottom,JSplitPane.BOTTOM);
		this.getContentPane().add(_principal);
		
	}

	public static void main(String[] args)
	{
		GraphicsPainter painter = new GraphicsPainter();
		
		
		painter.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){System.exit(0);}});
		painter.pack();
		painter.setVisible(true);
		
		
	}
}
