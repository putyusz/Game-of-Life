/*
 * Created by Ponekker Patrik on 12/10/17
 * Copyright (c) 2017.
 */

package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

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
    JPanel settingsPanel;
    JPanel modesPanel;
    JPanel shapePanel;

    private JButton backButton;
    private JRadioButton normalRadioButton;
    private JRadioButton drawRadioButton;
    private JSlider popSlider;
    private JSlider delaySlider;
    private JRadioButton circleRadioButton;
    private JRadioButton squareRadioButton;

    static int whichDrawMode = 1;
    static int whichColor = 1;
    static int whichGameMode = 1;
    static int population = 2500;
    static int delay = 5000;
    static int whichShape = 1;
    static boolean crazyRainbow = false;
    private int whichWasLast = 1;

    private SettingsData data = new SettingsData();

    Settings(JPanel mainPanel) {

        BLUERadioButton.setEnabled(false);
        CYANRadioButton.setEnabled(false);
        GREENRadioButton.setEnabled(false);
        YELLOWRadioButton.setEnabled(false);
        REDRadioButton.setEnabled(false);

        data = data.readSettings();

        whichDrawMode = data.getWhichDrawMode();
        whichColor = data.getWhichColor();
        whichGameMode = data.getWhichGameMode();
        population = data.getPopulation();
        delay = data.getDelay();
        crazyRainbow = data.isCrazyRainbow();
        whichWasLast = data.getWhichWasLast();

        popSlider.setValue(population);
        delaySlider.setValue(delay);

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

        switch (whichShape) {
            case 1:
                circleRadioButton.setSelected(true);
                break;
            case 2:
                squareRadioButton.setSelected(true);
                break;
        }

        rainbowDotsRadioButton.addActionListener(e -> {
            whichDrawMode = 1;
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
            whichDrawMode = 2;
            crazyRainbow = false;
            whichWasLast = 3;
            BLUERadioButton.setEnabled(false);
            CYANRadioButton.setEnabled(false);
            GREENRadioButton.setEnabled(false);
            YELLOWRadioButton.setEnabled(false);
            REDRadioButton.setEnabled(false);
        });

        coloredDotsRadioButton.addActionListener(e -> {
            whichDrawMode = 3;
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
            data.setWhichColor(whichColor);
            data.setWhichDrawMode(whichDrawMode);
            data.setWhichGameMode(whichGameMode);
            data.setWhichWasLast(whichWasLast);
            data.setPopulation(population);
            data.setShape(whichShape);
            data.setDelay(delay);
            data.setCrazyRainbow(crazyRainbow);
            data.writeSettings(data);
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

        circleRadioButton.addActionListener(e -> whichShape = 1);

        squareRadioButton.addActionListener(e -> whichShape = 2);
    }
}
