/*
 * Created by Ponekker Patrik on 12/10/17
 * Copyright (c) 2017.
 */

package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static game.MainForm.data;
import static game.MainForm.mainFrame;

class DrawGame extends NormalGame implements ActionListener {
    private int x = 0, y = 0;

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
            table.clear();
            for (int i = 0; i < row; i++) {
                ArrayList<Cell> Row = new ArrayList<>();
                for (int j = 0; j < column; j++) {
                    Row.add(new Cell(false, false));
                }
                table.add(Row);
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

        for (int i = 0; i < row; i++) {
            ArrayList<Cell> Row = new ArrayList<>();
            for (int j = 0; j < column; j++) {
                Row.add(new Cell(false, false));
            }
            table.add(Row);
        }
    }

    private void createCell(int x, int y) {
        if (0 <= x && x < column && 0 <= y && y < row) {
            if (table.get(y).get(x).getStatus()) {
                table.get(y).get(x).setStatus(false);
                if (beforeStartDrawing) {
                    repaint();
                }
            } else {
                table.get(y).get(x).setStatus(true);
                if (beforeStartDrawing) {
                    repaint();
                }
            }
        }
    }

    private void panelMousePressed(MouseEvent e) {
        x = e.getX() / 10;
        y = e.getY() / 10;
        createCell(x, y);
    }

    private void panelMouseDragged(MouseEvent e) {
        x = e.getX() / 10;
        y = e.getY() / 10;
        createCell(x, y);
    }

    @Override
    void paintScreen(Graphics g) {
        if (beforeStartDrawing) {
            for ( int i = 0; i < row; i++){
                for ( int j = 0; j < column; j++){
                    if(table.get(i).get(j).getStatus()){
                        g.fillRoundRect(j * 10 + 1, i * 10 + 1, 8, 8, 8, 8);
                    }
                }
            }
        } else {
            screenDrawer(g);
            GameAlgorithm();
        }
        gridDrawer(g);
    }
}
