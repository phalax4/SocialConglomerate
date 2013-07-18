package Lim.myImages;

/**
 * Write a description of class ImageBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;

import java.awt.geom.*;
import javax.swing.JPanel;
import java.util.Random;
import javax.swing.Timer;


public class ImageBoard extends JPanel
{
    //JLabel castle;
    //Graphics g = getGraphics();
    Image castle;
    private JLayeredPane layeredPane;private final int FONT_SIZE = 70;

    public ImageBoard(JLabel label1,JLabel label2)
    {
        Color custom = new Color(139,0,0);
        this.setLayout(null);
        ImageIcon ii = new ImageIcon(this.getClass().getResource("../myPictures/Axios.png"));
        castle = ii.getImage();

        label1.setFont(new Font("Monotype Corsiva", Font.BOLD,FONT_SIZE));
        label2.setFont(new Font("Vivaldi", Font.BOLD,FONT_SIZE));
        label1.setForeground(Color.red);
        label2.setForeground(custom);

        
        
        label1.setBounds(280,140,500,100);
        label2.setBounds(30,300,500,100);
            this.add(label1);
        this.add(label2);

    }

    public void paintComponent(Graphics g){

        painter(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.drawImage(castle,20,500,null);
        g2.drawImage(castle,500,500,null);
        g2.drawImage(castle,100,50,null);
        g2.drawImage(castle,335,335,null);

    }

    public void painter(Graphics g){
        int r,gr,b; Random wizard = new Random();int i = 1;

        Graphics2D g2a = (Graphics2D)g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);

        Dimension size = getSize();
        double w = size.getWidth();
        double h = size.getHeight();
        Rectangle2D e = new Rectangle2D.Double(50,50,80,130);
        Rectangle2D e1 = new Rectangle2D.Double(200,200,80,130);
        g2a.setStroke(new BasicStroke(5));
        for(double deg = 0;deg<360;deg+=5){
            r = wizard.nextInt(255);gr = wizard.nextInt(255);b = wizard.nextInt(255);
            Color myColor = new Color(r,gr,b);
            g2a.setColor(myColor);
            AffineTransform at = AffineTransform.getTranslateInstance(w/2,h/2);
            at.rotate(Math.toRadians(deg),40,40);
            //             g2a.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
            //                     i * 0.1f));
            g2a.draw(at.createTransformedShape(e1));
            //             if(i<10){
            //                 i++;}
            //             else{
            //                 i = 1;
            //             }
            //repaint();
        }

        for(double deg = 0;deg<360;deg+=5){
            r = wizard.nextInt(255);gr = wizard.nextInt(255);b = wizard.nextInt(255);
            Color myColor = new Color(r,gr,b);
            g2a.setColor(myColor);
            AffineTransform at = AffineTransform.getTranslateInstance(w/2,h/2);
            at.rotate(Math.toRadians(deg),40,40);
            //             g2a.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
            //                     0.1f));
            g2a.draw(at.createTransformedShape(e));
            //repaint();
        }

    }

}