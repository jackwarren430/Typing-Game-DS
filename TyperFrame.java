
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.lang.Math;


public class TyperFrame extends JFrame{

	private Timer t;

	public static final int WIDTH = 1300;
	public static final int HEIGHT = 700;

	private JFrame thisFrame = this;

	private GameComponent game;

	public TyperFrame(){
		this.setSize(WIDTH, HEIGHT + 25);
		this.setTitle("Typing Wizard");
		addComponents();		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  	this.setVisible(true);
  	}

  	public void go(){
  		t.start();
  	}

  	public void addComponents(){

  		game = new GameComponent();
  		this.add(game);

		t = new Timer(10, new MovementListener());
		this.addMouseMotionListener(new MouseMovementListener());
		this.addKeyListener(new KeyBoardListener());
  		this.setFocusable(true);
  	}

  	public int[] getFrameSize(){
  		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  		pack(); // Need this, otherwise insets() show as 0.  
		int scrW = (int)screenSize.getWidth();
		int scrH = (int)screenSize.getHeight();
		int innerW = scrW - getInsets().left - getInsets().right;
		int innerH = scrH - getInsets().top - getInsets().bottom;
  		// Need to setSize(), otherwise pack() will collapse the empty JFrame
  		setSize(scrW, scrH);
  		int[] toReturn = {innerW, innerH};
  		return toReturn;
  	}

  	class MovementListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
        	//repaint();
        	//System.out.println("My mouse moved!");
    	}
   	}

  	class MouseMovementListener implements MouseMotionListener{
  		public void mouseMoved(MouseEvent me){
  			
      	}
      	public void mouseDragged(MouseEvent me){
      		
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

}