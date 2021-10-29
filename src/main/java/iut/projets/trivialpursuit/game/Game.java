package iut.projets.trivialpursuit.game;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.graphics.Scene;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.userinterface.UIElement;
import iut.projets.trivialpursuit.game.assets.ui.FPSCounter;
import iut.projets.trivialpursuit.game.assets.ui.TestImage;
import iut.projets.trivialpursuit.game.scenes.GameScene;

public class Game {

    public static void start() {
        Scene scene = new GameScene();
        Engine.getSettings().setMaxFPS(30);
        Engine.setActiveScene(scene);

        FPSCounter element = (FPSCounter) Engine.getUserInterface().addElement(FPSCounter.class);
        element.setAnchor(UIElement.Anchor.TOP_LEFT);
        element.setPosition(new Vector2D(2, 2));

        Engine.getUserInterface().addElement(TestImage.class);

        Engine.getGameLoop().setDebug(true);
    }

}
