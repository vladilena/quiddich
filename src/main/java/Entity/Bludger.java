package Entity;

import javax.swing.*;
import java.awt.*;

public class Bludger  {
    /**
     * params of bludger image
     */
    int imageWeight = 55;
    int imageHeight = 53;
    int x;
    int y;
    int speed;
    /**
     * Open an image
     */
    Image img = new ImageIcon("src\\main\\resources\\bludger.png").getImage();
    Road road;
    /**
     * Creating a rectangle for collisium method
     */
    public Rectangle getRect() {
        return new Rectangle(x, y, imageWeight-20, imageHeight-20);
    }

    public Bludger(int x, int y, int speed, Road road) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.road = road;

    }
    /**
     * Moving method
     */
    public void move() {
        x = x - road.p.speed + speed;
    }
}


