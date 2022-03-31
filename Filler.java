import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class Filler extends JComponent {
	private static final long serialVersionUID = 0000;
	public Filler(){}
	public void paintComponent(Graphics g){
		showBord(g);
	}

	public void showBord(Graphics g){
		Graphics2D pen = (Graphics2D) g;
		int width = getWidth();
		int height = getHeight();
		Rectangle r = new Rectangle (0,0, width, height);
		pen.draw(r);
	}
}