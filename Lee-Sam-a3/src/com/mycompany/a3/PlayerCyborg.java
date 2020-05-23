/**
 * PlayerCyborg.java
 * defines the PlayerCyborg class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * PlayerCyborg extends Cyborg class, ensures that only a single
 * instance of PlayerCyborg exists
 */
public class PlayerCyborg extends Cyborg {
   private static PlayerCyborg playerCyborg;
   private static final int MAX_DAMAGE = 100;
   private static final int PLAYER_MAX_SPEED = 100;

	/**
	 * Creates a new instance of Player Cyborg
	 * @param newGw - GameWorld object to be used for calling collision handlers
	 */
   private PlayerCyborg(GameWorld newGw) {
      super(0,0, PLAYER_MAX_SPEED, 100, PlayerCyborg.MAX_DAMAGE, 1, newGw);
   }
	
	/**
	 * if there is no player cyborg, instantiate a new one and return it, if there is one, return it
	 * @param newGw - GameWorld object to be used for calling collision handlers
	 * @return PlayerCyborg object
	 */
   public static PlayerCyborg getPlayerCyborg(GameWorld newGw) {
      if(PlayerCyborg.playerCyborg == null) {
         PlayerCyborg.playerCyborg = new PlayerCyborg(newGw);
      }
      return PlayerCyborg.playerCyborg;
   }
	
	/**
	 * resets the PlayerCyborg to its initial state
	 * @param newGw - GameWorld object to be used for calling collision handlers
	 */
   public static void reset(GameWorld newGw) {
      PlayerCyborg.playerCyborg = new PlayerCyborg(newGw);
   }
	
	/**
	 * draw the player cyborg to the screen
	 * @param g - the Graphics object used to draw
	 * @param pCmpRelPrnt - origin of container relative to parent
	 */
   public void draw(Graphics g, Point pCmpRelPrnt) {
      double diameter = super.getSize() / 2;
      int drawX = (int)Math.round(super.getLocationX() - diameter) + pCmpRelPrnt.getX();
      int drawY = (int)Math.round(super.getLocationY() - diameter) + pCmpRelPrnt.getY();
      g.setColor(ColorUtil.rgb(255,0,0));
      g.fillRect(drawX, drawY, super.getSize(), super.getSize());
   	
      
   }
     
}
