/**
 * ChangeStrategiesCommand.java
 * defines the ChangeStrategiesCommand class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * ChangeStrategiesCommand class extends Command class and is used to initiate requests
 * to change the strategies of the non-player cyborgs in a GameWorld object
 */
public class ChangeStrategiesCommand extends Command {
   private GameWorld gw;
	
	/**
	 * creates a new instance of the ChangeStrategiesCommand class
	 * @param gwRef - 
	 */
   public ChangeStrategiesCommand(GameWorld gwRef) {
      super("Change Strategies");
      gw = gwRef;
   }
	
	/**
	 * calls the changeStrategies method of the GameWorld object
	 * @param ev - event has called the actionPerformed method to be called
	 */
   @Override
   public void actionPerformed(ActionEvent ev) {
      gw.changeStrategies();
   }

}
