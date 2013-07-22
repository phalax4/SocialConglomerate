import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import twitter4j.TwitterException;

import com.temboo.core.TembooException;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.UIManager.*;
import javax.swing.plaf.ColorUIResource;
public class JTabbedPaneDemo extends JPanel  implements ActionListener {
	JButton b1;ImageIcon icon,icon1,icon2,icon3,icon5;
	
	JTabbedPane jtbExample;
	JLabel lb1,lb2,lb3,lb4;
	
/*	AutoLogTest1 bob  = new AutoLogTest1();
	Tweet1 dave = new Tweet1();
	NYTRunner1 sally = new NYTRunner1();*/


	String text1,text2,text3;
	JPanel jplInnerPanel1,jplInnerPanel2,jplInnerPanel3,jplInnerPanel4 ;
	JTextArea textArea;boolean counter = true;boolean rev = false;
	
	public JTabbedPaneDemo() throws TembooException, IOException, URISyntaxException, TwitterException{
		
		b1 = new JButton("Refresh");
		//b1.setForeground(Color.WHITE);
		b1.setBackground(Color.WHITE);
		b1.setFont(new Font("Gothic",Font.BOLD,12));
		demo();
		getW();
	}
	public void demo() throws TembooException, IOException, URISyntaxException, TwitterException {
		jtbExample = new JTabbedPane();
		
		
		icon5 = new ImageIcon(this.getClass().getResource("f.png"));
		icon1 = new ImageIcon(this.getClass().getResource("t.png"));
		icon2 = new ImageIcon(this.getClass().getResource("n.png"));
		icon3 = new ImageIcon(this.getClass().getResource("sy.png"));
		
		 lb1 = new JLabel("Facebook");
		 lb2 = new JLabel("Twitter");
		 lb3 = new JLabel("NYtimes");

		

	/*	jplInnerPanel1 = createInnerPanel(bob.refresh());
		jplInnerPanel2 = createInnerPanel(dave.check());
		jplInnerPanel3 = createInnerPanel(sally.refresh());*/

		jplInnerPanel1 = createInnerPanel("Facebook");
		jplInnerPanel2 = createInnerPanel("Twitter");
		jplInnerPanel3 = createInnerPanel("Nytimes");
		


		
		jtbExample.addTab("Facebook", icon5, jplInnerPanel1);
		jtbExample.addTab("Twitter", icon1, jplInnerPanel2);
		jtbExample.addTab("News", icon2, jplInnerPanel3);
		
		jtbExample.addTab("Refresh", icon3, jplInnerPanel4);
		
		
		addIcon(lb1,icon5,0);
		addIcon(lb2,icon1,1);
		addIcon(lb3,icon2,2);
		
		
		
		
		jtbExample.setTabComponentAt(3,b1);
		
		
		setLayout(new GridLayout(1, 1));
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
			
			jplInnerPanel1 = createInnerPanel("F");
			jtbExample.addTab("Facebook", icon, jplInnerPanel1);

			jplInnerPanel2 = createInnerPanel("T");
			jtbExample.addTab("Twitter", icon, jplInnerPanel2);

			jplInnerPanel3 = createInnerPanel("S");
			jtbExample.addTab("news", icon, jplInnerPanel3);
			
			jtbExample.remove(0);
			jplInnerPanel4 = createInnerPanel("");
			jtbExample.addTab("", icon, jplInnerPanel4);

			jtbExample.setTabComponentAt(3,b1);

			addIcon(lb1,icon5,0);
			addIcon(lb2,icon1,1);
			addIcon(lb3,icon2,2);
			
			b1.setActionCommand("fresh");
			b1.addActionListener(this);
			
			/*try {
				jplInnerPanel1 = createInnerPanel(bob.refresh());
				jtbExample.addTab("Facebook", icon5, jplInnerPanel1);
				
				jplInnerPanel2 = createInnerPanel(dave.check());
				jtbExample.addTab("Twitter", icon1, jplInnerPanel2);

				jplInnerPanel3 = createInnerPanel(sally.refresh());
				jtbExample.addTab("news", icon2, jplInnerPanel3);

				
				
				jtbExample.remove(0);
				jplInnerPanel4 = createInnerPanel("");
				jtbExample.addTab("", icon3, jplInnerPanel4);
				jtbExample.setTabComponentAt(3,b1);
				b1.setActionCommand("fresh");
				b1.addActionListener(this);
				
			} catch (TembooException | TwitterException | IOException | URISyntaxException e1) {
				e1.printStackTrace();

			}*/


		}
	}
	
	public void getW() throws IOException{
		String state = "CA";
		String city = "San_Jose";
		String web = "http://api.wunderground.com/api/4ebc3b3ae2c41145/geolookup/conditions/forecast/q/"+state+"/"+city+".json";

		 URL oracle = new URL(web);
	        URLConnection yc = oracle.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	        String inputLine;
	        while ((inputLine = in.readLine()) != null){ 
	            System.out.println(inputLine);
	        
	    }
	}
	
	private void addIcon(JLabel label,ImageIcon icon,int index){
		label.setIcon(icon);
		label.setIconTextGap(5);
		label.setHorizontalTextPosition(SwingConstants.RIGHT);
		jtbExample.setTabComponentAt(index,label);
	}
	
	public static void main(String[] args) throws TembooException, IOException, URISyntaxException, TwitterException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//Color mycolor = new Color(127,233,255);
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            UIManager.getLookAndFeelDefaults().put("Panel.background", Color.BLACK);
		           
		            break;
		        }
		    }
		} catch (Exception e) {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}
		JFrame frame = new JFrame("TLdr");
		
		frame.getContentPane().add(new JTabbedPaneDemo(), BorderLayout.CENTER);
		frame.setSize(600, 600);
		frame.setVisible(true);
	}

}





