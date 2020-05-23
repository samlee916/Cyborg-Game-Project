/**
 * RightTurnCommand.java
 * defines the RightTurnCommand class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * RightTurnCommand processes requests to turn the player cyborg to the right
 */
public class RightTurnCommand extends Command {
   private GameWorld gw;
	
	/**
	 * creates a new instance of the RightTurnCommand class
	 * @param gwRef - GameWorld containing the player cyborg
	 */
   public RightTurnCommand(GameWorld gwRef) {
      super("Steer Right");
      gw = gwRef;
   }
	
	/**
	 * process the request to turn right
	 */
   @Override
   public void actionPerformed(ActionEvent ev) {
      gw.turnRight();
   }

}
