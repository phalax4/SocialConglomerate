package Holding;

/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import com.temboo.Library.Facebook.OAuth.FinalizeOAuth;
/*     */ import com.temboo.Library.Facebook.OAuth.FinalizeOAuth.FinalizeOAuthInputSet;
/*     */ import com.temboo.Library.Facebook.OAuth.FinalizeOAuth.FinalizeOAuthResultSet;
/*     */ import com.temboo.Library.Facebook.OAuth.InitializeOAuth;
/*     */ import com.temboo.Library.Facebook.OAuth.InitializeOAuth.InitializeOAuthInputSet;
/*     */ import com.temboo.Library.Facebook.OAuth.InitializeOAuth.InitializeOAuthResultSet;
/*     */ import com.temboo.Library.Facebook.Publishing.SetStatus;
/*     */ import com.temboo.Library.Facebook.Publishing.SetStatus.SetStatusInputSet;
/*     */ import com.temboo.Library.Facebook.Publishing.SetStatus.SetStatusResultSet;
/*     */ import com.temboo.Library.Facebook.Reading.NewsFeed;
/*     */ import com.temboo.Library.Facebook.Reading.NewsFeed.NewsFeedInputSet;
/*     */ import com.temboo.Library.Facebook.Reading.NewsFeed.NewsFeedResultSet;
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
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JOptionPane;
/*     */ import twitter4j.TwitterException;
/*     */ 
/*     */ public class AutoLogTest1
/*     */ {
/*     */   private TembooSession session;
/*     */   private FinalizeOAuth.FinalizeOAuthResultSet finalizeOAuthResults;
/*     */   String fbAccessToken;
/*     */   Icon icon;
/*     */   private String message;
/*     */   String line;
/*  49 */   Writer output = null; ArrayList<String> list = new ArrayList();
/*     */   File file;
/*  51 */   JFrame mark = new JFrame();
/*     */ 
/*  53 */   public AutoLogTest1() throws TembooException, IOException, URISyntaxException { this.session = new TembooSession("jayos", "myFirstApp", "d23787cb-8d8f-4c83-a"); }
/*     */ 
/*     */ 
/*     */   public String check()
/*     */     throws TwitterException, IOException, URISyntaxException, TembooException
/*     */   {
/*  60 */     if (getCreds() == null)
/*  61 */       token();
/*     */     else {
/*  63 */       return refresh(this.list);
/*     */     }
/*  65 */     return null;
/*     */   }
/*     */ 
/*     */   public String getCreds()
/*     */   {
/*     */     try {
/*  71 */       FileInputStream fstream = new FileInputStream("dc.txt");
/*  72 */       DataInputStream in = new DataInputStream(fstream);
/*  73 */       BufferedReader br = new BufferedReader(new InputStreamReader(in));
/*     */ 
/*  75 */       while ((this.line = br.readLine()) != null) {
/*  76 */         this.list.add(this.line);
/*     */       }
/*     */ 
/*  79 */       return "Yay";
/*     */     } catch (Exception e) {
/*  81 */       System.err.println("Error: " + e.getMessage());
/*  82 */     }return null;
/*     */   }
/*     */ 
/*     */   public void token() throws TembooException, IOException
/*     */   {
/*  87 */     this.file = new File("dc.txt");
/*     */ 
/*  90 */     InitializeOAuth initializeOAuthChoreo = new InitializeOAuth(this.session);
/*     */ 
/*  92 */     InitializeOAuth.InitializeOAuthInputSet initializeOAuthInputs = initializeOAuthChoreo.newInputSet();
/*     */ 
/*  94 */     initializeOAuthInputs.set_AppID("395494320570728");
/*  95 */     initializeOAuthInputs.set_Scope("read_stream,publish_actions");
/*     */ 
/*  97 */     InitializeOAuth.InitializeOAuthResultSet initializeOAuthResults = initializeOAuthChoreo.execute(initializeOAuthInputs);
/*  98 */     String callbackID = initializeOAuthResults.get_CallbackID().trim();
/*     */ 
/* 101 */     JOptionPane.showMessageDialog(this.mark, "Opening URL's. Please Wait...", "Initializing", 1, this.icon);
/*     */ 
/* 105 */     openBrowser(initializeOAuthResults.get_AuthorizationURL());
/*     */ 
/* 107 */     FinalizeOAuth finalizeOAuthChoreo = new FinalizeOAuth(this.session);
/* 108 */     FinalizeOAuth.FinalizeOAuthInputSet finalizeOAuthInputs = finalizeOAuthChoreo.newInputSet();
/* 109 */     finalizeOAuthInputs.set_CallbackID(callbackID);
/* 110 */     finalizeOAuthInputs.set_AppSecret("7c874744ce5b9cfdf94fd330441ab8eb");
/* 111 */     finalizeOAuthInputs.set_AppID("395494320570728");
/* 112 */     FinalizeOAuth.FinalizeOAuthResultSet finalizeOAuthResults = finalizeOAuthChoreo.execute(finalizeOAuthInputs);
/* 113 */     this.fbAccessToken = finalizeOAuthResults.get_AccessToken();
/* 114 */     storeAccessToken();
/*     */ 
/* 116 */     getCreds();
/* 117 */     refresh(this.list);
/*     */   }
/*     */ 
/*     */   public void storeAccessToken()
/*     */     throws IOException
/*     */   {
/* 124 */     this.output = new BufferedWriter(new FileWriter(this.file));
/* 125 */     this.output.write(this.fbAccessToken + "\r\n");
/* 126 */     this.output.flush();
/* 127 */     this.output.close();
/*     */   }
/*     */ 
/*     */   public void post(String status)
/*     */     throws TembooException
/*     */   {
/* 138 */     SetStatus setStatusChoreo = new SetStatus(this.session);
/* 139 */     SetStatus.SetStatusInputSet setStatusInputs = setStatusChoreo.newInputSet();
/* 140 */     setStatusInputs.set_AccessToken((String)this.list.get(0));
/* 141 */     setStatusInputs.set_Message(status);
/* 142 */     SetStatus.SetStatusResultSet setStatusResults = setStatusChoreo.execute(setStatusInputs);
/*     */   }
/*     */ 
/*     */   public String refresh(ArrayList<String> list1) throws TembooException
/*     */   {
/* 147 */     String f = "";
/*     */ 
/* 149 */     NewsFeed newsFeedChoreo = new NewsFeed(this.session);
/* 150 */     NewsFeed.NewsFeedInputSet newsFeedInputs = newsFeedChoreo.newInputSet();
/*     */ 
/* 154 */     newsFeedInputs.set_AccessToken((String)list1.get(0));
/*     */ 
/* 156 */     NewsFeed.NewsFeedResultSet newsFeedResults = newsFeedChoreo.execute(newsFeedInputs);
/* 157 */     String json = newsFeedResults.get_Response();
/*     */ 
/* 159 */     JsonParser jp = new JsonParser();
/* 160 */     JsonElement root = jp.parse(json);
/* 161 */     JsonObject rootobj = root.getAsJsonObject();
/*     */ 
/* 163 */     JsonArray feed = rootobj.get("data").getAsJsonArray();
/*     */ 
/* 165 */     for (int i = 0; i < feed.size(); i++) {
/* 166 */       String name = feed.get(i).getAsJsonObject().get("from").getAsJsonObject().get("name").getAsString();
/* 167 */       JsonElement msg = feed.get(i).getAsJsonObject().get("message");
/*     */ 
/* 169 */       String pict = "";
/* 170 */       if (msg != null)
/*     */       {
/* 174 */         JsonElement pic = feed.get(i).getAsJsonObject().get("picture");
/*     */ 
/* 176 */         if (pic != null) {
/* 177 */           pict = pic.getAsString();
/* 178 */           f = f + name + "\n" + msg + "\n" + pict + "\n\n";
/*     */         }
/*     */         else {
/* 181 */           f = f + name + "\n" + msg + "\n\n";
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 186 */     return f;
/*     */   }
/*     */ 
/*     */   public void openBrowser(String myUrl) {
/*     */     try {
/* 191 */       Desktop desktop = Desktop.getDesktop();
/* 192 */       URI oURL = new URI(myUrl);
/* 193 */       desktop.browse(oURL);
/*     */     } catch (Exception e) {
/* 195 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Jeremy\Desktop\SocialConglomerate13.jar
 * Qualified Name:     AutoLogTest1
 * JD-Core Version:    0.6.2
 */