package Entity;

import javax.swing.*;
import java.awt.*;

public class Snitch {
    int imageWeight = 70;
    int imageHeight = 27;
    int x;
    int y;
    int speed;
    Image img = new ImageIcon("src\\main\\resources\\snitch.png").getImage();
    Road road;

    public Rectangle getRect() {
        return new Rectangle(x, y, imageWeight, imageHeight);
    }

    public Snitch(int x, int y, int speed, Road road) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.road = road;

    }

    public void move() {
        x = x - road.p.speed + speed;
    }
}



