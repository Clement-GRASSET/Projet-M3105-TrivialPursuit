package iut.projets.trivialpursuit.game;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.graphics.Scene;
import iut.projets.trivialpursuit.game.scenes.GameScene;

public class Game {

    public static void start() {
        Scene scene = new GameScene();
        Engine.setActiveScene(scene);
    }

}
