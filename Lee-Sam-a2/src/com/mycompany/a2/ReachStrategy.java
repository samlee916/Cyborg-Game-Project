/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import com.codename1.util.MathUtil;

/**
 * ReachStrategy is a strategy for non-player cyborgs that makes them attempt to reach the next base
 */
public class ReachStrategy implements IStrategy{
	private NonPlayerCyborg myCyborg;
	private GameWorld gw;
	
	/**
	 * Creates a new instance of the StratRace class 
	 * @param inGw - the GameWorld object containing bases to reach
	 * @param inCyborg - the Non-Player Cyborg to apply the strategy to
	 */
	public ReachStrategy(GameWorld inGw, NonPlayerCyborg inCyborg) {
		gw = inGw;
		myCyborg = inCyborg;
	}
	
	/**
	 * Will attempt to steer the non-player cyborg towards the next base in the sequence
	 */
	public void apply() {
		Base newBase = gw.getBase(myCyborg.getLastBase() + 1);
		if(newBase != null) {
			double angleRad = MathUtil.atan2(newBase.getLocationY() - myCyborg.getLocationY(), newBase.getLocationX() - myCyborg.getLocationX());
			int angleDeg = (int)Math.toDegrees(angleRad);
			if(angleDeg > myCyborg.getHeading()) {
				myCyborg.steer(Direction.RIGHT);
			} else if(angleDeg < myCyborg.getHeading()) {
				myCyborg.steer(Direction.LEFT);
			}
		}
	}
	
	/**
	 * Returns "Reach"
	 */
	public String getName() {
		return "Reach";
	}
}
