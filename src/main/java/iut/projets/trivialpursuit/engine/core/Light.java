package iut.projets.trivialpursuit.engine.core;

import iut.projets.trivialpursuit.engine.types.Vector3D;

/**
 * représente une lumière à ajouter dans la scène.
 */
public abstract class Light {

    private Vector3D intensity;

    /**
     * Construit une lumière
     */
    public Light() {
        intensity = new Vector3D(1,1,1);
    }

    /**
     * Renvoie l'intensité de la lumière.
     * Rouge = x.
     * Vert = y.
     * Bleu = z.
     * @return L'intensité de la lumière.
     */
    public Vector3D getIntensity() {
        return intensity;
    }

    /**
     * Définit l'intensité de la lumière.
     * Rouge = x.
     * Vert = y.
     * Bleu = z.
     * @param intensity L'intensité de la lumière.
     */
    public void setIntensity(Vector3D intensity) {
        double r = (intensity.getX() > 0) ? intensity.getX() : 0;
        double g = (intensity.getY() > 0) ? intensity.getY() : 0;
        double b = (intensity.getZ() > 0) ? intensity.getZ() : 0;
        this.intensity = new Vector3D(r, g, b);
    }

    /**
     * Définit l'intensité de la lumière.
     * @param intensity L'intensité de la lumière ( = new Vector3D(intensity, intensity, intensity) ).
     */
    public void setIntensity(double intensity) {
        double value = (intensity > 0) ? intensity : 0;
        this.intensity = new Vector3D(value, value, value);
    }
}
