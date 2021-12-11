package iut.projets.trivialpursuit.engine.types;

import java.awt.*;

/**
 * Un vecteur à trois dimensions.
 */
public class Vector3D {

    private final double x, y, z;

    /**
     * Construit un vecteur 3D
     * @param x Composante x.
     * @param y Composante y.
     * @param z Composante z.
     */
    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Construit un vecteur 3D à partir d'une couleur.
     * X = Rouge
     * Y = Vert
     * Z = Bleu
     * @param color La couleur à convertir en vecteur 3D.
     */
    public Vector3D(Color color) {
        this.x = color.getRed()/255.0;
        this.y = color.getGreen()/255.0;
        this.z = color.getBlue()/255.0;
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
     * Renvoie la composante Z du vecteur.
     * @return La composante Z du vecteur.
     */
    public double getZ() {
        return z;
    }

    /**
     * Renvoie la longueur du vecteur.
     * @return La longueur du vecteur.
     */
    public double length() {
        return Math.sqrt(x*x + y*y + z*z);
    }

    /**
     * Calcule la somme de deux vecteurs.
     * @param vector1 Vecteur 1.
     * @param vector2 Vecteur 2.
     * @return vector1 + vector2.
     */
    public static Vector3D add(Vector3D vector1, Vector3D vector2) {
        return new Vector3D(
                vector1.x + vector2.x,
                vector1.y + vector2.y,
                vector1.z + vector2.z
        );
    }

    /**
     * Multiplie un vecteur par un coefficient.
     * @param vector Le vecteur à multiplier.
     * @param value Le coefficient par lequel multiplier le vecteur.
     * @return Vector * value.
     */
    public static Vector3D multiply(Vector3D vector, double value) {
        return new Vector3D(
                vector.getX() * value,
                vector.getY() * value,
                vector.getZ() * value
        );
    }

    /**
     * Calcule le produit scalaire entre deux vecteurs.
     * @param vector1 Vecteur 1.
     * @param vector2 Vecteur 2.
     * @return Le produit scalaire de vector1 et vector2.
     */
    public static double dot(Vector3D vector1, Vector3D vector2) {
        return vector1.x*vector2.x + vector1.y*vector2.y + vector1.z*vector2.z;
    }

    /**
     * Normalize un vecteur de sorte que sa longueur soit égale à 1.
     * @param vector Le vecteur à normaliser.
     * @return Le vecteur normalisé.
     */
    public static Vector3D normalize(Vector3D vector) {
        double length = vector.length();
        return new Vector3D(
                vector.x/length,
                vector.y/length,
                vector.z/length
        );
    }
}
