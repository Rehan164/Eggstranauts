import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Resources {
    // to add an image to the environment:
    // 1. put the file into the res folder.
    // 2. Declare a variable before the static block.
    // 3. Initialize the variable by copying and pasting and modifying the
    //    ImageIO line.


    public static BufferedImage blue1, blue2, blue3;


    static{
        try{
            blue1 = ImageIO.read(new File("./res/sprite_0.png"));
            blue2 = ImageIO.read(new File("./res/sprite_1.png"));
            blue3 = ImageIO.read(new File("./res/sprite_2.png"));


        }catch(Exception e){e.printStackTrace();}
    }
}