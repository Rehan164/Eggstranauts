import javax.swing.*;

import Sprites.Player;
import Sprites.Floor;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Game extends JPanel{

    private Timer timer;  // fires an event to trigger updating the animation.
    private boolean[] keys;

    private Player player;
    private Player player2;
    private Floor floor, floor2;

    public Game(int w, int h) {
        setSize(w, h);

        keys = new boolean[256];
        timer = new Timer(1000/60, e-> update());
        timer.start();

        
        player = new Player(new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB), new Point(200, 100));
        player2 = new Player(new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB), new Point(800, 100));
        floor = new Floor ( new BufferedImage(400,100,BufferedImage.TYPE_INT_ARGB), new Point (0,400));
        floor2 = new Floor(new BufferedImage(400,100,BufferedImage.TYPE_INT_ARGB), new Point (600,400));
        
        setupKeys();
    }

    public void update() { // runs 60 frames per second

        if(keys[KeyEvent.VK_W]) {
            //jump
        }

        if(keys[KeyEvent.VK_D]) {
            player.move(5,0);
        }

        if(keys[KeyEvent.VK_A]) {
            player.move(-5,0);
        }


        if(keys[KeyEvent.VK_RIGHT]) {
            player2.move(5,0);
        }

        if(keys[KeyEvent.VK_LEFT]) {
            player2.move(-5,0);
        }
      
        player.fallingDown(floor);
        player2.fallingDown(floor2);

        repaint(); //refreshes the screen 
    }

    public void paintComponent(Graphics g) { //draws 
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        player2.draw(g2);

        floor.draw(g2);
        floor2.draw(g2);

    }


    public double distance(int x1, int x2, int y1, int y2) {
        return Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));
    }
    /*public boolean detectCollision() {
        //If distance < radius of circle than they have collided
        int radius, centerX, centerY, nextX, nextY;

    }*/
    public void setupKeys(){
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
