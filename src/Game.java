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

public class Game extends JPanel {

    private Timer timer; // fires an event to trigger updating the animation.
    private boolean[] keys;

    private Player player;
    private Player player2;
    private Floor floor, floor2, platformA;
    private int counter, counter2;
    private boolean canShoot1, canShoot2;
    private int deathCounter1, deathCounter2;
    private boolean die1, die2;
    private int deathTimer1, deathTimer2;

    private Platform plat1, plat2, platA, water;
    private ImageIcon skyImageIcon;
    private Image skyImage;

    private int count;

    private ImageIcon player1Icon1, player1Icon2, player1Icon3;
    private Image playerImage;

    private ImageIcon player2Icon1, player2Icon2, player2Icon3;
    private Image player2Image;

    private ArrayList<Bullet> bulletArrayList, bulletArrayList2;

    private GameTimer gameTimer;

    public Game(int w, int h) {
        setSize(w, h);
        bulletArrayList = new ArrayList<>();
        bulletArrayList2 = new ArrayList<>();

        keys = new boolean[256];
        timer = new Timer(1000 / 60, e -> update());
        timer.start();

        player = new Player(new BufferedImage(56, 100, BufferedImage.TYPE_INT_ARGB), new Point(200, 100));
        player2 = new Player(new BufferedImage(56, 100, BufferedImage.TYPE_INT_ARGB), new Point(800, 100));
        floor = new Floor(new BufferedImage(500, 100, BufferedImage.TYPE_INT_ARGB), new Point(0, 500));
        floor2 = new Floor(new BufferedImage(500, 100, BufferedImage.TYPE_INT_ARGB), new Point(700, 500));
        platformA = new Floor(new BufferedImage(200, 50, BufferedImage.TYPE_INT_ARGB), new Point(0, 250));

        plat1 = new Platform(0, 500, 500, 100, 1);
        plat2 = new Platform(700, 500, 500, 100, 1);

        platA = new Platform(0, 250, 200, 50, 2);
        water = new Platform(500, 500, 200, 100, 3);

        skyImageIcon = new ImageIcon(Game.class.getResource("images/sky.jpg"));
        skyImage = this.skyImageIcon.getImage();

        player2Icon1 = new ImageIcon(Game.class.getResource("images/sprite_0.png"));
        player2Icon2 = new ImageIcon(Game.class.getResource("images/sprite_1.png"));
        player2Icon3 = new ImageIcon(Game.class.getResource("images/sprite_2.png"));
        player2Image = player2Icon2.getImage();

        player1Icon1 = new ImageIcon(Game.class.getResource("images/sprite_0_red.png"));
        player1Icon2 = new ImageIcon(Game.class.getResource("images/sprite_1_red.png"));
        player1Icon3 = new ImageIcon(Game.class.getResource("images/sprite_2_red.png"));
        playerImage = player1Icon1.getImage();

        counter = 10;
        deathCounter2 = 0;
        canShoot1 = true;
        counter2 = 10;
        deathCounter1 = 0;
        canShoot2 = true;
        die1 = false;
        die2 = false;

        gameTimer = new GameTimer(120, this);
        gameTimer.start();

        setupKeys();
    }

