Tested on Hydra
Notes:
Explanation of UML in case needed:
	Most Command classes, ReachStrategy, and Game are associated with GameWorld 
	Game is associated with MapView and ScoreView
	Attack/ReachStrategy is associated with NonPlayerCyborg
	Fixed, Drone, Cyborg, and Movable inherits from GameObject and GameObjectCollection is associated with GameObject
	PlayerCyborg/NonPlayerCyborg inherits from Cyborg
	AttackStrategy is associated with PlayerCyborg
	Cyborg implements ISteerable
	Movable inherits Cyborg
	GameObjectCollection implements ICollection
	GameObjectIterator is part of GameObjectCollection and implements IIterator
	NonPlayerCyborg is associated with IStrategy and IStrategy implements Non/Attack/ReachStrategy
	Base and EnergyStation inherits from Fixed which inherits from GameObject
