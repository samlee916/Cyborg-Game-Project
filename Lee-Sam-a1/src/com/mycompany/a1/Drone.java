package com.mycompany.a1;

import java.util.Random;
import com.codename1.charts.util.ColorUtil;

public class Drone extends Movable {
	
    //super or this for set size?
	/**
	 * 
	 * @param x
	 * @param y
	 * @param size
	 * @param heading
	 * @param speed - has to be random between 5-10
	 */
	
	/**
	 * Drone constructor
	 */
	public Drone(float x, float y, int size, int heading, int speed) {
		super(x, y, heading);
		
//		this.setX(x);
//		this.setY(y);
		super.setSize(size);
		this.setSpeed(speed);
		
		super.setColor(ColorUtil.YELLOW);
		
	}
	
	/**
	 * Check bounds and changes heading
	 */
	public void headingChange() {
		if(this.getX() < 1024 && this.getX() > 0 && this.getY() < 768 && this.getY() > 0)
		{
			Random r = new Random();
			int randomN = r.nextInt(11) - 5;
			this.setHeading(this.getHeading() + randomN);
		}else {
			this.setHeading(this.getHeading()+180);
		}
	}
	
	/**
	 * prints out the heading, speed, size, and drone information
	 */
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " heading="+this.getHeading()+ " speed="+ this.getSpeed()+ " size="+this.getSize();
		return "Drone: "+parentDesc + myDesc;
	}
	
	/**
	 * Setter does nothing
	 * @override
	 */
	public void setSize(int size) {};
	public void setColor(int color) {};
	
		
}