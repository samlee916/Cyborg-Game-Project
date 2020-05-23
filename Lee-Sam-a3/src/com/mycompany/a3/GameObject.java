/**
 * GameObject.java
 * defines the GameObject abstract class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import java.util.Vector;

/**
 * GameObejct abstract class is the core class form with all objects in the game will inherit, storing their
 * location, size, and color
 */
public abstract class GameObject implements IDrawable, ICollider{
   private int size;
   private double locX, locY;
   private int color;
   private GameWorld gw;
   private Vector<GameObject> collisions;
	/**
	 * create a new game object, a game object cannot be created without a size, color, or location,
	 * and so no empty constructor is provided.
	 * @param newSize - the size of the object
	 * @param newColor - the object's color
	 * @param startX - the object's X coordinate at time of create
	 * @param startY - the object's Y coordinate at time of creation
	 * @param newGw - GameWorld object to be used for calling collision handlers
	 * @return a new GameObject
	 */
   public GameObject(int newSize, int newColor, double startX, double startY, GameWorld newGW) {
      size = newSize;
      color = newColor;
      locX = startX;
      locY = startY;
      gw = newGW;
      collisions = new Vector<GameObject>();
   }
	
	/**
	 * provides the GameObject's size
	 * @return integer value defining the object's size
	 */
   public int getSize() { 
      return size; }
	
	/**
	 * provides the object's current X coordinate
	 * @return integer value representing the object's X coordinate
	 */
   public double getLocationX() { 
      return locX; }
	
	/**
	 * provides the object's current Y coordinate
	 * @return integer value representing the object's Y coordinate
	 */
   public double getLocationY() { 
      return locY; }
	
	/**
	 * sets a new location for the object.
	 * @param newX - New X coordinate
	 * @param newY - New Y coordinate
	 */
   public void setLocation(double newX, double newY) {
      locX = newX;
      locY = newY;
   }

	/**
	 * returns the integer value representing the obejct's color
	 * @return integer value representing the color of the object.
	 */
   public int getColor() { 
      return color; }
	
	/**
	 * changes the color of the object
	 * @param newColor - new integer value of the color to set the object to 
	 */
   public void setColor(int newColor) { color = newColor; }
	
	/**
	 * changes the color of the object using Red, Green, and Blue values
	 * @param r - the red component of the new color
	 * @param g - the green component of the new color
	 * @param b - the blue component of the new color
	 */
   public void setColor(int r, int g, int b) { color = ColorUtil.rgb(r,g,b); }
	
	/**
	 * require that sub objects define the "toString method"
	 */
   abstract public String toString();
	
	/**
	 * determines whether or not the current object collides with another given object
	 * @return true: the objects are colliding, false: the objects are not colliding
	 */
   public boolean collidesWith(GameObject otherObject) {
   	/**
   	 * get the size and calculated locations for each object
   	 */
      double myDiameter = this.size / 2.0;
      double otherDiameter = otherObject.size / 2.0;
      double myLeft = locX - myDiameter;
      double myRight = myLeft + this.size;
      double myTop = locY - myDiameter;
      double myBottom = myTop + this.size;
      double otherLeft = otherObject.locX - otherDiameter;
      double otherRight = otherLeft + otherObject.size;
      double otherTop = otherObject.locY - otherDiameter;
      double otherBottom = otherTop + otherObject.size;
      boolean leftRightOverlap, topBottomOverlap;
   	
   	/**
   	 * determine if there is any overlap
   	 */
      leftRightOverlap = !(myRight < otherLeft || myLeft > otherRight);
      topBottomOverlap = !(myBottom < otherTop || myTop > otherBottom);
   
   	
      if(leftRightOverlap && topBottomOverlap) {
         if(!collisions.contains(otherObject)) {
         	/**
         	 * add the objects to each other's collision lists so that they are considered "passing through" list
         	 */
            collisions.add(otherObject);
            otherObject.collisions.add(this);
            return true;
         } else {
            return false;
         }
      	
      } else {
      	/**
      	 * if there is no overlap then we need to ensure the objects are not still considering themselves
      	 * as "passing through" one another
      	 */
         if(collisions.contains(otherObject)) {
            collisions.remove(otherObject);
         }
         return false;
      }
   }
	
	/**
	 * get the game world object that the current GameObject is referencing
	 * @return reference to GameWorld object
	 */
   public GameWorld getMyGw() { 
      return gw; }
}