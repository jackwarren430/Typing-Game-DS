import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.lang.Math;

import java.io.*;
import java.util.*;

public class GameStatsComponent extends JPanel implements ActionListener{
	private static final long serialVersionUID = 0000;

	private MainStatsComp mainComp;
	private Filler fillLeft;
	private Filler fillRight;
	private BorderLayout layout;
	private GridLayout buttonsLayout;
	private JPanel buttons;
	private TyperFrame frame;
	int width;
	int height;

	private Profile loadedProfile;
	private int numGameStats;
	private int whichGameStat;

	private JButton saveProfileButt;

	public GameStatsComponent(TyperFrame frame){
		this.frame = frame;
		width = frame.getFrameSize()[0];
		height = 6*frame.getFrameSize()[1]/8;

		loadedProfile = frame.getLoadedProfile();
		numGameStats = loadedProfile.getGamesPlayed().size();
		whichGameStat = 0;

		layout = new BorderLayout();
		setLayout(layout);
		buttonsLayout = new GridLayout(1,3);
		buttons = new JPanel(buttonsLayout);
		mainComp = new MainStatsComp();
		fillLeft = new Filler();
		fillRight = new Filler();

		saveProfileButt = new JButton("Save Profile");
		
		saveProfileButt.addActionListener(this);
		saveProfileButt.setFocusable(false);

		
		buttons.add(fillLeft);
		buttons.add(saveProfileButt);
		buttons.add(fillRight);
		add(buttons, BorderLayout.NORTH);
		add(mainComp, BorderLayout.CENTER);
		
		setPrefSize();
	}



	public void actionPerformed(ActionEvent e){
		
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

	class MainStatsComp extends JComponent {
		private static final long serialVersionUID = 0000;

		public MainStatsComp(){}

		public void paintComponent(Graphics g){
			width = frame.getFrameSize()[0];
			height = 6*frame.getFrameSize()[1]/8;
			Graphics2D pen = (Graphics2D) g;
			numGameStats = loadedProfile.getGamesPlayed().size();

			setPrefSize();

			if (numGameStats == 0){
				pen.drawString("No games played", width/2, height/2);
			} else {
				GameStat gameStats = loadedProfile.getGamesPlayed().get(whichGameStat);
				pen.drawString("Time: " + gameStats.getTime() + "s", width/2, 5*height/16);
				pen.drawString("Errors: " + gameStats.getNumMissedWords(), width/2, 6*height/16);
				pen.drawString("Missed Words: " + gameStats.getMissedWords(), width/2, 7*height/16);
				pen.drawString("Words Per Minute (WPM): " + gameStats.getWPM(), width/2, 8*height/16);
				pen.drawString("Characters Per Second (CPS): " + gameStats.getCPS(), width/2, 9*height/16);
			}
		}
	}

	class Filler extends JComponent {
		private static final long serialVersionUID = 0000;
		public Filler(){}
		public void paintComponent(Graphics g){}
	}

	private void setPrefSize(){
		buttons.setPreferredSize(new Dimension(width/2, height/8));
		mainComp.setPreferredSize(new Dimension(width/2, 7*height/8));
	}
	
}






