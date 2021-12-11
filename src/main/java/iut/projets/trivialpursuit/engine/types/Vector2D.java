package iut.projets.trivialpursuit.engine.types;

/**
 * Un vecteur à deux dimensions.
 */
public class Vector2D {

    private final double x, y;

    /**
     * Construit un vecteur 2D
     * @param x Composante x.
     * @param y Composante y.
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Renvoie la composante X du vecteur.
     * @return La composante X du vecteur.
     */
    public double getX() {
        return x;
    }

    /**
     * Renvoie la composante Y du vecteur.
     * @return La composante Y du vecteur.
     */
    public double getY() {
        return y;
    }

    /**
     * Calcule la somme de deux vecteurs.
     * @param v1 Vecteur 1.
     * @param v2 Vecteur 2.
     * @return v1 + v2.
     */
    public static Vector2D add(Vector2D v1, Vector2D v2) {
        return new Vector2D(v1.x + v2.x, v1.y + v2.y);
    }

    /**
     * Calcule la différence de deux vecteurs.
     * @param v1 Vecteur 1.
     * @param v2 Vecteur 2.
     * @return v1 - v2.
     */
    public static Vector2D subtract(Vector2D v1, Vector2D v2) {
        return new Vector2D(v1.x - v2.x, v1.y - v2.y);
    }

    /**
     * Renvoie le vecteur ayant reçu une rotation.
     * @param vector2D Le vecteur auquel appliquer la rotation.
     * @param rotation La rotation à appliquer.
     * @return Le vecteur ayant reçu une rotation.
     */
    public static Vector2D rotate(Vector2D vector2D, Rotation rotation) {
        double x = Math.cos(rotation.getRad()) * vector2D.x - Math.sin(rotation.getRad()) * vector2D.y;
        double y = Math.sin(rotation.getRad()) * vector2D.x + Math.cos(rotation.getRad()) * vector2D.y;
        return new Vector2D(x, y);
    }

    /**
     * Calcule la distance entre deux vecteurs
     * @param v1 Vecteur 1.
     * @param v2 Vecteur 2.
     * @return La distance entre v1 et v2.
     */
    public static double distance(Vector2D v1, Vector2D v2) {
        return Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v1.y - v2.y, 2));
    }

    /**
     * Calcule la longueur du vecteur.
     * @return La longueur du vecteur.
     */
    public double length() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    /**
     * Multiplie un vecteur par un coefficient.
     * @param vector Le vecteur à multiplier.
     * @param value Le coefficient par lequel multiplier le vecteur.
     * @return Vector * value.
     */
    public static Vector2D multiply(Vector2D vector, double value) {
        return new Vector2D(vector.x * value, vector.y * value);
    }

    /**
     * Normalize un vecteur de sorte que sa longueur soit égale à 1.
     * @param vector Le vecteur à normaliser.
     * @return Le vecteur normalisé.
     */
    public static Vector2D normalize(Vector2D vector) {
        return multiply(vector, 1/vector.length());
    }
}
