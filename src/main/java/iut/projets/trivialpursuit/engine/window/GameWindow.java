package iut.projets.trivialpursuit.engine.window;

import javax.swing.*;
import java.awt.*;

/**
 * La fenêtre dans laquelle se trouve le jeu.
 */
public class GameWindow extends JFrame {

    private final RenderCanvas canvas;
    private boolean isFullScreen;
    private Dimension lastWindowSize;

    /**
     * Construit une fenêtre.
     */
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

    /**
     * Renvoie le canvas dans lequel faire le rendu.
     * @return Le canvas dans lequel faire le rendu.
     */
    public RenderCanvas getCanvas() {
        return canvas;
    }

    /**
     * Renvoie vrai si la fenêtre est en plein écran.
     * @return Vrai si la fenêtre est en plein écran.
     */
    public boolean isFullScreen() {
        return isFullScreen;
    }

    /**
     * Définit si la fenêtre devrait être en plein écran.
     * @param fullScreen Vrai si la fenêtre est en plein écran.
     */
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
        canvas.createBufferStrategy(2);
        isFullScreen = fullScreen;
    }
}
