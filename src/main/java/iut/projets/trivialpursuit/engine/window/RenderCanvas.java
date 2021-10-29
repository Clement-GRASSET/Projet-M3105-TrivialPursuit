package iut.projets.trivialpursuit.engine.window;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.graphics.SceneRenderer;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class RenderCanvas extends Canvas implements MouseListener, MouseMotionListener {

    SceneRenderer sceneRenderer;

    RenderCanvas() {
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
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        updateMousePosition(e);
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
        Engine.getActiveScene().setMousePosition(e.getPoint());
        Engine.getUserInterface().setMousePosition(e.getX(), e.getY());
    }
}
