package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class Cyborg extends Movable implements ISteerable{
	
	/**
	 * Private variables
	 */
	private final int cyborgSize = 40;
	private int steeringDirection = 0;
	private int maximumSpeed = 40;
	private int energyLevel = 20;
	private int energyConsumtionRate = 1;
	private int damageLevel = 0;
	private int lastBaseReached = 1;
	private boolean theCyborgIsDead = false;
	private int maxDamage = 15;
	
	/**
	 * Cyborg constructor
	 */
	public Cyborg(float x, float y) {
		super(x, y);
//      this.setX(x);
//		this.setY(y);
		this.setSpeed(0);
		this.setSize(cyborgSize);
		
		this.setColor(ColorUtil.rgb(255,0,0));
		
	}
	
	/**
	 * Returns maximumSpeed
	 */
	public int getMaximumSpeed() {
		return maximumSpeed;
	}
	
	/**
	 * Sets maximumSpeed
	 */
	public void setMaximumSpeed(int maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}
	
	/**
	 * Returns energyLevel
	 */
	public int getEnergyLevel() {
		return energyLevel;
	}
	
	/**
	 * Sets energyLevel
	 */
	public void setEnergyLevel(int energyLevel) {
		this.energyLevel = energyLevel;
	}
	
	/**
	 * Returns energyConsumtionRate
	 */
	public int getEnergyConsumptionRate() {
		return energyConsumtionRate;
	}
	
	/**
	 * Sets energyConsumtionRate
	 */
	public void setEnergyConsumptionRate(int energyConsumtion ) {
		this.energyConsumtionRate = energyConsumtion;
	}
	
	/**
	 * Returns damageLevel
	 */
	public int getDamageLevel() {
		return damageLevel;
	}
	
	/**
	 * Sets damageLevel
	 */
	public void setDamageLevel(int damage) {
		if(damage < maxDamage)
		{
			this.damageLevel = damage;
		}else {
			this.damageLevel = maxDamage;
		}
			
	}
	
	/**
	 * Returns lastBaseReached
	 */
	public int getLastBaseReached() {
		return lastBaseReached;
	}
	
	/**
	 * Sets lastBaseReached
	 */
	public void setLastBaseReached(int lastBase) {
		this.lastBaseReached = lastBase;
	}
	
	/**
	 * Returns theCyborgIsDead
	 */
	public boolean getTheCyborgIsDead() {
		return theCyborgIsDead;
	}
	
	/**
	 * Sets lastBaseReached
	 */
	public void setTheCyborgIsDead(boolean dead) {
		this.theCyborgIsDead = dead;
	}
	
	/**
	 * Right direction
	 */
	public void turnRight() {
		if(steeringDirection <= 40)
		{
			steeringDirection += 5;
		}
	}
	
	/**
	 * Left direction
	 */
	public void turnLeft() {
		if(steeringDirection >=-40)
		{
			steeringDirection -= 5;
		}
	}
	
	/**
	 * Sets steeringDirection
	 */
	public void setSteeringDirection(int direction) {
		this.steeringDirection = direction;
	}
	
	/**
	 * returns steeringDirection
	 */
	public int getSteeringDirection() {
		return steeringDirection;
	}
	
	/**
	 * Part of ISteerable
	 */
	public void steerHeading(int heading) {
		setHeading(heading);
	}
	
	/**
	 * Sets speed of cyborg
	 */
	public void setCyborgSpeed(int speed) {
		if(speed < 0) {
			this.setSpeed(0);
		}
		if(speed < this.getMaximumSpeed() * this.getEnergyLevel() + .1) {
			this.setSpeed(speed);
		}
		else {
			this.setSpeed((int)(this.getMaximumSpeed() * this.getEnergyLevel() + .1));
		}
		this.isCyborgDead();
	}
	
	/**
	 * updates cyborg speed
	 */
	public void updateCyborgSpeed() {
		if(this.getSpeed() < this.getMaximumSpeed()*this.getEnergyLevel()*this.getEnergyLevel() * 0.1) {
			
		} else {
			this.setSpeed((int)(this.getMaximumSpeed() * this.getEnergyLevel() * .1));
		}
	}
	
	/**
	 * Fading of the colors method
	 */
	public void dimColor(int r, int g, int b) {
//      int newRed= ColorUtil.red(getColor()) - 10;
      //System.out.println(newRed);
//      super.setColor(ColorUtil.rgb(newRed, 0, 0));
      super.setColor(ColorUtil.rgb(r, g, b));
  }
	
	/**
	 * Cyborg collision method
	 */
	/*public void hitCyborg() {
        //this.damageLevel = this.damageLevel - 1;
		this.damageLevel = this.damageLevel + 1;
        this.updateCyborgSpeed();
//        this.setColor(ColorUtil.rgb(255,(10 * (20 - this.getDamageLevel())),0));
        this.dimColor(255,(10 * (20 - this.getDamageLevel())),0);
        if(this.getDamageLevel() <= 0)
        {
                this.damageLevel = 0;
                this.setTheCyborgIsDead(true);
        }

    }*/
	
	public void hitCyborg()
    {
        this.damageLevel = this.damageLevel + 1;
        this.updateCyborgSpeed();
//        this.setColor(ColorUtil.rgb(255,(10 * (20 - this.getDamageLevel())),0));
        this.dimColor(255,(10 * (20 - this.getDamageLevel())),0);
        int damageLimit = 10;
        if(this.getDamageLevel() >= damageLimit)
        {
                //this.damageLevel = 0;
                this.setTheCyborgIsDead(true);
        }

    }
	
	/**
	 * Drone collision method
	 */
    public void hitDrone() {
    	this.damageLevel = this.damageLevel + 1;
        //this.energyLevel = this.energyLevel - 2;
        this.updateCyborgSpeed();
//        this.setColor(ColorUtil.rgb(255,(10 * (20 - this.getEnergyLevel())),(10 * (20 - this.getEnergyLevel()))));
        this.dimColor(255,(10 * (20 - this.getEnergyLevel())),(10 * (20 - this.getEnergyLevel())));
        int damageLimit = 10;
        if(this.getDamageLevel() >= damageLimit)
        {
                //this.damageLevel = 0;
                this.setTheCyborgIsDead(true);
        }
//        if(this.getEnergyLevel() <= 0)
//        {
//                this.energyLevel = 0;
//                this.setTheCyborgIsDead(true);
//        }
        
    }
	
	/*public void hitCyborg()
    {
        this.damageLevel = this.damageLevel - 1;
        this.updateCyborgSpeed();
        this.setColor(ColorUtil.rgb(255,(10 * (20 - this.getDamageLevel())),0));
        if(this.getDamageLevel() <= 0)
        {
                this.damageLevel = 0;
                this.setTheCyborgIsDead(true);
        }

    }
	public void hitDrone()
	{
		this.damageLevel = this.damageLevel - 2;
		this.updateCyborgSpeed();
		this.setColor(ColorUtil.rgb(255,(10 * (20 - this.getEnergyLevel())),(10 * (20 - this.getEnergyLevel()))));
		if(this.getEnergyLevel() <= 0)
		{
				this.energyLevel = 0;
				this.setTheCyborgIsDead(true);
		}
		
	}*/
	
	
	/**
	 * Checks if cyborg is dead and sets speed
	 */
	public void isCyborgDead() {
		if(this.getEnergyLevel() <= 0)
		{
			this.setSpeed(0);
		}
		if(this.damageLevel >= maxDamage)
		{
			this.setSpeed(0);
		}
		if(this.getSpeed() == 0)
		{
			this.theCyborgIsDead = true;
		}
	}
	
	/**
	 * Drains energyLevel
	 */
	public void energyDrain() {
		energyLevel -= energyConsumtionRate;
		if(energyLevel <= 0) {
			this.setSpeed(0);
		}
	}
	
	/**
	 * Resets the Cyborg
	 */
	 public void cyborgReboot() {
		this.setColor(ColorUtil.rgb(255,0,0));
		this.setX(50);
		this.setY(50);
		this.setSpeed(0);
		this.setLastBaseReached(1);
		this.setEnergyLevel(20);
		this.setDamageLevel(0);
		this.setSteeringDirection(0);
		this.setTheCyborgIsDead(false);
		
		
	}
	
	/**
	 * Prints out the heading, speed, size max speed, steering direction, energy level, and cyborg information
	 */
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " heading=" + this.getHeading() + " speed=" + this.getSpeed() + " size=" + this.getSize()
		+ " maxspeed=" + this.getMaximumSpeed() + " steeringDirection=" + this.getSteeringDirection() + " energyLevel=" + this.getEnergyLevel() + " damageLevel=" + this.getDamageLevel(); 
		return "Cyborg: " + parentDesc + myDesc ;
	}

}