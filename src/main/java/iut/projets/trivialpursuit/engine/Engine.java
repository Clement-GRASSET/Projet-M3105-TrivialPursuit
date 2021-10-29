package iut.projets.trivialpursuit.engine;

import iut.projets.trivialpursuit.engine.graphics.Scene;
import iut.projets.trivialpursuit.engine.graphics.SceneRenderer;
import iut.projets.trivialpursuit.engine.settings.Settings;
import iut.projets.trivialpursuit.engine.userinterface.UserInterface;
import iut.projets.trivialpursuit.engine.window.GameWindow;

public class Engine {

    private static GameWindow gameWindow;
    private static Scene activeScene;
    private static SceneRenderer sceneRenderer;
    private static UserInterface userInterface;
    private static GameLoop gameLoop;
    private static Settings settings;

    public static void start() {
        activeScene = new Scene();
        sceneRenderer = new SceneRenderer();
        userInterface = new UserInterface();
        gameWindow = new GameWindow();
        gameLoop = new GameLoop();
        settings = new Settings();
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

    public static UserInterface getUserInterface() {
        return userInterface;
    }

    public static GameLoop getGameLoop() {
        return gameLoop;
    }

    public static Settings getSettings() {
        return settings;
    }
}
