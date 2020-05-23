/**
 * Fixed.java
 * defines the Fixed abstract class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;

import com.codename1.ui.geom.Point;

/**
 * Fixed abstract classed used for GameObjects that will not move
 * extends the GameObject class
 */
public abstract class Fixed extends GameObject implements ISelectable{
   private boolean isSelected;
	/**
	 * creates a new Fixed Game object
	 * extends the GameObject class
	 * @param newSize - size of the object to create
	 * @param newColor - color of the object to create
	 * @param startX - the object's X position within the game world
	 * @param startY - the object's Y position within the game world
	 * @param newGw - GameWorld object to be used for calling collision handlers
	 */
   public Fixed(int newSize, int newColor, double startX, double startY, GameWorld newGw) {
   	/**
   	 * all relevant fields are stored within the parent class, so we call the parent's constructor
   	 */
      super(newSize, newColor, startX, startY, newGw);
   }
	/**
	 * overrides the setLocation methods of the parent class to prevent changes to the Fixed object's position
	 */
   public void setLocation(double X, double Y) {
      if(isSelected) {
         super.setLocation(X,  Y);
      }
   }
	
	/**
	 * returns a boolean specifying if the current object has been selected
	 * @return boolean - if true the object is selected, if false the object is not selected
	 */
   public boolean isSelected() { 
      return isSelected; }
	
	/**
	 * change whether or not the object is selected
	 * @param yesNo - value to change the selected state to
	 */
   public void setSelected(boolean yesNo) {
      isSelected = yesNo;
   }
	
	/**
	 * does the object contain the pointer point
	 * @param pPtrRelPrtn - position of the pointer event relative to the container's parent
	 * @param pCmpRelPrtn - position of the container's origin relative to the parent
	 */
   public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
      int diameter = super.getSize() / 2;
      int myLeft = (int)Math.round(super.getLocationX() - diameter + pCmpRelPrnt.getX());
      int myRight = myLeft + super.getSize();
      int myTop = (int)Math.round(super.getLocationY() - diameter + pCmpRelPrnt.getY());
      int myBottom = myTop + super.getSize();
   	
   	
      boolean leftRight = pPtrRelPrnt.getX() > myLeft && pPtrRelPrnt.getX() < myRight;
      boolean topBottom = pPtrRelPrnt.getY() > myTop && pPtrRelPrnt.getY() < myBottom;
      System.out.println(myLeft + ":" + myRight + ":" + leftRight + ", " + myTop + ":" + myBottom + ":" + topBottom + " - " + (leftRight && topBottom));
   	
      return leftRight && topBottom;
   }
}
