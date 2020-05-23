/**
 * AttackStrategy.java
 * defines the AttackStrategy class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import com.codename1.util.MathUtil;

/**
 * AttackStrategy defines a strategy for non-player cyborgs to attempt to collide with the player cyborg
 */
public class AttackStrategy implements IStrategy{
   private PlayerCyborg playerCyborg;
   private NonPlayerCyborg myCyborg;
	
	/**
	 * creates a new instance of the AttackStrategy class
	 * @param inputCyborg - reference to the player cyborg the NPC is going to attack
	 * @param inputNPC - reference to the NPC that is going to attack the player cyborg
	 */
   public AttackStrategy(PlayerCyborg inputCyborg, NonPlayerCyborg inputNPC) {
      playerCyborg = inputCyborg;
      myCyborg = inputNPC;
   }
	
	/**
	 * apply the strategy
	 */
   public void apply() {
   	/**
   	 * find the angle between the NPC and the player cyborg
   	 */
      double angleRad = MathUtil.atan2(playerCyborg.getLocationX() - myCyborg.getLocationX(), playerCyborg.getLocationY() - myCyborg.getLocationY());
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
         if( angleDifference < 180) {
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
	
	/**
	 * returns "Attack"
	 */
   public String getName() {
      return "Attack";
   }
}
