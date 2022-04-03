import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.lang.Math;
import java.util.*;

public class SettingsComponent extends JPanel implements ActionListener {
	private static final long serialVersionUID = 0000;

	private TyperFrame frame;
	private int width;
	private int height;

	private GridBagConstraints c;

	private ArrayList<String> colorSchemes;
	private HashMap<String, Color[]> colorMap;
	private JLabel colorLabel;
	private JLabel whichColorLabel;
	private JButton nextColorButt;
	private JButton prevColorButt;

	private JLabel settingsLabel;
	private JButton applyChangesButt;



	public SettingsComponent(TyperFrame frame){
		this.frame = frame;
		width = frame.getFrameSize()[0];
		height = 7*frame.getFrameSize()[1]/8;

		setLayout(new GridBagLayout());
		c = new GridBagConstraints();

		settingsLabel = new JLabel("SETTINGS", JLabel.CENTER);

		colorSchemes = Styles.colorSchemes;
		colorMap = Styles.getColorMap();
		colorLabel = new JLabel("Color Scheme: ", JLabel.CENTER);
		whichColorLabel = new JLabel(colorSchemes.get(0));
		nextColorButt = new JButton(">");
		prevColorButt = new JButton("<");

		applyChangesButt = new JButton("Apply Changes");

		prepareGUI();
	}

	public void prepareGUI(){
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		add(settingsLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		add(colorLabel, c);

		c.gridx = 2;
		c.gridy = 1;
		add(whichColorLabel, c);

		prevColorButt.addActionListener(this);
		prevColorButt.setFocusable(false);
		c.gridx = 1;
		c.gridy = 3;
		add(prevColorButt, c);

		nextColorButt.addActionListener(this);
		nextColorButt.setFocusable(false);
		c.gridx = 2;
		c.gridy = 3;
		add(nextColorButt, c);

		applyChangesButt.addActionListener(this);
		applyChangesButt.setFocusable(false);
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 2;
		add(applyChangesButt, c);

	}

	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand().equals(">")){
			if (colorSchemes.indexOf(whichColorLabel.getText()) == colorSchemes.size() - 1){
				whichColorLabel.setText(colorSchemes.get(0));
			} else {
				whichColorLabel.setText(colorSchemes.get(colorSchemes.indexOf(whichColorLabel.getText()) + 1));
			}
		} else if (e.getActionCommand().equals("<")){
			if (colorSchemes.indexOf(whichColorLabel.getText()) == 0){
				whichColorLabel.setText(colorSchemes.get(colorSchemes.size() - 1));
			} else {
				whichColorLabel.setText(colorSchemes.get(colorSchemes.indexOf(whichColorLabel.getText()) - 1));
			}
		} else if (e.getActionCommand().equals("Apply Changes")){
			frame.updateColors(colorMap.get(whichColorLabel.getText())[0], colorMap.get(whichColorLabel.getText())[1]);
		}
	}


}