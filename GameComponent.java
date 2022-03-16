import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.lang.Math;

public class GameComponent extends JComponent{
	private String[] wordArr;
	private int gameSize;

	public GameComponent(String[] wordArr){
		this.wordArr = wordArr;
		this.gameSize = wordArr.length;
	}

	public void paintComponent(Graphics g){
		Graphics2D pen = (Graphics2D) g;
		pen.drawString("wham", 200, 200);
	}


}