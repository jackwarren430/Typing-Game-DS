
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

	public TyperFrame(){
		this.setSize(WIDTH, HEIGHT + 25);
		this.setTitle("Visuals");
		addComponents();		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  	this.setVisible(true);
  	}

  	public void go(){
  		t.start();
  	}

  	public void addComponents(){

		t = new Timer(10, new MovementListener());
		this.addMouseMotionListener(new MouseMovementListener());
  		this.setFocusable(true);
  	}

}