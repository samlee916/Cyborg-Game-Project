/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * RightTurnCommand processes requests to turn the player cyborg to the right
 */
public class RightTurnCommand extends Command {
	private GameWorld gw;
	
	/**
	 * Creates a new instance of the RightTurnCommand class
	 * @param gwRef GameWorld containing the player cyborg
	 */
	public RightTurnCommand(GameWorld gwRef) {
		super("Right");
		gw = gwRef;
	}
	
	/**
	 * Process the request to turn right
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.turnRight();
	}

}
