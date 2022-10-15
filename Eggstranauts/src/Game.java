import javax.swing.*;

import Sprites.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Game extends JPanel{

    private Timer timer;  // fires an event to trigger updating the animation.
    private boolean[] keys;

    private Player player;
    private Player player2;
 //   private Floor floor;

    public Game(int w, int h) {
        setSize(w, h);

        keys = new boolean[256];
        timer = new Timer(1000/60, e-> update());
        timer.start();

       player = new Player(new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB), new Point(100, 100));
       player2 = new Player(new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB), new Point(300, 100));
     //  floor = new Floor(new BufferedImage(800,100,BufferedImage.TYPE_INT_ARGB), new Point (0,100));
    }

    public void update() { // runs 60 frames per second

        if(keys[KeyEvent.VK_W]) {
            System.out.println("hello");
        }
        player.move(2,0);
        if (player.getX() == player2.getX()) { //Ideally group that with any obj sprite
            System.out.println("GRAAAH");
            player.setLocation(player.getX()-200, player.getY());
        }
      
        repaint(); //refresses the screen 
    }

    public void paintComponent(Graphics g) { //draws 
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        player2.draw(g2);
        g2.drawRect(100, 100, 100, 100);
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
