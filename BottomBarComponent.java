import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.lang.Math;

import java.io.*;
import java.util.*;


public class BottomBarComponent extends JComponent {
	private static final long serialVersionUID = 0000;
	private double WPM;
	private int errors;
	private int time;
	private Boolean displayErrors;

	private TyperFrame frame;
	int width;
	int height;


	public BottomBarComponent(TyperFrame frame){
		this.frame = frame;
		width = frame.getFrameSize()[0];
		height = frame.getFrameSize()[1] - 25;

		WPM = 0;
		errors = 0;
		time = 0;
		displayErrors = false;
	}

	public void paintComponent(Graphics g){
		setBorder(BorderFactory.createLineBorder(Color.black));
		Graphics2D pen = (Graphics2D) g;
		width = frame.getFrameSize()[0];
		height = frame.getFrameSize()[1] - 25;

		pen.drawRect(width/8, 13*height/16, 3*width/4, height/8);

		pen.drawString(time + "s", width/4, 7*height/8);
		pen.drawString("WPM: " + WPM, width/2, 7*height/8);
		//pen.drawString("WPM: " + WPM, 10, 10);

		if (displayErrors){
			pen.drawString("Errors: " + errors, width/4, 7*height/8);
		}
	}

	public void update(double WPM, int time){
		this.WPM = WPM;
		this.time = time;
	}

	public void setErrors(int errors){
		this.errors = errors;
	}

	public void setDisplayErrors(boolean b){
		displayErrors = b;
	}


}