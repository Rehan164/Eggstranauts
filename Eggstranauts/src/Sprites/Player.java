package Sprites;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class Player extends Sprite{
 
    public Player(BufferedImage image, Point location) {

        super(image, location);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.fillRect(getX(), getY(), getWidth(), getHeight());
    }

}