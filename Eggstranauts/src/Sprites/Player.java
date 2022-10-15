package Sprites;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class Player extends Sprite{

    private int acceleration;
    private Point location;

    private boolean isGrounded;
    private int counter = 0;

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
        Rectangle otherHitBox = new Rectangle(other.getLocation().x, other.getLocation().y, other.getImage().getWidth(), other.getImage().getHeight());
        return bottomHitBox().intersects(otherHitBox);
    }

    public void move(int dx, int dy){
        super.move(dx, dy);
    }

    public void fallingDown(Sprite other) {
        if(intersectBot(other)) {
            location.translate(0, 0);
            isGrounded = true;
            isJumping = false;
            counter = 0;
            acceleration = 2;
        }
        else {     
            counter++;
            if (counter == 10) {
                acceleration++;
            }
            location.translate(0, acceleration);
        }
    }

    public void jumping(int dy) {
        if(isGrounded) {
            super.move(0, dy+dy);
            if(counter == 10) {
                isGrounded = false;
                counter = 0;
            }
            else {
                counter ++;
            }
        }
    }

    public boolean getGround() {
        return isGrounded;
    }
}