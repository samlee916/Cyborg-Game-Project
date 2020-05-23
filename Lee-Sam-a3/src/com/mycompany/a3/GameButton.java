/**
 * GameButton.java
 * defines the GameButton class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Command; 
import com.codename1.charts.util.ColorUtil;

/**
 * GameButton class extends the Button class, defining styles to be used in this program
 */
public class GameButton extends Button {
	/**
	 * create a new instance of GameButton attached to a Command object
	 * @param buttonCommand - the command object to attach to the button
	 */
   public GameButton(Command buttonCommand) {
      super(buttonCommand);
      setStyle();
   }
	
	/**
	 * create a new instance of GameButton with no attached command, but with a 
	 * text label
	 * @param buttonName - the text to display in the button
	 */
   public GameButton(String buttonName) {
      super(buttonName);
      setStyle();
   }
	
	/**
	 * set the button's styling
	 */
   private void setStyle() {
      super.getUnselectedStyle().setBgTransparency(255);
      super.getUnselectedStyle().setBgColor(ColorUtil.rgb(50,50,175));
      super.getUnselectedStyle().setFgColor(ColorUtil.rgb(200,200,200));
      super.getAllStyles().setPadding(Component.TOP, 5);
      super.getAllStyles().setPadding(Component.BOTTOM, 5);
      super.getDisabledStyle().setBgColor(ColorUtil.GRAY);
   }
}
