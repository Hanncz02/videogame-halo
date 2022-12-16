/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package halo;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author cris_
 */
public class Sound {
    
   
   
    public static void RunMusic(String path){
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (UnsupportedAudioFileException ex) {   
        } catch (IOException ex) {
        } catch (LineUnavailableException ex) {
            
        }
        
       
    }
    
    
    
    public static void loopMusic(String path){
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.loop(10);
            
            
        } catch (UnsupportedAudioFileException ex) {   
        } catch (IOException ex) {
        } catch (LineUnavailableException ex) {
            
        }
    }
    

    
  
    

    
    
}
