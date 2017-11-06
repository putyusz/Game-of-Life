/*
 * Created by Ponekker Patrik on 29/10/17
 * Copyright (c) 2017.
 */

package game;

import java.io.*;

class SettingsData implements Serializable {
    private static long serialVersionUID = -1;

    public enum Color {
        BLUE, CYAN, GREEN, YELLOW, RED
    }

    public enum GameMode {
        NORMAL, DRAW
    }

    public enum Shape {
        CIRCLE, SQUARE
    }

    public enum DrawMode {

    }

    private int whichDrawMode = 1;
    private Color color = Color.BLUE;
    private GameMode gameMode = GameMode.NORMAL;
    private int whichWasLast = 1;
    private int population = 2500;
    private int delay = 5000;
    private Shape shape = Shape.CIRCLE;
    private boolean crazyRainbow = false;

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

    int getWhichWasLast() {
        return whichWasLast;
    }

    void setWhichWasLast(int whichWasLast) {
        this.whichWasLast = whichWasLast;
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

    int getWhichDrawMode() {
        return whichDrawMode;
    }

    void setWhichDrawMode(int whichDrawMode) {
        this.whichDrawMode = whichDrawMode;
    }

    Shape getShape() {
        return shape;
    }

    void setShape(Shape shape) {
        this.shape = shape;
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
            FileInputStream f = new FileInputStream(settingsFile);
            ObjectInputStream in = new ObjectInputStream(f);
            data = (SettingsData) in.readObject();
            in.close();
            setShape(data.getShape());
            setDelay(data.getDelay());
            setCrazyRainbow(data.isCrazyRainbow());
            setPopulation(data.getPopulation());
            setWhichWasLast(data.getWhichWasLast());
            setGameMode(data.getGameMode());
            setWhichDrawMode(data.getWhichDrawMode());
            setColor(data.getColor());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}

