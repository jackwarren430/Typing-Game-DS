
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
		t = new Timer(500, new MovementListener());
		this.addMouseListener(new MouseMovementListener());
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
		int scrH = (int)screenSize.getHeight() - 25;
  		int[] toReturn = new int[] {scrW, scrH};
  		return toReturn;

  	}


  	class MovementListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		//System.out.println(getFrameSize()[0]);
    	}
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
				game.checkForStartClick(x, y);
				//System.out.println("click!");
			}
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




