/**
 * ICollection.java
 * defines the ICollection interface for storing collection of objects
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;

/**
 * ICollection interface defines an interface for storing objects
 */

/**
 * this code came directly from the lecture slides
 */
public interface ICollection {
	/**
	 * adds a new object to the collection
	 * @param newObject - object to add
	 * @return returns true if adding the object was successful.
	 */
   public boolean add(Object newObject);
	/**
	 * returns an iterator for moving through the collection
	 * @return Iterator object for traversing the collection
	 */
   public IIterator getIterator();
}
