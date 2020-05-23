/**
 * TickCommand.java
 * defines the TickCommand class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * defines a class for processing requests to process the next GameWorld tick
 */
public class TickCommand extends Command {
   private GameWorld gw;
	
	/**
	 * create a new instance of TickCommand
	 * @param gwRef - Game World to process
	 */
   public TickCommand(GameWorld gwRef) {
      super("Tick");
      gw = gwRef;
   }
	
	/**
	 * calls the tick method of the GameWorld class
	 */
   @Override
   public void actionPerformed(ActionEvent ev) {
      gw.tick(1000);
   }

}
