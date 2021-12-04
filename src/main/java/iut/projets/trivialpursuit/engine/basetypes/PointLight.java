package iut.projets.trivialpursuit.engine.basetypes;

import iut.projets.trivialpursuit.engine.core.Light;
import iut.projets.trivialpursuit.engine.types.Vector2D;

public class PointLight extends Light {

    Vector2D position;
    double radius;

    public PointLight() {
        position = new Vector2D(0,0);
        radius = 10;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
