

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
 

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import org.jdom.Document;

public class TheOnionFeed {
	
	public TheOnionFeed(){}
	
	
	public String refresh() throws IOException, IllegalArgumentException, FeedException
	{
		URL url = new URL("http://feeds.feedburner.com/OnionNewsNetwork");
		
		XmlReader reader = new XmlReader(url);
		HttpURLConnection httpcon = (HttpURLConnection)url.openConnection();
        // Reading the feed
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed asdf = input.build(new XmlReader(httpcon));
		
        List entries = asdf.getEntries();
        Iterator itEntries = entries.iterator();
        
        String feed = "";
        
        while (itEntries.hasNext()) {
            SyndEntry entry = (SyndEntry) itEntries.next();
            feed += entry.getTitle() + "\n";
            if(!entry.getAuthor().equals(""))
            	feed += entry.getAuthor() + "\n";
            feed += entry.getPublishedDate() + "\n";
            feed += entry.getLink() + "\n\n";
        }
	   // System.out.println("Feed Title: "+ feed.getAuthor());
		
		return feed;
	}

}
