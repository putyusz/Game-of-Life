package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static game.MainForm.data;

class DrawGame extends NormalGame implements ActionListener {
    private int x = 0, y = 0;

    private boolean beforeStartDrawing = true;

    /**
     * @see NormalGame
     */
    DrawGame(JPanel mainPanel, JMenuBar menuBar, JMenuItem startItem, JMenuItem restartItem, JMenuItem exitItem) {
        super(mainPanel, menuBar, startItem, restartItem, exitItem);
    }

    /**
     * A DrawGame felhasználói felületét csinálja meg
     * paraméterekhez:
     * @see NormalGame
     */
    @Override
    void makeGUIForGame(JPanel mainPanel, JMenuBar menuBar, JMenuItem startItem, JMenuItem restartItem, JMenuItem exitItem){
        menuBar.setVisible(true);

        startItem.setEnabled(true);
        restartItem.setEnabled(true);
        exitItem.setEnabled(true);

        startItem.addActionListener(e -> {
            timer.start();
            beforeStartDrawing = false;
        });

        restartItem.addActionListener(e -> {
            beforeStartDrawing = true;
            repaint();
            timer.stop();
            table.clear();
            makeTable();
        });

        addExitItemListener(mainPanel, menuBar, startItem, restartItem, exitItem);

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
    }

    /**
     * Alap tábla csak halott sejtekkel
     */
    @Override
    void makeTable(){
        for (int i = 0; i < row; i++) {
            ArrayList<Cell> Row = new ArrayList<>();
            for (int j = 0; j < column; j++) {
                Row.add(new Cell(false, false));
            }
            table.add(Row);
        }
    }

    /**
     * Létrehoz egy sejtet adott koordinátákon
     * @param x Létrehozandó sejt x koordinátája
     * @param y Létrehozandó sejt y koordinátája
     */
    private void createCell(int x, int y) {
        if (0 <= x && x < column && 0 <= y && y < row) {
            if (!table.get(y).get(x).getStatus()) {
                table.get(y).get(x).setStatus(true);
                if (beforeStartDrawing) {
                    repaint();
                }
            }
        }
    }

    /**
     * Megszüntet egy sejtet az adott koordinátákon
     * @param x Megszüntetendő sejt x koordinátája
     * @param y Megszüntetendő sejt y koordinátája
     */
    private void destroyCell(int x, int y) {
        if (0 <= x && x < column && 0 <= y && y < row) {
            if (table.get(y).get(x).getStatus()) {
                table.get(y).get(x).setStatus(false);
                if (beforeStartDrawing) {
                    repaint();
                }
            }
        }
    }

    /**
     * Bal klikkre létrehoz, jobb klikkre megszüntet egy sejtet
     * @param e Kattintás esemény
     */
    private void panelMousePressed(MouseEvent e) {
        x = e.getX() / data.getCellSize();
        y = e.getY() / data.getCellSize();
        if (SwingUtilities.isLeftMouseButton(e)) {
            createCell(x, y);
        } else if (SwingUtilities.isRightMouseButton(e)){
            destroyCell(x, y);
        }
    }

    /**
     * Bal egérhúzásra létrehoz, jobb egérhúzásra megszüntet sejteket
     * @param e Húzás esemény
     */
    private void panelMouseDragged(MouseEvent e) {
        x = e.getX() / data.getCellSize();
        y = e.getY() / data.getCellSize();
        if (SwingUtilities.isLeftMouseButton(e)) {
            createCell(x, y);
        } else if (SwingUtilities.isRightMouseButton(e)){
            destroyCell(x, y);
        }
    }

    /**
     * Minden egyes iterációban (timer jelzéseire) meghívja a rajzoló függvényeket miután elindult a játék
     * @param g amire rajzol
     */
    @Override
    void paintScreen(Graphics g) {
        if (beforeStartDrawing) {
            for ( int i = 0; i < row; i++){
                for ( int j = 0; j < column; j++){
                    if(table.get(i).get(j).getStatus()){
                        g.fillRect(j * data.getCellSize() + 1, i * data.getCellSize() + 1, data.getCellSize() - 1, data.getCellSize() - 1);
                    }
                }
            }
        } else {
            cellDrawer(g);
            GameAlgorithm();
        }
        gridDrawer(g);
    }
}
