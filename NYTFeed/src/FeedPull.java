import com.temboo.Library.NYTimes.TimesNewswire.GetRecentNewsItems;
import com.temboo.Library.NYTimes.TimesNewswire.GetRecentNewsItems.GetRecentNewsItemsInputSet;
import com.temboo.Library.NYTimes.TimesNewswire.GetRecentNewsItems.GetRecentNewsItemsResultSet;
import com.temboo.core.TembooException;
import com.temboo.core.TembooSession;


public class FeedPull {
	
	private String API_Key;
	
	public FeedPull(String key)
	{
		API_Key = key;
	}
	
	public String pullFeed() throws TembooException
	{
		TembooSession session = new TembooSession("recollect", "myFirstApp", "dea3a230-9f31-4054-8");
		
		GetRecentNewsItems getRecentNewsItemsChoreo = new GetRecentNewsItems(session);

		// Get an InputSet object for the choreo
		GetRecentNewsItemsInputSet getRecentNewsItemsInputs = getRecentNewsItemsChoreo.newInputSet();

		// Set inputs
		getRecentNewsItemsInputs.set_APIKey(API_Key);

		// Execute Choreo
		GetRecentNewsItemsResultSet getRecentNewsItemsResults = getRecentNewsItemsChoreo.execute(getRecentNewsItemsInputs);
		
		return getRecentNewsItemsResults.get_Response();
	}
	
}
