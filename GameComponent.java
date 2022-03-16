import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.lang.Math;

public class GameComponent extends JComponent{
	private String[] wordArr = {"blam", "kablooey", "whabul"};
	private int gameSize;

	public GameComponent(){
		this.gameSize = wordArr.length;
	}

	public void paintComponent(Graphics g){
		Graphics2D pen = (Graphics2D) g;
		pen.drawString("wham", 400, 200);
		Container c = this.getTopLevelAncestor();
		TyperFrame t = (TyperFrame)c;
		t.getFrameSize();
	}


}