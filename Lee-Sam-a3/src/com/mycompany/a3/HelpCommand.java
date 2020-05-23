/**
 * HelpCommand.java
 * defines the HelpCommand class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

/**
 * HelpCommand extends Command
 * used to display a help dialog
 */
public class HelpCommand extends Command {
	/**
	 * creates a new instance of the HelpCommand class
	 */
   public HelpCommand() {
      super("Help");
   }
	
	/**
	 * creates a dialog box showing keyboard commands for the program
	 */
   @Override
   public void actionPerformed(ActionEvent ev) {
      Dialog.show("Help", "Key Bindings:\na - accelerate\nb - brake\nl - turn left\nr - turn right\ne - Energy Station\ng - hit drone\nt - tick", new Command("Close"));
   }

}
