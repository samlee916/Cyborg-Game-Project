/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * ChnageStrategiesCommand class extends Command class and is used to initiate requests
 * to change the strategies of the non-player cyborgs in a GameWorld object
 */
public class ChangeStrategiesCommand extends Command {
   private GameWorld gw;
	
	/**
	 * Creates a new instance of the ChangeStrategiesCommand class
	 * @param gwRef
	 */
   public ChangeStrategiesCommand(GameWorld gwRef) {
      super("Change Strategies");
      gw = gwRef;
   }
	
	/**
	 * Calls the changeStrategies method of the GameWorld object
	 */
   @Override
   public void actionPerformed(ActionEvent ev) {
      gw.changeStrategies();
   }

}
