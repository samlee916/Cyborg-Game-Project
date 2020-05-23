/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;

/**
 * Fixed abstract classed used for GameObjects that will not move
 * Extends the GameObject class
 */
public abstract class Fixed extends GameObject{

	/**
	 * Creates a new Fixed Game object. Extends the GameObject class
	 * @param newSize Size of the object to create
	 * @param newColor Color of the object to create
	 * @param startX The object's X position within the game world
	 * @param startY The object's Y position within the game world
	 */
   public Fixed(int newSize, int newColor, double startX, double startY) {
   	//All relevant fields are stored within the parent class, so we call the parent's constructor
      super(newSize, newColor, startX, startY);
   }
	
	/**
	 * Overrides the setLocation method of the parent class to prevent changes to the Fixed object's position
	 */
   public void setLocation() {
   	
   }
}
