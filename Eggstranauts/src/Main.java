import javax.swing.*;
import java.awt.*;

//hello
public class Main extends JPanel {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int width = 1000;
        int height = 500;
        window.setBounds(0, 0, width, height + 22); // (x, y, w, h) 22 due to title bar.

        JPanel panel = new Game(width, height);
        panel.setFocusable(true);
        panel.grabFocus();
        panel.setBackground(Color.black);

        JLabel jlabel = new JLabel("This is a label");
        jlabel.setFont(new Font("Verdana", 1, 20));
        panel.add(jlabel);

        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }
}
