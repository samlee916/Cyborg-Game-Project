/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Command; 
import com.codename1.charts.util.ColorUtil;

/**
 * GameButton class extends the Button class, defining styles to be used in this program
 */
public class GameButton extends Button {
	/**
	 * Create a new instance of GameButton attached to a Command object
	 * @param buttonCommand The command object to attach to the button
	 */
	public GameButton(Command buttonCommand) {
		super(buttonCommand);
		setStyle();
	}
	
	/**
	 * Create a new instance of GameButton with no attached command, but with a 
	 * text label
	 * @param buttonName - The text to display in the button
	 */
	public GameButton(String buttonName) {
		super(buttonName);
		setStyle();
	}
	
	/**
	 * Set the button's styling
	 */
	private void setStyle() {
		super.getUnselectedStyle().setBgTransparency(255);
		super.getUnselectedStyle().setBgColor(ColorUtil.rgb(50,50,175));
		super.getUnselectedStyle().setFgColor(ColorUtil.rgb(200,200,200));
		super.getAllStyles().setPadding(Component.TOP, 5);
		super.getAllStyles().setPadding(Component.BOTTOM, 5);
	}
}
