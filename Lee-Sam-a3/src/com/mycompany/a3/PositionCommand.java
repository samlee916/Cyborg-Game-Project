/**
 * PositionCommand.java
 * defines the PositionCommand class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * command to begin repositioning an object
 */
public class PositionCommand extends Command {
   GameWorld gw;
   Game myGame;
	
	/**
	 * create a new PositionCommand object
	 * @param newGw - GameWorld object reference to target
	 * @param newGame - Game form object reference to target
	 */
   public PositionCommand(GameWorld newGw, Game newGame) {
      super("Position");
      gw = newGw;
      myGame = newGame;
   }
	
	/**
	 * if the game is paused, call the togglePositioning method of GameWorld
	 */
   public void actionPerformed(ActionEvent ev) {
      if(myGame.isPaused())
         gw.togglePositioning();
   }
}
