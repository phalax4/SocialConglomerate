
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

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
import com.temboo.Library.Facebook.Reading.NewsFeed;
import com.temboo.Library.Facebook.Reading.NewsFeed.NewsFeedInputSet;
import com.temboo.Library.Facebook.Reading.NewsFeed.NewsFeedResultSet;
import com.temboo.core.TembooException;
import com.temboo.core.TembooSession;

public class AutoLogTest1 {
	
	private TembooSession session;
	private FinalizeOAuthResultSet finalizeOAuthResults;
	private String fbAccessToken;
	private String message;
	public AutoLogTest1() throws TembooException, IOException, URISyntaxException{
		
		String callbackID;
		String fbAccessToken;
		Scanner in = new Scanner(System.in);
		
		session = new TembooSession("jayos", "myFirstApp", "d23787cb-8d8f-4c83-a");
		InitializeOAuth initializeOAuthChoreo = new InitializeOAuth(session);
		
		InitializeOAuthInputSet initializeOAuthInputs = initializeOAuthChoreo.newInputSet();
		

		initializeOAuthInputs.set_AppID("395494320570728");
		initializeOAuthInputs.set_Scope("read_stream");


		InitializeOAuthResultSet initializeOAuthResults = initializeOAuthChoreo.execute(initializeOAuthInputs);
			

		callbackID = initializeOAuthResults.get_CallbackID().trim();
		
		
		
		System.out.println("Opening URL");

		this.openBrowser(initializeOAuthResults.get_AuthorizationURL());
		
		FinalizeOAuth finalizeOAuthChoreo = new FinalizeOAuth(session);
		FinalizeOAuthInputSet finalizeOAuthInputs = finalizeOAuthChoreo.newInputSet();
		finalizeOAuthInputs.set_CallbackID(callbackID);
		finalizeOAuthInputs.set_AppSecret("7c874744ce5b9cfdf94fd330441ab8eb");
		finalizeOAuthInputs.set_AppID("395494320570728");
		finalizeOAuthResults = finalizeOAuthChoreo.execute(finalizeOAuthInputs);
		
				
	}
	
	public String refresh() throws TembooException
	{
		String f = "";
		
				NewsFeed newsFeedChoreo = new NewsFeed(session);
				NewsFeedInputSet newsFeedInputs = newsFeedChoreo.newInputSet();
				fbAccessToken = finalizeOAuthResults.get_AccessToken();
				newsFeedInputs.set_AccessToken(fbAccessToken);
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
		   				f += name + "\n" + message + "\n" + pict + "\n\n";
		   			}
		   			else{
		   				f += name + "\n" + message + "\n\n";
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