package game;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;


import static game.MainForm.data;
import static game.SettingsData.Shape.*;

/**
 * A sejtet megvalósító osztály
 */
class Cell {
    private boolean status, nextStatus;

    private int R, G, B;

    private Color randomColor;

    /**
     * Konstruktor
     * @param status A sejt jelenlegi állapota (él vagy halott)
     * @param nextStatus A sejt következő állapota ( meg fog halni vagy él tovább)
     */
    Cell(boolean status, boolean nextStatus) {
        this.status = status;
        this.nextStatus = nextStatus;

        R = (int) (Math.random() * 256);
        G = (int) (Math.random() * 256);
        B = (int) (Math.random() * 256);
        randomColor = new Color(R, G, B);
    }

    boolean getStatus() {
        return status;
    }

    void setStatus(boolean status) {
        this.status = status;
    }

    boolean getNextStatus() {
        return nextStatus;
    }

    private void setNextStatus(boolean nextStatus) {
        this.nextStatus = nextStatus;
    }

    /**
     * Függvény ami eldönti egy sejtről, hogy az életben marad-e.
     * @param cell A sejt amiről el kell dönteni, hogy életben marad-e.
     * @param count Hány szomszédja van az adott sejtnek
     */
    void live(Cell cell, int count) {
        if (cell.getStatus()) {
            if (count == 2 || count == 3) {
                cell.setNextStatus(true);
            } else {
                cell.setNextStatus(false);
            }
        } else {
            if (count == 3) {
                cell.setNextStatus(true);
            } else {
                cell.setNextStatus(false);
            }
        }
    }

    /**
     * Sejtet kirajzoló függvény
     * @param x A sejt x koordinátája
     * @param y A sejt y koordinátája
     * @param g Amire rajzol
     */
    void DrawCell(int x, int y, Graphics g) {
        if (data.isCrazyRainbow()) {
            R = (int) (Math.random() * 256);
            G = (int) (Math.random() * 256);
            B = (int) (Math.random() * 256);

            randomColor = new Color(R, G, B);
        }
        g.setColor(randomColor);
        if (data.getShape() == CIRCLE) {
            g.fillRoundRect(x, y, data.getCellSize(), data.getCellSize(), data.getCellSize(), data.getCellSize());
        } else if (data.getShape() == SQUARE) {
            g.fillRect(x + 1, y + 1, data.getCellSize() - 1,data.getCellSize() - 1);
        }
    }

    /**
     * Sejtet kirajzoló függvény
     * @param x A sejt x koordinátája
     * @param y A sejt y koordinátája
     * @param g Amire rajzol
     * @param color Ilyen színű lesz a sejt
     */
    void DrawCell(int x, int y, Graphics g, Color color) {
        g.setColor(color);

        if (data.getShape() == CIRCLE) {
            g.fillRoundRect(x, y, data.getCellSize(), data.getCellSize(), data.getCellSize(), data.getCellSize());
        } else if (data.getShape() == SQUARE) {
            g.fillRect(x + 1, y + 1, data.getCellSize() - 1,data.getCellSize() - 1);
        }
    }

    /**
     * Sejtet kirajzoló függvény
     * @param x A sejt x koordiánátja
     * @param y A sejt y koordinátája
     * @param g Amire Rajzol
     * @param img Képe a sejtnek
     */
    void DrawCell(int x, int y, Graphics g, ImageIcon img) {
        g.drawImage(img.getImage(), x + 1, y + 1, null);
    }

}
