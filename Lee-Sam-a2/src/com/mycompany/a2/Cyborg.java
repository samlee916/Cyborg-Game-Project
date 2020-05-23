/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;

/**
 * A class that defines a Cyborg for the game
 * Extends the Movable class and implements ISteerable
 */
public class Cyborg extends Movable implements ISteerable {
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
	 * Create a Cyborg with the specified starting coordinates X,Y and an initial speed and energy level.
	 * @param startX - The X coordinate for the Cyborg's starting location
	 * @param startY - The Y coordinate for the Cyborg's starting location
	 * @param speedMax - the Cyborg's maximum speed
	 * @param startingEnergy - The Cyborg's initial energy level
	 */
   public Cyborg(double startX, double startY, int speedMax, int startingEnergy, int newMaxDamage) {
   	  /**
   	   *  Call parent class constructor to store fields higher up the hierarchy
   	   */
      super(Cyborg.CYBORG_SIZE, Cyborg.CYBORG_COLOR, startX, startY);
   	
     /**
      * Call parent methods to set initial heading and speed
      */
      super.setHeading(0);
      super.setSpeed(5);
   	
      /**
       * Set fields local to Cyborg class
       */
      steeringDirection = 0;
      damageLevel = 0;
      maximumSpeed = speedMax;
      energyLevel = startingEnergy;
      lastBaseReached = 1;
      maxDamage = newMaxDamage;
      energyConsumptionRate = 5;
   }
	
	/**
	 * Changes the Cyborg's steering direction. Will increment the steering amount by 5 degrees. Any attempt to
	 * steer more than the Cyborg class's MAX_STEER constant, or below the negative equivalent, will result in
	 * no change
	 * @param direction - either LEFT or RIGHT from the ISteerable interface's DIRECTION enumeration
	 * @return - Boolean value: true - the attempt to steer was successful: false, the attempt to steer failed
	 */
   public void steer(Direction direction) {
      switch(direction) {
         case LEFT:
            steeringDirection -= Cyborg.STEER_RATE;
            /**
             * Ensure that we have not exceeded the maximum steering rate. Since turning left is a negative heading
             * we first need to invert the max steering rate
             */
            if(steeringDirection < (Cyborg.MAX_STEER * -1)) {
               steeringDirection = (Cyborg.MAX_STEER * -1);
            }
            break;
         case RIGHT:
        	/**
        	 * Ensure that we have not exceed the maximum steering rate in the right direction
        	 */
            steeringDirection += Cyborg.STEER_RATE;
            if(steeringDirection > Cyborg.MAX_STEER) {
               steeringDirection = Cyborg.MAX_STEER;
            }
            break;
      }
   }
	
	/**
	 * Adds energyToAdd to the Cyborg's energy level
	 * @param energyToAdd - Amount of energy to add
	 */
   public void addEnergy(int energyToAdd) {
      energyLevel += energyToAdd;
   }
	
	/**
	 * Adjust the heading of the Cyborg, using the current steeringDirection.
	 * If the Cyborg is disabled, either through lack of energy or excessive damage then nothing
	 * will change.
	 */
   public void adjustHeading() { 
      if(!isDisabled()) {
    	/**
    	 * Only adjust heading the the Cyborg is not disabled
    	 */
         super.setHeading(super.getHeading() + steeringDirection);
      }
   }
	
	/**
	 * Lower the Cyborg's energy level to represent energy consumption
	 */
   public void consumeEnergy() {
      energyLevel -= energyConsumptionRate;
   }
	
	/**
	 * Increments the counter of which base the Cyborg last reached
	 */
   public void incrementLastBase() { lastBaseReached++; }
	
	/**
	 * returns the number of the last base the Cyborg reached.
	 * @return - The number of the last base the Cyborg reached.
	 */
   public int getLastBase() { 
      return lastBaseReached; }
	
