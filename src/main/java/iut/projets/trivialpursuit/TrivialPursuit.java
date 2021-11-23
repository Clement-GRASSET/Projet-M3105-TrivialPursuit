package iut.projets.trivialpursuit;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.Game;
import iut.projets.trivialpursuit.game.scenes.StartScene;

public class TrivialPursuit extends Game {

    @Override
    public void start() {
        setName("Trivial Pursuit");
        Engine.getSettings().setMaxFPS(0);
        Engine.getSettings().setRenderScale(1);
        Engine.getGameLoop().setDebug(true);

        Engine.setActiveScene(new StartScene());
    }

    public static void main(String[] args) {
        Engine.start(new TrivialPursuit());
    }

}
