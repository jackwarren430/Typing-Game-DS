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


	public SettingsComponent(TyperFrame frame){
		this.frame = frame;
		width = frame.getFrameSize()[0];
		height = 7*frame.getFrameSize()[1]/8;

	}


	public void actionPerformed(ActionEvent e){

	}


}