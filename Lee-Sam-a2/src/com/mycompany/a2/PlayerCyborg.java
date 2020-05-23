/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;

/**
 * PlayerCyborg extends Cyborg class, ensures that only a single
 * instance of PlayerCyborg exists
 */
public class PlayerCyborg extends Cyborg {
	private static PlayerCyborg playerCyborg;
	private static final int MAX_DAMAGE = 100;

	/**
	 * Creates a new instance of player cyborg
	 */
	private PlayerCyborg() {
		super(0,0, 40, 100, PlayerCyborg.MAX_DAMAGE);
	}
	
	/**
	 * If there is no player cyborg, instantiate a new one and return it, if there is one return it
	 * @return PlayerCyborg object
	 */
	public static PlayerCyborg getPlayerCyborg() {
		if(PlayerCyborg.playerCyborg == null) {
			PlayerCyborg.playerCyborg = new PlayerCyborg();
		}
		return PlayerCyborg.playerCyborg;
	}
	
	/**
	 * Resets the PlayerCyborg to its initial state.
	 */
	public static void reset() {
		PlayerCyborg.playerCyborg = new PlayerCyborg();
	}
	
}
