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

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
