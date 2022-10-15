import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main extends JPanel {
    // Creates the window and starts the game loop
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // Start the soundtrack
        AudioPlayer.playLoopingTrack("./SFX/battle_music.wav");

        // Create the window
        JFrame window = new JFrame();
        int width = 1000;
        int height = 500;
        window.setBounds(0, 0, width, height + 22); // (x, y, w, h) 22 due to title bar.
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the panel
        JPanel panel = new Game(width, height);

        panel.setFocusable(true);
        panel.grabFocus();
        panel.setBackground(Color.black);

        // Add the panel to the window
        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }
}
