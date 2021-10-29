package iut.projets.trivialpursuit;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.Game;
import iut.projets.trivialpursuit.engine.graphics.Scene;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.userinterface.UIElement;
import iut.projets.trivialpursuit.game.assets.ui.FPSCounter;
import iut.projets.trivialpursuit.game.assets.ui.TestImage;
import iut.projets.trivialpursuit.game.scenes.GameScene;

public class TrivialPursuit extends Game {

    @Override
    public void start() {
        Engine.getSettings().setMaxFPS(30);
        Engine.getSettings().setRenderScale(0.85);
        Engine.getGameLoop().setDebug(true);

        Scene scene = new GameScene();
        Engine.setActiveScene(scene);
    }

    public static void main(String[] args) {
        Engine.start(new TrivialPursuit());
    }

}
