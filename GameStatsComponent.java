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

	private SaveProfileComp saveComp;
	private LoadProfileComp loadComp;

	private Profile loadedProfile;
	private int numGameStats;
	private int whichGameStat;

	private JButton viewStatsButt;
	private JButton saveProfileButt;
	private JButton loadProfileButt;

	public GameStatsComponent(TyperFrame frame){
		this.frame = frame;
		width = frame.getFrameSize()[0];
		height = 6*frame.getFrameSize()[1]/8;

		loadedProfile = frame.getLoadedProfile();
		numGameStats = loadedProfile.getGamesPlayed().size();
		whichGameStat = 0;

		layout = new BorderLayout();
		setLayout(layout);
		buttonsLayout = new GridLayout(1,5);
		buttons = new JPanel(buttonsLayout);
		fillLeft = new Filler();
		fillRight = new Filler();

		saveComp = new SaveProfileComp(this);
		loadComp = new LoadProfileComp(this);
		mainComp = new MainStatsComp();

		viewStatsButt = new JButton("View Stats");
		saveProfileButt = new JButton("Save Profile");
		loadProfileButt = new JButton("Load Profile");
		
		saveProfileButt.addActionListener(this);
		saveProfileButt.setFocusable(false);
		loadProfileButt.addActionListener(this);
		loadProfileButt.setFocusable(false);
		viewStatsButt.addActionListener(this);
		viewStatsButt.setFocusable(false);

		
		buttons.add(fillLeft);
		buttons.add(viewStatsButt);
		buttons.add(saveProfileButt);
		buttons.add(loadProfileButt);
		buttons.add(fillRight);
		add(buttons, BorderLayout.NORTH);
		add(mainComp, BorderLayout.CENTER);
		
		setPrefSize();
	}



	public void actionPerformed(ActionEvent e){
		
		if (e.getActionCommand().equals("View Stats")){
			Component center = layout.getLayoutComponent(BorderLayout.CENTER);
			layout.removeLayoutComponent(center);
			center.setVisible(false);
			add(mainComp, BorderLayout.CENTER);
			mainComp.setVisible(true);
		} else if (e.getActionCommand().equals("Save Profile")){
			Component center = layout.getLayoutComponent(BorderLayout.CENTER);
			layout.removeLayoutComponent(center);
			center.setVisible(false);
			add(saveComp, BorderLayout.CENTER);
			saveComp.setVisible(true);
		} else if (e.getActionCommand().equals("Load Profile")){
			Component center = layout.getLayoutComponent(BorderLayout.CENTER);
			layout.removeLayoutComponent(center);
			center.setVisible(false);
			add(loadComp, BorderLayout.CENTER);
			loadComp.setVisible(true);
			setVisible(true);
			repaint();
		} else if (e.getActionCommand().equals("Save")){
			System.out.println("saving");
		} else if (e.getActionCommand().equals("Load")){
			System.out.println("loading");
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

	class SaveProfileComp extends JPanel {
		private static final long serialVersionUID = 0000;

		private GameStatsComponent parent;

		private JButton saveButt;
		private TextField enterName;

		private FillerPanel savePan;
		private FillerPanel enterPan;

		public SaveProfileComp(GameStatsComponent parent){
			setLayout(new GridLayout(2, 1));
			this.parent = parent;

			saveButt = new JButton("Save");
			enterName = new TextField("enter name");

			saveButt.addActionListener(parent);
			saveButt.setFocusable(false);

			savePan = new FillerPanel(saveButt);
			enterPan = new FillerPanel(enterName);

			add(enterPan);
			add(savePan);
		}

		public void adjust(){
			saveButt.setPreferredSize(new Dimension(width/3, height/9));
			enterName.setPreferredSize(new Dimension(width/3, height/9));
			savePan.adjust(width/3, height/9);
			enterPan.adjust(width/3, height/9);
		}
	}

	class LoadProfileComp extends JPanel {
		private static final long serialVersionUID = 0000;

		private GameStatsComponent parent;

		private JButton loadButt;
		private TextField enterName;

		private FillerPanel loadPan;
		private FillerPanel enterPan;

		public LoadProfileComp(GameStatsComponent parent){
			setLayout(new GridLayout(2, 1));
			this.parent = parent;

			loadButt = new JButton("Load");
			enterName = new TextField("enter name");

			loadButt.addActionListener(parent);
			loadButt.setFocusable(false);

			loadPan = new FillerPanel(loadButt);
			enterPan = new FillerPanel(enterName);

			add(enterPan);
			add(loadPan);
		}

		public void adjust(){
			loadButt.setPreferredSize(new Dimension(width/3, height/9));
			enterName.setPreferredSize(new Dimension(width/3, height/9));
			loadPan.adjust(width/3, height/9);
			enterPan.adjust(width/3, height/9);
		}
	}

	class MainStatsComp extends JComponent {
		private static final long serialVersionUID = 0000;

		public MainStatsComp(){}

		public void paintComponent(Graphics g){
			width = frame.getFrameSize()[0];
			height = 6*frame.getFrameSize()[1]/8;
			Graphics2D pen = (Graphics2D) g;
			numGameStats = loadedProfile.getGamesPlayed().size();

			if (numGameStats == 0){
				pen.drawString("Profile Name: " + loadedProfile.getName(), width/4, height/4);
				pen.drawString("No games played", width/2, height/2);
			} else {
				GameStat gameStats = loadedProfile.getGamesPlayed().get(whichGameStat);
				pen.drawString("Profile Name: " + loadedProfile.getName(), width/4, height/4);
				pen.drawString("Time: " + gameStats.getTime() + "s", width/2, 5*height/16);
				pen.drawString("Game Size: " + gameStats.getGameSize() + " words", width/2, 6*height/16);
				pen.drawString("Errors: " + gameStats.getNumMissedWords(), width/2, 7*height/16);
				pen.drawString("Missed Words: " + gameStats.getMissedWords(), width/2, 8*height/16);
				pen.drawString("Words Per Minute (WPM): " + gameStats.getWPM(), width/2, 9*height/16);
				pen.drawString("Characters Per Second (CPS): " + gameStats.getCPS(), width/2, 10*height/16);
			}
		}
	}

	public void setPrefSize(){
		buttons.setPreferredSize(new Dimension(width/2, height/8));
		mainComp.setPreferredSize(new Dimension(width/2, 7*height/8));
		saveComp.adjust();
		loadComp.adjust();
	}
	
}






