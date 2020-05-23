/**
 * MapView.java
 * defines the MapView class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import java.util.Observer;
import com.codename1.ui.plaf.Border;
import java.util.Observable;
import com.codename1.ui.Container;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * MapView class observers the GameWorld, outputting the current state of the map
 */
public class MapView extends Container implements Observer {
   private GameWorld gw;
   private Game myGame;
	/**
	 * creates a new instance of the MapView class
	 */
   public MapView(GameWorld newGw, Game newGame) {
      gw = newGw;
      myGame = newGame;
      this.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.rgb(255,0,0)));
   }
	
	/**
	 * outputs the current state of the GameWorld to the console
	 * @param o - the GameWorld object that the MapView is observing
	 * @param arg - 
	 */
   public void update (Observable o, Object arg) {
      this.repaint();
   }
	
	/**
	 * paints the map view to the screen calling the draw method of all game objects
	 */
   public void paint(Graphics g) {
      IIterator paintIterator = gw.getWorldIterator();
   	/**
   	 * draw a background color
   	 */
      g.setColor(ColorUtil.WHITE);
      g.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
   	/**
   	 * get the mapView origin point
   	 */
      Point mvOrigin = new Point(super.getX(),super.getY());
      while(paintIterator.hasNext()) {
         GameObject curObject = (GameObject)paintIterator.getNext();
         System.out.println(curObject);
         curObject.draw(g, mvOrigin);
      }
   }
	
	/**
	 * process a click event
	 */
   @Override
   public void pointerPressed(int x, int y) {
      Point clickPoint = new Point(x - getParent().getAbsoluteX(), y - getParent().getAbsoluteY());
      Point originPoint = new Point(getX(), getY());
      if(myGame.isPaused()) {
         gw.processPointerClick(clickPoint, originPoint);
      }
   }
	
}
