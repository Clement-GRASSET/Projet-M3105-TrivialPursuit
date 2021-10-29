package iut.projets.trivialpursuit.engine.graphics;

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
        this.direction = direction.normalize();
    }

}
