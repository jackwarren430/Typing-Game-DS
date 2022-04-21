
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import java.lang.Math;

import java.io.*;
import java.util.*;

public class TyperFrame extends JFrame{
	private static final long serialVersionUID = 0000;

	private Timer t;

	public static final int WIDTH = 1300;
	public static final int HEIGHT = 700;

	//component vars
	private JFrame thisFrame = this;
	private JPanel mainPanel;
	private BorderLayout mainLayout;
	private GameComponent gameComp;
	private BottomBarComponent bottomBarComp;
	private TopBarComponent menuBarComp;
	private InfoComponent infoComp;
	private GameStatsComponent gameStatsComp;
	private SettingsComponent settingsComp;
	private GameOptComponent optComp;

	//navigation vars
	private Boolean gameStart;
	private Boolean isHome;
	private Boolean isInfoPage;
	private Boolean isStatsPage;
	private Boolean isGameOptPage;
	private Boolean isSettingsPage;

	//game vars
	private int gameSize;
	private ArrayList<String> gameInput;
	private int gameTimeCount;
	private int gameTimeLength;
	private String gameMode; // "count" -> end at gameSize; "time" -> end at GameTimeLength
	private String tempIn;
	private ArrayList<int[]> errorLocs;

	//storage/stats
	private Profile loadedProfile;

