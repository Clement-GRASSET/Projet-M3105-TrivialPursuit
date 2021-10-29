package iut.projets.trivialpursuit;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.Game;
import iut.projets.trivialpursuit.game.scenes.StartScene;

public class TrivialPursuit extends Game {

    @Override
    public void start() {
        Engine.getSettings().setMaxFPS(30);
        Engine.getSettings().setRenderScale(0.85);
        Engine.getGameLoop().setDebug(true);

        Engine.setActiveScene(new StartScene());
    }

    public static void main(String[] args) {
        Engine.start(new TrivialPursuit());
    }

}
