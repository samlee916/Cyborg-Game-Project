/**
 * GameWorld.java
 * defines the GameWorld class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import com.codename1.ui.Display;
import com.codename1.ui.geom.Point;
import com.codename1.ui.Command;
import java.util.Random;

import com.codename1.ui.Dialog;

import java.util.Observable;

/**
 * GameWorld class is the main data storage class of the Cyborg game, holding all 
 * GameObjects and providing facilities for program control to modify and update the GameWorld state
 */
public class GameWorld extends Observable {
   private static Random rand = new Random();
   private static final double DEFAULT_WIDTH = 1000.0;
   private static final double DEFAULT_HEIGHT = 1000.0;
   private final int ACCEL_AMOUNT = 5;
   private final int CYBORG_COLLIDE_DAMAGE = 40;
   private int playerLives;
   private int gameClock;
   private boolean soundOn;
   private GameObjectCollection gameObjects;
   private int worldWidth;
   private int worldHeight;
   private int tickCounter;
   private boolean isRepositioning;
   private Sound CrashSound;
   private Sound PowerUpSound;
   private Sound ExplodeSound;
   private BackgroundSound BackgroundSoundSound;
   private Game myGame;
	/**
	 * create a new GameWorld object
	 * does not initialize the game world, only sets global variables
	 * @return a new GameWorld object with playerLives set to 3 and game Clock set to 0
	 */
   public GameWorld(Game newGame) {
      gameClock = 0;
      playerLives = 3;
      soundOn = true;
      worldWidth = (int)GameWorld.DEFAULT_WIDTH;
      worldHeight = (int)GameWorld.DEFAULT_HEIGHT;
      isRepositioning = false;
      CrashSound = new Sound("cyborgcrash.wav");
      PowerUpSound = new Sound("energyhit.wav");
      ExplodeSound = new Sound("explosion.wav");
      BackgroundSoundSound = new BackgroundSound("backgroundsound.wav");
      myGame = newGame;
   }
	
	/**
	 * modify the game world boundaries
	 * @param newWidth - the new width of the game world
	 * @param newHeight - the new height of the game world
	 */
   public void setBoundaries(int newWidth, int newHeight) {
      worldWidth = newWidth;
      worldHeight = newHeight;
   }
	
	/**
	 * initializes the game world, creating bases, drones, energy stations, a player cyborg and three non player cyborgs
	 */
   public void init() {
      gameObjects = new GameObjectCollection();
      isRepositioning = false;
   	
   	/**
   	 * create the bases of the track
   	 */
      gameObjects.add(new Base(1, (int)((100 / GameWorld.DEFAULT_WIDTH) * worldWidth), (int)((75 / GameWorld.DEFAULT_HEIGHT) * worldHeight), this));
      gameObjects.add(new Base(2, (int)((50 / GameWorld.DEFAULT_WIDTH) * worldWidth), (int)((350 / GameWorld.DEFAULT_HEIGHT) * worldHeight), this));
      gameObjects.add(new Base(3, (int)((512 / GameWorld.DEFAULT_WIDTH) * worldWidth), (int)((300 / GameWorld.DEFAULT_HEIGHT) * worldHeight), this));
      gameObjects.add(new Base(4, (int)((350 / GameWorld.DEFAULT_WIDTH) * worldWidth), (int)((500 / GameWorld.DEFAULT_HEIGHT) * worldHeight), this));
      gameObjects.add(new Base(5, (int)((700 / GameWorld.DEFAULT_WIDTH) * worldWidth), (int)((400 / GameWorld.DEFAULT_HEIGHT) * worldHeight), this));
      gameObjects.add(new Base(6, (int)((920 / GameWorld.DEFAULT_WIDTH) * worldWidth), (int)((150 / GameWorld.DEFAULT_HEIGHT) * worldHeight), this));
   	
   	/**
   	 * create the player PlayerCyborg at the location of the first base
   	 */
      PlayerCyborg playerCyborg = PlayerCyborg.getPlayerCyborg(this);
      playerCyborg.setLocation((int)((100 / GameWorld.DEFAULT_WIDTH) * worldWidth),(int)((75 / GameWorld.DEFAULT_HEIGHT) * worldHeight));
      gameObjects.add(playerCyborg);
      PlayerCyborg.reset(this);
      tickCounter = 0;
   	
   	/**
   	 * create the non-player cyborgs, assign them strategies, and add them to the collection
   	 */
      NonPlayerCyborg newCyborg1 = new NonPlayerCyborg((int)((15 / GameWorld.DEFAULT_WIDTH) * worldWidth), (int)((75 / GameWorld.DEFAULT_HEIGHT) * worldHeight), this);
      newCyborg1.changeStrategy(new ReachStrategy(this, newCyborg1));
      NonPlayerCyborg newCyborg2 = new NonPlayerCyborg((int)((185 / GameWorld.DEFAULT_WIDTH) * worldWidth), (int)((75 / GameWorld.DEFAULT_HEIGHT) * worldHeight), this);
      newCyborg2.changeStrategy(new AttackStrategy(playerCyborg, newCyborg2));
      NonPlayerCyborg newCyborg3 = new NonPlayerCyborg((int)((100 / GameWorld.DEFAULT_WIDTH) * worldWidth), (int)((15 / GameWorld.DEFAULT_HEIGHT) * worldHeight), this);
      newCyborg3.changeStrategy(new RandomStrategy(newCyborg3));
      gameObjects.add(newCyborg1);
      gameObjects.add(newCyborg2);
      gameObjects.add(newCyborg3);
   	
   	/**
   	 * create between 2 and 8 energy stations
   	 */
      for(int counter = 0; counter < rand.nextInt(7) + 2; counter++) {
         gameObjects.add(new EnergyStation(rand.nextInt(worldWidth), rand.nextInt(worldHeight), this));
      }
   	
   	/**
   	 * create between 2 and 4 drone
   	 */
      for(int counter = 0; counter< rand.nextInt(3) + 2; counter++) {
         gameObjects.add(new Drone(rand.nextInt(worldWidth), rand.nextInt(worldHeight), this));
      }
   	/**
   	 * start the background audio
   	 */
      if(soundOn) {
         BackgroundSoundSound.play();
      }
      setChanged();
      notifyObservers();
   }
	
