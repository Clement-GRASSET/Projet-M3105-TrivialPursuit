package iut.projets.trivialpursuit.engine.window;

import iut.projets.trivialpursuit.engine.Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel {

    long lastFrame, newFrame;

    Canvas() {
        lastFrame = System.nanoTime();
    }

    private void newFrame(Graphics2D g, double frameTime) {
        Engine.tick(frameTime);
        Engine.getSceneRenderer().setResolution(getWidth(), getHeight());
        try {
            Engine.getSceneRenderer().render(Engine.getActiveScene());
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
        BufferedImage image = Engine.getSceneRenderer().getBuffer();
        g.drawImage(image, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        newFrame = System.nanoTime();
        long deltaTime = newFrame-lastFrame;
        newFrame((Graphics2D) g, deltaTime/1000000000.0);
        lastFrame = newFrame;
        repaint();
    }
}
