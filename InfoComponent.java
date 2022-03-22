import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.lang.Math;

import java.io.*;
import java.util.*;

public class InfoComponent extends JComponent {
	private static final long serialVersionUID = 0000;

	private TyperFrame frame;
	int width;
	int height;

	public InfoComponent(TyperFrame frame){
		this.frame = frame;
		width = frame.getFrameSize()[0];
		height = 6*frame.getFrameSize()[1]/8;
	}

	public void paintComponent(Graphics g){
		Graphics2D pen = (Graphics2D) g;
		width = frame.getFrameSize()[0];
		height = 6*frame.getFrameSize()[1]/8;

		paintInfo(pen);
	}

	private void paintInfo(Graphics2D pen){
		pen.drawString("TYPING WIZARD 2022", width/4, height/4);
		String info = "Welcome to my game, Typing Wizard.";
		info = "I made this game for my Data Structures Final project.";
		pen.drawString(info, width/4, 5*height/16);
		info = "The inspiration came from when I switched how I type to try to raise my WPM";
		pen.drawString(info, width/4, 6*height/16);
		info = "I started playing alot of TyperRacer and TypingMonkey, so I figured I would code my own typing game to practice with.\n";
		pen.drawString(info, width/4, 7*height/16);
		info = "The main data related aspect to this game is the ability to save and load custom Profiles,";
		pen.drawString(info, width/4, 8*height/16);
		info = "which contains all your games played. This allows you to view your stats from all your games.";
		pen.drawString(info, width/4, 9*height/16);
		pen.drawString("Developed by Jack Warren", width/4, 11*height/16);
		pen.drawString("Email: jackwarren430@gmail.com", width/4, 12*height/16);
	}
}

