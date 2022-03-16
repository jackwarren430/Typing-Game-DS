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

	public GameComponent() throws IOException{
		initWordArr();

		frame = (TyperFrame)this.getTopLevelAncestor();
	}

	public void paintComponent(Graphics g){
		Graphics2D pen = (Graphics2D) g;
		int width = frame.getFrameSize()[0];
		int height = frame.getFrameSize()[1];


		if (frame.getIsHome() && !frame.getGameStart()){
			paintStartButton(pen);
		} else if (!frame.getIsHome() && frame.getGameStart()){
			paintWords(pen);
		}
		
	
	}

	private void paintWords(Graphics2D pen){
		for (String word : wordArr){
			pen.drawString(word, );
		}
	}	

	private void paintStartButton(Graphics2D pen){

	}

	public void initWordArr() throws IOException{
		Scanner scan = new Scanner(new File("dictionary.txt"));
		hold = new ArrayList<String>();
		while (scan.hasNextLine()){
			hold.add(scan.nextLine().strip());
		}

		for (int i = 0; i < frame.getGameSize(); i++){
			int randInt = 0 + (int)(Math.random() * ((frame.getGameSize()) + 1));
			wordArr.add(hold.at(randInt));
		}
	}

}