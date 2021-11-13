package iut.projets.trivialpursuit.engine.window;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.graphics.SceneRenderer;
import iut.projets.trivialpursuit.engine.types.Vector2D;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class RenderCanvas extends Canvas implements MouseListener, MouseMotionListener {

    SceneRenderer sceneRenderer;

    RenderCanvas() {
        setBackground(Color.BLACK);
        sceneRenderer = Engine.getSceneRenderer();
        Engine.getSceneRenderer().setResolution(getWidth(), getHeight());
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

    private void updateMousePosition(MouseEvent e) {
        Engine.getActiveScene().setMousePosition(new Vector2D(e.getX(), e.getY()));
        Engine.getUserInterface().setMousePosition(e.getX(), e.getY());
    }

    private void updateMousePressed(boolean pressed) {
        Engine.getUserInterface().setMousePressed(pressed);
    }
}
