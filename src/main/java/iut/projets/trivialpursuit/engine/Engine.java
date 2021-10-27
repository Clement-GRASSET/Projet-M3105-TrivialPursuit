package iut.projets.trivialpursuit.engine;

import iut.projets.trivialpursuit.engine.graphics.Scene;
import iut.projets.trivialpursuit.engine.graphics.SceneRenderer;
import iut.projets.trivialpursuit.engine.window.GameWindow;

public class Engine {

    private static GameWindow gameWindow;
    private static Scene activeScene;
    private static SceneRenderer sceneRenderer;

    public static void start() {
        gameWindow = new GameWindow();
        activeScene = new Scene();
        sceneRenderer = new SceneRenderer();
    }

    public static void tick(double frameTime) {
        if (activeScene != null)
            activeScene.tick(frameTime);
    }

    public static GameWindow getGameWindow() {
        return gameWindow;
    }

    public static Scene getActiveScene() {
        return activeScene;
    }

    public static void setActiveScene(Scene scene) {
        activeScene = scene;
    }

    public static SceneRenderer getSceneRenderer() {
        return sceneRenderer;
    }
}
