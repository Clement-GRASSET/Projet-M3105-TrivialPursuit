package iut.projets.trivialpursuit.engine.window;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private final RenderCanvas canvas;
    private boolean isFullScreen;
    private Dimension lastWindowSize;

    public GameWindow() {
        lastWindowSize = new Dimension(1280, 720);
        setMinimumSize(new Dimension(640, 360));
        setSize(lastWindowSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIgnoreRepaint(true);

        isFullScreen = false;
        canvas = new RenderCanvas();
        add(canvas);
        setVisible(true);
        canvas.createBufferStrategy(2);
    }

    public RenderCanvas getCanvas() {
        return canvas;
    }

    public void setFullScreen(boolean fullScreen) {
        if (isFullScreen == fullScreen) return;
        dispose();
        //setVisible(false);

        if (fullScreen) {
            lastWindowSize = getSize();
            setSize(Toolkit.getDefaultToolkit().getScreenSize());
        } else {
            setSize(lastWindowSize);
        }
        setLocationRelativeTo(null);
        setUndecorated(fullScreen);
        setVisible(true);
        isFullScreen = fullScreen;
    }
}
