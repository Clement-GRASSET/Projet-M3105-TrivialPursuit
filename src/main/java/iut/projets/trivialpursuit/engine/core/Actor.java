package iut.projets.trivialpursuit.engine.core;

import iut.projets.trivialpursuit.engine.types.Rotation;
import iut.projets.trivialpursuit.engine.types.Vector2D;

/**
 * Un acteur à placer sur la scène.
 */
public abstract class Actor extends GameObject {

    private Vector2D position;
    private Vector2D scale;
    private Rotation rotation;
    private int renderOrder;
    private boolean allowRender;

    private Material material;

    private Scene scene;

    /**
     * Construit un acteur.
     */
    protected Actor() {
        this.position = new Vector2D(0,0);
        this.scale = new Vector2D(1,1);
        this.rotation = Rotation.rad(0);
        this.renderOrder = 0;
        setMaterial( new Material() );
        allowRender = true;
    }

    /**
     * Renvoie la position de l'acteur.
     * @return La position de l'acteur.
     */
    public Vector2D getPosition() {
        return position;
    }

    /**
     * Définit la position de l'acteur.
     * @param position La position de l'acteur.
     */
    public void setPosition(Vector2D position) {
        this.position = position;
    }

    /**
     * Renvoie la taille de l'acteur.
     * @return La taille de l'acteur.
     */
    public Vector2D getScale() {
        return scale;
    }

    /**
     * Définit la taille de l'acteur.
     * @param scale La taille de l'acteur.
     */
    public void setScale(Vector2D scale) {
        this.scale = scale;
    }

    /**
     * Renvoie la rotation de l'acteur.
     * @return La rotation de l'acteur.
     */
    public Rotation getRotation() {
        return rotation;
    }

    /**
     * Définit la rotation de l'acteur.
     * @param rotation La rotation de l'acteur.
     */
    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    /**
     * Renvoie le matériau utilisé lors du rendu.
     * @return Le matériau utilisé lors du rendu.
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Définit le matériau utilisé lors du rendu.
     * @param material Le matériau utilisé lors du rendu.
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * Renvoie la scène dans laquelle se trouve l'acteur.
     * @return La scène dans laquelle se trouve l'acteur.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Définit la scène dans laquelle se trouve l'acteur.
     * @param scene La scène dans laquelle se trouve l'acteur.
     */
    void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Renvoie l'ordre dans lequel l'acteur est rendu dans la scène.
     * @return L'ordre dans lequel l'acteur est rendu dans la scène.
     */
    public int getRenderOrder() {
        return renderOrder;
    }

    /**
     * Définit l'ordre dans lequel l'acteur est rendu dans la scène.
     * @param renderOrder L'ordre dans lequel l'acteur est rendu dans la scène.
     */
    public void setRenderOrder(int renderOrder) {
        this.renderOrder = renderOrder;
    }

    /**
     * Renvoie vrai si l'acteur est rendu dans la scène.
     * @return Vrai si l'acteur est rendu dans la scène.
     */
    public boolean getAllowRender() {
        return allowRender;
    }

    /**
     * Définit si l'acteur est rendu dans la scène.
     * @param allowRender Vrai si l'acteur est rendu dans la scène.
     */
    public void setAllowRender(boolean allowRender) {
        this.allowRender = allowRender;
    }
}
