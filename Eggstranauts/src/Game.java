import javax.swing.*;

import Sprites.Player;
import Sprites.Bullet;
import Sprites.Floor;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Game extends JPanel {

    private Timer timer; // fires an event to trigger updating the animation.
    private boolean[] keys;

    private Player player;
    private Player player2;
    private Floor floor, floor2;
    private int counter;
    private boolean canShoot1;
    private int deathCounter2;

    private ArrayList<Bullet> bulletArrayList;

    public Game(int w, int h) {
        setSize(w, h);

        keys = new boolean[256];
        timer = new Timer(1000 / 60, e -> update());
        timer.start();

        player = new Player(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB), new Point(200, 100));
        player2 = new Player(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB), new Point(800, 100));
        floor = new Floor(new BufferedImage(400, 100, BufferedImage.TYPE_INT_ARGB), new Point(0, 400));
        floor2 = new Floor(new BufferedImage(400, 100, BufferedImage.TYPE_INT_ARGB), new Point(600, 400));

        bulletArrayList = new ArrayList<>();
        counter = 10;
        deathCounter2 = 0;
        canShoot1 = true;
        setupKeys();
    }

    public void update() { // runs 60 frames per second

        if (keys[KeyEvent.VK_W]) {
             player.jumping(-7);
        }

        if (keys[KeyEvent.VK_D]) {
            if (player.getGround()) {
                player.move(6, 0);
            } else {
                player.move(3, 0);
            }
        }

        if (keys[KeyEvent.VK_A]) {
            if (player.getGround()) {
                player.move(-6, 0);
            } else {
                player.move(-3, 0);
            }
        }

        if (keys[KeyEvent.VK_RIGHT]) {
            if (player2.getGround()) {
                player2.move(6, 0);
            } else {
                player2.move(3, 0);
            }
        }

        if (keys[KeyEvent.VK_LEFT]) {
            if (player2.getGround()) {
                player2.move(-6, 0);
            } else {
                player2.move(-3, 0);
            }
        }

        if (counter % 10 == 0) {
            canShoot1 = true;
        }

        if (keys[KeyEvent.VK_Q] && canShoot1) {
            bulletArrayList.add(new Bullet((new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB)),
                    new Point(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2)));
            canShoot1 = false;
            counter = 0;
        }

        for (int i = 0; i < bulletArrayList.size(); i++) {
            if (bulletArrayList.get(i).intersects(player2)) {
                bulletArrayList.remove(i);
                deathCounter2++;
                if (deathCounter2 == 3) {
                    player2.die(new Point(800, 100));
                    deathCounter2 = 0;
                }
                i--;
            }

            else if (bulletArrayList.get(i).getX() > 10000) {
                bulletArrayList.remove(i);
                i--;
            }
        }

        for (Bullet bullet : bulletArrayList) {
            bullet.move(20, 0);
        }

        player.fallingDown(floor);
        player2.fallingDown(floor2);

        counter++;

        repaint(); // refreshes the screen
    }

    public void paintComponent(Graphics g) { // draws
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        player2.draw(g2);

        for (Bullet bullet : bulletArrayList) {
            bullet.draw(g2);
        }

        floor.draw(g2);
        floor2.draw(g2);

    }

    public double distance(int x1, int x2, int y1, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
    public void setupKeys() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                keys[e.getKeyCode()] = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keys[e.getKeyCode()] = false;
            }

            @Override
            public void keyTyped(KeyEvent e) {

            }
        });
    }

}