	/**
	 * accelerate the playerCyborg
	 */
   public void accelerate() {
      PlayerCyborg playerCyborg = getPlayerCyborg();
   	
      if(playerCyborg != null) {
         if(playerCyborg.accelerate(ACCEL_AMOUNT)) {
            System.out.println("PlayerCyborg has accelerated");
         } else {
            System.out.println("PlayerCyborg is already at maximum speed");
         }
      } else {
         System.out.println("Player Cyborg not found");
      }
      setChanged();
      notifyObservers();
   }
	
	/**
	 * apply the brakes on the playerCyborg
	 */
   public void brake() {
      PlayerCyborg playerCyborg = getPlayerCyborg();
   	
      if(playerCyborg != null) {
         if(playerCyborg.brake(ACCEL_AMOUNT)) {
            System.out.println("Applying the brakes");
         } else {
            System.out.println("PlayerCyborg's speed is already 0");
         }
      } else {
         System.out.println("Player Cyborg not found");
      }
      setChanged();
      notifyObservers();
   }
	
	/**
	 * turn the playerCyborg's steering wheel to the left
	 */
   public void turnLeft() {
      PlayerCyborg playerCyborg = getPlayerCyborg();
   	
      if(playerCyborg != null) {
         playerCyborg.steer(Direction.LEFT);
         System.out.println("PlayerCyborg steering is now at " + playerCyborg.getSteeringDirection() + " degrees");
      } else {
         System.out.println("Player Cyborg not found");
      }
      setChanged();
      notifyObservers();
   }
	
	/**
	 * turn the playerCyborg's steering wheel to the right
	 */
   public void turnRight() {
      PlayerCyborg playerCyborg = getPlayerCyborg();
   	
      if(playerCyborg != null) {
         playerCyborg.steer(Direction.RIGHT);
         System.out.println("PlayerCyborg steering is now at " + playerCyborg.getSteeringDirection() + " degrees");
      } else {
         System.out.println("Player Cyborg not found");
      }
      setChanged();
      notifyObservers();
   }
	
	/**
	 * process a collision between two cyborgs
	 */
   public void cyborgCollide(Cyborg cyborg1, Cyborg cyborg2) {
      cyborg1.damage(CYBORG_COLLIDE_DAMAGE);
      cyborg2.damage(CYBORG_COLLIDE_DAMAGE);
      if(soundOn) 
         CrashSound.play();
   }

