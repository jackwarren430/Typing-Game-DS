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
	private int width;
	private int height;

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
		saveComp.removeError();
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
			loadComp = new LoadProfileComp(this);
			add(loadComp, BorderLayout.CENTER);
			updateColors(getBackground(), getForeground());
			loadComp.setVisible(true);
			setVisible(true);
			repaint();
		} else if (e.getActionCommand().equals("Save")){
			File f = new File("SavedProfiles");
			String[] fileList = f.list();
			Boolean doSave = true;
			for (String n : fileList){
				if (n.equals(saveComp.getName() + ".txt")){
					doSave = false;
				}
			}
			if (saveComp.getName().equals("") || saveComp.getName().equals("Player")){
				saveComp.noNameError();
			} else if (frame.getLoadedProfile().getGamesPlayed().size() == 0){
				saveComp.noGamesPlayedError();
			} else if (doSave){
				frame.saveProfile(saveComp.getName());
				saveComp.success();
			} else {
				saveComp.override();
			}
			
		} else if (e.getActionCommand().equals("Load")){
			if (loadComp.getName() != null){
				frame.loadProfile(loadComp.getName() + ".txt");
				loadedProfile = frame.getLoadedProfile();
		
				Component center = layout.getLayoutComponent(BorderLayout.CENTER);
				layout.removeLayoutComponent(center);
				center.setVisible(false);
				add(mainComp, BorderLayout.CENTER);
				mainComp.setVisible(true);

			} else {
				System.out.println("Nothing selected");
			}
		} else if (e.getActionCommand().equals("override")){
			File f = new File("SavedProfiles" + File.separator + saveComp.getName() + ".txt");
			f.delete();
			frame.saveProfile(saveComp.getName());
			
		}
		revalidate();
	}


	class SaveProfileComp extends JPanel {
		private static final long serialVersionUID = 0000;

		private GameStatsComponent parent;

		private JButton saveButt;
		private TextField enterName;
		private JLabel error;
		private JButton overrideButt;
		private JPanel combPan;

		private FillerPanel savePan;
		private FillerPanel enterPan;
		private FillerPanel errorPan;

		private Boolean overrideStart;

		public SaveProfileComp(GameStatsComponent parent){
			setLayout(new FlowLayout());
			this.parent = parent;

			saveButt = new JButton("Save");
			enterName = new TextField("Player");
			error = new JLabel("");
			overrideButt = new JButton("override");

			saveButt.setBorderPainted(true);
			saveButt.addActionListener(parent);
			saveButt.setFocusable(false);
			overrideButt.addActionListener(parent);
			overrideButt.setFocusable(false);
			overrideButt.setVisible(false);

			combPan = new JPanel(new GridLayout(2,1));
			combPan.add(error);
			combPan.add(overrideButt);

			savePan = new FillerPanel(saveButt);
			enterPan = new FillerPanel(enterName);
			errorPan = new FillerPanel(combPan);

			add(errorPan);
			add(enterPan);
			add(savePan);
		}

		public void adjust(){
			savePan.adjust(width, height/3, "UP", 1f);
			enterPan.adjust(width, height/3);
		}

		public String getName(){
			return enterName.getText();
		}

		public void noNameError(){
			error.setText("Enter a name before saving");
			errorPan.setVisible(true);
		}

		public void noGamesPlayedError(){
			error.setText("Play some games before saving!");
			errorPan.setVisible(true);
		}

		public void success(){
			error.setText("Profile saved!");
			errorPan.setVisible(true);
		}

		public void override(){
			error.setText("A profile already exists with that name. Override?");
			overrideButt.setVisible(true);
			errorPan.setVisible(true);
		}

		public void removeError(){
			error.setText("");
			errorPan.setVisible(false);
			overrideButt.setVisible(false);
		}

		public void updateColors(Color backgroundColor, Color foregroundColor){
			setBackground(backgroundColor);
			setForeground(foregroundColor);
			errorPan.setBackground(backgroundColor);
			errorPan.setForeground(foregroundColor);
			enterPan.setBackground(backgroundColor);
			//enterPan.setForeground(foregroundColor);
			savePan.setBackground(backgroundColor);
			savePan.setForeground(foregroundColor);
			saveButt.setBackground(foregroundColor);
			saveButt.setForeground(backgroundColor);
			combPan.setBackground(backgroundColor);
			combPan.setForeground(foregroundColor);
			error.setForeground(foregroundColor);
		}
	}

	class LoadProfileComp extends JPanel {
		private static final long serialVersionUID = 0000;

		private GameStatsComponent parent;
		private GridLayout glay;

		private JButton loadButt;
		private JList<String> fileList;
		private JScrollPane filePan;

		private FillerPanel loadPan;
		private FillerPanel pickPan;

		public LoadProfileComp(GameStatsComponent parent){
			glay = new GridLayout(2,1);
			setLayout(glay);
			this.parent = parent;

			loadButt = new JButton("Load");
			updateList();
			filePan = new JScrollPane(fileList);

			loadButt.setFont(Styles.buttonFont2);
			loadButt.setBorderPainted(true);
			loadButt.addActionListener(parent);
			loadButt.setFocusable(false);

			loadPan = new FillerPanel(loadButt);
			pickPan = new FillerPanel(filePan);
			
			add(pickPan);
			add(loadPan);
		}

		public void adjust(){
			loadPan.adjust(width, height/2);
			pickPan.adjust(width, height/2, "DOWN", 0.7f);
		}

		public String getName(){
			return fileList.getSelectedValue();
		}

		public void updateList(){
			String[] list = new File("SavedProfiles").list();
			String[] goodList = new String[list.length-1];
			for (int i = 0; i < list.length - 1; i++){
				goodList[i] = list[i+1].substring(0, list[i+1].length()-4);
			}
			fileList = new JList<String>(goodList);
		}

		public void updateColors(Color backgroundColor, Color foregroundColor){
			setBackground(backgroundColor);
			setForeground(foregroundColor);
			pickPan.setBackground(backgroundColor);
			pickPan.setForeground(foregroundColor);
			loadPan.setBackground(backgroundColor);
			loadPan.setForeground(foregroundColor);
			loadButt.setBackground(foregroundColor);
			loadButt.setForeground(backgroundColor);
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
			pen.setFont(Styles.logiFont);
			//updateColors(getBackground(), getForeground());
			if (numGameStats == 0){
				pen.drawString("Profile Name: " + loadedProfile.getName(), width/4, height/4);
				pen.drawString("No games played", width/2, height/2);
			} else {
				GameStat gameStats = loadedProfile.getGamesPlayed().get(whichGameStat);
				pen.drawString("Profile Name: " + loadedProfile.getName(), 3*width/16, 2*height/16);
				pen.drawString("Frequently Missed Words: " + loadedProfile.getFreqMissedWords(), 3*width/16, 3*height/16);
				pen.drawString("Average WPM: " + loadedProfile.getAvgWPM(), 3*width/16, 4*height/16);
				pen.drawString("Time: " + gameStats.getTime() + "s", 7*width/16, 5*height/16);
				pen.drawString("Game Size: " + gameStats.getGameSize() + " words", 7*width/16, 6*height/16);
				pen.drawString("Errors: " + gameStats.getNumMissedWords(), 7*width/16, 7*height/16);
				pen.drawString("Missed Words: " + gameStats.getMissedWords(), 7*width/16, 8*height/16);
				pen.drawString("Words Per Minute (WPM): " + gameStats.getWPM(), 7*width/16, 9*height/16);
				pen.drawString("Characters Per Second (CPS): " + gameStats.getCPS(), 7*width/16, 10*height/16);
			}
		}

		public void updateColors(Color backgroundColor, Color foregroundColor){
			setBackground(backgroundColor);
			setForeground(foregroundColor);
			repaint();
		}
	}

	public void updateColors(Color backgroundColor, Color foregroundColor){
		setBackground(backgroundColor);
		setForeground(foregroundColor);
		mainComp.updateColors(backgroundColor, foregroundColor);
		loadComp.updateColors(backgroundColor, foregroundColor);
		saveComp.updateColors(backgroundColor, foregroundColor);

	}

	public void setPrefSize(){
		buttons.setPreferredSize(new Dimension(width/2, height/8));
		mainComp.setPreferredSize(new Dimension(width/2, 7*height/8));
		saveComp.adjust();
		loadComp.adjust();
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






