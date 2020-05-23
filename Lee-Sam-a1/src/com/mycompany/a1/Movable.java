package com.mycompany.a1;

public abstract class Movable extends GameObject {
   private int heading;
   private int speed;
	
	/**
	 * Movable constructor
	 * @param heading
	 */

   public Movable(float x, float y) {
      super(x, y);
   }
	
   public Movable(float x, float y, int heading) {
      super(x, y);
      this.heading =  heading;
   }
	
	/**
	 * Updates location, formula given in the specifications
	 */
   public void move() {
      double deltaX = (float)Math.cos(Math.toRadians(90- this.getHeading())) * this.getSpeed();
      double deltaY = (float)Math.sin(Math.toRadians(90- this.getHeading())) * this.getSpeed();
      this.setX(this.getX() + (float)deltaX );
      this.setY(this.getY() + (float)deltaY );
   
   }
	/**
	 * Setter for heading i.e. needs to be between 0-359 randomly
	 * @param heading
	 */
   public void setHeading(int heading){
      this.heading = heading;
   }
	
	/**
	 * Getter for heading 
	 * @return heading
	 */
   public int getHeading() {
      return this.heading;
   }
	
	/**
	 * Setter for speed
	 * @param d 
	 */
   public void setSpeed(int d) {
      this.speed = d;
   }
	
	/** 
	 * @return speed
	 */
   public int getSpeed() {
      return this.speed;
   	
   }
	
	/**
	 * ToStringmethod 
	 */
   public String toString() {
      String parentDesc = super.toString();
   	//String myDesc = "heading=" + this.getHeading()  + "speed=" +this.getSize();
      return parentDesc; //+myDesc;
   	
   }
	
}