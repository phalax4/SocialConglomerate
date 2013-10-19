package Holding;

/*    */ import com.sun.syndication.feed.synd.SyndEntry;
/*    */ import com.sun.syndication.feed.synd.SyndFeed;
/*    */ import com.sun.syndication.io.FeedException;
/*    */ import com.sun.syndication.io.SyndFeedInput;
/*    */ import com.sun.syndication.io.XmlReader;
/*    */ import java.io.IOException;
/*    */ import java.net.HttpURLConnection;
/*    */ import java.net.URL;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ public class TheOnionFeed
/*    */ {
/*    */   public String refresh()
/*    */     throws IOException, IllegalArgumentException, FeedException
/*    */   {
/* 26 */     URL url = new URL("http://feeds.feedburner.com/OnionNewsNetwork");
/*    */ 
/* 28 */     XmlReader reader = new XmlReader(url);
/* 29 */     HttpURLConnection httpcon = (HttpURLConnection)url.openConnection();
/*    */ 
/* 31 */     SyndFeedInput input = new SyndFeedInput();
/* 32 */     SyndFeed asdf = input.build(new XmlReader(httpcon));
/*    */ 
/* 34 */     List entries = asdf.getEntries();
/* 35 */     Iterator itEntries = entries.iterator();
/*    */ 
/* 37 */     String feed = "";
/*    */ 
/* 39 */     while (itEntries.hasNext()) {
/* 40 */       SyndEntry entry = (SyndEntry)itEntries.next();
/* 41 */       feed = feed + entry.getTitle() + "\n";
/* 42 */       if (!entry.getAuthor().equals(""))
/* 43 */         feed = feed + entry.getAuthor() + "\n";
/* 44 */       feed = feed + entry.getPublishedDate() + "\n";
/* 45 */       feed = feed + entry.getLink() + "\n\n";
/*    */     }
/*    */ 
/* 49 */     return feed;
/*    */   }
/*    */ }

/* Location:           C:\Users\Jeremy\Desktop\SocialConglomerate13.jar
 * Qualified Name:     TheOnionFeed
 * JD-Core Version:    0.6.2
 */