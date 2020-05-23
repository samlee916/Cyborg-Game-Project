/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import com.codename1.util.MathUtil;

/**
 * AttackStrategy defines a strategy for non-player cyborgs to attempt to collide with the player cyborg
 */
public class AttackStrategy implements IStrategy{
   private PlayerCyborg playerCyborg;
   private NonPlayerCyborg myCyborg;
	
	/**
	 * Creates a new instance of the AttackStrategy class
	 * @param inputCyborg - Reference to the player cyborg the NPC is going to attack
	 * @param inputNPC - Reference to the NPC that is going to attack the player cyborg
	 */
   public AttackStrategy(PlayerCyborg inputCyborg, NonPlayerCyborg inputNPC) {
      playerCyborg = inputCyborg;
      myCyborg = inputNPC;
   }
	
	/**
	 * Apply the strategy
	 */
   public void apply() {
   	/**
   	 * Find the angle between the NPC and the player cyborg 
   	 */
      double angleRad = MathUtil.atan2(playerCyborg.getLocationY() - myCyborg.getLocationY(), playerCyborg.getLocationX() - myCyborg.getLocationX());
      int angleDeg = (int)Math.toDegrees(angleRad);
   	/**
   	 * Determine which way to turn the NPC to try and intercept the player cyborg
   	 */
      if(angleDeg > myCyborg.getHeading()) {
         myCyborg.steer(Direction.RIGHT);
      } else if(angleDeg < myCyborg.getHeading()) {
         myCyborg.steer(Direction.LEFT);
      }
   }
	
	/**
	 * Returns "Attack"
	 */
   public String getName() {
      return "Attack";
   }
}
