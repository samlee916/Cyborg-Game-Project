package com.mycompany.a1;

import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;


public class Game extends Form {
   private GameWorld gw;
	
   public Game() {
      gw = new GameWorld();
      gw.init();
      play();
   }
   //Provided code	
   @SuppressWarnings("rawtypes")
   private void play() {
      Label myLabel=new Label ("Enter a Command:");
      this.addComponent(myLabel);
      final TextField myTextField = new TextField();
      this.addComponent(myTextField);
      this.show();
      myTextField.addActionListener(
         new ActionListener() {
            boolean allow = false; 
            public void actionPerformed(ActionEvent evt) {
            
               String sCommand = myTextField.getText().toString();
               myTextField.clear();
            /**
               * All the case statements needed from the manual
             */
               if (!sCommand.isEmpty()) {
                  switch (sCommand.charAt(0)) {
                     case 'a':
                        gw.increaseSpeed();
                        System.out.println("Increasing speed.");
                        break;
                     case 'b':
                        gw.applyBrake();
                        System.out.println("Apply break slowing.");
                        break;
                     case 'l':
                        gw.steeringDirectionLeft();
                        System.out.println("Turning left.");
                        break;
                     case 'r':
                        gw.steeringDirectionRight();
                        System.out.println("Turning right.");
                        break;
                     case 'c': 
                        gw.contactWithCyborg();
                        System.out.println("Hit cyborg.");
                        break;
                     case '1':
                        gw.ranOverBase(1);
                        System.out.println("Ran over base 1.");
                        break;
                     case '2':
                        gw.ranOverBase(2);
                        System.out.println("Ran over base 2.");
                        break;
                     case '3':
                        gw.ranOverBase(3);
                        System.out.println("Ran over base 3.");
                        break;
                     case '4':
                        gw.ranOverBase(4);
                        System.out.println("Ran over base 4.");
                        break;
                     case '5':
                        gw.ranOverBase(5);
                        System.out.println("Ran over base 5.");
                        break;
                     case '6':
                        gw.ranOverBase(6);
                        System.out.println("Ran over base 6.");
                        break;
                     case '7':
                        gw.ranOverBase(7);
                        System.out.println("Ran over base 7.");
                        break;
                     case '8':
                        gw.ranOverBase(8);
                        System.out.println("Ran over base 8.");
                        break;
                     case '9':
                        gw.ranOverBase(9);
                        System.out.println("Ran over base 9.");
                        break;
                     case 'e':
                        gw.ranOverEnergyStation();
                        System.out.println("Ran over energy station.");
                        break;
                     case 'g':
                        gw.contactWithDrone();
                        System.out.println("Hit the drone.");
                        break;
                     case 't':
                        gw.clockTick();
                        System.out.println("Clock tick.");
                        break;
                     case 'd':
                        gw.gameDisplay();
                        System.out.println("Game displayed.");
                        break;
                     case 'm':
                        gw.mapDisplay();
                        System.out.println("Map display.");
                        break;
                     case 'x':
                        this.allow = true;
                        gw.exitquestion();
                     //gw.exit();
                     //System.out.println("Exit game.");
                        break;
                     case 'y':
                        if(this.allow == true) {
                           gw.exit();
                           break;
                        } else 
                           break;
                     case 'n':
                        this.allow = false;
                        System.out.println("Play is continued.");
                        break;
                     default: 
                        System.out.println("Invalid command!");
                        break;
                  }
               }
            }	
         });
   				
   }
				
			
}