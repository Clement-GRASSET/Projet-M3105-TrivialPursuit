package fr.trivialpursuit.engine.graphics;

import java.util.Vector;

public class Vector3D {

    private final double x, y, z;

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
        return new Vector3D(
                this.x + vector.x,
                this.y + vector.y,
                this.z + vector.z
        );
    }

    public Vector3D multiply(double value) {
        return new Vector3D(
                this.x * value,
                this.y * value,
                this.z * value
        );
    }

    public double dot(Vector3D vector) {
        return this.x*vector.x + this.y*vector.y + this.z*vector.z;
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
