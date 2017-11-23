package game;

import java.awt.*;
import java.io.*;

class SettingsData implements Serializable {
    private static long serialVersionUID = -1;

    public enum GameMode {
        NORMAL, DRAW
    }

    public enum Shape {
        CIRCLE, SQUARE
    }

    public enum DrawMode {
        RAINBOW, CRAZY_RAINBOW, GHOST, COLORED
    }

    private DrawMode drawMode = DrawMode.RAINBOW;
    private Color color = Color.BLUE;
    private GameMode gameMode = GameMode.NORMAL;
    private int population = 25;
    private int delay = 5000;
    private Shape shape = Shape.CIRCLE;
    private boolean crazyRainbow = false;
    private int cellSize = 10;

    private String settingsPath = System.getenv("APPDATA");
    private File settingsFile = new File(settingsPath,"settings").getAbsoluteFile();


    SettingsData() {

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


    void writeSettings(SettingsData data) {
        try {
            FileOutputStream f = new FileOutputStream(settingsFile);
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(data);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    SettingsData readSettings() {
        SettingsData data = new SettingsData();
        try {
            if(settingsFile.exists()) {
                FileInputStream f = new FileInputStream(settingsFile);
                ObjectInputStream in = new ObjectInputStream(f);
                data = (SettingsData) in.readObject();
                in.close();
            }
            setShape(data.getShape());
            setDelay(data.getDelay());
            setCrazyRainbow(data.isCrazyRainbow());
            setPopulation(data.getPopulation());
            setGameMode(data.getGameMode());
            setDrawMode(data.getDrawMode());
            setColor(data.getColor());
            setCellSize(data.getCellSize());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}

