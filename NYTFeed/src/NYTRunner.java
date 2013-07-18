import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.temboo.core.TembooException;


public class NYTRunner {

	/**
	 * @param args
	 * @throws TembooException 
	 */
	public static void main(String[] args) throws TembooException {
		
		FeedPull asdf = new FeedPull("1e189707ebda2e84b55d989021b0fb0b:8:67890430");
		
		String thread = asdf.pullFeed();
		
		JsonParser jp = new JsonParser();
		JsonElement root = jp.parse(thread);
    	JsonObject rootobj = root.getAsJsonObject(); // may be Json Array if it's an array, or other type if a primitive
    	
    	JsonArray titles = rootobj.get("results").getAsJsonArray();
    	
    	for(int i = 0; i < titles.size(); i++)
    	{
    		System.out.println(titles.get(i).getAsJsonObject().get("section"));
    		System.out.println(titles.get(i).getAsJsonObject().get("title"));
    		String author = titles.get(i).getAsJsonObject().get("byline").toString();
    		if(!author.equals("\"\""))
    			System.out.println(author);
    		System.out.println(titles.get(i).getAsJsonObject().get("url"));
    		System.out.println("\n");
    		
    	}
	}

}
