/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import com.codename1.ui.Display;
import java.util.Random;

import com.codename1.ui.Dialog;

import java.util.Observable;

/**
 * GameWorld class is the main data storage class of the Cyborg tracks game, holding all 
 * GameObjects and providing facilities for program control to modify and update the GameWorld state.
 */
public class GameWorld extends Observable {
	private static Random rand = new Random();
	private static final double DEFAULT_WIDTH = 1000.0;
	private static final double DEFAULT_HEIGHT = 1000.0;
	private final int ACCEL_AMOUNT = 5;
	private final int Cyborg_Collide_Damage = 40;
	private final int Drone_Collide_Damage = Cyborg_Collide_Damage / 2;
	private int playerLives;
	private int gameClock;
	private boolean soundOn;
	private GameObjectCollection gameObjects;
	private double worldWidth;
	private double worldHeight;
	
	/**
	 * Create a new GameWorld object. does not initialize the game world, only sets global variables.
	 * @return a new GameWorld object with playerLives set to 3 and game Clock set to 0
	 */
	public GameWorld() {
		gameClock = 0;
		playerLives = 3;
		soundOn = true;
		worldWidth = GameWorld.DEFAULT_WIDTH;
		worldHeight = GameWorld.DEFAULT_HEIGHT;
	}
	
	/**
	 * Modify the game world boundaries
	 * @param newWidth - The new width of the game world
	 * @param newHeight - The new height of the game world
	 */
	public void setBoundaries(double newWidth, double newHeight) {
		worldWidth = newWidth;
		worldHeight = newHeight;
	}
	
