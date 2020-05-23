/**
 * AboutCommand.java
 * defines the AboutCommand class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

/**
 * AboutCommand
 * defines a command for displaying an About dialog box
 */
public class AboutCommand extends Command{
	/**
	 * create the new command called "About"
	 */
   public AboutCommand() {
      super("About");
   }
	
	/**
	 * creates the dialog box, providing version information for the program
	 * @param ev - ActionEvent object containing details of the action that initiated the command
	 */
   @Override
   public void actionPerformed(ActionEvent ev) {
      Dialog.show("Sili-Challenge Game",  "", new Command("Close"));
   }

}
