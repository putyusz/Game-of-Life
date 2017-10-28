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
    static SettingsData data = new SettingsData();
    private JPanel mainPanel;

    private JButton SETTINGSButton;
    private JButton EXITButton;
    private JButton STARTGAMEButton;

    JPanel buttonsPanel;
    JLabel pictureLabel;

    private JMenuBar menuBar = new JMenuBar();

    private JMenuItem startItem = new JMenuItem("Start");
    private JMenuItem restartItem = new JMenuItem("Restart");
    private JMenuItem exitItem = new JMenuItem("Exit");

    MainForm() {
        URL iconURL = MainForm.class.getResource("GameIcon.png" /*"GhostIcon.png"*/);
        ImageIcon icon = new ImageIcon(iconURL);

        data = data.readSettings();

        startItem.setEnabled(false);
        restartItem.setEnabled(false);
        exitItem.setEnabled(false);

        JMenu menu = new JMenu("Menu");
        menu.setBackground(new Color(49,49,49));
        menu.setForeground(new Color(255,255,255));
        menu.setBorder(null);

        menu.add(startItem);
        menu.add(restartItem);
        menu.add(exitItem);

        menuBar.setBackground(new Color(49,49,49));
        menuBar.setBorder(null);
        menuBar.setSize(JFrame.MAXIMIZED_HORIZ,20);
        menuBar.add(menu);

        menuBar.setVisible(false);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setIconImage(icon.getImage());
        mainFrame.add(mainPanel);
        mainFrame.setJMenuBar(menuBar);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setUndecorated(true);
        mainFrame.setVisible(true);

        STARTGAMEButton.addActionListener(e -> {
            mainFrame.getContentPane().removeAll();
            mainFrame.getContentPane().repaint();

            if (data.getWhichGameMode() == 1) {
                mainFrame.add(new NormalGame(mainPanel, menuBar, exitItem));
                mainFrame.validate();
            } else if (data.getWhichGameMode() == 2) {
                mainFrame.add(new DrawGame(mainPanel, menuBar, startItem, restartItem, exitItem));
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