	public TyperFrame() throws IOException{

		try {
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		gameStart = false;
		isHome = true;
		isInfoPage = false;
		isStatsPage = false;
		isGameOptPage = false;
		isSettingsPage = false;
		tempIn = "";

		gameSize = 24;
		gameTimeLength = 30;
		gameMode = "count";

		loadedProfile = new Profile();

		this.setSize(WIDTH, HEIGHT + 25);
		this.setTitle("Typing Wizard");

		addPanel();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  	this.setVisible(true);
  	}

  	public void go() throws IOException{
  		t.start();
  	}

  	public void addPanel() throws IOException{
  		configurePanel();
  		this.add(mainPanel);
		t = new Timer(100, new MovementListener());
		this.addMouseListener(new MouseMovementListener());
		this.addKeyListener(new KeyBoardListener());
  		this.setFocusable(true);
  	}

  	private void configurePanel() throws IOException{
  		mainPanel = new JPanel();
		mainLayout = new BorderLayout();
  		mainPanel.setLayout(mainLayout);

  		gameComp = new GameComponent(this);
  		gameStatsComp = new GameStatsComponent(this);
  		bottomBarComp = new BottomBarComponent(this);
  		menuBarComp = new TopBarComponent(this);
  		infoComp = new InfoComponent(this);
  		settingsComp = new SettingsComponent(this);
			optComp = new GameOptComponent(this);

  		mainPanel.add(menuBarComp, BorderLayout.NORTH);
  		mainPanel.add(gameComp, BorderLayout.CENTER, SwingConstants.CENTER);
  		mainPanel.add(bottomBarComp, BorderLayout.SOUTH);

  		updateColors(Styles.navy, Styles.lightBlue);
  		//updateFont();

  		bottomBarComp.setPreferredSize(new Dimension(getFrameSize()[0], getFrameSize()[1]/8));
    	menuBarComp.setPreferredSize(new Dimension(getFrameSize()[0], getFrameSize()[1]/8));
    	mainLayout.getLayoutComponent(BorderLayout.CENTER).setPreferredSize(new Dimension(getFrameSize()[0], 6*getFrameSize()[1]/8));
  	}

  	public void updateColors(Color backgroundColor, Color foregroundColor){
  		mainPanel.setBackground(backgroundColor);
  		mainPanel.setForeground(foregroundColor);
  		bottomBarComp.updateColors(backgroundColor, foregroundColor);
  		gameStatsComp.updateColors(backgroundColor, foregroundColor);
  		menuBarComp.updateColors(backgroundColor, foregroundColor);
  		settingsComp.updateColors(backgroundColor, foregroundColor);
  	}

  	public void updateFont(Font f){
  		gameComp.changeFont(f);
  	}

  	public void startGame(){
  		gameStart = true;
  		gameTimeCount = 0;
  		gameInput = new ArrayList<String>();
  		errorLocs = new ArrayList<int[]>();
  	}

  	public void endGame(){
  		gameStart = false;
  		gameComp.wrapUpGame(gameInput, gameTimeCount);
  	}

  	public int[] getFrameSize(){
  		Dimension screenSize = getSize();
		int scrW = (int)screenSize.getWidth();
		int scrH = (int)screenSize.getHeight() - 25;
  		int[] toReturn = new int[] {scrW, scrH};
  		return toReturn;

  	}

  	class MovementListener implements ActionListener{
  		int count = 0;
    	public void actionPerformed(ActionEvent e){

    		repaint();
			if (isHome || isStatsPage){
    			bottomBarComp.setPreferredSize(new Dimension(getFrameSize()[0], getFrameSize()[1]/8));
    		}
  			menuBarComp.setPreferredSize(new Dimension(getFrameSize()[0], getFrameSize()[1]/8));
    		mainLayout.getLayoutComponent(BorderLayout.CENTER).setPreferredSize(new Dimension(getFrameSize()[0], 6*getFrameSize()[1]/8));
    		if (isStatsPage){
    			gameStatsComp.setPrefSize();
    		} else if (isSettingsPage){
    			settingsComp.setPrefSize();
    		}
    		if (count == 10){
    			if (gameStart){
	    			gameTimeCount++;
	    			updateBottomBar();
	    		} else {

	    		}
	    		count = 0;
    		} else {
    			count++;
    		}

    	}
   }

   private void updateBottomBar(){
   		int currWPM = 60 * gameInput.size() / gameTimeCount;
   		bottomBarComp.update(currWPM, gameTimeCount);
   }

  	class MouseMovementListener implements MouseListener {
		public void mouseEntered(MouseEvent me){}
		public void mouseExited(MouseEvent me){}
		public void mousePressed(MouseEvent me){}
		public void mouseReleased(MouseEvent me){}
		public void mouseClicked(MouseEvent me){
			int x = me.getX();
			int y = me.getY() - 25;
			if (!gameStart && isHome){
				try {
					gameComp.checkForStartClick(x, y);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
  	}

   	class KeyBoardListener implements KeyListener {
		public void keyTyped(KeyEvent e){}
		public void keyReleased(KeyEvent e){}
		public void keyPressed(KeyEvent e){
			if (gameStart){
				int keyCode = e.getKeyCode();
				String charIn = String.valueOf(Character.toString(keyCode));
				//System.out.println(keyCode + " -> " + charIn);
				if (gameMode.equals("count")){
					if (keyCode == 32 || keyCode == 10){
						if (!tempIn.strip().equals("")){
							gameInput.add(tempIn);
							tempIn = "";
							if (gameInput.size() == gameSize){
								endGame();
							}
						}
					} else if (keyCode == 8){
						if (tempIn.equals("") && !gameInput.isEmpty()){
							tempIn = gameInput.remove(gameInput.size()-1);
						} else if (!tempIn.equals("")){
							tempIn = tempIn.substring(0, tempIn.length()-1);
						}
					} else {
						tempIn += charIn;
					}
					//checking for errors
					errorLocs = new ArrayList<int[]>();
					for (int i = 0; i < gameInput.size(); i++){
						String actualWord = gameComp.getGame().getWordArr().get(i).toLowerCase();
						String inWord = gameInput.get(i).toLowerCase();
						if (!inWord.equals(actualWord)){
							int[] errorLoc = new int[3];
							int lWidth = getFrameSize()[0]/8;
							int lHeight = 9*getFrameSize()[1]/64;
							errorLoc[0] = lWidth + ((lWidth*6)/(gameSize/4))*(i%(gameSize/4)) + lWidth/4;
    						errorLoc[1] = lHeight + ((int)((double)lHeight/1.5) * (i/(gameSize/4))) + 9*lHeight/10;
							errorLoc[2] = actualWord.length();
							errorLocs.add(errorLoc);
						}
					}
				} else if (gameMode.equals("time")){

				}
			} else if (isHome){
				if (e.getKeyCode() == 32){
					try{
						gameComp.INITTYPINGWIZARD();
					} catch (IOException flipOff) {
						flipOff.printStackTrace();
					}

				}
			}

		}

    }

    public int[] getOverlayLoc(int lWidth, int lHeight){
    	int[] loc = new int[2];
    	loc[0] = lWidth + ((lWidth*6)/(gameSize/4))*(gameInput.size()%(gameSize/4)) + (int)((double)tempIn.length()*10.5) + lWidth/4;
    	loc[1] = lHeight + ((int)((double)lHeight/1.5) * (gameInput.size()/(gameSize/4))) + lHeight/2;
    	return loc;
    }

    public void goHomePage(){
    	if (!isHome && !gameStart){
    		clearNavVars();
    		isHome = true;
    		Component mainComp = mainLayout.getLayoutComponent(BorderLayout.CENTER);
    		mainLayout.removeLayoutComponent(mainComp);
    		mainComp.setVisible(false);
    		mainPanel.add(gameComp, BorderLayout.CENTER);
    		mainPanel.add(bottomBarComp, BorderLayout.SOUTH);
    		bottomBarComp.setVisible(true);
    		gameComp.setVisible(true);
    	}
    }

    public void goStatsPage(){
    	if (!gameStart){
    		clearNavVars();
    		isStatsPage = true;
    		Component mainComp = mainLayout.getLayoutComponent(BorderLayout.CENTER);
    		mainLayout.removeLayoutComponent(mainComp);
    		mainComp.setVisible(false);
    		mainPanel.add(gameStatsComp, BorderLayout.CENTER);
    		mainPanel.add(bottomBarComp, BorderLayout.SOUTH);
    		bottomBarComp.setVisible(true);
    		gameStatsComp.setVisible(true);
    		gameStatsComp.updateColors(mainPanel.getBackground(), mainPanel.getForeground());
    	}
    }

    public void goInfoPage(){
    	if (!gameStart){
    		clearNavVars();
    		isInfoPage = true;
    		Component mainComp = mainLayout.getLayoutComponent(BorderLayout.CENTER);
    		mainLayout.removeLayoutComponent(mainComp);
    		mainLayout.removeLayoutComponent(bottomBarComp);
    		bottomBarComp.setVisible(false);
    		mainComp.setVisible(false);
    		mainPanel.add(infoComp, BorderLayout.CENTER);
    		infoComp.setVisible(true);
    	}
    }

    public void goGameOptPage(){
    	if (!gameStart){
    		clearNavVars();
    		isSettingsPage = true;
    		mainLayout.removeLayoutComponent(bottomBarComp);
    		bottomBarComp.setVisible(false);
    		Component mainComp = mainLayout.getLayoutComponent(BorderLayout.CENTER);
    		mainLayout.removeLayoutComponent(mainLayout.getLayoutComponent(BorderLayout.CENTER));
    		mainComp.setVisible(false);
    		mainPanel.add(optComp, BorderLayout.CENTER);
    		optComp.setVisible(true);

    	}
    }

    public void goSettingsPage(){
    	if (!gameStart){
    		clearNavVars();
    		isSettingsPage = true;
    		mainLayout.removeLayoutComponent(bottomBarComp);
    		bottomBarComp.setVisible(false);
    		Component mainComp = mainLayout.getLayoutComponent(BorderLayout.CENTER);
    		mainLayout.removeLayoutComponent(mainLayout.getLayoutComponent(BorderLayout.CENTER));
    		mainComp.setVisible(false);
    		mainPanel.add(settingsComp, BorderLayout.CENTER);
    		settingsComp.setVisible(true);
    		//settingsComp.resetDeleteButt();

    	}
    }

    public void saveProfile(String name){
    	loadedProfile.setName(name);
    	loadedProfile.saveProfile();
    }

    public void loadProfile(String name){
    	loadedProfile = new Profile(name);
    	gameStatsComp = new GameStatsComponent(this);
    }

    public Boolean getIsInfoPage(){
    	return isInfoPage;
    }

    public Boolean getIsGameOptPage(){
    	return isGameOptPage;
    }

    public Boolean getIsStatsPage(){
    	return isStatsPage;
    }

    public Boolean getIsSettingsPage(){
    	return isSettingsPage;
    }

    public Boolean getIsHome(){
    	return isHome;
    }

    public void setIsHome(Boolean b){
    	isHome = b;
    }

    public void clearNavVars(){
    	isHome = false;
    	isInfoPage = false;
    	isStatsPage = false;
    	isGameOptPage = false;
    	isSettingsPage = false;
    }

    public Boolean getGameStart(){
    	return gameStart;
    }

    public void setGameStart(Boolean b){
    	gameStart = b;
    }

    public int getGameSize(){
    	return gameSize;
    }

    public void setGameSize(int s){
    	gameSize = s;
    }

    public String getGameMode(){
    	return gameMode;
    }

    public void setGameMode(String m){
    	gameMode = m;
    }

    public GameStatsComponent getGameStatsComp(){
    	return gameStatsComp;
    }

    public BottomBarComponent getBottomBarComp(){
    	return bottomBarComp;
    }

    public Profile getLoadedProfile(){
    	return loadedProfile;
    }

    public void setLoadedProfile(Profile p){
    	loadedProfile = p;
    	gameStatsComp.setLoadedProfile(p);
    }

    public ArrayList<int[]> getErrorLocs(){
    	return errorLocs;
    }

}
