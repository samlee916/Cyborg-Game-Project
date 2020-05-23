/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * BrakeCommand class extends Command class and is used to process a request for the 
 * player cyborg to brake
 */
public class BrakeCommand extends Command {
   private GameWorld gw;
	
	/**
	 * Create a new instance of the BrakeCOmmand class
	 * @param gwRef
	 */
   public BrakeCommand(GameWorld gwRef) {
      super("Brake");
      gw = gwRef;
   }
	
	/**
	 * Initiates braking for the player cyborg contained in the GameWorld object passed into the
	 * constructor
	 */
   @Override
   public void actionPerformed(ActionEvent ev) {
      gw.brake();
   }

}
