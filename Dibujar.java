/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package halo;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Dibujar extends JPanel {
    S117 miS117;
    List <Bala> balazos;
    List <Bala> plasma;
    List <Bala> carga;
    MueveBalas disparo;
    generaPlasma genera;
    generaCarga cargas;
    
    DisparaCovenant plasmas;
    int dx, dy;
    MueveS117 mueveS117;
    Covenant miCovenant ;
    MueveCovenant mueveCovenant;
    private int vidas;
    private String path = 
    "C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\images\\";
    private ImageIcon Img, inicio;
    private boolean disparando = false, disparandoL = false;
    private boolean inGame, reintentando = false;
    SoundMain music;
    Random r;
    
            
    public Dibujar() {
        miS117 = new S117();
        miCovenant = new Covenant();
        
        inGame = false;
        r = new Random();
        balazos = new ArrayList <Bala> ();    
        plasma = new ArrayList <Bala>();
        carga = new ArrayList <Bala>();
        cargas = new generaCarga();
        disparo = new MueveBalas();
        genera = new generaPlasma();
        plasmas = new DisparaCovenant();
        mueveS117 = new MueveS117();
        mueveCovenant = new MueveCovenant();
        music = new SoundMain();
        dx=0; dy =0;
        Img = new ImageIcon(path+"fondo.jpg");
        inicio = new ImageIcon(path + "main.jpg");
        
        
       
       music.start();
        this.setPreferredSize(new Dimension(400, 400)); 
        this.setBackground(Color.WHITE); 
        this.setVisible(true);
       
        
    }
    
 
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        g.setFont(new Font("Arial", Font.BOLD, 14));
        
        if(inGame){
            g.drawImage(Img.getImage(), 0,0, 400, 400, null);
        }   
        else{
           g.drawImage(inicio.getImage(), 0, 0, 400, 400, null);
        }
        g.setColor(Color.WHITE);
        if(inGame)
            g.drawString("Vidas: " + vidas, 330, 20);
        
        if(dx > 0 || dy > 0 || dy < 0){
            miS117.camina(g);
            
        }
        if(dx < 0)
            miS117.retrocede(g);
        
        if(disparando){
            miS117.Dispara(g);
        }
        
        if(disparandoL){
            miS117.DisparaL(g);
        }
        if(reintentando){
            String start = "Has muerto"; //\n¿Reintentar?\n\nSpace = Si\tESC = Salir
            g.setColor(Color.yellow);
            g.drawString(start, 160, 260);
            g.drawString("¿Reintentar?", 155, 280);
            g.drawString("Space = Si                ESC = Salir", 90, 300);
        }
        
        try {
            if(miCovenant.estado() && inGame)
                miCovenant.dibuja(g);
            
            if(dx == 0&& dy == 0 && disparando == false && disparandoL == false && inGame)
                miS117.dibuja(g); 
            for(Bala b: balazos)
                b.dibuja(g);
            
            if(inGame){
                for(Bala n: plasma){
                    n.plasma(g);    
                }
                
                for(Bala x: carga){
                    x.superBala(g);
                }
            }
        } catch (ConcurrentModificationException e) {}
    }
    
    
    
 
 

    public void keyPressed(KeyEvent arg0) throws InterruptedException {
        if(arg0.getKeyCode() == KeyEvent.VK_LEFT && inGame == true)  dx--;
        if(arg0.getKeyCode() == KeyEvent.VK_RIGHT && inGame == true) dx++;
        if(arg0.getKeyCode() == KeyEvent.VK_UP && inGame == true)  dy--;        
        if(arg0.getKeyCode() == KeyEvent.VK_DOWN && inGame == true) dy++;
        
        if(arg0.getKeyCode() == KeyEvent.VK_A && inGame == true) {
            
            balazos.add(new Bala(miS117.getX(), miS117.getY(), -1, 0) );
            Sound.RunMusic("C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\sounds\\shoot.wav");
            disparandoL = true;
        
        }
        if(arg0.getKeyCode() == KeyEvent.VK_D&& inGame == true) {
            balazos.add(new Bala(miS117.getX(), miS117.getY(), 1, 0));
            Sound.RunMusic("C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\sounds\\shoot.wav");
            disparando = true;
        }
        
        if(arg0.getKeyCode() == KeyEvent.VK_W && inGame == true) {
            balazos.add(new Bala(miS117.getX(), miS117.getY(), 0, -1));
            Sound.RunMusic("C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\sounds\\shoot.wav");
        }
        
        
        
       
        
        if(arg0.getKeyCode() == KeyEvent.VK_SPACE && inGame == false){
            
            music.clip.stop();
            if(miS117.estado())
                initGame();
             
             
            
        }
        
        if(arg0.getKeyCode() == KeyEvent.VK_SPACE && miS117.estado() == false){

             restartGame();

        }
        
        if(arg0.getKeyCode() == KeyEvent.VK_ESCAPE ){

            System.exit(0);

        }
            
           
        
            
    }
    
    public void keyReleased(KeyEvent arg0) {
        dx=0;
        dy=0;
        disparando = false;
        disparandoL = false;
    }
    
   
    private class MueveS117 extends Thread {
        @Override        
        public void run(){
            while(true) {
                miS117.mueve(dx, dy);
                repaint();
                try {
                    sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Dibujar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }        
    }
    
    private class MueveBalas extends Thread {      
        public int distancia(int x0, int y0, int x1, int y1) {
            return ((x0-x1)*(x0-x1) + (y0 - y1)*(y0 - y1)) ;
        }
        
        void borra() {
            int k, elimina =-1;
            
            if (balazos.isEmpty()) return;
                
            
            do {
                k=0;
                elimina = -1;
                for(Bala b: balazos) {
                    /*
                    if(b.getX() <= 0   ) { elimina = k; break;}
                    if(b.getX() >= 400 ) { elimina = k; break;}
                    if(b.getY() <= 0   ) { elimina = k; break;}
                    if(b.getY() >= 400 ) { elimina = k; break;}
                     * */
                    if(b.getX() <= 0   || 
                       b.getX() >= 400 ||
                       b.getY() <= 0   ||
                       b.getY() >= 400 ) { elimina = k; break;}
                    
                    k++;
                }
                if(elimina >= 0) balazos.remove(elimina);
            } while(elimina >=0);
        }

        
        @Override        
        public void run(){
            while(true) {
                
                try{
                    for(Bala b: balazos) {
                        b.mueve();
                        if(distancia(b.getX(), b.getY(), 
                                miCovenant.getX(), miCovenant.getY()) < 100) {
                            miCovenant.muere();
                            balazos.remove(b);
                        }
                    }
                    borra();
                    repaint();
                } catch(ConcurrentModificationException e) {}
                
                try {
                    sleep(2);
                } catch (InterruptedException ex) {
                }
            }         
        }
    }
    
    private class MueveCovenant extends Thread {
          @Override        
        public void run(){
            while(true) {
                miCovenant.mueve();
                if(miCovenant.estado() == false) {
                    miCovenant.revive();
                    
                }
                repaint();
                try {
                    sleep(30);
                } catch (InterruptedException ex) {
                }
            }         
        }      
    }
    
    private class DisparaCovenant extends Thread{
        
        
        
        public int distancia(int x0, int y0, int x1, int y1) {
            return ((x0-x1)*(x0-x1) + (y0 - y1)*(y0 - y1)) ;
        }
        
        void borra() {
            int k, elimina =-1;
            
            if (plasma.isEmpty() || carga.isEmpty()) return;
            
            do {
                k=0;
                elimina = -1;
                for(Bala b: plasma) {
                    /*
                    if(b.getX() <= 0   ) { elimina = k; break;}
                    if(b.getX() >= 400 ) { elimina = k; break;}
                    if(b.getY() <= 0   ) { elimina = k; break;}
                    if(b.getY() >= 400 ) { elimina = k; break;}
                     * */
                    if(b.getX() <= 0    
                       
                        ) { elimina = k; break;}
                    
                    k++;
                }

                if(elimina >= 0) plasma.remove(elimina);
            } while(elimina >=0);
            
            
             do {
                k=0;
                elimina = -1;
                for(Bala b: carga) {
                    /*
                    if(b.getX() <= 0   ) { elimina = k; break;}
                    if(b.getX() >= 400 ) { elimina = k; break;}
                    if(b.getY() <= 0   ) { elimina = k; break;}
                    if(b.getY() >= 400 ) { elimina = k; break;}
                     * */
                    if(b.getX() <= 0    
                       
                        ) { elimina = k; break;}
                    
                    k++;
                }

                if(elimina >= 0) carga.remove(elimina);
            } while(elimina >=0);
            
            
        }
        
        @Override        
        public void run(){
            while(true) {
                
                
                
                try{
                    
                    for(Bala b: plasma) {
                        b.mueve();
                        
                        
                        if(distancia(b.getX(), b.getY(), 
                                miS117.getX(), miS117.getY()) < 100 && miS117.estado()) {
                            vidas--;
                            plasma.remove(b);
                           Sound.RunMusic("C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\sounds\\ou.wav");
                           if(vidas == 0)
                               Sound.RunMusic("C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\sounds\\sinesc.wav");
                           
                           if(vidas < 0 ){
                               miS117.muere();
                                reintentando = true;
                                Sound.RunMusic("C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\sounds\\death.wav");
                                inGame = false;
                            
                        }
                               
                        }
                    }
                    
                    for(Bala b: carga) {
                        b.mueve();
                        
                        
                        if(distancia(b.getX(), b.getY(), 
                                miS117.getX(), miS117.getY()) < 100 && miS117.estado()) {
                             vidas = 0;
                            carga.remove(b);
                           if(vidas == 0)
                               Sound.RunMusic("C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\sounds\\sinesc.wav");
                           
                           else if(distancia(b.getX(), b.getY(), 
                                miS117.getX(), miS117.getY()) < 100 && vidas == 0) {
                               vidas--;
                           }
                           
                           if(vidas < 0 ){
                               miS117.muere();
                                reintentando = true;
                                Sound.RunMusic("C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\sounds\\death.wav");
                                inGame = false;
                            
                            }

                               
                        }
                    }
                    borra();
                    repaint();
                } catch(ConcurrentModificationException e) {}
                
                try {
                    sleep(2);
                } catch (InterruptedException ex) {
                }
            }         
        }
        
    }
    
    private class generaPlasma extends Thread{
        @Override
        public void run(){
            while(true){
            try {
                sleep(r.nextInt(200, 1000));
                Bala x = new Bala(miCovenant.getX(), miCovenant.getY(), -1, 0);
            plasma.add(x);
            if(inGame)
                Sound.RunMusic("C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\sounds\\plasma.wav");
             sleep(r.nextInt(1000, 2500));
            } catch (InterruptedException ex) {
                Logger.getLogger(Dibujar.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
        }
    }
    
    private class generaCarga extends Thread{
        @Override
        public void run(){
            while(true){
            try {
                
                Bala x = new Bala(miCovenant.getX(), miCovenant.getY(), -1, 0);
            carga.add(x);
            if(inGame)
                Sound.RunMusic("C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\sounds\\plasma.wav");
             sleep(r.nextInt(5000, 15000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Dibujar.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
        }
    }
    
    private  class SoundMain extends Thread{
        
        boolean reproduciendo = true;
        Clip clip;
        @Override
        public void run(){
            
            
                try {
                AudioInputStream audio = AudioSystem.getAudioInputStream(new File("C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\sounds\\mainMenu.wav"));
                clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();
                
            
            
            } catch (UnsupportedAudioFileException ex) {   
            } catch (IOException ex) {
            } catch (LineUnavailableException ex) {
            
            }
                

        
        
         }
    }
    
    public void restartGame(){
           vidas = 3;
           inGame = true;
           miS117.revive();
           reintentando = false;
        
    }
    
    public void initGame(){
        vidas = 3;
        inGame = true;
        genera.start();
        disparo.start();
        mueveS117.start();
        mueveCovenant.start();
        plasmas.start();
        cargas.start();
        
        Sound.loopMusic("C:\\Users\\cris_\\Documents\\NetBeansProjects\\Halo\\sounds\\battle.wav");
        
    }
}