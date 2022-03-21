import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.lang.Math;

import java.io.*;
import java.util.*;

public class TopBarComponent extends JPanel implements ActionListener{
	private static final long serialVersionUID = 0000;

	private TyperFrame frame;
	int width;
	int height;

	private JButton homeButt;
	private JButton statsButt;
	private JButton settingsButt;
	private JButton gameOptButt;
	private JButton infoButt;



	public TopBarComponent(TyperFrame frame){
		setLayout(new GridLayout(1, 5));
		this.frame = frame;
		width = frame.getFrameSize()[0];
		height = frame.getFrameSize()[1] - 25;

		initButtons();
	}

	private void initButtons(){
		statsButt = new JButton("Game Stats");
		homeButt = new JButton("Home");
		settingsButt = new JButton("Settings");
		gameOptButt = new JButton("Game Options");
		infoButt = new JButton("Info");

		statsButt.addActionListener(this);
		homeButt.addActionListener(this);
		settingsButt.addActionListener(this);
		gameOptButt.addActionListener(this);
		infoButt.addActionListener(this);

		homeButt.setFocusable(false);
		statsButt.setFocusable(false);
		settingsButt.setFocusable(false);
		gameOptButt.setFocusable(false);
		infoButt.setFocusable(false);

		add(homeButt);
		add(statsButt);
		add(settingsButt);
		add(gameOptButt);
		add(infoButt);
	}

	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand().equals("Game Stats")){
			frame.goStatsPage();
		} else if (e.getActionCommand().equals("Home")){
			frame.goHomePage();
		} else if (e.getActionCommand().equals("Settings")){
			frame.goSettingsPage();
		} else if (e.getActionCommand().equals("Game Options")){
			frame.goGameOptPage();
		} else if (e.getActionCommand().equals("Info")){
			frame.goInfoPage();
		}
	
	}
}