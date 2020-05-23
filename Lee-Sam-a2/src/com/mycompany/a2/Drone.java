/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;

/**
 * Drone class implements a class for Drone objects within the Cyborg Tracks game. Extends the Movable class
 * 
 */
public class Drone extends Movable {
	private static Random rand = new Random();
	private static final int SIZE_MAX = 41;
	private static final int STEER_MAX = 11;
	private static final int DRONE_COLOR = ColorUtil.rgb(255,0,0);
	
	/**
	 * Creates a new Drone object of a random size in the location specified by the input parameters
	 * @param startX - Double representing the new drone's initial X coordinate
	 * @param startY - Double representing the new drone's initial Y coordinate
	 * @return a new Drone object
	 */
	public Drone(double startX, double startY) {
		/**
		 * Call the parent constructor with a random size and the Drone color
		 */
		super(rand.nextInt(Drone.SIZE_MAX) + 10, Drone.DRONE_COLOR, startX, startY);
		/**
		 * Call parent methods to set an initial heading and speed
		 */
		super.setHeading(rand.nextInt(360));
		super.setSpeed(rand.nextInt(5) + 5);
	}
	
	/**
	 * Adjust the heading of the Drone, randomly selecting a new direction within 5 degrees of the current 
	 * heading
	 */
	public void adjustHeading() {
		/**
		 * determine the amount of deflection between -5 and 5 degrees
		 */
		int deflection = rand.nextInt(Drone.STEER_MAX) - 5;
		/**
		 * Use the parent class setHEading method to set the new heading
		 */
		super.setHeading(super.getHeading() + deflection);
	}
	
	/**
	 * Checks the Drone's position within the given limits and alter the heading if it is at the edge, reflecting the drone off the wall
	 * Angle between the wall and the drone's heading should remain consistent, but the heading will now be pointing back 
	 * into the frame instead of out of it.
	 * @param maxWidth - double representing the maximum X value
	 * @param maxHeight - double representing the maximum Y value
	 */
	public void checkEdgeHit(double maxWidth, double maxHeight) {
		/**
		 * Check if the drone has hit one of the side walls, and invert the heading if so, keeping the angle of the heading
		 * to the edge consistent, but now pointing back inwards instead of outwards
		 */
		if(super.getLocationX() <= 0.0 || super.getLocationX() >= maxWidth) {
			super.setHeading(180 - (super.getHeading() - 180));
		}
		/**
		 * Check if the drone has hit the top or bottom of the frame, and if so inverting the heading,
		 * keeping the angle between the heading and the wall consistent, but now pointing into the frame
		 */
		if(super.getLocationY() <= 0.0 || super.getLocationY() >= maxHeight) {
			super.setHeading(90 + (90 - super.getHeading()));
		}
	}
	
	/**
	 * Output the Drone's information as a string
	 * @return a sting value of the format "Drone: loc=X,Y color=[r,g,b] heading=H speed=S size=Z"
	 */
	public String toString() {
		return "Drone: loc=" + (Math.round(super.getLocationX() * 10)/10) + "," + (Math.round(super.getLocationY() * 10)/10)
				+ " color=[" + ColorUtil.red(super.getColor()) + "," + ColorUtil.green(super.getColor()) + "," + ColorUtil.blue(super.getColor()) + "]"
				+ " heading=" + super.getHeading() + " speed=" + super.getSpeed() + " size=" + super.getSize(); 
	}
	
	/**
	 * override the parent object's setColor methods so that the drone's color can not be changed
	 */
	public void setColor(int newColor) {
		
	}
	public void setColor(int r, int g, int b) {
	
	}
}
