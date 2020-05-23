/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * LeftTurnCommand for processing requests to turn the player cyborg left
 */
public class LeftTurnCommand extends Command {
	private GameWorld gw;
	
	/**
	 * Creates a new LeftTurnCommand object
	 * @param gwRef the GameWorld object containing the player cyborg
	 */
	public LeftTurnCommand(GameWorld gwRef) {
		super("Left");
		gw = gwRef;
	}
	
	/**
	 * Calls the GameWorld turnLeft method
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.turnLeft();
	}

}
