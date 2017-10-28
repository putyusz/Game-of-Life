/*
 * Created by Ponekker Patrik on 12/10/17
 * Copyright (c) 2017.
 */

package game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static game.MainForm.data;

class Cell {
    private boolean status, nextstatus;

    private int R = (int) (Math.random() * 256);
    private int G = (int) (Math.random() * 256);
    private int B = (int) (Math.random() * 256);

    Cell(boolean s, boolean ns) {
        status = s;
        nextstatus = ns;
    }

    Cell() {
        status = false;
        nextstatus = false;
    }

    boolean getStatus() {
        return status;
    }

    void setStatus(boolean s) {
        status = s;
    }

    private boolean getNextstatus() {
        return nextstatus;
    }

    private void setNextstatus(boolean ns) {
        nextstatus = ns;
    }

    private void live(Cell c, int count) {
        if (c.getStatus()) {
            if (count == 2 || count == 3) {
                c.setNextstatus(true);
            } else {
                c.setNextstatus(false);
            }
        } else {
            if (count == 3) {
                c.setNextstatus(true);
            } else {
                c.setNextstatus(false);
            }
        }
    }

    private void DrawCell(int x, int y, Graphics g) {
        Color randomColor;

        if (data.isCrazyRainbow()) {
            R = (int) (Math.random() * 256);
            G = (int) (Math.random() * 256);
            B = (int) (Math.random() * 256);

            randomColor = new Color(R, G, B);
        } else {
            randomColor = new Color(R, G, B);
        }
        g.setColor(randomColor);
        if (data.getWhichShape() == 1) {
            g.fillRoundRect(x + 1, y + 1, 8, 8, 8, 8);
        } else if (data.getWhichShape() == 2) {
            g.fillRect(x + 1, y + 1, 8,8);
        }
    }

    void DrawCell(int x, int y, Graphics g, Color c) {
        g.setColor(c);

        if (data.getWhichShape() == 1) {
            g.fillRoundRect(x + 1, y + 1, 8, 8, 8, 8);
        } else if (data.getWhichShape() == 2) {
            g.fillRect(x + 1, y + 1, 8,8);
        }
    }
    private void DrawCell(int x, int y, Graphics g, ImageIcon img) {
        g.drawImage(img.getImage(), x, y, null);
    }

    ArrayList<ArrayList<Cell>> GameAlgorithm(int s, int o, ArrayList<ArrayList<Cell>> Table) {
        //jatek

        //sarkok
        //jobbfelso
        int ccount = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (Table.get(i).get(j).getStatus() && (i != 0 || j != 0)) {
                    ccount++;
                }
            }
        }
        Table.get(0).get(0).live(Table.get(0).get(0), ccount);
        ccount = 0;

        //jobbalso
        for (int i = s - 2; i < s; i++) {
            for (int j = 0; j < 2; j++) {
                if (Table.get(i).get(j).getStatus() && (i != s - 1 || j != 0)) {
                    ccount++;
                }
            }
        }
        Table.get(s - 1).get(0).live(Table.get(s - 1).get(0), ccount);
        ccount = 0;

        //balfelso
        for (int i = 0; i < 2; i++) {
            for (int j = o - 2; j < o; j++) {
                if (Table.get(i).get(j).getStatus() && (i != 0 || j != o - 1)) {
                    ccount++;
                }
            }
        }
        Table.get(0).get(o - 1).live(Table.get(0).get(o - 1), ccount);
        ccount = 0;

        //balalso
        for (int i = s - 2; i < s; i++) {
            for (int j = o - 2; j < o; j++) {
                if (Table.get(i).get(j).getStatus() && (i != s - 1 || j != o - 1)) {
                    ccount++;
                }
            }
        }
        Table.get(s - 1).get(o - 1).live(Table.get(s - 1).get(o - 1), ccount);

        //szelek
        // 1. sor
        for (int j = 1; j < o - 1; j++) {
            int count = 0;
            for (int l = j - 1; l <= j + 1; l++) {
                if (Table.get(0).get(l).getStatus() && l != j) {
                    count++;
                }
                if (Table.get(1).get(l).getStatus()) {
                    count++;
                }
            }
            Table.get(0).get(j).live(Table.get(0).get(j), count);
        }

        //1. oszlop
        for (int i = 1; i < s - 1; i++) {
            int count = 0;
            for (int l = i - 1; l <= i + 1; l++) {
                if (Table.get(l).get(0).getStatus() && l != i) {
                    count++;
                }
                if (Table.get(l).get(1).getStatus()) {
                    count++;
                }
            }
            Table.get(i).get(0).live(Table.get(i).get(0), count);
        }

        //s. sor
        for (int j = 1; j < o - 1; j++) {
            int count = 0;
            for (int l = j - 1; l <= j + 1; l++) {
                if (Table.get(s - 2).get(l).getStatus()) {
                    count++;
                }
                if (Table.get(s - 1).get(l).getStatus() && l != j) {
                    count++;
                }
            }
            Table.get(s - 1).get(j).live(Table.get(s - 1).get(j), count);
        }

        //o. oszlop
        for (int i = 1; i < s - 1; i++) {
            int count = 0;
            for (int l = i - 1; l <= i + 1; l++) {
                if (Table.get(l).get(o - 2).getStatus()) {
                    count++;
                }
                if (Table.get(l).get(o - 1).getStatus() && l != i) {
                    count++;
                }
            }
            Table.get(i).get(o - 1).live(Table.get(i).get(o - 1), count);
        }

        //kozepe
        for (int i = 1; i < s - 1; i++) {
            for (int j = 1; j < o - 1; j++) {
                int count = 0;
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        if (Table.get(k).get(l).getStatus() && (k != i || l != j)) {
                            count++;
                        }
                    }
                }
                Table.get(i).get(j).live(Table.get(i).get(j), count);
            }
        }

        //feluliras
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < o; j++) {
                Table.get(i).get(j).setStatus(Table.get(i).get(j).getNextstatus());
            }
        }

        return Table;
    }

    void screenDrawer(int s, int o, Graphics g, ArrayList<ArrayList<Cell>> Table, ImageIcon Ghost) {
        //iteraciok
        //kirajzolas

        int x = 0, y = 0;
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < o; j++) {
                if (Table.get(i).get(j).getStatus()) {
                    if (data.getWhichDrawMode() == 1) {
                        Table.get(i).get(j).DrawCell(x, y, g);
                    } else if (data.getWhichDrawMode() == 2) {
                        Table.get(i).get(j).DrawCell(x, y, g, Ghost);
                    } else if (data.getWhichDrawMode() == 3) {
                        switch (data.getWhichColor()) {
                            case 1:
                                Table.get(i).get(j).DrawCell(x, y, g, Color.BLUE);
                                break;
                            case 2:
                                Table.get(i).get(j).DrawCell(x, y, g, Color.CYAN);
                                break;
                            case 3:
                                Table.get(i).get(j).DrawCell(x, y, g, Color.GREEN);
                                break;
                            case 4:
                                Table.get(i).get(j).DrawCell(x, y, g, Color.YELLOW);
                                break;
                            case 5:
                                Table.get(i).get(j).DrawCell(x, y, g, Color.RED);
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

    void gridDrawer(Graphics g, int s, int o, int height, int width) {
        int m = 0, n = 0;
        for (int i = 0; i < s; i++){
            for (int j = 0; j < o; j++){
                g.setColor(new Color(49,49,49));
                g.drawLine(m, 0, m, height);
                g.drawLine(0, n, width,  n);
                m += 10;
                n += 10;
            }
        }
    }
}
