/**
 * NonPlayerCyborg.java
 * defines the NonPlayerCyborg class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
/**
 * NonPlayerCyborg class extends Cyborg class, adding features necessary for AI interaction
 */
public class NonPlayerCyborg extends Cyborg{
   public static final int MAX_DAMAGE = 200;
   public static final int NPC_MAX_SPEED = 80;
   private IStrategy currentStrategy;
	
	/**
	 * creates a new instance of NonPlayerCyborg (NPC) 
	 * @param startX - the NPC's initial X coordinate
	 * @param startY - the NPC's initial Y coordinate
	 * @param newGw - GameWorld object to be used for calling collision handlers
	 */
   public NonPlayerCyborg(double startX, double startY, GameWorld newGw) {
      super(startX, startY, NPC_MAX_SPEED, 255, NonPlayerCyborg.MAX_DAMAGE, 0, newGw);
      currentStrategy = new NonStrategy();
   }

	/**
	 * change the Non-Player Cyborg's current strategy
	 * @param newStrategy - the strategy to change the NPC to
	 * this is the setStrategy method
	 */
   public void changeStrategy(IStrategy newStrategy) {
      currentStrategy = newStrategy;
   }
	
	/**
	 * executes the current strategy
	 */
   public void invokeStrategy() {
      currentStrategy.apply();
   }
	
	/**
	 * outputs the NPC's information as a formatted string
	 * @return - string showing the NPC's current status
	 */
   public String toString() {
      return super.toString() + " Current Strategy: " + currentStrategy.getName();
   }
	
	/**
	 * returns the name of the current strategy
	 * @return String giving the current strategy's name
	 */
   public String getCurrentStrategy() {
      return currentStrategy.getName();
   }
	
	/**
	 * overrides the consumeEnergy command of the Cyborg class, refilling the 
	 * NPC's energy when it gets low
	 */
   @Override
   public void consumeEnergy() {
      super.consumeEnergy();
      if(super.getEnergyLevel() <= 0) {
         super.addEnergy(200);
      }
   }
	
	/**
	 * draws the NonPlayerCyborg to the screen
	 * @param g - the Graphics object used to draw
	 * @param pCmpRelPrnt - origin of the container relative to the parent
	 */
   public void draw(Graphics g, Point pCmpRelPrnt) {
      int diameter = super.getSize() / 2;
      int drawX = (int)Math.round(super.getLocationX() - diameter) + pCmpRelPrnt.getX();
      int drawY = (int)Math.round(super.getLocationY() - diameter) + pCmpRelPrnt.getY();
      g.setColor(ColorUtil.rgb(255,0,0));
      g.drawRect(drawX, drawY, super.getSize(), super.getSize());
   }
}