    // Runs at 60 fps
    public void update() {

        // Player 1 movement
        if (keys[KeyEvent.VK_W]) {
            player.jumping(-7);
        }
        if (keys[KeyEvent.VK_S]) {
            player.setFall(true);
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

            if (count % 14 == 0) {
                playerImage = player1Icon3.getImage();
            } else if (count % 7 == 0) {
                playerImage = player1Icon2.getImage();
            }

        }

        if (keys[KeyEvent.VK_A] && player.getX() > 0) {
            if (player.getGround()) {
                player.move(-6, 0);
            } else {
                player.move(-3, 0);
            }

            if (count % 14 == 0) {
                playerImage = player1Icon3.getImage();
            } else if (count % 7 == 0) {
                playerImage = player1Icon2.getImage();
            }
        }

        // Player 2 movement
        if (keys[KeyEvent.VK_RIGHT] && player2.getX() + 70 < 1200) {
            if (player2.getGround()) {
                player2.move(6, 0);
            } else {
                player2.move(3, 0);
            }

            if (count % 14 == 0) {
                player2Image = player2Icon3.getImage();
            } else if (count % 7 == 0) {
                player2Image = player2Icon2.getImage();
            }
        }

        if (keys[KeyEvent.VK_LEFT]) {
            if (player2.getGround()) {
                player2.move(-6, 0);
            } else {
                player2.move(-3, 0);
            }

            if (count % 14 == 0) {
                player2Image = player2Icon3.getImage();
            } else if (count % 7 == 0) {
                player2Image = player2Icon2.getImage();
            }
        }

        if (counter % 10 == 0) {
            canShoot1 = true;
        }

        if (counter2 % 10 == 0) {
            canShoot2 = true;
        }

        if (keys[KeyEvent.VK_Q] && canShoot1 && !die1) {
            bulletArrayList.add(new Bullet((new BufferedImage(15, 15, BufferedImage.TYPE_INT_ARGB)),
                    new Point(player.getX() + player.getWidth() / 2, (player.getY() + player.getHeight() / 2) - 30)));
            canShoot1 = false;
            counter = 0;
        }

        if (keys[KeyEvent.VK_SPACE] && canShoot2 && !die2) {
            bulletArrayList2.add(new Bullet((new BufferedImage(15, 15, BufferedImage.TYPE_INT_ARGB)),
                    new Point(player2.getX() + player2.getWidth() / 2,
                            (player2.getY() + player2.getHeight() / 2) - 30)));
            canShoot2 = false;
            counter2 = 0;
        }

        // Determine if player 1's bullets hit player 2
        // If so, player 2 dies.
        // Delete bullets that are out of bounds.
        for (int i = bulletArrayList.size() - 1; i >= 0; i--) {
            if (bulletArrayList.get(i).intersects(player2)) {
                bulletArrayList.remove(i);
                deathCounter2++;
                player2.die();
                die2 = true;
            } else if (bulletArrayList.get(i).getX() > 10000) {
                bulletArrayList.remove(i);
            }
        }

        // Determine if player 2's bullets hit player 1
        // If so, player 1 dies.
        // Delete bullets that are out of bounds.
        for (int i = bulletArrayList2.size() - 1; i >= 0; i--) {
            if (bulletArrayList2.get(i).intersects(player)) {
                bulletArrayList2.remove(i);
                deathCounter1++;
                player.die();
                die1 = true;
            } else if (bulletArrayList2.get(i).getX() < -10000) {
                bulletArrayList2.remove(i);
            }
        }

        for (Bullet bullet : bulletArrayList) {
            bullet.move(20, 0);
        }
        for (Bullet bullet : bulletArrayList2) {
            bullet.move(-20, 0);
        }

        player.fallingDown(floor, platformA);
        player2.fallingDown(floor2, platformA);
        counter++;
        counter2++;

        if(player.getY() > 1000) {
            player.die();

            if(!die1) {
                deathCounter1++;
            }
            die1 = true;
        }

        if(player2.getY() > 1000) {
            player2.die();
            if(!die2) {
                deathCounter2++;
            }
            die2 = true;
        }

        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.skyImage, 0, 0, 1200, 500, null);

        if (!die1) {
            player.draw(g2);
            g2.drawImage(this.playerImage, player.getX(), player.getY(), null);
        } else {
            player.setLocation(-1000000, -1000000);
            deathTimer1++;
            if (deathTimer1 >= 180) {
                player = new Player(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB), new Point(200, 100));
                die1 = false;
                deathTimer1 = 0;
            }

        }
        if (!die2) {
            player2.draw(g2);
            g2.drawImage(this.player2Image, player2.getX(), player2.getY(), null);
        } else {
            player2.setLocation(10000, 100000);
            deathTimer2++;
            if (deathTimer2 >= 180) {
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
        water.drawSelf(g2);

        floor.draw(g2);
        floor2.draw(g2);
        plat1.drawSelf(g2);
        plat2.drawSelf(g2);

        // Draw a string centered on the screen.
        g2.setColor(Color.WHITE);
        g2.setFont(getFont().deriveFont(50f));
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(deathCounter2 + "-" + deathCounter1)) / 2;
        g2.drawString(deathCounter2 + "-" + deathCounter1, x, 50);

        g2.setFont(getFont().deriveFont(20f));
        FontMetrics fm2 = g2.getFontMetrics();
        String timeLeft = "" + (120 - gameTimer.secondsElapsed());
        int x2 = (getWidth() - fm2.stringWidth(timeLeft)) / 2;
        g2.drawString(timeLeft, x2, 80);
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

    public void onTimerEnd() {
        System.out.println("Game over!");
    }

}
