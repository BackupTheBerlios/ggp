/*
 * This code is based on an example provided by Richard Stanford, 
 * a tutorial reader.
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.io.Serializable;

import java.io.*;
import java.util.jar.*;
import javax.swing.*;


public class DynamicTree extends JPanel implements Serializable {
    protected DefaultMutableTreeNode rootNode;
	//protected Node rootNode;
    protected DefaultTreeModel treeModel;
    protected JTree tree;
    private Toolkit toolkit = Toolkit.getDefaultToolkit();

    public DynamicTree(String nom) {
        rootNode = new DefaultMutableTreeNode(nom);
		//rootNode = new Node(nom,"iup.html");
        treeModel = new DefaultTreeModel(rootNode);
        treeModel.addTreeModelListener(new MyTreeModelListener());

        tree = new JTree(treeModel);
        tree.setEditable(false);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(true);
		tree.putClientProperty("JTree.lineStyle", "Angled");

		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		renderer.setClosedIcon(new ImageIcon("images/ferme.png"));
		renderer.setOpenIcon(new ImageIcon("images/ouvert.png"));
		renderer.setLeafIcon(new ImageIcon("images/file.png"));

		tree.setCellRenderer(renderer);



        JScrollPane scrollPane = new JScrollPane(tree);
        setLayout(new GridLayout(1,0));
        add(scrollPane);
    }

    /** Remove all nodes except the root node. */
    public void clear() {
        rootNode.removeAllChildren();
        treeModel.reload();
    }

    /** Remove the currently selected node. */
    public void removeCurrentNode() {
        TreePath currentSelection = tree.getSelectionPath();
        if (currentSelection != null) {
            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode)
                         (currentSelection.getLastPathComponent());
            MutableTreeNode parent = (MutableTreeNode)(currentNode.getParent());
            if (parent != null) {
                treeModel.removeNodeFromParent(currentNode);
                return;
            }
        } 

        // Either there was no selection, or the root was selected.
        toolkit.beep();
    }

    /** Add child to the currently selected node. */
    public DefaultMutableTreeNode addObject(Object child) {
        DefaultMutableTreeNode parentNode = null;
        TreePath parentPath = tree.getSelectionPath();

        if (parentPath == null) {
            parentNode = rootNode;
        } else {
            parentNode = (DefaultMutableTreeNode)
                         (parentPath.getLastPathComponent());
        }

        return addObject(parentNode, child, true);
    }

    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
                                            Object child) {
        return addObject(parent, child, false);
    }

    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
                                            Object child, 
                                            boolean shouldBeVisible) {
        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

        if (parent == null) {
            parent = rootNode;
        }

        treeModel.insertNodeInto(childNode, parent, 
                                 parent.getChildCount());

        // Make sure the user can see the lovely new node.
        if (shouldBeVisible) {
            tree.scrollPathToVisible(new TreePath(childNode.getPath()));
        }
        return childNode;
    }

/************************************************************************
*																		*
*						Fonctions supplementaires						*
*																		*
************************************************************************/
public void sauve_arbre(String nom_fichier)
    {
      ObjectOutputStream os;


      try
      {
	  //sauvegarde l'arbre dans un fichier
	  os = new ObjectOutputStream(new FileOutputStream("arbre.txt"));
	  os.writeObject(tree);
      }
      catch(FileNotFoundException e)
      {
	  System.out.println("FileNotFoundException : " + e);
	  return;
      }
      catch(IOException e)
      {
	System.out.println("IOException : " + e);
        return;
      }

      creer_archive_jar(nom_fichier, "arbre.txt");
    }

    public void charger(String nomFichier)
    {
	try
	    {
		ObjectInputStream is = new ObjectInputStream(getClass().getResourceAsStream(nomFichier));
		tree.setModel( ( (JTree)(is.readObject()) ).getModel());
		treeModel=(DefaultTreeModel)tree.getModel();
		rootNode=(DefaultMutableTreeNode)tree.getModel().getRoot();
	    }
	catch(Exception e)
	    {
		System.out.println("Erreur : "+ e);
	    }
    }

    public void creer_archive_jar(String nom_archive, String nom_fichier)
    {
	try
	{
	   JarOutputStream jar = new JarOutputStream(new FileOutputStream(nom_archive), new Manifest());

	   //Allocation d'un tampon pour lire le fichier à archiver
	   byte[] buffer = new byte[1024];
	   int bytesRead;

	   //Ouverture du fichier à archiver
	   FileInputStream file = new FileInputStream(nom_fichier);

	   jar.putNextEntry(new JarEntry(nom_fichier));

	   //Lecture du fichier et ajout dans l'archive jar
	   while((bytesRead = file.read(buffer)) != -1)
	       jar.write(buffer, 0, bytesRead);

	   file.close();
	   jar.close();
	}
	catch(Exception e)
	{
	    System.out.println(e);
	}
    }



























    class MyTreeModelListener implements TreeModelListener 
    {
        public void treeNodesChanged(TreeModelEvent e) {
            DefaultMutableTreeNode node;
            node = (DefaultMutableTreeNode)(e.getTreePath().getLastPathComponent());

            /*
             * If the event lists children, then the changed
             * node is the child of the node we've already
             * gotten.  Otherwise, the changed node and the
             * specified node are the same.
             */
            try {
                int index = e.getChildIndices()[0];
                node = (DefaultMutableTreeNode)
                       (node.getChildAt(index));
            } catch (NullPointerException exc) {}

            System.out.println("The user has finished editing the node.");
            System.out.println("New value: " + node.getUserObject());
        }
        public void treeNodesInserted(TreeModelEvent e) {
        }
        public void treeNodesRemoved(TreeModelEvent e) {
        }
        public void treeStructureChanged(TreeModelEvent e) {
        }
    }
}

