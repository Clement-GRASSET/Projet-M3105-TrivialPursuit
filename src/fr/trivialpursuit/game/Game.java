package fr.trivialpursuit.game;

import fr.trivialpursuit.engine.Engine;
import fr.trivialpursuit.engine.graphics.Scene;
import fr.trivialpursuit.game.scenes.GameScene;

public class Game {

    public static void start() {
        Scene scene = new GameScene();
        Engine.setActiveScene(scene);
    }

}
