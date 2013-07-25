

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
import java.util.Properties;

import javax.swing.JOptionPane;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.temboo.Library.Twitter.Timelines.HomeTimeline;
import com.temboo.Library.Twitter.Timelines.HomeTimeline.HomeTimelineInputSet;
import com.temboo.Library.Twitter.Timelines.HomeTimeline.HomeTimelineResultSet;
import com.temboo.Library.Twitter.Tweets.StatusesUpdate;
import com.temboo.Library.Twitter.Tweets.StatusesUpdate.StatusesUpdateInputSet;
import com.temboo.Library.Twitter.Tweets.StatusesUpdate.StatusesUpdateResultSet;
import com.temboo.core.TembooException;
import com.temboo.core.TembooSession;



public class Tweet1 {
	String sub;
	String pin;
	String token; String tokenSecret;long name;
    String line; Gson gson = new Gson();String au;
    int var1,var2,var3,var4;String msg;
    private final static String CONSUMER_KEY = "hyL303lpgZpSt6cMmilBw";
	private final static String CONSUMER_KEY_SECRET = "EqgkdjEPuhP4KyVm3PEV926YuDrPcZAG249FxwXE9Q";
	private final static String APP_KEY_NAME = "myFirstApp";
	private final static String APP_KEY_VALUE = "231248aa-6da1-4f21-8";
	File file;Writer output = null;
	Twitter twitter = new TwitterFactory().getInstance();
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String> stringList = new ArrayList<String>();
	String getter;
	
	/*public String runPost(String message) throws TembooException{
		
		msg = message;
		return post(msg,list);
	}*/
	public String check() throws TwitterException, IOException, URISyntaxException, TembooException{
		if(getCreds()==null){
			start();
		}else{
			return refresh(list);
		}
		return null;	
	}
	public void start() throws TwitterException, IOException,URISyntaxException, TembooException {

		 

		file = new File("db.txt");
		twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);
		RequestToken requestToken = twitter.getOAuthRequestToken();
		System.out.println("Going to "+ requestToken.getAuthorizationURL());
		String web = requestToken.getAuthorizationURL();
		this.openBrowser(web);
		AccessToken accessToken = null;
		
		while (null == accessToken) {
			try {
				
				pin = JOptionPane.showInputDialog (null,"Input Pin: ");  

				accessToken = twitter.getOAuthAccessToken(requestToken, pin);

			} catch (TwitterException te) {

				System.out.println("Failed to get access token, caused by: "
						+ te.getMessage()); 

				System.out.println("Pin Error");
			}

		}
		//System.out.println(accessToken.getToken());
		//System.out.println(accessToken.getTokenSecret());
		//System.out.println("Id is: "+ twitter.getId());
		token = accessToken.getToken();
		tokenSecret = accessToken.getTokenSecret();
		name = twitter.getId();
		this.storeAccessToken();
		getCreds();
		refresh(list);
		
	}



	public String post(String text) throws TembooException{

		TembooSession session = new TembooSession("phalax4", APP_KEY_NAME, APP_KEY_VALUE);
		StatusesUpdate statusesUpdateChoreo = new StatusesUpdate(session);
		StatusesUpdateInputSet statusesUpdateInputs = statusesUpdateChoreo.newInputSet();

		statusesUpdateInputs.set_AccessToken(list.get(0));
		statusesUpdateInputs.set_AccessTokenSecret(list.get(1));
		statusesUpdateInputs.set_ConsumerSecret(CONSUMER_KEY_SECRET);
		statusesUpdateInputs.set_ConsumerKey(CONSUMER_KEY);
		statusesUpdateInputs.set_StatusUpdate(text);

		StatusesUpdateResultSet statusesUpdateResults = statusesUpdateChoreo.execute(statusesUpdateInputs);
		//System.out.println("Tweet Posted");
		return ("Posted!");
	}
	
	public String refresh(ArrayList<String> list1) throws TembooException, IOException, TwitterException{
		getCreds();
		TembooSession session = new TembooSession("phalax4", APP_KEY_NAME, APP_KEY_VALUE);
		
		HomeTimeline homeTimelineChoreo = new HomeTimeline(session);
		HomeTimelineInputSet homeTimelineInputs = homeTimelineChoreo.newInputSet();

		homeTimelineInputs.set_AccessToken(list1.get(0));
		homeTimelineInputs.set_AccessTokenSecret(list1.get(1));
		homeTimelineInputs.set_ConsumerSecret(CONSUMER_KEY_SECRET);
		homeTimelineInputs.set_ConsumerKey(CONSUMER_KEY);

		HomeTimelineResultSet homeTimelineResults = homeTimelineChoreo.execute(homeTimelineInputs);
		
		//System.out.println(homeTimelineResults.get_Response());

		
		 /*JsonElement jelement = new JsonParser().parse(homeTimelineResults.get_Response());
		    JsonObject  rootobj = jelement.getAsJsonObject();*/
		   
		 
		JsonParser jp = new JsonParser();
    	JsonElement root = jp.parse(homeTimelineResults.get_Response());
    	
    	JsonArray statuses = root.getAsJsonArray();
    	
		
    	//JsonArray statuses = rootobj.get("statuses").getAsJsonArray();
    	String thread = "";
    	
    	for(int i = 0; i < statuses.size(); i++) {
    		JsonObject status = statuses.get(i).getAsJsonObject();
    		
    		String text = status.get("text").getAsString();
    		String screen_name = status.get("user").getAsJsonObject().get("name").getAsString();
    		
    		JsonArray urls = status.get("entities").getAsJsonObject().get("urls").getAsJsonArray();
    		
    		au = urls.toString();
    		
    		var1 = au.indexOf(":")+1;
    		var2 = au.indexOf(":",var1)+1;
    		var3 = au.indexOf(":",var2)+2;
    		var4 = au.indexOf(",",var3)-1;
    		
    		if(au.charAt(var1)== ' '){
    			sub = " ";
    		}else if (!(var3 < 0 || var4 < 0)){
    			sub = au.substring(var3,var4);
    		}
    		else
    		{
    			sub = "";
    		}
    		
    		
    		thread += screen_name + "\n" + text+"\n"+sub +"\n\n";
    	}
		//new Reader().parse(result);

    	return thread;
	}

	public void storeAccessToken() throws IOException{
		output = new BufferedWriter(new FileWriter(file));
		output.write(token+"\r\n");
		output.write(tokenSecret+"\r\n");
		output.close();
		//System.out.println("Tokens Saved");
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
	
	public String getCreds(){

		try{
			FileInputStream fstream = new FileInputStream("db.txt");
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
	

}