import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import java.lang.Math;

import java.io.*;
import java.util.*;

public class GameOptComponent extends JPanel{

  private TyperFrame frame;
  private int width;
  private int height;

  private JLabel optLabel;
  private JLabel gameModeLabel;
  private JButton nextModeButt;
  private JButton prevModeButt;

  private ArrayList<String> gameModes;

  public GameOptComponent(TyperFrame frame){
    this.frame = frame;
    width = 7*frame.getFrameSize()[0]/8;
    height = frame.getFrameSize()[1];

    prepareGUI();

  }

  public void prepareGUI(){

    setLayout(new GridLayout());

    optLabel = new JLabel("GameMode", JLabel.CENTER);

    add(optLabel, 0, 0);

  }



}
