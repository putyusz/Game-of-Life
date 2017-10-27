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

public class DrawGame extends JPanel implements ActionListener {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();
    private int s = height / 10, o = width / 10, x = 0, y = 0;

    private ArrayList<ArrayList<Cell>> Table = new ArrayList<>();

    private Timer timer = new Timer(Settings.delay / 60, this);

    private URL ghostURL = DrawGame.class.getResource("Ghost.jpg");
    private ImageIcon Ghost = new ImageIcon(ghostURL);

    private Cell c = new Cell();

    private ArrayList<Point> Points = new ArrayList<>();

    private boolean beforeStartDrawing = true;

    DrawGame(JPanel mainPanel) {
        JButton exitButton = new JButton("EXIT");
        exitButton.setForeground(new Color(255, 255, 255));
        exitButton.setBackground(new Color(49, 49, 49));
        exitButton.setBounds(0, 0, 70, 30);

        JButton startButton = new JButton("START");
        startButton.setForeground(new Color(255, 255, 255));
        startButton.setBackground(new Color(49, 49, 49));
        startButton.setBounds(exitButton.getWidth() + 10, 0, 90, 30);

        JButton restartButton = new JButton("RESTART");
        restartButton.setForeground(new Color(255, 255, 255));
        restartButton.setBackground(new Color(49, 49, 49));
        restartButton.setBounds(exitButton.getWidth() + 10 + startButton.getWidth() + 10, 0, 100, 30);

        setPreferredSize(new Dimension(960, 540));
        setBackground(new Color(69, 69, 69));
        setLayout(null);
        add(exitButton);
        add(startButton);
        add(restartButton);

        exitButton.addActionListener(e -> {
            MainForm.mainFrame.getContentPane().removeAll();
            MainForm.mainFrame.getContentPane().repaint();
            MainForm.mainFrame.add(mainPanel);
            MainForm.mainFrame.validate();
            timer.stop();
        });

        startButton.addActionListener(e -> {
            timer.start();
            beforeStartDrawing = false;
        });

        restartButton.addActionListener(e -> {
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
