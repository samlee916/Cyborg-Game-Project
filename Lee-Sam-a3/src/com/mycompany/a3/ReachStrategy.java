/**
 * ReachStrategy.java
 * defines the ReachStrategy class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import com.codename1.util.MathUtil;

/**
 * ReachStrategy is a strategy for non-player cyborgs that makes them attempt to reach the next base
 */
public class ReachStrategy implements IStrategy{
   private NonPlayerCyborg myCyborg;
   private GameWorld gw;
	
	/**
	 * creates a new instance of the ReachStrategy class 
	 * @param inGw - the GameWorld object containing bases to reach
	 * @param inCyborg - the non player Cyborg to apply the strategy to
	 */
   public ReachStrategy(GameWorld inGw, NonPlayerCyborg inCyborg) {
      gw = inGw;
      myCyborg = inCyborg;
   }
	
	/**
	 * will attempt to steer the non-player cyborg towards the next base in the sequence
	 */
   public void apply() {
      Base newBase = gw.getBase(myCyborg.getLastBase() + 1);
      if(newBase != null) {
         double angleRad = MathUtil.atan2(newBase.getLocationX() - myCyborg.getLocationX(), newBase.getLocationY() - myCyborg.getLocationY());
         double angleDeg = Math.toDegrees(angleRad);
         int angleDifference = (int)(angleDeg - myCyborg.getHeading());
      	
      	/**
      	 * normalize angle between 0 and 360 to match heading limits
      	 */
         while(angleDifference >= 360.0) {
            angleDifference -= 360.0;
         } 
         while(angleDifference < 0.0) {
            angleDifference += 360.0;
         }
         if(angleDifference != 0) {
            if(angleDifference < 180) {
               myCyborg.steer(Direction.RIGHT);
            } else {
               myCyborg.steer(Direction.LEFT);
            }
         }
         if(angleDifference < 5 && angleDifference > -5) {
            myCyborg.accelerate(5);
         } else if(myCyborg.getSpeed() > 10){
            myCyborg.brake(1);
         }
      }
   }
	
	/**
	 * returns "Reach"
	 */
   public String getName() {
      return "Reach";
   }
}
