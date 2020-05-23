/**
 * ICollider.java
 * defines the ICollider class 
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;

/**
 * ICollider interface for objects that collide with one another
 */

/**
 * this code came directly from the lecture slides
 */
public interface ICollider {
	/**
	 * returns true if the objects collide with one another
	 * @param otherObject - The object to check for collision with the current object
	 * @return true if the objects collide, false if the do not
	 */
   public boolean collidesWith(GameObject otherObject);
	
	/**
	 * process the collision between two objects
	 * @param otherObejct - other object being collided with
	 */
   public void handleCollision(GameObject otherObejct);
}
