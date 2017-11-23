package game;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;


import static game.MainForm.data;
import static game.SettingsData.Shape.*;

class Cell {
    private boolean status, nextStatus;

    private int R, G, B;

    private Color randomColor;

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

    void live(Cell c, int count) {
        if (c.getStatus()) {
            if (count == 2 || count == 3) {
                c.setNextStatus(true);
            } else {
                c.setNextStatus(false);
            }
        } else {
            if (count == 3) {
                c.setNextStatus(true);
            } else {
                c.setNextStatus(false);
            }
        }
    }

    void DrawCell(int x, int y, Graphics g) {
        if (data.isCrazyRainbow()) {
            R = (int) (Math.random() * 256);
            G = (int) (Math.random() * 256);
            B = (int) (Math.random() * 256);

            randomColor = new Color(R, G, B);
        }
        g.setColor(randomColor);
        if (data.getShape() == CIRCLE) {
            g.fillRoundRect(x + 1, y + 1, data.getCellSize() - 1, data.getCellSize() - 1, data.getCellSize() - 1, data.getCellSize() - 1);
        } else if (data.getShape() == SQUARE) {
            g.fillRect(x + 1, y + 1, data.getCellSize() - 1,data.getCellSize() - 1);
        }
    }
    void DrawCell(int x, int y, Graphics g, Color c) {
        g.setColor(c);

        if (data.getShape() == CIRCLE) {
            g.fillRoundRect(x + 1, y + 1, data.getCellSize() - 1, data.getCellSize() - 1, data.getCellSize() - 1, data.getCellSize() - 1);
        } else if (data.getShape() == SQUARE) {
            g.fillRect(x + 1, y + 1, data.getCellSize() - 1,data.getCellSize() - 1);
        }
    }

    void DrawCell(int x, int y, Graphics g, ImageIcon img) {
        g.drawImage(img.getImage(), x + 1, y + 1, null);
    }

}
