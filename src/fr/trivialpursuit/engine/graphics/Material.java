package fr.trivialpursuit.engine.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Material {

    protected Image color;
    protected Image normals;

    public Material() {
        Graphics2D g;

        color = new BufferedImage(16,16, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) color.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,16,16);

        normals = new BufferedImage(16,16, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) normals.getGraphics();
        g.setColor(new Color(127, 127, 255));
        g.fillRect(0,0,16,16);
    }

    public Image getColor() {
        return color;
    }

    public Image getNormals() {
        return normals;
    }
}
