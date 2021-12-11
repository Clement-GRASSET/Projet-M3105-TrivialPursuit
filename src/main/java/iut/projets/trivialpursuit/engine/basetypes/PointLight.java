package iut.projets.trivialpursuit.engine.basetypes;

import iut.projets.trivialpursuit.engine.core.Light;
import iut.projets.trivialpursuit.engine.types.Vector2D;

/**
 * Lumière locale.
 */
public class PointLight extends Light {

    Vector2D position;
    double radius;
    double height;

    /**
     * Construit une lumière locale.
     */
    public PointLight() {
        position = new Vector2D(0,0);
        radius = 10;
        height = 1;
    }

    /**
     * Renvoie la position de la lumière.
     * @return La position de la lumière.
     */
    public Vector2D getPosition() {
        return position;
    }

    /**
     * Définit la position de la lumière.
     * @param position Position de la lumière.
     */
    public void setPosition(Vector2D position) {
        this.position = position;
    }

    /**
     * Renvoie la hauteur de la lumière.
     * @return La hauteur de la lumière.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Définit la hauteur de la lumière.
     * @param height Hauteur de la lumière.
     */
    public void setHeight(double height) {
        this.height = Math.max(height, 0);
    }

    /**
     * Renvoie le rayon dans lequel la lumière peut être rendu à l'écran.
     * @return Le rayon dans lequel la lumière peut être rendu à l'écran.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Définit le rayon dans lequel la lumière peut être rendu à l'écran.
     * Réduire la valeur augmente les performances, mais réduit la qualité de la lumière.
     * @param radius Rayon de la lumière.
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }
}
