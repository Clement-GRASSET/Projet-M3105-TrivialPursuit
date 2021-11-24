package iut.projets.trivialpursuit.game.scenes;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.graphics.Scene;
import iut.projets.trivialpursuit.game.ui.MainMenu;

public class MainMenuScene extends Scene {

    public MainMenuScene() {
        super();

        //Engine.getAudioManager().playMusic("origamiKingBB.wav", true, 2.18181818);

        MainMenu mainMenuUI = new MainMenu();
        Engine.getUserInterface().addElement(mainMenuUI);
    }
}
