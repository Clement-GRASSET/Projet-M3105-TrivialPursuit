package fr.trivialpursuit.engine.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class SceneRenderer {

    BufferedImage finalImage;

    public SceneRenderer() {
        finalImage = null;
    }

    public void render(Scene scene) {
        Graphics2D g = (Graphics2D) finalImage.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(scene.getBackgroundColor());
        int width = finalImage.getWidth();
        int height = finalImage.getHeight();
        g.fillRect(0, 0, width, height);

        Vector<Actor> actors = scene.getActors();
        for (Actor actor : actors) {
            drawActor(g, actor, scene);
        }
    }

    private void drawActor(Graphics2D g, Actor actor, Scene scene) {
        Material material = actor.getMaterial();

        double unit = finalImage.getHeight()/100.0;

        double xPosition = finalImage.getWidth()/2.0 + actor.getPosition().getX()*unit;
        double yPosition = finalImage.getHeight()/2.0 + actor.getPosition().getY()*unit;

        double xScale = actor.getScale().getX()*unit;
        double yScale = actor.getScale().getY()*unit;

        int x = (int) (xPosition - xScale/2.0);
        int y = (int) (yPosition - yScale/2.0);
        int w = (int) (xScale);
        int h = (int) (yScale);

        if (x > finalImage.getWidth() || y > finalImage.getHeight() || x+w < 0 || y+h < 0)
            return;

        g.drawImage(material.getColor(), x, y, w, h, null);
    }

    public BufferedImage getFinalImage() {
        return finalImage;
    }

    public void setResolution(int width, int height) {
        finalImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }
}
