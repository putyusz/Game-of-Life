/*
 * Created by Ponekker Patrik on 12/10/17
 * Copyright (c) 2017.
 */

package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import static game.MainForm.data;
import static game.MainForm.mainFrame;
import static game.SettingsData.DrawMode.*;

public class NormalGame extends JPanel implements ActionListener {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();
    int row = height / 10 - 2, column = width / 10;

    ArrayList<ArrayList<Cell>> table;

    private URL ghostURL = NormalGame.class.getResource("ghost.jpg");
    private ImageIcon ghost;

    Timer timer;

    NormalGame(){
        table = new ArrayList<>();
        ghost = new ImageIcon(ghostURL);
    }

    NormalGame(JPanel mainPanel, JMenuBar menuBar, JMenuItem exitItem) {
        table = new ArrayList<>();
        ghost = new ImageIcon(ghostURL);

        setBackground(new Color(69, 69, 69));
        setLayout(null);

        menuBar.setVisible(true);

        exitItem.setEnabled(true);

        timer = new Timer(data.getDelay() / 60, this);
        timer.start();

        //alap tabla
        for (int i = 0; i < row; i++) {
            ArrayList<Cell> Row = new ArrayList<>();
            for (int j = 0; j < column; j++) {
                Random rand = new Random();
                int k = rand.nextInt(10000);
                if (k < data.getPopulation()) {
                    Row.add(new Cell(true, false));
                } else {
                    Row.add(new Cell(false, false));
                }
            }
            table.add(Row);
        }

        exitItem.addActionListener(e -> {
            menuBar.setVisible(false);

            exitItem.setEnabled(false);

            mainFrame.getContentPane().removeAll();
            mainFrame.getContentPane().repaint();
            mainFrame.add(mainPanel);
            mainFrame.validate();
            timer.stop();
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintScreen(g);
    }

    void GameAlgorithm() {
        //jatek

        //sarkok
        //jobbfelso
        int ccount = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (table.get(i).get(j).getStatus() && (i != 0 || j != 0)) {
                    ccount++;
                }
            }
        }
        table.get(0).get(0).live(table.get(0).get(0), ccount);
        ccount = 0;

        //jobbalso
        for (int i = row - 2; i < row; i++) {
            for (int j = 0; j < 2; j++) {
                if (table.get(i).get(j).getStatus() && (i != row - 1 || j != 0)) {
                    ccount++;
                }
            }
        }
        table.get(row - 1).get(0).live(table.get(row - 1).get(0), ccount);
        ccount = 0;

        //balfelso
        for (int i = 0; i < 2; i++) {
            for (int j = column - 2; j < column; j++) {
                if (table.get(i).get(j).getStatus() && (i != 0 || j != column - 1)) {
                    ccount++;
                }
            }
        }
        table.get(0).get(column - 1).live(table.get(0).get(column - 1), ccount);
        ccount = 0;

        //balalso
        for (int i = row - 2; i < row; i++) {
            for (int j = column - 2; j < column; j++) {
                if (table.get(i).get(j).getStatus() && (i != row - 1 || j != column - 1)) {
                    ccount++;
                }
            }
        }
        table.get(row - 1).get(column - 1).live(table.get(row - 1).get(column - 1), ccount);

        //szelek
        // 1. sor
        for (int j = 1; j < column - 1; j++) {
            int count = 0;
            for (int l = j - 1; l <= j + 1; l++) {
                if (table.get(0).get(l).getStatus() && l != j) {
                    count++;
                }
                if (table.get(1).get(l).getStatus()) {
                    count++;
                }
            }
            table.get(0).get(j).live(table.get(0).get(j), count);
        }

        //1. oszlop
        for (int i = 1; i < row - 1; i++) {
            int count = 0;
            for (int l = i - 1; l <= i + 1; l++) {
                if (table.get(l).get(0).getStatus() && l != i) {
                    count++;
                }
                if (table.get(l).get(1).getStatus()) {
                    count++;
                }
            }
            table.get(i).get(0).live(table.get(i).get(0), count);
        }

        //s. sor
        for (int j = 1; j < column - 1; j++) {
            int count = 0;
            for (int l = j - 1; l <= j + 1; l++) {
                if (table.get(row - 2).get(l).getStatus()) {
                    count++;
                }
                if (table.get(row - 1).get(l).getStatus() && l != j) {
                    count++;
                }
            }
            table.get(row - 1).get(j).live(table.get(row - 1).get(j), count);
        }

        //o. oszlop
        for (int i = 1; i < row - 1; i++) {
            int count = 0;
            for (int l = i - 1; l <= i + 1; l++) {
                if (table.get(l).get(column - 2).getStatus()) {
                    count++;
                }
                if (table.get(l).get(column - 1).getStatus() && l != i) {
                    count++;
                }
            }
            table.get(i).get(column - 1).live(table.get(i).get(column - 1), count);
        }

        //kozepe
        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < column - 1; j++) {
                int count = 0;
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        if (table.get(k).get(l).getStatus() && (k != i || l != j)) {
                            count++;
                        }
                    }
                }
                table.get(i).get(j).live(table.get(i).get(j), count);
            }
        }

        //feluliras
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                table.get(i).get(j).setStatus(table.get(i).get(j).getNextStatus());
            }
        }
    }

    void screenDrawer(Graphics g) {
        //iteraciok
        //kirajzolas

        int x = 0, y = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (table.get(i).get(j).getStatus()) {
                    if (data.getDrawMode() == RAINBOW || data.getDrawMode() == CRAZY_RAINBOW) {
                        table.get(i).get(j).DrawCell(x, y, g);
                    } else if (data.getDrawMode() == GHOST) {
                        table.get(i).get(j).DrawCell(x, y, g, ghost);
                    } else if (data.getDrawMode() == COLORED) {
                        switch (data.getColor()) {
                            case BLUE:
                                table.get(i).get(j).DrawCell(x, y, g, Color.BLUE);
                                break;
                            case CYAN:
                                table.get(i).get(j).DrawCell(x, y, g, Color.CYAN);
                                break;
                            case GREEN:
                                table.get(i).get(j).DrawCell(x, y, g, Color.GREEN);
                                break;
                            case YELLOW:
                                table.get(i).get(j).DrawCell(x, y, g, Color.YELLOW);
                                break;
                            case RED:
                                table.get(i).get(j).DrawCell(x, y, g, Color.RED);
                                break;
                        }
                    }
                }
                x += 10;
            }
            x = 0;
            y += 10;
        }
    }

    void gridDrawer(Graphics g) {
        int m = 0, n = 0;
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                g.setColor(new Color(49,49,49));
                g.drawLine(m, 0, m, height);
                g.drawLine(0, n, width,  n);
                m += 10;
                n += 10;
            }
        }
    }

    void paintScreen(Graphics g){
        screenDrawer(g);
        GameAlgorithm();
        gridDrawer(g);
    }

    @Override
    public void actionPerformed(ActionEvent s) {
        if (s.getSource() == timer) {
            repaint();
        }
    }
}