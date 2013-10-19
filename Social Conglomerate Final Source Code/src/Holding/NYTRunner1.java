package Holding;

/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParser;
/*    */ import com.temboo.core.TembooException;
/*    */ 
/*    */ public class NYTRunner1
/*    */ {
/*    */   String thread;
/* 14 */   FeedPull1 asdf = new FeedPull1("1e189707ebda2e84b55d989021b0fb0b:8:67890430");
/*    */ 
/*    */   public NYTRunner1() throws TembooException
/*    */   {
/*    */   }
/*    */ 
/*    */   public String refresh() throws TembooException
/*    */   {
/* 22 */     this.thread = this.asdf.pullFeed();
/* 23 */     JsonParser jp = new JsonParser();
/* 24 */     JsonElement root = jp.parse(this.thread);
/* 25 */     JsonObject rootobj = root.getAsJsonObject();
/*    */ 
/* 27 */     JsonArray titles = rootobj.get("results").getAsJsonArray();
/* 28 */     String parsedThread = "";
/* 29 */     for (int i = 0; i < titles.size(); i++)
/*    */     {
/* 31 */       parsedThread = parsedThread + titles.get(i).getAsJsonObject().get("section") + "\n";
/* 32 */       parsedThread = parsedThread + titles.get(i).getAsJsonObject().get("title") + "\n";
/* 33 */       String author = titles.get(i).getAsJsonObject().get("byline").toString();
/* 34 */       if (!author.equals("\"\""))
/* 35 */         parsedThread = parsedThread + author + "\n";
/* 36 */       parsedThread = parsedThread + titles.get(i).getAsJsonObject().get("url") + "\n\n";
/*    */     }
/*    */ 
/* 39 */     this.thread = "";
/* 40 */     return parsedThread;
/*    */   }
/*    */ }

/* Location:           C:\Users\Jeremy\Desktop\SocialConglomerate13.jar
 * Qualified Name:     NYTRunner1
 * JD-Core Version:    0.6.2
 */