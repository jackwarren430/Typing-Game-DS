import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import java.lang.Math;

import java.io.*;
import java.util.*;

public class GameOptComponent extends JPanel implements ActionListener{
    private static final long serialVersionUID = 0000;

    private TyperFrame frame;
    private int width;
    private int height;
    private GridBagConstraints c;

    private JLabel optLabel;

    private JLabel gameModeLabel;
    private JButton nextModeButt;
    private JButton prevModeButt;
    private ArrayList<String> gameModes;
    private int whichGameMode;

    private JLabel freqWordsLabel;
    private JButton toggleFreqWordsButt;
    private Boolean isFreqWords;

    private WordCountModeComponent countModeComp;
    

    public GameOptComponent(TyperFrame frame){
        this.frame = frame;
        width = 7*frame.getFrameSize()[0]/8;
        height = frame.getFrameSize()[1];

        gameModes = new ArrayList<String>();
        gameModes.add("count");
        gameModes.add("time");

        optLabel = new JLabel("GameMode", JLabel.CENTER);

        isFreqWords = false;
        freqWordsLabel = new JLabel("Frequently Missed Words");
        toggleFreqWordsButt = new JButton("off");
        toggleFreqWordsButt.addActionListener(this);
        toggleFreqWordsButt.setFocusable(false);

        gameModeLabel = new JLabel("Game Mode: count");
        nextModeButt = new JButton(">");
        prevModeButt = new JButton("<");
        whichGameMode = 0;
        nextModeButt.addActionListener(this);
        nextModeButt.setFocusable(false);
        prevModeButt.addActionListener(this);
        prevModeButt.setFocusable(false);

        countModeComp = new WordCountModeComponent(this);

        prepareGUI();

    }   

    public void prepareGUI(){
        setLayout(new GridBagLayout());
        c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        add(optLabel, c);

        c.gridx = 0;
        c.gridy = 1;
        add(freqWordsLabel, c);

        c.gridx = 2;
        c.gridy = 1;
        add(toggleFreqWordsButt, c);

        c.gridx = 0;
        c.gridy = 2;
        add(gameModeLabel, c);

        c.gridx = 0;
        c.gridy = 3;
        add(prevModeButt, c);

        c.gridx = 1;
        c.gridy = 3;
        add(nextModeButt, c);

        c.gridx = 0;
        c.gridy = 4;
        add(countModeComp, c);


    }

    class WordCountModeComponent extends JPanel {
        private static final long serialVersionUID = 0000;

        private GameOptComponent parent;
        private GridBagLayout layout;
        private GridBagConstraints c;

        private int wordCount;
        private JLabel wordCountLabel;
        private JButton addWordCountButt;
        private JButton subWordCountButt;


        public WordCountModeComponent(GameOptComponent parent){
            
            this.parent = parent;
            wordCount = frame.getGameSize();
            addWordCountButt = new JButton("+");
            subWordCountButt = new JButton("-");
            wordCountLabel = new JLabel("Word Count: " + wordCount);

            prepareGUI();
        }

        public void prepareGUI(){
            layout = new GridBagLayout();
            setLayout(layout);
            c = new GridBagConstraints();

            c.gridx = 0;
            c.gridy = 0;
            add(wordCountLabel, c);

            c.gridx = 0;
            c.gridy = 1;
            add(subWordCountButt, c);

            c.gridx = 1;
            c.gridy = 1;
            add(subWordCountButt, c);


        }

        public void addWords(){
            if (wordCount < 40){
                wordCount += 4;
                wordCountLabel.setText("Word Count: " + wordCount);
            }
        }

        public void subWords(){
            if (wordCount > 12){
                wordCount -= 4;
                wordCountLabel.setText("Word Count: " + wordCount);
            }
        }

        public JButton getAdd(){
            return addWordCountButt;
        }

        public JButton getSub(){
            return subWordCountButt;
        }

    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == toggleFreqWordsButt){
            isFreqWords = !isFreqWords;
            toggleFreqWordsButt.setText(isFreqWords ? "on" : "off");
        } else if (e.getSource() == countModeComp.getAdd()){
            countModeComp.addWords();
        } else if (e.getSource() == countModeComp.getSub()){
            countModeComp.subWords();
        }
    }


}
