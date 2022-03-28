import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.lang.Math;

import java.io.*;
import java.util.*;

public class GameComponent extends JComponent {
	private static final long serialVersionUID = 0000;

	TyperFrame frame;
	TyperGame game;

	private int height;
	private int width;
	private Polygon startButton;
	private Font stringFont;

	public GameComponent(TyperFrame frame) throws IOException{
		//frame = (TyperFrame)this.getTopLevelAncestor();
		this.frame = frame;
		width = frame.getFrameSize()[0];
		height = 6*frame.getFrameSize()[1]/8;

		game = null;

		stringFont = new Font( "GillSans", Font. PLAIN, width / 40);
	}

	public void paintComponent(Graphics g){
		Graphics2D pen = (Graphics2D) g;
		width = frame.getFrameSize()[0];
		height = 6*frame.getFrameSize()[1]/8;;

		//height = height - 50;

		if (!frame.getGameStart()){
			pen.drawRect(width/8, height/8, 3*width/4, 3*height/4);
			paintStartButton(pen);
		} else {
			pen.drawRect(width/8, height/8, 3*width/4, 3*height/4);
			paintWords(pen);
		}
	
	}

	private void paintWords(Graphics2D pen){
		pen.setFont(stringFont);
		ArrayList<String> wordArr = game.getWordArr();
		int wordsPerRow = frame.getGameSize() / 4;
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < wordsPerRow; j++){
				String currWord = wordArr.get((i * wordsPerRow) + j);
				int locX = width/8 + ((j * 3*width)/(4 * wordsPerRow)) + width/32;
				int locY = height/4 + (((i+1) * height)/(8)) - 3*height/64;
				pen.drawString(currWord, locX, locY);
			}
		}
		
	}	

	private void paintStartButton(Graphics2D pen){
		int[] xVals = new int[] {9*width/16, 7*width/16, 7*width/16 };
		int[] yVals = new int[] {height/2, 3*height/8, 5*height/8};
		startButton = new Polygon(xVals, yVals, 3);
		pen.fillPolygon(startButton);
	}

	public void wrapUpGame(ArrayList<String> finalInput, int finalTime){
		ArrayList<String> missedWords = game.getErrors(finalInput);
		GameStat recentGameStat = new GameStat(missedWords, finalTime, game.getTotalCharCount(), frame.getGameSize());
		frame.getLoadedProfile().add(recentGameStat, missedWords);
	}

	public void checkForStartClick(int x, int y) throws IOException{
		y = 6*y/8;
		if (startButton.contains(x, y)){
			game = new TyperGame(frame.getGameSize());
			frame.startGame();
		}
	}

	public void changeFont(){

	}


}