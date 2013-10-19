package Holding;

/*     */ import com.sun.syndication.io.FeedException;
/*     */ import com.temboo.core.TembooException;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Font;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Writer;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPasswordField;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UIManager.LookAndFeelInfo;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ import twitter4j.TwitterException;
/*     */ 
/*     */ public class PasswordDialog
/*     */   implements ActionListener
/*     */ {
/*     */   JButton confirm;
/*     */   JButton register;
/*     */   JFrame guiFrame;
/*     */   Icon icon;
/*     */   Icon icon1;
/*     */   Icon icon2;
/*     */   char[] user;
/*     */   char[] correctPassword;
/*     */   File file;
/*  56 */   Writer output = null;
/*     */   String line;
/*  57 */   ArrayList<String> list = new ArrayList();
/*     */   JTextArea tracker;
/*     */   String inputUser;
/*     */   String inputPass;
/*  60 */   JTextField username = new JTextField();
/*  61 */   JPasswordField passwordFld = new JPasswordField();
/*     */   JFrame tempframe;
/*     */ 
/*     */   public PasswordDialog(JFrame frame)
/*     */   {
/*  65 */     this.icon = new ImageIcon(getClass().getResource("images/fire.png"));
/*  66 */     this.icon1 = new ImageIcon(getClass().getResource("images/info.png"));
/*  67 */     this.icon2 = new ImageIcon(getClass().getResource("images/check.png"));
/*  68 */     this.tempframe = frame;
/*     */   }
/*     */ 
/*     */   public String createGuiFrame()
/*     */     throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException
/*     */   {
/*     */     try
/*     */     {
/*  87 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
/*  88 */         if ("Nimbus".equals(info.getName())) {
/*  89 */           UIManager.setLookAndFeel(info.getClassName());
/*     */ 
/*  92 */           break;
/*     */         }
/*     */     }
/*     */     catch (Exception e) {
/*  96 */       UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
/*     */     }
/*     */ 
/*  99 */     this.guiFrame = new JFrame();
/*     */ 
/* 101 */     this.guiFrame.setDefaultCloseOperation(3);
/* 102 */     this.guiFrame.setBackground(Color.WHITE);
/* 103 */     this.guiFrame.setTitle("Security");
/* 104 */     this.guiFrame.setSize(500, 300);
/*     */ 
/* 107 */     this.guiFrame.setLocationRelativeTo(null);
/* 108 */     this.guiFrame.setVisible(true);
/*     */ 
/* 111 */     this.tracker = new JTextArea("Password Tracker:");
/* 112 */     this.guiFrame.getContentPane().add(this.tracker);
/*     */ 
/* 115 */     JPanel userPanel = new JPanel();
/* 116 */     JLabel dis = new JLabel("MULTI-SOCIAL\n", 0);
/* 117 */     JLabel space = new JLabel();
/* 118 */     userPanel.setLayout(new GridLayout(7, 1));
/*     */ 
/* 122 */     JLabel usernameLbl = new JLabel("Username:");
/*     */ 
/* 124 */     usernameLbl.setFont(new Font("Gothic", 1, 14));
/* 125 */     usernameLbl.setForeground(Color.BLACK);
/*     */ 
/* 128 */     JLabel passwordLbl = new JLabel("Password:");
/* 129 */     passwordLbl.setFont(new Font("Gothic", 1, 14));
/* 130 */     passwordLbl.setForeground(Color.BLACK);
/*     */ 
/* 134 */     this.confirm = new JButton("Confirm");
/* 135 */     this.register = new JButton("Register");
/*     */ 
/* 137 */     dis.setFont(new Font("BankGothic Lt Bt", 1, 21));
/*     */ 
/* 139 */     userPanel.add(dis);
/*     */ 
/* 141 */     userPanel.add(usernameLbl);
/* 142 */     userPanel.add(this.username);
/*     */ 
/* 144 */     userPanel.add(passwordLbl);
/* 145 */     userPanel.add(this.passwordFld);
/* 146 */     this.confirm.addActionListener(this);
/* 147 */     this.register.addActionListener(this);
/* 148 */     userPanel.add(this.confirm);
/* 149 */     userPanel.add(this.register);
/*     */ 
/* 151 */     Object[] options = { "Help", "Configure", "Cancel" };
/* 152 */     int input = JOptionPane.showOptionDialog(this.guiFrame, userPanel, "Security", 
/* 153 */       1, -1, 
/* 154 */       null, options, null);
/*     */ 
/* 162 */     if (input == 0) {
/* 163 */       File file = new File("db.txt");
/* 164 */       File file1 = new File("dc.txt");
/* 165 */       File file2 = new File("dp.txt");
/* 166 */       file2.delete();
/* 167 */       file.delete();
/* 168 */       file1.delete();
/*     */ 
/* 170 */       JOptionPane.showMessageDialog(this.guiFrame, "\nFatal Error, Please attempt to log in AGAIN.\nThank You.", "Reset", 2, this.icon2);
/*     */     }
/* 173 */     else if (input == 1) {
/* 174 */       JOptionPane.showMessageDialog(this.guiFrame, "Feature Coming Soon", "Configure", 1, this.icon1);
/*     */     }
/*     */     else
/*     */     {
/* 178 */       this.tracker.append("\nSecurity Protocol cancelled...");
/*     */     }
/*     */ 
/* 181 */     return null;
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 189 */     if (e.getSource() == this.confirm)
/*     */     {
/*     */       try
/*     */       {
/* 193 */         getUser();
/*     */       } catch (IOException e1) {
/* 195 */         e1.printStackTrace();
/*     */       }
/* 197 */       this.user = ((String)this.list.get(0)).toCharArray();
/* 198 */       this.correctPassword = ((String)this.list.get(1)).toCharArray();
/*     */ 
/* 200 */       char[] entereduser = this.username.getText().toCharArray();
/* 201 */       char[] enteredPassword = this.passwordFld.getPassword();
/*     */ 
/* 204 */       if ((Arrays.equals(this.correctPassword, enteredPassword)) && (Arrays.equals(this.user, entereduser)))
/*     */       {
/* 206 */         this.guiFrame.dispose();
/*     */         try {
/* 208 */           for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
/* 209 */             if ("Nimbus".equals(info.getName())) {
/* 210 */               UIManager.setLookAndFeel(info.getClassName());
/*     */ 
/* 213 */               break;
/*     */             }
/*     */         }
/*     */         catch (Exception e1) {
/*     */           try {
/* 218 */             UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
/*     */           }
/*     */           catch (ClassNotFoundException|InstantiationException|IllegalAccessException|UnsupportedLookAndFeelException e2)
/*     */           {
/* 223 */             e2.printStackTrace();
/*     */           }
/*     */         }
/*     */         try
/*     */         {
/* 228 */           this.tempframe.getContentPane().add(new JTabbedPaneDemo(), "Center");
/*     */         }
/*     */         catch (TembooException|IOException|URISyntaxException|TwitterException|IllegalArgumentException|FeedException e1) {
/* 231 */           e1.printStackTrace();
/*     */         }
/* 233 */         this.tempframe.setSize(600, 600);
/* 234 */         this.tempframe.setVisible(true);
/*     */       }
/*     */       else {
/* 237 */         JOptionPane.showMessageDialog(this.guiFrame, "Please Enter a valid username and password", "Incomplete", 1, this.icon1);
/*     */       }
/*     */ 
/* 244 */       Arrays.fill(enteredPassword, '0');
/*     */     }
/*     */ 
/* 249 */     if (e.getSource() == this.register)
/* 250 */       if (getCreds() == null) {
/* 251 */         this.file = new File("dp.txt");
/* 252 */         JLabel label1 = new JLabel("Username");
/* 253 */         JLabel label2 = new JLabel("Password");
/* 254 */         JPanel panel = new JPanel();
/* 255 */         JTextField field1 = new JTextField(15);
/* 256 */         panel.add(label1);
/* 257 */         panel.add(field1);
/* 258 */         JTextField field2 = new JTextField(15);
/* 259 */         panel.add(label2);
/* 260 */         panel.add(field2);
/*     */ 
/* 263 */         int value = JOptionPane.showConfirmDialog(this.guiFrame, panel, "Registration", 2, 3, this.icon);
/* 264 */         if (value == 0) {
/* 265 */           this.inputUser = field1.getText();
/* 266 */           this.inputPass = field2.getText();
/*     */ 
/* 268 */           this.user = this.inputUser.toCharArray();
/* 269 */           this.correctPassword = this.inputPass.toCharArray();
/*     */           try {
/* 271 */             storeAccessToken();
/*     */           } catch (IOException e1) {
/* 273 */             e1.printStackTrace();
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 280 */         JOptionPane.showMessageDialog(this.guiFrame, "You are already Registered.\nPress Help for help.\nThen Register again.", "Incomplete", 1, this.icon1);
/*     */       }
/*     */   }
/*     */ 
/*     */   public void storeAccessToken()
/*     */     throws IOException
/*     */   {
/* 288 */     String u = ""; String p = "";
/* 289 */     for (int i = 0; i < this.user.length; i++) {
/* 290 */       u = u + this.user[i];
/*     */     }
/*     */ 
/* 293 */     for (int i = 0; i < this.correctPassword.length; i++) {
/* 294 */       p = p + this.correctPassword[i];
/*     */     }
/* 296 */     this.output = new BufferedWriter(new FileWriter(this.file));
/* 297 */     this.output.write(u + "\r\n");
/* 298 */     this.output.write(p + "\r\n");
/*     */ 
/* 300 */     this.output.close();
/* 301 */     System.out.println("Username && Password stored");
/*     */   }
/*     */ 
/*     */   public String getCreds()
/*     */   {
/*     */     try {
/* 307 */       FileInputStream fstream = new FileInputStream("dp.txt");
/* 308 */       DataInputStream in = new DataInputStream(fstream);
/* 309 */       BufferedReader br = new BufferedReader(new InputStreamReader(in));
/*     */ 
/* 315 */       return "Yay";
/*     */     } catch (Exception e) {
/* 317 */       System.err.println("Error: " + e.getMessage());
/* 318 */     }return null;
/*     */   }
/*     */ 
/*     */   public void getUser() throws IOException
/*     */   {
/*     */     try {
/* 324 */       FileInputStream fstream = new FileInputStream("dp.txt");
/* 325 */       DataInputStream in = new DataInputStream(fstream);
/* 326 */       BufferedReader br = new BufferedReader(new InputStreamReader(in));
/*     */ 
/* 328 */       while ((this.line = br.readLine()) != null)
/* 329 */         this.list.add(this.line);
/*     */     }
/*     */     catch (FileNotFoundException e) {
/* 332 */       e.printStackTrace();
/* 333 */       JOptionPane.showMessageDialog(this.guiFrame, "Incorrect Credentials", "Incomplete", 1);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Jeremy\Desktop\SocialConglomerate13.jar
 * Qualified Name:     PasswordDialog
 * JD-Core Version:    0.6.2
 */