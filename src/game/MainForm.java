/*
 * Created by Ponekker Patrik on $today.year.month.day
 * Copyright (c) 2017.
 */

package game;

import javax.swing.*;
import java.net.URL;

public class MainForm {
    static JFrame mainFrame = new JFrame("Game of Life");
    private JPanel mainPanel;

    private JButton SETTINGSButton;
    private JButton EXITButton;
    private JButton STARTGAMEButton;

    JPanel buttonsPanel;
    JLabel pictureLabel;

    MainForm() {
        URL iconURL = MainForm.class.getResource("GameIcon.png" /*"GhostIcon.png"*/);
        ImageIcon icon = new ImageIcon(iconURL);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainFrame.setIconImage(icon.getImage());
        mainFrame.add(mainPanel);
//        mainFrame.setMinimumSize(new Dimension(800, 450));
//        mainFrame.setSize(new Dimension(960, 540));
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setUndecorated(true);
        mainFrame.setVisible(true);

        STARTGAMEButton.addActionListener(e -> {
            mainFrame.getContentPane().removeAll();
            mainFrame.getContentPane().repaint();

            if (Settings.whichGameMode == 1) {
                mainFrame.add(new NormalGame(mainPanel));
                mainFrame.validate();
            } else if (Settings.whichGameMode == 2) {
                mainFrame.add(new DrawGame(mainPanel));
                mainFrame.validate();
            }
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
