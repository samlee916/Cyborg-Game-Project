/**
 * AccelCommand.java
 * defines the AccelCommand class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * AccelCommand class extends the Command class for use when a request to 
 * accelerate the player cyborg has been received
 */
public class AccelCommand extends Command {
   private GameWorld gw;
	
	/**
	 * creates a new object of the AccelCommand
	 * @param gwRef - reference to the game world object that the command is to modify
	 */
   public AccelCommand(GameWorld gwRef) {
      super("Accelerate");
      gw = gwRef;
   }
	
	/**
	 * calls the accelerate method of the GameWorld object passed into the constructor
	 */
   @Override
   public void actionPerformed(ActionEvent ev) {
      gw.accelerate();
   }
}
