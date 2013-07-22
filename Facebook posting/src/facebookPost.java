import java.util.Scanner;

import javax.swing.JOptionPane;

import com.temboo.Library.Facebook.OAuth.FinalizeOAuth;
import com.temboo.Library.Facebook.OAuth.InitializeOAuth;
import com.temboo.Library.Facebook.OAuth.FinalizeOAuth.FinalizeOAuthInputSet;
import com.temboo.Library.Facebook.OAuth.FinalizeOAuth.FinalizeOAuthResultSet;
import com.temboo.Library.Facebook.OAuth.InitializeOAuth.InitializeOAuthInputSet;
import com.temboo.Library.Facebook.OAuth.InitializeOAuth.InitializeOAuthResultSet;
import com.temboo.Library.Facebook.Publishing.SetStatus;
import com.temboo.Library.Facebook.Publishing.SetStatus.SetStatusInputSet;
import com.temboo.Library.Facebook.Publishing.SetStatus.SetStatusResultSet;
import com.temboo.core.TembooException;
import com.temboo.core.TembooSession;


public class facebookPost {
	
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
		}
}