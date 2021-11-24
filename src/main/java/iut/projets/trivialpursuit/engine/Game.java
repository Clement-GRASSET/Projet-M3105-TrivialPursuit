package iut.projets.trivialpursuit.engine;

import iut.projets.trivialpursuit.engine.graphics.Scene;

public abstract class Game {

    private Scene activeScene;

    public Game() {
        activeScene = new Scene();
    }

    public abstract void start();

    protected void setName(String name) {
        Engine.getGameWindow().setTitle(name);
    }

    public Scene getActiveScene() {
        return activeScene;
    }

    public void setActiveScene(Scene activeScene) {
        this.activeScene = activeScene;
    }
}
