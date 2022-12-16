/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package halo;

/**
 *
 * @author cris_
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Halo extends JFrame implements KeyListener {
    private Dibujar area;
    private ImageIcon icon;
    
    public Halo() {
        area = new Dibujar();
        icon = new ImageIcon("C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\images\\logo.png");
        add(area);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setIconImage(icon.getImage());
        setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.addKeyListener(this);
        this.setTitle("HALO");
        pack();
    }

    public void keyTyped(KeyEvent arg0) {
    }

    public void keyPressed(KeyEvent arg0) {
        
        try {
            area.keyPressed(arg0);
        } catch (InterruptedException ex) {
            Logger.getLogger(Halo.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }

    public void keyReleased(KeyEvent arg0) {
        area.keyReleased(arg0);
    }
    
    static public void main(String args[]) {
        new Halo();
    }
}
