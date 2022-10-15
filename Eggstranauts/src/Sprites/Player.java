package Sprites;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends Sprite {

    private int acceleration;
    private Point location;

    private boolean isGrounded;
    private int counter = 0;
    private int grav = 0;

    private int deathCount = 0;

    private boolean isJumping;
 
    public Player(BufferedImage image, Point location) {
        super(image, location);
        this.location = location;
        acceleration = 2;
        isGrounded = false;
        isJumping = false;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.fillOval(getX(), getY(), getWidth(), getHeight());
        g2.setColor(Color.red);
        g2.fillRect(bottomHitBox().x, bottomHitBox().y, bottomHitBox().width, bottomHitBox().height);
        g2.setColor(Color.GRAY);
    }

    public Rectangle bottomHitBox() {
        return new Rectangle(getX(), getY() + getHeight(), getWidth(), 2);
    }

    public boolean intersectBot(Sprite other) {
        Rectangle otherHitBox = new Rectangle(other.getLocation().x, other.getLocation().y, other.getImage().getWidth(),
                other.getImage().getHeight());
        return bottomHitBox().intersects(otherHitBox);
    }

    public void move(int dx, int dy) {
        super.move(dx, dy);
    }

    public void fallingDown(Sprite other) {
        if (intersectBot(other)) {
            location.translate(0, 0);
            isGrounded = true;
            isJumping = false;
            grav = 0;
            acceleration = 1;
        } else {
            grav++;
            if (grav >= 10) {
                acceleration++;
            }
            location.translate(0, acceleration);
        }
    }

    // "Death" is handled by hiding the player, and resetting their location after
    // some time.
    public void die() {
        deathCount++;
    }

    public int getDeathCount() {
        return deathCount;
    }

    public void jumping(int dy) {
        if (isGrounded == true) {
            super.move(0, dy + dy);
         //   isGrounded = false;
             if (counter == 10) {
                isGrounded = false;
                counter = 0;
            } else {
                counter++;
            }
        }
    }

    public boolean getGround() {
        return isGrounded;
    }
}