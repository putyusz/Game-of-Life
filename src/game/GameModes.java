/*
 * Created by Ponekker Patrik on 12/10/17
 * Copyright (c) 2017.
 */

package game;

import javax.swing.*;

public class GameModes extends JPanel {
    private JRadioButton normalRadioButton;
    private JRadioButton fromFileNemWörkingRadioButton;
    private JRadioButton drawRadioButton;

    JPanel mainPanel;
    JPanel modesPanel;
    JPanel filesPanel;

    private JButton backButton;
    private JPanel gameModesPanel;

    static int whichGameMode = 1;

    GameModes(JPanel mainPanel) {

        normalRadioButton.addActionListener(e -> whichGameMode = 1);

        drawRadioButton.addActionListener(e -> whichGameMode = 2);

        backButton.addActionListener(e -> {
            MainForm.mainFrame.getContentPane().removeAll();
            MainForm.mainFrame.getContentPane().repaint();
            MainForm.mainFrame.add(mainPanel);
            MainForm.mainFrame.validate();
        });
    }
}
