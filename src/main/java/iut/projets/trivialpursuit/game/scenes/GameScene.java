package iut.projets.trivialpursuit.game.scenes;

import iut.projets.trivialpursuit.engine.graphics.*;
import iut.projets.trivialpursuit.game.actors.*;

import java.util.*;

public class GameScene extends Scene {

    FPSCounter fpsCounter;

    DirectionalLight light;
    double compteur;

    public GameScene() {
        Actor materialTestActor = addActor(MaterialTestActor.class);
        materialTestActor.setScale(new Vector2D(100,100));

        fpsCounter = (FPSCounter) addActor(FPSCounter.class);
        fpsCounter.setPosition(new Vector2D(-45, -45));

        compteur = 0;
        light = (DirectionalLight) addLight(DirectionalLight.class);
        light.setDirection(new Vector3D(0,-10,-1));
        light.setIntensity(5);

        DirectionalLight blueLight = (DirectionalLight) addLight(DirectionalLight.class);
        blueLight.setDirection(new Vector3D(3,5,-1));
        blueLight.setIntensity(new Vector3D(2,4,6));

        Timer spawnTimer = new Timer();
        spawnTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Actor testActor = addActor(TestActor.class);
                testActor.setPosition(new Vector2D(-50.0, 0.0));
            }
        }, 1000, 500);
    }

    @Override
    protected void update(double frameTime) {
        compteur += frameTime;
        light.setDirection(new Vector3D(Math.cos(compteur), Math.sin(compteur), -0.08));
    }
}
