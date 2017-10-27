/*
 * Created by Ponekker Patrik on 12/10/17
 * Copyright (c) 2017.
 */

package game;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.*;

import static java.lang.Integer.parseInt;

public class Settings extends JPanel implements Serializable {

    private JRadioButton ghostsRadioButton;
    private JRadioButton rainbowDotsRadioButton;
    private JRadioButton coloredDotsRadioButton;
    private JRadioButton BLUERadioButton;
    private JRadioButton REDRadioButton;
    private JRadioButton YELLOWRadioButton;
    private JRadioButton GREENRadioButton;
    private JRadioButton CYANRadioButton;
    private JRadioButton crazyRainbowDotsRadioButton;

    JPanel mainPanel;
    JPanel stylePanel;
    JPanel colorPanel;
    JPanel popPanel;
    JPanel delayPanel;

    private JButton backButton;
    private JPanel settingsPanel;
    private JPanel modesPanel;
    private JRadioButton normalRadioButton;
    private JRadioButton drawRadioButton;
    private JSlider popSlider;
    private JSlider delaySlider;

    static int whichMode = 1;
    static int whichColor = 1;
    static int whichGameMode = 1;
    static int population = 2500;
    static int delay = 5000;
    static boolean crazyRainbow = false;
    private int whichWasLast = 1;

    Settings(JPanel mainPanel) {

        BLUERadioButton.setEnabled(false);
        CYANRadioButton.setEnabled(false);
        GREENRadioButton.setEnabled(false);
        YELLOWRadioButton.setEnabled(false);
        REDRadioButton.setEnabled(false);

        switch (whichWasLast) {
            case 1:
                rainbowDotsRadioButton.setSelected(true);
                break;
            case 2:
                crazyRainbowDotsRadioButton.setSelected(true);
                break;
            case 3:
                ghostsRadioButton.setSelected(true);
                break;
            case 4:
                coloredDotsRadioButton.setSelected(true);
                break;

        }

        switch (whichColor) {
            case 1:
                BLUERadioButton.setSelected(true);
                break;
            case 2:
                CYANRadioButton.setSelected(true);
                break;
            case 3:
                GREENRadioButton.setSelected(true);
                break;
            case 4:
                YELLOWRadioButton.setSelected(true);
                break;
            case 5:
                REDRadioButton.setSelected(true);
                break;
        }

        switch (whichGameMode) {
            case 1:
                normalRadioButton.setSelected(true);
                break;
            case 2:
                drawRadioButton.setSelected(true);
                break;
        }

        rainbowDotsRadioButton.addActionListener(e -> {
            whichMode = 1;
            crazyRainbow = false;
            whichWasLast = 1;
            BLUERadioButton.setEnabled(false);
            CYANRadioButton.setEnabled(false);
            GREENRadioButton.setEnabled(false);
            YELLOWRadioButton.setEnabled(false);
            REDRadioButton.setEnabled(false);
        });

        crazyRainbowDotsRadioButton.addActionListener(e -> {
            crazyRainbow = true;
            whichWasLast = 2;
        });

        ghostsRadioButton.addActionListener(e -> {
            whichMode = 2;
            crazyRainbow = false;
            whichWasLast = 3;
            BLUERadioButton.setEnabled(false);
            CYANRadioButton.setEnabled(false);
            GREENRadioButton.setEnabled(false);
            YELLOWRadioButton.setEnabled(false);
            REDRadioButton.setEnabled(false);
        });

        coloredDotsRadioButton.addActionListener(e -> {
            whichMode = 3;
            crazyRainbow = false;
            whichWasLast = 4;
            BLUERadioButton.setSelected(true);
            BLUERadioButton.setEnabled(true);
            CYANRadioButton.setEnabled(true);
            GREENRadioButton.setEnabled(true);
            YELLOWRadioButton.setEnabled(true);
            REDRadioButton.setEnabled(true);
        });

        BLUERadioButton.addActionListener(e -> {
            whichColor = 1;
            BLUERadioButton.setSelected(true);
        });

        CYANRadioButton.addActionListener(e -> {
            whichColor = 2;
            CYANRadioButton.setSelected(true);
        });

        GREENRadioButton.addActionListener(e -> {
            whichColor = 3;
            GREENRadioButton.setSelected(true);
        });

        YELLOWRadioButton.addActionListener(e -> {
            whichColor = 4;
            YELLOWRadioButton.setSelected(true);
        });

        REDRadioButton.addActionListener(e -> {
            whichColor = 5;
            REDRadioButton.setSelected(true);
        });

        backButton.addActionListener(e -> {
            MainForm.mainFrame.getContentPane().removeAll();
            MainForm.mainFrame.getContentPane().repaint();
            MainForm.mainFrame.add(mainPanel);
            MainForm.mainFrame.validate();
        });

        popSlider.addChangeListener(e -> {
            JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting()) {
                population = source.getValue();
            }
        });

        delaySlider.addChangeListener(e -> {
            JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting() ){
                delay = source.getValue();
            }
        });

        normalRadioButton.addActionListener(e -> whichGameMode = 1);

        drawRadioButton.addActionListener(e -> whichGameMode = 2);
    }
}
