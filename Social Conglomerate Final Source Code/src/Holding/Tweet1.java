package Holding;

/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import com.temboo.Library.Twitter.Timelines.HomeTimeline;
/*     */ import com.temboo.Library.Twitter.Timelines.HomeTimeline.HomeTimelineInputSet;
/*     */ import com.temboo.Library.Twitter.Timelines.HomeTimeline.HomeTimelineResultSet;
/*     */ import com.temboo.Library.Twitter.Tweets.StatusesUpdate;
/*     */ import com.temboo.Library.Twitter.Tweets.StatusesUpdate.StatusesUpdateInputSet;
/*     */ import com.temboo.Library.Twitter.Tweets.StatusesUpdate.StatusesUpdateResultSet;
/*     */ import com.temboo.core.TembooException;
/*     */ import com.temboo.core.TembooSession;
/*     */ import java.awt.Desktop;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Writer;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JOptionPane;
/*     */ import twitter4j.Twitter;
/*     */ import twitter4j.TwitterException;
/*     */ import twitter4j.TwitterFactory;
/*     */ import twitter4j.auth.AccessToken;
/*     */ import twitter4j.auth.RequestToken;
/*     */ 
/*     */ public class Tweet1
/*     */ {
/*     */   String sub;
/*     */   String pin;
/*     */   String token;
/*     */   String tokenSecret;
/*     */   long name;
/*     */   String line;
/*  46 */   Gson gson = new Gson();
/*     */   String au;
/*     */   int var1;
/*     */   int var2;
/*     */   int var3;
/*     */   int var4;
/*     */   String msg;
/*     */   private static final String CONSUMER_KEY = "hyL303lpgZpSt6cMmilBw";
/*     */   private static final String CONSUMER_KEY_SECRET = "EqgkdjEPuhP4KyVm3PEV926YuDrPcZAG249FxwXE9Q";
/*     */   private static final String APP_KEY_NAME = "myFirstApp";
/*     */   private static final String APP_KEY_VALUE = "231248aa-6da1-4f21-8";
/*     */   File file;
/*  52 */   Writer output = null;
/*  53 */   Twitter twitter = new TwitterFactory().getInstance();
/*  54 */   ArrayList<String> list = new ArrayList();
/*  55 */   ArrayList<String> stringList = new ArrayList();
/*     */   String getter;
/*     */   TembooSession session;
/*     */ 
/*     */   public String check()
/*     */     throws TwitterException, IOException, URISyntaxException, TembooException
/*     */   {
/*  61 */     if (getCreds() == null)
/*  62 */       start();
/*     */     else {
/*  64 */       return refresh(this.list);
/*     */     }
/*  66 */     return null;
/*     */   }
/*     */ 
/*     */   public void start()
/*     */     throws TwitterException, IOException, URISyntaxException, TembooException
/*     */   {
/*  72 */     this.file = new File("db.txt");
/*  73 */     this.twitter.setOAuthConsumer("hyL303lpgZpSt6cMmilBw", "EqgkdjEPuhP4KyVm3PEV926YuDrPcZAG249FxwXE9Q");
/*  74 */     RequestToken requestToken = this.twitter.getOAuthRequestToken();
/*  75 */     System.out.println("Going to " + requestToken.getAuthorizationURL());
/*  76 */     String web = requestToken.getAuthorizationURL();
/*  77 */     openBrowser(web);
/*  78 */     AccessToken accessToken = null;
/*     */ 
/*  80 */     while (accessToken == null) {
/*     */       try
/*     */       {
/*  83 */         this.pin = JOptionPane.showInputDialog(null, "Input Pin: ");
/*     */ 
/*  85 */         accessToken = this.twitter.getOAuthAccessToken(requestToken, this.pin);
/*     */       }
/*     */       catch (TwitterException te)
/*     */       {
/*  89 */         System.out.println("Failed to get access token, caused by: " + 
/*  90 */           te.getMessage());
/*     */ 
/*  92 */         System.out.println("Pin Error");
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  99 */     this.token = accessToken.getToken();
/* 100 */     this.tokenSecret = accessToken.getTokenSecret();
/* 101 */     this.name = this.twitter.getId();
/* 102 */     storeAccessToken();
/* 103 */     getCreds();
/* 104 */     refresh(this.list);
/*     */   }
/*     */ 
/*     */   public String post(String text)
/*     */     throws TembooException
/*     */   {
/* 112 */     this.session = new TembooSession("phalax4", "myFirstApp", "231248aa-6da1-4f21-8");
/* 113 */     StatusesUpdate statusesUpdateChoreo = new StatusesUpdate(this.session);
/* 114 */     StatusesUpdate.StatusesUpdateInputSet statusesUpdateInputs = statusesUpdateChoreo.newInputSet();
/*     */ 
/* 116 */     statusesUpdateInputs.set_AccessToken((String)this.list.get(0));
/* 117 */     statusesUpdateInputs.set_AccessTokenSecret((String)this.list.get(1));
/* 118 */     statusesUpdateInputs.set_ConsumerSecret("EqgkdjEPuhP4KyVm3PEV926YuDrPcZAG249FxwXE9Q");
/* 119 */     statusesUpdateInputs.set_ConsumerKey("hyL303lpgZpSt6cMmilBw");
/* 120 */     statusesUpdateInputs.set_StatusUpdate(text);
/*     */ 
/* 122 */     StatusesUpdate.StatusesUpdateResultSet statusesUpdateResults = statusesUpdateChoreo.execute(statusesUpdateInputs);
/*     */ 
/* 124 */     return "Posted!";
/*     */   }
/*     */ 
/*     */   public String refresh(ArrayList<String> list1) throws TembooException, IOException, TwitterException {
/* 128 */     getCreds();
/* 129 */     this.session = new TembooSession("phalax4", "myFirstApp", "231248aa-6da1-4f21-8");
/*     */ 
/* 131 */     HomeTimeline homeTimelineChoreo = new HomeTimeline(this.session);
/* 132 */     HomeTimeline.HomeTimelineInputSet homeTimelineInputs = homeTimelineChoreo.newInputSet();
/*     */ 
/* 134 */     homeTimelineInputs.set_AccessToken((String)list1.get(0));
/* 135 */     homeTimelineInputs.set_AccessTokenSecret((String)list1.get(1));
/* 136 */     homeTimelineInputs.set_ConsumerSecret("EqgkdjEPuhP4KyVm3PEV926YuDrPcZAG249FxwXE9Q");
/* 137 */     homeTimelineInputs.set_ConsumerKey("hyL303lpgZpSt6cMmilBw");
/*     */ 
/* 139 */     HomeTimeline.HomeTimelineResultSet homeTimelineResults = homeTimelineChoreo.execute(homeTimelineInputs);
/*     */ 
/* 148 */     JsonParser jp = new JsonParser();
/* 149 */     JsonElement root = jp.parse(homeTimelineResults.get_Response());
/*     */ 
/* 151 */     JsonArray statuses = root.getAsJsonArray();
/*     */ 
/* 155 */     String thread = "";
/*     */ 
/* 157 */     for (int i = 0; i < statuses.size(); i++) {
/* 158 */       JsonObject status = statuses.get(i).getAsJsonObject();
/*     */ 
/* 160 */       String text = status.get("text").getAsString();
/* 161 */       String screen_name = status.get("user").getAsJsonObject().get("name").getAsString();
/*     */ 
/* 163 */       JsonArray urls = status.get("entities").getAsJsonObject().get("urls").getAsJsonArray();
/*     */ 
/* 165 */       this.au = urls.toString();
/*     */ 
/* 167 */       this.var1 = (this.au.indexOf(":") + 1);
/* 168 */       this.var2 = (this.au.indexOf(":", this.var1) + 1);
/* 169 */       this.var3 = (this.au.indexOf(":", this.var2) + 2);
/* 170 */       this.var4 = (this.au.indexOf(",", this.var3) - 1);
/*     */ 
/* 172 */       if (this.au.charAt(this.var1) == ' ')
/* 173 */         this.sub = " ";
/* 174 */       else if ((this.var3 >= 0) && (this.var4 >= 0)) {
/* 175 */         this.sub = this.au.substring(this.var3, this.var4);
/*     */       }
/*     */       else
/*     */       {
/* 179 */         this.sub = "";
/*     */       }
/*     */ 
/* 183 */       thread = thread + screen_name + "\n" + text + "\n" + this.sub + "\n\n";
/*     */     }
/*     */ 
/* 187 */     return thread;
/*     */   }
/*     */ 
/*     */   public void storeAccessToken() throws IOException {
/* 191 */     this.output = new BufferedWriter(new FileWriter(this.file));
/* 192 */     this.output.write(this.token + "\r\n");
/* 193 */     this.output.write(this.tokenSecret + "\r\n");
/* 194 */     this.output.close();
/*     */   }
/*     */ 
/*     */   public void openBrowser(String myUrl) {
/*     */     try {
/* 199 */       Desktop desktop = Desktop.getDesktop();
/* 200 */       URI oURL = new URI(myUrl);
/* 201 */       desktop.browse(oURL);
/*     */     } catch (Exception e) {
/* 203 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getCreds()
/*     */   {
/*     */     try {
/* 210 */       FileInputStream fstream = new FileInputStream("db.txt");
/* 211 */       DataInputStream in = new DataInputStream(fstream);
/* 212 */       BufferedReader br = new BufferedReader(new InputStreamReader(in));
/*     */ 
/* 214 */       while ((this.line = br.readLine()) != null) {
/* 215 */         this.list.add(this.line);
/*     */       }
/*     */ 
/* 218 */       return "Yay";
/*     */     } catch (Exception e) {
/* 220 */       System.err.println("Error: " + e.getMessage());
/* 221 */     }return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Jeremy\Desktop\SocialConglomerate13.jar
 * Qualified Name:     Tweet1
 * JD-Core Version:    0.6.2
 */