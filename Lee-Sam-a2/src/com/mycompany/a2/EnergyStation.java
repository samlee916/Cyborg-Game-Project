/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;

/**
 * EnergyStation class extending the Fixed class to provide a place for cyborgs to refill their energy
 * Extends the Fixed class
 * 
 */
public class EnergyStation extends Fixed {
   private static Random rand = new Random();
   private static final int SIZE_MAX = 41;
   private static final int STATION_COLOR = ColorUtil.rgb(0,255,0);
   private static final int FADED_COLOR = ColorUtil.rgb(128, 200, 128);
   private int capacity;
	
	/**
	 * Construct an object of the EnergyStation class in a random location and random size between 10 and 40
	 * @return a new object of the EnergyStation class
	 */
   public EnergyStation(double locationX, double locationY) {
   	/**
   	 * Call the parent's constructor to set the energy station's size and color 
   	 */ 
      super((rand.nextInt(EnergyStation.SIZE_MAX) + 10), STATION_COLOR, locationX, locationY);
      capacity = super.getSize();		
   }
	
	/**
	 * Get the station's current capacity
	 * @return an integer value representing the station's current capacity level
	 */
   public int getCapacity() { 
      return capacity; 
   }
	
	/**
	 * Drain's the station's capacity, setting it to 0 and outputting the previous value
	 * @return The capacity of the Energy Station before it was set to 0.
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
	 * @return A string in the format: "Energy Station: loc=X,Y color=[r,g,b] size=S capacity=C"
	 */
   public String toString() {
      return "Energy Station: loc=" + (Math.round(super.getLocationX() * 10)/10) + "," + (Math.round(super.getLocationY() * 10)/10)
         	+ " color=[" + ColorUtil.red(super.getColor()) + "," + ColorUtil.green(super.getColor()) + "," + ColorUtil.blue(super.getColor()) + "]"
         	+ " size=" + super.getSize() + " capacity=" + capacity; 
   }
}
