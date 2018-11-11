package FrameworkSet;

import Entity.Road;

import javax.swing.*;


public class Frameworkset {
    public void frame() {

        JFrame f = new JFrame("Quiddich 2D");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1100, 640);
        f.add(new Road());
        f.setVisible(true);
    }
}
