package iut.projets.trivialpursuit.engine.types;

public class Vector2D {

    private double x, y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public static Vector2D add(Vector2D v1, Vector2D v2) {
        return new Vector2D(v1.x + v2.x, v1.y + v2.y);
    }

    public static Vector2D subtract(Vector2D v1, Vector2D v2) {
        return new Vector2D(v1.x - v2.x, v1.y - v2.y);
    }

    public static Vector2D rotate(Vector2D vector2D, Rotation rotation) {
        double x = Math.cos(rotation.getRad()) * vector2D.x - Math.sin(rotation.getRad()) * vector2D.y;
        double y = Math.sin(rotation.getRad()) * vector2D.x + Math.cos(rotation.getRad()) * vector2D.y;
        return new Vector2D(x, y);
    }

    public static double length(Vector2D v1, Vector2D v2) {
        return Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v1.y - v2.y, 2));
    }
}
