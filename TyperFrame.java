
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.lang.Math;

import java.io.*;


public class TyperFrame extends JFrame{
	private static final long serialVersionUID = 0000;

	private Timer t;

	public static final int WIDTH = 1300;
	public static final int HEIGHT = 700;

	//jframe vars
	private JFrame thisFrame = this;
	private JPanel mainPanel;
	private GameComponent game;

	//navigation vars
	private Boolean gameStart;
	private Boolean isHome;

	//game vars
	private int gameSize;

	public TyperFrame() throws IOException{

		gameStart = false;
		isHome = true;

		gameSize = 20;

		this.setSize(WIDTH, HEIGHT + 25);
		this.setTitle("Typing Wizard");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  	this.setVisible(true);

	  	addPanel();
	  	repaint();
  	}

  	public void go() throws IOException{
  		t.start();
  	}

  	public void addPanel() throws IOException{
  		configurePanel();
  		this.add(mainPanel);
		t = new Timer(500, new MovementListener());
		this.addMouseMotionListener(new MouseMovementListener());
		this.addKeyListener(new KeyBoardListener());
  		this.setFocusable(true);
  	}

  	private void configurePanel() throws IOException{
  		mainPanel = new JPanel(new BorderLayout());
  		game = new GameComponent(this);
  		mainPanel.add(game, BorderLayout.CENTER);
  	}

  	public int[] getFrameSize(){
  		Dimension screenSize = getSize();
		int scrW = (int)screenSize.getWidth();
		int scrH = (int)screenSize.getHeight() - 23;
  		int[] toReturn = {scrW, scrH};
  		return toReturn;

  	}


  	class MovementListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
        	repaint();
    	}
   	}


  	class MouseMovementListener implements MouseMotionListener{
  		public void mouseMoved(MouseEvent me){
  			
      	}
      	public void mouseDragged(MouseEvent me){
      		
      		//System.out.println(getSize());
      	}
      	public void mouseClicked(MouseEvent me){

      	}
  	}
  	

   	class KeyBoardListener implements KeyListener {
   /*
      final int UP = 38;
         final int DOWN = 40;
         final int LEFT = 37;
         final int RIGHT = 39;
   */
		public void keyTyped(KeyEvent e){}
		public void keyReleased(KeyEvent e){}
		public void keyPressed(KeyEvent e){


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

}




