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
    private int grav = 0; //rate at which player falls. Increases the longer they're in the air
    private boolean fall; //checks if the player wants to drop thru a platform

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
    public void setFall(boolean set) {
        fall = set;
    }
    //Checks to see whethere or not player's bottom is touching solid ground
    //Kinda ineffecient cos the more platforms we add the more checks we hafta do. But it works.
    public void fallingDown(Sprite other, Sprite possiblePlat1) {
        if ((intersectBot(other))) {
            location.translate(0, 0);
            isGrounded = true;
            isJumping = false;
            grav = 0;
            acceleration = 1;
            fall = false;
        } else if (intersectBot(possiblePlat1) && (fall == false)) {
            location.translate(0, 0);
            isGrounded = true;
            isJumping = false;
            grav = 0;
            acceleration = 1;
        } else if (intersectBot(possiblePlat1) && (fall == true)){
          grav = 10;
          freeFall();
        } else {
           freeFall();
        }
    }
    public void freeFall() {
        grav++;
        if (grav >= 10) {
            acceleration++;
        }
        location.translate(0, acceleration);
    }
    // "Death" is handled by hiding the player, and resetting their location after
    // some time.
    public void die(Point respawnPoint) {
        setLocation(-100000000, -100000);
        deathCount++;

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                // The location
                setLocation(respawnPoint.x, respawnPoint.y);
            }
        };

        timer.schedule(task, 3000);
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