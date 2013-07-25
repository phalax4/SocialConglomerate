import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;











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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.syndication.io.FeedException;
import com.temboo.core.TembooException;

import twitter4j.TwitterException;
public class JTabbedPaneDemo extends JPanel  implements ActionListener {
	JButton b1,b2,b3;ImageIcon icon,icon1,icon2,icon3,icon5,icon4;
	JTextPane mary = new JTextPane();
	JTabbedPane jtbExample;
	JLabel lb1,lb2,lb3,lb4,twitter,facebook,lb5;
	JPanel p = new JPanel();
	String input,input1;


	/*Tweet1 dave = new Tweet1();
	AutoLogTest1 bob  = new AutoLogTest1();

	NYTRunner1 sally = new NYTRunner1();
	 */
	//TheOnionFeed crystal = new TheOnionFeed();

	String text1,text2,text3;JTextField t,t1 ;
	JPanel jplInnerPanel1,jplInnerPanel2,jplInnerPanel3,jplInnerPanel4,jplInnerPanel5 ,jplInnerPanel6;
	JTextArea textArea;boolean counter = true;boolean rev = false;

	public JTabbedPaneDemo() throws TembooException, IOException, URISyntaxException, TwitterException, IllegalArgumentException, FeedException{

		b1 = new JButton("Refresh");
		b2 = new JButton("Post");
		b3 = new JButton("Send");
		b1.setBackground(Color.WHITE);
		b1.setFont(new Font("Gothic",Font.BOLD,12));
		mary.setEditable(false);

		demo();

		//getW();
	}
	public void demo() throws TembooException, IOException, URISyntaxException, TwitterException, IllegalArgumentException, FeedException {
		jtbExample = new JTabbedPane();


		icon5 = new ImageIcon(this.getClass().getResource("f.png"));
		icon1 = new ImageIcon(this.getClass().getResource("t.png"));
		icon2 = new ImageIcon(this.getClass().getResource("n.png"));
		icon3 = new ImageIcon(this.getClass().getResource("sy.png"));
		icon4=new ImageIcon(this.getClass().getResource("onion.png"));
		lb1 = new JLabel("Facebook");
		lb2 = new JLabel("Twitter");
		lb3 = new JLabel("NYtimes");
		lb4 = new JLabel("Interact");
		lb5 = new JLabel("theOnion");


		/*jplInnerPanel1 = createInnerPanel(bob.check());
		jplInnerPanel2 = createInnerPanel(dave.check());

		jplInnerPanel3 = createInnerPanel(sally.refresh());*/
		//jplInnerPanel6 = createInnerPanel(crystal.refresh());

		jplInnerPanel1 = createInnerPanel("Facebook");
		jplInnerPanel2 = createInnerPanel("Twitter");
		jplInnerPanel3 = createInnerPanel("Nytimes");

		jplInnerPanel6 = createInnerPanel("theOnion");





		jtbExample.addTab("Facebook", icon5, jplInnerPanel1);
		jtbExample.addTab("Twitter", icon1, jplInnerPanel2);
		jtbExample.addTab("News", icon2, jplInnerPanel3);

		jtbExample.addTab("theOnion",icon4,jplInnerPanel6);
		jtbExample.addTab("Interact",icon3,p);

		jtbExample.addTab("Refresh", icon3, jplInnerPanel4);


		addIcon(lb1,icon5,0);
		addIcon(lb2,icon1,1);
		addIcon(lb3,icon2,2);
		addIcon(lb5,icon4,3);
		addIcon(lb4,icon3,4);



		jtbExample.setTabComponentAt(5,b1);
		twitter = new JLabel("Twitter");
		facebook = new JLabel("Facebook");
		t = new JTextField(15);
		t1 = new JTextField(15);

		p.setBackground(Color.WHITE);
		p.setLayout(new FlowLayout());

		p.add(twitter);
		p.add(t);
		p.add(b2);

		p.add(facebook);

		p.add(t1);
		p.add(b3);



		setLayout(new GridLayout(1, 1));
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		add(jtbExample);




	}



