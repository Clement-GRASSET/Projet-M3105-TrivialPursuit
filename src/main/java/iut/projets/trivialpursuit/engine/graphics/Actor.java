package iut.projets.trivialpursuit.engine.graphics;

import iut.projets.trivialpursuit.engine.game.GameObject;
import iut.projets.trivialpursuit.engine.types.Rotation;
import iut.projets.trivialpursuit.engine.types.Vector2D;

public abstract class Actor extends GameObject {

    private Vector2D position;
    private Vector2D scale;
    private Rotation rotation;
    private int renderOrder;

    private Material material;

    private Scene scene;

    protected Actor() {
        this.position = new Vector2D(0,0);
        this.scale = new Vector2D(1,1);
        this.rotation = Rotation.rad(0);
        this.renderOrder = 0;
        setMaterial( new Material() );
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Vector2D getScale() {
        return scale;
    }

    public void setScale(Vector2D scale) {
        this.scale = scale;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Scene getScene() {
        return scene;
    }

    void setScene(Scene scene) {
        this.scene = scene;
    }

    public int getRenderOrder() {
        return renderOrder;
    }

    public void setRenderOrder(int renderOrder) {
        this.renderOrder = renderOrder;
    }
}
