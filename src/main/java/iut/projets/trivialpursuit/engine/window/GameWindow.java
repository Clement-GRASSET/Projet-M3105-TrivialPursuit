package iut.projets.trivialpursuit.engine.window;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private final RenderCanvas canvas;

    public GameWindow() {
        setMinimumSize(new Dimension(640, 360));
        setSize(1280, 720);
        setTitle("Trivial Pursuit");
        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        canvas = new RenderCanvas();
        add(canvas);
        setVisible(true);
        canvas.createBufferStrategy(3);
    }

    public RenderCanvas getCanvas() {
        return canvas;
    }
}
