/**
 * Drove.java
 * defines the Drone class
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
 * Drone class implements a class for Drone objects within the game
 * extends the Movable class
 */
public class Drone extends Movable {
   private static Random rand = new Random();
   private static final int SIZE_MAX = 40;
   private static final int STEER_MAX = 10;
   private static final int DRONE_COLOR = ColorUtil.rgb(0,0,0);
	
	/**
	 * creates a new drone object of a random size in the location specified by the input parameters
	 * @param startX - double representing the new drone's initial X coordinate
	 * @param startY - double representing the new drone's initial Y coordinate
	 * @param newGw - GameWorld object to be used for calling collision handlers
	 * @return a new Drone object
	 */
   public Drone(double startX, double startY, GameWorld newGw) {
   	/**
   	 * call the parent constructor with a random size and the Drone color
   	 */
      super(rand.nextInt(Drone.SIZE_MAX) + 10, Drone.DRONE_COLOR, startX, startY, newGw);
   	/**
   	 * call parent methods to set an initial heading and speed
   	 */
      super.setHeading(rand.nextInt(360));
      super.setSpeed(rand.nextInt(10) + 30);
   }
	
	/**
	 * adjust the heading of the Drone, randomly selecting a new direction within 5 degrees of the current 
	 * heading
	 * @param timeMS the number of milliseconds since the last call to adjust heading
	 */
   public void adjustHeading(int timeMS) {
   	/**
   	 * determine the amount of deflection between -5 and 5 degrees
   	 */
      int deflection = rand.nextInt(Drone.STEER_MAX) - 5; 
      double percentOfSecond = timeMS / 1000;
   	/**
   	 * use the parent class setHeading method to set the new heading
   	 */
      super.setHeading(super.getHeading() + (deflection * percentOfSecond));
   }
	
	/**
	 * checks the Drone's position within the given limits and alter the heading if it is at the edge, reflecting the drone off the wall
	 * angle between the wall and the drone's heading should remain consistent, but the heading will now be pointing back 
	 * into the frame instead of out of it.
	 * @param maxWidth - double representing the maximum X value
	 * @param maxHeight - double representing the maximum Y value
	 */
   public void checkEdgeHit(double maxWidth, double maxHeight) {
   	/**
   	 * check if the drone has hit one of the side walls, and invert the heading if so, keeping the angle of the heading
   	 * to the edge consistent, but now pointing back inwards instead of outwards
   	 */
      if(super.getLocationX() <= 0.0 || super.getLocationX() >= maxWidth) {
         super.setHeading(180 - (super.getHeading() - 180));
      }
   	/**
   	 * check if the drone has hit the top or bottom of the frame, and if so inverting the heading,
   	 * keeping the angle between the heading and the wall consistent, but now pointing into the frame
   	 */
      if(super.getLocationY() <= 0.0 || super.getLocationY() >= maxHeight) {
         super.setHeading(90 + (90 - super.getHeading()));
      }
   }
	
	/**
	 * output the Drone's information as a string
	 * @return a string value of the format "Drone: loc=X,Y color=[r,g,b] heading=H speed=S size=Z"
	 */
   public String toString() {
      return "Drone: loc=" + (Math.round(super.getLocationX() * 10)/10) + "," + (Math.round(super.getLocationY() * 10)/10)
         	+ " color=[" + ColorUtil.red(super.getColor()) + "," + ColorUtil.green(super.getColor()) + "," + ColorUtil.blue(super.getColor()) + "]"
         	+ " heading=" + super.getHeading() + " speed=" + super.getSpeed() + " size=" + super.getSize(); 
   }
	
	/**
	 * draws the drone onto the game world
	 * @param g - the Graphics object used to draw
	 * @param pCmpRelPrtn - the origin point of the GameWorld window relative to its parent.
	 */
   public void draw(Graphics g, Point pCmpRelPrnt) {
      int diameter = super.getSize() / 2;
      int drawX = (int)Math.round(super.getLocationX() - diameter + pCmpRelPrnt.getX());
      int drawY = (int)Math.round(super.getLocationY() - diameter + pCmpRelPrnt.getY());
   
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
      g.drawPolygon(drawXPoints, drawYPoints, 3);
   }
	
	/**
	 * if the other object is a Cyborg then call GameWorld's droneCollide method
	 * @param otherObject - object that is colliding with the drone
	 */
   public void handleCollision(GameObject otherObject) {
      
   }
	
	/**
	 * override the parent object's setColor methods so that the drone's color can not be changed
	 */
   public void setColor(int newColor) {}
   public void setColor(int r, int g, int b) {}
}
