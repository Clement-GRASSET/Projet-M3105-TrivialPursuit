package iut.projets.trivialpursuit.engine.core;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Material {

    private Image color;
    private Image normals;

    public Material() {
        setColor(getDefaultColor());
        setNormals(getDefaultNormals());
    }

    public Image getColor() {
        return color;
    }

    public Image getNormals() {
        return normals;
    }

    public void setColor(Image color) {
        this.color = color;
    }

    public void setNormals(Image normals) {
        this.normals = normals;
    }

    public static Image getDefaultColor() {
        BufferedImage color_img = new BufferedImage(16,16, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) color_img.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,16,16);
        g.dispose();
        return color_img;
    }

    public static Image getDefaultNormals() {
        BufferedImage normals_img = new BufferedImage(16,16, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) normals_img.getGraphics();
        g.setColor(new Color(127, 127, 255));
        g.fillRect(0,0,16,16);
        g.dispose();
        return normals_img;
    }
}
