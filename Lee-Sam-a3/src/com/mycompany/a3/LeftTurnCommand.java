/**
 * LeftTurncommand.java
 * defines the LeftTurnCommand class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * LeftTurnCommand for processing requests to turn the player cyborg left
 */
public class LeftTurnCommand extends Command {
   private GameWorld gw;
	
	/**
	 * creates a new LeftTurnCommand object
	 * @param gwRef - the GameWorld object containing the player cyborg
	 */
   public LeftTurnCommand(GameWorld gwRef) {
      super("Steer Left");
      gw = gwRef;
   }
	
	/**
	 * calls the GameWorld turnLeft method
	 */
   @Override
   public void actionPerformed(ActionEvent ev) {
      gw.turnLeft();
   }

}
