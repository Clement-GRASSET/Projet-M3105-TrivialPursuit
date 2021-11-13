package iut.projets.trivialpursuit.game.actors.gameboard;

import iut.projets.trivialpursuit.engine.graphics.Actor;
import iut.projets.trivialpursuit.engine.graphics.Material;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.game.Resources;
import iut.projets.trivialpursuit.game.assets.materials.BaseMaterial;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Case extends Actor {

    private final Image defaultImage, hoverImage;

    public Case() {
        setScale(new Vector2D(8,8));
        Graphics2D g;

        defaultImage = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) defaultImage.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.BLUE);
        g.fillOval(0,0,100,100);
        g.dispose();

        hoverImage = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) hoverImage.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.CYAN);
        g.fillOval(0,0,100,100);
        g.dispose();

        Image normals = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) normals.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(127, 127, 255));
        g.fillOval(0,0,100,100);
        g.dispose();

        setMaterial(new BaseMaterial(defaultImage, normals));
        setRenderOrder(1);
    }

    @Override
    public void start() {

    }

    @Override
    public void update(double frameTime) {
        Vector2D mouse = getScene().getMousePositionInScene();
        if (Vector2D.length(getPosition(), mouse) < getScale().getX()/2) {
            ((BaseMaterial) getMaterial()).setColor(hoverImage);
        } else {
            ((BaseMaterial) getMaterial()).setColor(defaultImage);
        }
    }
}
