import javax.swing.*;
import java.awt.*;
//hello
public class Main extends JPanel{
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int width = 800;
        int height = 800;
        window.setBounds(0, 0, width, height + 22); //(x, y, w, h) 22 due to title bar.

        JPanel panel = new Game(width, height);
        panel.setFocusable(true);
        panel.grabFocus();
        panel.setBackground(Color.black);

        window.add(panel);
        window.setVisible(true);
        window.setResizable(true);
    }
}
