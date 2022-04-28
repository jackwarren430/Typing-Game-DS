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
    private GridBagLayout mainLayout;

    private JLabel optLabel;

    private JLabel gameModeLabel;
    private JButton toggleModeButt;
    private ArrayList<String> gameModes;
    private String gameMode;

    private JLabel freqWordsLabel;
    private JButton toggleFreqWordsButt;
    private Boolean isFreqWords;

    private WordCountModeComponent countModeComp;
    private TimedModeComponent timeModeComp;
    

    public GameOptComponent(TyperFrame frame){
        this.frame = frame;
        width = frame.getFrameSize()[0];
        height = 7*frame.getFrameSize()[1]/8;

        gameModes = new ArrayList<String>();
        gameModes.add("count");
        gameModes.add("time");

        optLabel = new JLabel("Game Options", JLabel.CENTER);

        isFreqWords = false;
        freqWordsLabel = new JLabel("Frequently Missed Words:", JLabel.CENTER);
        toggleFreqWordsButt = new JButton("off");
        toggleFreqWordsButt.addActionListener(this);
        toggleFreqWordsButt.setFocusable(false);

        gameModeLabel = new JLabel("Game Mode:", JLabel.CENTER);
        toggleModeButt = new JButton("count");
        gameMode = "count";
        toggleModeButt.addActionListener(this);
        toggleModeButt.setFocusable(false);

        countModeComp = new WordCountModeComponent(this);
        timeModeComp = new TimedModeComponent(this);

        optLabel.setFont(Styles.buttonFont2);
        freqWordsLabel.setFont(Styles.logiFont);
        gameModeLabel.setFont(Styles.logiFont);

        prepareGUI();

    }   

    public void prepareGUI(){
        mainLayout = new GridBagLayout();
        setLayout(mainLayout);
        c = new GridBagConstraints();

        remove(timeModeComp);
        remove(countModeComp);
        c.fill = GridBagConstraints.HORIZONTAL;

        Insets i = new Insets(height/12,0,height/10,0);
        c.insets = i;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        add(optLabel, c);

        i = new Insets(0,0,height/10,0);
        c.insets = i;

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        add(freqWordsLabel, c);

        i = new Insets(0,width/32,height/10,0);
        c.insets = i;

        c.gridx = 2;
        c.gridy = 1;
        add(toggleFreqWordsButt, c);

        i = new Insets(0,0,height/10,0);
        c.insets = i;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        add(gameModeLabel, c);

        i = new Insets(0,0,height/10,0);
        c.insets = i;

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        add(toggleModeButt, c);

        i = new Insets(0,0,height/9,0);
        c.insets = i;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 3;
        add(countModeComp, c);


    }

    public void setPrefSize(){
        width = frame.getFrameSize()[0];
        height = 7*frame.getFrameSize()[1]/8;
        toggleFreqWordsButt.setPreferredSize(new Dimension(width/16, height/20));
        toggleModeButt.setPreferredSize(new Dimension(width/16, height/20));
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
            addWordCountButt.setFocusable(false);
            addWordCountButt.addActionListener(parent);
            subWordCountButt = new JButton("-");
            subWordCountButt.setFocusable(false);
            subWordCountButt.addActionListener(parent);
            wordCountLabel = new JLabel("Word Count: " + wordCount);
            wordCountLabel.setFont(Styles.logiFont);

            prepareGUI();
        }

        public void prepareGUI(){
            layout = new GridBagLayout();
            setLayout(layout);
            c = new GridBagConstraints();

            Insets i = new Insets(0,0,height/20,0);
            c.insets = i;
            c.gridx = 0;
            c.gridy = 0;
            add(wordCountLabel, c);

            i = new Insets(0,0,0,0);
            c.insets = i;

            c.gridx = 0;
            c.gridy = 1;
            add(subWordCountButt, c);

            c.gridx = 1;
            c.gridy = 1;
            add(addWordCountButt, c);


        }



        public void addWords(){
            if (wordCount < 32){
                wordCount += 4;
                wordCountLabel.setText("Word Count: " + wordCount);
                frame.setGameSize(wordCount);
            }
        }

        public void subWords(){
            if (wordCount > 12){
                wordCount -= 4;
                wordCountLabel.setText("Word Count: " + wordCount);
                frame.setGameSize(wordCount);
            }
        }

        public JButton getAdd(){
            return addWordCountButt;
        }

        public JButton getSub(){
            return subWordCountButt;
        }

        public void updateColors(Color backgroundColor, Color foregroundColor){
            setBackground(backgroundColor);
            setForeground(foregroundColor);
            wordCountLabel.setForeground(foregroundColor);
            subWordCountButt.setBackground(foregroundColor);
            subWordCountButt.setForeground(backgroundColor);
            addWordCountButt.setForeground(backgroundColor);
            addWordCountButt.setBackground(foregroundColor);
        }

    }

    class TimedModeComponent extends JPanel {
        private static final long serialVersionUID = 0000;

        GameOptComponent parent;
        private GridBagLayout layout;
        private GridBagConstraints c;

        private int time;
        private JLabel timeLabel;
        private JButton addTimeButt;
        private JButton subTimeButt;

        public TimedModeComponent(GameOptComponent parent){
            this.parent = parent;

            time = 20;
            timeLabel = new JLabel("Game Time: " + time);
            addTimeButt = new JButton("+");
            addTimeButt.addActionListener(parent);
            addTimeButt.setFocusable(false);
            subTimeButt = new JButton("-");
            subTimeButt.addActionListener(parent);
            subTimeButt.setFocusable(false);
            timeLabel.setFont(Styles.logiFont);
            prepareGUI();
        }

        public void prepareGUI(){
            layout = new GridBagLayout();
            setLayout(layout);
            c = new GridBagConstraints();

            Insets i = new Insets(0,0,height/20,0);
            c.insets = i;

            c.gridx = 0;
            c.gridy = 0;
            add(timeLabel, c);

            i = new Insets(0,0,0,0);
            c.insets = i;

            c.gridx = 0;
            c.gridy = 1;
            add(subTimeButt, c);

            c.gridx = 1;
            c.gridy = 1;
            add(addTimeButt, c);
        }

        public void addTime(){
            if (time < 100){
                time += 2;
                timeLabel.setText("Game Time: " + time);
                frame.setGameTimeLength(time);
            }
        }

        public void subTime(){
            if (time > 10){
                time -= 2;
                timeLabel.setText("Game Time: " + time);
                frame.setGameTimeLength(time);
            }
        }

        public JButton getAdd(){
            return addTimeButt;
        }

        public JButton getSub(){
            return subTimeButt;
        }

        public void updateColors(Color backgroundColor, Color foregroundColor){
            setBackground(backgroundColor);
            setForeground(foregroundColor);
            timeLabel.setForeground(foregroundColor);
            subTimeButt.setBackground(foregroundColor);
            subTimeButt.setForeground(backgroundColor);
            addTimeButt.setForeground(backgroundColor);
            addTimeButt.setBackground(foregroundColor);
        }
    }

    

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == toggleFreqWordsButt){
            if (frame.getLoadedProfile().getFreqWordsArr().size() > 8){
                isFreqWords = !isFreqWords;
                frame.toggleFreqMode();
                frame.getGameComp().setFreqMode(isFreqWords);
                toggleFreqWordsButt.setText(isFreqWords ? "on" : "off");
            }
        } else if (e.getSource() == countModeComp.getAdd()){
            countModeComp.addWords();
        } else if (e.getSource() == countModeComp.getSub()){
            countModeComp.subWords();
        } else if (e.getSource() == toggleModeButt){   
            gameMode = gameMode.equals("time") ? "count" : "time";
            toggleModeButt.setText(gameMode);
            frame.setGameMode(gameMode);
            remove(gameMode.equals("time") ? countModeComp : timeModeComp);
            c.gridx = 0;
            c.gridy = 4;
            c.gridwidth = 2;
            add(gameMode.equals("time") ? timeModeComp : countModeComp, c);
            if (gameMode.equals("time")){
                frame.setGameSize(24);
            }
        } else if (e.getSource() == timeModeComp.getAdd()){
            timeModeComp.addTime();
        } else if (e.getSource() == timeModeComp.getSub()){
            timeModeComp.subTime();
        }
    }

    public void updateColors(Color backgroundColor, Color foregroundColor){
        setBackground(backgroundColor);
        setForeground(foregroundColor);
        optLabel.setForeground(foregroundColor);
        freqWordsLabel.setForeground(foregroundColor);
        toggleFreqWordsButt.setForeground(backgroundColor);
        toggleFreqWordsButt.setBackground(foregroundColor);
        toggleModeButt.setForeground(backgroundColor);
        toggleModeButt.setBackground(foregroundColor);
        gameModeLabel.setForeground(foregroundColor);

        countModeComp.updateColors(backgroundColor, foregroundColor);
        timeModeComp.updateColors(backgroundColor, foregroundColor);
    }


}
