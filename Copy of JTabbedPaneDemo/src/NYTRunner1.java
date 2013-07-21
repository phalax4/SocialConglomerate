import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.temboo.core.TembooException;


public class NYTRunner1 {

	/**
	 * @param args
	 * @throws TembooException 
	 */
	String thread;FeedPull asdf = new FeedPull("1e189707ebda2e84b55d989021b0fb0b:8:67890430");
	public NYTRunner1() throws TembooException {
		
		
		
		
		
		
	}
	
	public String refresh() throws TembooException
	{
		thread = asdf.pullFeed();
		JsonParser jp = new JsonParser();
		JsonElement root = jp.parse(thread);
    	JsonObject rootobj = root.getAsJsonObject(); // may be Json Array if it's an array, or other type if a primitive
    	
    	JsonArray titles = rootobj.get("results").getAsJsonArray();
    	String parsedThread = "";
    	for(int i = 0; i < titles.size(); i++)
    	{
    		parsedThread += titles.get(i).getAsJsonObject().get("section") + "\n";
    		parsedThread += titles.get(i).getAsJsonObject().get("title") + "\n";
    		String author = titles.get(i).getAsJsonObject().get("byline").toString();
    		if(!author.equals("\"\""))
    			parsedThread += author + "\n";
    		parsedThread += titles.get(i).getAsJsonObject().get("url") + "\n\n";
    		
    	}
    	thread = "";
    	return parsedThread;
	}

}
