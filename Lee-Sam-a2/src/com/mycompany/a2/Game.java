/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a2;
import com.codename1.ui.Form;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.charts.util.ColorUtil;

/**
 * Game class serves as the main control and logic program for the Cyborg Tracks game
 * 
 */
public class Game extends Form {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private Container east, west, south;
	
	/**
	 * Build a new instance of the Game object, creates the user interface, and initializing the game world.
	 */
	public Game() {
		GameWorld gw = new GameWorld();

		mv = new MapView();
		sv = new ScoreView();
		/**
		 * Add out observers to the GameWorld so they can be updated when the state changes
		 */
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		/**
		 * Build all of the command objects that the user interface will need
		 */
		AccelCommand accelCmd = new AccelCommand(gw);
		BrakeCommand brakeCmd = new BrakeCommand(gw);
		LeftTurnCommand leftCmd = new LeftTurnCommand(gw);
		RightTurnCommand rightCmd = new RightTurnCommand(gw);
		CyborgCollideCommand nprCmd = new CyborgCollideCommand(gw);
		BaseCollideCommand baseCmd = new BaseCollideCommand(gw);
		StationCollideCommand stationCmd = new StationCollideCommand(gw);
		DroneCollideCommand droneCmd = new DroneCollideCommand(gw);
		TickCommand tickCmd = new TickCommand(gw);
		ExitCommand exitCmd = new ExitCommand(gw);
		ChangeStrategiesCommand strategyCmd = new ChangeStrategiesCommand(gw);
		ToggleSoundCommand toggleSoundCmd = new ToggleSoundCommand(gw);
		AboutCommand aboutCmd = new AboutCommand();
		HelpCommand helpCmd = new HelpCommand();

		/**
		 * Create user interaction elements, attaching commands to them
		 */
		GameButton accelButton = new GameButton(accelCmd);
		GameButton brakeButton = new GameButton(brakeCmd);
		GameButton leftTurnButton = new GameButton(leftCmd);
		GameButton rightTurnButton = new GameButton(rightCmd);
		GameButton robotCollideButton = new GameButton(nprCmd);
		GameButton baseCollideButton = new GameButton(baseCmd);
		GameButton stationCollideButton = new GameButton(stationCmd);
		GameButton droneCollideButton = new GameButton(droneCmd);
		GameButton tickButton = new GameButton(tickCmd);
		GameButton strategiesButton = new GameButton(strategyCmd);
		CheckBox soundCheckBox = new CheckBox();
		
		/**
		 * Create listeners for keyboard keys, attaching commands to them
		 */
		addKeyListener('a', accelCmd);
		addKeyListener('b', brakeCmd);
		addKeyListener('l', leftCmd);
		addKeyListener('r', rightCmd);
		addKeyListener('e', nprCmd);
		addKeyListener('g', droneCmd);
		addKeyListener('t', tickCmd);
		
		/**
		 * Build the toolbar
		 */
		Toolbar myToolbar = new Toolbar();
		Toolbar.setOnTopSideMenu(false);
		soundCheckBox.setCommand(toggleSoundCmd);
		soundCheckBox.setSelected(gw.getSoundState());
		soundCheckBox.getAllStyles().setBgTransparency(255);
		soundCheckBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		setToolbar(myToolbar);
		
		/**
		 * Build the toolbar menu
		 */
		myToolbar.addCommandToSideMenu(exitCmd);
		myToolbar.addComponentToSideMenu(soundCheckBox);
		myToolbar.addCommandToSideMenu(helpCmd);
		myToolbar.addCommandToSideMenu(aboutCmd);
		myToolbar.addCommandToRightBar(accelCmd);
		myToolbar.setTitle("Sili-Challenge Game");
		
		/**
		 * create containers to hold the buttons
		 */
		west = new Container();
		east = new Container();
		south = new Container();
		
		/**
		 * add buttons to the interface containers
		 */
		west.addComponent(accelButton);
		west.addComponent(leftTurnButton);
		west.addComponent(strategiesButton);
		west.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		west.getAllStyles().setPadding(TOP, 15);
		
		east.addComponent(brakeButton);
		east.addComponent(rightTurnButton);
		east.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		east.getAllStyles().setPadding(TOP, 15);
		
		south.setLayout(new FlowLayout(CENTER));
		south.addComponent(robotCollideButton);
		south.addComponent(baseCollideButton);
		south.addComponent(stationCollideButton);
		south.addComponent(droneCollideButton);
		south.addComponent(tickButton);
		
		/**
		 * set the form layout, adding the containers to it
		 */
		setLayout(new BorderLayout());
		add(BorderLayout.WEST, west);
		add(BorderLayout.EAST, east);
		add(BorderLayout.SOUTH, south);
		add(BorderLayout.NORTH, sv);
		add(BorderLayout.CENTER, mv);
		
		/**
		 * show the form
		 */
		this.show();
		
		/**
		 * Now that the form is rendered, give gw its size restrictions
		 */
		gw.setBoundaries(mv.getWidth(), mv.getHeight());
		/**
		 * Initialize the game world
		 */
		gw.init();
	}
}
