/**
 * RandomStrategy.java
 * defines the RandomStrategy class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import java.util.Random;

/**
 * strategy for making the non player cyborg move in random directions
 */
public class RandomStrategy implements IStrategy{
   NonPlayerCyborg myCyborg;
   private static Random rand = new Random();
   private int counterMax;
   private int counter;
	
	/**
	 * create a new instance of the RandomStrategy class
	 * @param newNpc - target NonPlayerCyborg to apply the strategy to
	 */
   public RandomStrategy(NonPlayerCyborg newNpc) {
      myCyborg = newNpc;
      reset();
   }
	
	/**
	 * apply the strategy to the target NonPlayerCyborg
	 * if the counter has been reached then set a new heading, otherwise increment the counter
	 */
   public void apply() {
      if(counter >= counterMax) {
         reset();
      }
      else {
         counter++;
      }
   }
	
	/**
	 * return "Random"
	 */
   public String getName() { 
      return "Random"; }
	
	/**
	 * set a new heading and reset the counter to 0, and get a new counter target
	 */
   private void reset() {
      myCyborg.setHeading(rand.nextDouble() * 360.0);
      counterMax = rand.nextInt(6000) + 2000;
      counter = 0;
   }

}
