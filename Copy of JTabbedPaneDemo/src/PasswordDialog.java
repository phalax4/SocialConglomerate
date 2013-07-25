<<<<<<< HEAD
=======
import java.awt.BorderLayout;
>>>>>>> c4aac1efca96473293c828533bfc6fcadde15636
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
<<<<<<< HEAD
=======
import java.net.URISyntaxException;
>>>>>>> c4aac1efca96473293c828533bfc6fcadde15636
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

<<<<<<< HEAD
import com.temboo.core.TembooException;
=======
import twitter4j.TwitterException;

import com.sun.syndication.io.FeedException;
import com.temboo.core.TembooException;


>>>>>>> c4aac1efca96473293c828533bfc6fcadde15636
//timer for refresh?
//weather
//onion and CNN
//revamp refresh?
//encrypt txt files
//encrpyted chat?
//matrik screen
public class PasswordDialog implements ActionListener{
<<<<<<< HEAD
	  JButton reset,register;JFrame guiFrame;Icon icon,icon1,icon2;
=======
	  JButton confirm,register;JFrame guiFrame;Icon icon,icon1,icon2;
>>>>>>> c4aac1efca96473293c828533bfc6fcadde15636
	  char[]user; char[] correctPassword;
	  File file;Writer output = null;String line;
	  ArrayList<String> list = new ArrayList<String>();
	  JTextArea tracker;
	  String inputUser,inputPass;
<<<<<<< HEAD
	  
    public PasswordDialog (){
		icon = new ImageIcon(this.getClass().getResource("fire.png"));
		//icon1 = new ImageIcon(this.getClass().getResource("w.png"));
		icon2 = new ImageIcon(this.getClass().getResource("check.png"));
=======
	  JTextField username = new JTextField();
      JPasswordField passwordFld = new JPasswordField();
      JFrame tempframe;
	  
    public PasswordDialog (JFrame frame){
		icon = new ImageIcon(this.getClass().getResource("fire.png"));
		icon1 = new ImageIcon(this.getClass().getResource("info.png"));
		icon2 = new ImageIcon(this.getClass().getResource("check.png"));
		tempframe = frame;
>>>>>>> c4aac1efca96473293c828533bfc6fcadde15636

    }
   /* public void check() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
    	if(getCreds()!=null){
			createGuiFrame();
		}else{
			
		}
    }*/
    
    /**
     * @wbp.parser.entryPoint
     */
    public String createGuiFrame() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException
    {
    	  
    	  
    	try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
<<<<<<< HEAD
		           // UIManager.getLookAndFeelDefaults().put("Panel.background", Color.WHITE);
=======
		           // UIManager.getLookAndFeelDefaults().put("Panel.background", Color.);
>>>>>>> c4aac1efca96473293c828533bfc6fcadde15636
		           
		            break;
		        }
		    }
		} catch (Exception e) {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}
        
        guiFrame = new JFrame();
    
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setBackground(Color.WHITE);
        guiFrame.setTitle("Security");
        guiFrame.setSize(500,300);
        
  
        guiFrame.setLocationRelativeTo(null);
        guiFrame.setVisible(true);
        
    
       tracker = new JTextArea("Password Tracker:");
       guiFrame.getContentPane().add(tracker);
        
        
        JPanel userPanel = new JPanel();
<<<<<<< HEAD
     
=======
        JLabel dis = new JLabel("MULTI-SOCIAL\n",JLabel.CENTER);
        JLabel space = new JLabel();
>>>>>>> c4aac1efca96473293c828533bfc6fcadde15636
        userPanel.setLayout(new GridLayout(7,1));
       // userPanel.setLayout(GridLayout(3,3);
        
        
        JLabel usernameLbl = new JLabel("Username:");
        
        usernameLbl.setFont(new Font("Gothic", Font.BOLD,14));
        usernameLbl.setForeground(Color.BLACK);
       
        
        JLabel passwordLbl = new JLabel("Password:");
        passwordLbl.setFont(new Font("Gothic", Font.BOLD,14));
        passwordLbl.setForeground(Color.BLACK);
        
