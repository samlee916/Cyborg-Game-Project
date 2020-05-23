/**
 * Base.java
 * class file for the Base class used within game
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point;
import com.codename1.ui.Graphics;

/**
 * Base class describes a "base" which is a point along the track that the
 * cyborgs must reach, in sequential order, to complete the track
 * extends the Fixed class
 */
public class Base extends Fixed {
   private static final int BASE_SIZE = 45;
   private static final int BASE_COLOR = ColorUtil.rgb(0,0,255); 
   private int sequenceNumber;
	
	/**
	 * Base constructor, building a new Base object with a specified location and 
	 * sequence number
	 * @param newSequenceNumber - the sequence number of the base. This should be unique from all
	 * other bases on the track
	 * @param startX - the location of the base along the X axis of the game world
	 * @param startY - the location of the base along the Y axis of the game world
	 * @param newGw - GameWorld object to be used for calling collision handlers
	 * @return a new Base object with the provided sequence number and location
	 */
   public Base(int newSequenceNumber, double startX, double startY, GameWorld newGw) {
   	/**
   	 * call the constructor of the Fixed parent class to set the size, color and location of the base
   	 */
      super(Base.BASE_SIZE, Base.BASE_COLOR, startX, startY, newGw);
      sequenceNumber = newSequenceNumber;
   }
	
	/**
	 * get the numerical sequence number of this Base object
	 * @return The base's sequence number
	 */
   public int getSequenceNumber() { 
      return sequenceNumber; }
	
	/**
	 * outputs the base information as a string of the format:
	 * "Base: loc=X,Y color[r,g,b] size=s seqNum=n"
	 * @return A string describing the base's internal fields
	 */
   public String toString() {
      return "Base: loc=" + (Math.round(super.getLocationX() * 10)/10) + "," + (Math.round(super.getLocationY() * 10)/10)
         	+ " color=[" + ColorUtil.red(super.getColor()) + "," + ColorUtil.green(super.getColor()) + "," + ColorUtil.blue(super.getColor()) + "]"
         	+ " size=" + super.getSize() + " seqNum=" + sequenceNumber; 
   }
	
	/**
	 * these methods only serve to override the setColor methods of the parent classes
	 * so that users cannot override the base color
	 */
   public void setColor(int newColor) { }
   public void setColor(int r, int g, int b) { }
	
	/**
	 * draws the base to the game window
	 * @param g - the Graphics object used for drawing
	 * @param pCmpRelPrtn - the origin point of the GameWorld window relative to its parent.
	 */
   public void draw(Graphics g, Point pCmpRelPrnt) {
      int diameter = super.getSize() / 2;
      int drawX = (int)Math.round(super.getLocationX() - diameter) + pCmpRelPrnt.getX();
      int drawY = (int)Math.round(super.getLocationY() - diameter) + pCmpRelPrnt.getY();
      int stringX = (int)Math.round(super.getLocationX() - 8) + pCmpRelPrnt.getX();
      int stringY = (int)Math.round(super.getLocationY() - 24) + pCmpRelPrnt.getY();
   
      int drawXPoints[] = {
         drawX,
         drawX + super.getSize(),
         drawX + (super.getSize() / 2),
         };
      int drawYPoints[] = {
         	drawY,
         	drawY,
         	drawY + super.getSize(),
         };
      g.setColor(super.getColor());
      if(super.isSelected()) {
         g.drawPolygon(drawXPoints, drawYPoints, 3);
      } else {
         g.fillPolygon(drawXPoints, drawYPoints, 3);
      }
      g.setColor(ColorUtil.WHITE);
      g.drawString("" + this.sequenceNumber, stringX, stringY);
   }
	
	/**
	 * if the incoming object is a cyborg, calls the GameWorld method for incrementing the base the cyborg has reached
	 * @param otherObject - the object that the base is colliding with
	 */
   public void handleCollision(GameObject otherObject) {
     
   }
}
