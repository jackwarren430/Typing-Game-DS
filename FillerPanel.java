import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class FillerPanel extends JPanel {
	private static final long serialVersionUID = 0000;
	private Filler fill1;
	private Filler fill2;
	private Filler fill3;
	private Filler fill4;

	public FillerPanel(){
		fill1 = new Filler();
		fill2 = new Filler();
		fill3 = new Filler();
		fill4 = new Filler();

		add(fill1, BorderLayout.NORTH);
		add(fill2, BorderLayout.SOUTH);
		add(fill3, BorderLayout.EAST);
		add(fill4, BorderLayout.WEST);
		setVisible(true);
	}

	public void add(JComponent comp){
		super.add(comp, BorderLayout.CENTER);
	}

	public void adjust(int width, int height){
		fill1.setPreferredSize(new Dimension(width*3, height));
		fill2.setPreferredSize(new Dimension(width*3, height));
		fill3.setPreferredSize(new Dimension(width,height));
		fill4.setPreferredSize(new Dimension(width,height));
	}

}