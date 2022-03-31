import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

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

	private HashMap<Integer, Filler> table;
	private Component comp;

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
		this.comp = comp;
		table = new HashMap<Integer, Filler>();

		table.put(1, fill1);
		table.put(2, fill2);
		table.put(3, fill3);
		table.put(4, fill4);
		table.put(5, fill5);
		table.put(6, fill6);
		table.put(7, fill7);
		table.put(8, fill8);

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

	public void adjust(int twidth, int theight){
		int width = twidth / 3;
		int height = theight / 3;
		fill1.setPreferredSize(new Dimension(width, height));
		fill2.setPreferredSize(new Dimension(width, height));
		fill3.setPreferredSize(new Dimension(width, height));
		fill4.setPreferredSize(new Dimension(width, height));
		comp.setPreferredSize(new Dimension(width, height));
		fill5.setPreferredSize(new Dimension(width, height));
		fill6.setPreferredSize(new Dimension(width, height));
		fill7.setPreferredSize(new Dimension(width, height));
		fill8.setPreferredSize(new Dimension(width, height));
	}

	public void adjust(int twidth, int theight, String dir, float percent){
		int width = twidth / 3;
		int height = theight / 3;
		if (dir.equals("UP")){
			//2
			System.out.println("called");
			fill1.setPreferredSize(new Dimension(width, height - (int)(height*percent)));
			fill2.setPreferredSize(new Dimension(width, height - (int)(height*percent)));
			fill3.setPreferredSize(new Dimension(width, height - (int)(height*percent)));
			fill4.setPreferredSize(new Dimension(width, height + (int)(height*percent)));
			comp.setPreferredSize(new Dimension(width, height + (int)(height*percent)));
			fill5.setPreferredSize(new Dimension(width, height + (int)(height*percent)));
			fill6.setPreferredSize(new Dimension(width, height));
			fill7.setPreferredSize(new Dimension(width, height));
			fill8.setPreferredSize(new Dimension(width, height));
		} else if (dir.equals("DOWN")){
			//7
			fill1.setPreferredSize(new Dimension(width, height));
			fill2.setPreferredSize(new Dimension(width, height));
			fill3.setPreferredSize(new Dimension(width, height));
			fill4.setPreferredSize(new Dimension(width, height + (int)(height*percent)));
			comp.setPreferredSize(new Dimension(width, height + (int)(height*percent)));
			fill5.setPreferredSize(new Dimension(width, height + (int)(height*percent)));
			fill6.setPreferredSize(new Dimension(width, height - (int)(height*percent)));
			fill7.setPreferredSize(new Dimension(width, height - (int)(height*percent)));
			fill8.setPreferredSize(new Dimension(width, height - (int)(height*percent)));
			//System.out.println("height: " + height + ", percent: " + percent + ", newhight: " + (int)(height*percent));
		} else if (dir.equals("RIGHT")){
			//5
			fill1.setPreferredSize(new Dimension(width, height));
			fill2.setPreferredSize(new Dimension(width + (int)(width*percent), height));
			fill3.setPreferredSize(new Dimension(width  - (int)(width*percent), height));
			fill4.setPreferredSize(new Dimension(width, height));
			comp.setPreferredSize(new Dimension(width + (int)(width*percent), height));
			fill5.setPreferredSize(new Dimension(width - (int)(width*percent), height));
			fill6.setPreferredSize(new Dimension(width, height));
			fill7.setPreferredSize(new Dimension(width + (int)(width*percent), height));
			fill8.setPreferredSize(new Dimension(width - (int)(width*percent), height));
		} else if (dir.equals("LEFT")){
			//4
			fill1.setPreferredSize(new Dimension(width - (int)(width*percent), height));
			fill2.setPreferredSize(new Dimension(width + (int)(width*percent), height));
			fill3.setPreferredSize(new Dimension(width, height));
			fill4.setPreferredSize(new Dimension(width - (int)(width*percent), height));
			comp.setPreferredSize(new Dimension(width + (int)(width*percent), height));
			fill5.setPreferredSize(new Dimension(width, height));
			fill6.setPreferredSize(new Dimension(width - (int)(width*percent), height));
			fill7.setPreferredSize(new Dimension(width + (int)(width*percent), height));
			fill8.setPreferredSize(new Dimension(width, height));
		}	else if (dir.equals("test")){
			System.out.println("testing size");
			fill1.setPreferredSize(new Dimension(width, 0));
			fill2.setPreferredSize(new Dimension(width, 0));
			fill3.setPreferredSize(new Dimension(width, 0));
			fill4.setPreferredSize(new Dimension(width, 2 *height));
			comp.setPreferredSize(new Dimension(width, 2 *height));
			fill5.setPreferredSize(new Dimension(width, 2 *height));
			fill6.setPreferredSize(new Dimension(width, height));
			fill7.setPreferredSize(new Dimension(width, height));
			fill8.setPreferredSize(new Dimension(width, height));
		}
	}

}