import javax.swing.*;

import Sprites.Player;
import Sprites.Bullet;
import Sprites.Floor;
import Sprites.Platform;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.TimerTask;

public class Game extends JPanel {

    private Timer timer; // fires an event to trigger updating the animation.
    private boolean[] keys;

    private Player player;
    private Player player2;
<<<<<<< Updated upstream
    private Floor floor, floor2, platformA, platformB;
    private int counter;
    private boolean canShoot1;
    private int deathCounter2;
=======
    private Floor floor, floor2;
    private int counter, counter2;
    private boolean canShoot1, canShoot2;
    private int deathCounter1, deathCounter2;
    private boolean die1, die2;
    private int deathTimer1, deathTimer2;
>>>>>>> Stashed changes

    private Platform plat1, plat2, platA;

    private ArrayList<Bullet> bulletArrayList, bulletArrayList2;

    public Game(int w, int h) {
        setSize(w, h);
        bulletArrayList = new ArrayList<>();
        bulletArrayList2 = new ArrayList<>();

        keys = new boolean[256];
        timer = new Timer(1000 / 60, e -> update());
        timer.start();

        player = new Player(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB), new Point(200, 100));
        player2 = new Player(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB), new Point(800, 100));
        floor = new Floor(new BufferedImage(400, 100, BufferedImage.TYPE_INT_ARGB), new Point(0, 400));
        floor2 = new Floor(new BufferedImage(400, 100, BufferedImage.TYPE_INT_ARGB), new Point(600, 400));

        platformA = new Floor(new BufferedImage(200, 50, BufferedImage.TYPE_INT_ARGB), new Point(0, 250));

        plat1 = new Platform(0, 400, 400, 100, 1);
        plat2 = new Platform(600, 400, 400, 100, 1);
        platA = new Platform(0,250,200,50,2);

        counter = 10;
        deathCounter2 = 0;
        canShoot1 = true;
        counter2 = 10;
        deathCounter1 = 0;
        canShoot2 = true;
        die1 = false;
        die2 = false;

        setupKeys();
    }

    public void update() { // runs 60 frames per second

        if (keys[KeyEvent.VK_W]) {
             player.jumping(-9);
        }

        if (keys[KeyEvent.VK_UP]) {
            player2.jumping(-7);
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
        if (counter2 % 10 == 0) {
            canShoot2 = true;
        }

        if (keys[KeyEvent.VK_Q] && canShoot1 && !die1) {
            bulletArrayList.add(new Bullet((new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB)),
                    new Point(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2)));
            canShoot1 = false;
            counter = 0;
        }

        if (keys[KeyEvent.VK_SPACE] && canShoot2 && !die2) {
            bulletArrayList2.add(new Bullet((new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB)),
                    new Point(player2.getX() + player2.getWidth() / 2, player2.getY() + player2.getHeight() / 2)));
            canShoot2 = false;
            counter2 = 0;
        }

        for (int i = 0; i < bulletArrayList.size(); i++) {
            if (bulletArrayList.get(i).intersects(player2)) {
                bulletArrayList.remove(i);
                deathCounter2++;
                if (deathCounter2 == 3) {
                    player2.die();
                    die2 = true;
                    deathCounter2 = 0;
                }
                i--;
            }

            else if (bulletArrayList.get(i).getX() > 10000) {
                bulletArrayList.remove(i);
                i--;
            }
        }

        for (int i = 0; i < bulletArrayList2.size(); i++) {
            if (bulletArrayList2.get(i).intersects(player)) {
                bulletArrayList2.remove(i);
                deathCounter1++;
                if (deathCounter1 == 3) {
                    player.die();
                    die1 = true;
                    deathCounter1 = 0;
                }
                i--;
            }

            else if (bulletArrayList2.get(i).getX() < -1000) {
                bulletArrayList2.remove(i);
                i--;
            }
        }

        for (Bullet bullet : bulletArrayList) {
            bullet.move(20, 0);
        }
        for (Bullet bullet : bulletArrayList2) {
            bullet.move(-20, 0);
        }

        player.fallingDown(floor);
        player2.fallingDown(floor2);

        counter++;
        counter2 ++;

        repaint(); // refreshes the screen
    }

    public void paintComponent(Graphics g) { // draws
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if(!die1) {
            player.draw(g2);
        }
        else {
            deathTimer1 ++;
            if(deathTimer1 >= 180) {
                player = new Player(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB), new Point(200, 100));
                die1 = false;
                deathTimer1 = 0;
            }

        }
        if(!die2) {
            player2.draw(g2);
        }
        else {
            deathTimer2 ++;
            if(deathTimer2 >= 180) {
                player2 = new Player(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB), new Point(800, 100));
                die2 = false;
                deathTimer2 = 0;
            }

        }

        for (Bullet bullet : bulletArrayList) {
            bullet.draw(g2);
        }
        for (Bullet bullet : bulletArrayList2) {
            bullet.draw(g2);
        }

        floor.draw(g2);
        floor2.draw(g2);
        platformA.draw(g2);
        plat1.drawSelf(g2);
        plat2.drawSelf(g2);
        platA.drawSelf(g2);
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
