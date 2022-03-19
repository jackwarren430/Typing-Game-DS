import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.lang.Math;

import java.io.*;
import java.util.*;


public class BottomBarComponent extends JPanel {
	private static final long serialVersionUID = 0000;
	private int WPM;
	private int errors;
	private int time;
	private Boolean displayErrors;

	private TyperFrame frame;
	int width;
	int height;

	private JLabel WPMLabel;
	private JLabel timeLabel;
	private JLabel errorsLabel;


	public BottomBarComponent(TyperFrame frame){
		setLayout(new GridLayout(1, 3));
		this.frame = frame;
		width = frame.getFrameSize()[0];
		height = frame.getFrameSize()[1] - 25;

		WPM = 0;
		errors = 0;
		time = 0;
		displayErrors = false;

		display();
	}

	public void display(){
		width = frame.getFrameSize()[0];
		height = frame.getFrameSize()[1] - 25;

		timeLabel = new JLabel(time + "s", SwingConstants.CENTER);
		WPMLabel = new JLabel("WPM: " + WPM, SwingConstants.CENTER);
		errorsLabel = new JLabel("Errors: " + errors);

		add(timeLabel);
		add(WPMLabel);

		//pen.drawString(time + "s", width/4, 7*height/8);
		//pen.drawString("WPM: " + WPM, width/2, 7*height/8);
		//pen.drawString("WPM: " + WPM, 10, 10);
	}

	public void update(int WPM, int time){
		this.WPM = WPM;
		this.time = time;
		WPMLabel.setText("WPM: " + WPM);
		timeLabel.setText(time + "s");
	}

	public void setErrors(int errors){
		this.errors = errors;
	}

	public void displayErrors(){
		//addLayoutComponent("errors", errorsLabel);
	}

	public void hideErrors(){

	}

	public void setDisplayErrors(boolean b){
		displayErrors = b;
	}


}