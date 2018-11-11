package Entity;

import Audio.AudioThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class Road extends JPanel implements ActionListener, Runnable {
    int counter;
    /**
     * Update the game every 20 millis
     */
    Timer mainTimer = new Timer(20, this);

    /**
     * Set background image
     */
    Image img = new ImageIcon("src\\main\\resources\\quidditch.png").getImage();
    /**
     * Create player object
     */
    Player p = new Player();

    /**
     * Create threads for each Class Factory
     */
    Thread bludgerFactory = new Thread(this);
    List<Bludger> bludgers = new ArrayList<>();

    Thread snitchFactory = new Thread(this);
    List<Snitch> snitches = new ArrayList<>();

    Thread enemiesFactory = new Thread(this);
    List<Enemy> enemies = new ArrayList<>();

    Thread audioThread = new Thread(new AudioThread());

    /**
     * Constructor which starts each thread
     */
    public Road() {
        mainTimer.start();
        bludgerFactory.start();
        snitchFactory.start();
        enemiesFactory.start();
        audioThread.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);

    }
    /**
     * Implements Runnable method run().
     * Add objects with random starting positions and speeds.
     */
    @Override
    public void run() {
        while (true) {
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(3000));
                bludgers.add(new Bludger(1400, rand.nextInt(600), rand.nextInt(20), this));

                Thread.sleep(rand.nextInt(1000));
                snitches.add(new Snitch(1200, rand.nextInt(550), rand.nextInt(28), this));

                Thread.sleep(rand.nextInt(10000));
                enemies.add(new Enemy(2500, rand.nextInt(600), rand.nextInt(25), this));


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Creating a custom KeyAdapter
     */
    private class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            p.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            p.keyReleased(e);
        }
    }

    /**
     * Paint images of background and player
     */
    public void paint(Graphics g) {
        int limit = 4800;
        g.drawImage(img, p.layer1, 0, null);
        g.drawImage(img, p.layer2, 0, null);
        g.drawImage(p.img, p.x, p.y, null);
/**
 * Create a string with counter
 */
        g.setColor(Color.ORANGE);
        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        g.drawString("You catch " + counter + "/20 golden snitches.", 100, 30);
/**
 * Draw bludgers and delete unusable
 */
        Iterator<Bludger> i = bludgers.iterator();
        while (i.hasNext()) {
            Bludger b = i.next();
            if (b.x >= limit || b.x <= -limit) {
                i.remove();
            } else
                b.move();
            g.drawImage(b.img, b.x, b.y, null);
        }
        /**
         * Draw snitches and delete unusable
         */
        Iterator<Snitch> s = snitches.iterator();
        while (s.hasNext()) {
            Snitch sn = s.next();
            if (sn.x >= limit || sn.x <= -limit) {
                s.remove();
            } else {
                sn.move();
                g.drawImage(sn.img, sn.x, sn.y, null);
            }
        }
        /**
         * Draw enemies and delete unusable
         */
        Iterator<Enemy> e = enemies.iterator();
        while (e.hasNext()) {
            Enemy en = e.next();
            if (en.x >= limit || en.x <= -limit) {
                e.remove();
            } else {
                en.move();
                g.drawImage(en.img, en.x, en.y, null);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        p.move();
        repaint();
        testCollisionWithBludgers();
        testCollisionWithSnitches();
    }

    private void testCollisionWithSnitches() {
        Iterator<Snitch> s = snitches.iterator();
        while (s.hasNext()) {
            Snitch sn = s.next();
            if (sn.getRect().intersects(p.getRect())) {
                s.remove();
                counter++;
                if (counter == 20) {
                    JOptionPane.showMessageDialog(null, "You are win!!");
                    System.exit(0);
                }
            }
        }
    }

    private void testCollisionWithBludgers() {
        Iterator<Bludger> i = bludgers.iterator();
        while (i.hasNext()) {
            Bludger b = i.next();
            if (b.getRect().intersects(p.getRect())) {
                JOptionPane.showMessageDialog(null, "Sorry, You must go to the hospital wing!");
                System.exit(1);
            }
        }

    }

//    public void loserTimer() {
//        final long startTime = System.currentTimeMillis();
//        long loseTime = startTime + 30000;
//        timer = 30;
//        while (true) {
//            if (System.currentTimeMillis() == loseTime) {
//                JOptionPane.showMessageDialog(null, "Time is over");
//                System.exit(0);
//            }else {
//                timer = (int) (((startTime-loseTime)/1000)*(-1));
//            }
//        }
//
//
//    }

}
