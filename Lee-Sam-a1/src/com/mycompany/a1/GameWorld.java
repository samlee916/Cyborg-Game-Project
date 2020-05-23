package com.mycompany.a1;

import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import com.codename1.charts.util.ColorUtil;

public class GameWorld {
	
	/**
	  * Fixed Game World Variables
	    Game World Stat
	 */
   private int lives = 3; 
   private int timer = 0; 
   private int finalBase = 4;
	// Added random number location becasue it is random not fixed
   private Random rd = new Random();
	//final private double WIDTH;
	//final private double HEIGHT;
	
	/**
	  * Initiate vector and iterator
	 */
   private Vector<GameObject> WorldObjects;
   Iterator<GameObject> itr; 
	 
	/**
	  * cyborg begins where base one begins 
	 */
	
	//Cyborg cyborg = new Cyborg(70,70);

   public GameWorld() {
   	//this.WIDTH = 1000;
   	//this.HEIGHT = 1000;
   }

	
	/**
	  * GameWorld methods
	 */
   public void init()
   {
      WorldObjects = new Vector<GameObject>();
      itr = WorldObjects.iterator();
   	
   	/**
         * 4 bases: between x_0-1000 & y_0-1000 *first base begins at 1*
        */
   	// WorldObjects.add(new Base(70,70,1));
   	// WorldObjects.add(new Base(180,180,2));
   	// WorldObjects.add(new Base(250,250,3));
   	// WorldObjects.add(new Base(500,500,4));
   	//// This should be random number at least 4 Bases///////
      int baseRandom = 4+rd.nextInt(5);
      int energyCyborgRandom = 2 + rd.nextInt(4);
   
      for (int i = 1; i < baseRandom; i++ )
      {
         WorldObjects.add(new Base(rd.nextInt(1000),rd.nextInt(1000),i));
      }
   	
      for (int i = 0; i < energyCyborgRandom; i++ )
      {
         WorldObjects.add(new EnergyStation(rd.nextInt((1000 - 0)+1)+0, rd.nextInt((1000 - 0)+1)+0, rd.nextInt((50 - 10 )+1) + 10));
      }
      for (int i = 0; i < energyCyborgRandom; i++ )
      {
         WorldObjects.add(new Drone(rd.nextInt((1000 - 0)+1)+0, rd.nextInt((1000 - 0)+1)+0, rd.nextInt((50 - 10 )+1) + 10, rd.nextInt((359- 0 )+1) + 0,  rd.nextInt((15 - 5 )+1) + 5));
      }
   	//********************************* */
   	//2 energy stations 
   	// WorldObjects.add(new EnergyStation(r.nextInt((1000 - 0)+1)+0, r.nextInt((1000 - 0)+1)+0, r.nextInt((50 - 10 )+1) + 10));
   	// WorldObjects.add(new EnergyStation(r.nextInt((1000 - 0)+1)+0, r.nextInt((1000 - 0)+1)+0, r.nextInt((50 - 10 )+1) + 10));
   	
   	//2 drones
   	// WorldObjects.add(new Drone(r.nextInt((1000 - 0)+1)+0, r.nextInt((1000 - 0)+1)+0, r.nextInt((50 - 10 )+1) + 10, r.nextInt((359- 0 )+1) + 0,  r.nextInt((15 - 5 )+1) + 5));
   	// WorldObjects.add(new Drone(r.nextInt((1000 - 0)+1)+0, r.nextInt((1000 - 0)+1)+0, r.nextInt((50 - 10 )+1) + 10, r.nextInt((359- 0 )+1) + 0,  r.nextInt((15 - 5 )+1) + 5));
   	
   	//cyborg
   	// I am using ramdon number for it
      Cyborg cyborg = new Cyborg(rd.nextInt()* 1000, rd.nextInt()* 1000);
      WorldObjects.add(cyborg);
   }

	
	/**
	 * Increase speed of cyborg
	 */
   public void increaseSpeed()
   {
      for (int i = 0;i < WorldObjects.size();i++)
      {	
         if (WorldObjects.get(i) instanceof Cyborg)
         {
            int temp = ((Cyborg) WorldObjects.get(i)).getSpeed();
            temp+=2;
            if (temp > 40)
               temp = 40;
            ((Cyborg) WorldObjects.get(i)).setSpeed(temp);
         }
      }
   	
   	// This one only works when you put it as global variables
   	// We are supposed to initialize all the objects in the init() methods
   //		int temp = cyborg.getSpeed();
   //		temp+=2;
   //		if(temp > 40)
   //		{
   //			temp = 40;
   //		}
   //		cyborg.setSpeed(temp);
   }
	
