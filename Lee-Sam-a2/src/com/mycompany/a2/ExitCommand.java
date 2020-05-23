/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * ExitCommand class extends Command class and is used to process requests to exit the program
 */
public class ExitCommand extends Command {
   private GameWorld gw;
	
	/**
	 * Creates a new instance of the ExitCommand class
	 * @param gwRef GameWorld object to be used to process exit requests
	 */
   public ExitCommand(GameWorld gwRef) {
      super("Exit");
      gw = gwRef;
   }

	/**
	 * Calls the exit method of the GameWorld class
	 */
   @Override
   public void actionPerformed(ActionEvent ev) {
      gw.exit();
   }
}
