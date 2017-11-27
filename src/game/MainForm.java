package game;

import javax.swing.*;
import java.awt.*;

import static game.SettingsData.GameMode.*;

/**
 *A játék főmenüje
 */
public class MainForm {
    static JFrame mainFrame;
    static SettingsData data;
    private JPanel mainPanel;

    private JButton SETTINGSButton;
    private JButton EXITButton;
    private JButton STARTGAMEButton;

    JPanel buttonsPanel;
    JLabel pictureLabel;

    private JMenuBar menuBar;

    private JMenuItem startItem;
    private JMenuItem restartItem;
    private JMenuItem exitItem;

    /**
     * A főmenüt csinálja meg valamint beolvassa fájlból a beállításokat
     */
    MainForm() {

        mainFrame = new JFrame("Game of Life");
        ImageIcon icon = new ImageIcon("assets/GameIcon.png");

        data = new SettingsData();
        data.readSettings();

        startItem = new JMenuItem("Start");
        restartItem = new JMenuItem("Restart");
        exitItem = new JMenuItem("Exit");

        startItem.setEnabled(false);
        restartItem.setEnabled(false);
        exitItem.setEnabled(false);

        menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menu.setBackground(new Color(49, 49, 49));
        menu.setForeground(new Color(255, 255, 255));
        menu.setBorder(null);

        menu.add(startItem);
        menu.add(restartItem);
        menu.add(exitItem);

        menuBar.setBackground(new Color(49, 49, 49));
        menuBar.setBorder(null);
        menuBar.setSize(JFrame.MAXIMIZED_HORIZ , 20);
        menuBar.add(menu);

        menuBar.setVisible(false);

        pictureLabel.setIcon(new ImageIcon("assets/Title.png"));

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setIconImage(icon.getImage());
        mainFrame.setJMenuBar(menuBar);
        mainFrame.add(mainPanel);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setUndecorated(true);
        mainFrame.setVisible(true);

        STARTGAMEButton.addActionListener(e -> {
            mainFrame.getContentPane().removeAll();
            mainFrame.getContentPane().repaint();

            if (data.getGameMode() == NORMAL) {
                mainFrame.add(new NormalGame(mainPanel, menuBar, startItem,restartItem, exitItem));
                mainFrame.validate();
            } else if (data.getGameMode() == DRAW) {
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

        EXITButton.addActionListener(e -> {
            mainFrame.dispose();
            data.writeSettings();
        });
    }

}
