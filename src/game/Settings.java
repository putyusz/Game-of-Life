/*
 * Created by Ponekker Patrik on 12/10/17
 * Copyright (c) 2017.
 */

package game;

import javax.swing.*;
import java.io.*;

import static game.MainForm.data;
import static game.MainForm.mainFrame;

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

        data = data.readSettings();

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

        switch (data.getWhichColor()) {
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

        switch (data.getWhichGameMode()) {
            case 1:
                normalRadioButton.setSelected(true);
                break;
            case 2:
                drawRadioButton.setSelected(true);
                break;
        }

        switch (data.getWhichShape()) {
            case 1:
                circleRadioButton.setSelected(true);
                break;
            case 2:
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
            data.setWhichColor(1);
            BLUERadioButton.setSelected(true);
        });

        CYANRadioButton.addActionListener(e -> {
            data.setWhichColor(2);
            CYANRadioButton.setSelected(true);
        });

        GREENRadioButton.addActionListener(e -> {
            data.setWhichColor(3);
            GREENRadioButton.setSelected(true);
        });

        YELLOWRadioButton.addActionListener(e -> {
            data.setWhichColor(4);
            YELLOWRadioButton.setSelected(true);
        });

        REDRadioButton.addActionListener(e -> {
            data.setWhichColor(5);
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

        normalRadioButton.addActionListener(e -> data.setWhichGameMode(1));

        drawRadioButton.addActionListener(e -> data.setWhichGameMode(2));

        circleRadioButton.addActionListener(e -> data.setWhichShape(1));

        squareRadioButton.addActionListener(e -> data.setWhichShape(2));
    }
}
