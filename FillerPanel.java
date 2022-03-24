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

	private Filler fill5;
	private Filler fill6;
	private Filler fill7;
	private Filler fill8;

	public FillerPanel(Component comp){
		fill1 = new Filler();
		fill2 = new Filler();
		fill3 = new Filler();
		fill4 = new Filler();
		fill5 = new Filler();
		fill6 = new Filler();
		fill7 = new Filler();
		fill8 = new Filler();

		setLayout(new GridLayout(3,3));
		
		add(fill1);
		add(fill2);
		add(fill3);
		add(fill4);
		add(comp);
		add(fill5);
		add(fill6);
		add(fill7);
		add(fill8);
	}

	public void adjust(int width, int height){
		fill1.setPreferredSize(new Dimension(width, height));
		fill2.setPreferredSize(new Dimension(width, height));
		fill3.setPreferredSize(new Dimension(width, height));
		fill4.setPreferredSize(new Dimension(width, height));
		fill5.setPreferredSize(new Dimension(width, height));
		fill6.setPreferredSize(new Dimension(width, height));
		fill7.setPreferredSize(new Dimension(width, height));
		fill8.setPreferredSize(new Dimension(width, height));

	}

}