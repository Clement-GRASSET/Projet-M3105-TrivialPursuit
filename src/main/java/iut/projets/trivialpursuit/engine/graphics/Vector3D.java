package iut.projets.trivialpursuit.engine.graphics;

import java.util.Vector;

public class Vector3D {

    private double x, y, z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double length() {
        return Math.sqrt(x*x + y*y + z*z);
    }

    public Vector3D add(Vector3D vector) {
        this.x += vector.x;
        this.y += vector.y;
        this.z += vector.z;
        return this;
    }

    public Vector3D multiply(double value) {
        this.x *= value;
        this.y *= value;
        this.z *= value;
        return this;
    }

    public static Vector3D multiply(Vector3D vector, double value) {
        return new Vector3D(
                vector.getX() * value,
                vector.getY() * value,
                vector.getZ() * value
        );
    }

    public static double dot(Vector3D vector1, Vector3D vector2) {
        return vector1.x*vector2.x + vector1.y*vector2.y + vector1.z*vector2.z;
    }

    public Vector3D normalize() {
        double length = this.length();
        return new Vector3D(
                this.x/length,
                this.y/length,
                this.z/length
        );
    }
}
