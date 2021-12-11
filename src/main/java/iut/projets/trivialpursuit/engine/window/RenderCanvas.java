package iut.projets.trivialpursuit.engine.window;

import iut.projets.trivialpursuit.engine.SceneManager;
import iut.projets.trivialpursuit.engine.UIManager;
import iut.projets.trivialpursuit.engine.types.Vector2D;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Le canvas dans lequel on dessine le jeu.
 */
public class RenderCanvas extends Canvas implements MouseListener, MouseMotionListener {

    /**
     * Construit le canvas.
     */
    RenderCanvas() {
        setBackground(Color.BLACK);
        SceneManager.setResolution(getWidth(), getHeight());
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        updateMousePosition(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        updateMousePosition(e);
        updateMousePressed(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        updateMousePosition(e);
        updateMousePressed(false);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        updateMousePosition(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        updateMousePosition(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        updateMousePosition(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        updateMousePosition(e);
    }

    /**
     * Envoie la position de la souris au SceneManager et l'UIManager.
     * @param e Informations de la souris.
     */
    private void updateMousePosition(MouseEvent e) {
        SceneManager.setMousePosition(new Vector2D(e.getX(), e.getY()));
        UIManager.setMousePosition(new Vector2D(e.getX(), e.getY()));
    }

    /**
     * Envoie l'état du clic de la souris au SceneManager et l'UIManager.
     * @param pressed Vrai si le bouton gauche de la souris est pressé.
     */
    private void updateMousePressed(boolean pressed) {
        UIManager.setMousePressed(pressed);
    }
}
