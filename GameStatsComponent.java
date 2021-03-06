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
				frame.goStatsPage();
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
		private GridBagConstraints c;
		private JButton saveButt;
		private TextField enterName;
		private JLabel error;
		private JButton overrideButt;
		private Boolean overrideStart;

		private Filler fill1;
		private Filler fill2;

		public SaveProfileComp(GameStatsComponent parent){
			setLayout(new GridBagLayout());
			c = new GridBagConstraints();
			this.parent = parent;

			saveButt = new JButton("Save");
			enterName = new TextField("Player");
			error = new JLabel("");
			overrideButt = new JButton("override");
			fill1 = new Filler();
			fill2 = new Filler();

			saveButt.setFont(Styles.buttonFont2);
			saveButt.setBorderPainted(true);
			saveButt.addActionListener(parent);
			saveButt.setFocusable(false);
			overrideButt.addActionListener(parent);
			overrideButt.setFocusable(false);
			overrideButt.setVisible(false);

			prepareGUI();

		}

		public void prepareGUI(){
			c.insets = new Insets(0,0, 20 ,0);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 0;
			add(error, c);

			c.insets = new Insets(0,0, height/8 ,0);
			c.gridx = 0;
			c.gridy = 1;
			add(overrideButt, c);

			c.gridx = 0;
			c.gridy = 2;
			add(enterName, c);

			c.gridx = 0;
			c.gridy = 3;
			add(saveButt, c);
		}

		public void adjust(){
			error.setPreferredSize(new Dimension(width/5,height/9));
			enterName.setPreferredSize(new Dimension(width/3,height/7));
			saveButt.setPreferredSize(new Dimension(width/3,height/9));
		}

		public String getName(){
			return enterName.getText();
		}

		public void noNameError(){
			error.setText("Enter a name before saving");
			error.setVisible(true);
		}

		public void noGamesPlayedError(){
			error.setText("Play some games before saving!");
			error.setVisible(true);
		}

		public void success(){
			error.setText("Profile saved!");
			error.setVisible(true);
		}

		public void override(){
			error.setText("A profile already exists with that name. Override?");
			overrideButt.setVisible(true);
			error.setVisible(true);
		}

		public void removeError(){
			error.setText("");
			error.setVisible(false);
			overrideButt.setVisible(false);
		}

		public void updateColors(Color backgroundColor, Color foregroundColor){
			setBackground(backgroundColor);
			setForeground(foregroundColor);
			error.setBackground(backgroundColor);
			error.setForeground(foregroundColor);
			enterName.setForeground(Color.black);
			overrideButt.setBackground(foregroundColor);
			overrideButt.setForeground(backgroundColor);
			saveButt.setBackground(foregroundColor);
			saveButt.setForeground(backgroundColor);
		}
	}

	class LoadProfileComp extends JPanel {
		private static final long serialVersionUID = 0000;

		private GameStatsComponent parent;
		private JButton loadButt;
		private JList<String> fileList;
		private JScrollPane filePan;
		private Filler fill1;
		private GridBagConstraints c;

		public LoadProfileComp(GameStatsComponent parent){
			setLayout(new GridBagLayout());
			c = new GridBagConstraints();
			this.parent = parent;

			loadButt = new JButton("Load");
			updateList();
			filePan = new JScrollPane(fileList);
			fill1 = new Filler();

			loadButt.setFont(Styles.buttonFont2);
			loadButt.setBorderPainted(true);
			loadButt.addActionListener(parent);
			loadButt.setFocusable(false);

			c.insets = new Insets(0, 0, height/8, 0);
			c.gridx = 0;
			c.gridy = 0;
			add(filePan, c);

			c.insets = new Insets(0, 0, 0, 0);
			c.gridx = 0;
			c.gridy = 2;
			add(loadButt, c);

			adjust();
			
		}

		public void adjust(){
			filePan.setPreferredSize(new Dimension(width/3, 3*height/8));
			loadButt.setPreferredSize(new Dimension(width/4, 1*height/8));
		}

		public String getName(){
			return fileList.getSelectedValue();
		}

		public void updateList(){
			String[] list = new File("SavedProfiles").list();
			ArrayList<String> goodList = new ArrayList<String>();
			for (int i = 0; i < list.length; i++){
				if (list[i] != null && !list[i].equals(".DS_S") && !list[i].equals(".DS_Store")){
					goodList.add(list[i].substring(0, list[i].length() - 4));
				}
			}
			String[] toAdd = new String[goodList.size()];
			for (int i = 0; i < toAdd.length; i++){
				toAdd[i] = goodList.get(i);
			}
			fileList = new JList<String>(toAdd);
			// System.out.println("list: " + list.length + ", good: " + goodList.length);
			// for (String s : list){
			// 	System.out.println(s);
			// }
			// for (String s : goodList){
			// 	System.out.println(s);
			// }
			
		}

		public void updateColors(Color backgroundColor, Color foregroundColor){
			setBackground(backgroundColor);
			setForeground(foregroundColor);
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
		buttons.setBackground(backgroundColor);
		buttons.setForeground(foregroundColor);
		viewStatsButt.setBackground(foregroundColor);
		viewStatsButt.setForeground(backgroundColor);
		saveProfileButt.setBackground(foregroundColor);
		saveProfileButt.setForeground(backgroundColor);
		loadProfileButt.setBackground(foregroundColor);
		loadProfileButt.setForeground(backgroundColor);
	}

	public void setPrefSize(){
		width = frame.getFrameSize()[0];
		height = 6*frame.getFrameSize()[1]/8;
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

	public void setLoadedProfile(Profile p){
		loadedProfile = p;
	}

	
}






