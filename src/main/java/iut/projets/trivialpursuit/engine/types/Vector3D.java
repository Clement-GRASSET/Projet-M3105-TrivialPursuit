package iut.projets.trivialpursuit.engine.types;

import java.awt.*;

public class Vector3D {

    private double x, y, z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(Color color) {
        this.x = color.getRed()/255.0;
        this.y = color.getGreen()/255.0;
        this.z = color.getBlue()/255.0;
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

    public static Vector3D add(Vector3D vector1, Vector3D vector2) {
        return new Vector3D(
                vector1.x += vector2.x,
                vector1.y += vector2.y,
                vector1.z += vector2.z
        );
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

    public static Vector3D normalize(Vector3D vector) {
        double length = vector.length();
        return new Vector3D(
                vector.x/length,
                vector.y/length,
                vector.z/length
        );
    }
}
