/**
 * Game.java
 * defines the Game class
 */
/**
 * Sam Lee, CSC 133, Professor Scott Gordon
 */
package com.mycompany.a3;
import com.codename1.ui.Form;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.util.UITimer;
/**
 * Game class serves as the main control and logic program for the game
 */
public class Game extends Form implements Runnable {
   private static final int CLOCK_RATE = 20;
   private GameWorld gw;
   private MapView mv;
   private ScoreView sv;
   private Container east, west, south;
   private UITimer timer;
   private boolean paused;
	
   AccelCommand accelCmd;
   BrakeCommand brakeCmd;
   LeftTurnCommand leftCmd;
   RightTurnCommand rightCmd;
   ExitCommand exitCmd;
   ChangeStrategiesCommand strategyCmd;
   ToggleSoundCommand toggleSoundCmd;
   AboutCommand aboutCmd;
   HelpCommand helpCmd;
   PositionCommand positionCmd;
   PlayPauseCommand playPauseCmd;
	
   GameButton accelButton;
   GameButton brakeButton;
   GameButton leftTurnButton;
   GameButton rightTurnButton;
   GameButton strategiesButton;
   GameButton playPauseButton;
   GameButton positionButton;
   CheckBox soundCheckBox;
	/**
	 * build a new instance of the Game object, creates the user interface, and initializing the game world
	 */
   public Game() {		
      gw = new GameWorld(this);
      timer = new UITimer(this);
   	
      mv = new MapView(gw, this);
      sv = new ScoreView();
   	
   	/**
   	 * add out observers to the GameWorld so they can be updated when the state changes
   	 */
      gw.addObserver(mv);
      gw.addObserver(sv);
   
   	/**
   	 * build all of the command objects that the user interface will need
   	 */
      accelCmd = new AccelCommand(gw);
      brakeCmd = new BrakeCommand(gw);
      leftCmd = new LeftTurnCommand(gw);
      rightCmd = new RightTurnCommand(gw);
      exitCmd = new ExitCommand(gw);
      strategyCmd = new ChangeStrategiesCommand(gw);
      toggleSoundCmd = new ToggleSoundCommand(gw);
      aboutCmd = new AboutCommand();
      helpCmd = new HelpCommand();
      positionCmd = new PositionCommand(gw, this);
      positionCmd.setEnabled(false);
   
   	/**
   	 * create user interaction elements, attaching commands to them
   	 */
      accelButton = new GameButton(accelCmd);
      brakeButton = new GameButton(brakeCmd);
      leftTurnButton = new GameButton(leftCmd);
      rightTurnButton = new GameButton(rightCmd);
      strategiesButton = new GameButton(strategyCmd);
      playPauseButton = new GameButton("playPause");
      playPauseCmd = new PlayPauseCommand(this, playPauseButton);
      playPauseButton.setCommand(playPauseCmd);
      positionButton = new GameButton(positionCmd);
      soundCheckBox = new CheckBox();
   	
   	/**
   	 * create listeners for keyboard keys, attaching commands to them
   	 */
      addKeyListener('a', accelCmd);
      addKeyListener('b', brakeCmd);
      addKeyListener('l', leftCmd);
      addKeyListener('r', rightCmd);
   	
   	/**
   	 * build the toolbar
   	 */
      Toolbar myToolbar = new Toolbar();
      Toolbar.setOnTopSideMenu(true);
      soundCheckBox.setCommand(toggleSoundCmd);
      soundCheckBox.setSelected(gw.getSoundState());
      soundCheckBox.getAllStyles().setBgTransparency(255);
      soundCheckBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);
      setToolbar(myToolbar);
   	
   	/**
   	 * build the toolbar menu
   	 */
      myToolbar.addCommandToSideMenu(exitCmd);
      myToolbar.addComponentToSideMenu(soundCheckBox);
      myToolbar.addCommandToSideMenu(helpCmd);
      myToolbar.addCommandToSideMenu(aboutCmd);
   	
      myToolbar.setTitle("Sili-Challenge Game");
      myToolbar.addCommandToRightBar(helpCmd);
   	
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
      west.getAllStyles().setPadding(TOP, 40);
   	
      east.addComponent(brakeButton);
      east.addComponent(rightTurnButton);
      east.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
      east.getAllStyles().setPadding(TOP, 40);
   	
      south.setLayout(new FlowLayout(CENTER));
      south.addComponent(positionButton);
      south.addComponent(playPauseButton);
   	
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
   	 * now that the form is rendered, give gw its size restrictions
   	 */
      gw.setBoundaries(mv.getWidth(), mv.getHeight());
   	/**
   	 * initialize the game world
   	 */
      gw.init();
      
      timer.schedule(CLOCK_RATE,  true,  this);
   }
	
	/**
	 * calls GameWorld tick method to process the next step in the game clock
	 */
   public void run() {
      gw.tick(CLOCK_RATE);
   }
	
	/**
	 * returns whether or not the game has been paused
	 * @return true if the game has been paused and false if the game is not paused
	 */
   public boolean isPaused() {
      return paused;
   }
	
	/**
	 * switch between paused and unpaused state
	 */
   public void togglePaused() {
      paused = !paused;
      if(paused) {
         timer.cancel();
         removeKeyListener('a', accelCmd);
         removeKeyListener('b', brakeCmd);
         removeKeyListener('l', leftCmd);
         removeKeyListener('r', rightCmd);
         accelCmd.setEnabled(false);
         brakeCmd.setEnabled(false);
         leftCmd.setEnabled(false);
         rightCmd.setEnabled(false);
         strategyCmd.setEnabled(false);
         positionCmd.setEnabled(true);
         accelButton.setEnabled(false);
         brakeButton.setEnabled(false);
         leftTurnButton.setEnabled(false);
         rightTurnButton.setEnabled(false);
         strategiesButton.setEnabled(false);
         positionButton.setEnabled(true);
         gw.stopAmbient();
      } else {
         timer.schedule(CLOCK_RATE,  true,  this);
         addKeyListener('a', accelCmd);
         addKeyListener('b', brakeCmd);
         addKeyListener('l', leftCmd);
         addKeyListener('r', rightCmd);
         accelCmd.setEnabled(true);
         brakeCmd.setEnabled(true);
         leftCmd.setEnabled(true);
         rightCmd.setEnabled(true);
         strategyCmd.setEnabled(true);
         positionCmd.setEnabled(false);
         accelButton.setEnabled(true);
         brakeButton.setEnabled(true);
         leftTurnButton.setEnabled(true);
         rightTurnButton.setEnabled(true);
         strategiesButton.setEnabled(true);
         positionButton.setEnabled(false);
         gw.playAmbient();
         gw.unselectAll();
         gw.disablePositioning();
      }
   }
}
