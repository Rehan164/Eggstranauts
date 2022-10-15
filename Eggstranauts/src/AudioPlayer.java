import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.*;

public class AudioPlayer {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        playLoopingTrack("./SFX/battle_music.wav");
    }

    public static void playLoopingTrack(String path)
        throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        File file = new File(path);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        clip.start();

        // String response = scanner.next();
        // clip.close();
    }
}