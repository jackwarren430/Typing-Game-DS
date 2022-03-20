
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

	//navigation vars
	private Boolean gameStart;
	private Boolean isHome;
	private Boolean isInfoPage;

	//game vars
	private int gameSize;
	private ArrayList<String> gameInput;
	private int gameTimeCount;
	private int gameTimeLength;
	private String gameMode; // count -> end at gameSize; time -> end at GameTimeLength

	//storage/stats
	private Profile loadedProfile;

	public TyperFrame() throws IOException{

		gameStart = false;
		isHome = true;
		isInfoPage = false;

		gameSize = 20;
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
  		//mainPanel.setOpaque(false);
		t = new Timer(1000, new MovementListener());
		this.addMouseListener(new MouseMovementListener());
		this.addKeyListener(new KeyBoardListener());
  		this.setFocusable(true);
  	}

  	private void configurePanel() throws IOException{
  		mainPanel = new JPanel();
  		//mainPanel.setBackground(Color.blue);
		mainLayout = new BorderLayout();
  		mainPanel.setLayout(mainLayout);

  		gameComp = new GameComponent(this);
  		bottomBarComp = new BottomBarComponent(this);
  		menuBarComp = new TopBarComponent(this);
  		infoComp = new InfoComponent(this);

  		mainPanel.add(gameComp, BorderLayout.CENTER);
  		mainPanel.add(bottomBarComp, BorderLayout.SOUTH);
  		mainPanel.add(menuBarComp, BorderLayout.NORTH);

  		bottomBarComp.setPreferredSize(new Dimension(getFrameSize()[0], getFrameSize()[1]/8));
    	menuBarComp.setPreferredSize(new Dimension(getFrameSize()[0], getFrameSize()[1]/8));
    	mainLayout.getLayoutComponent(BorderLayout.CENTER).setPreferredSize(new Dimension(getFrameSize()[0], 6*getFrameSize()[1]/8));
  	}

  	public void startGame(){
  		gameStart = true;
  		gameTimeCount = 0;
  		gameInput = new ArrayList<String>();
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
    	public void actionPerformed(ActionEvent e){
    		repaint();
    		bottomBarComp.setPreferredSize(new Dimension(getFrameSize()[0], getFrameSize()[1]/8));
    		menuBarComp.setPreferredSize(new Dimension(getFrameSize()[0], getFrameSize()[1]/8));
    		mainLayout.getLayoutComponent(BorderLayout.CENTER).setPreferredSize(new Dimension(getFrameSize()[0], 6*getFrameSize()[1]/8));

    		if (gameStart){
    			if (gameMode.equals("count")){
    				updateCountMode();
    			} else if (gameMode.equals("time")){
    				updateTimeMode();
    			}
    			updateBottomBar();
    		} else {

    		}
    	}
   }

   private void updateBottomBar(){
   		int currWPM = 60 * gameInput.size() / gameTimeCount;
   		bottomBarComp.update(currWPM, gameTimeCount);
   }

   private void updateCountMode(){
   		gameTimeCount++;
   }

   private void updateTimeMode(){

   }

  	class MouseMovementListener implements MouseListener{
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
   		private String tempIn = "";

		public void keyTyped(KeyEvent e){}
		public void keyReleased(KeyEvent e){}
		public void keyPressed(KeyEvent e){
			if (gameStart){
				int keyCode = e.getKeyCode();
				String charIn = String.valueOf(Character.toString(keyCode));
				//System.out.println(keyCode + " -> " + charIn);
				if (gameMode.equals("count")){
					if (keyCode == 32){
						if (!tempIn.strip().equals("")){
							gameInput.add(tempIn);
							tempIn = "";
							if (gameInput.size() == gameSize){
								endGame();
							}
						} else {
 							// do nothing!!!! :D :D :D :D :D
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
				} else if (gameMode.equals("time")){

				}
			}
		}

    }

    public void goHomePage(){
    	if (!isHome && !gameStart){
    		clearNavVars();
    		isHome = true;
    		mainLayout.removeLayoutComponent(mainLayout.getLayoutComponent(BorderLayout.CENTER));
    		mainPanel.add(gameComp, BorderLayout.CENTER);
    		setVisible(true);
    		mainPanel.repaint();
    	}
    }

    public void goStatsPage(){

    }

    public void goInfoPage(){
    	if (!gameStart){
    		clearNavVars();
    		isInfoPage = true;
    		mainLayout.removeLayoutComponent(mainLayout.getLayoutComponent(BorderLayout.CENTER));
    		mainPanel.add(infoComp, BorderLayout.CENTER);
    		setVisible(true);
    		mainPanel.repaint();
    	}
    }

    public void goGameOptPage(){

    }

    public void goSettingsPage(){

    }

    public Boolean getIsInfoPage(){
    	return isInfoPage;
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

    public Profile getLoadedProfile(){
    	return loadedProfile;
    }

    public void setLoadedProfile(Profile p){
    	loadedProfile = p;
    }

}




