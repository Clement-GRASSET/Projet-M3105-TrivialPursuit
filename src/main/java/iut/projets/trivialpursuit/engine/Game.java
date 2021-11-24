package iut.projets.trivialpursuit.engine;

import iut.projets.trivialpursuit.engine.graphics.Scene;

public abstract class Game {

    public abstract void start();

    protected void setName(String name) {
        Engine.getGameWindow().setTitle(name);
    }
}
