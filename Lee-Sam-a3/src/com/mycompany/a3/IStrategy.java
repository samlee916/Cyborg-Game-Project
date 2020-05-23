/**
 * IStrategy.java
 * defines the IStrategy interface
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
/**
 * this code came directly from the lecture slides
 */
/**
 * IStrategy interface used to define strategies for he non player cyborgs
 */
public interface IStrategy {
	/**
	 * execute the strategy
	 */
   public void apply(); 
	/**
	 * return the name of the strategy
	 * @return a string of the strategy's name
	 */
   public String getName();
}
