package com.mycompany.a1;

public abstract class Fixed extends GameObject {	

	/**
	 * Fixed constructor
	 */
	public Fixed(float x, float y) {
		super(x, y);
//		super.setX(x);
//		super.setY(y);
	}
	
	/**
	 * Fixed objects should not be able to edit their position.
	   Empty body implementation
	 */
	public void setX(float x){}
	public void setY(float y){}
}