/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;

/**
 * ICollection interface defines an interface for storing objects
 *This was copy and used straight from the lecture notes
 */
public interface ICollection {
	/**
	 * Adds a new object to the collection
	 * @param newObject - Object to add
	 * @return returns true if adding the object was successful.
	 */
	public boolean add(Object newObject);
	/**
	 * Returns an iterator for moving through the collection
	 * @return IITerator object for traversing the collection
	 */
	public IIterator getIterator();
}
