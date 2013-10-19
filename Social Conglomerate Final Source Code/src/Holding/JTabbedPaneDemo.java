package Holding;

/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import com.sun.syndication.io.FeedException;
/*     */ import com.temboo.core.TembooException;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.JTextPane;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UIManager.LookAndFeelInfo;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ import twitter4j.TwitterException;
/*     */ 
/*     */ public class JTabbedPaneDemo extends JPanel
/*     */   implements ActionListener
/*     */ {
/*     */   JButton b1;
/*     */   JButton b2;
/*     */   JButton b3;
/*     */   JButton bcity;
/*     */   JButton bweather;
/*     */   ImageIcon icon;
/*     */   ImageIcon icon1;
/*     */   ImageIcon icon2;
/*     */   ImageIcon icon3;
/*     */   ImageIcon icon5;
/*     */   ImageIcon icon4;
/*  40 */   JTextPane mary = new JTextPane();
/*     */   JTabbedPane jtbExample;
/*     */   JLabel lb1;
/*     */   JLabel lb2;
/*     */   JLabel lb3;
/*     */   JLabel lb4;
/*     */   JLabel twitter;
/*     */   JLabel facebook;
/*     */   JLabel lb5;
/*     */   JLabel city;
/*     */   JLabel state;
/*     */   JLabel weather;
/*     */   JLabel posting;
/*  43 */   JPanel p = new JPanel();
/*     */   String input;
/*     */   String input1;
/*     */   String text1;
/*     */   String text2;
/*     */   String text3;
/*     */   JTextField t;
/*     */   JTextField t1;
/*     */   JTextField icity;
/*     */   JTextField istate;
/*     */   JPanel jplInnerPanel1;
/*     */   JPanel jplInnerPanel2;
/*     */   JPanel jplInnerPanel3;
/*     */   JPanel jplInnerPanel4;
/*     */   JPanel jplInnerPanel5;
/*     */   JPanel jplInnerPanel6;
/*     */   JTextArea textArea;
/*     */   static JFrame frame;
/*  50 */   int count = 0;
/*     */   String s;
/*     */   String c;
/*  52 */   Tweet1 dave = new Tweet1();
/*  53 */   AutoLogTest1 bob = new AutoLogTest1();
/*  54 */   NYTRunner1 sally = new NYTRunner1();
/*  55 */   TheOnionFeed crystal = new TheOnionFeed();
/*     */ 
/*     */   public JTabbedPaneDemo()
/*     */     throws TembooException, IOException, URISyntaxException, TwitterException, IllegalArgumentException, FeedException
/*     */   {
/*  62 */     this.b1 = new JButton("Refresh");
/*  63 */     this.b2 = new JButton("Post");
/*  64 */     this.b3 = new JButton("Send");
/*  65 */     this.bweather = new JButton("Enter");
/*  66 */     this.b1.setBackground(Color.WHITE);
/*  67 */     this.b1.setFont(new Font("Gothic", 1, 12));
/*  68 */     this.mary.setEditable(false);
/*     */ 
/*  70 */     demo();
/*     */   }
/*     */ 
/*     */   public void demo()
/*     */     throws TembooException, IOException, URISyntaxException, TwitterException, IllegalArgumentException, FeedException
/*     */   {
/*  77 */     this.jtbExample = new JTabbedPane();
/*     */ 
/*  79 */     this.icon5 = new ImageIcon(getClass().getResource("images/f.png"));
/*  80 */     this.icon1 = new ImageIcon(getClass().getResource("images/t.png"));
/*  81 */     this.icon2 = new ImageIcon(getClass().getResource("images/n.png"));
/*  82 */     this.icon3 = new ImageIcon(getClass().getResource("images/sy.png"));
/*  83 */     this.icon4 = new ImageIcon(getClass().getResource("images/onion.png"));
/*  84 */     this.lb1 = new JLabel("Facebook");
/*  85 */     this.lb2 = new JLabel("Twitter");
/*  86 */     this.lb3 = new JLabel("NYtimes");
/*  87 */     this.lb4 = new JLabel("Interact");
/*  88 */     this.lb5 = new JLabel("theOnion");
/*  89 */     this.posting = new JLabel("Posting", 0);
/*  90 */     this.weather = new JLabel("Weather");
/*     */ 
/*  93 */     this.jplInnerPanel1 = createInnerPanel(this.bob.check());
/*  94 */     this.jplInnerPanel2 = createInnerPanel(this.dave.check());
/*     */ 
/*  96 */     this.jplInnerPanel3 = createInnerPanel(this.sally.refresh());
/*     */ 
/*  98 */     this.jplInnerPanel6 = createInnerPanel(this.crystal.refresh());
/*     */ 
/* 110 */     this.jtbExample.addTab("Facebook", this.icon5, this.jplInnerPanel1);
/* 111 */     this.jtbExample.addTab("Twitter", this.icon1, this.jplInnerPanel2);
/* 112 */     this.jtbExample.addTab("News", this.icon2, this.jplInnerPanel3);
/*     */ 
/* 114 */     this.jtbExample.addTab("theOnion", this.icon4, this.jplInnerPanel6);
/* 115 */     this.jtbExample.addTab("Interact", this.icon3, this.p);
/*     */ 
/* 117 */     this.jtbExample.addTab("Refresh", this.icon3, this.jplInnerPanel4);
/*     */ 
/* 119 */     addIcon(this.lb1, this.icon5, 0);
/* 120 */     addIcon(this.lb2, this.icon1, 1);
/* 121 */     addIcon(this.lb3, this.icon2, 2);
/* 122 */     addIcon(this.lb5, this.icon4, 3);
/* 123 */     addIcon(this.lb4, this.icon3, 4);
/*     */ 
/* 125 */     this.jtbExample.setTabComponentAt(5, this.b1);
/* 126 */     this.twitter = new JLabel("Twitter:");
/* 127 */     this.facebook = new JLabel("Facebook:");
/* 128 */     this.t = new JTextField(15);
/* 129 */     this.t1 = new JTextField(15);
/* 130 */     this.city = new JLabel("City:  ");
/* 131 */     this.state = new JLabel("State:  ");
/* 132 */     this.istate = new JTextField(15);
/* 133 */     this.icity = new JTextField(15);
/*     */ 
/* 136 */     this.p.setBackground(Color.WHITE);
/* 137 */     this.p.setLayout(null);
/* 138 */     this.posting.setFont(new Font("Gothic", 1, 16));
/* 139 */     this.weather.setFont(new Font("Gothic", 1, 16));
/*     */ 
/* 141 */     this.twitter.setFont(new Font("Gothic", 0, 14));
/* 142 */     this.facebook.setFont(new Font("Gothic", 0, 14));
/*     */ 
/* 144 */     this.posting.setBounds(250, 5, 80, 20);
/* 145 */     this.weather.setBounds(250, 130, 80, 20);
/*     */ 
/* 147 */     this.twitter.setBounds(10, 40, 80, 20);
/* 148 */     this.t.setBounds(60, 40, 160, 25);
/* 149 */     this.b2.setBounds(225, 40, 80, 25);
/* 150 */     this.facebook.setBounds(10, 80, 80, 20);
/* 151 */     this.t1.setBounds(80, 80, 160, 25);
/* 152 */     this.b3.setBounds(245, 80, 80, 25);
/*     */ 
/* 154 */     this.p.add(this.posting);
/* 155 */     this.p.add(this.twitter);
/* 156 */     this.p.add(this.t);
/* 157 */     this.p.add(this.b2);
/* 158 */     this.p.add(this.facebook);
/* 159 */     this.p.add(this.t1);
/* 160 */     this.p.add(this.b3);
/*     */ 
/* 162 */     this.city.setBounds(10, 160, 80, 20);
/* 163 */     this.icity.setBounds(40, 160, 160, 25);
/* 164 */     this.state.setBounds(10, 200, 80, 20);
/* 165 */     this.istate.setBounds(45, 200, 160, 25);
/* 166 */     this.bweather.setBounds(60, 235, 80, 25);
/*     */ 
/* 168 */     this.p.add(this.weather);
/* 169 */     this.p.add(this.city);
/* 170 */     this.p.add(this.icity);
/* 171 */     this.p.add(this.state);
/* 172 */     this.p.add(this.istate);
/* 173 */     this.p.add(this.bweather);
/*     */ 
/* 175 */     this.b1.addActionListener(this);
/* 176 */     this.b2.addActionListener(this);
/* 177 */     this.b3.addActionListener(this);
/* 178 */     this.bweather.addActionListener(this);
/* 179 */     setLayout(new GridLayout(1, 1));
/* 180 */     add(this.jtbExample);
/*     */   }
/*     */ 
/*     */   protected JPanel createInnerPanel(String text)
/*     */   {
/* 185 */     JPanel jplPanel = new JPanel();
/* 186 */     this.textArea = new JTextArea();
/*     */ 
/* 189 */     JScrollPane scrollPane = new JScrollPane(this.textArea);
/* 190 */     this.textArea.setText(text);
/*     */ 
/* 192 */     jplPanel.setLayout(new GridLayout(1, 1));
/* 193 */     jplPanel.add(scrollPane);
/*     */ 
/* 195 */     return jplPanel;
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 202 */     if (e.getSource() == this.b1)
/*     */     {
/*     */       try
/*     */       {
/* 207 */         this.jtbExample.remove(0);
/* 208 */         this.jtbExample.remove(0);
/* 209 */         this.jtbExample.remove(0);
/*     */ 
/* 211 */         this.jtbExample.remove(0);
/* 212 */         this.jtbExample.remove(0);
/* 213 */         this.jtbExample.remove(0);
/*     */ 
/* 215 */         this.jplInnerPanel1 = createInnerPanel(this.bob.check());
/* 216 */         this.jtbExample.addTab("Facebook", this.icon, this.jplInnerPanel1);
/*     */ 
/* 218 */         this.jplInnerPanel2 = createInnerPanel(this.dave.check());
/* 219 */         this.jtbExample.addTab("Twitter", this.icon, this.jplInnerPanel2);
/*     */ 
/* 221 */         this.jplInnerPanel3 = createInnerPanel(this.sally.refresh());
/* 222 */         this.jtbExample.addTab("news", this.icon, this.jplInnerPanel3);
/*     */ 
/* 224 */         this.jplInnerPanel6 = createInnerPanel(this.crystal.refresh());
/* 225 */         this.jtbExample.addTab("theOnion", this.icon4, this.jplInnerPanel6);
/*     */ 
/* 227 */         this.jtbExample.addTab("Interact", this.icon3, this.p);
/*     */ 
/* 229 */         this.jplInnerPanel4 = createInnerPanel("");
/* 230 */         this.jtbExample.addTab("", this.icon, this.jplInnerPanel4);
/*     */ 
/* 232 */         addIcon(this.lb1, this.icon5, 0);
/* 233 */         addIcon(this.lb2, this.icon1, 1);
/* 234 */         addIcon(this.lb3, this.icon2, 2);
/* 235 */         addIcon(this.lb5, this.icon4, 3);
/* 236 */         addIcon(this.lb4, this.icon3, 4);
/*     */ 
/* 238 */         this.posting.setBounds(250, 5, 80, 20);
/* 239 */         this.weather.setBounds(250, 130, 80, 20);
/*     */ 
/* 241 */         this.twitter.setBounds(10, 40, 80, 20);
/* 242 */         this.t.setBounds(60, 40, 160, 25);
/* 243 */         this.b2.setBounds(225, 40, 80, 25);
/* 244 */         this.facebook.setBounds(10, 80, 80, 20);
/* 245 */         this.t1.setBounds(80, 80, 160, 25);
/* 246 */         this.b3.setBounds(245, 80, 80, 25);
/*     */ 
/* 248 */         this.p.add(this.posting);
/* 249 */         this.p.add(this.twitter);
/* 250 */         this.p.add(this.t);
/* 251 */         this.p.add(this.b2);
/* 252 */         this.p.add(this.facebook);
/* 253 */         this.p.add(this.t1);
/* 254 */         this.p.add(this.b3);
/*     */ 
/* 256 */         this.city.setBounds(10, 160, 80, 20);
/* 257 */         this.icity.setBounds(40, 160, 160, 25);
/* 258 */         this.state.setBounds(10, 200, 80, 20);
/* 259 */         this.istate.setBounds(45, 200, 160, 25);
/* 260 */         this.bweather.setBounds(60, 235, 80, 25);
/*     */ 
/* 262 */         this.p.add(this.weather);
/* 263 */         this.p.add(this.city);
/* 264 */         this.p.add(this.icity);
/* 265 */         this.p.add(this.state);
/* 266 */         this.p.add(this.istate);
/* 267 */         this.p.add(this.bweather);
/* 268 */         this.jtbExample.setTabComponentAt(5, this.b1);
/*     */       }
/*     */       catch (TwitterException|IOException|URISyntaxException|TembooException|IllegalArgumentException|FeedException e1) {
/* 271 */         e1.printStackTrace();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 277 */     if (e.getSource() == this.b2)
/*     */     {
/* 279 */       this.input = this.t.getText();
/*     */       try
/*     */       {
/* 283 */         this.dave.post(this.input); } catch (TembooException e1) {
/* 284 */         e1.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 288 */     if (e.getSource() == this.b3) { this.input1 = this.t1.getText();
/*     */       try { this.bob.post(this.input1);
/*     */       } catch (TembooException e1) {
/* 291 */         e1.printStackTrace();
/*     */       }
/*     */     }
/* 294 */     if (e.getSource() == this.bweather)
/*     */     {
/* 296 */       this.s = this.istate.getText();
/* 297 */       this.c = this.icity.getText();
/*     */       try {
/*     */         try {
/* 300 */           for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
/* 301 */             if ("Nimbus".equals(info.getName())) {
/* 302 */               UIManager.setLookAndFeel(info.getClassName());
/*     */ 
/* 305 */               break;
/*     */             }
/*     */         }
/*     */         catch (Exception e1) {
/*     */           try {
/* 310 */             UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
/*     */           }
/*     */           catch (ClassNotFoundException|InstantiationException|IllegalAccessException|UnsupportedLookAndFeelException e2)
/*     */           {
/* 314 */             e2.printStackTrace();
/*     */           }
/*     */         }
/* 317 */         JOptionPane.showMessageDialog(frame, getW(this.s, this.c), "Weather", 1);
/*     */       }
/*     */       catch (IOException e1) {
/* 320 */         e1.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getW(String state, String city)
/*     */     throws IOException
/*     */   {
/* 329 */     String J = "";
/* 330 */     String web = "http://api.wunderground.com/api/4ebc3b3ae2c41145/geolookup/conditions/forecast/q/" + state + "/" + city + ".json";
/*     */ 
/* 333 */     URL oracle = new URL(web);
/* 334 */     URLConnection yc = oracle.openConnection();
/* 335 */     BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
/*     */     String inputLine;
/* 338 */     while ((inputLine = in.readLine()) != null)
/*     */     {
/*     */       //String inputLine;
/* 339 */       JsonParser jp = new JsonParser();
/* 340 */       JsonElement root = jp.parse(in);
/* 341 */       JsonObject rootobj = root.getAsJsonObject();
/*     */ 
/* 343 */       String forecast = rootobj.get("forecast").getAsJsonObject().get("txt_forecast").getAsJsonObject().get("forecastday").getAsJsonArray().get(0).getAsJsonObject().get("fcttext").getAsString();
/*     */ 
/* 346 */       J = forecast;
/*     */     }
/* 348 */     return J;
/*     */   }
/*     */ 
/*     */   private void addIcon(JLabel label, ImageIcon icon, int index)
/*     */   {
/* 353 */     label.setIcon(icon);
/* 354 */     label.setIconTextGap(5);
/* 355 */     label.setHorizontalTextPosition(4);
/* 356 */     this.jtbExample.setTabComponentAt(index, label);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */     throws TembooException, IOException, URISyntaxException, TwitterException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
/*     */   {
/* 365 */     frame = new JFrame("Multi-Social");
/*     */ 
/* 367 */     PasswordDialog carl = new PasswordDialog(frame);
/* 368 */     carl.createGuiFrame();
/*     */ 
/* 370 */     frame.setDefaultCloseOperation(3);
/*     */   }
/*     */ }

/* Location:           C:\Users\Jeremy\Desktop\SocialConglomerate13.jar
 * Qualified Name:     JTabbedPaneDemo
 * JD-Core Version:    0.6.2
 */