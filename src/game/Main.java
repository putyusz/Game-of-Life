package game;

import javax.swing.*;

/**
 * Main class
 */
public class Main {
    /**
     * Létrehozza a főmenüt és beállítja a Look And Feel-t
     * @param args parancssori argumentumokat lehet megadni, nem használt
     */
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        }

        new MainForm();
    }
}