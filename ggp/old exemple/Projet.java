import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.io.*;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.net.URL;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.TreeSelectionModel;

public class Projet extends JPanel
{
    private JEditorPane htmlPane;
    public DynamicTree treePane;
    public MenuAction gestion = new MenuAction();
    private static boolean DEBUG = false;
    private URL helpURL;
    public MenuSelection select = new MenuSelection();
	public URL noeudUrl;
	
    public Projet(JFrame frame)
    {
	
	// création des panels qui vont être affichés à droite et à gauche (resp Infos et JEditorPane) 
        
        treePane = new DynamicTree("IUP");
	htmlPane = new JEditorPane();
        treePane.tree.addTreeSelectionListener(select);
	/*
	treePane.tree.addTreeSelectionListener(new TreeSelectionListener() {
           public void valueChanged(TreeSelectionEvent e) {
	       //DefaultMutableTreeNode node = (DefaultMutableTreeNode)
	       Node node = (Node)
		   treePane.tree.getLastSelectedPathComponent();
	       
	       if (node == null) return;
	       
                Object nodeInfo = node.getUserObject();
                //if (node.isLeaf()) {
		Node book = (Node)nodeInfo;
		displayURL(book.bookURL);
		if (DEBUG) {
		    System.out.print(book.bookURL + ":  \n    ");
                   }
                //} else {
                //    displayURL(helpURL); 
                //}
                if (DEBUG) {
                    System.out.println(nodeInfo.toString());
                }
	   }
	    });
	*/
	
	
	populateTree(treePane);
		
	htmlPane.setEditable(false);
	
	// on définit le layout utilisé
        setLayout(new BorderLayout());
	
	// changement des dimensions du treePanel
        treePane.setPreferredSize(new Dimension(400, 400));
	htmlPane.setPreferredSize(new Dimension(450, 450));
	
	// Creation du menu
	
	JMenuBar menuBar = new JMenuBar();
	JMenu fichier = new JMenu("Fichier");
	JMenu edition = new JMenu("Edition");
	
	fichier.setMnemonic('F');
	edition.setMnemonic('E');
	
	menuBar.add(fichier);
	menuBar.add(edition);

	
	
	// Menu "Fichier" :
	
	JMenuItem charger = new JMenuItem("Charger");
	JMenuItem sauver = new JMenuItem("Sauvegarder");
	JMenuItem quitter = new JMenuItem("Quitter");
	JMenuItem initialiser = new JMenuItem("Initialiser");
	
	charger.setMnemonic('C');
	sauver.setMnemonic('S');
	quitter.setMnemonic('Q');

	fichier.add(initialiser);
	fichier.add(charger);
	fichier.add(sauver);
	fichier.addSeparator();
	fichier.add(quitter);
	
		
	// Ajout au gestionnaire :
	initialiser.addActionListener(gestion);
	initialiser.setActionCommand("initialiser");
	charger.addActionListener(gestion);
	charger.setActionCommand("Charger");
	sauver.addActionListener(gestion);
	sauver.setActionCommand("Sauvegarder");
	quitter.addActionListener(gestion);
	quitter.setActionCommand("Quitter");
		
	// Menu "Edition" :

	JMenuItem nc = new JMenuItem("Nouvelle Catégorie");
	JMenuItem nsc = new JMenuItem("Nouvelle Sous-catégorie");
	JMenuItem sn = new JMenuItem("Supprimer noeud selectionné");
	JMenuItem vider = new JMenuItem("Vider l'arbre");

	nc.setMnemonic('N');
	nsc.setMnemonic('o');
	sn.setMnemonic('u');
	vider.setMnemonic('V');
	edition.add(nc);
	edition.add(nsc);
	edition.add(sn);
	edition.add(vider);

	// Ajout au gestionnaire :
	nc.addActionListener(gestion);
	nsc.addActionListener(gestion);
	sn.addActionListener(gestion);
	vider.addActionListener(gestion);

	nc.setActionCommand("nc");
	nsc.setActionCommand("nsc");
	sn.setActionCommand("sn");
	vider.setActionCommand("vider");

	// Creation dse Panels pour l'affichage :

	// Panel contenant les menus :
	JPanel menuPanel = new JPanel();
	BorderLayout b1 = new BorderLayout(4,4);
	menuPanel.setLayout(b1);
	menuPanel.add(menuBar);
	add(menuPanel, BorderLayout.NORTH);

	htmlPane = new JEditorPane();
        htmlPane.setEditable(false);

	JScrollPane treeView, htmlView;
	treeView = new JScrollPane(treePane);
	htmlView = new JScrollPane(htmlPane);
		
		
	// creation url pour afficher
	String url="http://www.google.fr";
	String s = "file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "test.html";
	try
	{
	    htmlPane.setPage(s);
	    htmlPane.getPage();
	}
	catch (NullPointerException exc) {}
	catch (IOException e)
	    {
		System.err.println("Attempted to read a bad URL: " + s);
	    }
	
	
	JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,treeView,htmlView);
	splitPane.setPreferredSize(new Dimension(950,450));
	add(splitPane, BorderLayout.CENTER);
	
	
    }
    private class MenuSelection implements TreeSelectionListener
    {
	public void valueChanged(TreeSelectionEvent e)
	{
	    DefaultMutableTreeNode node = (DefaultMutableTreeNode)treePane.tree.getLastSelectedPathComponent();
	    //Node node = (Node)treePane.tree.getLastSelectedPathComponent();
	       
	    if (node == null) return;
	       
	    Object nodeInfo = node.getUserObject();
	    //if (node.isLeaf()) {
	    Node book = (Node)nodeInfo;

		// petit test :-)
		
		try { noeudUrl = new URL(book.url); }
		catch (java.net.MalformedURLException exc) 
			{
                System.err.println("Attempted to create a BookInfo "
                                   + "with a bad URL: " + noeudUrl);
                noeudUrl = null;
            }


		displayURL(noeudUrl);
	    //displayURL(book.bookURL);
	    if (DEBUG) {
		System.out.print(book.bookURL + ":  \n    ");
	    }
	    //} else {
	    //    displayURL(helpURL); 
	    //}
	    if (DEBUG) {
		System.out.println(nodeInfo.toString());
	    }
	}
    }

    private class MenuAction implements ActionListener
    {
	public void actionPerformed(ActionEvent e)
	{
	    if(e.getActionCommand().equals("initialiser")) { populateTree(treePane); }
	    if(e.getActionCommand().equals("Sauvegarder"))
			{
			//treePane.sauve_arbre("arbre.jar");
			save(treePane.tree);
			}
	    if(e.getActionCommand().equals("Charger"))
			{ 
			load(treePane);
			//treePane.charger("arbre.txt");
			treePane.tree.addTreeSelectionListener(select);
			}
	    if(e.getActionCommand().equals("Quitter")) System.exit(0);
	    if(e.getActionCommand().equals("nc"))
		{
		    newNode np;
		    np = new newNode(false);
		}
	    if(e.getActionCommand().equals("nsc"))
		{
		    newNode ne;
		    ne = new newNode(true);
		}
	    if(e.getActionCommand().equals("vider")) { treePane.clear(); }
	    
	    /*
	      possible à utiliser mais il faut initialiser les .setActionCommand(...)
	      avec des entiers au lieu des chaines de caracteres
	      A voir plus tard quand tout sera terminé
	      
	      switch(e.getActionCommand())
	      {
	      case "Sauvegarder" : save(treePanel); break;
	      case "Charger" : System.out.println("Charger"); break;
	      case "Quitter" : System.exit(0); break;
	      case "np" : newNode np = new newNode(false); break;
	      case "ne" : newNode ne = new newNode(true); break;
	      case "cls" : treePanel.clear(); break;
	      default : System.out.println("action non prevue par le reglement ;-)"); break;
	      }*/
	    
	}
    }
    /*
      public class Node implements Serializable
      {
      public String bookName;
      public URL bookURL;
      public String prefix = "file:" 
      + System.getProperty("user.dir")
      + System.getProperty("file.separator");
      public Node(String book, String filename) {
      bookName = book;
      try {
      bookURL = new URL(prefix + filename);
      } catch (java.net.MalformedURLException exc) {
      System.err.println("Attempted to create a BookInfo "
      + "with a bad URL: " + bookURL);
      bookURL = null;
      }
      }
      
      public String toString() {
      return bookName;
      }}
    */
	public class newNode extends JFrame
	{
	    private JTextField nom;
	    public newNode(boolean nat)
	    {
		JOptionPane pane = new JOptionPane();
		String nom = "";
		nom = pane.showInputDialog("Entrez le nom de la nouvelle categorie");
		try
		    {
			if(!nom.equals(""))
			    {
				DefaultMutableTreeNode px;
				if(nat){ px = treePane.addObject(nom); }
				else{ px = treePane.addObject(null, nom); }
			    }
			}
		catch( NullPointerException e) {}
	    }
	};
    
    
