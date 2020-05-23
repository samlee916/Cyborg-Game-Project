/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * AccelCommand class extends the Command class for use when a request to 
 * accelerate the player cyborg has been received
 */
public class AccelCommand extends Command {
   private GameWorld gw;
	
	/**
	 * Creates a new object of the AccelCommand
	 * @param gwRef - Reference to the game world object that the command is to modify
	 */
   public AccelCommand(GameWorld gwRef) {
      super("Accelerate");
      gw = gwRef;
   }
	
	/**
	 * Calls the accelerate method of the GameWorld object passed into the constructor
	 */
   @Override
   public void actionPerformed(ActionEvent ev) {
      gw.accelerate();
   }
}
