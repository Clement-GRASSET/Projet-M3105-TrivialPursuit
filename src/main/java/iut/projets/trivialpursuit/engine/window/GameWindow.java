package iut.projets.trivialpursuit.engine.window;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private final Canvas canvas;

    public GameWindow() {
        setMinimumSize(new Dimension(640, 360));
        setSize(1280, 720);
        setTitle("Trivial Pursuit");
        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        canvas = new Canvas();
        setContentPane(canvas);

        setVisible(true);
    }

}
