/*
 * Created by Ponekker Patrik on 12/10/17
 * Copyright (c) 2017.
 */

package game;

import javax.swing.*;
import java.io.*;

import static game.MainForm.data;
import static game.MainForm.mainFrame;
import static game.SettingsData.Color.*;
import static game.SettingsData.Shape.*;
import static game.SettingsData.GameMode.*;

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

    Settings(JPanel mainPanel) {

        BLUERadioButton.setEnabled(false);
        CYANRadioButton.setEnabled(false);
        GREENRadioButton.setEnabled(false);
        YELLOWRadioButton.setEnabled(false);
        REDRadioButton.setEnabled(false);

        popSlider.setValue(data.getPopulation());
        delaySlider.setValue(data.getDelay());

        switch (data.getWhichWasLast()) {
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

        switch (data.getColor()) {
            case BLUE:
                BLUERadioButton.setSelected(true);
                break;
            case CYAN:
                CYANRadioButton.setSelected(true);
                break;
            case GREEN:
                GREENRadioButton.setSelected(true);
                break;
            case YELLOW:
                YELLOWRadioButton.setSelected(true);
                break;
            case RED:
                REDRadioButton.setSelected(true);
                break;
        }

        switch (data.getGameMode()) {
            case NORMAL:
                normalRadioButton.setSelected(true);
                break;
            case DRAW:
                drawRadioButton.setSelected(true);
                break;
        }

        switch (data.getShape()) {
            case CIRCLE:
                circleRadioButton.setSelected(true);
                break;
            case SQUARE:
                squareRadioButton.setSelected(true);
                break;
        }

        rainbowDotsRadioButton.addActionListener(e -> {
            data.setWhichDrawMode(1);
            data.setCrazyRainbow(false);
            data.setWhichWasLast(1);
            BLUERadioButton.setEnabled(false);
            CYANRadioButton.setEnabled(false);
            GREENRadioButton.setEnabled(false);
            YELLOWRadioButton.setEnabled(false);
            REDRadioButton.setEnabled(false);
        });

        crazyRainbowDotsRadioButton.addActionListener(e -> {
            data.setWhichDrawMode(1);
            data.setCrazyRainbow(true);
            data.setWhichWasLast(2);
        });

        ghostsRadioButton.addActionListener(e -> {
            data.setWhichDrawMode(2);
            data.setCrazyRainbow(false);
            data.setWhichWasLast(3);
            BLUERadioButton.setEnabled(false);
            CYANRadioButton.setEnabled(false);
            GREENRadioButton.setEnabled(false);
            YELLOWRadioButton.setEnabled(false);
            REDRadioButton.setEnabled(false);
        });

        coloredDotsRadioButton.addActionListener(e -> {
            data.setWhichDrawMode(3);
            data.setCrazyRainbow(false);
            data.setWhichWasLast(4);
            BLUERadioButton.setSelected(true);
            BLUERadioButton.setEnabled(true);
            CYANRadioButton.setEnabled(true);
            GREENRadioButton.setEnabled(true);
            YELLOWRadioButton.setEnabled(true);
            REDRadioButton.setEnabled(true);
        });

        BLUERadioButton.addActionListener(e -> {
            data.setColor(BLUE);
            BLUERadioButton.setSelected(true);
        });

        CYANRadioButton.addActionListener(e -> {
            data.setColor(CYAN);
            CYANRadioButton.setSelected(true);
        });

        GREENRadioButton.addActionListener(e -> {
            data.setColor(GREEN);
            GREENRadioButton.setSelected(true);
        });

        YELLOWRadioButton.addActionListener(e -> {
            data.setColor(YELLOW);
            YELLOWRadioButton.setSelected(true);
        });

        REDRadioButton.addActionListener(e -> {
            data.setColor(RED);
            REDRadioButton.setSelected(true);
        });

        backButton.addActionListener(e -> {
            data.writeSettings(data);
            mainFrame.getContentPane().removeAll();
            mainFrame.getContentPane().repaint();
            mainFrame.add(mainPanel);
            mainFrame.validate();
        });

        popSlider.addChangeListener(e -> {
            JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting()) {
                data.setPopulation(source.getValue());
            }
        });

        delaySlider.addChangeListener(e -> {
            JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting() ){
                data.setDelay(source.getValue());
            }
        });

        normalRadioButton.addActionListener(e -> data.setGameMode(NORMAL));

        drawRadioButton.addActionListener(e -> data.setGameMode(DRAW));

        circleRadioButton.addActionListener(e -> data.setShape(CIRCLE));

        squareRadioButton.addActionListener(e -> data.setShape(SQUARE));
    }
}
