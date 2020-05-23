/**
 * GameObjectCollection.java
 * defines the GameObjectCollection class for storing the game objects
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import java.util.ArrayList;

/**
 * GameObjctCollection class implements the ICollection interface
 * to store a collection of GameObjects and provides an iterator for moving through the 
 * collection
 */
public class GameObjectCollection implements ICollection{
   private ArrayList<GameObject> objectList;
	
	/**
	 * creates a new GameObjectCollection with an empty but initialized 
	 * collection
	 */
   public GameObjectCollection() {
      objectList = new ArrayList<GameObject>();
   }
	
	/**
	 * adds a new object to the collection
	 * will return true if add is successful
	 * will return false if the object being added is not a GameObject
	 * @param newObject - Object to be added
	 * must be a GameObject
	 */
   public boolean add(Object newObject) {
   	/**
   	 * this collection only stores GameObjects, so we need to verify that
   	 * the Generic object is the correct type
   	 */
      if(newObject instanceof GameObject) {
         objectList.add((GameObject)newObject);
         return true;
      } else {
         return false;
      }
   }
	
	/**
	 * get an iterator to use to move through the collection
	 * @return 
	 */
   public IIterator getIterator() {
      return new GameObjectIterator();
   }
	
	/**
	 * GameObjectIterator implements the IIterator interface and is used to move through the 
	 * object collection
	 */
   private class GameObjectIterator implements IIterator {
      private int curIndex;
   	
   	/**
   	 * Create a new instance of the GameObjectIterator
   	 */
      public GameObjectIterator() {
         curIndex = -1;
      }
   	
   	/**
   	 * returns true of there is another object in the collection beyond the current one
   	 */
      public boolean hasNext() {
         if(objectList.size() <= 0 || objectList.size() - 1 == curIndex) {
            return false;
         } else {
            return true;
         }
      }
   	
   	/**
   	 * returns the next object in the collection, moving the index forward
   	 */
      public Object getNext() {
         curIndex++;
         return getCurrent();
      }
   	
   	/**
   	 * returns true if there is an object prior to the current index in the collection
   	 */
      public boolean hasPrev() {
         if(curIndex <= 0) {
            return false;
         } else {
            return true;
         }
      }
   	
   	/**
   	 * returns the previous object in the collection, moving the index backwards
   	 */
      public Object getPrev() {
         curIndex--;
         return getCurrent();
      }
   	
   	/**
   	 * returns the object at the colelction's current index
   	 */
      public Object getCurrent() {
         return objectList.get(curIndex);
      }
   	
   	/**
   	 * returns the index to the starting location
   	 */
      public void reset() {
         curIndex = -1;
      }
   }
}
