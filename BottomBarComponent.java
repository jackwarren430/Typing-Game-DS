import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.lang.Math;

import java.io.*;
import java.util.*;


public class BottomBarComponent extends JPanel implements ActionListener {
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

	private JButton statsNextButt;
	private JButton statsPrevButt;
	private JLabel whichStatLabel;

	public BottomBarComponent(TyperFrame frame){

		setLayout(new GridLayout(1, 3));
		this.frame = frame;
		width = frame.getFrameSize()[0];
		height = frame.getFrameSize()[1] - 25;

		timeLabel = new JLabel(time + "s", SwingConstants.CENTER);
		WPMLabel = new JLabel("WPM: " + WPM, SwingConstants.CENTER);
		errorsLabel = new JLabel("Errors: " + errors);

		statsNextButt = new JButton(">");
		statsPrevButt = new JButton("<");
		whichStatLabel = new JLabel(frame.getGameStatsComp().getWhichGameStat() + 1 + "", SwingConstants.CENTER);

		statsNextButt.setFont(Styles.buttonFont);
		statsPrevButt.setFont(Styles.buttonFont);
		timeLabel.setFont(Styles.labelsFont);
		WPMLabel.setFont(Styles.labelsFont);
		whichStatLabel.setFont(Styles.buttonFont);
		statsNextButt.setBorderPainted(false);
		statsPrevButt.setBorderPainted(false);

		statsNextButt.addActionListener(this);
		statsPrevButt.addActionListener(this);
		statsNextButt.setFocusable(false);
		statsPrevButt.setFocusable(false);


		WPM = 0;
		errors = 0;
		time = 0;
		displayErrors = false;

		display();
	}

	public void display(){
		width = frame.getFrameSize()[0];
		height = frame.getFrameSize()[1] - 25;
		
		if(frame.getIsGameOptPage()){
			add(timeLabel);
		}

		if (frame.getIsHome()){
			add(timeLabel);
			add(WPMLabel);
			remove(statsNextButt);
			remove(whichStatLabel);
			remove(statsPrevButt);
		} else if (frame.getIsStatsPage()){
			add(statsPrevButt);
			add(whichStatLabel);
			add(statsNextButt);
			remove(timeLabel);
			remove(WPMLabel);
		} else {
			remove(timeLabel);
			remove(WPMLabel);
			remove(statsNextButt);
			remove(whichStatLabel);
			remove(statsPrevButt);
		}
	}

	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand().equals(">")){
			int order = frame.getGameStatsComp().getWhichGameStat();
			int maxSize = frame.getGameStatsComp().getNumGameStats() - 1;
			if (order < maxSize){
				frame.getGameStatsComp().setWhichGameStat(order + 1);
				whichStatLabel.setText(order + 2 + "");
			}
			display();
		} else if (e.getActionCommand().equals("<")){
			int order = frame.getGameStatsComp().getWhichGameStat();
			if (order > 0){
				frame.getGameStatsComp().setWhichGameStat(order - 1);
				whichStatLabel.setText(order + "");
			}
			display();
		}
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
		
	}

	public void hideErrors(){

	}

	public void setDisplayErrors(boolean b){
		displayErrors = b;
	}

	public void updateColors(Color backgroundColor, Color foregroundColor){
		setBackground(backgroundColor);
		setForeground(foregroundColor);
		timeLabel.setForeground(foregroundColor);
		WPMLabel.setForeground(foregroundColor);
		whichStatLabel.setForeground(foregroundColor);
		statsNextButt.setBackground(backgroundColor);
		statsNextButt.setForeground(foregroundColor);
		statsPrevButt.setBackground(backgroundColor);
		statsPrevButt.setForeground(foregroundColor);
	}

}