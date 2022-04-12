import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.lang.Math;
import java.util.*;
import java.util.stream.Collectors;

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

	private ArrayList<String> fonts;
	private HashMap<String, Font> fontMap;
	private JLabel fontLabel;
	private JLabel whichFontLabel;
	private JButton nextFontButt;
	private JButton prevFontButt;

	private JLabel settingsLabel;
	private JButton applyChangesButt;



	public SettingsComponent(TyperFrame frame){
		this.frame = frame;
		width = frame.getFrameSize()[0];
		height = 7*frame.getFrameSize()[1]/8;

		setLayout(new GridBagLayout());
		c = new GridBagConstraints();

		settingsLabel = new JLabel("SETTINGS", JLabel.CENTER);

		colorMap = Styles.getColorMap();
		colorSchemes = new ArrayList<String>(colorMap.keySet());
		colorLabel = new JLabel("Color Scheme: ", JLabel.CENTER);
		whichColorLabel = new JLabel(colorSchemes.get(0));
		nextColorButt = new JButton(">");
		prevColorButt = new JButton("<");

		fontMap = Styles.getFontMap();
		fonts = new ArrayList<String>(fontMap.keySet());
		fontLabel = new JLabel("Font: ", JLabel.CENTER);
		whichFontLabel = new JLabel(fonts.get(0));
		nextFontButt = new JButton(">");
		prevFontButt = new JButton("<");

		applyChangesButt = new JButton("Apply Changes");

		prepareGUI();
	}

	public void prepareGUI(){
		
		//c.fill = GridBagConstraints.HORIZONTAL;
		int top = height / 40;
		int bottom = height / 40;
		int left = width / 10;
		int right = width / 10;
		Insets i = new Insets(top, left, bottom, right);
		c.insets = i;

		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		add(settingsLabel, c);

		//adding colors
		bottom = height / 70;
		left = width / 20;
		right = width / 20;
		c.insets = new Insets(top, left, bottom, right);

		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		add(colorLabel, c);

		c.gridx = 2;
		c.gridy = 1;
		add(whichColorLabel, c);

		left = width / 10;
		right = width / 10;
		bottom = height / 40;
		c.insets = new Insets(top, left, bottom, right);

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

		//adding fonts
		bottom = height / 70;
		left = width / 20;
		right = width / 20;
		c.insets = new Insets(top, left, bottom, right);

		c.gridx = 1;
		c.gridy = 4;
		add(fontLabel, c);

		c.gridx = 2;
		c.gridy = 4;
		add(whichFontLabel, c);

		left = 0;
		right = 0;
		bottom = height / 40;
		c.insets = new Insets(top, left, bottom, right);

		prevFontButt.addActionListener(this);
		prevFontButt.setFocusable(false);
		c.gridx = 1;
		c.gridy = 5;
		add(prevFontButt, c);

		nextFontButt.addActionListener(this);
		nextFontButt.setFocusable(false);
		c.gridx = 2;
		c.gridy = 5;
		add(nextFontButt, c);

		applyChangesButt.addActionListener(this);
		applyChangesButt.setFocusable(false);
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 2;
		add(applyChangesButt, c);

	}

	public void setPrefSize(){
		width = frame.getFrameSize()[0];
		height = 7*frame.getFrameSize()[1]/8;

		settingsLabel.setFont(Styles.buttonFont2);
		nextColorButt.setPreferredSize(new Dimension(width/14, height/16));
		prevColorButt.setPreferredSize(new Dimension(width/14, height/16));
		nextFontButt.setPreferredSize(new Dimension(width/14, height/16));
		prevFontButt.setPreferredSize(new Dimension(width/14, height/16));

		applyChangesButt.setPreferredSize(new Dimension(width/4, height/14));

		prepareGUI();
	}

	public void actionPerformed(ActionEvent e){
		if (e.getSource() == nextColorButt){
			if (colorSchemes.indexOf(whichColorLabel.getText()) == colorSchemes.size() - 1){
				whichColorLabel.setText(colorSchemes.get(0));
			} else {
				whichColorLabel.setText(colorSchemes.get(colorSchemes.indexOf(whichColorLabel.getText()) + 1));
			}
		} else if (e.getSource() == prevColorButt){
			if (colorSchemes.indexOf(whichColorLabel.getText()) == 0){
				whichColorLabel.setText(colorSchemes.get(colorSchemes.size() - 1));
			} else {
				whichColorLabel.setText(colorSchemes.get(colorSchemes.indexOf(whichColorLabel.getText()) - 1));
			}
		} else if (e.getSource() == applyChangesButt){
			//apply changes
			frame.updateColors(colorMap.get(whichColorLabel.getText())[0], colorMap.get(whichColorLabel.getText())[1]);
			frame.updateFont(fontMap.get(whichFontLabel.getText()));
		} else if (e.getSource() == nextFontButt){
			if (fonts.indexOf(whichFontLabel.getText()) == fonts.size() - 1){
				whichFontLabel.setText(fonts.get(0));
			} else {
				whichFontLabel.setText(fonts.get(fonts.indexOf(whichFontLabel.getText()) + 1));
			}
		} else if (e.getSource() == prevFontButt){
			if (fonts.indexOf(whichFontLabel.getText()) == 0){
				whichFontLabel.setText(fonts.get(fonts.size() - 1));
			} else {
				whichFontLabel.setText(fonts.get(fonts.indexOf(whichFontLabel.getText()) - 1));
			}
		}
	}

	public void updateColors(Color backgroundColor, Color foregroundColor){
		setBackground(backgroundColor);
		setForeground(foregroundColor);
		settingsLabel.setForeground(foregroundColor);
		applyChangesButt.setBackground(foregroundColor);
		applyChangesButt.setForeground(backgroundColor);
		//colors
		colorLabel.setForeground(foregroundColor);
		whichColorLabel.setForeground(foregroundColor);
		nextColorButt.setBackground(foregroundColor);
		nextColorButt.setForeground(backgroundColor);
		prevColorButt.setBackground(foregroundColor);
		prevColorButt.setForeground(backgroundColor);
		//fonts
		fontLabel.setForeground(foregroundColor);
		whichFontLabel.setForeground(foregroundColor);
		nextFontButt.setBackground(foregroundColor);
		nextFontButt.setForeground(backgroundColor);
		prevFontButt.setBackground(foregroundColor);
		prevFontButt.setForeground(backgroundColor);

	}


}