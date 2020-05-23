/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;

/**
 * IStrategy interface used to define strategies for he non player cyborgs
 */
public interface IStrategy {
	/**
	 * Execute the strategy
	 */
	public void apply(); 
	/**
	 * Return the name of the strategy
	 * @return A string of the strategy's name
	 */
	public String getName();
}
