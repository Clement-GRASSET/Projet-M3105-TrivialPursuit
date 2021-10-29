package iut.projets.trivialpursuit.engine.types;

public class Rotation {

    private double rotation;

    private Rotation(double radians) {
        this.rotation = radians;
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

    public static Rotation rad(double radians) {
        return new Rotation(radians);
    }

    public static Rotation deg(double degrees) {
        return new Rotation(Math.toRadians(degrees));
    }
}
