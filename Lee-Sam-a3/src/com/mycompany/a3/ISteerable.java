/**
 * ISteerable.java
 * defines the ISteerable interface
 */
 /**
  * Sam Lee, CSC 133, Professor Scott Gordon
  */
package com.mycompany.a3;

/**
 * Interface for steerable objects
 */
public interface ISteerable {
	
	/**
	 * turn the object
	 * @param direction - the direction of the turn, using the Direction enumeration
	 */
   public void steer(Direction direction);
}
