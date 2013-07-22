import java.util.Scanner;

import javax.swing.JOptionPane;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.temboo.Library.Facebook.OAuth.FinalizeOAuth;
import com.temboo.Library.Facebook.OAuth.InitializeOAuth;
import com.temboo.Library.Facebook.OAuth.FinalizeOAuth.FinalizeOAuthInputSet;
import com.temboo.Library.Facebook.OAuth.FinalizeOAuth.FinalizeOAuthResultSet;
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


public class facebookcleanup {
	
	public static TembooSession session() throws TembooException{
		TembooSession session = new TembooSession("jayos", "myFirstApp", "d23787cb-8d8f-4c83-a");
		return session;
	}

	public static String token() throws TembooException{
		String callbackID;
		String fbAccessToken;
		Scanner in = new Scanner(System.in);
		
		// Instantiate the Choreo, using a previously instantiated TembooSession object, eg:
		TembooSession session = new TembooSession("jayos", "myFirstApp", "d23787cb-8d8f-4c83-a");
		InitializeOAuth initializeOAuthChoreo = new InitializeOAuth(session);
		
		// Get an InputSet object for the choreo
		InitializeOAuthInputSet initializeOAuthInputs = initializeOAuthChoreo.newInputSet();
		
		// Set inputs
		initializeOAuthInputs.set_AppID("395494320570728");
		initializeOAuthInputs.set_Scope("read_stream,publish_actions");

		// Execute Choreo
		InitializeOAuthResultSet initializeOAuthResults = initializeOAuthChoreo.execute(initializeOAuthInputs);
			
		// Send user to website to authorize and get code for finalize step
		//JOptionPane.showInputDialog(null, "go to the following link \n" + initializeOAuthResults);
		System.out.println("Visit the following website and click Allow, pressing enter here after you have done so.\n" + initializeOAuthResults.get_AuthorizationURL());
		callbackID = in.nextLine().trim();
		callbackID = initializeOAuthResults.get_CallbackID().trim();
		
		FinalizeOAuth finalizeOAuthChoreo = new FinalizeOAuth(session);
		FinalizeOAuthInputSet finalizeOAuthInputs = finalizeOAuthChoreo.newInputSet();
		finalizeOAuthInputs.set_CallbackID(callbackID);
		finalizeOAuthInputs.set_AppSecret("7c874744ce5b9cfdf94fd330441ab8eb");
		finalizeOAuthInputs.set_AppID("395494320570728");
		FinalizeOAuthResultSet finalizeOAuthResults = finalizeOAuthChoreo.execute(finalizeOAuthInputs);
		fbAccessToken = finalizeOAuthResults.get_AccessToken();
		return fbAccessToken;
	}
	
	public static void main(String[] args) throws TembooException{
		
		String status = JOptionPane.showInputDialog("Enter your facebook status: ");
		String key = token();
		
		SetStatus setStatusChoreo = new SetStatus(session());

		// Get an InputSet object for the choreo
		SetStatusInputSet setStatusInputs = setStatusChoreo.newInputSet();

		// Set inputs
		setStatusInputs.set_AccessToken(key);
		//String status = JOptionPane.showInputDialog("Enter your facebook status: ");
		setStatusInputs.set_Message(status);

		// I know there's an error, just leave it
		// It needs the line below to work
		SetStatusResultSet setStatusResults = setStatusChoreo.execute(setStatusInputs);
		
		NewsFeed newsFeedChoreo = new NewsFeed(session());
		NewsFeedInputSet newsFeedInputs = newsFeedChoreo.newInputSet();
		//System.out.println(token());
		newsFeedInputs.set_AccessToken(key);
		NewsFeedResultSet newsFeedResults = newsFeedChoreo.execute(newsFeedInputs);
		String json = newsFeedResults.get_Response();
		//System.out.println(json);
		
		// Parse the json with gson
    	JsonParser jp = new JsonParser();
    	JsonElement root = jp.parse(json);
    	JsonObject rootobj = root.getAsJsonObject(); // may be Json Array if it's an array, or other type if a primitive
    	
    	JsonArray feed = rootobj.get("data").getAsJsonArray();
    	
    	for(int i = 0; i < feed.size(); i++) {
    		String name = feed.get(i).getAsJsonObject().get("from").getAsJsonObject().get("name").getAsString();
   			JsonElement msg = feed.get(i).getAsJsonObject().get("message");
   			String message = "a custom post such as a video or picture";
   			String pict = "";
   			JsonElement pic = feed.get(i).getAsJsonObject().get("picture");
   			if(msg != null) {
   				message = msg.getAsString();
   			}
   			if(pic != null){
   				pict = pic.getAsString();
   				System.out.println(name + "\n" + message + "\n" + pict + "\n");
   			}
   			else{
   				System.out.println(name + "\n" + message + "\n");
   			}
   			//JOptionPane.showMessageDialog(null, name + "\n" + message);
    	}		
	}
}
