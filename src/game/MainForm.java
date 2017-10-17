/*
 * Created by Ponekker Patrik on $today.year.month.day
 * Copyright (c) 2017.
 */

package game;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainForm {
    static JFrame mainFrame = new JFrame("Game of Life");
    JPanel mainPanel;

    private JButton SETTINGSButton;
    private JButton EXITButton;
    private JButton GAMEMODESButton;
    private JButton STARTGAMEButton;

    private JPanel buttonsPanel;

    MainForm() {
        //URL backgroundURL = MainForm.class.getResource("Background.png");
        URL iconURL = MainForm.class.getResource("GameIcon.png" /*"GhostIcon.png"*/);
        ImageIcon icon = new ImageIcon(iconURL);
        //ImageIcon backGround = new ImageIcon(backgroundURL);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setIconImage(icon.getImage());

//        JLabel bg = new JLabel(backGround);

        mainFrame.add(mainPanel);
        mainFrame.setMinimumSize(new Dimension(800, 450));
        mainFrame.setSize(new Dimension(960, 540));
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        STARTGAMEButton.addActionListener(e -> {
            mainFrame.getContentPane().removeAll();
            mainFrame.getContentPane().repaint();

            if (GameModes.whichGameMode == 1) {
                mainFrame.add(new NormalGame(mainPanel));
                mainFrame.validate();
            } else if (GameModes.whichGameMode == 2) {
                mainFrame.add(new DrawGame(mainPanel));
                mainFrame.validate();
            }
        });

        GAMEMODESButton.addActionListener(e -> {
            mainFrame.getContentPane().removeAll();
            mainFrame.getContentPane().repaint();
            mainFrame.add(new GameModes(mainPanel).mainPanel);
            mainFrame.validate();
        });

        SETTINGSButton.addActionListener(e -> {
            mainFrame.getContentPane().removeAll();
            mainFrame.getContentPane().repaint();
            mainFrame.add(new Settings(mainPanel).mainPanel);
            mainFrame.validate();
        });

        EXITButton.addActionListener(e -> System.exit(0));
    }
}
