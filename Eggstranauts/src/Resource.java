
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

public class Resource {
    // to add an image to the environment:
    // 1. put the file into the res folder.
    // 2. Declare a variable before the static block.
    // 3. Initialize the variable by copying and pasting and modifying the
    //    ImageIO line.

    public static BufferedImage duck, ground;

    static{
        try{
            ground = ImageIO.read(new File("ground.png"));

        }catch(Exception e){e.printStackTrace();}
    }
}
