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
		height = frame.getFrameSize()[1] - 25;
	}

	public void paintComponent(Graphics g){
		Graphics2D pen = (Graphics2D) g;
		width = frame.getFrameSize()[0];
		height = frame.getFrameSize()[1] - 25;

		pen.drawString("TYPING WIZARD 2022", width/2, height/4);
	
	}
}

