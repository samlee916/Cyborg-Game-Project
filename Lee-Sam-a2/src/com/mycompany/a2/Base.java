/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;

/**
 * Base class describes a "base" which is a point along the track that the
 * Cyborgs must reach, in sequential order, to complete the track
 * Extends the Fixed class
 */
public class Base extends Fixed {
   private static final int BASE_SIZE = 10;
   private static final int BASE_COLOR = ColorUtil.rgb(0,0,255); 
   private int sequenceNumber;
	
	/**
	 * Base constructor, building a new Base object with a specified location and 
	 * sequence number.
	 * @param newSequenceNumber - The sequence number of the base. This should be unique from all
	 * other bases on the track
	 * @param startX - The location of the base along the X axis of the game world
	 * @param startY - The location of the base along the Y axis of the game world
	 * @return A new Base object with the provided sequence number and location
	 */
   public Base(int newSequenceNumber, double startX, double startY) {
   	// Call the constructor of the Fixed parent class to set the size, color and
   	// location of the base.
      super(Base.BASE_SIZE, Base.BASE_COLOR, startX, startY);
      sequenceNumber = newSequenceNumber;
   }
	
	/**
	 * Get the numerical sequence number of this Base object
	 * @return The base's sequence number
	 */
   public int getSequenceNumber() { 
      return sequenceNumber; 
   }
	
	/**
	 * Outputs the base information as a string of the format:
	 * "Base: loc=X,Y color[r,g,b] size=s seqNum=n"
	 * @return A string describing the base's internal fields
	 */
   public String toString() {
      return "Base: loc=" + (Math.round(super.getLocationX() * 10)/10) + "," + (Math.round(super.getLocationY() * 10)/10)
         	+ " color=[" + ColorUtil.red(super.getColor()) + "," + ColorUtil.green(super.getColor()) + "," + ColorUtil.blue(super.getColor()) + "]"
         	+ " size=" + super.getSize() + " seqNum=" + sequenceNumber; 
   }
	
	// These methods only serve to override the setColor methods of the parent classes
	// so that users cannot override the base color
   public void setColor(int newColor) { 
   }
   public void setColor(int r, int g, int b) { 
   }
}