<<<<<<< HEAD
        JTextField username = new JTextField();
        JPasswordField passwordFld = new JPasswordField();
        reset = new JButton("Help");
        register = new JButton("Register");
        
       
=======
       // JTextField username = new JTextField();
        //JPasswordField passwordFld = new JPasswordField();
        confirm = new JButton("Confirm");
        register = new JButton("Register");
        //dis.setHorizontalTextPosition(JLabel.CENTER);
       dis.setFont(new Font("BankGothic Lt Bt", Font.BOLD, 21));

        userPanel.add(dis);
       // userPanel.add(space);
>>>>>>> c4aac1efca96473293c828533bfc6fcadde15636
        userPanel.add(usernameLbl);
        userPanel.add(username);
        
        userPanel.add(passwordLbl);
        userPanel.add(passwordFld);
<<<<<<< HEAD
        reset.addActionListener(this);
        register.addActionListener(this);
        userPanel.add(reset);
        userPanel.add(register);
        
        int input = JOptionPane.showConfirmDialog(guiFrame, userPanel, "Enter your password:",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.NO_OPTION,icon);
        
        
        
        if (input == 0) {
            //tracker.append("\nUsername entered was: " + username.getText());
        	getUser();
=======
        confirm.addActionListener(this);
        register.addActionListener(this);
        userPanel.add(confirm);
        userPanel.add(register);
        
        Object[] options = {"Help","Configure","Cancel"};
        int input = JOptionPane.showOptionDialog(guiFrame, userPanel, "Security",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, null);        
        

        
        //int input = JOptionPane.showConfirmDialog(guiFrame, userPanel, "Enter your password:",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.NO_OPTION,icon);
        
        
        
        if (input == JOptionPane.YES_OPTION) {
        	File file = new File("db.txt");
			File file1 = new File("dc.txt");
			File file2 = new File("dp.txt");
			file.delete();
			file1.delete();
			file2.delete();
			JOptionPane.showMessageDialog(guiFrame,"\nFatal Error, Please attempt to log in AGAIN."+"\nThank You.","Reset",JOptionPane.WARNING_MESSAGE,icon2);
			createGuiFrame();
        }
        else if(input ==JOptionPane.NO_OPTION){
			JOptionPane.showMessageDialog(guiFrame,"Feature Coming Soon","Configure",JOptionPane.INFORMATION_MESSAGE,icon1);

                        
        }else{
            tracker.append("\nSecurity Protocol cancelled...");

        }
		return null;
    
    }



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==confirm){
			
			
			try {
				getUser();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
>>>>>>> c4aac1efca96473293c828533bfc6fcadde15636
        	user = list.get(0).toCharArray();
        	correctPassword = list.get(1).toCharArray();
           
            char[] entereduser = username.getText().toCharArray();
            char[] enteredPassword = passwordFld.getPassword();
<<<<<<< HEAD
           // tracker.append("\nPassword entered was: " + String.valueOf(enteredPassword));
=======
>>>>>>> c4aac1efca96473293c828533bfc6fcadde15636
            
            
            if ((Arrays.equals(correctPassword, enteredPassword))&&(Arrays.equals(user, entereduser)) ){
  
            	guiFrame.dispose();
<<<<<<< HEAD
                return "true";
            }
            else{
                tracker.append("\nPassword or Username is incorrect"); 
                tracker.append("\nPlease try again. Press Help for help.");
=======
            	try {
        		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        		        if ("Nimbus".equals(info.getName())) {
        		            UIManager.setLookAndFeel(info.getClassName());
        		            UIManager.getLookAndFeelDefaults().put("Panel.background", Color.BLACK);
        		           
        		            break;
        		        }
        		    }
        		} catch (Exception e1) {
        			try {
						UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
					} catch (ClassNotFoundException | InstantiationException
							| IllegalAccessException
							| UnsupportedLookAndFeelException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
        		}
            	
            	try {
					tempframe.getContentPane().add(new JTabbedPaneDemo(), BorderLayout.CENTER);
				} catch (TembooException | IOException | URISyntaxException
						| TwitterException | IllegalArgumentException | FeedException e1) {
					e1.printStackTrace();
				}
        		tempframe.setSize(600, 600);
        		tempframe.setVisible(true);
            }
            else{
    			JOptionPane.showMessageDialog(guiFrame,"Please Enter a valid username and password","Incomplete",JOptionPane.INFORMATION_MESSAGE,icon1);

               // tracker.append("\nPassword or Username is incorrect"); 
               // tracker.append("\nPlease try again. Press Help for help.");
>>>>>>> c4aac1efca96473293c828533bfc6fcadde15636
                
            }

            Arrays.fill(enteredPassword, '0');
<<<<<<< HEAD
        }
        else if(input==1){
        	
                        
        }else{
            tracker.append("\nSecurity Protocol cancelled...");

        }
		return null;
    
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==reset){
			File file = new File("db.txt");
			File file1 = new File("dc.txt");
			File file2 = new File("dp.txt");
			file.delete();
			file1.delete();
			file2.delete();
			JOptionPane.showMessageDialog(guiFrame,"Fatal Error, Please attempt to log in AGAIN."+"\nThank You.","Reset",JOptionPane.WARNING_MESSAGE,icon2);
		}
		if(e.getSource()=="No"){
			System.out.println("Hi");
		}
		
