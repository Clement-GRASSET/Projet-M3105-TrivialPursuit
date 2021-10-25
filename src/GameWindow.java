import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private Canvas canvas;

    GameWindow() {
        setMinimumSize(new Dimension(640, 360));
        setSize(1280, 720);
        setTitle("Trivial Pursuit");
        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new Canvas();
        setContentPane(canvas);

        setVisible(true);
    }

}
