package iut.projets.trivialpursuit.game.actors.gameboard;

import iut.projets.trivialpursuit.engine.graphics.Actor;
import iut.projets.trivialpursuit.engine.graphics.Material;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.game.assets.materials.BaseMaterial;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Case extends Actor {

    private Image defaultImage, hoverImage;

    public Case() {
        setScale(new Vector2D(8,8));
        Graphics g;

        defaultImage = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
        g = defaultImage.getGraphics();
        g.setColor(Color.BLUE);
        g.fillOval(0,0,100,100);
        g.dispose();

        hoverImage = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
        g = hoverImage.getGraphics();
        g.setColor(Color.CYAN);
        g.fillOval(0,0,100,100);
        g.dispose();

        setMaterial(new BaseMaterial(defaultImage, Material.getDefaultNormals()));
        setRenderOrder(1);
    }

    @Override
    public void start() {

    }

    @Override
    public void update(double frameTime) {
        Vector2D mouse = getScene().getMousePositionInScene();
        if (Vector2D.length(getPosition(), mouse) < 4) {
            setMaterial(new BaseMaterial(hoverImage, Material.getDefaultNormals()));
        } else {
            setMaterial(new BaseMaterial(defaultImage, Material.getDefaultNormals()));
        }
    }
}
