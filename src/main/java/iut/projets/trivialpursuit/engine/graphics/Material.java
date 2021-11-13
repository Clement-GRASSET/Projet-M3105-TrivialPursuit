package iut.projets.trivialpursuit.engine.graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Material {

    protected Image color;
    protected Image normals;

    private static Image DEFAULT_COLOR, DEFAULT_NORMALS;

    public Material() {
        Graphics2D g;

        color = new BufferedImage(16,16, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) color.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,16,16);
        g.dispose();

        normals = new BufferedImage(16,16, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) normals.getGraphics();
        g.setColor(new Color(127, 127, 255));
        g.fillRect(0,0,16,16);
        g.dispose();

        DEFAULT_COLOR = color;
        DEFAULT_NORMALS = normals;
    }

    public Image getColor() {
        return color;
    }

    public Image getNormals() {
        return normals;
    }

    public static Image getDefaultColor() {
        return DEFAULT_COLOR;
    }

    public static Image getDefaultNormals() {
        return DEFAULT_NORMALS;
    }
}
