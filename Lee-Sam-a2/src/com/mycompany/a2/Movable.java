/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;

/**
 * Movable abstract class for objects that require movement within the game. Extends GameObject class.
 * Stores heading as an angle from 0 to 359 degrees.
 */
public abstract class Movable extends GameObject {
	private int heading;
	private int speed;
	
	/**
	 * Create a new object of the Movable
	 * @param newSize - Size of the object
	 * @param newColor - color of the object
	 * @param startX - X coordinate of the object's location
	 * @param startY - Y coordinate of the object's location
	 * @return - A new object of the Movable class
	 */
	public Movable(int newSize, int newColor, double startX, double startY) {
		/**
		  * Call the parent class constructor to set the new object's size, color, and location
		  */
		super(newSize, newColor, startX, startY);
		speed = 0;
		heading = 0;
	}
	
	/**
	 * Abstract method to be defined by sub classes for adjusting the heading of the
	 * movable object
	 */
	public abstract void adjustHeading(); 
	
	/**
	 * Set the object's heading to a new value. Will adjust any value below 0 or above 359 to 
	 * be the same angle but defined within a single 360 degree circle
	 * @param newHeading - Integer value of the new heading in degrees.
	 */
	protected void setHeading(int newHeading) {
		/**
		 * Constrain the heading to 0 - 359 
		 * Uses loops because we don't know how many "full rotations" past 359 or 0 it could be
		 * Each iteration of the loop accounts for one full 360 degree rotation
		 */
		while(newHeading > 359) {
			newHeading -= 360;
		} 
		while(newHeading < 0) {
			newHeading += 360;
		}
		heading = newHeading;
	}
	
	/**
	 * Returns the current heading of the object
	 * @return - Integer value of the current heading
	 */
	public int getHeading() { 
		return heading; 
		}
	
	/**
	 * set the object's speed to a new value
	 * @param newSpeed - Integer value for the new speed
	 */
	protected void setSpeed(int newSpeed) { 
		speed = newSpeed; 
		}
	
	/**
	 * Get the object's current speed
	 * @return - Integer value of the object's current speed
	 */
	public int getSpeed() { 
		return speed; 
		}
	
	/**
	 * Updates the object's location based on the current speed and heading.
	 */
	public void move() {
		/**
		 * Convert heading to radians
		 */
		double radianHeading = Math.toRadians(90 - heading);
		/**
		 * Calculate new coordinates
		 */
		double deltaX = Math.cos(radianHeading) * (double)speed;
		double deltaY = Math.sin(radianHeading) * (double)speed;
		
		/**
		 * Set new location
		 */
		super.setLocation(super.getLocationX() + deltaX, super.getLocationY() + deltaY);
	};
}
