package iut.projets.trivialpursuit.game.assets.materials;

import iut.projets.trivialpursuit.engine.graphics.Material;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WhiteMaterial extends Material {

    public WhiteMaterial() {
        super();
        color = new BufferedImage(10,10, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) color.getGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(0,0,10,10);
    }

}
