package Lim.myImages;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.*;
import javax.swing.JLabel;

import javax.swing.JPanel;
/**
 * Write a description of class ImageFrame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ImageFrame extends JFrame
{
 JLayeredPane lpane = new JLayeredPane();
     private Container ContentPane = getContentPane();private final int FONT_SIZE = 50;
    public ImageFrame()
    {
        Color custom = new Color(147,197,114);
        
        JLabel bob = new JLabel("Hell0");
          bob.setFont(new Font("Purisa", Font.BOLD,FONT_SIZE));
          

        this.add(new ImageBoard(new JLabel("Sicarii"),new JLabel("Praetor")));
   
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700,700);
        //this.setPreferredSize(new Dimension(700, 700));
        //this.setLayout(new BorderLayout());
        //this.add(lpane,BorderLayout.CENTER);
        //lpane.setBounds(0,0,700,700);

        this.setLocationRelativeTo(null);
        this.setTitle("Axios");
        this.setResizable(false);
       
        
        this.setBackground(custom);
         // lpane.setOpaque(true);
         
        

        this.setVisible(true);

    }

    public static void main(String args[]){
        new ImageFrame();
        // new ImageFrame();

    }
}
