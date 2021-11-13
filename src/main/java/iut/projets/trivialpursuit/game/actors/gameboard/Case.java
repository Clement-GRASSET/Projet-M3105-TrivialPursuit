package iut.projets.trivialpursuit.game.actors.gameboard;

import iut.projets.trivialpursuit.engine.graphics.Actor;
import iut.projets.trivialpursuit.engine.graphics.Material;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.game.assets.materials.BaseMaterial;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Case extends Actor {

    public Case() {
        setScale(new Vector2D(8,8));
        BufferedImage image = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.BLUE);
        g.fillOval(0,0,100,100);
        setMaterial(new BaseMaterial(image, Material.getDefaultNormals()));
        setRenderOrder(1);
    }

    @Override
    public void start() {

    }

    @Override
    public void update(double frameTime) {

    }
}
