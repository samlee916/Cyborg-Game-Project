/**
 * IIterator.java
 * defines the IIterator interface
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
/**
 * this code came directly from the lecture slides
 */
/**
 * IIterator interface for moving through objects in a collection
 *
 */
public interface IIterator {
	/**
	 * determine if the collection has another object beyond the current entry
	 * @return returns true if there is another object
	 */
   public boolean hasNext();
	/**
	 * return the next object in the collection
	 * @return Next object in the collection
	 */
   public Object getNext();
	/**
	 * reset the index to the beginning of the collection
	 */
   public void reset();
	/**
	 * determine if the collection has an object prior to the current one
	 * @return returns true if there is an object prior to the current one
	 */
   public boolean hasPrev();
	/**
	 * returns the previous object in the collection
	 * @return previous object in the collection
	 */
   public Object getPrev();
	/**
	 * returns the current object in the collection
	 * @return object at the current index
	 */
   public Object getCurrent();
}
