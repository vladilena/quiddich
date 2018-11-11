package Entity;

import javax.swing.*;
import java.awt.*;

public class Enemy {
    /**
     * params of enemy image
     */
    int imageWeight = 200;
    int imageHeight = 133;
    int x;
    int y;
    int speed;

    /**
     * Open an image
     */

    Image img = new ImageIcon("src\\main\\resources\\enemy.png").getImage();
Road road;


public Enemy (int x, int y, int speed, Road road){
    this.x = x;
    this.y = y;
    this.speed = speed;
    this.road = road;

}
    /**
     * Moving method
     */
public void move (){
    x = x - road.p.speed + speed;
}
}
