package iut.projets.trivialpursuit.engine;

public abstract class Game {

    public abstract void start();

    protected void setName(String name) {
        Engine.getGameWindow().setTitle(name);
    }

}
