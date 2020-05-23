/**
 * IDrawable.java
 * defines the IDrawable interface
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * IDrawable interface for an object that can be drawn
 */

/**
 * this code came directly from the lecture slides
 */
public interface IDrawable {
	/**
	 * will draw the current object to the screen
	 * @param g - Graphics object to use for drawing
	 * @param pCmpRelPrnt - origin of the container relative to the parent
	 */
   public void draw(Graphics g, Point pCmpRelPrnt);
}
