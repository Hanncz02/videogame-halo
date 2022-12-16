/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package halo;

/**
 *
 * @author cris_
 */
import java.awt.Color;
import java.awt.Graphics;

public class Bala {   
    private int x, y, dx, dy;
    
    public Bala(int unaX, int unaY, int undx, int undy){
        x = unaX;
        y = unaY;
        dx = undx;
        dy = undy;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public synchronized void mueve() {
        x += dx;
        y += dy;
    }
    
    public void dibuja(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(x-2, y-2, 4, 4);
    }
    
    public void plasma(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x-2, y-2, 4, 4);
    }
    
    public void superBala(Graphics g){
        g.setColor(Color.GREEN);
        g.fillOval(x-6, y-6, 12, 12);
        
    }
}