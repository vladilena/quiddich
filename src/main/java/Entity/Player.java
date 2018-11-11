package Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    /**
     * params of player image
     */
    int imageWeight = 200;
    int imageHeight = 133;
    public static final int MAX_SPEED = 30;
    public static final int MAX_TOP = 10;
    public static final int MAX_BOTTOM = 550;

    /**
     * Open images
     */
    Image img_c = new ImageIcon("src\\main\\resources\\player.png").getImage();
    Image img_up = new ImageIcon("src\\main\\resources\\player_up.png").getImage();
    Image img_down = new ImageIcon("src\\main\\resources\\player_down.png").getImage();

    Image img = img_c;

    /**
     * Creating a rectangle for collisium method
     */
    public Rectangle getRect() {
        return new Rectangle(x,y,(imageWeight-50),(imageHeight-50));
   }

    int speed = 0;
    int acceleration = 0;
    int track = 0;
    /**
     * Coordinates at start
     */
    int x = 30;
    int y = 100;
    int dy = 0;
    /**
     * Background moving logic
     */
    int layer1 = 0;
    int layer2 = 2400;

    /**
     * Player moving logic. Set maximum and minimum speed. Set maximum and minimem height.
     */
    public void move() {
        track += speed;
        speed += acceleration;
        if (speed <= 0) {
        speed =0;
        }
        if (speed >= MAX_SPEED) {
            speed = MAX_SPEED;
        }
        y -= dy;
        if (y <=MAX_TOP) {
            y = MAX_TOP;
        }
        if (y >=MAX_BOTTOM){
            y = MAX_BOTTOM;
        }
        if (layer2 - speed <= 0) {
            layer1 = 0;
            layer2 = 2400;
        } else {

            layer1 -= speed;
            layer2 -= speed;
        }
    }

    /**
     * Keypressed logic.
     * And changing player's images
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            acceleration = 1;
        }
        if (key == KeyEvent.VK_LEFT) {
            acceleration = -1;
        }
        if (key == KeyEvent.VK_UP) {
            dy = 3;
            img = img_up;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = -3;
            img = img_down;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
            acceleration = 0;
            img = img_c;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            dy = 0;
            img = img_c;
        }
    }
}