	/**
	 * get the current value of the game's internal tick clock
	 * @return integer value of the gameClock
	 */
   public int getClock() {
      return gameClock;
   }
	
	/**
	 * process a PlayerCyborg reaching an energy station
	 * will randomly select an energy station 
	 * to apply to the PlayerCyborg, increasing the PlayerCyborg's energy level by the station's capacity
	 * and then setting the capacity to 0
	 * will also create a new energy station to replaced the drained one.
	 */
   public void reachEnergyStation(EnergyStation stationToDrain, Cyborg cyborgToCharge) {
      int energyToAdd = stationToDrain.drainCapacity();
      cyborgToCharge.addEnergy(energyToAdd);
      if(soundOn)
         PowerUpSound.play();
      gameObjects.add(new EnergyStation(rand.nextInt(worldWidth), rand.nextInt(worldHeight), this));
   }
	
	/**
	 * process one tick of the game world, adjusting heading of all PlayerCyborgs and drones, and updating their location 
	 * based on the new heading
	 * if the playerCyborg is not capable of moving a life will be subtracted and the gameWorld
	 * re-initialized
	 * if the player lives is equal to 0 then the game will exit with a failure message
	 * if the last base reached by playerCyborg is equal to the last base in the sequence the game will exit with a success message
	 */
   public void tick(int timeMS) {
      PlayerCyborg playerCyborg = getPlayerCyborg();
   	
      if(playerCyborg != null) {
      /**
       * check if the player PlayerCyborg has been disabled
       */
         if(!playerCyborg.isDisabled()) {
         	/**
         	 * if not disabled ensure that the PlayerCyborg has not reached the final base
         	 */
            if(playerCyborg.getLastBase() == getFinalBase()) {
            	/**
            	 * it's reached the last base thus winning the game and then exiting the program
            	 */
               System.out.println("Game over, you win! Total time: " + gameClock);
               exit(ExitCondition.WIN);
            } else {
            	/**
            	 * last base not reached, so now we update PlayerCyborgs and drones
            	 * only drain energy once a second
            	 */
               tickCounter += timeMS;
               if(tickCounter >= 1000) {
                  playerCyborg.consumeEnergy();
                  tickCounter = 0;
               }
               IIterator movableIterator = gameObjects.getIterator();
               while(movableIterator.hasNext()) {
                  if(movableIterator.getNext() instanceof Movable) {
                     Movable curMovable = ((Movable)movableIterator.getCurrent());
                  	/**
                  	 * if it is a non-player cyborg we need to process its AI
                  	 */
                     if(curMovable instanceof NonPlayerCyborg) {
                        ((NonPlayerCyborg)curMovable).invokeStrategy();
                        if(((NonPlayerCyborg)curMovable).getLastBase() >= getFinalBase()) {
                           System.out.println("Game over, a non-player cyborg wins!");
                           exit(ExitCondition.LOSE);
                        }
                     }
                  	/**
                  	 * update the object
                  	 */
                     curMovable.adjustHeading(timeMS);
                     curMovable.move(timeMS);
                  	/**
                  	 * make sure it hasn't gone out of bounds
                  	 */
                     if(curMovable.getLocationX() < 0) {
                        curMovable.setLocation(0,  curMovable.getLocationY());
                     } else if(curMovable.getLocationX() > worldWidth) {
                        curMovable.setLocation(worldWidth, curMovable.getLocationY());
                     }
                     if(curMovable.getLocationY() < 0) {
                        curMovable.setLocation(curMovable.getLocationX(), 0);
                     } else if(curMovable.getLocationY() > worldHeight) {
                        curMovable.setLocation(curMovable.getLocationX(),  worldHeight);
                     }
                  	/**
                  	 * if it is a drone make sure it bounces
                  	 */
                     if(curMovable instanceof Drone) {
                        ((Drone)curMovable).checkEdgeHit(worldWidth, worldHeight);
                     }
                     IIterator collisionIterator = gameObjects.getIterator();
                     while(collisionIterator.hasNext()) {
                        if(curMovable!=collisionIterator.getNext()) {
                           if(curMovable.collidesWith((GameObject)collisionIterator.getCurrent())) {
                              curMovable.handleCollision((GameObject)collisionIterator.getCurrent());
                           }
                        }
                     }
                  }
               }
            }
         } else {
            playerLives--;
            if(soundOn)
               ExplodeSound.play();
         	/**
         	 * check to see if the player is out of lives
         	 */
            if(playerLives == 0) {
            	/**
            	 * out of lives
            	 */
               System.out.println("Game over, you failed!");
               exit(ExitCondition.DIE);
            } else {
            	/**
            	 * still has lives, re-initialize the game world
            	 */
               init();
            }
         }
      } else {
         System.out.println("Player cyborg not found, can't play!");
         exit(ExitCondition.ERROR);
      }
   	
      gameClock++;
      System.out.println("Game world updated");
      setChanged();
      notifyObservers();
   }

