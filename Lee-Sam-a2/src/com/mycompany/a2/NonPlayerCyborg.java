/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;

/**
 * NonPlayerCyborg class extends Cyborg class, adding features necessary for AI interaction
 */
public class NonPlayerCyborg extends Cyborg {
	public static final int MAX_DAMAGE = 400;
	//private String currentStrategy;
	private IStrategy currentStrategy;
	
	/**
	 * Creates a new instance of NonPlayerCyborg (NPC) 
	 * @param startX - the NPC's initial X coordinate
	 * @param startY - the NPC's initial Y coordinate
	 */
	public NonPlayerCyborg(double startX, double startY) {
		super(startX, startY, 30, 255, NonPlayerCyborg.MAX_DAMAGE);
		currentStrategy = new NonStrategy();
	}
	
	/**
	 * Change the Non-Player Cyborg's current strategy
	 * @param newStrategy - the strategy to change the NPC to
	 * this is the setStrategy method
	 */
	public void changeStrategy(IStrategy newStrategy) {
		currentStrategy = newStrategy;
	}
	
	/**
	 * Executes the current strategy
	 * invokeStrategy method
	 */
	public void invokeStrategy() {
		currentStrategy.apply();
	}
	
	/**
	 * Outputs the NPC's information as a formatted string
	 * @return - String showing the NPC's current status
	 */
	public String toString() {
		return super.toString() + " Current Strategy: " + currentStrategy.getName();
	}
	
	/**
	 * Returns the name of the current strategy
	 * @return String giving the current strategy's name
	 */
	public String getCurrentStrategy() {
		return currentStrategy.getName();
	}
	
	/**
	 * Overrides the consumeEnergy command of the Cyborg class, refilling the 
	 * NPC's energy when it gets low
	 */
	@Override
	public void consumeEnergy() {
		super.consumeEnergy();
		if(super.getEnergyLevel() <= 0) {
			super.addEnergy(200);
		}
	}
}
