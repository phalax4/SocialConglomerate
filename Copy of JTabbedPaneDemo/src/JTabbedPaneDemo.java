import javax.swing.JButton;
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
import javax.swing.UIManager.*;
public class JTabbedPaneDemo extends JPanel  implements ActionListener {
	JButton b1;ImageIcon icon;
	JTabbedPane jtbExample;
	AutoLogTest1 bob  = new AutoLogTest1();
	Tweet1 dave = new Tweet1();
	NYTRunner1 sally = new NYTRunner1();


	String text1,text2,text3;
	JPanel jplInnerPanel1,jplInnerPanel2,jplInnerPanel3,jplInnerPanel4 ;
	JTextArea textArea;
	public JTabbedPaneDemo() throws TembooException, IOException, URISyntaxException, TwitterException {
		b1 = new JButton("Refresh");
		icon = new ImageIcon("java-swing-tutorial.JPG");

		jtbExample = new JTabbedPane();

		jplInnerPanel1 = createInnerPanel(bob.refresh());
		jplInnerPanel2 = createInnerPanel(dave.check());
		jplInnerPanel3 = createInnerPanel(sally.refresh());

		//jplInnerPanel1 = createInnerPanel("Facebook");
		// jplInnerPanel2 = createInnerPanel("Twitter");
		//jplInnerPanel3 = createInnerPanel("Nytimes");



		jplInnerPanel4 = createInnerPanel("");

		jtbExample.addTab("Facebook", icon, jplInnerPanel1);
		jtbExample.addTab("Twitter", icon, jplInnerPanel2);
		jtbExample.addTab("News", icon, jplInnerPanel3);
		setLayout(new GridLayout(1, 1));
		jtbExample.addTab("", icon, jplInnerPanel4);
		setLayout(new GridLayout(1, 1));
		jtbExample.setTabComponentAt(3,b1);
		b1.setActionCommand("fresh");
		b1.addActionListener(this);
		add(jtbExample);

	}



	protected JPanel createInnerPanel(String text) {
		JPanel jplPanel = new JPanel();
		textArea = new JTextArea();
		textArea.setText(text);
		JScrollPane scrollPane = new JScrollPane(textArea);
		jplPanel.setLayout(new GridLayout(1, 1));
		jplPanel.add(scrollPane);

		return jplPanel;
	}





	@Override
	public void actionPerformed(ActionEvent e) {
		if ("fresh".equals(e.getActionCommand())) {
			jtbExample.remove(0);
			jtbExample.remove(0);
			jtbExample.remove(0);

			try {
				jplInnerPanel1 = createInnerPanel(bob.refresh());
				jtbExample.addTab("Facebook", icon, jplInnerPanel1);
				
				jplInnerPanel2 = createInnerPanel(dave.check());
				jtbExample.addTab("Twitter", icon, jplInnerPanel2);

				jplInnerPanel3 = createInnerPanel(sally.refresh());
				jtbExample.addTab("news", icon, jplInnerPanel3);

				
				
				jtbExample.remove(0);
				jplInnerPanel4 = createInnerPanel("");
				jtbExample.addTab("", icon, jplInnerPanel4);
				jtbExample.setTabComponentAt(3,b1);
				b1.setActionCommand("fresh");
				b1.addActionListener(this);
				
			} catch (TembooException | TwitterException | IOException | URISyntaxException e1) {
				e1.printStackTrace();

			}


		}
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


