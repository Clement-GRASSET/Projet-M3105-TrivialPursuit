package iut.projets.trivialpursuit.engine;

import iut.projets.trivialpursuit.engine.window.GameWindow;

import java.io.File;

public abstract class Game {

    private static final GameWindow window = new GameWindow();
    private static GameLoop gameLoop;
    private static String directory;

    public Game(String name, String directory) {

        Game.directory = directory;
        File file = new File(directory);
        if (!file.exists())
            file.mkdir();

        gameLoop = new GameLoop(this);
        gameLoop.start();
        Settings.load();
        Game.getWindow().setTitle(name);
    }

    public static GameWindow getWindow() {
        return window;
    }

    public static GameLoop getGameLoop() {
        return gameLoop;
    }

    public static String getDirectory() {
        return directory;
    }

    public abstract void start();

}
