/**
 * Cyborg.java
 * defines the Cyborg class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
/**
 * a class that defines a Cyborg for the game
 * extends the Movable class and implements ISteerable
 */
public abstract class Cyborg extends Movable implements ISteerable {
   private static final int CYBORG_SIZE = 40;
   private static final int STEER_RATE = 5;
   private static final int MAX_STEER = 40;
   private static final int CYBORG_COLOR = ColorUtil.rgb(0,200,150);
	
   private int steeringDirection;
   private int maximumSpeed;
   private int energyLevel;
   private int energyConsumptionRate;
   private int damageLevel;
   private int lastBaseReached;
   private int maxDamage;
	
	/**
	 * create a Cyborg with the specified starting coordinates X,Y and an initial speed and energy level.
	 * @param startX - the X coordinate for the Cyborg's starting location
	 * @param startY - the Y coordinate for the Cyborg's starting location
	 * @param speedMax - the Cyborg's maximum speed
	 * @param startingEnergy - the Cyborg's initial energy level
	 */
   public Cyborg(double startX, double startY, int speedMax, int startingEnergy, int newMaxDamage, int initialBase, GameWorld newGw) {
   	/**
   	 * call parent class constructor to store fields higher up the hierarchy
   	 */
      super(Cyborg.CYBORG_SIZE, Cyborg.CYBORG_COLOR, startX, startY, newGw);
   	
   	/**
   	 * call parent methods to set initial heading and speed
   	 */
      super.setHeading(0);
      super.setSpeed(30);
   	
   	/**
   	 * set fields local to Cyborg class
   	 */
      steeringDirection = 0;
      damageLevel = 0;
      maximumSpeed = speedMax;
      energyLevel = startingEnergy;
      lastBaseReached = initialBase;
      maxDamage = newMaxDamage;
      energyConsumptionRate = 1;
   }

	/**
	 * changes the Cyborg's steering direction
	 * will increment the steering amount by 5 degrees
	 * any attempt to steer more than the Cyborg class's MAX_STEER constant, or below the negative equivalent, 
	 * will result in no change
	 * @param direction - either LEFT or RIGHT from the ISteerable interface's DIRECTION enumeration
	 * @return - boolean value: true if the attempt to steer was successful and false if the attempt to steer failed
	 */
   public void steer(Direction direction) {
      switch(direction) {
         case LEFT:
            steeringDirection -= Cyborg.STEER_RATE;
         	/**
         	 * ensure that we have not exceeded the maximum steering rate
         	 * since turning left is a negative heading
         	 * we first need to invert the max steering rate
         	 */
            if(steeringDirection < (Cyborg.MAX_STEER * -1)) {
               steeringDirection = (Cyborg.MAX_STEER * -1);
            }
            break;
         case RIGHT:
         	/**
         	 * ensure that we have not exceed the maximum steering rate in the right direction
         	 */
            steeringDirection += Cyborg.STEER_RATE;
            if(steeringDirection > Cyborg.MAX_STEER) {
               steeringDirection = Cyborg.MAX_STEER;
            }
            break;
      }
   }
	
	/**
	 * adds energyToAdd to the Cyborg's energy level
	 * @param energyToAdd - amount of energy to add
	 */
   public void addEnergy(int energyToAdd) {
      energyLevel += energyToAdd;
   }
	
	/**
	 * adjust the heading of the Cyborg, using the current steeringDirection.
	 * if the Cyborg is disabled, either through lack of energy or excessive damage then nothing
	 * will change
	 */
   public void adjustHeading(int timeMS) { 
      if(!isDisabled()) {
      	/**
      	 * only adjust heading the the Cyborg is not disabled
      	 */
         double percentOfSecond = timeMS / 1000.0;
         super.setHeading(super.getHeading() + (steeringDirection * percentOfSecond));
      }
   }
	
	/**
	 * lower the Cyborg's energy level to represent energy consumption
	 */
   public void consumeEnergy() {
      energyLevel -= energyConsumptionRate;
      if(energyLevel < 0) {
         energyLevel = 0;
      }
   }
	
	/**
	 * increments the counter of which base the Cyborg last reached
	 */
   public void incrementLastBase() { lastBaseReached++; }
	
	/**
	 * returns the number of the last base the Cyborg reached
	 * @return - the number of the last base the Cyborg reached
	 */
   public int getLastBase() { 
      return lastBaseReached; }
	
	/**
	 * increase the speed of the Cyborg. Will not increase speed higher than the Cyborg's
	 * current maximum speed based on damage level
	 * @param accelAmount - amount of speed to add to the Cyborg's current speed
	 * @return - boolean value telling the calling method if the attempt to accelerate
	 * was successful or not
	 */
   public boolean accelerate(int accelAmount) {
   	/**
   	 * First we need to get the current maximum speed based on damage level
   	 */
      int currentMaxSpeed = calcMaxSpeed();
   	
   	/**
   	 * ensure we are not already at calculated maximum
   	 */
      if(super.getSpeed() < currentMaxSpeed) {
      	/**
      	 * increase speed by the requested amount
      	 */
         int newSpeed = super.getSpeed() + accelAmount;
      	/**
      	 * we then need to constrain the new speed to that calculated maximum
      	 */
         if(newSpeed > currentMaxSpeed) {
            newSpeed = currentMaxSpeed;
         }
      	/**
      	 * set speed using parent method
      	 */
         super.setSpeed(newSpeed);;
      	/**
      	 * return true, acceleration successful
      	 */
         return true;
      } else {
      	/**
      	 * return false, acceleration was not successful
      	 */
         return false;
      }
   }
	
