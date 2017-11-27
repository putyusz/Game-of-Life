package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import static game.MainForm.data;
import static game.MainForm.mainFrame;
import static game.SettingsData.DrawMode.*;

/**
 * A sima játék, ekkor a felhasználónak nincs beavatkozása, csak elindítani és újraindítani tudja a játékot, utóbbi esetben új tábla generálódik
 */
public class NormalGame extends JPanel implements ActionListener {

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();
    int row, column;

    ArrayList<ArrayList<Cell>> table;

    private ImageIcon ghost;

    Timer timer;

    /** NormalGame
     * Konstruktor ami létrehozza az alap táblát és megcsinálja a grafikus felületet
     * @param mainPanel a főpanel amire rajzol
     * @param menuBar innen érhetőek el a kilépés, kezdés, újrakezdés, csak játék alatt látszik
     * @param startItem a játék elindítása
     * @param restartItem a játék újraindítása újragenerált táblával
     * @param exitItem kilépés a játékból
     */
    NormalGame(JPanel mainPanel, JMenuBar menuBar, JMenuItem startItem, JMenuItem restartItem, JMenuItem exitItem) {
        table = new ArrayList<>();
        ghost = new ImageIcon("assets/Ghost.png");
        row = (height - 20) / data.getCellSize();
        column = width / data.getCellSize();
        timer = new Timer(data.getDelay() / 60, this);

        setBackground(new Color(69, 69, 69));
        setLayout(null);

        makeTable();

        makeGUIForGame(mainPanel, menuBar, startItem, restartItem, exitItem);
    }

    /**
     * A NormalGame felhasználói felületét csinálja meg
     * paraméterekhez:
     * @see NormalGame
     */
    void makeGUIForGame(JPanel mainPanel, JMenuBar menuBar, JMenuItem startItem, JMenuItem restartItem, JMenuItem exitItem) {
        menuBar.setVisible(true);

        startItem.setEnabled(true);
        restartItem.setEnabled(true);
        exitItem.setEnabled(true);

        startItem.addActionListener(e -> timer.start());

        restartItem.addActionListener(e -> {
            repaint();
            timer.stop();
            table.clear();
            makeTable();
            timer.start();
        });

       addExitItemListener(mainPanel, menuBar, startItem, restartItem, exitItem);
    }

    /**
     * Az Exit menüponthoz csinálja meg a listener-t
     * paraméterekhez:
     * @see NormalGame
     */
    void addExitItemListener(JPanel mainPanel, JMenuBar menuBar, JMenuItem startItem, JMenuItem restartItem, JMenuItem exitItem){
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
    }

    /**
     * Alap tábla véletlenszerűen elhelyezett sejtekkel
     */
    void makeTable(){
        for (int i = 0; i < row; i++) {
            ArrayList<Cell> tableRow = new ArrayList<>();
            for (int j = 0; j < column; j++) {
                Random rand = new Random();
                int k = rand.nextInt(100);
                if (k < data.getPopulation()) {
                    tableRow.add(new Cell(true, false));
                } else {
                    tableRow.add(new Cell(false, false));
                }
            }
            table.add(tableRow);
        }
    }

    /**
     * A játék fő algoritmusa ami végignéz a táblában minden cellát
     */
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


    /**
     * Megrajzolja a cellákat a jelenlegi állapot szerint
     * @param g amire rajzol
     */
    void cellDrawer(Graphics g) {
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
                        table.get(i).get(j).DrawCell(x, y, g, data.getColor());
                    }
                }
                x += data.getCellSize();
            }
            x = 0;
            y += data.getCellSize();
        }
    }

    /**
     * Segédfüggvény a kirajzoláshoz a rácshálót csinálja meg
     * @param g amire rajzol
     */
    void gridDrawer(Graphics g) {
        int m = 0, n = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                g.setColor(new Color(49, 49, 49));
                g.drawLine(m, 0, m, height);
                g.drawLine(0, n, width, n);
                m += data.getCellSize();
                n += data.getCellSize();
            }
        }
    }

    /**
     * Minden egyes iterációban (timer jelzéseire) meghívja a rajzoló függvényeket és az algoritmust
     * @param g amire rajzol
     */
    void paintScreen(Graphics g) {
        cellDrawer(g);
        GameAlgorithm();
        gridDrawer(g);
    }

    /**
     * Az ősosztály (JPanel) paintComponent függvényét definiálja felül, a rajzoló függvényeket hívja meg
     * @param g amire rajzol
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintScreen(g);
    }

    /**
     * Minden egyes timer tick-nél újrarajzolja a képernyőt
     * @param s a timer ActionEvent-je
     */
    @Override
    public void actionPerformed(ActionEvent s) {
        if (s.getSource() == timer) {
            repaint();
        }
    }
}