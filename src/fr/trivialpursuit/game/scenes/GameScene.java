package fr.trivialpursuit.game.scenes;

import fr.trivialpursuit.engine.graphics.*;
import fr.trivialpursuit.game.actors.*;

import java.util.*;

public class GameScene extends Scene {

    FPSCounter fpsCounter;

    public GameScene() {
        fpsCounter = (FPSCounter) addActor(FPSCounter.class);
        fpsCounter.setPosition(new Vector2D(-45, -45));

        Timer spawnTimer = new Timer();
        spawnTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Actor testActor = addActor(TestActor.class);
                testActor.setPosition(new Vector2D(-50.0, 0.0));
            }
        }, 0, 1000);
    }

    @Override
    protected void update(double frameTime) {

    }
}
