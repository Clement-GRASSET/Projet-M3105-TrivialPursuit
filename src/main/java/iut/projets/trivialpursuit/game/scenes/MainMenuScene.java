package iut.projets.trivialpursuit.game.scenes;

import iut.projets.trivialpursuit.engine.UIManager;
import iut.projets.trivialpursuit.engine.core.Scene;
import iut.projets.trivialpursuit.game.questions.QuestionsManager;
import iut.projets.trivialpursuit.game.ui.MainMenu;


public class MainMenuScene extends Scene {
    QuestionsManager questions;


    public MainMenuScene() {
        super();

        //Engine.getAudioManager().playMusic("origamiKingBB.wav", true, 2.18181818);

        MainMenu mainMenuUI = new MainMenu();
        UIManager.addElement(mainMenuUI);
    }
}