	/**
	 * Increase the speed of the Cyborg. Will not increase speed higher than the Cyborg's
	 * current maximum speed based on damage level
	 * @param accelAmount - Amount of speed to add to the Cyborg's current speed
	 * @return - Boolean value telling the calling method if the attempt to accelerate
	 * was successful or not.
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
           * We then need to constrain the new speed to that calculated maximum
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
           * return false, acceleration was not successful, we are already at maximum
           */
         return false;
      }
   }
	
	/**
	 * Decrease the speed of the Cyborg by the specified amount. Will not decrease below 0
	 * @param brakeAmount - Amount to lower the speed of the Cyborg
	 * @return - boolean value informing the calling method whether or not the
	 * attempt to brake was successful
	 */
   public boolean brake(int brakeAmount) {
   	/**
   	 * Need to ensure that we are not already stopped
   	 */
      if(super.getSpeed() > 0) {
      	/**
      	 * if not stopped, lower speed y requested amount
           */
         int newSpeed = super.getSpeed() - brakeAmount;
      	/**
      	 * Constrain speed so that it cannot be below 0
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
      	 * Brake failure, we are already stopped
           */
         return false;
      }
   }
	
	/**
	 * Calculates the Cyborg's current maximum speed based on the current damage level
	 * @return - Current maximum speed of the Cyborg
	 */
   private int calcMaxSpeed() {
      return maximumSpeed * (1 - (damageLevel / maxDamage));
   }
	
	/**
	 * Increase the Cyborg's current damage level. Will not increase beyond Cyborg class's
	 * maximum damage. Will lower speed if new maximum speed is lower than current speed
	 * @param damageAmount - Amount of damage to apply to the Cyborg
	 */
   public void damage(int damageAmount) {
   	/**
   	 * Add new damage to current damage level
   	 */
      damageLevel += damageAmount;
   	
   	/**
   	 * Constrain damage level to be maximum damage. Important for accurately calculating max possible speed
   	 * and disabled status
   	 */
      if(damageLevel > maxDamage) {
         damageLevel = maxDamage;
      }
   	/**
   	 * Also constrain speed if new damage level causes max speed to be lower than current speed
   	 */
      int newMaxSpeed = calcMaxSpeed();
      if(super.getSpeed() > newMaxSpeed) {
         super.setSpeed(newMaxSpeed);;
      }
   	/**
   	 * Increase red value of the Cyborg's color
   	 */
      super.setColor(255 * (damageLevel / maxDamage), ColorUtil.green(CYBORG_COLOR), ColorUtil.blue(CYBORG_COLOR));
   }
	
	/**
	 * Get the Cyborg's current damage level
	 * @return - Current level of damage
	 */
   public int getDamageLevel() {
      return damageLevel;
   }
	
	/**
	 * Get the Cyborg's current steering value
	 * @return - Current steering amount of the Cyborg
	 */
   public int getSteeringDirection() {
      return steeringDirection;
   }
	
	/**
	 * Get the Cyborg's current energy level
	 * @return - Energy level of the Cyborg
	 */
   public int getEnergyLevel() {
      return energyLevel;
   }
	
	/**
	 * Determine whether or not the Cyborg is currently disabled, either through lack of energy or too much damage.
	 * @return - Boolean value, true for disabled, false for not disabled
	 */
   public boolean isDisabled() {
      return energyLevel == 0 || damageLevel >= maxDamage;
   }
	
	/**
	 * Returns the values of the object in a labeled and formatted string
	 * @return - string value of the format: "Cyborg: loc=X,Y color=[r,g,b] heading=H speed=S size=Z maxSpeed=m steeringDirection=D energyLevel=E damageLevel=:"
	 */
   public String toString() {
      return "Cyborg: loc=" + (Math.round(super.getLocationX() * 10)/10) + "," + (Math.round(super.getLocationY() * 10)/10)
         	+ " color=[" + ColorUtil.red(super.getColor()) + "," + ColorUtil.green(super.getColor()) + "," + ColorUtil.blue(super.getColor()) + "]"
         	+ " heading=" + super.getHeading() + " speed=" + super.getSpeed() + " size=" + super.getSize()
         	+ " maxSpeed=" + calcMaxSpeed() + " steeringDirection=" + steeringDirection + " energyLevel=" + energyLevel + " damageLevel=" + damageLevel; 
   }
}
