package iut.projets.trivialpursuit.engine.basetypes;

import iut.projets.trivialpursuit.engine.core.Light;
import iut.projets.trivialpursuit.engine.types.Vector3D;

/**
 * Lumière directionnelle.
 */
public class DirectionalLight extends Light {

    private Vector3D direction;

    /**
     * Construit une lumière directionnelle.
     */
    public DirectionalLight() {
        super();
        direction = new Vector3D(0,0,1);
    }

    /**
     * Renvoie la direction normalisée de la lumière.
     * @return La direction normalisée de la lumière.
     */
    public Vector3D getDirection() {
        return direction;
    }

    /**
     * Définit la direction de la lumière.
     * La direction est normalisée.
     * @param direction Direction de la lumière.
     */
    public void setDirection(Vector3D direction) {
        this.direction = Vector3D.normalize(direction);
    }

}
