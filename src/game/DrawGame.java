/*
 * Created by Ponekker Patrik on 12/10/17
 * Copyright (c) 2017.
 */

package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;

import static game.MainForm.data;
import static game.MainForm.mainFrame;

public class DrawGame extends JPanel implements ActionListener {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();
    private int s = height / 10 - 2, o = width / 10, x = 0, y = 0;

    private ArrayList<ArrayList<Cell>> Table = new ArrayList<>();

    private Timer timer;

    private URL ghostURL = DrawGame.class.getResource("Ghost.jpg");
    private ImageIcon Ghost = new ImageIcon(ghostURL);

    private Cell c = new Cell();

    private ArrayList<Point> Points = new ArrayList<>();

    private boolean beforeStartDrawing = true;

    DrawGame(JPanel mainPanel, JMenuBar menuBar, JMenuItem startItem, JMenuItem restartItem, JMenuItem exitItem) {
        setBackground(new Color(69, 69, 69));
        setLayout(null);

        menuBar.setVisible(true);

        startItem.setEnabled(true);
        restartItem.setEnabled(true);
        exitItem.setEnabled(true);

        timer = new Timer(data.getDelay() / 60, this);

        startItem.addActionListener(e -> {
            timer.start();
            beforeStartDrawing = false;
        });

        restartItem.addActionListener(e -> {
            beforeStartDrawing = true;
            repaint();
            timer.stop();
            Table.clear();
            Points.clear();
            for (int i = 0; i < s; i++) {
                ArrayList<Cell> Row = new ArrayList<>();
                for (int j = 0; j < o; j++) {
                    Row.add(new Cell(false, false));
                }
                Table.add(Row);
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                panelMousePressed(e);
            }
        });

        exitItem.addActionListener(e -> {
            menuBar.setVisible(false);

            startItem.setEnabled(false);
            restartItem.setEnabled(false);
            exitItem.setEnabled(false);

            mainFrame.getContentPane().removeAll();
            mainFrame.getContentPane().repaint();
            mainFrame.add(mainPanel);
            mainFrame.validate();
            timer.stop();
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                panelMouseDragged(e);
            }
        });

        for (int i = 0; i < s; i++) {
            ArrayList<Cell> Row = new ArrayList<>();
            for (int j = 0; j < o; j++) {
                Row.add(new Cell(false, false));
            }
            Table.add(Row);
        }
    }

    private void createCell1(int x, int y) {
        if (x < o && y < s && x >= 0 && y >= 0) {
            if (!Table.get(y).get(x).getStatus()) {
                Table.get(y).get(x).setStatus(true);
                if (beforeStartDrawing) {
                    Points.add(new Point(x, y));
                    repaint();
                }
            }
        }
    }

    private void createCell2(int x, int y) {
        if (x < o && y < s && x >= 0 && y >= 0) {
            if (Table.get(y).get(x).getStatus()) {
                if (beforeStartDrawing) {
                    Table.get(y).get(x).setStatus(false);
                    Points.remove(new Point(x, y));
                    repaint();
                }
            } else {
                createCell1(x, y);
            }
        }
    }

    private void panelMousePressed(MouseEvent e) {
        x = e.getX() / 10;
        y = e.getY() / 10;
        createCell2(x, y);
    }

    private void panelMouseDragged(MouseEvent e) {
        x = e.getX() / 10;
        y = e.getY() / 10;
        createCell1(x, y);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (beforeStartDrawing) {
            for (Point point : Points) {
                c.DrawCell(point.x * 10, point.y * 10, g, new Color(49, 49, 49));
            }
        } else {
            c.screenDrawer(s, o, g, Table, Ghost);
            Table = c.GameAlgorithm(s, o, Table);
        }
        c.gridDrawer(g,s,o,height,width);
    }

    @Override
    public void actionPerformed(ActionEvent s) {
        if (s.getSource() == timer) {
            repaint();
        }
    }
}
