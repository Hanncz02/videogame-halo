/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package halo;


import java.awt.Graphics;
import javax.swing.ImageIcon;

class S117 {
    private int x, y;
    private ImageIcon Img, Camina, Dispara, CaminaL, DisparaL;
    private String path = 
    "C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\images\\";
    private boolean vivo = true;
    
    public S117() {
        x = 40;
        y = 40;
        Img = new ImageIcon(path+"jefeN.png");
        images();
    }
    
    private void images(){
        Dispara = new ImageIcon("C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\images\\jefeSh.gif");
        DisparaL = new ImageIcon("C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\images\\jefeShL.gif");
        Camina = new ImageIcon("C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\images\\jefeR.gif");
        CaminaL = new ImageIcon("C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\images\\jefeL.gif");
        
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    public void setImage(String path){
        this.path = path;
    }
    
    public void dibuja(Graphics g) {
        g.drawImage(Img.getImage(), x-30, y-30, 60, 60, null);
    }
    
    public void camina(Graphics g) {
        g.drawImage(Camina.getImage(), x-30, y-30, 60, 60, null);
    }
    
    public void retrocede(Graphics g) {
        g.drawImage(CaminaL.getImage(), x-30, y-30, 60, 60, null);
    }
    public void Dispara(Graphics g) {
        g.drawImage(Dispara.getImage(), x-30, y-30, 60, 60, null);
    }
     public void DisparaL(Graphics g) {
        g.drawImage(DisparaL.getImage(), x-30, y-30, 60, 60, null);
    }
    public void mueve(int dx, int dy) {
        x = x+dx  >   0 ? x+dx : 400;
        x = x+dx  < 400 ? x+dx : 0;
        y = y+dy  >   0 ? y+dy : 400;
        y = y+dy  < 400 ? y+dy : 0;
    }     
    
    public void muere(){
        vivo = false;
    }
    
    public void revive(){
        vivo = true;
    }
    
    public boolean estado (){
        return vivo;
    }
}