	/**
	 * decrease the speed of the Cyborg by the specified amount
	 * will not decrease below 0
	 * @param brakeAmount - amount to lower the speed of the Cyborg
	 * @return - boolean value informing the calling method whether or not the
	 * attempt to brake was successful
	 */
   public boolean brake(int brakeAmount) {
   	/**
   	 * need to ensure that we are not already stopped
   	 */
      if(super.getSpeed() > 0) {
      	/**
      	 * if not stopped, lower speed y requested amount
      	 */
         int newSpeed = super.getSpeed() - brakeAmount;
      	/**
      	 * constrain speed so that it cannot be below 0
      	 */
         if(newSpeed < 0) {
            newSpeed = 0;
         }
      	/**
      	 * use parent method to set new speed
      	 */
         super.setSpeed(newSpeed);
         return true;
      } else {
      	/**
      	 * brake failure, we are already stopped
      	 */
         return false;
      }
   }
	
	/**
	 * calculates the Cyborg's current maximum speed based on the current damage level
	 * @return - current maximum speed of the Cyborg
	 */
   private int calcMaxSpeed() {
      return (int)(maximumSpeed * (1.0 - ((double)damageLevel / (double)maxDamage / 2.0)));
   }
	
	/**
	 * increase the Cyborg's current damage level
	 * will not increase beyond Cyborg class's maximum damage
	 * will lower speed if new maximum speed is lower than current speed
	 * @param damageAmount - amount of damage to apply to the Cyborg
	 */
   public void damage(int damageAmount) {
   	/**
   	 * add new damage to current damage level
   	 */
      damageLevel += damageAmount;
   	
   	/**
   	 * constrain damage level to be maximum damage. Important for accurately calculating max possible speed
   	 * and disabled status
   	 */
      if(damageLevel > maxDamage) {
         damageLevel = maxDamage;
      }
   	/**
   	 * also constrain speed if new damage level causes max speed to be lower than current speed
   	 */
      int newMaxSpeed = calcMaxSpeed();
      if(super.getSpeed() > newMaxSpeed) {
         super.setSpeed(newMaxSpeed);;
      }
   	
   	/**
   	 * increase red value of the Cyborg's color
   	 */
      super.setColor((int)(255 * ((double)damageLevel / (double)maxDamage)), ColorUtil.green(CYBORG_COLOR), ColorUtil.blue(CYBORG_COLOR));
   }
	
	/**
	 * get the Cyborg's current damage level
	 * @return - current level of damage
	 */
   public int getDamageLevel() {
      return damageLevel;
   }
	
	/**
	 * get the Cyborg's current steering value
	 * @return - current steering amount of the Cyborg
	 */
   public int getSteeringDirection() {
      return steeringDirection;
   }
	
	/**
	 * get the Cyborg's current energy level
	 * @return - energy level of the Cyborg
	 */
   public int getEnergyLevel() {
      return energyLevel;
   }
	
	/**
	 * determine whether or not the Cyborg is currently disabled, either through lack of energy or too much damage.
	 * @return - boolean value, true for disabled, false for not disabled
	 */
   public boolean isDisabled() {
      return energyLevel == 0 || damageLevel >= maxDamage;
   }
	
	/**
	 * returns the values of the object in a labeled and formatted string
	 * @return - string value of the format: "Cyborg: loc=X,Y color=[r,g,b] heading=H speed=S size=Z maxSpeed=m steeringDirection=D energyLevel=E damageLevel=:"
	 */
   public String toString() {
      return "Cyborg: loc=" + (Math.round(super.getLocationX() * 10)/10) + "," + (Math.round(super.getLocationY() * 10)/10)
         	+ " color=[" + ColorUtil.red(super.getColor()) + "," + ColorUtil.green(super.getColor()) + "," + ColorUtil.blue(super.getColor()) + "]"
         	+ " heading=" + super.getHeading() + " speed=" + super.getSpeed() + " size=" + super.getSize()
         	+ " maxSpeed=" + calcMaxSpeed() + " steeringDirection=" + steeringDirection + " energyLevel=" + energyLevel + " damageLevel=" + damageLevel; 
   }
	
	/**
	 * process a collision with another game object
	 * @param otherObject - other object that is colliding with the current object
	 */
   public void handleCollision(GameObject otherObject) {
      if(otherObject instanceof Cyborg) {
         super.getMyGw().cyborgCollide(this, (Cyborg)otherObject);
      } 
      if(otherObject instanceof EnergyStation) {
         super.getMyGw().reachEnergyStation((EnergyStation)otherObject, this);
      }
   }
}
