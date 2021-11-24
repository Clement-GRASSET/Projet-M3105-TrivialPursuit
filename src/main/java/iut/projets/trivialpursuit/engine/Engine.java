package iut.projets.trivialpursuit.engine;

import iut.projets.trivialpursuit.engine.audio.AudioManager;
import iut.projets.trivialpursuit.engine.graphics.Scene;
import iut.projets.trivialpursuit.engine.graphics.SceneRenderer;
import iut.projets.trivialpursuit.engine.settings.Settings;
import iut.projets.trivialpursuit.engine.userinterface.UserInterface;
import iut.projets.trivialpursuit.engine.window.GameWindow;

public class Engine {

    private static Game game;
    private static GameWindow gameWindow;
    private static Scene activeScene, nextScene;
    private static SceneRenderer sceneRenderer;
    private static UserInterface userInterface;
    private static GameLoop gameLoop;
    private static Settings settings;
    private static AudioManager audioManager;

    public static void start(Game game) {
        Engine.game = game;
        activeScene = new Scene();
        nextScene = null;
        sceneRenderer = new SceneRenderer();
        userInterface = new UserInterface();
        gameWindow = new GameWindow();
        audioManager = new AudioManager();
        settings = new Settings();
        gameLoop = new GameLoop(game);
        gameLoop.start();
    }

    public static void tick(double frameTime) {
        if (nextScene != null) {
            activeScene = nextScene;
            nextScene = null;
            activeScene.start();
        }
    }

    public static Game getGame() {
        return game;
    }

    public static GameWindow getGameWindow() {
        return gameWindow;
    }

    public static Scene getActiveScene() {
        return activeScene;
    }

    public static void setActiveScene(Scene scene) {
        nextScene = scene;
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

    public static AudioManager getAudioManager() {
        return audioManager;
    }
}