/**************************************************************************/
/*																		  */
/*					fonction pour sauver l'arbre						  */
/*																		  */
/**************************************************************************/

    public void save(JTree tree) 
    {
	try
	    {
		JFileChooser choixFichier = new JFileChooser();
		choixFichier.setFileSelectionMode(JFileChooser.FILES_ONLY);
		choixFichier.addChoosableFileFilter(new ImageFilter());
		choixFichier.setAcceptAllFileFilterUsed(false);
		
		int resultat = choixFichier.showSaveDialog(this);
		if(resultat == JFileChooser.CANCEL_OPTION) return;
		File nom = choixFichier.getSelectedFile();
		
		if(nom == null || nom.getName().equals(""))
		    {
			JOptionPane mess = new JOptionPane();
			JOptionPane.showMessageDialog(mess, "Nom de fichier incorrect","", JOptionPane.ERROR_MESSAGE);
		    }
		else
		    {
			String nomf = nom.toString() + ".ser";
			FileOutputStream fichierOut = new FileOutputStream(nomf);
			ObjectOutputStream flux = new ObjectOutputStream(fichierOut);
			flux.writeObject(tree);//.getModel().getRoot());
			//flux.writeObject(tree);
			flux.flush();
			flux.close();
		    }
	    }
	catch (Exception e1)
	    {
		System.out.println("Erreur lors de la sauvegarde");
	    }
    }
    
    /************************************************************/
    /*															*/
    /*					fonction pour charger l'arbre			*/
    /*															*/
    /************************************************************/
    
    public void load(DynamicTree t)
    {
	try
	    {
		JFileChooser choixFichier = new JFileChooser();
		choixFichier.setFileSelectionMode(JFileChooser.FILES_ONLY);
		choixFichier.addChoosableFileFilter(new ImageFilter());
		choixFichier.setAcceptAllFileFilterUsed(false);
		
		int resultat = choixFichier.showOpenDialog(this);
		
		if(resultat == JFileChooser.CANCEL_OPTION) return;
		
		File nom = choixFichier.getSelectedFile();

		if(nom == null || nom.getName().equals(""))
		    {
			JOptionPane mess = new JOptionPane();
			JOptionPane.showMessageDialog(mess, "Nom de fichier incorrect","", JOptionPane.ERROR_MESSAGE);
		    }
		else
		    {
			FileInputStream fichierIn = new FileInputStream(nom);
			ObjectInputStream flux = new ObjectInputStream(fichierIn);
			DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode)flux.readObject();
			//DynamicTree tt = (DynamicTree)flux.readObject();
			t.rootNode = rootNode;
			t.treeModel = new DefaultTreeModel(rootNode);			
			t.tree.setModel(t.treeModel);
			//t=tt;
			//t.validate();
			//treePane = tt;
			
			treePane.validate();		
					
			flux.close();
		    }
	    }
	catch (Exception e2)
	    {
		System.out.println("Erreur lors du chargement" + e2);
	    }
    }
    
    
    // la méthode remplit l'arbre en définissant les parents et leurs enfants
    public void populateTree(DynamicTree treePanel)
    {
	String IUP1 = new String("IUP 1");
        String IUP2 = new String("IUP 2");
        String IUP3 = new String("IUP 3");
	
        String Mod1 = new String("Mod1");
	String Mod2 = new String("Mod2");
	String Mod3 = new String("Mod3");
	String Mod4 = new String("Mod4");
	
	DefaultMutableTreeNode rep1, rep2, rep3;
	
	rep1 = treePanel.addObject(null, new Node(IUP1,"truc.html"));
	rep2 = treePanel.addObject(null, new Node(IUP2,"iup2.html"));
	rep3 = treePanel.addObject(null, IUP3);
	
	//treePanel.addObject(rep1,Mod1);
	treePanel.addObject(rep1,new Node(Mod1,"bibi.html"));
	treePanel.addObject(rep1,new Node(Mod1,"truc.html"));
	treePanel.addObject(rep1,new Node(Mod1,"boum.html"));
	
	treePanel.addObject(rep2,new Node(Mod1,"bibi.html"));
	treePanel.addObject(rep2,new Node(Mod1,"bibi.html"));
	treePanel.addObject(rep2,new Node(Mod1,"bibi.html"));
	//treePanel.addObject(rep2,Mod4);
	
	treePanel.addObject(rep3,new Node(Mod1,"boum.html"));
	treePanel.addObject(rep3,new Node(Mod1,"boum.html"));
	//treePanel.addObject(rep3,Mod3);
	treePanel.addObject(rep3,new Node(Mod1,"boum.html"));
	
    }
    



    private void displayURL(URL url)
    {
        try
	    {
		htmlPane.setPage(url);
	    } catch (IOException e) {
		System.err.println("Attempted to read a bad URL: " + url);
	    }
    }
    







    public static void main(String[] args) 
    {
	JFrame frame = new JFrame();
	
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(1,1));
        contentPane.add(new Projet(frame));

        frame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		    System.exit(0);
		}
	    });
	
        frame.pack();
        frame.setVisible(true);
    }
}
