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
import java.io.Serializable;

public class Node extends DefaultMutableTreeNode implements Serializable
		{
		public String bookName;
        public URL bookURL;
		public String url;
        public String prefix = "file:" 
                               + System.getProperty("user.dir")
                               + System.getProperty("file.separator");
        public Node(String book, String filename) {
            bookName = book;
			url = prefix + filename;
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
