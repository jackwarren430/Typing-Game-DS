import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.lang.Math;

import java.io.*;
import java.util.*;

public class GameComponent extends JComponent {
	private static final long serialVersionUID = 0000;

	private ArrayList<String> wordArr;
	TyperFrame frame;

	private int height;
	private int width;
	private Polygon startButton;
	private Font stringFont;

	public GameComponent(TyperFrame frame) throws IOException{
		//frame = (TyperFrame)this.getTopLevelAncestor();
		this.frame = frame;
		width = frame.getFrameSize()[0];
		height = frame.getFrameSize()[1];

		initWordArr();

		stringFont = new Font( "SansSerif", Font. PLAIN, width / 20);
	}

	public void paintComponent(Graphics g){
		Graphics2D pen = (Graphics2D) g;
		width = frame.getFrameSize()[0];
		height = frame.getFrameSize()[1];

		// Rectangle border = new Rectangle(width/8, height/4, 3*width/4, height/2);
		// pen.draw(border);
		// frame.repaint();

		pen.drawRect(width/8, height/4, 3*width/4, height/2);

		if (!frame.getGameStart()){
			paintStartButton(pen);
		} else {
			paintWords(pen);
		}
	
	}

	private void paintWords(Graphics2D pen){
		pen.setFont(stringFont);
		pen.drawString("hellooooooo", width/2, height/2);
		
	}	

	private void paintStartButton(Graphics2D pen){
		int[] xVals = new int[] {7*width/16, 9*width/16, 9*width/16};
		int[] yVals = new int[] {height/2, 3*height/8, 5*height/8};
		startButton = new Polygon(xVals, yVals, 3);
		pen.drawPolygon(startButton);
	}

	public void checkForStartClick(int x, int y){
		if (startButton.contains(x, y)){
			frame.setGameStart(true);
			//System.out.println("button clicked");
			frame.repaint();
		}
	}

	public void changeFont(){

	}

	public void initWordArr() throws IOException{
		Scanner scan = new Scanner(new File("dictionary.txt"));
		ArrayList<String> hold = new ArrayList<String>();
		wordArr = new ArrayList<String>();
		while (scan.hasNextLine()){
			hold.add(scan.nextLine().strip());
		}
		for (int i = 0; i < frame.getGameSize(); i++){
			int randInt =  (int)((Math.random() * (frame.getGameSize()) + 1));
			wordArr.add(hold.get(randInt));
		}
	}

}