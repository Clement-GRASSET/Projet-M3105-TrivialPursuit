package iut.projets.trivialpursuit.engine.core;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Contient la texture de couleur et de normales d'un acteur.
 */
public class Material {

    private Image color;
    private Image normals;

    /**
     * Construit un matériau.
     */
    public Material() {
        setColor(getDefaultColor());
        setNormals(getDefaultNormals());
    }

    /**
     * Renvoie la texture de couleur.
     * @return La texture de couleur.
     */
    public Image getColor() {
        return color;
    }

    /**
     * Renvoie la texture de normales.
     * @return La texture de normales.
     */
    public Image getNormals() {
        return normals;
    }

    /**
     * Définit la texture de couleur.
     * @param color La texture de couleur.
     */
    public void setColor(Image color) {
        this.color = color;
    }

    /**
     * Définit la texture de normales.
     * @param normals La texture de normales.
     */
    public void setNormals(Image normals) {
        this.normals = normals;
    }

    /**
     * Renvoie la texture de couleur par défaut.
     * @return La texture de couleur par défaut.
     */
    public static Image getDefaultColor() {
        BufferedImage color_img = new BufferedImage(16,16, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) color_img.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,16,16);
        g.dispose();
        return color_img;
    }

    /**
     * Renvoie la texture de normales par défaut.
     * @return La texture de normales par défaut.
     */
    public static Image getDefaultNormals() {
        BufferedImage normals_img = new BufferedImage(16,16, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) normals_img.getGraphics();
        g.setColor(new Color(127, 127, 255));
        g.fillRect(0,0,16,16);
        g.dispose();
        return normals_img;
    }
}
