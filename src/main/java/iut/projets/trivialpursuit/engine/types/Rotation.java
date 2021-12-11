package iut.projets.trivialpursuit.engine.types;

/**
 * Représente une rotation.
 */
public class Rotation {

    private double rotation;

    /**
     * Construit une rotation
     * @param radians Rotation en radians.
     */
    private Rotation(double radians) {
        this.rotation = radians;
    }

    /**
     * Renvoie la valeur de la rotation en radians.
     * @return La valeur de la rotation en radians.
     */
    public double getRad() {
        return rotation;
    }

    /**
     * Renvoie la valeur de la rotation en degrés.
     * @return La valeur de la rotation en degrés.
     */
    public double getDeg() {
        return Math.toDegrees(rotation);
    }

    /**
     * Renvoie une instance de Rotation.
     * @param radians rotation en radians.
     * @return Une instance de Rotation.
     */
    public static Rotation rad(double radians) {
        return new Rotation(radians);
    }

    /**
     * Renvoie une instance de Rotation.
     * @param degrees rotation en degrés.
     * @return Une instance de Rotation.
     */
    public static Rotation deg(double degrees) {
        return new Rotation(Math.toRadians(degrees));
    }
}
