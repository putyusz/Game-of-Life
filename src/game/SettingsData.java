/*
 * Created by Ponekker Patrik on $today.year.month.day
 * Copyright (c) 2017.
 */

package game;

import java.io.*;

public class SettingsData implements Serializable {
    private int whichDrawMode = 1; //1
    private int whichColor = 1;//1
    private int whichGameMode = 1;//1
    private int whichWasLast = 1;//1
    private int population = 2500;//2500
    private int delay = 5000;//5000
    private boolean crazyRainbow = false;

    public SettingsData(int whichDrawMode, int whichColor, int whichGameMode, int whichWasLast, int population, int delay, boolean crazyRainbow){
        this.whichDrawMode = whichDrawMode;
        this.whichColor = whichColor;
        this.whichGameMode = whichGameMode;
        this.whichWasLast = whichWasLast;
        this.population = population;
        this.delay = delay;
        this.crazyRainbow = crazyRainbow;
    }

    public SettingsData() {}

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

    int getWhichGameMode() {
        return whichGameMode;
    }

    void setWhichGameMode(int whichGameMode) {
        this.whichGameMode = whichGameMode;
    }

    int getWhichColor() {
        return whichColor;
    }

    void setWhichColor(int whichColor) {
        this.whichColor = whichColor;
    }

    int getWhichDrawMode() {
        return whichDrawMode;
    }

    void setWhichDrawMode(int whichDrawMode) {
        this.whichDrawMode = whichDrawMode;
    }

    void writeSettings(SettingsData data) {
        try {
            FileOutputStream f = new FileOutputStream("settings");
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
            FileInputStream f = new FileInputStream("settings");
            ObjectInputStream in = new ObjectInputStream(f);
            data = (SettingsData) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}

