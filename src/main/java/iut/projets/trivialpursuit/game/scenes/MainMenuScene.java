package iut.projets.trivialpursuit.game.scenes;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.graphics.Scene;
import iut.projets.trivialpursuit.game.assets.ui.MainMenu;

public class MainMenuScene extends Scene {

    public MainMenuScene() {
        super();
        MainMenu mainMenuUI = new MainMenu();
        mainMenuUI.onPlayClicked(() -> {
            Engine.setActiveScene(new GameScene());
            Engine.getUserInterface().removeElement(mainMenuUI);
        });
        mainMenuUI.onQuitClicked(() -> {
            System.exit(0);
        });
        Engine.getUserInterface().addElement(mainMenuUI);
    }

}
