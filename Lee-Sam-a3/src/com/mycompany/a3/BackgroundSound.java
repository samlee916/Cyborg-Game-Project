/**
 * BackgroundSound.java
 * defines the BackgroundSound class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import java.io.InputStream; 
import com.codename1.ui.Display;

/**
 * BackgroundSound implements Runnable and is responsible for handling the creation and playing of 
 * a repeating background sound loop
 */
/**
 * this code came directly from the lecture slides
 */
public class BackgroundSound implements Runnable {
   private Media m;

	/** 
	 * creates and returns a new instance of BackgroundSound
	 * @param fileName - the wav file to load
	 */
   public BackgroundSound(String fileName){
      try {
         InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
      	
         m = MediaManager.createMedia(is, "audio/wav", this);
      } catch(Exception e){
         e.printStackTrace(); 
      } 
   }
	
	/**
	 * pause playback of the sound effect.
	 */
   public void pause(){ m.pause(); }
	/**
	 * begin playing the sound file
	 */
   public void play(){ m.play(); }

	/**
	 * set the sound file to the beginning and then play it.
	 */
   public void run() { 
      m.setTime(0);
      m.play();
   }
}
