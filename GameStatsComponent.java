import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.lang.Math;

import java.io.*;
import java.util.*;

public class GameStatsComponent extends JComponent{
	private static final long serialVersionUID = 0000;

	private TyperFrame frame;
	int width;
	int height;

	private Profile loadedProfile;
	private int numGameStats;
	private int whichGameStat;

	public GameStatsComponent(TyperFrame frame){
		this.frame = frame;
		width = frame.getFrameSize()[0];
		height = frame.getFrameSize()[1] - 25;

		loadedProfile = frame.getLoadedProfile();
		numGameStats = loadedProfile.getGamesPlayed().size();
		whichGameStat = 0;

		
	}

	public void paintComponent(Graphics g){
		numGameStats = loadedProfile.getGamesPlayed().size();
		Graphics2D pen = (Graphics2D) g;
		width = frame.getFrameSize()[0];
		height = frame.getFrameSize()[1] - 25;

		if (frame.getIsStatsPage() && numGameStats == 0){
			pen.drawString("No games played", width/2, height/2);
		} else if (frame.getIsStatsPage()){
			GameStat gameStats = loadedProfile.getGamesPlayed().get(whichGameStat);

			pen.drawString("Time: " + gameStats.getTime(), width/2, 5*height/16);
			pen.drawString("Errors: " + gameStats.getNumMissedWords(), width/2, 6*height/16);
			pen.drawString("Missed Words: " + gameStats.getMissedWords(), width/2, 7*height/16);
			pen.drawString("Words Per Minute (WPM): " + gameStats.getWPM(), width/2, 8*height/16);
			pen.drawString("Characters Per Second (CPS): " + gameStats.getCPS(), width/2, 9*height/16);

		} else {
			super.paintComponent(g);
		}

	}

	public int getNumGameStats(){
		return numGameStats;
	}

	public void setWhichGameStat(int i){
		whichGameStat = i;
	}

	public int getWhichGameStat(){
		return whichGameStat;
	}


	
}