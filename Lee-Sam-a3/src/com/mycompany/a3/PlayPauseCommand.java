/**
 * PlayPauseCommand.java
 * defines the PlayPauseCommand class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.Button;

/**
 * PlayCommand class defines actions to performed when the command is called
 */
public class PlayPauseCommand extends Command {
   private Game myGame;
   private Button myButton;
	
	/**
	 * create the new command called "Play"
	 */
   public PlayPauseCommand(Game newGame, Button button) {
      super("Pause");
      myGame = newGame;
      myButton = button;
   }
	
	/**
	 * resumes the timer
	 * @param ev - ActionEvent object containing details of the action that initiated the command
	 */
   @Override
   public void actionPerformed(ActionEvent ev) {
      myGame.togglePaused();
      if(myGame.isPaused()) {
         myButton.setText("Play");
      } else {
         myButton.setText("Pause");
      }
   }

}
