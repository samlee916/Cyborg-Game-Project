/**
 * Interface for making an object selectable
 */
 /**
  * Sam Lee, CSC 133, Professor Scott Gordon
  */
/**
 * this code came directly from the lecture slides
 */
package com.mycompany.a3;
import com.codename1.ui.geom.Point;
import com.codename1.ui.Graphics;
/**
 * this code came directly from the lecture slides
 */
public interface ISelectable {
	/**
	 * change the state of the selected flag
	 * @param yesNo - new value to set the flag to
	 */
   public void setSelected(boolean yesNo);
	
	/**
	 * return whether or not the current object is selected
	 * @return true if the object is selected, false if the object is not selected
	 */
   public boolean isSelected();
	
	/**
	 * determines if the object contains the click point
	 * @param pPtrRelPrnt - pointer position relative to the parent origin
	 * @param pCmpRelPrtn - origin of container relative to parent
	 * @return true if the object contains the click point, false if the object does not contain the click point
	 */
   public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrtn);
	
	/**
	 * draw the object to the screen
	 * @param g - Graphics object to use for drawing
	 * @param pCmpRelPrnt - origin of the container relative to the parent
	 */
   public void draw(Graphics g, Point pCmpRelPrnt);
}
