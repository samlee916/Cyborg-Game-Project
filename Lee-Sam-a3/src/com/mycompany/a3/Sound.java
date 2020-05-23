/**
 * Sound.java
 * defines the Sound class
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
 * the sound class storing a media stream for playing a sound effect
 */
/**
 * this code came directly from the lecture slides
 */
public class Sound {
   private Media m;
	
	/**
	 * create a new Sound object
	 * @param fileName- name of the wav file to load
	 */
   public Sound(String fileName) {
      try {
         InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
      	
         m = MediaManager.createMedia(is, "audio/wav");
      }
      catch(Exception e) {
         e.printStackTrace();
      }
   }
	
	/**
	 * set the play position to 0 and play the sound effect.
	 */
   public void play() {
      m.setTime(0);
      m.play();
   }
	
}

