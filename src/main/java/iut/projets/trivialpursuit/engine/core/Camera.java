package iut.projets.trivialpursuit.engine.core;

import iut.projets.trivialpursuit.engine.types.Rotation;
import iut.projets.trivialpursuit.engine.types.Vector2D;

/**
 * Représente le point de vue de la scène à utiliser lors du rendu.
 */
public class Camera {

    private Vector2D position;
    private Rotation rotation;
    private double zoom;

    /**
     * Construit une caméra
     */
    public Camera() {
        position = new Vector2D(0, 0);
        zoom = 1;
        rotation = Rotation.rad(0);
    }

    /**
     * Renvoie la position de la caméra.
     * @return La position de la caméra.
     */
    public Vector2D getPosition() {
        return position;
    }

    /**
     * Définit la position de la caméra.
     * @param position La position de la caméra.
     */
    public void setPosition(Vector2D position) {
        this.position = position;
    }

    /**
     * Renvoie la rotation de la caméra.
     * @return La rotation de la caméra.
     */
    public Rotation getRotation() {
        return rotation;
    }

    /**
     * Définit la rotation de la caméra.
     * @param rotation La rotation de la caméra.
     */
    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    /**
     * Renvoie le zoom de la caméra.
     * @return Le zoom de la caméra.
     */
    public double getZoom() {
        return zoom;
    }

    /**
     * Définit le zoom de la caméra.
     * @param zoom Le zoom de la caméra.
     */
    public void setZoom(double zoom) {
        this.zoom = zoom;
    }
}
