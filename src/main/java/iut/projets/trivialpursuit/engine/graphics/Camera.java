package iut.projets.trivialpursuit.engine.graphics;

import iut.projets.trivialpursuit.engine.types.Rotation;
import iut.projets.trivialpursuit.engine.types.Vector2D;

public class Camera {

    private Vector2D position;
    private Rotation rotation;
    private double zoom;

    public Camera() {
        position = new Vector2D(0, 0);
        zoom = 1.0;
        rotation = Rotation.rad(0);
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }
}
