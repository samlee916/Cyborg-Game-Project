/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * ToggleSoundCommand is used to process requests to turn sound on or off
 */
public class ToggleSoundCommand extends Command {
	private GameWorld gw;
	
	/**
	 * Creates a new instance of ToggleSoundCommand
	 * @param gwRef GmaeWorld to process
	 */
	public ToggleSoundCommand(GameWorld gwRef) {
		super("Toggle Sound");
		gw = gwRef;
	}
	
	/**
	 * Calls toggleSound method of the GameWorld class
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.toggleSound();
	}

}
