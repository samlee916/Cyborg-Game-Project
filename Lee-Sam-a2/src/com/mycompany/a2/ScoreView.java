/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import java.util.Observable;
import com.codename1.ui.layouts.FlowLayout;
import java.util.Observer;
import com.codename1.ui.Container;
import com.codename1.ui.Label;

/**
 * ScoreView will create a series of labels showing the current player status
 */
public class ScoreView extends Container implements Observer {
	private Label livesLeftVal;
	private Label clockValueVal;
	private Label baseReachedVal;
	private Label energyLevelVal;
	private Label damageLevelVal;
	private Label soundStateVal;
	
	/**
	 * Creates a new instance of ScoreView
	 */
	public ScoreView() {
		this.setLayout(new FlowLayout(CENTER));
		Label livesLeftText = new Label("Lives left:");
		livesLeftVal = new Label("0");
		livesLeftVal.getAllStyles().setPadding(LEFT, 2);
		livesLeftVal.getAllStyles().setPadding(RIGHT, 2);
		Label clockValueText = new Label("Time:");
		clockValueVal = new Label("0");
		clockValueVal.getAllStyles().setPadding(LEFT, 2);
		clockValueVal.getAllStyles().setPadding(RIGHT, 2);
		Label baseReachedText = new Label("Player Last Base Reached:");
		baseReachedVal = new Label("0");
		baseReachedVal.getAllStyles().setPadding(LEFT, 2);
		baseReachedVal.getAllStyles().setPadding(RIGHT, 2);
		Label energyLevelText = new Label("Player Energy Level:");
		energyLevelVal = new Label("0");
		energyLevelVal.getAllStyles().setPadding(LEFT, 2);
		energyLevelVal.getAllStyles().setPadding(RIGHT, 2);
		Label damageLevelText = new Label("Player Damage Level:");
		damageLevelVal = new Label("0");
		damageLevelVal.getAllStyles().setPadding(LEFT, 2);
		damageLevelVal.getAllStyles().setPadding(RIGHT, 2);
		Label soundStateText = new Label("Sound:");
		soundStateVal = new Label("0");
		soundStateVal.getAllStyles().setPadding(LEFT, 2);
		soundStateVal.getAllStyles().setPadding(RIGHT, 2);
		this.addComponent(clockValueText);
		this.addComponent(clockValueVal);	
		this.addComponent(livesLeftText);
		this.addComponent(livesLeftVal);	
		this.addComponent(baseReachedText);
		this.addComponent(baseReachedVal);
		this.addComponent(energyLevelText);
		this.addComponent(energyLevelVal);
		this.addComponent(damageLevelText);
		this.addComponent(damageLevelVal);
		this.addComponent(soundStateText);
		this.addComponent(soundStateVal);
	}
	
	/**
	 * Updates the values of the labels in the component using the updated GameWorld state
	 */
	public void update(Observable o, Object arg) {
		livesLeftVal.setText(((GameWorld)o).getPlayerLives() + "");
		clockValueVal.setText(((GameWorld)o).getGameClock() + "");
		baseReachedVal.setText(((GameWorld)o).getLastBase() + "");
		energyLevelVal.setText(((GameWorld)o).getEnergyLevel() + "");
		damageLevelVal.setText(((GameWorld)o).getDamageLevel() + "");
		soundStateVal.setText((((GameWorld)o).getSoundState()?"On":"Off"));
		repaint();
	}
}
