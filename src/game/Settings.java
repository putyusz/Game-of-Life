package game;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.io.*;

import static game.MainForm.data;
import static game.MainForm.mainFrame;
import static game.SettingsData.DrawMode.*;
import static game.SettingsData.Shape.*;
import static game.SettingsData.GameMode.*;

/**
 * A beállítások felhasználói felülete
 */
public class Settings extends JPanel implements Serializable {

    private JRadioButton ghostsRadioButton;
    private JRadioButton rainbowDotsRadioButton;
    private JRadioButton coloredDotsRadioButton;
    private JRadioButton crazyRainbowDotsRadioButton;

    JPanel mainPanel;
    JPanel stylePanel;
    JPanel popPanel;
    JPanel delayPanel;
    JPanel settingsPanel;
    JPanel modesPanel;
    JPanel shapePanel;
    JPanel colorChooserPanel;
    JPanel cellSizePanel;

    private JButton backButton;
    private JRadioButton normalRadioButton;
    private JRadioButton drawRadioButton;
    private JSlider popSlider;
    private JSlider delaySlider;
    private JSlider cellSizeSlider;
    private JRadioButton circleRadioButton;
    private JRadioButton squareRadioButton;
    private JColorChooser colorChooser;

    /**
     * A beállítások felhasználói felületét hozza létre
     * @param mainPanel A főmenü JPanelje
     */
    Settings(JPanel mainPanel) {

        popSlider.setValue(data.getPopulation());
        delaySlider.setValue(data.getDelay());
        cellSizeSlider.setValue(data.getCellSize());

        colorChooser.setEnabled(false);

        colorChooser.setColor(data.getColor());

        switch (data.getDrawMode()) {
            case RAINBOW:
                rainbowDotsRadioButton.setSelected(true);
                break;
            case CRAZY_RAINBOW:
                crazyRainbowDotsRadioButton.setSelected(true);
                break;
            case GHOST:
                ghostsRadioButton.setSelected(true);
                cellSizeSlider.setEnabled(false);
                cellSizeSlider.setValue(15);
                break;
            case COLORED:
                coloredDotsRadioButton.setSelected(true);
                colorChooser.setEnabled(true);
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
            data.setDrawMode(RAINBOW);
            data.setCrazyRainbow(false);
            cellSizeSlider.setEnabled(true);
            colorChooser.setEnabled(false);
        });

        crazyRainbowDotsRadioButton.addActionListener(e -> {
            data.setDrawMode(CRAZY_RAINBOW);
            data.setCrazyRainbow(true);
            cellSizeSlider.setEnabled(true);
            colorChooser.setEnabled(false);
        });

        ghostsRadioButton.addActionListener(e -> {
            data.setDrawMode(GHOST);
            data.setCellSize(15);
            cellSizeSlider.setEnabled(false);
            cellSizeSlider.setValue(15);
            data.setCrazyRainbow(false);
            colorChooser.setEnabled(false);
        });

        coloredDotsRadioButton.addActionListener(e -> {
            data.setDrawMode(COLORED);
            data.setCrazyRainbow(false);
            cellSizeSlider.setEnabled(true);
            colorChooser.setEnabled(true);
        });

        normalRadioButton.addActionListener(e -> data.setGameMode(NORMAL));

        drawRadioButton.addActionListener(e -> data.setGameMode(DRAW));

        circleRadioButton.addActionListener(e -> data.setShape(CIRCLE));

        squareRadioButton.addActionListener(e -> data.setShape(SQUARE));

        AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
        for (int i = 1; i <= 4; i++) {
            colorChooser.removeChooserPanel(panels[i]);
        }
        colorChooser.setPreviewPanel(new JPanel());
        colorChooser.getSelectionModel().addChangeListener(e -> data.setColor(colorChooser.getColor()));

        popSlider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                data.setPopulation(source.getValue());
            }
        });

        delaySlider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                data.setDelay(source.getValue());
            }
        });

        cellSizeSlider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                data.setCellSize(source.getValue());
            }
        });

        backButton.addActionListener(e -> {
            data.writeSettings();
            mainFrame.getContentPane().removeAll();
            mainFrame.getContentPane().repaint();
            mainFrame.add(mainPanel);
            mainFrame.validate();
        });
    }
}
