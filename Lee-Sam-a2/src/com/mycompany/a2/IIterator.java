/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;

/**
 * IIterator interface for moving through objects in a collection
 *This was copy and used straight from the lecture notes
 */
public interface IIterator {
	/**
	 * Determine if the collection has another object beyond the current entry
	 * @return returns true if there is another object
	 */
	public boolean hasNext();
	/**
	 * Return the next object in the collection
	 * @return Next object in the collection
	 */
	public Object getNext();
	/**
	 * Reset the index to the beginning of the collection
	 */
	public void reset();
	/**
	 * Determine if the collection has an object prior to the current one
	 * @return returns true if there is an object prior to the current one
	 */
	public boolean hasPrev();
	/**
	 * Returns the previous object in the collection
	 * @return Previous object in the collection
	 */
	public Object getPrev();
	/**
	 * Returns the current object in the collection
	 * @return Object at the current index
	 */
	public Object getCurrent();
}
