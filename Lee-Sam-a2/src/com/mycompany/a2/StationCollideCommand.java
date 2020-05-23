/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Processes requests to collide with an energy station
 */
public class StationCollideCommand extends Command {
	private GameWorld gw;
	
	/**
	 * Creates a new instance of StationCollideCommand
	 * @param gwRef Game World containing the energy stations and player robot
	 */
	public StationCollideCommand(GameWorld gwRef) {
		super("Collide with Energy Station");
		gw = gwRef;
	}
	
	/**
	 * Calls the reachEnergyStation method within GameWorld
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.reachEnergyStation();
	}

}
