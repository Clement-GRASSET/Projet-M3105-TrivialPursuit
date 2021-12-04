package iut.projets.trivialpursuit.engine.basetypes;

import iut.projets.trivialpursuit.engine.core.Light;
import iut.projets.trivialpursuit.engine.types.Vector2D;

public class PointLight extends Light {

    Vector2D position;
    double radius;
    double height;

    public PointLight() {
        position = new Vector2D(0,0);
        radius = 10;
        height = 1;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = Math.max(height, 0);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
