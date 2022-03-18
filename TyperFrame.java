
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

	//jframe vars
	private JFrame thisFrame = this;
	private JPanel mainPanel;
	private GameComponent gameComp;
	private BottomBarComponent bottomBarComp;

	//navigation vars
	private Boolean gameStart;
	private Boolean isHome;

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
		t = new Timer(1000, new MovementListener());
		this.addMouseListener(new MouseMovementListener());
		this.addKeyListener(new KeyBoardListener());
  		this.setFocusable(true);
  	}

  	private void configurePanel() throws IOException{
  		mainPanel = new JPanel(new BorderLayout());
  		gameComp = new GameComponent(this);
  		bottomBarComp = new BottomBarComponent(this);

  		mainPanel.add(gameComp, BorderLayout.CENTER);
  		mainPanel.add(bottomBarComp, BorderLayout.PAGE_END);
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
    		if (gameStart){
    			if (gameMode.equals("count")){
    				updateCountMode();
    			} else if (gameMode.equals("time")){
    				updateTimeMode();
    			}
    			updateBottomBar();
    		}
    	}
   }

   private void updateBottomBar(){
   		double currWPM = (double)gameInput.size() / (double)gameTimeCount;
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

    public Boolean getIsHome(){
    	return isHome;
    }

    public void setIsHome(Boolean b){
    	isHome = b;
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