	/**
	 * Decrease speed of cyborg
	 */

   public void applyBrake()
   {
      for (int i = 0;i < WorldObjects.size();i++)
      {	
         if (WorldObjects.get(i) instanceof Cyborg)
         {
            int temp = ((Cyborg) WorldObjects.get(i)).getSpeed();
            temp-=2;
            if(temp < 0)
            {
               temp = 0;
            }
            ((Cyborg) WorldObjects.get(i)).setCyborgSpeed(temp);	
         }
      }
   //		int temp = cyborg.getSpeed();
   //		temp-=2;
   //		if(temp < 0)
   //		{
   //			temp = 0;
   //		}
   //		cyborg.setRobotSpeed(temp);
   }
	
	/**
	 * these methods call cyborg method to steer without this cyborg steering left/ right won't happen
	 */
   public void steeringDirectionLeft()
   {
      for (int i = 0;i < WorldObjects.size();i++)
      {	
         if (WorldObjects.get(i) instanceof Cyborg)
         {
            ((Cyborg) WorldObjects.get(i)).turnLeft();
         }
      }
   	
   }
	
   public void steeringDirectionRight()
   {
      for (int i = 0;i < WorldObjects.size();i++)
      {	
         if (WorldObjects.get(i) instanceof Cyborg)
         {
            ((Cyborg) WorldObjects.get(i)).turnRight();
         }
      }
   }
	
	/**
	 * Check if base is ran over
	 * @param baseNumber
	 */
	/*public void ranOverBase(int baseNumber)
	{
		if((baseNumber) - cyborg.getLastBaseReached() == 1)
		{
			cyborg.setLastBaseReached(baseNumber);
		}
	}*/
	
   public void ranOverBase(int baseNumber)
   {
      for (int i = 0;i < WorldObjects.size();i++)
      {	
         if (WorldObjects.get(i) instanceof Cyborg)
         {
            if (((Cyborg) WorldObjects.get(i)).getLastBaseReached() == finalBase) {
               System.out.println("You reached the end!!!");
            }
            
            else {
               if (((Cyborg) WorldObjects.get(i)).getLastBaseReached() == baseNumber)
               {
                  ((Cyborg) WorldObjects.get(i)).setLastBaseReached(baseNumber);
                  System.out.println("Ran over base " + baseNumber + ".");
               }
               else {
                  System.out.println("Not in sequence!!!!");
               }
            }
         }
      
      
      }
   
   ////		if(cyborg.getLastBaseReached() == finalBase) {
   ////            System.out.println("You reached the end!!!");
   ////        } 
   //        else
   //        {    
   //            if((baseNumber) - cyborg.getLastBaseReached() == 1)
   //            {
   //                cyborg.setLastBaseReached(baseNumber);
   //                System.out.println("Ran over base " + baseNumber + ".");
   //            } 
   //            else 
   //            {
   //                System.out.println("Not in sequence!!!!");
   //            }
   //        }
   }
	
	/**
	 * checks if energy has been ran over and spawns a new one
	 */
   public void ranOverEnergyStation()
   {
      Random r = new Random();
   	//itr = WorldObjects.iterator();
   	//while(itr.hasNext())
   	//{
      for (int j = 0; j < WorldObjects.size(); j++)
      {
      		
         if(WorldObjects.get(j) instanceof EnergyStation)
         {
            if(((EnergyStation) WorldObjects.get(j)).getCapacity() != 0)
            {
               for (int i = 0; i < WorldObjects.size(); i++)
               {
                  if (( WorldObjects.get(i)) instanceof Cyborg)
                  {
                     int temp_num = ((Cyborg) WorldObjects.get(i)).getEnergyLevel() + ((EnergyStation) WorldObjects.get(j)).getCapacity();
                     ((Cyborg) WorldObjects.get(i)).setEnergyLevel(temp_num);
                     ((EnergyStation) WorldObjects.get(j)).setCapacity(0);
                     ((EnergyStation) WorldObjects.get(j)).setColor(ColorUtil.rgb(0,100,0));
                  }
               }
            	//cyborg.setEnergyLevel(cyborg.getEnergyLevel() + ((EnergyStation) itr.next()).getCapacity() );
            //					((EnergyStation) itr.next()).setCapacity(0);
            //					((EnergyStation) itr.next()).setColor(ColorUtil.rgb(0,100,0));
            	
            }
         }
      }
      WorldObjects.add(new EnergyStation(r.nextInt((1000 - 0)+1)+0, r.nextInt((1000 - 0)+1)+0, r.nextInt((50 - 10 )+1) + 10));
   }
	