=======
			
		}
		
		
>>>>>>> c4aac1efca96473293c828533bfc6fcadde15636
		if(e.getSource()==register){
			if(getCreds()==null){
				file = new File("dp.txt");
				JLabel label1 = new JLabel("Username");
				JLabel label2 = new JLabel("Password");
				JPanel panel = new JPanel();  
				JTextField field1 = new JTextField(15);  
				panel.add(label1);
				panel.add(field1);  
				JTextField field2 = new JTextField(15); 
				panel.add(label2);
				panel.add(field2);  
				
				
				int value = JOptionPane.showConfirmDialog(guiFrame, panel, "Registration", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,icon);  
				if (value == JOptionPane.OK_OPTION){  
				   inputUser = field1.getText();  
				   inputPass = field2.getText();  
				   
				    user = inputUser.toCharArray();
				    correctPassword = inputPass.toCharArray();
				    try {
						storeAccessToken();
					} catch (IOException e1) {
<<<<<<< HEAD
						// TODO Auto-generated catch block
=======
>>>>>>> c4aac1efca96473293c828533bfc6fcadde15636
						e1.printStackTrace();
					}
				}  
				
			}else{
<<<<<<< HEAD
				tracker.append("You are already registered. We Only support one user."
						+ "\nPlease press help for more details.Then Press Register");
=======
				//tracker.append("You are already registered. We Only support one user."
						//+ "\nPlease press help for more details.Then Press Register");
    			JOptionPane.showMessageDialog(guiFrame,"You are already Registered.\nPress Help for help.\nThen Register again.","Incomplete",JOptionPane.INFORMATION_MESSAGE,icon1);

>>>>>>> c4aac1efca96473293c828533bfc6fcadde15636
			}
		}

	}
	
	public void storeAccessToken() throws IOException{
		String u = "";String p = "";
		for(int i=0;i<user.length;i++){
			u+=user[i];
		}
		
		for(int i=0;i<correctPassword.length;i++){
			p+=correctPassword[i];
		}
		output = new BufferedWriter(new FileWriter(file));
		output.write(u+"\r\n");
		output.write(p+"\r\n"
				);
		output.close();
		System.out.println("Username && Password stored");
	}
	
	public String getCreds(){

		try{
			FileInputStream fstream = new FileInputStream("dp.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			/*while ((line = br.readLine()) != null)   {
				list.add(line);
			}	*/		
			
			return "Yay";
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
			return null;
		}
	}
	public void getUser() throws IOException{
		FileInputStream fstream = new FileInputStream("dp.txt");
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		while ((line = br.readLine()) != null)   {
			list.add(line);
		}		
	}
	
	
	
	
	
	
	
}
