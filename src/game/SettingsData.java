package game;

import java.awt.*;
import java.io.*;

/**
 *A beállítások értékeit tárolja és fájlba kiírja
 */
class SettingsData implements Serializable {
    private static long serialVersionUID = -1;

    /**
     *A két játékmódot reprezentálja
     */
    public enum GameMode {
        NORMAL, DRAW
    }

    /**
     * A sejtek alakja
     */
    public enum Shape {
        CIRCLE, SQUARE
    }

    /**
     * Milyen módon rajzolja ki a sejteket
     */
    public enum DrawMode {
        RAINBOW, CRAZY_RAINBOW, GHOST, COLORED
    }

    private DrawMode drawMode;
    private Color color;
    private GameMode gameMode;
    private int population;
    private int delay;
    private Shape shape;
    private boolean crazyRainbow;
    private int cellSize;

    private String settingsPath = System.getenv("APPDATA");
    private File settingsFile = new File(settingsPath,"settings").getAbsoluteFile();

    /**
     *Az alapértelmezett értékeket állítja be a beállításoknak, ha van fájl amiből beolvas felülíródnak
     */
    SettingsData() {
        drawMode = DrawMode.COLORED;
        color = new Color(69,69,69);
        gameMode = GameMode.NORMAL;
        population = 25;
        delay = 5000;
        shape = Shape.CIRCLE;
        crazyRainbow = false;
        cellSize = 10;
    }

    boolean isCrazyRainbow() {
        return crazyRainbow;
    }

    void setCrazyRainbow(boolean crazyRainbow) {
        this.crazyRainbow = crazyRainbow;
    }

    int getDelay() {
        return delay;
    }

    void setDelay(int delay) {
        this.delay = delay;
    }

    int getPopulation() {
        return population;
    }

    void setPopulation(int population) {
        this.population = population;
    }

    GameMode getGameMode() {
        return gameMode;
    }

    void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    Color getColor() {
        return color;
    }

    void setColor(Color color) {
        this.color = color;
    }

    DrawMode getDrawMode() {
        return drawMode;
    }

    void setDrawMode(DrawMode drawMode) {
        this.drawMode = drawMode;
    }

    Shape getShape() {
        return shape;
    }

    void setShape(Shape shape) {
        this.shape = shape;
    }

    int getCellSize() {
        return cellSize;
    }

    void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    /**
     *A beállítások értékeit írja ki fájlba
     */
    void writeSettings() {
        try {
            FileOutputStream f = new FileOutputStream(settingsFile);
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(this);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A beállítások értékeit olvassa be fájlból
     */
    void readSettings() {
        SettingsData data = new SettingsData();
        try {
            if(settingsFile.exists()) {
                FileInputStream f = new FileInputStream(settingsFile);
                ObjectInputStream in = new ObjectInputStream(f);
                data = (SettingsData) in.readObject();
                in.close();
            }
            this.setShape(data.getShape());
            this.setDelay(data.getDelay());
            this.setCrazyRainbow(data.isCrazyRainbow());
            this.setPopulation(data.getPopulation());
            this.setGameMode(data.getGameMode());
            this.setDrawMode(data.getDrawMode());
            this.setColor(data.getColor());
            this.setCellSize(data.getCellSize());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

