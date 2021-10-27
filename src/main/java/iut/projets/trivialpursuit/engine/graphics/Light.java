package iut.projets.trivialpursuit.engine.graphics;

import java.awt.*;

public abstract class Light {

    private Vector3D intensity;

    public Light() {
        intensity = new Vector3D(1,1,1);
    }

    public Vector3D getIntensity() {
        return intensity;
    }

    public void setIntensity(Vector3D intensity) {
        double r = (intensity.getX() > 0) ? intensity.getX() : 0;
        double g = (intensity.getY() > 0) ? intensity.getY() : 0;
        double b = (intensity.getZ() > 0) ? intensity.getZ() : 0;
        this.intensity = new Vector3D(r, g, b);
    }

    public void setIntensity(double intensity) {
        double value = (intensity > 0) ? intensity : 0;
        this.intensity = new Vector3D(intensity, intensity, intensity);
    }
}
