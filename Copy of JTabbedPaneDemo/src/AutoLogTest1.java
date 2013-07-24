
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import twitter4j.TwitterException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.temboo.Library.Facebook.OAuth.FinalizeOAuth;
import com.temboo.Library.Facebook.OAuth.FinalizeOAuth.FinalizeOAuthInputSet;
import com.temboo.Library.Facebook.OAuth.FinalizeOAuth.FinalizeOAuthResultSet;
import com.temboo.Library.Facebook.OAuth.InitializeOAuth;
import com.temboo.Library.Facebook.OAuth.InitializeOAuth.InitializeOAuthInputSet;
import com.temboo.Library.Facebook.OAuth.InitializeOAuth.InitializeOAuthResultSet;
import com.temboo.Library.Facebook.Publishing.SetStatus;
import com.temboo.Library.Facebook.Publishing.SetStatus.SetStatusInputSet;
import com.temboo.Library.Facebook.Publishing.SetStatus.SetStatusResultSet;
import com.temboo.Library.Facebook.Reading.NewsFeed;
import com.temboo.Library.Facebook.Reading.NewsFeed.NewsFeedInputSet;
import com.temboo.Library.Facebook.Reading.NewsFeed.NewsFeedResultSet;
import com.temboo.core.TembooException;
import com.temboo.core.TembooSession;

public class AutoLogTest1 {

	private TembooSession session;
	private FinalizeOAuthResultSet finalizeOAuthResults;
	String fbAccessToken;Icon icon;
	private String message;String line;
	Writer output = null;ArrayList<String> list = new ArrayList<String>();
	File file;
	JFrame mark = new JFrame();
	public AutoLogTest1() throws TembooException, IOException, URISyntaxException{
		session = new TembooSession("jayos", "myFirstApp", "d23787cb-8d8f-4c83-a");
		icon = new ImageIcon(this.getClass().getResource("html.png"));

	}
	
	public String check() throws TwitterException, IOException, URISyntaxException, TembooException{
		if(getCreds()==null){
			token();
		}else{
			return refresh(list);
		}
		return null;	
	}

	public String getCreds(){

		try{
			FileInputStream fstream = new FileInputStream("dc.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			while ((line = br.readLine()) != null)   {
				list.add(line);
			}			

			return "Yay";
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
			return null;
		}
	}

	public void token() throws TembooException, IOException{
		file = new File("dc.txt");
		String callbackID;

		InitializeOAuth initializeOAuthChoreo = new InitializeOAuth(session);

		InitializeOAuthInputSet initializeOAuthInputs = initializeOAuthChoreo.newInputSet();

		initializeOAuthInputs.set_AppID("395494320570728");
		initializeOAuthInputs.set_Scope("read_stream,publish_actions");

		InitializeOAuthResultSet initializeOAuthResults = initializeOAuthChoreo.execute(initializeOAuthInputs);
		callbackID = initializeOAuthResults.get_CallbackID().trim();	

		//System.out.println("Opening URL");
		JOptionPane.showMessageDialog(mark,"Opening URL's. Please Wait...","Initializing",JOptionPane.INFORMATION_MESSAGE,icon);

		
		
		this.openBrowser(initializeOAuthResults.get_AuthorizationURL());

		FinalizeOAuth finalizeOAuthChoreo = new FinalizeOAuth(session);
		FinalizeOAuthInputSet finalizeOAuthInputs = finalizeOAuthChoreo.newInputSet();
		finalizeOAuthInputs.set_CallbackID(callbackID);
		finalizeOAuthInputs.set_AppSecret("7c874744ce5b9cfdf94fd330441ab8eb");
		finalizeOAuthInputs.set_AppID("395494320570728");
		FinalizeOAuthResultSet finalizeOAuthResults = finalizeOAuthChoreo.execute(finalizeOAuthInputs);
		fbAccessToken = finalizeOAuthResults.get_AccessToken();
		storeAccessToken();
		//System.out.println(fbAccessToken);
		getCreds();
		refresh(list);

	}


	public void storeAccessToken() throws IOException{

		output = new BufferedWriter(new FileWriter(file));
		output.write(fbAccessToken+"\r\n");
		output.flush();
		output.close();
		//System.out.println(fbAccessToken);
		//System.out.println(file);
		//System.out.println("Tokens Saved");
	}


	public void post(String status) throws TembooException{
		//TembooSession session = new TembooSession("jayos", "myFirstApp", "d23787cb-8d8f-4c83-a");


		SetStatus setStatusChoreo = new SetStatus(session);
		SetStatusInputSet setStatusInputs = setStatusChoreo.newInputSet();
		setStatusInputs.set_AccessToken(list.get(0));
		setStatusInputs.set_Message(status);
		SetStatusResultSet setStatusResults = setStatusChoreo.execute(setStatusInputs);
	}

	public String refresh(ArrayList<String> list1) throws TembooException
	{
		String f = "";

		NewsFeed newsFeedChoreo = new NewsFeed(session);
		NewsFeedInputSet newsFeedInputs = newsFeedChoreo.newInputSet();
		//fbAccessToken = token();

		//fbAccessToken = finalizeOAuthResults.get_AccessToken();
		newsFeedInputs.set_AccessToken(list1.get(0));

		NewsFeedResultSet newsFeedResults = newsFeedChoreo.execute(newsFeedInputs);
		String json = newsFeedResults.get_Response();

		JsonParser jp = new JsonParser();
		JsonElement root = jp.parse(json);
		JsonObject rootobj = root.getAsJsonObject(); 

		JsonArray feed = rootobj.get("data").getAsJsonArray();

		for(int i = 0; i < feed.size(); i++) {
			String name = feed.get(i).getAsJsonObject().get("from").getAsJsonObject().get("name").getAsString();
			JsonElement msg = feed.get(i).getAsJsonObject().get("message");

			String pict = "";
			if(msg==null){
				continue;

			}
			JsonElement pic = feed.get(i).getAsJsonObject().get("picture");

			if(pic != null){
				pict = pic.getAsString();
				f += name + "\n" + msg+ "\n" + pict + "\n\n";
			}
			else{
				f += name + "\n" + msg + "\n\n";
			}

		}

		return f;
	}
	
	public void openBrowser(String myUrl){
		try {
			Desktop desktop = java.awt.Desktop.getDesktop();
			URI oURL = new URI(myUrl);
			desktop.browse(oURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}