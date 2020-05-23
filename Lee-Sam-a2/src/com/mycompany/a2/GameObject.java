/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;

/**
 * GameObejct abstract class is the core class form with all objects in the game will inherit, storing their
 * location, size, and color
 */
public abstract class GameObject {
   private int size;
   private double locX;
   private double locY;
   private int color;
	
	/**
	 * Create a new game object. A game object cannot be created without a size, color, or location,
	 * and so no empty constructor is provided.
	 * @param newSize - The size of the object
	 * @param newColor - The object's color
	 * @param startX - The object's X coordinate at time of create
	 * @param startY - The object's Y coordinate at time of creation
	 * @return A new GameObject
	 */
   public GameObject(int newSize, int newColor, double startX, double startY) {
      size = newSize;
      color = newColor;
      setLocation(startX, startY);
   }
	
	/**
	 * Provides the GameObject's size
	 * @return Integer value defining the object's size
	 */
   public int getSize() { 
      return size; 
   }
	
	/**
	 * Provides the object's current X coordinate
	 * @return Double value representing the object's X coordinate
	 */
   public double getLocationX() { 
      return locX; 
   }
	
	/**
	 * Provides the object's current Y coordinate
	 * @return Double value representing the object's Y coordinate
	 */
   public double getLocationY() { 
      return locY; 
   }
	
	/**
	 * Sets a new location for the object.
	 * @param newX - New X coordinate
	 * @param newY - New Y coordinate
	 */
   public void setLocation(double newX, double newY) {
      locX = newX;
      locY = newY;
   }
	
	/**
	 * Returns the integer value representing the obejct's color
	 * @return Integer value representing the color of the object.
	 */
   public int getColor() { 
      return color; 
   }
	
	/**
	 * Changes the color of the object
	 * @param newColor - New integer value of the color to set the object to 
	 */
   public void setColor(int newColor) { 
      color = newColor; 
   }
	
	/**
	 * Changes the color of the object using Red, Green, and Blue values
	 * @param r - the red component of the new color
	 * @param g - the green component of the new color
	 * @param b - the blue component of the new color
	 */
   public void setColor(int r, int g, int b) { 
      color = ColorUtil.rgb(r,g,b); 
   }
	
	/**
	 * Require that sub objects define the "toString method"
	 */
   abstract public String toString();
	
}