	/** 
	 * exits the game, displaying a message stating the nature of the exit
	 */
   public void exit(ExitCondition condition) {
      Command cOk = new Command("Ok");
      Command cCancel = new Command("Cancel");
      Command cResponse = new Command("default");
      switch(condition) {
         case QUIT:
            cResponse = Dialog.show("Confirm quit",  "are you sure you want to quit?", cOk, cCancel);
            break;
         case WIN:
            cResponse = Dialog.show("Congratulations!",  "You Win!", cOk);
            break;
         case LOSE:
            cResponse = Dialog.show("Sorry",  "another cyborg has reached the last base!", cOk);
            break;
         case DIE:
            cResponse = Dialog.show("Out of lives",  "you have run out of lives.", cOk);
            break;
         case ERROR:
            cResponse = Dialog.show("Error",  "exiting due to error",cOk);
            break;
      }
      if(cResponse.equals(cOk)) {
         Display.getInstance().exitApplication();
      }
   }

	/**
	 * returns the number of lives the player has left
	 * @return the number of lives
	 */
   public int getPlayerLives() { 
      return playerLives; }
	
	/**
	 * returns the current value of the game clock
	 * @return Game clock value
	 */
   public int getGameClock() { 
      return gameClock; }
	
	/**
	 * returns the last base the player cyborg has reached
	 * @return The sequence number of the last base the player reached
	 */
   public int getLastBase() { 
      PlayerCyborg playerCyborg = getPlayerCyborg();
      if(playerCyborg != null) {
         return playerCyborg.getLastBase();
      }
      return 0;
   }
	
	/**
	 * returns the current energy level of the player cyborg
	 * @return player cyborg's current energy level
	 */
   public int getEnergyLevel() { 
      PlayerCyborg playerCyborg = getPlayerCyborg();
      if(playerCyborg != null) {
         return playerCyborg.getEnergyLevel();
      }
      return 0;
   }
	
	/**
	 * returns the current damage level of the player cyborg
	 * @return Player cyborg's current damage level
	 */
   public int getDamageLevel() {
      PlayerCyborg playerCyborg = getPlayerCyborg();
   	
      if(playerCyborg != null) {
         return playerCyborg.getDamageLevel();
      }
      return 0;
   }

	/**
	 * turns sound on and off
	 */
   public void toggleSound() {
      soundOn = !soundOn;
      if(soundOn && !myGame.isPaused()) {
         BackgroundSoundSound.play();
      } else {
         BackgroundSoundSound.pause();
      }
      setChanged();
      notifyObservers();
   }
	
	/**
	 * returns the current state of the sound flag
	 * @return Sound flag's current state
	 */
   public boolean getSoundState() { 
      return soundOn; }
	
	/**
	 * change the strategies of the non player cyborgs 
	 * will also increment the last base reached
	 * for the non-player cyborgs
	 */
   public void changeStrategies() {
      IIterator npcIterator = gameObjects.getIterator();
      while(npcIterator.hasNext()) {
         if(npcIterator.getNext() instanceof NonPlayerCyborg) {
            NonPlayerCyborg currentNpc = (NonPlayerCyborg)npcIterator.getCurrent();
            switch(rand.nextInt(3)) {
               case 0:
                  currentNpc.changeStrategy(new ReachStrategy(this, currentNpc));
                  break;
               case 1:
                  currentNpc.changeStrategy(new AttackStrategy(getPlayerCyborg(), currentNpc));
                  break;
               case 2:
                  currentNpc.changeStrategy(new RandomStrategy(currentNpc));
            }
         }
      }
      setChanged();
      notifyObservers();
   }
	
