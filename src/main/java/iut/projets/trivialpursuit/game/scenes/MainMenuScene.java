package iut.projets.trivialpursuit.game.scenes;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.graphics.Scene;
import iut.projets.trivialpursuit.engine.userinterface.UIButton;
import iut.projets.trivialpursuit.game.assets.ui.MainMenu;

public class MainMenuScene extends Scene {

    public MainMenuScene() {
        super();

        //Engine.getAudioManager().playMusic("origamiKingBB.wav", true, 2.18181818);
        //Engine.getAudioManager().playMusic("origamiKingBBT.wav", true, 2.18181818);

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
