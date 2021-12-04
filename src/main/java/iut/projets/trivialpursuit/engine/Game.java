package iut.projets.trivialpursuit.engine;

import iut.projets.trivialpursuit.engine.window.GameWindow;

public abstract class Game {

    private static final GameWindow window = new GameWindow();
    private static GameLoop gameLoop;

    public Game(String name) {
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

    public abstract void start();

}
