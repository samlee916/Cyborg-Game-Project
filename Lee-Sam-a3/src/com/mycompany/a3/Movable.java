/**
 * Movable.java
 * defines the Movable class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;

/**
 * Movable abstract class for objects that require movement within the game
 * extends GameObject class
 * stores heading as an angle from 0 to 359 degrees.
 */
public abstract class Movable extends GameObject {
   private double heading;
   private int speed;
	
	/**
	 * create a new object of the Movable
	 * @param newSize - Size of the object
	 * @param newColor - color of the object
	 * @param startX - X coordinate of the object's location
	 * @param startY - Y coordinate of the object's location
	 * @param newGw - GameWorld object to be used for calling collision handlers
	 * @return - a new object of the Movable class
	 */
   public Movable(int newSize, int newColor, double startX, double startY, GameWorld newGw) {
   	/**
   	 * call the parent class constructor to set the new object's size, color, and location
   	 */
      super(newSize, newColor, startX, startY, newGw);
      speed = 0;
      heading = 0;
   }

	/**
	 * abstract method to be defined by sub classes for adjusting the heading of the
	 * movable object
	 * @param timeMS - milliseconds since the last call
	 */
   public abstract void adjustHeading(int timeMS); 
	
	/**
	 * set the object's heading to a new value
	 * will adjust any value below 0 or above 359 to 
	 * be the same angle but defined within a single 360 degree circle
	 * @param newHeading - integer value of the new heading in degrees
	 */
   protected void setHeading(double newHeading) {
   	/**
   	 * constrain the heading to 0 - 359
   	 * uses loops because we don't know how many "full rotations" past 359 or 0 it could be
   	 * each iteration of the loop accounts for one full 360 degree rotation
   	 */
      while(newHeading >= 360.0) {
         newHeading -= 360.0;
      } 
      while(newHeading < 0.0) {
         newHeading += 360.0;
      }
      heading = newHeading;
   }
	
	/**
	 * returns the current heading of the object
	 * @return - integer value of the current heading
	 */
   public double getHeading() { 
      return heading; }
	
	/**
	 * set the object's speed to a new value
	 * @param newSpeed - Integer value for the new speed
	 */
   protected void setSpeed(int newSpeed) { speed = newSpeed; }
	
	/**
	 * get the object's current speed
	 * @return - integer value of the object's current speed
	 */
   public int getSpeed() { 
      return speed; }
	
	/**
	 * updates the object's location based on the current speed and heading.
	 * @param timeMS - the number of miliseconds that have elapsed since the last call to move
	 */
   public void move(int timeMS) {
   	/**
   	 * convert heading to radians
   	 */
      double radianHeading = Math.toRadians(90 - heading);
      double percentOfSecond = (double)timeMS / 1000.0;
      double distance = speed * percentOfSecond;
   	/**
   	 * calculate new coordinates
   	 */
      double deltaX = (Math.cos(radianHeading) * distance);
      double deltaY = (Math.sin(radianHeading) * distance);
   	
   	/**
   	 * set new location
   	 */
      super.setLocation(super.getLocationX() + deltaX, super.getLocationY() + deltaY);
   };
}
