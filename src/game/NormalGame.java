/*
 * Created by Ponekker Patrik on 12/10/17
 * Copyright (c) 2017.
 */

package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class NormalGame extends JPanel implements ActionListener {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();
    private int s = height / 10, o = width / 10;
    private ArrayList<ArrayList<Cell>> Table = new ArrayList<>();

    private Timer timer = new Timer(Settings.delay / 60, this);

    private URL ghostURL = NormalGame.class.getResource("Ghost.jpg");
    private ImageIcon Ghost = new ImageIcon(ghostURL);

    ArrayList<Integer> ints;

    NormalGame(JPanel mainPanel) {
        JButton exitButton = new JButton("EXIT");
        exitButton.setForeground(new Color(255, 255, 255));
        exitButton.setBackground(new Color(49, 49, 49));
        exitButton.setSize(50, 30);
        exitButton.setBounds(0, 0, 70, 30);

        setPreferredSize(new Dimension(960, 540));
        setBackground(new Color(69, 69, 69));
        setLayout(null);
        add(exitButton);

        timer.start();

        //alap tabla
        for (int i = 0; i < s; i++) {
            ArrayList<Cell> Row = new ArrayList<>();
            for (int j = 0; j < o; j++) {
                Random rand = new Random();
                int k = rand.nextInt(10000);
                if (k < Settings.population) {
                    Row.add(new Cell(true, false));
                } else {
                    Row.add(new Cell(false, false));
                }
            }
            Table.add(Row);
        }

        exitButton.addActionListener(e -> {
            MainForm.mainFrame.getContentPane().removeAll();
            MainForm.mainFrame.getContentPane().repaint();
            MainForm.mainFrame.add(mainPanel);
            MainForm.mainFrame.validate();
            timer.stop();
        });

//        ArrayList<Integer> ints = new ArrayList<>();
//        for (int i = 0; i < width; i++){
//            int n = (int) (Math.random() * 1000);
//            ints.add(n);
//        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Cell c = new Cell();
        c.screenDrawer(s, o, g, Table, Ghost);
        Table = c.GameAlgorithm(s, o, Table);

        c.gridDrawer(g,s,o,height,width);


//        for (int i = 0; i < width; i++){
//            int R = (int) (Math.random() * 256);
//            int G = (int) (Math.random() * 256);
//            int B = (int) (Math.random() * 256);
//            g.setColor(new Color(R,G,B));
//            g.drawLine(i,ints.get(i),i,height);
//       }
    }

    @Override
    public void actionPerformed(ActionEvent s) {
        if (s.getSource() == timer) {
            repaint();
        }
    }
}