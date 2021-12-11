package iut.projets.trivialpursuit.engine.basetypes;

/**
 * Représente une image clé.
 * Utilisé par les animations.
 */
public class Keyframe {
    double value, time;

    /**
     * Construit une image clé.
     * @param value Valeur de la clé.
     * @param time Temps de la clé dans une animation.
     */
    public Keyframe(double value, double time) {
        this.value = value;
        this.time = time;
    }
}
