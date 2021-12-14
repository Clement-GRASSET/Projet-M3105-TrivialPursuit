package iut.projets.trivialpursuit.game.actors;

import iut.projets.trivialpursuit.engine.basetypes.PointLight;
import iut.projets.trivialpursuit.engine.types.Vector3D;
import iut.projets.trivialpursuit.game.TrivialPursuitColor;

/**
 * Lumière par défaut des cases.
 */
public class CaseLightSingle extends CaseLight {

    private final PointLight light;
    private Vector3D lightColor;

    /**
     * Construit une lumière de case
     */
    public CaseLightSingle() {
        super();
        light = new PointLight();
        light.setHeight(8);
    }

    @Override
    public void start() {

    }

    @Override
    public void update(double frameTime) {
        light.setPosition(getPosition());
    }

    @Override
    public void show() {
        getScene().addLight(light);
        setLightIntensity(light, lightColor, 0.5, null);
    }

    @Override
    public void remove() {
        setLightIntensity(light, new Vector3D(0,0,0), 0.5, () -> {
            getScene().removeLight(light);
            getScene().removeActor(this);
        });
    }

    /**
     * Définit la couleur de trivial pursuit à partir de laquelle la lumière devrait se colorer.
     * @param color Couleur de trivial pursuit.
     */
    public void setColor(TrivialPursuitColor color) {
        lightColor = colorToVector(color);
    }
}
