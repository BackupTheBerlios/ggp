/*
 * Created on 24 févr. 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package ihm;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import configuration.Config;

/**
 * @author ferreira
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class VectorFrame extends JFrame{
	private ResourceBundle message = ResourceBundle.getBundle(Config.config.getProperty("cheminTraduction") + "." + Config.locale.getLanguage() + getClass().getName().substring(getClass().getName().lastIndexOf('.')), Config.locale);
	private String title;
	private VectorPanel mainPanel;
	
	
	public VectorFrame(String title){
		super(title);
		this.title=title;
		mainPanel = new VectorPanel();
		setContentPane(mainPanel);
		
		/*addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent arg0) {
				dispose();
			}
		});*/
		addWindowListener(new MyWindowsListener());
		addMouseListener(new MyMouseListener());
	}
	
	class VectorPanel extends JPanel{
		public VectorPanel(){
			setLayout(new GridLayout(2,2));
			
			//---
			JButton bouton = new JButton(message.getString("Hello_world"));
			bouton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					new MyDialog();
				}
			});
			add(bouton);
			
			add(new JLabel("adresse :"));
			add(new JTextField(20));
			JTextArea textArea = new JTextArea(5,20);
			add(new JScrollPane(textArea));
		}
	}
	
	
	class MyWindowsListener extends WindowAdapter {
		public void windowClosing(WindowEvent arg0) {
			System.out.println("sortie");
			int res = JOptionPane.showConfirmDialog(null, "quitter ?");
			switch (res) {
				case JOptionPane.YES_OPTION :
					System.exit(0);
					break;
				case JOptionPane.NO_OPTION :
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
					break;
				case JOptionPane.CANCEL_OPTION :
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
					break;
			}
		}
	}
	
	class MyMouseListener extends MouseAdapter{

		/* (non-Javadoc)
		 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
		 */
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("clicked");
		}

		/* (non-Javadoc)
		 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
		 */
		public void mousePressed(MouseEvent arg0) {
			System.out.println("pressed");
			
		}

		/* (non-Javadoc)
		 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
		 */
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("released");
		}
		
	};
}
