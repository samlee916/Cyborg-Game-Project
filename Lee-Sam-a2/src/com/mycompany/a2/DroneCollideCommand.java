/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * DroneCollideCommand extends Command and is used for processing requests to collide the player
 * cyborg with a drone
 */
public class DroneCollideCommand extends Command {
   private GameWorld gw;
	
	/**
	 * creates a new instance of the DroneCollideCommand class
	 * @param gwRef
	 */
   public DroneCollideCommand(GameWorld gwRef) {
      super("Collide with Drone");
      gw = gwRef;
   }
	
	/**
	 * Calls the droneCollide method of the GameWorld class
	 */
   @Override
   public void actionPerformed(ActionEvent ev) {
      gw.droneCollide();
   }

}
