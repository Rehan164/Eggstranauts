package Sprites;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends Sprite{

    public Bullet(BufferedImage image, Point location) {
        super(image, location);
    }

    public void draw(Graphics2D g2) {
        g2.fillOval(getX(), getY(), getWidth(), getHeight());
    }

}
