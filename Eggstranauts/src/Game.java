import javax.swing.*;

import Sprites.Player;
<<<<<<< Updated upstream
import Sprites.Resource;
=======
import Sprites.Bullet;
>>>>>>> Stashed changes
import Sprites.Floor;
import Sprites.Platform;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
<<<<<<< Updated upstream
import java.awt.Image;
import javax.swing.ImageIcon;
=======
import java.util.ArrayList;
>>>>>>> Stashed changes

public class Game extends JPanel {

    private Timer timer; // fires an event to trigger updating the animation.
    private boolean[] keys;

    private Player player;
    private Player player2;

    private Sprites.Platform floor1;
    private Sprites.Platform floor2;
    private ImageIcon skyImgIcon;
    private Image skyImage;


    private ArrayList<Bullet> bulletArrayList;

    public Game(int w, int h) {
        setSize(w, h);

        keys = new boolean[256];
        timer = new Timer(1000 / 60, e -> update());
        timer.start();

<<<<<<< Updated upstream
        player = new Player(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB), new Point(200, 100));
        player2 = new Player(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB), new Point(800, 100));

        this.floor1 = new Platform(0, 400, 400, 100, 1);
        this.floor2 = new Platform(600, 400, 400, 100, 1);
        this.skyImgIcon = new ImageIcon(Game.class.getResource("./res/Sky.jpeg"));
        this.skyImage = this.skyImgIcon.getImage();

=======
        
        player = new Player(new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB), new Point(200, 100));
        player2 = new Player(new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB), new Point(800, 100));
        floor = new Floor ( new BufferedImage(400,100,BufferedImage.TYPE_INT_ARGB), new Point (0,400));
        floor2 = new Floor(new BufferedImage(400,100,BufferedImage.TYPE_INT_ARGB), new Point (600,400));

        bulletArrayList = new ArrayList<>();
        
>>>>>>> Stashed changes
        setupKeys();
    }

    public void update() { // runs 60 frames per second

        if(keys[KeyEvent.VK_W]) {
            if (player.getGround()){
                player.jumping(-7);
            }
        }

        if(keys[KeyEvent.VK_D]) {
            if (player.getGround()) {
                player.move(6,0);
            } else {
                player.move(3,0);
            }   
        }

        if(keys[KeyEvent.VK_A]) {
            if (player.getGround()) {
                player.move(-6,0);
            } else {
                player.move(-3,0);
            }
        }

        if(keys[KeyEvent.VK_RIGHT]) {
            if (player2.getGround()) {
                player2.move(6,0);
            } else {
                player2.move(3,0);
            }
        }

        if(keys[KeyEvent.VK_LEFT]) {
            if (player2.getGround()) {
                player2.move(-6,0);
            } else {
                player2.move(-3,0);
            }
        }
<<<<<<< Updated upstream
=======

        if(keys[KeyEvent.VK_Q]) {
            bulletArrayList.add(new Bullet((new BufferedImage(30,30,BufferedImage.TYPE_INT_ARGB)), new Point(player.getX() + player.getWidth()/2, player.getY() + player.getHeight()/2)));
        }


      
        player.fallingDown(floor);
        player2.fallingDown(floor2);
>>>>>>> Stashed changes

        repaint(); // refreshes the screen
    }

    public void paintComponent(Graphics g) { // draws
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g2.drawImage(skyImage, 0, 0, null);
        floor1.drawSelf(g2);
        floor2.drawSelf(g2);
        
        player.draw(g2);
        player2.draw(g2);

<<<<<<< Updated upstream
=======
        for (Bullet bullet : bulletArrayList) {
            bullet.draw(g2);
        }

        floor.draw(g2);
        floor2.draw(g2);

>>>>>>> Stashed changes
    }

    public double distance(int x1, int x2, int y1, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    /*
     * public boolean detectCollision() {
     * //If distance < radius of circle than they have collided
     * int radius, centerX, centerY, nextX, nextY;
     * 
     * }
     */
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
