package iut.projets.trivialpursuit.game.scenes;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.graphics.Scene;
import iut.projets.trivialpursuit.game.questions.QuestionsManager;
import iut.projets.trivialpursuit.game.ui.MainMenu;


public class MainMenuScene extends Scene {
    QuestionsManager questions;


    public MainMenuScene() {
        super();

        //test questions XML
        QuestionsManager.load();

        //Engine.getAudioManager().playMusic("origamiKingBB.wav", true, 2.18181818);

        MainMenu mainMenuUI = new MainMenu();
        Engine.getUserInterface().addElement(mainMenuUI);
    }
}
