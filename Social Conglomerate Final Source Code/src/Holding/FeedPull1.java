package Holding;

/*    */ import com.temboo.Library.NYTimes.TimesNewswire.GetRecentNewsItems;
/*    */ import com.temboo.Library.NYTimes.TimesNewswire.GetRecentNewsItems.GetRecentNewsItemsInputSet;
/*    */ import com.temboo.Library.NYTimes.TimesNewswire.GetRecentNewsItems.GetRecentNewsItemsResultSet;
/*    */ import com.temboo.core.TembooException;
/*    */ import com.temboo.core.TembooSession;
/*    */ 
/*    */ public class FeedPull1
/*    */ {
/*    */   private String API_Key;
/*    */ 
/*    */   public FeedPull1(String key)
/*    */   {
/* 14 */     this.API_Key = key;
/*    */   }
/*    */ 
/*    */   public String pullFeed()
/*    */     throws TembooException
/*    */   {
/* 20 */     TembooSession session = new TembooSession("recollect", "myFirstApp", "dea3a230-9f31-4054-8");
/*    */ 
/* 22 */     GetRecentNewsItems getRecentNewsItemsChoreo = new GetRecentNewsItems(session);
/*    */ 
/* 25 */     GetRecentNewsItems.GetRecentNewsItemsInputSet getRecentNewsItemsInputs = getRecentNewsItemsChoreo.newInputSet();
/*    */ 
/* 28 */     getRecentNewsItemsInputs.set_APIKey(this.API_Key);
/*    */ 
/* 31 */     GetRecentNewsItems.GetRecentNewsItemsResultSet getRecentNewsItemsResults = getRecentNewsItemsChoreo.execute(getRecentNewsItemsInputs);
/*    */ 
/* 33 */     return getRecentNewsItemsResults.get_Response();
/*    */   }
/*    */ }

/* Location:           C:\Users\Jeremy\Desktop\SocialConglomerate13.jar
 * Qualified Name:     FeedPull1
 * JD-Core Version:    0.6.2
 */