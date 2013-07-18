import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import twitter4j.TwitterException;

import com.temboo.core.TembooException;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class JTabbedPaneDemo extends JPanel {

	JTextArea textArea;
	public JTabbedPaneDemo() throws TembooException, IOException, URISyntaxException, TwitterException {
		ImageIcon icon = new ImageIcon("java-swing-tutorial.JPG");
		JTabbedPane jtbExample = new JTabbedPane();
		
		JPanel jplInnerPanel1 = createInnerPanel(new AutoLogTest1().refresh());
		jtbExample.addTab("Facebook", icon, jplInnerPanel1, "Tab 1");
		jtbExample.setSelectedIndex(0);
		JPanel p = new JPanel();
		
	    JPanel jplInnerPanel2 = createInnerPanel(new Tweet1().check());
		jtbExample.addTab("Twitter", icon, jplInnerPanel2);
		
		JPanel jplInnerPanel3 = createInnerPanel(new NYTRunner1().refresh());
		jtbExample.addTab("News", icon, jplInnerPanel3, "Tab 3");
		setLayout(new GridLayout(1, 1));
		add(jtbExample);

		//jtbExample.add(new JLabel("Hi"));
	//Specify different JLABEL to each
	
	}
	
	

	protected JPanel createInnerPanel(String text) {
		JPanel jplPanel = new JPanel();
//		JLabel jlbDisplay = new JLabel(text);
		
		textArea = new JTextArea();

		// add text to it; we want to make it scroll
		textArea.setText(text);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		jplPanel.setLayout(new GridLayout(1, 1));
		jplPanel.add(scrollPane);
		
		return jplPanel;
	}

	public static void main(String[] args) throws TembooException, IOException, URISyntaxException, TwitterException {
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