	protected JPanel createInnerPanel(String text) {
		JPanel jplPanel = new JPanel();
		textArea = new JTextArea();
		//mary.setText(text);

		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setText(text);
		//JScrollPane scrollPane = new JScrollPane(mary);
		jplPanel.setLayout(new GridLayout(1, 1));
		jplPanel.add(scrollPane);

		return jplPanel;
	}





	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==b1) {
			jtbExample.remove(0);
			jtbExample.remove(0);
			jtbExample.remove(0);

			jtbExample.remove(0);
			jtbExample.remove(0);
			jtbExample.remove(0);

			jplInnerPanel1 = createInnerPanel("F");
			jtbExample.addTab("Facebook", icon, jplInnerPanel1);

			jplInnerPanel2 = createInnerPanel("T");
			jtbExample.addTab("Twitter", icon, jplInnerPanel2);

			jplInnerPanel3 = createInnerPanel("S");
			jtbExample.addTab("news", icon, jplInnerPanel3);

			jplInnerPanel6 = createInnerPanel("O");
			jtbExample.addTab("theOnion",icon4,jplInnerPanel6);


			jtbExample.addTab("Interact",icon3,p);

			jplInnerPanel4 = createInnerPanel("");
			jtbExample.addTab("", icon, jplInnerPanel4);

			addIcon(lb1,icon5,0);
			addIcon(lb2,icon1,1);
			addIcon(lb3,icon2,2);
			addIcon(lb5,icon4,3);
			addIcon(lb4,icon3,4);




			p.setBackground(Color.WHITE);
			p.setLayout(new FlowLayout());
			p.add(twitter);
			p.add(t);
			p.add(b2);

			p.add(facebook);

			p.add(t1);
			p.add(b3);



			jtbExample.setTabComponentAt(5,b1);


			/*try {
			jtbExample.remove(0);
			jtbExample.remove(0);
			jtbExample.remove(0);

		jplInnerPanel1 = createInnerPanel(bob.check());
		jtbExample.addTab("Facebook", icon, jplInnerPanel1);

		jplInnerPanel2 = createInnerPanel(dave.check());
		jtbExample.addTab("Twitter", icon, jplInnerPanel2);

		jplInnerPanel3 = createInnerPanel(sally.refresh());
		jtbExample.addTab("news", icon, jplInnerPanel3);

		jtbExample.remove(0);
		jplInnerPanel4 = createInnerPanel("");
		jtbExample.addTab("", icon, jplInnerPanel4);

		jtbExample.setTabComponentAt(3,b1);

		addIcon(lb1,icon5,0);
		addIcon(lb2,icon1,1);
		addIcon(lb3,icon2,2);
		addIcon(lb4,icon3,3);



		} catch (TwitterException | IOException | URISyntaxException | TembooException e1) {
			e1.printStackTrace();

		}*/
		}








		if (e.getSource()==b2) {
			/*input=t.getText();

			try {

				dave.post(input);
			} catch (TembooException e1) {
				e1.printStackTrace();
			}

		}

		if(e.getSource()==b3){
			input1 = t1.getText();
			try {
				bob.post(input1);
			} catch (TembooException e1) {

				e1.printStackTrace();
			}*/
		}


	}





	public String getW() throws IOException{
		String state = "CA";
		String city = "San_Jose";
		String web = "http://api.wunderground.com/api/4ebc3b3ae2c41145/geolookup/conditions/forecast/q/"+state+"/"+city+".json";
		String weather = "";
		URL oracle = new URL(web);
		URLConnection yc = oracle.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null){ 
			System.out.println(inputLine);
			weather+=inputLine;
		}

		String thread = "";
		while ((inputLine = in.readLine()) != null){
			JsonParser jp = new JsonParser();
			JsonElement root = jp.parse(in);
			JsonObject rootobj = root.getAsJsonObject();

			String forecast = rootobj.get("forecast").getAsJsonObject().get("txt_forecast").getAsJsonObject().get("forecastday").getAsJsonArray().get(0).getAsJsonObject().get("fcttext").getAsString();
			//System.out.println(forecast);
			//System.out.println(inputLine);
			thread = forecast;
		}
		return thread;
	}

	private void addIcon(JLabel label,ImageIcon icon,int index){
		label.setIcon(icon);
		label.setIconTextGap(5);
		label.setHorizontalTextPosition(SwingConstants.RIGHT);
		jtbExample.setTabComponentAt(index,label);
	}

	static JFrame frame;
	public static void main(String[] args) throws TembooException, IOException, URISyntaxException, TwitterException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {




		frame = new JFrame("Multi-Social");



		PasswordDialog carl = new PasswordDialog(frame);
		carl.createGuiFrame();



		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}


}