	/**
	 * Check if cyborg has made contact with drone/cyborg
	 */
   public void contactWithDrone()
   {
      for (int i = 0; i < WorldObjects.size(); i++)
      {
         if (WorldObjects.get(i) instanceof Cyborg)
         {
            ((Cyborg) WorldObjects.get(i)).hitDrone();
         }
      }
   	
   }
	
   public void contactWithCyborg()
   {
      for (int i = 0; i < WorldObjects.size(); i++)
      {
         if (WorldObjects.get(i) instanceof Cyborg)
         {
         	
            ((Cyborg)WorldObjects.get(i)).hitCyborg();
         }
      }
   }
	
	/**
	 * The clock tick method
	 */
   public void clockTick()
   {
   //		itr = WorldObjects.iterator();
   
      timer++;
      for (int i = 0; i < WorldObjects.size(); i++)
      {
         if ((WorldObjects.get(i)) instanceof Cyborg)
         {
         	
            ((Cyborg) WorldObjects.get(i)).energyDrain();
         }
      }
   	//cyborg.energyDrain();
   	
      for (int i = 0; i < WorldObjects.size(); i++)
      {
         if ((WorldObjects.get(i)) instanceof Drone)
         {
            ((Drone) WorldObjects.get(i)).headingChange();
            ((Drone) WorldObjects.get(i)).move();
         }
      }
   	
   	
   //		while(itr.hasNext())
   //		{
   //			if(itr.next() instanceof Drone || itr.next() instanceof Movable )
   //			{
   //				((Drone) itr.next()).headingChange();
   //				((Movable) itr.next()).move();
   //			}
   //
   //		}
      for (int i = 0; i < WorldObjects.size(); i++)
      {
         if (WorldObjects.get(i) instanceof Cyborg)
         {
            if(((Cyborg) WorldObjects.get(i)).getTheCyborgIsDead())
            {
               lives = lives -1;
               if(lives == 0)
               {
               	//not true that the cyborg died but the lives reached 0
                  System.out.print("No lives left, Game Over");
                  this.init();
               }
               else
               {
               	//if its true that the cyborg has died
                  System.out.print("Your cyborg died.");
                  this.init();
               }
            }
         	
            if(((Cyborg) WorldObjects.get(i)).getLastBaseReached() == finalBase)
            {
               System.out.println("You win" + this.timer);
            }
         }
      }
   }
	
	/**
	 * Game stat display according to specifications
	 */
   public void gameDisplay()
   {
      int lastBaseReached = 0;
      int energyLevel = 0;
      int damageLevel = 0;
      for (int i = 0; i < WorldObjects.size(); i++)
      {
         if (( WorldObjects.get(i)) instanceof Cyborg)
         {
            lastBaseReached = ((Cyborg) WorldObjects.get(i)).getLastBaseReached();
            energyLevel = ((Cyborg) WorldObjects.get(i)).getEnergyLevel();
            damageLevel = ((Cyborg) WorldObjects.get(i)).getDamageLevel();
         }
      	
      }
   	
      System.out.println("Number of lives: " + lives);
      System.out.println("Time: " + timer);
      System.out.println("Base number reached: "+lastBaseReached);
      System.out.println("Energy Level: "+energyLevel);
   
      System.out.println("Damage: "+ damageLevel);
      System.out.println("____\t____\t_____\t_____\t_____\t____\t_____\t____\t_____\t_____\t_____\t_____\t_____");
   	
   	
   	
   }
	
	/**
	 * Map display
	 */
   public void mapDisplay() {
      itr = WorldObjects.iterator();
      System.out.println();
      while(itr.hasNext()) {
      
         System.out.println(itr.next().toString());
      }
   	
      System.out.println("____\t____\t_____\t_____\t_____\t____\t_____\t____\t_____\t_____\t_____\t_____\t_____");
   }
	
	/**
	 * 	Exit of of the game
	 */
   public void exit() {
   	
      System.out.println("Exit game.");
      System.exit(0);		
   
   }
	
	/**
	 * Exit question
	 */
   public void exitquestion() {
      System.out.println("Are you sure you want to quit, y/n?");
   }
	
}
