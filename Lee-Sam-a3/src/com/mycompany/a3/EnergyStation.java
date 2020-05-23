/**
 * EnergyStation.java
 * defines the EnergyStation class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
/**
 * EnergyStation class extending the Fixed class to provide a place for cyborgs to refill their energy
 * extends the Fixed class
 */
public class EnergyStation extends Fixed {
   private static Random rand = new Random();
   private static final int SIZE_MAX = 40;
   private static final int STATION_COLOR = ColorUtil.rgb(0,255,0);
   private static final int FADED_COLOR = ColorUtil.rgb(128, 200, 128);
   private int capacity;
	
	/**
	 * construct an object of the EnergyStation class in a random location and random size between 10 and 40
	 * @param locationX - the X coordinate of the energy station's location
	 * @param locationY - The Y coordinate of the energy station's location
	 * @param newGw - GameWorld object to be used for calling collision handlers
	 * @return a new object of the EnergyStation class
	 */
   public EnergyStation(double locationX, double locationY, GameWorld newGw) {
   	/**
   	 * call the parent's constructor to set the energy station's size and color
   	 */ 
      super((rand.nextInt(EnergyStation.SIZE_MAX) + 10) + 45, STATION_COLOR, locationX, locationY, newGw);
      capacity = super.getSize();		
   }

	
	/**
	 * get the station's current capacity
	 * @return an integer value representing the station's current capacity level
	 */
   public int getCapacity() { 
      return capacity; }
	
	/**
	 * drains the station's capacity, setting it to 0 and outputting the previous value
	 * @return the capacity of the Energy Station before it was set to 0.
	 */
   public int drainCapacity() {
   		/**
   		 * before we can set the capacity to 0 we need to store the current value for return
   		 */
      int amtToReturn = capacity;
   		/**
   		 * set capacity to 0
   		 */
      capacity = 0;
   		/**
   		 * fade the station's color
   		 */
      super.setColor(FADED_COLOR);
   		/**
   		 * return previous capacity
   		 */
      return amtToReturn;
   }
	
	/**
	 * outputs a string representing the Energy Station's current status
	 * @return aA string in the format: "Energy Station: loc=X,Y color=[r,g,b] size=S capacity=C"
	 */
   public String toString() {
      return "Energy Station: loc=" + (Math.round(super.getLocationX() * 10)/10) + "," + (Math.round(super.getLocationY() * 10)/10)
         	+ " color=[" + ColorUtil.red(super.getColor()) + "," + ColorUtil.green(super.getColor()) + "," + ColorUtil.blue(super.getColor()) + "]"
         	+ " size=" + super.getSize() + " capacity=" + capacity; 
   }
	
	/**
	 * draws the Energy Station to the screen
	 * @param g - Graphics object used to draw the station
	 * @param pCmpRelPrnt - provides the map view's relative position to its parent
	 */
   public void draw(Graphics g, Point pCmpRelPrnt) {
      int diameter = super.getSize() / 2;
      int drawX = (int)Math.round(super.getLocationX() - diameter + pCmpRelPrnt.getX());
      int drawY = (int)Math.round(super.getLocationY() - diameter + pCmpRelPrnt.getY());
      int numDigits = 0;
      int tempCapacity = capacity;
      do {
         tempCapacity /= 10;
         numDigits++;
      } while (tempCapacity != 0);
      int stringX = (int)Math.round(super.getLocationX() - (9 * numDigits)) + pCmpRelPrnt.getX();
      int stringY = (int)Math.round(super.getLocationY() - 20) + pCmpRelPrnt.getY();
   
      g.setColor(super.getColor());
      if(super.isSelected()) {
         g.drawArc(drawX, drawY, super.getSize(), super.getSize(),0,360);
      } else {
         g.fillArc(drawX, drawY, super.getSize(), super.getSize(),0,360);
      }
      g.setColor(ColorUtil.BLACK);
      g.drawString("" + this.capacity, stringX, stringY);
   }
	
	/**
	 * calls the reachEnergyStation method from myGw
	 * @param otherObject - the game object that is colliding with the energy station
	 */
   public void handleCollision(GameObject otherObject) {
      if(otherObject instanceof Cyborg) {
         super.getMyGw().reachEnergyStation(this, (Cyborg)otherObject);
      }
   }
}
