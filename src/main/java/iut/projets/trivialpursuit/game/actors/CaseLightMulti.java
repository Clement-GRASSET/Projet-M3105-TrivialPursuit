package iut.projets.trivialpursuit.game.actors;

import iut.projets.trivialpursuit.engine.basetypes.Delay;
import iut.projets.trivialpursuit.engine.basetypes.PointLight;
import iut.projets.trivialpursuit.engine.types.Rotation;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.types.Vector3D;
import iut.projets.trivialpursuit.game.TrivialPursuitColor;

public class CaseLightMulti extends CaseLight {

    private final PointLight [] lights;
    private double time;

    public CaseLightMulti() {
        super();
        lights = new PointLight[6];
        for (int i = 0; i < 6; i++) {
            lights[i] = new PointLight();
            lights[i].setHeight(8);
            lights[i].setIntensity(new Vector3D(0,0,0));
        }
        time = 0;
    }

    @Override
    public void start() {

    }

    @Override
    public void update(double frameTime) {
        time+=frameTime;
        for (int i = 0; i < 6; i++) {
            Rotation rotation = Rotation.deg(i*60 + time*100);
            lights[i].setPosition(new Vector2D(
                    Math.cos(rotation.getRad()) * 10,
                    Math.sin(rotation.getRad()) * 10
            ));
        }
    }

    @Override
    public void show() {
        for (PointLight light : lights) {
            getScene().addLight(light);
        }
        setLightIntensity(lights[0], colorToVector(TrivialPursuitColor.BLUE), 0.5, null);
        setLightIntensity(lights[1], colorToVector(TrivialPursuitColor.GREEN), 0.5, null);
        setLightIntensity(lights[2], colorToVector(TrivialPursuitColor.ORANGE), 0.5, null);
        setLightIntensity(lights[3], colorToVector(TrivialPursuitColor.PINK), 0.5, null);
        setLightIntensity(lights[4], colorToVector(TrivialPursuitColor.PURPLE), 0.5, null);
        setLightIntensity(lights[5], colorToVector(TrivialPursuitColor.YELLOW), 0.5, null);
    }

    @Override
    public void remove() {
        for (PointLight light : lights) {
            setLightIntensity(light, new Vector3D(0, 0, 0), 0.5, null);
        }
        Delay delay = new Delay(0.5);
        delay.onFinish(() -> {
            getScene().removeActor(this);
            for (PointLight light : lights) { getScene().removeLight(light); }
        });
        delay.start(this);
    }

}
