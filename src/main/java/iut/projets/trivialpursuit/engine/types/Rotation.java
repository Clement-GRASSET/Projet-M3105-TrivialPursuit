package iut.projets.trivialpursuit.engine.types;

public class Rotation {

    private double rotation;

    public Rotation() {
        this.rotation = 0;
    }

    public void setRad(double radians) {
        rotation = radians;
    }

    public void setDeg(double degrees) {
        rotation = Math.toRadians(degrees);
    }

    public double getRad() {
        return rotation;
    }

    public double getDeg() {
        return Math.toDegrees(rotation);
    }
}
