/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import java.util.Observer;
import com.codename1.ui.plaf.Border;
import java.util.Observable;
import com.codename1.ui.Container;
import com.codename1.charts.util.ColorUtil;

/**
 * MapView class observers the GameWorld, outputting the current state of the map
 */
public class MapView extends Container implements Observer {
	/**
	 * Creates a new instance of the MapView class
	 */
	public MapView() {
		this.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.MAGENTA));
	}
	
	/**
	 * Outputs the current state of the GameWorld to the console
	 * @param o - the GameWorld object that the MapView is observing
	 */
	public void update (Observable o, Object arg) {
		IIterator mapIterator = ((GameWorld)o).getWorldIterator();
		while(mapIterator.hasNext()) {
			System.out.println(mapIterator.getNext());
		}
	}
}
