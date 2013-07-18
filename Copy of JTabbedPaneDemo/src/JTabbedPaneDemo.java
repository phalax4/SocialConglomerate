import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import java.awt.*;
import java.awt.event.*;

public class JTabbedPaneDemo extends JPanel {

	public JTabbedPaneDemo() {
		ImageIcon icon = new ImageIcon("java-swing-tutorial.JPG");
		JTabbedPane jtbExample = new JTabbedPane();
		
		JPanel jplInnerPanel1 = createInnerPanel("Facebook");
		jtbExample.addTab("Facebook", icon, jplInnerPanel1, "Tab 1");
		jtbExample.setSelectedIndex(0);
		JPanel p = new JPanel();
		
	    JPanel jplInnerPanel2 = createInnerPanel("Twitter");
		jtbExample.addTab("Twitter", icon, jplInnerPanel2);
		
		JPanel jplInnerPanel3 = createInnerPanel("News");
		jtbExample.addTab("News", icon, jplInnerPanel3, "Tab 3");
		setLayout(new GridLayout(1, 1));
		add(jtbExample);
	//Specify different JLABEL to each
	
	}
	
	

	protected JPanel createInnerPanel(String text) {
		JPanel jplPanel = new JPanel();
//		JLabel jlbDisplay = new JLabel(text);
		
		JTextArea textArea = new JTextArea();

		// add text to it; we want to make it scroll
		textArea.setText(text + "\nxx\nxx\nxx\nxx\nxx\nxx\nxx\nxx\nxx\nxx\nxx\nxx\nxx\nxx\nxx\nxx\n");
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		jplPanel.setLayout(new GridLayout(1, 1));
		jplPanel.add(scrollPane);
		
		return jplPanel;
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("TabbedPane Source Demo");
		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.getContentPane().add(new JTabbedPaneDemo(), BorderLayout.CENTER);
		frame.setSize(600, 600);
		frame.setVisible(true);
	}


	}


