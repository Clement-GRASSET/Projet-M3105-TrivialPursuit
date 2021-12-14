package iut.projets.trivialpursuit.game.actors;

import iut.projets.trivialpursuit.engine.basetypes.Animation;
import iut.projets.trivialpursuit.engine.basetypes.Keyframe;
import iut.projets.trivialpursuit.engine.core.Actor;
import iut.projets.trivialpursuit.engine.core.Light;
import iut.projets.trivialpursuit.engine.types.Vector3D;
import iut.projets.trivialpursuit.game.TrivialPursuitColor;

/**
 * Acteur abstrait pour ajouter une lumière sur une case.
 */
public abstract class CaseLight extends Actor {

    /**
     * Construit une lumière de case
     */
    public CaseLight() {
        setAllowRender(false);
    }

    /**
     * Redéfinit l'intensité de la lumière.
     * @param light Lumière à modifier.
     * @param intensity Nouvelle intensité.
     * @param animationDuration Durée de la transition.
     * @param then Fonction à exécuter après.
     */
    protected void setLightIntensity(Light light, Vector3D intensity, double animationDuration, Runnable then) {
        Vector3D baseIntensity = light.getIntensity();
        Animation animation = new Animation(new Keyframe[] {
                new Keyframe(0, 0),
                new Keyframe(1, animationDuration),
        });
        animation.onUpdate(() -> {
            light.setIntensity(new Vector3D(
                    Animation.interpolate(baseIntensity.getX(), intensity.getX(), animation.getValue()),
                    Animation.interpolate(baseIntensity.getY(), intensity.getY(), animation.getValue()),
                    Animation.interpolate(baseIntensity.getZ(), intensity.getZ(), animation.getValue())
            ));
        });
        animation.onFinish(() -> {
            if (then != null)
                then.run();
        });
        animation.start(this);
    }

    /**
     * Affiche la lumière
     */
    public abstract void show();

    /**
     * Retire la lumière
     */
    public abstract void remove();

    /**
     * Transforme une couleur de trivial pursuit en vecteur 3D représentant une intensité coloré de lumière.
     * @param color Couleur de trivial pursuit.
     * @return Vecteur 3D représentant une intensité coloré de lumière.
     */
    protected Vector3D colorToVector(TrivialPursuitColor color) {
        return Vector3D.multiply(new Vector3D(color.getRGB()), 10);
    }

}
