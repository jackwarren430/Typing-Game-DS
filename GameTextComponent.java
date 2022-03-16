import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.lang.Math;

public class GameTextComponent extends JComponent {
	private int gameSize;
	private String[] wordList;

	public GameTextComponent(String[] wordList){
		this.wordList = wordList;
		this.gameSize = wordList.length;
	}

	public void paintComponent(Graphics g){
		for (Vector[] row : test){
			for (Vector v : row){
				v.draw(g);
			}
		}
	}

}