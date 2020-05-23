/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * CyborgCollideCommand process requests to collide the player cyborg with a 
 * non player cyborg
 */
public class CyborgCollideCommand extends Command {
   private GameWorld gw;
	
	/**
	 * Creates a new instance of the CyborgCollideCommand class
	 * @param gwRef
	 */
   public CyborgCollideCommand(GameWorld gwRef) {
      super("Collide with NPC");
      gw = gwRef;
   }
	
	/**
	 * Calls cyborgCollide method of the GameWorld class
	 */
   @Override
   public void actionPerformed(ActionEvent ev) {
      gw.cyborgCollide();
   }

}
