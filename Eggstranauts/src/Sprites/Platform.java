package Sprites;

import java.awt.*;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

public class Platform extends Sprite{
    private Rectangle plat;

    private ImageIcon platformImageIcon;

    private Image platformImage;

    public Platform(BufferedImage image, Point location, int x, int y, int w, int h, int type) {
        super(image, location);
        this.plat = new Rectangle(x, y, w, h);
        if (type == 1) {
            this.platformImageIcon = new ImageIcon(Platform.class.getResource("../res/ground.png"));
            this.platformImage = this.platformImageIcon.getImage();
        } else {
            this.platformImageIcon = new ImageIcon(Platform.class.getResource("../res/brick.png"));
            this.platformImage = this.platformImageIcon.getImage();
        }
    }

    public String toString() {
        return "plats x = " + getX() + ", y = " + getY();
    }

    public int getX() {
        return (int) this.plat.getX();
    }

    public int getY() {
        return (int) this.plat.getY();
    }

    public int getWidth() {
        return (int) this.plat.getWidth();
    }

    public int getHeight() {
        return (int) this.plat.getHeight();
    }

    public void drawSelf(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.platformImage, (int) this.plat.getX(), (int) this.plat.getY(), (int) this.plat.getWidth(),
                (int) this.plat.getHeight(), null);
    }
}