	/**
	 * utility function to find the player cyborg in the game objects collection
	 * @return the player cyborg object
	 */
   private PlayerCyborg getPlayerCyborg() {
      IIterator objectIterator = gameObjects.getIterator();
      while(objectIterator.hasNext()) {
         Object curObject = objectIterator.getNext();
         if(curObject instanceof PlayerCyborg) {
            return (PlayerCyborg)curObject;
         }
      }
      return null;
   }
	
	/**
	 * finds the highest base sequence number in the collection
	 * @return final base's sequence number
	 */
   private int getFinalBase() {
      int highestBase = 0;
   
      IIterator objectIterator = gameObjects.getIterator();
      while(objectIterator.hasNext()) {
         if(objectIterator.getNext() instanceof Base) {
            if(((Base)objectIterator.getCurrent()).getSequenceNumber() > highestBase) {
               highestBase = ((Base)objectIterator.getCurrent()).getSequenceNumber();
            }
         }
      }
      return highestBase;
   }
	
	/**
	 * gets the base with the specified sequence number
	 * @param baseToGet Sequence number of the base to return
	 * @return Base 0bject containing the specified sequence number
	 * will return null if that sequence number is not found
	 */
   public Base getBase(int baseToGet) {
      Base baseToReturn = null;
      IIterator baseIterator = gameObjects.getIterator();
      while(baseIterator.hasNext() && baseToReturn == null) {
         if( baseIterator.getNext() instanceof Base) {
            if(((Base)baseIterator.getCurrent()).getSequenceNumber() == baseToGet) {
               baseToReturn = ((Base)baseIterator.getCurrent());
            }
         }
      }
      return baseToReturn;
   }
	
	/**
	 * gets the current width of the game world
	 * @return Game world's current width
	 */
   public double getWorldWidth() { 
      return worldWidth; }
	/**
	 * gets the current height of the game world
	 * @return Game world's current height
	 */
   public double getWorldHeight() { 
      return worldHeight; }
	
	/**
	 * gets an iterator to move through the game object collection
	 * @return Game object iterator
	 */
   public IIterator getWorldIterator() { 
      return gameObjects.getIterator(); }
	
	/**
	 * if isRepositioning is set, then move the selected object
	 * if not isRepositioning then run through all objects, determining if any has been clicked, and selecting them if so
	 * @param clickPoint - point of the click
	 * @param originPoint - origin of the container
	 */
   public void processPointerClick(Point clickPoint, Point originPoint) {
      IIterator clickIterator = gameObjects.getIterator();
   	
      while(clickIterator.hasNext()) {
         if(clickIterator.getNext() instanceof Fixed) {
         	/**
         	 * Only check other items if the current object is a Fixed
         	 */
            Fixed curFixed = (Fixed)clickIterator.getCurrent();
            if(isRepositioning && curFixed.isSelected()) {
            	/**
            	 * if we are repositioning, and the current item is selected, then set location of selected object
            	 * to the location of the click
            	 */
               curFixed.setLocation(clickPoint.getX() - originPoint.getX(), clickPoint.getY() - originPoint.getY());
            	/**
            	 * Deactivate selection and position flag
            	 */
               isRepositioning = false;
               curFixed.setSelected(false);
            } else {	
            	/**
            	 * We are not repositioning so check if object contains point and set selected flag accordingly
            	 */
               if(curFixed.contains(clickPoint, originPoint)) {
                  curFixed.setSelected(true);
               } else {
                  curFixed.setSelected(false);
               }
            }
         	
         }
      }
      this.setChanged();
      this.notifyObservers();
   }
	
	/**
	 * switch the state of the repositioning flag
	 */
   public void togglePositioning() {
      isRepositioning = !isRepositioning;
   }
	
	/**
	 * turn off repositioning
	 */
   public void disablePositioning() {
      isRepositioning = false;
   }
	
	/**
	 * play the background sound. Will only play if sound is on
	 */
   public void playAmbient() {
      if(soundOn)
         BackgroundSoundSound.play();
   }
	
	/**
	 * stop playing the background sound
	 */
   public void stopAmbient() {
      BackgroundSoundSound.pause();
   }
	
	/**
	 * set the selected flag for all game objects to false
	 */
   public void unselectAll() {
      IIterator selectIterator = gameObjects.getIterator();
      while(selectIterator.hasNext()) {
         if(selectIterator.getNext() instanceof Fixed) {
            ((Fixed)selectIterator.getCurrent()).setSelected(false);
         }
      }
   }
}
