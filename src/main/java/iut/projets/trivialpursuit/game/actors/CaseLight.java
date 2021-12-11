package iut.projets.trivialpursuit.game.actors;

import iut.projets.trivialpursuit.engine.basetypes.Animation;
import iut.projets.trivialpursuit.engine.basetypes.Keyframe;
import iut.projets.trivialpursuit.engine.core.Actor;
import iut.projets.trivialpursuit.engine.core.Light;
import iut.projets.trivialpursuit.engine.types.Vector3D;
import iut.projets.trivialpursuit.game.TrivialPursuitColor;

public abstract class CaseLight extends Actor {

    public CaseLight() {
        setAllowRender(false);
    }

    protected void setLightIntensity(Light light, Vector3D intensity, double animationDuration, Runnable then) {
        Vector3D baseIntensity = light.getIntensity();
        Animation animation = new Animation(new Keyframe[] {
                new Keyframe(0, 0),
                new Keyframe(1, animationDuration),
        });
        animation.onUpdate(() -> {
            light.setIntensity(new Vector3D(
                    interpolate(baseIntensity.getX(), intensity.getX(), animation.getValue()),
                    interpolate(baseIntensity.getY(), intensity.getY(), animation.getValue()),
                    interpolate(baseIntensity.getZ(), intensity.getZ(), animation.getValue())
            ));
        });
        animation.onFinish(() -> {
            if (then != null)
                then.run();
        });
        animation.start(this);
    }

    public abstract void show();

    public abstract void remove();

    protected Vector3D colorToVector(TrivialPursuitColor color) {
        return Vector3D.multiply(new Vector3D(color.getRGB()), 10);
    }

}
