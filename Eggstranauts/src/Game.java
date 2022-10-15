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

    public Game(int w, int h) {
        setSize(w, h);

        keys = new boolean[256];
        timer = new Timer(1000/60, e-> update());
        timer.start();

        //player = new Player(, new Point(100, 100));

    }

    public void update() { // runs 60 frames per second

        if(keys[KeyEvent.VK_W]) {
            System.out.println("hello");
        }
        //player.move(5,0);
        repaint(); //refresses the screen 
    }

    public void paintComponent(Graphics g) { //draws 
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        //player.draw(g2);
        g2.drawRect(100, 100, 100, 100);
    }

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
