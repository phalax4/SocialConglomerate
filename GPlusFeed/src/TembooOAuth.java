import com.temboo.Library.Google.OAuth.FinalizeOAuth;
import com.temboo.Library.Google.OAuth.FinalizeOAuth.FinalizeOAuthInputSet;
import com.temboo.Library.Google.OAuth.FinalizeOAuth.FinalizeOAuthResultSet;
import com.temboo.Library.Google.OAuth.InitializeOAuth;
import com.temboo.Library.Google.OAuth.InitializeOAuth.InitializeOAuthInputSet;
import com.temboo.Library.Google.OAuth.InitializeOAuth.InitializeOAuthResultSet;
import com.temboo.Library.Google.Plus.Activities.List;
import com.temboo.Library.Google.Plus.Activities.List.ListInputSet;
import com.temboo.Library.Google.Plus.Activities.List.ListResultSet;
import com.temboo.Library.Google.Plus.*;
import com.temboo.core.TembooException;
import com.temboo.core.TembooSession;


public class TembooOAuth {

	/**
	 * @param args
	 * @throws TembooException 
	 */
	public static void main(String[] args) throws TembooException {
		// TODO Auto-generated method stub
		
		// Instantiate the Choreo, using a previously instantiated TembooSession object, eg:
		TembooSession session = new TembooSession("recollect", "myFirstApp", "dea3a230-9f31-4054-8");
		InitializeOAuth initializeOAuthChoreo = new InitializeOAuth(session);

		// Get an InputSet object for the choreo
		InitializeOAuthInputSet initializeOAuthInputs = initializeOAuthChoreo.newInputSet();

		// Set inputs
		initializeOAuthInputs.set_ClientID("936478966322-iba3ttorhi6r6cjdbs5hejs3ni1cabm6.apps.googleusercontent.com");
		initializeOAuthInputs.set_Scope("https://www.googleapis.com/auth/plus.login");

		// Execute Choreo
		InitializeOAuthResultSet initializeOAuthResults = initializeOAuthChoreo.execute(initializeOAuthInputs);
		
		String url = initializeOAuthResults.get_AuthorizationURL();
		url.replaceAll("force", "auto");
		
		System.out.println(url);
		
		// Instantiate the Choreo, using a previously instantiated TembooSession object, eg:
		// TembooSession session = new TembooSession("recollect", "APP_KEY_NAME", "APP_KEY_VALUE");
		FinalizeOAuth finalizeOAuthChoreo = new FinalizeOAuth(session);

		// Get an InputSet object for the choreo
		FinalizeOAuthInputSet finalizeOAuthInputs = finalizeOAuthChoreo.newInputSet();

		// Set inputs
		finalizeOAuthInputs.set_CallbackID(initializeOAuthResults.get_CallbackID());
		finalizeOAuthInputs.set_ClientSecret("VFHNmBKbgLgnRx77PMIlxhif");
		finalizeOAuthInputs.set_ClientID("936478966322-iba3ttorhi6r6cjdbs5hejs3ni1cabm6.apps.googleusercontent.com");

		// Execute Choreo
		FinalizeOAuthResultSet finalizeOAuthResults = finalizeOAuthChoreo.execute(finalizeOAuthInputs);
		
		System.out.println(finalizeOAuthResults.get_AccessToken());
		System.out.println(finalizeOAuthResults.get_Expires());
		System.out.println(finalizeOAuthResults.get_RefreshToken());
		
		//pulling threads
		List listChoreo = new List(session);

		// Get an InputSet object for the choreo
		ListInputSet listInputs = listChoreo.newInputSet();

		// Set inputs
		listInputs.set_AccessToken(finalizeOAuthResults.get_AccessToken());
		listInputs.set_UserID("me");

		// Execute Choreo
		ListResultSet listResults = listChoreo.execute(listInputs);

	}

}
