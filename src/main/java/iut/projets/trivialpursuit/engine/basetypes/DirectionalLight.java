package iut.projets.trivialpursuit.engine.basetypes;

import iut.projets.trivialpursuit.engine.core.Light;
import iut.projets.trivialpursuit.engine.types.Vector3D;

public class DirectionalLight extends Light {

    private Vector3D direction;

    public DirectionalLight() {
        super();
        direction = new Vector3D(0,0,1);
    }

    public Vector3D getDirection() {
        return direction;
    }

    public void setDirection(Vector3D direction) {
        this.direction = Vector3D.normalize(direction);
    }

}