	/**
	 * Initializes the game world, creating bases, drones, energy stations, a player cyborg and three non player cyborgs
	 */
	public void init() {
		gameObjects = new GameObjectCollection();
		
		/**
		 * Create the bases of the track
		 */
		gameObjects.add(new Base(1, ((30 / GameWorld.DEFAULT_WIDTH) * worldWidth), ((15 / GameWorld.DEFAULT_HEIGHT) * worldHeight)));
		gameObjects.add(new Base(2, ((40 / GameWorld.DEFAULT_WIDTH) * worldWidth), ((200 / GameWorld.DEFAULT_HEIGHT) * worldHeight)));
		gameObjects.add(new Base(3, ((300 / GameWorld.DEFAULT_WIDTH) * worldWidth), ((300 / GameWorld.DEFAULT_HEIGHT) * worldHeight)));
		gameObjects.add(new Base(4, ((800 / GameWorld.DEFAULT_WIDTH) * worldWidth), ((600 / GameWorld.DEFAULT_HEIGHT) * worldHeight)));
		gameObjects.add(new Base(5, ((900 / GameWorld.DEFAULT_WIDTH) * worldWidth), ((400 / GameWorld.DEFAULT_HEIGHT) * worldHeight)));
		gameObjects.add(new Base(6, ((920 / GameWorld.DEFAULT_WIDTH) * worldWidth), ((150 / GameWorld.DEFAULT_HEIGHT) * worldHeight)));
		
		/**
		 * Create the player PlayerCyborg at the location of the first base
		 */
		PlayerCyborg playerCyborg = PlayerCyborg.getPlayerCyborg();
		playerCyborg.setLocation(((30 / GameWorld.DEFAULT_WIDTH) * worldWidth),((15 / GameWorld.DEFAULT_HEIGHT) * worldHeight));
		gameObjects.add(playerCyborg);
		PlayerCyborg.reset();
		
		/**
		 * Create the non-player cyborgs, assign them strategies, and add them to the collection
		 */
		NonPlayerCyborg newCyborg1 = new NonPlayerCyborg(((0 / GameWorld.DEFAULT_WIDTH) * worldWidth), ((15 / GameWorld.DEFAULT_HEIGHT) * worldHeight));
		newCyborg1.changeStrategy(new AttackStrategy(playerCyborg, newCyborg1));
		NonPlayerCyborg newCyborg2 = new NonPlayerCyborg(((0 / GameWorld.DEFAULT_WIDTH) * worldWidth), ((15 / GameWorld.DEFAULT_HEIGHT) * worldHeight));
		newCyborg2.changeStrategy(new ReachStrategy(this, newCyborg2));
		NonPlayerCyborg newCyborg3 = new NonPlayerCyborg(((0 / GameWorld.DEFAULT_WIDTH) * worldWidth), ((15 / GameWorld.DEFAULT_HEIGHT) * worldHeight));
		newCyborg3.changeStrategy(new ReachStrategy(this, newCyborg3));
		gameObjects.add(newCyborg1);
		gameObjects.add(newCyborg2);
		gameObjects.add(newCyborg3);
		
		/**
		 * Create between 2 and 8 energy stations
		 */
		for(int counter = 0; counter < rand.nextInt(7) + 2; counter++) {
			gameObjects.add(new EnergyStation(rand.nextDouble() * worldWidth, rand.nextDouble() * worldHeight));
		}
		
		/**
		 * Create between 2 and 4 drones
		 */
		for(int counter = 0; counter< rand.nextInt(3) + 2; counter++) {
			gameObjects.add(new Drone(rand.nextDouble() * worldWidth, rand.nextDouble() * worldHeight));
		}
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Accelerate the playerCyborg
	 */
	public void accelerate() {
		PlayerCyborg playerCyborg = getPlayerCyborg();
		
		if(playerCyborg != null) {
			if(playerCyborg.accelerate(ACCEL_AMOUNT)) {
				System.out.println("PlayerCyborg has accelerated");
			} else {
				System.out.println("PlayerCyborg is already ax maximum speed!");
			}
		} else {
			System.out.println("Player Bot not found");
		}
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Apply the brakes on the playerCyborg
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
	 * Turn the playerCyborg's steering wheel to the left
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
	 * Turn the playerCyborg's steering wheel to the right
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
	 * Process a collision between two cyborgs
	 */
	public void cyborgCollide() {
		PlayerCyborg playerCyborg = getPlayerCyborg();
		
		/**
		 * Ensure we have found a player cyborg
		 */
		if(playerCyborg != null) {
			IIterator objectIterator = gameObjects.getIterator();
			NonPlayerCyborg cyborgToHit = null;
			/**
			 * Find an NPC to hit
			 */
			while(objectIterator.hasNext() && cyborgToHit == null) {
				if(objectIterator.getNext() instanceof NonPlayerCyborg) {
					/**
					 * Make sure this one is not already destroyed
					 */
					if(((NonPlayerCyborg)objectIterator.getCurrent()).getDamageLevel() < NonPlayerCyborg.MAX_DAMAGE) {
						cyborgToHit = (NonPlayerCyborg)objectIterator.getCurrent();
					}
				}
			}
			if(cyborgToHit != null) {
				/**
				 * damage both cyborgs
				 */
				playerCyborg.damage(Cyborg_Collide_Damage);
				cyborgToHit.damage(Cyborg_Collide_Damage);
				System.out.println("PlayerCyborg damaged! Current damage level is " + playerCyborg.getDamageLevel());
			} else {
				/**
				 * Make sure this one is not already destroyed
				 */
				System.out.println("No Non-Player Cyborgs to hit.");
			}
		} else {
			/**
			 * Couldn't find a player cyborg
			 */
			System.out.println("Player Cyborg not found");
		}
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Process a collision between a PlayerCyborg and a drone
	 */
	public void droneCollide() {
		PlayerCyborg playerCyborg = getPlayerCyborg();
		
		if(playerCyborg != null) {
			playerCyborg.damage(Drone_Collide_Damage);;
			System.out.println("PlayerCyborg damaged! Current damage level is " + playerCyborg.getDamageLevel());
		} else {
			System.out.println("Player Bot not found");
		}
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Process a PlayerCyborg reaching a base. Will only increment the last base the PlayerCyborg reached
	 * if the new base is exactly ones higher than its current last base reached
	 * @param baseNumber - Sequence number of the base the PlayerCyborg has touched.
	 */
	public void reachBase(int baseNumber) {
		PlayerCyborg playerCyborg = getPlayerCyborg();
		
		if(playerCyborg != null) {
			/**
			 * First need to ensure that the provided sequence number is a base that actually exists
			 */
			if(baseNumber <= getFinalBase()) {
				/**
				 * If it exists we need to ensure that it is the correct base number, that is, exactly 1
				 * higher than the current last base reached
				 */
				if(playerCyborg.getLastBase() + 1 == baseNumber) {
					/**
					 * If so, increment last base reached
					 */
					playerCyborg.incrementLastBase();
					System.out.println("PlayerCyborg has reach the next base!");
				} else {
					System.out.println("That is not the next base");
				}
			} else {
				System.out.println("Not a valid base number");
			}
		} else {
			System.out.println("Player Bot not found");
		}
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Get the current value of the game's internal tick clock
	 * @return - Integer value of the gameClock
	 */
	public int getClock() {
		return gameClock;
	}
	
	/**
	 * Process a PlayerCyborg reaching an energy station. Will randomly select an energy station 
	 * to apply to the PlayerCyborg, increasing the PlayerCyborg's energy level by the station's capacity
	 * and then setting the capacity to 0. Will also create a new energy station to replaced the drained one.
	 */
	public void reachEnergyStation() {
		EnergyStation stationToDrain = null;
		IIterator stationIterator = gameObjects.getIterator();
		/**
		 * First find an energy station
		 */
		while(stationIterator.hasNext() && stationToDrain == null) {
			if(stationIterator.getNext() instanceof EnergyStation) {
				/**
				 * Station needs to still have energy
				 */
				if(((EnergyStation)stationIterator.getCurrent()).getCapacity() > 0) {
					stationToDrain = (EnergyStation)stationIterator.getCurrent();
				}
			}
		}
		if(stationToDrain != null) {
			/**
			 * Transfer energy from station to PlayerCyborg
			 */
			PlayerCyborg playerCyborg = getPlayerCyborg();
			if(playerCyborg != null) {
				playerCyborg.addEnergy(stationToDrain.drainCapacity());
				/**
				 * Create a new station
				 */
				gameObjects.add(new EnergyStation(rand.nextDouble() * worldWidth, rand.nextDouble() * worldHeight));
				
				System.out.println("Current PlayerCyborg energy level: " + playerCyborg.getEnergyLevel());
			} else {
				System.out.println("Player Bot not Found");
			}
		} else {
			System.out.println("No valid energy stations found");
		}
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Process one tick of the game world, adjusting heading of all PlayerRobots and drones, and updating their location 
	 * based on the new heading. If the playerCyborg is not capable of moving a life will be subtracted and the gameWorld
	 * re-initialized. If the player lives is equal to 0 then the game will exit with a failure message. 
	 * If the last base reached by playerCyborg is equal to the last base in the sequence the game will exit with a success message
	 */
	public void tick() {
		PlayerCyborg playerCyborg = getPlayerCyborg();
		
		if(playerCyborg != null) {
			/**
			 * check if the player PlayerCyborg has been disabled
			 */
			if(!playerCyborg.isDisabled()) {
				/**
				 * If not disabled ensure that the PlayerCyborg has not reached the final base
				 */
				if(playerCyborg.getLastBase() == getFinalBase()) {
					/**
					 * It's reached the last base! "You Win" then exit program
					 */
					System.out.println("Game over, you win! Total time: " + gameClock);
					exit();
				} else {
					/**
					 * Last base not reached, so now we update PlayerRobots and drones
					 */
					playerCyborg.consumeEnergy();
					IIterator movableIterator = gameObjects.getIterator();
					while(movableIterator.hasNext()) {
						if(movableIterator.getNext() instanceof Movable) {
							Movable curMovable = ((Movable)movableIterator.getCurrent());
							/**
							 * If it is a non-player cyborg we need to process its AI
							 */
							if(curMovable instanceof NonPlayerCyborg) {
								((NonPlayerCyborg)curMovable).invokeStrategy();
								if(((NonPlayerCyborg)curMovable).getLastBase() >= getFinalBase()) {
									System.out.println("Game over, a non-player cyborg wins!");
									exit();
								}
							}
							/**
							 * update the object
							 */
							curMovable.adjustHeading();
							curMovable.move();
							/**
							 * Make sure it hasn't gone out of bounds
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
						}
					}
				}
			} else {
				playerLives--;
				/**
				 * Check to see if the player is out of lives
				 */
				if(playerLives == 0) {
					/**
					 * Out of lives, "You Lose" and exit
					 */
					System.out.println("Game over, you failed!");
					exit();
				} else {
					/**
					 * Still has lives, re-initialize the game world
					 */
					init();
				}
			}
		} else {
			System.out.println("Player bot not found. Can't play!");
			exit();
		}
		
		gameClock++;
		System.out.println("Game world updated");
		setChanged();
		notifyObservers();
	}

	/** 
	 * exits the game
	 */
	public void exit() {
		Boolean bOk = Dialog.show("Confirm quit",  "Are you sure you want to quit?", "Ok", "Cancel");
		if(bOk) {
			Display.getInstance().exitApplication();
		}
	}

	/**
	 * Returns the number of lives the player has left
	 * @return - The number of lives
	 */
	public int getPlayerLives() { return playerLives; }
	
	/**
	 * Returns the current value of the game clock
	 * @return Game clock value
	 */
	public int getGameClock() { return gameClock; }
	
	/**
	 * Returns the last base the player cyborg has reached
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
	 * Returns the current energy level of the player cyborg
	 * @return Player cyborg's current energy level
	 */
	public int getEnergyLevel() { 
		PlayerCyborg playerCyborg = getPlayerCyborg();
		if(playerCyborg != null) {
			return playerCyborg.getEnergyLevel();
		}
		return 0;
	}
	
	/**
	 * Returns the current damage level of the player cyborg
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
	 * Turns sound on and off
	 */
	public void toggleSound() {
		soundOn = !soundOn;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Returns the current state of the sound flag
	 * @return Sound flag's current state
	 */
	public boolean getSoundState() { 
		return soundOn; 
		}
	
	/**
	 * Change the strategies of the non player cyborgs. will also increment the last base reached
	 * for the non-player cyborgs.
	 */
	public void changeStrategies() {
		IIterator npcIterator = gameObjects.getIterator();
		while(npcIterator.hasNext()) {
			if(npcIterator.getNext() instanceof NonPlayerCyborg) {
				switch(((NonPlayerCyborg)npcIterator.getCurrent()).getCurrentStrategy()) {
				case "Attack":
					((NonPlayerCyborg)npcIterator.getCurrent()).changeStrategy(new ReachStrategy(this, ((NonPlayerCyborg)npcIterator.getCurrent())));
					break;
				case "Race":
					((NonPlayerCyborg)npcIterator.getCurrent()).changeStrategy(new AttackStrategy(getPlayerCyborg(), ((NonPlayerCyborg)npcIterator.getCurrent())));
					break;
				}
				((NonPlayerCyborg)npcIterator.getCurrent()).incrementLastBase();
			}
		}
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Utility function to find the player cyborg in the game objects collection
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
	 * Finds the highest base sequence number in the collection
	 * @return Final base's sequence number
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
	 * Gets the base with the specified sequence number
	 * @param baseToGet Sequence number of the base to return
	 * @return Base 0bject containing the specified sequence number. Will return null if that sequence number is not found.
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
	 * Gets the current width of the game  world
	 * @return Game world's current width
	 */
	public double getWorldWidth() { 
		return worldWidth; 
		}
	/**
	 * Gets the current height of the game world
	 * @return Game world's current height
	 */
	public double getWorldHeight() { 
		return worldHeight; 
		}
	
	/**
	 * Gets an iterator to move through the game object collection
	 * @return Game object iterator
	 */
	public IIterator getWorldIterator() { 
		return gameObjects.getIterator(); 
		}
}
