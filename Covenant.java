/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package halo;

/**
 *
 * @author cris_
 */
import java.awt.Graphics;
import java.util.Random;
import javax.swing.ImageIcon;


public class Covenant {
    private int x, y, k;
    private ImageIcon Img[];
    private String path = 
    "C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\images\\";
    private Random r;
    private boolean vivo;
    
    public Covenant() {
        r = new Random();
        x = Math.abs(r.nextInt(200, 400));
        y = Math.abs(r.nextInt())%400;
        Img = new ImageIcon[8];
        for(int i=1; i<9; i++)
            Img[i-1] = new ImageIcon(path+"m"+i+".png");
        k = Math.abs(r.nextInt())%8;
        
        
        vivo = true;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public boolean estado() {
        return vivo;
    }
    
    public void revive() {
        x = Math.abs(r.nextInt(200, 400));
        y = Math.abs(r.nextInt())%400; 
        k = Math.abs(r.nextInt())%8;

        vivo = true;
        if (k == 5 || k == 2){
            if(r.nextInt(1, 3) == 1)
                Sound.RunMusic("C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\sounds\\wort.wav");
            else
              Sound.RunMusic("C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\sounds\\pozole.wav");  
        }
        
        if(k == 1)
            Sound.RunMusic("C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\sounds\\grunt.wav"); 
        
        if(k == 6 || k == 7 || k == 3 )
            Sound.RunMusic("C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\sounds\\jackal.wav"); 
        
        if(k == 4 )
            Sound.RunMusic("C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\sounds\\flood.wav"); 
         if(k == 0)
            Sound.RunMusic("C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\sounds\\hunter.wav"); 
            
    }
    
    public void muere() {
        vivo = false;
    }
    

    
    public void dibuja(Graphics g) {
        g.drawImage(Img[k].getImage(), x-20, y-20,60,60, null);
        
    }   
    
    public void mueve() {
        int dx = r.nextInt()%4;
        int dy = r.nextInt()%7;
        
        x = x+dx  >   0 ? x+dx : 0;
        x = x+dx  < 400 ? x+dx : 400;
        y = y+dy  >   0 ? y+dy : 0;
        y = y+dy  < 380 ? y+dy : 380;
    }
}