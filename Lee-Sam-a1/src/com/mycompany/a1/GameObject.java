package com.mycompany.a1;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.models.Point;


public abstract class GameObject {
	
	/**
	 * Private variables
	 */
	
   private int color;
	//private float xLocation;
	//private float yLocation;
   private int size;
   private Point location; 
	
	/**
	 * GameObject constructor
	 */
   public GameObject(float x, float y) {
   //		this.size = size;
   //		this.color = color;
      this.location = new Point(x,y);
   }
	
   public GameObject(int size, int color, float x, float y) {
      this.size = size;
      this.color = color;
      this.location = new Point(x,y);
   }
	
	/**
	 * Sets color
	 */
   public void setColor(int c)
   {
      this.color = c;
   }
	
	/**
	 * returns color
	 */
   public int getColor()
   {
      return this.color;
   }
	
//	public void setLocation(float x, float y) {
		//double xCoordinate = x;
		//double yCoordinate = y;
//		location.setX(x);
//		location.setY(y);
		
		//location = new Point(xCoordinate, yCOordinate);
//	}
	
	/**
	 * Returns location
	 */
   public Point getLocation() {
      return location;
   }
	
	/**
	 * Sets x location
	 */
   public void setX(float x) {
      location.setX(x);
   }
	
	/**
	 * Returns x location
	 */
   public float getX()  {
      return location.getX(); 
   }
	
	/**
	 * Gets y location
	 */
   public void setY(float y) {
      location.setY(y);
   }
	
	/**
	 * Returns y location
	 */
   public float getY() {
      return location.getY();
   }
	
	/**
	 * Returns size
	 */
   public int getSize() {
      return this.size; 
   }
	
	/**
	 * Sets size
	 */
   public void setSize(int size) {
      this.size = size;
   }
	
	//Prints out x and y location plus color
   public String toString() {
      String myDesc =  "loc=" + Math.round(location.getX()*10.0)/10.0 + "," + Math.round(location.getY()*10.0)/10.0 + 
         	" color=[" + ColorUtil.red(color) + "," + ColorUtil.green(color) + "," + ColorUtil.blue(color) + "]";
   	
      return myDesc;
   }
	
	
}