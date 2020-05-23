/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Defines a class for processing requests to process the next GameWorld tick
 */
public class TickCommand extends Command {
	private GameWorld gw;
	
	/**
	 * Create a new instance of TickCommand
	 * @param gwRef Game World to process
	 */
	public TickCommand(GameWorld gwRef) {
		super("Tick");
		gw = gwRef;
	}
	
	/**
	 * Calls the tick method of the GameWorld class
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.tick();
	}

}
