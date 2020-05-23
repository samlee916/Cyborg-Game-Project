/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.TextField;

/**
 * BaseCollideCommand class extends the Command class and is used to constructor a handler for 
 * requests to collide the player cyborg with a base
 */
public class BaseCollideCommand extends Command {
   private GameWorld gw;
	
	/**
	 * Create a new instance of the BaeCollideCommand class
	 * @param gwRef - GameWorld object containing the player cyborg to collide with a base
	 */
   public BaseCollideCommand(GameWorld gwRef) {
      super("Collide with Base");
      gw = gwRef;
   }
	
	/**
	 * Processes the base collision. Displays a dialog box asking for the number of the
	 * base to collide with and passes this to the GameWorld object provided in the constructor
	 */
   @Override
   public void actionPerformed(ActionEvent ev) {
      Command cOk = new Command("Ok");
      Command cCancel = new Command("Cancel");
      Command[] cmds = new Command[] {cOk, cCancel};
      TextField baseNumber = new TextField();
      Command dialog = Dialog.show("Enter base number to reach:",  baseNumber,  cmds);
   	
      if(dialog == cOk) {
         gw.reachBase(Character.digit(baseNumber.getText().charAt(0), 10));
      }
   }

}
