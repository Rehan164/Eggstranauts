import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class AudioPlayer {
    public static void playSound(String path)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        File file = new File(path);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        clip.start();
    }
}